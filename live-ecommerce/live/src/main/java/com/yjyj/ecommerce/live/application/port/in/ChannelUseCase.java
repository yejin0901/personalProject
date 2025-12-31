package com.yjyj.ecommerce.live.application.port.in;


import com.yjyj.ecommerce.live.domain.channel.Channel;
import com.yjyj.ecommerce.live.representation.in.api.dto.ChannelRequest;

public interface ChannelUseCase {
    Channel createChannel(ChannelRequest channelRequest);
    Channel updateChannel(String channelId, ChannelRequest channelRequest);
    Channel getChannel(String id);
}
