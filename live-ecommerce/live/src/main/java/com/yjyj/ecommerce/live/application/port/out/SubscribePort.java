package com.yjyj.ecommerce.live.application.port.out;

import com.yjyj.ecommerce.live.domain.channel.Channel;
import com.yjyj.ecommerce.live.domain.user.User;
import java.util.List;

public interface SubscribePort {
    String insertSubscribeChannel(Channel channel, User user);

    void deleteSubscribeChannel(String subscribeId);

    List<Channel> listSubscribeChannel(String userId);
}
