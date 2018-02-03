package io.falcon.falcontest.repository.service;

import org.springframework.data.domain.Page;

import io.falcon.falcontest.model.TumblrAccount;

/**
 * This Service is used to hide the unused methods of the TumblrAccountRepository repository.
 * With it!s help, custom validation can be done. If required an extra service layer can be
 * used later for extended business logic with the use of multiple repository services.
 * The implementing class has the private TumblrAccount saveAccount(final TumblrAccount tumblrAccount) method,
 * which should be the only point where this entity is persisted. With this, we can enhance the code later easily
 * with metrics for example, and we can make sure, that we have full control of persisting the data.
 */
public interface TumblrAccountRepositoryService {

  TumblrAccount createTumblrAccount(TumblrAccount tumblrAccount);

  Page<TumblrAccount> findAll(Integer page, Integer size);
}
