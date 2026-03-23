package com.yjyj.ecommerce.notification.infrastructure.consumer;


import static com.yjyj.ecommerce.notification.domain.event.FollowEventType.ADD;
import static com.yjyj.ecommerce.notification.domain.event.FollowEventType.REMOVE;

import com.yjyj.ecommerce.notification.application.task.FollowAddTask;
import com.yjyj.ecommerce.notification.application.task.FollowRemoveTask;
import com.yjyj.ecommerce.notification.domain.event.FollowEvent;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FollowEventConsumer {

    private final FollowAddTask followAddTask;

    private final FollowRemoveTask followRemoveTask;

    public FollowEventConsumer(FollowAddTask followAddTask, FollowRemoveTask followRemoveTask) {
        this.followAddTask = followAddTask;
        this.followRemoveTask = followRemoveTask;
    }

    @Bean("follow")
    public Consumer<FollowEvent> follow() {
        return event -> {
            if (event.getType() == ADD) {
                followAddTask.processEvent(event);
            } else if (event.getType() == REMOVE) {
                followRemoveTask.processEvent(event);
            }
        };
    }
}
