package io.falcon.falcontest.repository.service.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.springframework.stereotype.Service;

import io.falcon.falcontest.repository.service.IdGenerator;

@Service
public class IdGeneratorImpl implements IdGenerator {

  @Override
  public String generate(final String identifier) {
    return identifier + generateIdPostfix();
  }

  private String generateIdPostfix() {
    Calendar c = new GregorianCalendar();
    Random random = new Random();
    return String.valueOf(c.getTimeInMillis() - random.nextInt(10000000) + random.nextInt(100000));
  }
}
