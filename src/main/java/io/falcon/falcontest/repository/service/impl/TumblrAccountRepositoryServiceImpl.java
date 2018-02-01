package io.falcon.falcontest.repository.service.impl;

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
    return tumblrAccountRepository.findByName(tumblrAccount.getName())
      .orElseGet(() -> saveAccount(tumblrAccount));
  }

  @Override
  public Page<TumblrAccount> findAll(final Integer page, final Integer pageSize) {
    return tumblrAccountRepository.findAll(getPageRequest(page, pageSize));
  }

  private Pageable getPageRequest(final Integer page, final Integer pageSize) {
    Sort sort = new Sort(new Sort.Order(ASC, "id"));
    return new PageRequest(page, pageSize, sort);
  }

  private TumblrAccount saveAccount(final TumblrAccount tumblrAccount) {
    return tumblrAccountRepository.saveAndFlush(tumblrAccount);
  }
}
