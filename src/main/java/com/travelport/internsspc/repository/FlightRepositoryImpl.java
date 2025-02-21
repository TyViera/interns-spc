package com.travelport.internsspc.repository;

import com.travelport.internsspc.controller.model.Flight;
import com.travelport.internsspc.db.entities.FlightEntity;
import com.travelport.internsspc.db.jpa.FlightJpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FlightRepositoryImpl implements FlightRepository {

  private final FlightJpaRepository flightJpaRepository;

  @Override
  public List<Flight> getFlights(
      List<String> departureAirports, List<String> arrivalAirports, LocalDateTime departureDate) {
    return flightJpaRepository
        .findFlightsByConditions(departureAirports, arrivalAirports, departureDate)
        .stream()
        .map(this::fromEntity)
        .toList();
  }

  private Flight fromEntity(FlightEntity flightEntity) {
    var flight = new Flight();
    flight.setIdentifier(UUID.fromString(flightEntity.getId()));
    flight.setAirlineCode(flightEntity.getAirlineCode());
    // flight.setSegments(flightEntity.getSegments());
    return flight;
  }
}
