package com.travelport.internsspc.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "flights", schema = FlightEntity.SPC_SCHEMA_NAME)
public class FlightEntity {

  public static final String SPC_SCHEMA_NAME = "spc";

  @Id private String id;

  @Column(name = "departure_airport_code")
  private String departureAirportCode;

  @Column(name = "arrival_airport_code")
  private String arrivalAirportCode;

  @Column(name = "airline_code")
  private String airlineCode;

  @OneToMany(mappedBy = "flight")
  private List<FlightSegmentEntity> segments;
}
