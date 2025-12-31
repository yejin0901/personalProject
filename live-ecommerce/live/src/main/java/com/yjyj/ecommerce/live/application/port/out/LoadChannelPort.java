package com.yjyj.ecommerce.live.application.port.out;

import com.yjyj.ecommerce.live.domain.channel.Channel;
import java.util.Optional;

public interface LoadChannelPort {
    Optional<Channel> loadChannel(String id);
}
