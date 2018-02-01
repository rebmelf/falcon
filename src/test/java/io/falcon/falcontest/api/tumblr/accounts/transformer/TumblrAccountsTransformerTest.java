package io.falcon.falcontest.api.tumblr.accounts.transformer;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import io.falcon.falcontest.api.tumblr.accounts.entity.TumblrAccountsResponse;
import io.falcon.falcontest.model.TumblrAccount;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TumblrAccountsTransformerTest {

  @Test
  public void transform() {
    Page<TumblrAccount> page = new PageImpl<>(asList(tumblrAccount(1), tumblrAccount(2)), new PageRequest(0, 2), 2);

    final TumblrAccountsResponse transformed = new TumblrAccountsTransformer().convert(page);

    assertThat(transformed.getTotal(), is(2L));
    assertThat(transformed.getTumblrAccountResponses().size(), is(2));
  }

  private TumblrAccount tumblrAccount(Integer num) {
    return new TumblrAccount()
      .setId("ID" + num)
      .setName("Name" + num)
      .setPopularity(2)
      .setAccType("Acctype");
  }
}