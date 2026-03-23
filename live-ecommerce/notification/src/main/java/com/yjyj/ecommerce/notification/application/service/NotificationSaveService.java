package com.yjyj.ecommerce.notification.application.service;


import com.yjyj.ecommerce.notification.domain.Notification;
import com.yjyj.ecommerce.notification.infrastructure.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationSaveService {

    private final NotificationRepository repository;

    public NotificationSaveService(NotificationRepository repository) {
        this.repository = repository;
    }

    public void insert(Notification notification) {
        Notification result = repository.insert(notification);
        log.info("inserted: {}", result);
    }

    public void upsert(Notification notification) {
        Notification result = repository.save(notification);
        log.info("upserted: {}", result);
    }
}
