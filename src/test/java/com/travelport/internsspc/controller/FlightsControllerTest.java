package com.travelport.internsspc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.travelport.internsspc.controller.model.DepartureArrival;
import com.travelport.internsspc.controller.model.Emissions;
import com.travelport.internsspc.controller.model.Fare;
import com.travelport.internsspc.controller.model.FareProperties;
import com.travelport.internsspc.controller.model.Flight;
import com.travelport.internsspc.controller.model.Segment;
import com.travelport.internsspc.service.FlightService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FlightsController.class)
class FlightsControllerTest {

  @Autowired private MockMvc mvc;

  @MockitoBean private FlightService flightService;

  @Test
  @DisplayName(
      "Given all input params When search flights "
          + "Then return 200 with all existent flights including all the fields")
  void testGetFlights() throws Exception {
    var departureAirport = "BCN";
    var arrivalAirport = "MXN";
    var departureDate = "2025-08-01";

    Mockito.when(flightService.getFlights(departureAirport, arrivalAirport, departureDate))
        .thenReturn(createFakeFlights(5));

    mvc.perform(
            get("/flights")
                .queryParam("departureAirports", departureAirport)
                .queryParam("arrivalAirports", arrivalAirport)
                .queryParam("departureDate", departureDate))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].identifier").exists())
        .andExpect(jsonPath("$[0].airlineCode").exists())
        .andExpect(jsonPath("$[0].segments").exists())
        .andExpect(jsonPath("$[0].segments").isArray())
        .andExpect(jsonPath("$[0].segments[0].identifier").exists())
        .andExpect(jsonPath("$[0].segments[0].departure").exists())
        .andExpect(jsonPath("$[0].segments[0].departure.airport").exists())
        .andExpect(jsonPath("$[0].segments[0].departure.time").exists())
        .andExpect(jsonPath("$[0].segments[0].arrival").exists())
        .andExpect(jsonPath("$[0].segments[0].arrival.airport").exists())
        .andExpect(jsonPath("$[0].segments[0].arrival.time").exists())
        .andExpect(jsonPath("$[0].segments[0].airline").exists())
        .andExpect(jsonPath("$[0].segments[0].aircraft").exists())
        .andExpect(jsonPath("$[0].fares").exists())
        .andExpect(jsonPath("$[0].fares").isArray())
        .andExpect(jsonPath("$[0].fares[0].identifier").exists())
        .andExpect(jsonPath("$[0].fares[0].name").exists())
        .andExpect(jsonPath("$[0].fares[0].price").exists())
        .andExpect(jsonPath("$[0].fares[0].emissions").exists())
        .andExpect(jsonPath("$[0].fares[0].properties").exists());
  }

  @Test
  @DisplayName(
      "Given no departure airport When search flights Then return 400 with code and message")
  void testGetFlightsForNotDepartureAirport() throws Exception {
    var arrivalAirport = "MXN";
    var departureDate = "2025-08-01";

    mvc.perform(
            get("/flights")
                .queryParam("arrivalAirports", arrivalAirport)
                .queryParam("departureDate", departureDate))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").exists())
        .andExpect(jsonPath("$.code").value("ER0003"))
        .andExpect(jsonPath("$.message").exists());
  }

  @Test
  @DisplayName("Given no arrival airport When search flights Then return 400 with code and message")
  void testGetFlightsForNotArrivalAirport() throws Exception {
    var departureAirports = "BCN";// BCN,,,,,,,,
    var departureDate = "2025-08-01";

    mvc.perform(
            get("/flights")
                .queryParam("departureAirports", departureAirports)
                .queryParam("departureDate", departureDate))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").exists())
        .andExpect(jsonPath("$.code").value("ER0002"))
        .andExpect(jsonPath("$.message").exists());
  }

  @Test
  @DisplayName("Given no departure date When search flights Then return 400 with code and message")
  void testGetFlightsForNotDepartureDate() throws Exception {
    var departureAirports = "BCN";
    var arrivalAirports = "MXN";

    mvc.perform(
            get("/flights")
                .queryParam("departureAirports", departureAirports)
                .queryParam("arrivalAirports", arrivalAirports))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").exists())
        .andExpect(jsonPath("$.code").value("ER0001"))
        .andExpect(jsonPath("$.message").exists());
  }

  private static List<Flight> createFakeFlights(int num) {
    return IntStream.range(0, num).mapToObj(i -> createFakeFlight()).toList();
  }

  private static Flight createFakeFlight() {
    var flight = new Flight();
    flight.setIdentifier(UUID.randomUUID());
    flight.setAirlineCode("IB");
    flight.setSegments(createFakeSegments(2));
    flight.setFares(createFakeFares(2));
    return flight;
  }

  private static List<Segment> createFakeSegments(int num) {
    return IntStream.range(0, num).mapToObj(i -> createFakeSegment()).toList();
  }

  private static Segment createFakeSegment() {
    var segment = new Segment();
    segment.setIdentifier(UUID.randomUUID().toString());
    segment.setAirline("IB");
    segment.setAircraft("Airbus A320");
    segment.setArrival(createFakeSegmentLocation());
    segment.setDeparture(createFakeSegmentLocation());
    return segment;
  }

  private static DepartureArrival createFakeSegmentLocation() {
    var departureArrival = new DepartureArrival();
    departureArrival.setAirport("BCN");
    departureArrival.setTime(LocalDateTime.now());
    departureArrival.setTerminal("T2");
    return departureArrival;
  }

  private static List<Fare> createFakeFares(int num) {
    return IntStream.range(0, num).mapToObj(i -> createFakeFare()).toList();
  }

  private static Fare createFakeFare() {
    var fare = new Fare();
    fare.setIdentifier(UUID.randomUUID().toString());
    fare.setName("Tarifa plana");
    fare.setEmissions(createFakeEmissions());
    fare.setProperties(new FareProperties());
    fare.setPrice(BigDecimal.TEN);
    return fare;
  }

  private static Emissions createFakeEmissions() {
    var emissions = new Emissions();
    emissions.setCurrent(BigDecimal.ONE);
    emissions.setTypical(BigDecimal.TEN);
    return emissions;
  }
}
