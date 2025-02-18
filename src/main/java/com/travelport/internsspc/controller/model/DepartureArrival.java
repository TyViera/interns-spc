package com.travelport.internsspc.controller.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DepartureArrival {
  @NotNull private String airport;
  @NotNull private LocalDateTime time;
  private String terminal;
}
