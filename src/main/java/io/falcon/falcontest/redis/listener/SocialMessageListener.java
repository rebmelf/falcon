package io.falcon.falcontest.redis.listener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.falcon.falcontest.api.tumblr.accounts.entity.AddTumblrAccountRequest;
import io.falcon.falcontest.model.TumblrAccount;
import io.falcon.falcontest.repository.service.TumblrAccountRepositoryService;

import static io.falcon.falcontest.message.LogMessage.HANDLING_REDIS_MESSAGE;
import static io.falcon.falcontest.message.LogMessage.HANDLING_REDIS_MESSAGE_FAILED;
import static io.falcon.falcontest.message.LogMessage.getLogMessage;
import static java.util.Collections.singletonList;

public class SocialMessageListener implements MessageListener {

  private Logger logger = LoggerFactory.getLogger(SocialMessageListener.class);

  private TumblrAccountRepositoryService tumblrAccountRepositoryService;

  public SocialMessageListener(final TumblrAccountRepositoryService tumblrAccountRepositoryService) {
    this.tumblrAccountRepositoryService = tumblrAccountRepositoryService;
  }

  @Override
  public void onMessage(final Message message, final byte[] pattern) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      final AddTumblrAccountRequest request = mapper.readValue(message.toString(), AddTumblrAccountRequest.class);
      logger.info(getLogMessage(HANDLING_REDIS_MESSAGE, singletonList(AddTumblrAccountRequest.class.getName())));
      tumblrAccountRepositoryService.createTumblrAccount(new TumblrAccount()
        .setName(request.getName())
        .setAccType(request.getType())
        .setPopularity(request.getPopularity()));
    } catch (IOException e) {
      logger.error(getLogMessage(HANDLING_REDIS_MESSAGE_FAILED, singletonList(message.toString())), e);
    }
  }
}
