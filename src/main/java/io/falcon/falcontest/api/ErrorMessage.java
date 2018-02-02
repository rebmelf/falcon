package io.falcon.falcontest.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class provides basic response json for the error handling methods of the class annotated with @ControllerAdvice
 */
public class ErrorMessage {

  @JsonProperty("errorMessage")
  private final String errorMessage;

  public ErrorMessage(final String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
