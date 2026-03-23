package com.yjyj.ecommerce.notification.application.task;


import com.yjyj.ecommerce.notification.application.service.NotificationSaveService;
import com.yjyj.ecommerce.notification.domain.Comment;
import com.yjyj.ecommerce.notification.domain.CommentNotification;
import com.yjyj.ecommerce.notification.domain.Notification;
import com.yjyj.ecommerce.notification.domain.NotificationType;
import com.yjyj.ecommerce.notification.domain.Post;
import com.yjyj.ecommerce.notification.domain.event.CommentEvent;
import com.yjyj.ecommerce.notification.infrastructure.NotificationIdGenerator;
import com.yjyj.ecommerce.notification.infrastructure.client.CommentClient;
import com.yjyj.ecommerce.notification.infrastructure.client.PostClient;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class CommentAddTask {

    private final PostClient postClient;

    private final CommentClient commentClient;

    private final NotificationSaveService saveService;

    public CommentAddTask(PostClient postClient, CommentClient commentClient, NotificationSaveService saveService) {
        this.postClient = postClient;
        this.commentClient = commentClient;
        this.saveService = saveService;
    }

    public void processEvent(CommentEvent event) {
        Post post = postClient.getPost(event.getPostId());
        if (Objects.equals(post.getUserId(), event.getUserId())) {
            return;
        }

        Comment comment = commentClient.getComment(event.getCommentId());

        Notification notification = createNotification(post, comment);
        saveService.insert(notification);
    }

    private Notification createNotification(Post post, Comment comment) {
        Instant now = Instant.now();

        return new CommentNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                NotificationType.COMMENT,
                comment.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                post.getId(),
                comment.getUserId(),
                comment.getContent(),
                comment.getId()
        );
    }
}
