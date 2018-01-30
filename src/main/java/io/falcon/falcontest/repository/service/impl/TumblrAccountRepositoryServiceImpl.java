package io.falcon.falcontest.repository.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.falcon.falcontest.model.TumblrAccount;
import io.falcon.falcontest.repository.TumblrAccountRepository;
import io.falcon.falcontest.repository.service.TumblrAccountRepositoryService;

@Service
public class TumblrAccountRepositoryServiceImpl implements TumblrAccountRepositoryService {

  private Logger logger = LoggerFactory.getLogger(TumblrAccountRepositoryServiceImpl.class);

  @Autowired
  private TumblrAccountRepository tumblrAccountRepository;

  @Override
  @Transactional
  public TumblrAccount createTumblrAccount(final TumblrAccount tumblrAccount) {
    logger.info("Creating account:" + tumblrAccount.getName());
    return tumblrAccountRepository.findByName(tumblrAccount.getName())
      .orElseGet(() -> saveAccount(tumblrAccount));
  }

  private TumblrAccount saveAccount(final TumblrAccount tumblrAccount) {
    return tumblrAccountRepository.saveAndFlush(tumblrAccount);
  }
}
