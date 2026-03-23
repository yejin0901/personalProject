package com.yjyj.ecommerce.notification.application.service.converter;


import com.yjyj.ecommerce.notification.application.service.dto.ConvertedFollowNotification;
import com.yjyj.ecommerce.notification.domain.FollowNotification;
import com.yjyj.ecommerce.notification.domain.User;
import com.yjyj.ecommerce.notification.infrastructure.client.UserClient;
import org.springframework.stereotype.Service;

@Service
public class FollowUserNotificationConverter {

    private final UserClient userClient;

    public FollowUserNotificationConverter(UserClient userClient) {
        this.userClient = userClient;
    }

    public ConvertedFollowNotification convert(FollowNotification notification) {
        User user = userClient.getUser(notification.getFollowerId());
        boolean isFollowing = userClient.getIsFollowing(notification.getUserId(), notification.getFollowerId());

        return new ConvertedFollowNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                isFollowing
        );
    }
}
