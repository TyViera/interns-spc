package com.travelport.internsspc.controller.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Emissions {
  private BigDecimal current;
  private BigDecimal typical;
}
