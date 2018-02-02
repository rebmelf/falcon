package io.falcon.falcontest.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

  private static final String destinationPrefix = "/topic";
  private static final String endpoint = "/falcon";
  private static final String allowedOrigins = "*";

  @Override
  public void configureMessageBroker(final MessageBrokerRegistry config) {
    config.enableSimpleBroker(destinationPrefix);
    config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(final StompEndpointRegistry registry) {
    registry.addEndpoint(endpoint)
      .setAllowedOrigins(allowedOrigins)
      .withSockJS();
  }

  public static String getFalconDestination() {
    return destinationPrefix + endpoint;
  }
}
