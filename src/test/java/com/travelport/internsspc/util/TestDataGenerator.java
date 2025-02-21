package com.travelport.internsspc.util;

import net.datafaker.Faker;

import com.travelport.internsspc.controller.model.DepartureArrival;
import com.travelport.internsspc.controller.model.Emissions;
import com.travelport.internsspc.controller.model.Fare;
import com.travelport.internsspc.controller.model.FareProperties;
import com.travelport.internsspc.controller.model.Flight;
import com.travelport.internsspc.controller.model.Segment;
import com.travelport.internsspc.db.entities.FlightEntity;
import com.travelport.internsspc.db.entities.SegmentEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataGenerator {

  public static final String VALID_SINGLE_DEPARTURE_AIRPORT = "LAX";
  public static final String VALID_SINGLE_ARRIVAL_AIRPORT = "MXN";

  public static final String VALID_MULTIPLE_DEPARTURE_AIRPORT_JOINED = "BCN,MAD";
  public static final List<String> VALID_MULTIPLE_DEPARTURE_AIRPORT = List.of("BCN", "MAD");

  public static final String VALID_MULTIPLE_ARRIVAL_AIRPORT_JOINED = "JFK,LAX";
  public static final List<String> VALID_MULTIPLE_ARRIVAL_AIRPORT = List.of("JFK", "LAX");

  public static final String VALID_DEPARTURE_DATE = "2025-12-24T12:00:00";
  public static final Faker FAKER = new Faker();

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

  public static List<FlightEntity> createFakeFlightEntities(int num) {
    return IntStream.range(0, num).mapToObj(i -> createFakeFlightEntity()).toList();
  }

  public static FlightEntity createFakeFlightEntity() {
    var entity = new FlightEntity();
    entity.setId(UUID.randomUUID().toString());
    entity.setArrivalAirportCode(FAKER.aviation().airport());
    entity.setDepartureAirportCode(FAKER.aviation().airport());
    return entity;
  }

  public static List<SegmentEntity> createFakeSegmentsEntities(int num) {
    return IntStream.range(0, num).mapToObj(i -> createFakeSegmentEntity()).toList();
  }

  public static SegmentEntity createFakeSegmentEntity() {
    var entity = new SegmentEntity();
    entity.setId(UUID.randomUUID().toString());
    entity.setSegmentCode(FAKER.aviation().flight());
    entity.setAirlineCode(FAKER.aviation().airline());
    entity.setDepartureAirportCode(FAKER.aviation().airport());
    entity.setDepartureDate(FAKER.date().future(2, TimeUnit.DAYS).toLocalDateTime());
    entity.setArrivalAirportCode(FAKER.aviation().airport());
    entity.setArrivalDate(FAKER.date().future(2, TimeUnit.DAYS).toLocalDateTime());
    return entity;
  }
}
