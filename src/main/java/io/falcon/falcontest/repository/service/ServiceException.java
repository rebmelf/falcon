package io.falcon.falcontest.repository.service;

/**
 * This Exception can be used to create a unified exception type for the class annotated with @RestControllerAdvice
 * In the long run, this class can be enhanced, with error code, parameterized error messages, etc.
 */
public class ServiceException extends RuntimeException {

  public ServiceException(String message) {
    super(message);
  }
}
