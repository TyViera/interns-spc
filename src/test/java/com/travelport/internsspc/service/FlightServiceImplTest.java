package com.travelport.internsspc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.travelport.internsspc.repository.FlightRepository;
import com.travelport.internsspc.util.TestDataGenerator;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

  private static final String VALID_SINGLE_DEPARTURE_AIRPORT = "LAX";
  private static final String VALID_SINGLE_ARRIVAL_AIRPORT = "MXN";

  private static final String VALID_MULTIPLE_DEPARTURE_AIRPORT_JOINED = "BCN,MAD";
  private static final List<String> VALID_MULTIPLE_DEPARTURE_AIRPORT = List.of("BCN", "MAD");

  private static final String VALID_MULTIPLE_ARRIVAL_AIRPORT_JOINED = "JFK,LAX";
  private static final List<String> VALID_MULTIPLE_ARRIVAL_AIRPORT = List.of("JFK", "LAX");

  private static final String VALID_DEPARTURE_DATE = "2025-12-24T12:00:00";

  @InjectMocks private FlightServiceImpl flightService;
  @Mock private FlightRepository flightRepository;

  @Test
  @DisplayName("When receive single values for airports then return a list of flights")
  void getFlightsSuccessTest() {
    when(flightRepository.getFlights(
            List.of(VALID_SINGLE_DEPARTURE_AIRPORT),
            List.of(VALID_SINGLE_ARRIVAL_AIRPORT),
            LocalDateTime.parse(VALID_DEPARTURE_DATE)))
        .thenReturn(TestDataGenerator.createFakeFlights(7));

    var result =
        flightService.getFlights(
            VALID_SINGLE_DEPARTURE_AIRPORT, VALID_SINGLE_ARRIVAL_AIRPORT, VALID_DEPARTURE_DATE);
    assertThat(result).isNotNull().isNotEmpty();
  }

  @Test
  @DisplayName("When receive comma-separated values for airports then return a list of flights")
  void getFlightsMultipleValuesSuccessTest() {
    when(flightRepository.getFlights(
            VALID_MULTIPLE_DEPARTURE_AIRPORT,
            VALID_MULTIPLE_ARRIVAL_AIRPORT,
            LocalDateTime.parse(VALID_DEPARTURE_DATE)))
        .thenReturn(TestDataGenerator.createFakeFlights(7));

    var result =
        flightService.getFlights(
            VALID_MULTIPLE_DEPARTURE_AIRPORT_JOINED,
            VALID_MULTIPLE_ARRIVAL_AIRPORT_JOINED,
            VALID_DEPARTURE_DATE);
    assertThat(result).isNotNull().isNotEmpty();
  }

  @Test
  @DisplayName("When receive same values for airports then throws exception")
  void checkFlightsDepartureAndArrivalSuccessTest() {
    assertThrows(
        Exception.class,
        () ->
            flightService.getFlights(
                VALID_SINGLE_DEPARTURE_AIRPORT,
                VALID_SINGLE_DEPARTURE_AIRPORT,
                VALID_DEPARTURE_DATE));
  }
}
