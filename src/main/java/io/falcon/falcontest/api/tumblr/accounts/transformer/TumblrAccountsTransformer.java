package io.falcon.falcontest.api.tumblr.accounts.transformer;

import java.util.List;

import org.springframework.data.domain.Page;

import io.falcon.falcontest.api.tumblr.accounts.account.entity.TumblrAccountResponse;
import io.falcon.falcontest.api.tumblr.accounts.account.transformer.TumblrAccountTransformer;
import io.falcon.falcontest.api.tumblr.accounts.entity.TumblrAccountsResponse;
import io.falcon.falcontest.model.TumblrAccount;

import static java.util.stream.Collectors.toList;

public class TumblrAccountsTransformer {

  private TumblrAccountTransformer transformer = new TumblrAccountTransformer();

  public TumblrAccountsResponse convert(final Page<TumblrAccount> page) {
    return new TumblrAccountsResponse()
      .setTumblrAccountResponses(getItemList(page))
      .setTotal(page.getTotalElements());
  }

  private List<TumblrAccountResponse> getItemList(final Page<TumblrAccount> page) {
    return page.getContent().stream()
      .map(transformer::transform)
      .collect(toList());
  }
}
