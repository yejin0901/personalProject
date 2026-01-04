package com.yjyj.ecommerce.live.infrastructure.adapter.out;

import com.yjyj.ecommerce.common.common.RedisKeyGenerator;
import com.yjyj.ecommerce.live.application.port.out.CommentBlockPort;
import java.util.Set;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommentBlockPersistenceAdapter implements CommentBlockPort {
    private final StringRedisTemplate stringRedisTemplate;

    public CommentBlockPersistenceAdapter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void saveUserCommentBlock(String userId, String commentId) {
        // user:comment-block:userId [commentId1, commentId2]
        stringRedisTemplate.opsForSet().add(RedisKeyGenerator.getUserCommentBlock(userId), commentId);
    }

    @Override
    public Set<String> getUserCommentBlocks(String userId) {
        return stringRedisTemplate.opsForSet().members(RedisKeyGenerator.getUserCommentBlock(userId));
    }
}
