package io.falcon.falcontest.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTumblrAccountRequest {

  @JsonProperty
  private String name;

  @JsonProperty
  private String type;

  @JsonProperty
  private Integer popularity;

  public String getName() {
    return name;
  }

  public AddTumblrAccountRequest setName(final String name) {
    this.name = name;
    return this;
  }

  public String getType() {
    return type;
  }

  public AddTumblrAccountRequest setType(final String type) {
    this.type = type;
    return this;
  }

  public Integer getPopularity() {
    return popularity;
  }

  public AddTumblrAccountRequest setPopularity(final Integer popularity) {
    this.popularity = popularity;
    return this;
  }
}
