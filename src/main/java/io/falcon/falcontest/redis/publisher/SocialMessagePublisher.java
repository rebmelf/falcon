package io.falcon.falcontest.redis.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.falcon.falcontest.api.entity.BaseRequest;

import static io.falcon.falcontest.message.LogMessage.PUBLISHING_REDIS_MESSAGE;
import static io.falcon.falcontest.message.LogMessage.PUBLISHING_REDIS_MESSAGE_FAILED;
import static io.falcon.falcontest.message.LogMessage.getLogMessage;
import static java.util.Collections.singletonList;

public class SocialMessagePublisher {

  private Logger logger = LoggerFactory.getLogger(SocialMessagePublisher.class);

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Autowired
  private ChannelTopic topic;

  public SocialMessagePublisher() {

  }

  public SocialMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
    this.redisTemplate = redisTemplate;
    this.topic = topic;
  }

  public void publish(final BaseRequest request) {
    try {
      String message = new ObjectMapper().writeValueAsString(request);
      logger.info(getLogMessage(PUBLISHING_REDIS_MESSAGE, singletonList(message)));
      redisTemplate.convertAndSend(topic.getTopic(), message);
    } catch (JsonProcessingException e) {
      logger.error(getLogMessage(PUBLISHING_REDIS_MESSAGE_FAILED, singletonList(request.toString())), e);
    }
  }
}
