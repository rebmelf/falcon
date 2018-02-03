package io.falcon.falcontest.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import io.falcon.falcontest.AbstractServiceTest;
import io.falcon.falcontest.repository.TumblrAccountRepository;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class RedisIntegrationTest extends AbstractServiceTest {

  @Value("${local.server.port}")
  private int port;

  @Autowired
  private TumblrAccountRepository tumblrAccountRepository;

  @Before
  public void init() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }

  @Test
  public void publishAndSubscribe() {
    given()
      .body("{\n" +
        "\t\"name\": \"Pizzambasador\",\n" +
        "\t\"type\": \"Food and Drink\",\n" +
        "\t\"popularity\": 3\n" +
        "}")
      .contentType("application/json")
      .when()
      .post("/api/v1/services/tumblr")
      .then()
      .statusCode(202);
    try {
      Thread.sleep(2000);
    } catch (final InterruptedException e) {
    }

    given()
      .contentType("application/json")
      .when()
      .get("/api/v1/services/tumblr?page=0&size=5")
      .then()
      .body("total", is(1));
  }

  @After
  public void cleanUp() {
    tumblrAccountRepository.deleteAll();
  }
}

