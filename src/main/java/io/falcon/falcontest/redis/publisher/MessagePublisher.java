package io.falcon.falcontest.redis.publisher;

import io.falcon.falcontest.api.entity.BaseRequest;

public interface MessagePublisher {

  void publish(BaseRequest request);
}
