package io.falcon.falcontest.repository.service;

import org.springframework.data.domain.Page;

import io.falcon.falcontest.model.TumblrAccount;

/**
 * This Service is used to hide the unused methods of the TumblrAccountRepository repository.
 * With it!s help, custom validation can be done. If required an extra service layer can be
 * used later for extended business logic with the use of multiple repository services.
 */
public interface TumblrAccountRepositoryService {

  TumblrAccount createTumblrAccount(TumblrAccount tumblrAccount);

  Page<TumblrAccount> findAll(Integer page, Integer size);
}
