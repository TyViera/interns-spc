package com.travelport.internsspc.db.jpa;

import static com.travelport.internsspc.util.TestDataGenerator.FAKER;
import static org.assertj.core.api.Assertions.assertThat;

import com.travelport.internsspc.db.entities.FlightEntity;
import com.travelport.internsspc.db.entities.FlightSegmentEntity;
import com.travelport.internsspc.db.entities.SegmentEntity;
import com.travelport.internsspc.util.TestDataGenerator;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// This annotation will create an embedded database ignoring properties file
// and will scan only JPA related beans, to create the required schema, I added the schema.sql file
@DataJpaTest
class FlightJpaIntegrationTest {

  @Autowired FlightJpaRepository flightJpaRepository;

  @Autowired EntityManager entityManager;

  @Test
  void test() {
    var flights = insertFlights(5);
    var segments = insertSegments(25);
    insertAssociation(flights, segments);

    var result =
        flightJpaRepository.findFlightsByConditions(
            TestDataGenerator.VALID_MULTIPLE_DEPARTURE_AIRPORT,
            TestDataGenerator.VALID_MULTIPLE_ARRIVAL_AIRPORT,
            LocalDateTime.now());

    assertThat(result).isNotNull().isNotEmpty();
  }

  private List<FlightEntity> insertFlights(int num) {
    var fligths = TestDataGenerator.createFakeFlightEntities(num);
    fligths.forEach(f -> entityManager.persist(f));
    return fligths;
  }

  private List<SegmentEntity> insertSegments(int num) {
    var segments = TestDataGenerator.createFakeSegmentsEntities(num);
    segments.forEach(f -> entityManager.persist(f));
    return segments;
  }

  private void insertAssociation(List<FlightEntity> flights, List<SegmentEntity> segments) {

    flights.forEach(
        f -> {
          // vuelo -> 3 segmentos -> 1 mañana desde bcn -> mad, segmento 2 -> en dos dias desde chg
          // -> lnd segmento 3 -> hoy desde msk -> dbd

          // n - associations
          var numberOfSegments = FAKER.number().numberBetween(1, segments.size() / 2);

          IntStream.range(0, numberOfSegments)
              .mapToObj(
                  x -> {
                    var fs = new FlightSegmentEntity();
                    fs.setId(UUID.randomUUID().toString());
                    fs.setFlight(f);
                    fs.setSegment(
                        segments.get(FAKER.number().numberBetween(0, segments.size() - 1)));
                    return fs;
                  })
              .forEach(fs -> entityManager.persist(fs));
        });
  }
}
