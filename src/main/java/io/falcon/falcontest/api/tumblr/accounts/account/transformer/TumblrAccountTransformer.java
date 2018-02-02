package io.falcon.falcontest.api.tumblr.accounts.account.transformer;

import io.falcon.falcontest.api.tumblr.accounts.account.entity.TumblrAccountResponse;
import io.falcon.falcontest.model.TumblrAccount;

public class TumblrAccountTransformer {

  public TumblrAccountResponse transform(final TumblrAccount tumblrAccount) {
    return new TumblrAccountResponse()
      .setId(tumblrAccount.getId())
      .setName(tumblrAccount.getName())
      .setType(tumblrAccount.getAccType())
      .setPopularity(tumblrAccount.getPopularity());
  }
}
