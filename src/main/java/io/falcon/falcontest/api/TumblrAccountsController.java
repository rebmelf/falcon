package io.falcon.falcontest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.falcon.falcontest.api.entity.AddTumblrAccountRequest;
import io.falcon.falcontest.api.path.Uris.API;
import io.falcon.falcontest.redis.publisher.SocialMessagePublisher;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = API.V1.SERVICES.TUMBLR.URI)
public class TumblrAccountsController {

  @Autowired
  private SocialMessagePublisher messagePublisher;

  @RequestMapping(
    method = POST,
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(ACCEPTED)
  public void addTumblrAccount(@RequestBody final AddTumblrAccountRequest addTumblrAccountRequest) {
    messagePublisher.publish(addTumblrAccountRequest);
  }
}
