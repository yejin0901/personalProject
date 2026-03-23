package com.yjyj.ecommerce.notification.application.task;


import com.yjyj.ecommerce.notification.application.service.NotificationGetService;
import com.yjyj.ecommerce.notification.application.service.NotificationRemoveService;
import com.yjyj.ecommerce.notification.domain.NotificationType;
import com.yjyj.ecommerce.notification.domain.event.FollowEvent;
import org.springframework.stereotype.Component;

@Component
public class FollowRemoveTask {

    private final NotificationGetService getService;

    private final NotificationRemoveService removeService;

    public FollowRemoveTask(NotificationGetService getService, NotificationRemoveService removeService) {
        this.getService = getService;
        this.removeService = removeService;
    }

    public void processEvent(FollowEvent event) {
        getService.getNotificationByTypeAndUserIdAndFollowerId(NotificationType.FOLLOW, event.getTargetUserId(),
                        event.getUserId())
                .ifPresent(
                        notification -> removeService.deleteById(notification.getId())
                );
    }
}
