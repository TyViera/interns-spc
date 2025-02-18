package com.travelport.internsspc.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
@Schema(description = "Flight")
public class Flight {

  @NotNull
  @Schema(description = "Flight ID")
  private UUID identifier;

  @NotNull
  @Schema(description = "Airline code")
  private String airlineCode;

  @NotEmpty
  @Schema(description = "List of segments")
  private List<Segment> segments;

  @NotEmpty
  @Schema(description = "List of fares")
  private List<Fare> fares;
}
