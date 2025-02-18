package com.travelport.internsspc.service;

import com.travelport.internsspc.controller.model.Flight;
import java.util.List;

public interface FlightService {
  List<Flight> getFlights(String departureAirports, String arrivalAirports, String departureDate);
}
