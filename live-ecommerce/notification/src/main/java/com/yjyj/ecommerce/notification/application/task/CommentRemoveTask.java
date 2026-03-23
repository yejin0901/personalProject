package com.yjyj.ecommerce.notification.application.task;


import static com.yjyj.ecommerce.notification.domain.NotificationType.COMMENT;

import com.yjyj.ecommerce.notification.application.service.NotificationGetService;
import com.yjyj.ecommerce.notification.application.service.NotificationRemoveService;
import com.yjyj.ecommerce.notification.domain.Post;
import com.yjyj.ecommerce.notification.domain.event.CommentEvent;
import com.yjyj.ecommerce.notification.infrastructure.client.PostClient;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommentRemoveTask {

    private final PostClient postClient;

    private final NotificationGetService getService;

    private final NotificationRemoveService removeService;

    public CommentRemoveTask(PostClient postClient, NotificationGetService getService, NotificationRemoveService removeService) {
        this.postClient = postClient;
        this.getService = getService;
        this.removeService = removeService;
    }

    public void processEvent(CommentEvent event) {
        Post post = postClient.getPost(event.getPostId());
        if (Objects.equals(post.getUserId(), event.getUserId())) {
            return;
        }

        getService.getNotificationByTypeAndCommentId(COMMENT, event.getCommentId())
                .ifPresent(
                        notification -> removeService.deleteById(notification.getId())
                );
    }
}
