package com.travelport.internsspc.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Schema(description = "Fare")
public class Fare {

  @NotNull
  private String identifier;
  private String name;
  private BigDecimal price;
  private Emissions emissions;
  private FareProperties properties;
}
