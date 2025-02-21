package com.travelport.internsspc.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "flights_segments", schema = FlightEntity.SPC_SCHEMA_NAME)
public class FlightSegmentEntity {

  @Id private String id;
  @ManyToOne private FlightEntity flight;
  @ManyToOne private SegmentEntity segment;
}
