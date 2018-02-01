package io.falcon.falcontest.api.tumblr.accounts.entity;

public class TumblrAccountsRequest {

  private Integer page;

  private Integer next;

  public TumblrAccountsRequest setPage(final Integer page) {
    this.page = page;
    return this;
  }

  public TumblrAccountsRequest setNext(final Integer next) {
    this.next = next;
    return this;
  }

  public Integer getPage() {
    return page;
  }

  public Integer getNext() {
    return next;
  }
}
