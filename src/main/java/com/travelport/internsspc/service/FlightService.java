package com.travelport.internsspc.service;

import com.travelport.internsspc.controller.model.Flight;
import java.util.List;
import java.util.Optional;

public interface FlightService {
  List<Flight> getFlights(String departureAirports, String arrivalAirports, String departureDate);

  Optional<Flight> getFlightById(String flightId);
}
