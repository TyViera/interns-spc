package com.travelport.internsspc.util;

import com.travelport.internsspc.controller.model.DepartureArrival;
import com.travelport.internsspc.controller.model.Emissions;
import com.travelport.internsspc.controller.model.Fare;
import com.travelport.internsspc.controller.model.FareProperties;
import com.travelport.internsspc.controller.model.Flight;
import com.travelport.internsspc.controller.model.Segment;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataGenerator {

  public static List<Flight> createFakeFlights(int num) {
    return IntStream.range(0, num).mapToObj(i -> createFakeFlight()).toList();
  }

  public static Flight createFakeFlight() {
    var flight = new Flight();
    flight.setIdentifier(UUID.randomUUID());
    flight.setAirlineCode("IB");
    flight.setSegments(createFakeSegments(2));
    flight.setFares(createFakeFares(2));
    return flight;
  }

  public static List<Segment> createFakeSegments(int num) {
    return IntStream.range(0, num).mapToObj(i -> createFakeSegment()).toList();
  }

  public static Segment createFakeSegment() {
    var segment = new Segment();
    segment.setIdentifier(UUID.randomUUID().toString());
    segment.setAirline("IB");
    segment.setAircraft("Airbus A320");
    segment.setArrival(createFakeSegmentLocation());
    segment.setDeparture(createFakeSegmentLocation());
    return segment;
  }

  public static DepartureArrival createFakeSegmentLocation() {
    var departureArrival = new DepartureArrival();
    departureArrival.setAirport("BCN");
    departureArrival.setTime(LocalDateTime.now());
    departureArrival.setTerminal("T2");
    return departureArrival;
  }

  public static List<Fare> createFakeFares(int num) {
    return IntStream.range(0, num).mapToObj(i -> createFakeFare()).toList();
  }

  public static Fare createFakeFare() {
    var fare = new Fare();
    fare.setIdentifier(UUID.randomUUID().toString());
    fare.setName("Tarifa plana");
    fare.setEmissions(createFakeEmissions());
    fare.setProperties(new FareProperties());
    fare.setPrice(BigDecimal.TEN);
    return fare;
  }

  public static Emissions createFakeEmissions() {
    var emissions = new Emissions();
    emissions.setCurrent(BigDecimal.ONE);
    emissions.setTypical(BigDecimal.TEN);
    return emissions;
  }
}
