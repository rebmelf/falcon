package io.falcon.falcontest.repository.service;

import org.springframework.data.domain.Page;

import io.falcon.falcontest.model.TumblrAccount;

public interface TumblrAccountRepositoryService {

  TumblrAccount createTumblrAccount(TumblrAccount tumblrAccount);

  Page<TumblrAccount> findAll(Integer page, Integer pageSize);
}
