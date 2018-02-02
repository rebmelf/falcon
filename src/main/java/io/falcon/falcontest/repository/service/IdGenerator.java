package io.falcon.falcontest.repository.service;

/**
 * This interface is for creating a custom ID generator, using a prefix, which can differ
 * for each table, or each domain. The current implementation is not safe,
 * it's possible, that it will generate the same id for two entities.
 */
public interface IdGenerator {

  String generate(String identifier);
}
