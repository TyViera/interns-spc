package com.travelport.internsspc.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Segment")
public class Segment {
  @NotNull private String identifier;
  @NotNull private DepartureArrival departure;
  @NotNull private DepartureArrival arrival;
  private String airline;
  private String aircraft;
}
