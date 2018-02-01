package io.falcon.falcontest.api.tumblr.accounts.account.transformer;

import org.junit.Test;

import io.falcon.falcontest.api.tumblr.accounts.account.entity.TumblrAccountResponse;
import io.falcon.falcontest.model.TumblrAccount;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TumblrAccountTransformerTest {

  @Test
  public void transform() {
    final TumblrAccountResponse transformed = new TumblrAccountTransformer().transform(tumblrAccount());

    assertThat(transformed.getId(), is("ID"));
    assertThat(transformed.getName(), is("Name"));
    assertThat(transformed.getPopularity(), is(2));
    assertThat(transformed.getType(), is("Acctype"));
  }

  private TumblrAccount tumblrAccount() {
    return new TumblrAccount()
      .setId("ID")
      .setName("Name")
      .setPopularity(2)
      .setAccType("Acctype");
  }
}