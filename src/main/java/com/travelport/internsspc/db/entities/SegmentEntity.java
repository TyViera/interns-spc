package com.travelport.internsspc.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "segments", schema = FlightEntity.SPC_SCHEMA_NAME)
public class SegmentEntity {

  @Id private String id;

  @Column(name = "segment_code")
  private String segmentCode;

  @Column(name = "airline_code")
  private String airlineCode;

  @Column(name = "departure_airport_code")
  private String departureAirportCode;

  @Column(name = "departure_date")
  private LocalDateTime departureDate;

  @Column(name = "departure_terminal")
  private String departureTerminal;

  @Column(name = "arrival_airport_code")
  private String arrivalAirportCode;

  @Column(name = "arrival_date")
  private LocalDateTime arrivalDate;

  @Column(name = "arrival_terminal")
  private String arrivalTerminal;

  @OneToMany(mappedBy = "segment")
  private List<FlightSegmentEntity> flights;
}
