package com.yjyj.ecommerce.live.infrastructure.adapter.out;

import com.yjyj.ecommerce.common.common.MessageTopics;
import com.yjyj.ecommerce.live.application.port.out.MessagePort;
import com.yjyj.ecommerce.live.domain.message.NewVideoMessage;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessagePersistenceAdapter implements MessagePort {
    private final RedisTemplate<String, Object> redisTemplate;

    public MessagePersistenceAdapter(@Lazy RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void sendNewVideMessage(String channelId) {
        redisTemplate.convertAndSend(MessageTopics.NEW_VIDEO, new NewVideoMessage(channelId));
    }

}
