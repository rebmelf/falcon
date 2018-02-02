package io.falcon.falcontest.repository.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.falcon.falcontest.model.TumblrAccount;
import io.falcon.falcontest.repository.TumblrAccountRepository;
import io.falcon.falcontest.repository.service.TumblrAccountRepositoryService;

import static io.falcon.falcontest.message.LogMessage.SAVING_ACCOUNT;
import static io.falcon.falcontest.message.LogMessage.SEARCH_FOR_EXISTING_ACCOUNT;
import static io.falcon.falcontest.message.LogMessage.getLogMessage;
import static java.util.Collections.singletonList;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
public class TumblrAccountRepositoryServiceImpl implements TumblrAccountRepositoryService {

  private Logger logger = LoggerFactory.getLogger(TumblrAccountRepositoryServiceImpl.class);

  @Autowired
  private TumblrAccountRepository tumblrAccountRepository;

  @Override
  @Transactional
  public TumblrAccount createTumblrAccount(final TumblrAccount tumblrAccount) {
    logger.info("Creating account:" + tumblrAccount.getName());
    return findByName(tumblrAccount.getName())
      .orElseGet(() -> saveAccount(tumblrAccount));
  }

  @Override
  public Page<TumblrAccount> findAll(final Integer page, final Integer pageSize) {
    return tumblrAccountRepository.findAll(getPageRequest(page, pageSize));
  }

  private Optional<TumblrAccount> findByName(final String name) {
    logger.info(getLogMessage(SEARCH_FOR_EXISTING_ACCOUNT, singletonList(name)));
    return tumblrAccountRepository.findByName(name);
  }

  private Pageable getPageRequest(final Integer page, final Integer pageSize) {
    Sort sort = new Sort(new Sort.Order(ASC, "id"));
    return new PageRequest(page, pageSize, sort);
  }

  private TumblrAccount saveAccount(final TumblrAccount tumblrAccount) {
    logger.info(getLogMessage(SAVING_ACCOUNT, singletonList(tumblrAccount.getName())));
    return tumblrAccountRepository.saveAndFlush(tumblrAccount);
  }
}
