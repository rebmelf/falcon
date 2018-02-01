package io.falcon.falcontest.api.tumblr.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.falcon.falcontest.api.tumblr.accounts.entity.TumblrAccountsRequest;
import io.falcon.falcontest.api.tumblr.accounts.entity.AddTumblrAccountRequest;
import io.falcon.falcontest.api.tumblr.accounts.entity.TumblrAccountsResponse;
import io.falcon.falcontest.api.path.Uris.API;
import io.falcon.falcontest.api.tumblr.accounts.transformer.TumblrAccountsTransformer;
import io.falcon.falcontest.model.TumblrAccount;
import io.falcon.falcontest.redis.publisher.SocialMessagePublisher;
import io.falcon.falcontest.repository.service.TumblrAccountRepositoryService;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = API.V1.SERVICES.TUMBLR.URI)
public class TumblrAccountsController {

  private SimpMessagingTemplate template;

  private TumblrAccountsTransformer transformer = new TumblrAccountsTransformer();

  @Autowired
  private SocialMessagePublisher messagePublisher;

  @Autowired
  private TumblrAccountRepositoryService tumblrAccountRepositoryService;


  @Autowired
  public TumblrAccountsController(SimpMessagingTemplate template) {
    this.template = template;
  }

  @RequestMapping(
    method = POST,
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(ACCEPTED)
  public void addTumblrAccount(@RequestBody final AddTumblrAccountRequest addTumblrAccountRequest) {
    messagePublisher.publish(addTumblrAccountRequest);
    template.convertAndSend("/topic/received-messages", addTumblrAccountRequest);
  }

  @RequestMapping(
    method = GET,
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  public TumblrAccountsResponse getAccountPage(TumblrAccountsRequest requestParams) {
    final Page<TumblrAccount> page = tumblrAccountRepositoryService.findAll(requestParams.getPage(), requestParams.getSize());
    return transformer.convert(page);
  }


}
