package io.falcon.falcontest.message;

import java.util.Arrays;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogMessageTest {

  @Test
  public void parameterListChanged() {
    final String message = LogMessage.getLogMessage("{0},{1},{2},{3}", Arrays.asList("a", "b", "c", "d"));

    assertThat(message, is("a,b,c,d"));
  }

  @Test
  public void tooFewParametersPassed() {
    final String message = LogMessage.getLogMessage("{0},{1},{2},{3}", Arrays.asList("a", "b", "c"));

    assertThat(message, is("a,b,c,{3}"));
  }

  @Test
  public void tooMuchParametersAdded() {
    final String message = LogMessage.getLogMessage("{0},{1},{2},{3}", Arrays.asList("a", "b", "c", "d", "e"));

    assertThat(message, is("a,b,c,d"));
  }

  @Test
  public void noChangeInStringWithoutParams() {
    final String messageString = "This message {0}";
    final String message = LogMessage.getLogMessage(messageString);

    assertThat(message, is(messageString));

  }
}