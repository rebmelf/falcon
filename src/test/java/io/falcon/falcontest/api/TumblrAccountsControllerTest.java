package io.falcon.falcontest.api;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.falcon.falcontest.AbstractMvcTest;
import io.falcon.falcontest.api.tumblr.accounts.TumblrAccountsController;
import io.falcon.falcontest.api.tumblr.accounts.entity.AddTumblrAccountRequest;
import io.falcon.falcontest.model.TumblrAccount;
import io.falcon.falcontest.redis.publisher.SocialMessagePublisher;
import io.falcon.falcontest.repository.ServiceExceptionMessages;
import io.falcon.falcontest.repository.service.ServiceException;
import io.falcon.falcontest.repository.service.TumblrAccountRepositoryService;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TumblrAccountsController.class)
public class TumblrAccountsControllerTest extends AbstractMvcTest {

  private static final String TUMBLR_ACCOUNTS_BASE_URI = "/api/v1/services/tumblr";

  @Autowired
  private MockMvc mvc;

  @MockBean
  private SocialMessagePublisher messagePublisher;

  @MockBean
  private SimpMessagingTemplate messagingTemplate;

  @MockBean
  private TumblrAccountRepositoryService tumblrAccountRepositoryService;

  @Test
  public void addAccount() throws Exception {
    mvc.perform(post(TUMBLR_ACCOUNTS_BASE_URI)
      .contentType(APPLICATION_JSON_UTF8)
      .content(getAddAccountRequestJson()))
      .andExpect(status().isAccepted());
  }

  @Test
  public void getAccountPage() throws Exception {
    when(tumblrAccountRepositoryService.findAll(0, 5))
      .thenReturn(getAllRecords());

    mvc.perform(get(TUMBLR_ACCOUNTS_BASE_URI + "?page=0&size=5")
      .contentType(APPLICATION_JSON_UTF8))
      .andExpect(status().isOk());
  }

  @Test
  public void getAccountPageWithMissingParam() throws Exception {
    when(tumblrAccountRepositoryService.findAll(null, null))
      .thenThrow(new ServiceException(ServiceExceptionMessages.PAGE_SIZE_MISSING));

    mvc.perform(get(TUMBLR_ACCOUNTS_BASE_URI)
      .contentType(APPLICATION_JSON_UTF8))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.errorMessage").value("Page and/or size parameters are missing"));
  }

  private String getAddAccountRequestJson() throws JsonProcessingException {
    AddTumblrAccountRequest addTumblrAccountRequest = new AddTumblrAccountRequest()
      .setName("Eating with Tumblr")
      .setType("Food and drink")
      .setPopularity(3);
    return new ObjectMapper().writeValueAsString(addTumblrAccountRequest);
  }

  private Page<TumblrAccount> getAllRecords() {
    return new PageImpl<>(
      asList(tumblrAccount(1), tumblrAccount(2), tumblrAccount(3), tumblrAccount(4), tumblrAccount(5), tumblrAccount(6)),
      new PageRequest(0, 5), 6);
  }

  private TumblrAccount tumblrAccount(Integer num) {
    return new TumblrAccount()
      .setId("ID" + num)
      .setName("Name" + num)
      .setPopularity(2)
      .setAccType("Acctype");
  }
}