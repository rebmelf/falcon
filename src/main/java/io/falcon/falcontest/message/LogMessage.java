package io.falcon.falcontest.message;

import java.text.MessageFormat;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

public abstract class LogMessage {

  public static final String ENTER_ENDPOINT = "Entering endpoint {0}";
  public static final String LEAVE_ENDPOINT = "Leaving endpoint {0}";
  public static final String HANDLING_REDIS_MESSAGE = "Handling Redis message of type {0}";
  public static final String HANDLING_REDIS_MESSAGE_FAILED = "Handling Redis message of {0} failed";
  public static final String SEARCH_FOR_EXISTING_ACCOUNT = "Searching for existing account with name {0}";
  public static final String SAVING_ACCOUNT = "Saving account with name {0}";
  public static final String PUBLISHING_REDIS_MESSAGE = "Publishing redis message {0}";
  public static final String PUBLISHING_REDIS_MESSAGE_FAILED = "Publishing redis message {0} failed";

  public static String getLogMessage(String message) {
    return getLogMessage(message, null);
  }

  public static String getLogMessage(String message, List<String> params) {
    return isEmpty(params) ? message : MessageFormat.format(message, params.toArray());
  }
}
