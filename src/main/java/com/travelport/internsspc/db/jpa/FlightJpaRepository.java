package com.travelport.internsspc.db.jpa;

import com.travelport.internsspc.db.entities.FlightEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FlightJpaRepository extends JpaRepository<FlightEntity, String> {

  // BCN, MAD, MXN
  /*
    | id |departure_airport_code |
    |----|-----------------------|
    | 1  | BCN                   |
    | 2  | MAD                   |
    | 3  | CDG                   |
    | 4  | LGH                   |
  */
  // JPQL
  // HQL
  @Query(
      """
                select f
                from FlightEntity f
                join f.segments sf
                join sf.segment s
                where f.departureAirportCode in :departureAirports
                  AND f.arrivalAirportCode in :arrivalAirports
                  AND s.departureDate >= :departureDate
          """)
  List<FlightEntity> findFlightsByConditions(
      List<String> departureAirports, List<String> arrivalAirports, LocalDateTime departureDate);
}
