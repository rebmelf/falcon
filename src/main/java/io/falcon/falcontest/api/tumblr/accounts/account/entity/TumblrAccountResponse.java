package io.falcon.falcontest.api.tumblr.accounts.account.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TumblrAccountResponse {

  @JsonProperty
  private String id;

  @JsonProperty
  private String name;

  @JsonProperty
  private String type;

  @JsonProperty
  private Integer popularity;

  public String getId() {
    return id;
  }

  public TumblrAccountResponse setId(final String id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public TumblrAccountResponse setName(final String name) {
    this.name = name;
    return this;
  }

  public String getType() {
    return type;
  }

  public TumblrAccountResponse setType(final String type) {
    this.type = type;
    return this;
  }

  public Integer getPopularity() {
    return popularity;
  }

  public TumblrAccountResponse setPopularity(final Integer popularity) {
    this.popularity = popularity;
    return this;
  }
}
