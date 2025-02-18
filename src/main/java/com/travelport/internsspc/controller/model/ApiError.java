package com.travelport.internsspc.controller.model;

import lombok.Data;

@Data
public class ApiError {
  private String code;
  private String message;
}
