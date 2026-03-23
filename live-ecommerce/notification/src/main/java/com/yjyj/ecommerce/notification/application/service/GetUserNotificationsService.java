package com.yjyj.ecommerce.notification.application.service;


import com.yjyj.ecommerce.notification.application.service.converter.CommentUserNotificationConverter;
import com.yjyj.ecommerce.notification.application.service.converter.FollowUserNotificationConverter;
import com.yjyj.ecommerce.notification.application.service.converter.GetUserNotificationsByPivotResult;
import com.yjyj.ecommerce.notification.application.service.converter.LikeUserNotificationConverter;
import com.yjyj.ecommerce.notification.application.service.dto.ConvertedNotification;
import com.yjyj.ecommerce.notification.application.service.dto.GetUserNotificationsResult;
import com.yjyj.ecommerce.notification.domain.CommentNotification;
import com.yjyj.ecommerce.notification.domain.FollowNotification;
import com.yjyj.ecommerce.notification.domain.LikeNotification;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetUserNotificationsService {

    private final NotificationListService listService;
    private final CommentUserNotificationConverter commentConverter;
    private final LikeUserNotificationConverter likeConverter;
    private final FollowUserNotificationConverter followConverter;

    public GetUserNotificationsService(NotificationListService listService,
                                       CommentUserNotificationConverter commentConverter,
                                       LikeUserNotificationConverter likeConverter, FollowUserNotificationConverter followConverter) {
        this.listService = listService;
        this.commentConverter = commentConverter;
        this.likeConverter = likeConverter;
        this.followConverter = followConverter;
    }

    public GetUserNotificationsResult getUserNotificationsByPivot(long userId, Instant pivot) {
        GetUserNotificationsByPivotResult result = listService.getUserNotificationsByPivot(userId, pivot);

        List<ConvertedNotification> convertedNotifications = result.getNotifications().stream()
                .map(notification -> switch (notification.getType()) {
                    case COMMENT -> commentConverter.convert((CommentNotification) notification);
                    case LIKE -> likeConverter.convert((LikeNotification) notification);
                    case FOLLOW -> followConverter.convert((FollowNotification) notification);
                })
                .toList();

        return new GetUserNotificationsResult(
               convertedNotifications,
               result.isHasNext()
        );
    }
}
