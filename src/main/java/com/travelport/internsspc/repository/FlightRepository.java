package com.travelport.internsspc.repository;

import com.travelport.internsspc.controller.model.Flight;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository {

  List<Flight> getFlights(
      List<String> departureAirports, List<String> arrivalAirports, LocalDateTime departureDate);
}
