package io.falcon.falcontest.repository.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.falcon.falcontest.AbstractServiceTest;
import io.falcon.falcontest.model.TumblrAccount;
import io.falcon.falcontest.repository.TumblrAccountRepository;
import io.falcon.falcontest.repository.service.TumblrAccountRepositoryService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TumblrAccountRepositoryServiceTest extends AbstractServiceTest {

  @Autowired
  private TumblrAccountRepositoryService tumblrAccountRepositoryService;

  @Autowired
  private TumblrAccountRepository tumblrAccountRepository;

  @Test
  public void createTumblrAccount() {
    final String id = "testId";
    TumblrAccount tumblrAccount = tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount(id));

    final TumblrAccount persistedAcc = tumblrAccountRepository.findOne("testId");
    assertThat(persistedAcc.getName(), is(tumblrAccount.getName()));
  }

  @Test
  public void onlyOneRecordCreatedWithTheSameName() {
    final String id1 = "testId1";
    final String id2 = "testId2";

    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount(id1));
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount(id2));

    final long records = tumblrAccountRepository.count();
    assertThat(records, is(1L));
  }

  private TumblrAccount tumblrAccount(final String id) {
    return new TumblrAccount()
      .setId(id)
      .setName("Test Name")
      .setAccType("Test Type")
      .setPopularity(3);
  }
}