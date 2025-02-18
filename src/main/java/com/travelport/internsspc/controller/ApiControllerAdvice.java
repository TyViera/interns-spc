package com.travelport.internsspc.controller;

import com.travelport.internsspc.controller.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    var apiError = new ApiError();
    apiError.setCode(getMissingParamErrorCode(ex.getParameterName()));

    apiError.setMessage(ex.getMessage());
    return ResponseEntity.badRequest().body(apiError);
  }

  private String getMissingParamErrorCode(String parameterName) {
    return switch (parameterName) {
      case "departureAirports" -> "ER0003";
      case "arrivalAirports" -> "ER0002";
      case "departureDate" -> "ER0001";
      default -> "ER9999";
    };
  }
}
