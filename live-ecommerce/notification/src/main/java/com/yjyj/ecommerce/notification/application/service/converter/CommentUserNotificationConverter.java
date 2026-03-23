package com.yjyj.ecommerce.notification.application.service.converter;


import com.yjyj.ecommerce.notification.application.service.dto.ConvertedCommentNotification;
import com.yjyj.ecommerce.notification.domain.CommentNotification;
import com.yjyj.ecommerce.notification.domain.Post;
import com.yjyj.ecommerce.notification.domain.User;
import com.yjyj.ecommerce.notification.infrastructure.client.PostClient;
import com.yjyj.ecommerce.notification.infrastructure.client.UserClient;
import org.springframework.stereotype.Service;

@Service
public class CommentUserNotificationConverter {

    private final UserClient userClient;
    private final PostClient postClient;

    public CommentUserNotificationConverter(UserClient userClient, PostClient postClient) {
        this.userClient = userClient;
        this.postClient = postClient;
    }

    public ConvertedCommentNotification convert(CommentNotification notification) {
        User user = userClient.getUser(notification.getWriterId());
        Post post = postClient.getPost(notification.getPostId());

        return new ConvertedCommentNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getComment(),
                post.getImageUrl()
        );
    }
}
