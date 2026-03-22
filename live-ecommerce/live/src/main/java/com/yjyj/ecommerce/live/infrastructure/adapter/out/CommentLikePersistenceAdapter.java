package com.yjyj.ecommerce.live.infrastructure.adapter.out;


import static com.yjyj.ecommerce.common.common.RedisKeyGenerator.getCommentLikeKey;

import com.yjyj.ecommerce.live.application.port.out.CommentLikePort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommentLikePersistenceAdapter implements CommentLikePort {
    private final RedisTemplate<String, Long> redisTemplate;

    public CommentLikePersistenceAdapter(@Qualifier("longRedisTemplate") RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long getCommentLikeCount(String commentId) {
        var likeCount = redisTemplate.opsForValue().get(getCommentLikeKey(commentId));
        return likeCount == null ? 0 : likeCount;
    }
}
