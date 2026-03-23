package com.yjyj.ecommerce.config.mongo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
    basePackages = "com.yjyj.ecommerce.live.infrastructure.adapter.out.mongo.comment",
    mongoTemplateRef = "mongoTemplate"
)
public class LiveMongoRepositoriesConfig {
}
