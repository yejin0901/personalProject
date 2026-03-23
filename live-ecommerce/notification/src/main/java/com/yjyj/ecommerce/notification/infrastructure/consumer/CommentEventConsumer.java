package com.yjyj.ecommerce.notification.infrastructure.consumer;

import static com.yjyj.ecommerce.notification.domain.event.CommentEventType.ADD;
import static com.yjyj.ecommerce.notification.domain.event.CommentEventType.REMOVE;

import com.yjyj.ecommerce.notification.application.task.CommentAddTask;
import com.yjyj.ecommerce.notification.application.task.CommentRemoveTask;
import com.yjyj.ecommerce.notification.domain.event.CommentEvent;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommentEventConsumer {

    private final CommentAddTask commentAddTask;

    private final CommentRemoveTask commentRemoveTask;

    public CommentEventConsumer(CommentAddTask commentAddTask, CommentRemoveTask commentRemoveTask) {
        this.commentAddTask = commentAddTask;
        this.commentRemoveTask = commentRemoveTask;
    }

    @Bean("comment")
    public Consumer<CommentEvent> comment() {
        return event -> {
            if (event.getType() == ADD) {
                commentAddTask.processEvent(event);
            } else if (event.getType() == REMOVE) {
                commentRemoveTask.processEvent(event);
            }
        };
    }
}
