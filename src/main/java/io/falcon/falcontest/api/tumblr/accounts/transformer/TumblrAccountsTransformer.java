package io.falcon.falcontest.api.tumblr.accounts.transformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import io.falcon.falcontest.api.tumblr.accounts.account.entity.TumblrAccountResponse;
import io.falcon.falcontest.api.tumblr.accounts.account.transformer.TumblrAccountTransformer;
import io.falcon.falcontest.api.tumblr.accounts.entity.TumblrAccountsResponse;
import io.falcon.falcontest.model.TumblrAccount;

public class TumblrAccountsTransformer {

  private TumblrAccountTransformer transformer = new TumblrAccountTransformer();

  public TumblrAccountsResponse convert(Page<TumblrAccount> page) {
    return new TumblrAccountsResponse()
      .setTumblrAccountResponses(getItemList(page))
      .setTotal(page.getTotalElements());
  }

  private List<TumblrAccountResponse> getItemList(Page<TumblrAccount> page) {
    return page.getContent().stream()
      .map(transformer::transform)
      .collect(Collectors.toList());
  }
}
