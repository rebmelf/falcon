package io.falcon.falcontest.api;

import com.fasterxml.jackson.annotation.JsonProperty;

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
