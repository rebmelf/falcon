package io.falcon.falcontest.redis.listener;

import java.io.IOException;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.falcon.falcontest.api.entity.AddTumblrAccountRequest;
import io.falcon.falcontest.model.TumblrAccount;
import io.falcon.falcontest.repository.service.TumblrAccountRepositoryService;

public class SocialMessageListener implements MessageListener {

  private TumblrAccountRepositoryService tumblrAccountRepositoryService;

  public SocialMessageListener(TumblrAccountRepositoryService tumblrAccountRepositoryService) {
    this.tumblrAccountRepositoryService = tumblrAccountRepositoryService;
  }

  @Override
  public void onMessage(Message message, byte[] pattern) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      final AddTumblrAccountRequest request = mapper.readValue(message.toString(), AddTumblrAccountRequest.class);
      tumblrAccountRepositoryService.createTumblrAccount(new TumblrAccount()
        .setId("ID" + request.getName())
        .setName(request.getName())
        .setAccType(request.getType())
        .setPopularity(request.getPopularity()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
