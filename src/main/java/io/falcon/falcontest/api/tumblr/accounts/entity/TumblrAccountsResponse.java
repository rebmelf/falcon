package io.falcon.falcontest.api.tumblr.accounts.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.falcon.falcontest.api.tumblr.accounts.account.entity.TumblrAccountResponse;

public class TumblrAccountsResponse {

  @JsonProperty("items")
  private List<TumblrAccountResponse> tumblrAccountResponses;

  @JsonProperty("total")
  private Long total;

  public List<TumblrAccountResponse> getTumblrAccountResponses() {
    return tumblrAccountResponses;
  }

  public TumblrAccountsResponse setTumblrAccountResponses(final List<TumblrAccountResponse> tumblrAccountResponses) {
    this.tumblrAccountResponses = tumblrAccountResponses;
    return this;
  }

  public Long getTotal() {
    return total;
  }

  public TumblrAccountsResponse setTotal(final Long total) {
    this.total = total;
    return this;
  }
}
