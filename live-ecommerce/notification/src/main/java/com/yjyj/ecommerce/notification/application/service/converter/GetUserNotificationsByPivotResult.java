package com.yjyj.ecommerce.notification.application.service.converter;

import com.yjyj.ecommerce.notification.domain.Notification;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserNotificationsByPivotResult {
    private List<Notification> notifications;
    private boolean hasNext;
}
