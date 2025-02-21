package com.travelport.internsspc.service;

import com.travelport.internsspc.controller.model.Flight;
import com.travelport.internsspc.repository.FlightRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

  private final FlightRepository flightRepository;

  @Override
  public List<Flight> getFlights(
      String departureAirports, String arrivalAirports, String departureDate) {
    var departureList = getAirports(departureAirports);
    var arrivalList = getAirports(arrivalAirports);
    checkAirports(departureList, arrivalList);

    return flightRepository.getFlights(
        departureList, arrivalList, LocalDateTime.parse(departureDate));
  }

  @Override
  public Optional<Flight> getFlightById(String flightId) {
    return Optional.empty();
  }

  private void checkAirports(List<String> departureAirports, List<String> arrivalAirports) {
    if (departureAirports.equals(arrivalAirports)) {
      throw new IllegalArgumentException("Departure and arrival airports cannot be the same");
    }
    boolean hasSameAirport =
        departureAirports.stream()
            .anyMatch(
                departure ->
                    arrivalAirports.stream()
                        .anyMatch(arrival -> arrival.equalsIgnoreCase(departure)));

    if (hasSameAirport) {
      throw new IllegalArgumentException("Departure and arrival airports cannot be the same");
    }
  }

  private List<String> getAirports(String airports) {
    return Arrays.asList(airports.split(","));
  }
}
