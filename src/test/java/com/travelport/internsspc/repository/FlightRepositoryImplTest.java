package com.travelport.internsspc.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.travelport.internsspc.db.jpa.FlightJpaRepository;
import com.travelport.internsspc.util.TestDataGenerator;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightRepositoryImplTest {
  @InjectMocks private FlightRepositoryImpl flightRepository;
  @Mock private FlightJpaRepository flightJpaRepository;

  @Test
  void testSuccess() {
    var departureDate = LocalDateTime.now();
    when(flightJpaRepository.findFlightsByConditions(
            TestDataGenerator.VALID_MULTIPLE_DEPARTURE_AIRPORT,
            TestDataGenerator.VALID_MULTIPLE_ARRIVAL_AIRPORT,
            departureDate))
        .thenReturn(TestDataGenerator.createFakeFlightEntities(2));

    var result =
        flightRepository.getFlights(
            TestDataGenerator.VALID_MULTIPLE_DEPARTURE_AIRPORT,
            TestDataGenerator.VALID_MULTIPLE_ARRIVAL_AIRPORT,
            departureDate);
    assertThat(result).isNotNull().isNotEmpty();
  }
}
