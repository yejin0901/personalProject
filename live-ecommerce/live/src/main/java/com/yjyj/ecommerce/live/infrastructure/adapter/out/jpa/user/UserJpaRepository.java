package com.yjyj.ecommerce.live.infrastructure.adapter.out.jpa.user;

import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<UserJpaEntity, String> {
}
