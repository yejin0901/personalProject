package com.yjyj.ecommerce.live.application.port.in;

import com.yjyj.ecommerce.live.domain.channel.Channel;
import java.util.List;

public interface SubscribeUseCase {
    String subscribeChannel(String channelId, String userId);

    void unsubscribeChannel(String subscribeId, String userId);

    List<Channel> listSubscribeChannel(String userId);
}
