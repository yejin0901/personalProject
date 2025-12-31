package com.yjyj.ecommerce.live.infrastructure.adapter.out.redis.channel;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRedisRepository extends CrudRepository<ChannelRedisHash, String> {
    List<ChannelRedisHash> findAllByContentOwnerId(String contentOwnerId);
}
