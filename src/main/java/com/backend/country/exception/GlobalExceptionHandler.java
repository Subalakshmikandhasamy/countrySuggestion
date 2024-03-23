package com.backend.country.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler to handle exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * Handles all unhandled exceptions.
   * Logs the error and returns a generic error response with status code 500.
   *
   * @param e The exception that occurred.
   * @return ResponseEntity with status 500 and a generic error message.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception e) {
    logger.error("An unexpected error occurred: {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
  }
}
