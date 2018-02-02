package io.falcon.falcontest.repository.service.impl;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import io.falcon.falcontest.AbstractServiceTest;
import io.falcon.falcontest.model.TumblrAccount;
import io.falcon.falcontest.repository.TumblrAccountRepository;
import io.falcon.falcontest.repository.service.ServiceException;
import io.falcon.falcontest.repository.service.TumblrAccountRepositoryService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TumblrAccountRepositoryServiceTest extends AbstractServiceTest {

  @Autowired
  private TumblrAccountRepositoryService tumblrAccountRepositoryService;

  @Autowired
  private TumblrAccountRepository tumblrAccountRepository;

  @Rule
  public ExpectedException eex = ExpectedException.none();

  @Test
  public void createTumblrAccount() {
    TumblrAccount tumblrAccount = tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("testId", "name"));

    final TumblrAccount persistedAcc = tumblrAccountRepository.findOne("testId");

    assertThat(persistedAcc.getName(), is(tumblrAccount.getName()));
  }

  @Test
  public void onlyOneRecordCreatedWithTheSameName() {
    final String name = "Test Name";

    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("testId1", name));
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("testId2", name));

    final long records = tumblrAccountRepository.count();
    assertThat(records, is(1L));
  }

  @Test
  public void pagingWithValueToReturn() {
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("id1", "name1"));
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("id2", "name2"));
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("id3", "name3"));
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("id4", "name4"));
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("id5", "name5"));

    final Page<TumblrAccount> page = tumblrAccountRepositoryService.findAll(0, 4);

    assertThat(page.getTotalElements(), is(5L));
    assertThat(page.getTotalPages(), is(2));
    assertThat(page.getContent().size(), is(4));
  }

  @Test
  public void notExistingPage() {
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("id1", "name1"));
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("id2", "name2"));
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("id3", "name3"));
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("id4", "name4"));
    tumblrAccountRepositoryService.createTumblrAccount(tumblrAccount("id5", "name5"));

    final Page<TumblrAccount> page = tumblrAccountRepositoryService.findAll(2, 4);

    assertThat(page.getTotalElements(), is(5L));
    assertThat(page.getTotalPages(), is(2));
    assertThat(page.getContent().size(), is(0));
  }

  @Test
  public void pageParamIsMissing() {
    eex.expect(ServiceException.class);
    eex.expectMessage("Page and/or size parameters are missing");
    tumblrAccountRepositoryService.findAll(null, 4);
  }

  @Test
  public void sizeParamIsMissing() {
    eex.expect(ServiceException.class);
    eex.expectMessage("Page and/or size parameters are missing");
    tumblrAccountRepositoryService.findAll(2, null);
  }

  @Test
  public void allParamsMissing() {
    eex.expect(ServiceException.class);
    eex.expectMessage("Page and/or size parameters are missing");
    tumblrAccountRepositoryService.findAll(null, null);
  }

  @After
  public void deleteAccounts() {
    tumblrAccountRepository.deleteAll();
  }

  private TumblrAccount tumblrAccount(final String id, final String name) {
    return new TumblrAccount()
      .setId(id)
      .setName(name)
      .setAccType("Test Type")
      .setPopularity(3);
  }
}