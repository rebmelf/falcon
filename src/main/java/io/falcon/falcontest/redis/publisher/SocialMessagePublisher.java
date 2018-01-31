package io.falcon.falcontest.redis.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.falcon.falcontest.api.entity.BaseRequest;

public class SocialMessagePublisher {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Autowired
  private ChannelTopic topic;

  public SocialMessagePublisher() {

  }

  public SocialMessagePublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
    this.redisTemplate = redisTemplate;
    this.topic = topic;
  }

  public void publish(BaseRequest request) {
    try {
      String message = new ObjectMapper().writeValueAsString(request);
      System.out.println(
        "Publishing... " + message + ", " + Thread.currentThread().getName());

      redisTemplate.convertAndSend(topic.getTopic(), message);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
