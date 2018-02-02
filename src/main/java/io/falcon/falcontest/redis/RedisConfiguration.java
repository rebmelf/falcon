package io.falcon.falcontest.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import io.falcon.falcontest.redis.listener.SocialMessageListener;
import io.falcon.falcontest.redis.publisher.SocialMessagePublisher;
import io.falcon.falcontest.repository.service.TumblrAccountRepositoryService;

@Configuration
public class RedisConfiguration {

  @Autowired
  private TumblrAccountRepositoryService tumblrAccountRepositoryService;

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setHostName(host);
    jedisConnectionFactory.setPort(port);
    return jedisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
    return template;
  }

  @Bean
  RedisMessageListenerContainer redisContainer() {
    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(jedisConnectionFactory());
    container.addMessageListener(messageListener(), topic());
    return container;
  }

  @Bean
  MessageListenerAdapter messageListener() {
    return new MessageListenerAdapter(new SocialMessageListener(tumblrAccountRepositoryService));
  }

  @Bean
  SocialMessagePublisher socialMessagePublisher() {
    return new SocialMessagePublisher(redisTemplate(), topic());
  }

  @Bean
  ChannelTopic topic() {
    return new ChannelTopic("pubsub:social");
  }
}
