package com.yjyj.ecommerce.live.infrastructure.adapter.out.redis.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<UserRedisHash, String> {
}
