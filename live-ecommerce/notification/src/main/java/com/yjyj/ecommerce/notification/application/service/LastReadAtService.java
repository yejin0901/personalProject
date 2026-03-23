package com.yjyj.ecommerce.notification.application.service;

import com.yjyj.ecommerce.notification.infrastructure.repository.NotificationReadRepository;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class LastReadAtService {

    private final NotificationReadRepository repository;

    public LastReadAtService(NotificationReadRepository repository) {
        this.repository = repository;
    }

    public Instant setLastReadAt(long userId) {
        return repository.setLastReadAt(userId);
    }

    public Instant getLastReadAt(long userId) {
        return repository.getLastReadAt(userId);
    }
}
