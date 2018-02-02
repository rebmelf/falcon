package io.falcon.falcontest.repository.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.falcon.falcontest.AbstractServiceTest;

import static org.hamcrest.CoreMatchers.not;

public class IdGeneratorTest extends AbstractServiceTest {

  @Autowired
  private IdGenerator idGenerator;

  @Test
  public void generateId() throws Exception {
    final String id1 = idGenerator.generate("OO");
    //Since with the current implementation, it's possible that two ids are the same, let's wait a milli
    Thread.sleep(1);
    final String id2 = idGenerator.generate("OO");

    Assert.assertThat(id1, not(id2));
  }
}