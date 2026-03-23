package com.yjyj.ecommerce.notification.application.service;


import com.yjyj.ecommerce.notification.application.service.converter.GetUserNotificationsByPivotResult;
import com.yjyj.ecommerce.notification.domain.Notification;
import com.yjyj.ecommerce.notification.infrastructure.repository.NotificationRepository;
import java.time.Instant;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class NotificationListService {

    private final NotificationRepository repository;

    public NotificationListService(NotificationRepository repository) {
        this.repository = repository;
    }

    public GetUserNotificationsByPivotResult getUserNotificationsByPivot(long userId, Instant occurredAt) {
        Slice<Notification> result;

        if (occurredAt == null) {
            result = repository.findAllByUserIdOrderByOccurredAtDesc(userId, PageRequest.of(0, PAGE_SIZE));
        } else {
            result = repository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, occurredAt, PageRequest.of(0, PAGE_SIZE));
        }

        return new GetUserNotificationsByPivotResult(
                result.toList(),
                result.hasNext()
        );
    }

    private static final int PAGE_SIZE = 20;
}
