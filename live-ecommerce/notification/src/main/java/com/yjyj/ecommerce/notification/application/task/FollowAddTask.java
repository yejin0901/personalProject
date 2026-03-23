package com.yjyj.ecommerce.notification.application.task;


import static com.yjyj.ecommerce.notification.domain.NotificationType.FOLLOW;

import com.yjyj.ecommerce.notification.application.service.NotificationSaveService;
import com.yjyj.ecommerce.notification.domain.FollowNotification;
import com.yjyj.ecommerce.notification.domain.event.FollowEvent;
import com.yjyj.ecommerce.notification.infrastructure.NotificationIdGenerator;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FollowAddTask {

    private final NotificationSaveService saveService;

    public FollowAddTask(NotificationSaveService saveService) {
        this.saveService = saveService;
    }

    public void processEvent(FollowEvent event) {
        if (event.getTargetUserId() == event.getUserId()) {
            log.error("targetUserId and userId cannot be the same");
            return;
        }

        saveService.insert(createFollowNotification(event));
    }

    private FollowNotification createFollowNotification(FollowEvent event) {
        Instant now = Instant.now();

        return new FollowNotification(
                NotificationIdGenerator.generate(),
                event.getTargetUserId(),
                FOLLOW,
                event.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                event.getUserId()
        );
    }
}
