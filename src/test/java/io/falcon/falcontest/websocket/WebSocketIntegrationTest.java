package io.falcon.falcontest.websocket;

import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import io.falcon.falcontest.AbstractServiceTest;

import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class WebSocketIntegrationTest extends AbstractServiceTest {

  @Value("${local.server.port}")
  private int port;

  private static final String WEBSOCKET_URI = "ws://localhost";
  private static final String WEBSOCKET_ENDPOINT = "/falcon";
  private static final String WEBSOCKET_TOPIC = "/topic/received-messages";

  private BlockingQueue<String> blockingQueue;
  private WebSocketStompClient stompClient;

  @Before
  public void setup() {
    blockingQueue = new LinkedBlockingDeque<>();
    stompClient = new WebSocketStompClient(new SockJsClient(
      singletonList(new WebSocketTransport(new StandardWebSocketClient()))));
  }

  @Test
  public void sendAndReceiveMessage() throws Exception {
    StompSession session = stompClient
      .connect(createUrl(), new StompSessionHandlerAdapter() {
      })
      .get(3, SECONDS);
    session.subscribe(WEBSOCKET_TOPIC, new CustomStompFrameHandler());

    session.send(WEBSOCKET_TOPIC, jsonMessage().getBytes());

    assertEquals(jsonMessage(), blockingQueue.poll(3, SECONDS));
  }

  private class CustomStompFrameHandler implements StompFrameHandler {

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
      return byte[].class;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {
      blockingQueue.offer(new String((byte[]) o));
    }
  }

  private String createUrl() {
    return WEBSOCKET_URI + ":" + port + WEBSOCKET_ENDPOINT;
  }

  private String jsonMessage() {
    return "{" +
      "\"name\": \"Test account\"," +
      "\"type\": \"Food and Drink\"," +
      "\"popularity\": 3" +
      "}";
  }
}

