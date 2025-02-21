package com.travelport.internsspc.exceptions;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
  private final String code;
  private final int httpCode;

  public ApiException(int httpCode, String code, String message) {
    super(message);
    this.code = code;
    this.httpCode = httpCode;
  }
}
