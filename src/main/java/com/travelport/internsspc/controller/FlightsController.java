package com.travelport.internsspc.controller;

import com.travelport.internsspc.controller.model.ApiError;
import com.travelport.internsspc.controller.model.Flight;
import com.travelport.internsspc.exceptions.ApiException;
import com.travelport.internsspc.exceptions.NotFoundException;
import com.travelport.internsspc.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flights")
public class FlightsController {

  private final FlightService flightService;

  @GetMapping
  @Operation(
      operationId = "getFlights",
      summary = "Get Flights TODO: complete",
      description = "Get Flights TODO: complete",
      tags = "flights",
      responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = Flight.class)))
            }),
      })
  public List<Flight> getFlights(
      @RequestParam String departureAirports,
      @RequestParam String arrivalAirports,
      @RequestParam String departureDate) {
    // -> verify data
    return flightService.getFlights(departureAirports, arrivalAirports, departureDate);
  }

  @GetMapping("/{flightId}")
  @Operation(
      operationId = "getFlightById",
      summary = "Get Flights TODO: complete",
      description = "Get Flights TODO: complete",
      tags = "flights",
      responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Flight.class))
            }),
      })
  public Flight getFlightById(@PathVariable String flightId) {
    return flightService
        .getFlightById(flightId)
        .orElseThrow(() -> new ApiException(404, "ER0004", "Flight not found"));
  }

  public ResponseEntity<Object> getFlightById2(String flightId) {
    return flightService
        .getFlightById(flightId)
        .map(this::createBody)
        .orElseGet(
            () -> {
              var error = new ApiError();
              error.setCode("ER0004");
              error.setMessage("Flight not found");
              return ResponseEntity.status(404).body(error);
            });
  }

  private ResponseEntity<Object> createBody(Object body) {
    return ResponseEntity.ok(body);
  }
}
