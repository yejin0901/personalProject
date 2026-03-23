package com.yjyj.ecommerce.notification.representation.in.test;

import com.yjyj.ecommerce.notification.domain.event.CommentEvent;
import com.yjyj.ecommerce.notification.domain.event.FollowEvent;
import com.yjyj.ecommerce.notification.domain.event.LikeEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.function.Consumer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "알림 컨슈머 테스트 API")
public class EventConsumerTestController implements EventConsumerTestControllerSpec {

    private final Consumer<CommentEvent> comment;

    private final Consumer<LikeEvent> like;

    private final Consumer<FollowEvent> follow;

    public EventConsumerTestController(Consumer<CommentEvent> comment, Consumer<LikeEvent> like, Consumer<FollowEvent> follow) {
        this.comment = comment;
        this.like = like;
        this.follow = follow;
    }

    @Override
    @PostMapping("/test/comment")
    @Operation(summary = "댓글 이벤트 처리")
    public void comment(@RequestBody CommentEvent event) {
        comment.accept(event);
    }

    @Override
    @PostMapping("/test/like")
    @Operation(summary = "좋아요 이벤트 처리")
    public void like(@RequestBody LikeEvent event) {
        like.accept(event);
    }

    @Override
    @PostMapping("/test/follow")
    @Operation(summary = "팔로우 이벤트 처리")
    public void follow(@RequestBody FollowEvent event) {
        follow.accept(event);
    }
}
