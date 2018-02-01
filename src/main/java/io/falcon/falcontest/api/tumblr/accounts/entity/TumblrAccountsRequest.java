package io.falcon.falcontest.api.tumblr.accounts.entity;

public class TumblrAccountsRequest {

  private Integer page;

  private Integer size;

  public TumblrAccountsRequest setPage(final Integer page) {
    this.page = page;
    return this;
  }

  public TumblrAccountsRequest setSize(final Integer size) {
    this.size = size;
    return this;
  }

  public Integer getPage() {
    return page;
  }

  public Integer getSize() {
    return size;
  }
}
