package com.yjyj.ecommerce.live.application.port.out;


import com.yjyj.ecommerce.live.domain.channel.Channel;

public interface SaveChannelPort {
    void saveChannel(Channel channel);
}
