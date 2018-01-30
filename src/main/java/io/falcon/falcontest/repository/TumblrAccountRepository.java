package io.falcon.falcontest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import io.falcon.falcontest.model.TumblrAccount;

public interface TumblrAccountRepository extends JpaRepository<TumblrAccount, String>, QueryDslPredicateExecutor<TumblrAccount> {

  Optional<TumblrAccount> findByName(String name);
}
