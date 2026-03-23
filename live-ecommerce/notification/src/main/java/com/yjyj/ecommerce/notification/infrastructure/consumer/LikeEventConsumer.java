package com.yjyj.ecommerce.notification.infrastructure.consumer;

import static com.yjyj.ecommerce.notification.domain.event.LikeEventType.ADD;
import static com.yjyj.ecommerce.notification.domain.event.LikeEventType.REMOVE;

import com.yjyj.ecommerce.notification.application.task.LikeAddTask;
import com.yjyj.ecommerce.notification.application.task.LikeRemoveTask;
import com.yjyj.ecommerce.notification.domain.event.LikeEvent;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LikeEventConsumer {

    private final LikeAddTask likeAddTask;

    private final LikeRemoveTask likeRemoveTask;

    public LikeEventConsumer(LikeAddTask likeAddTask, LikeRemoveTask likeRemoveTask) {
        this.likeAddTask = likeAddTask;
        this.likeRemoveTask = likeRemoveTask;
    }

    @Bean("like")
    public Consumer<LikeEvent> like() {
        return event -> {
            if (event.getType() == ADD) {
                likeAddTask.processEvent(event);
            } else if (event.getType() == REMOVE) {
                likeRemoveTask.processEvent(event);
            }
        };
    }
}
