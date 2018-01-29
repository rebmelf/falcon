package io.falcon.falcontest.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.falcon.falcontest.api.entity.AddTumblrAccountRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TumblrAccountsController.class)
public class TumblrAccountsControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void addAccount() throws Exception {
    mvc.perform(MockMvcRequestBuilders.post("/api/v1/services/tumblr")
      .contentType(APPLICATION_JSON_UTF8)
      .content(getRequestJson()))
      .andExpect(status().isAccepted());
  }

  private String getRequestJson() throws JsonProcessingException {
    AddTumblrAccountRequest addTumblrAccountRequest = new AddTumblrAccountRequest()
      .setName("Eating with Tumblr")
      .setType("Food and drink")
      .setPopularity(3);
    return new ObjectMapper().writeValueAsString(addTumblrAccountRequest);
  }
}