package com.yjyj.ecommerce.notification.representation.in;


import com.yjyj.ecommerce.notification.application.service.LastReadAtService;
import com.yjyj.ecommerce.notification.representation.response.SetLastReadAtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user-notifications")
@Tag(name = "알림 센터 API")
public class NotificationReadController implements NotificationReadControllerSpec {

    private final LastReadAtService service;

    public NotificationReadController(LastReadAtService service) {
        this.service = service;
    }

    @Override
    @PutMapping("/{userId}/read")
    @Operation(summary = "유저 알림 읽음 처리")
    public SetLastReadAtResponse setLastReadAt(
            @PathVariable(value = "userId") long userId
    ) {
        Instant lastReadAt = service.setLastReadAt(userId);
        return new SetLastReadAtResponse(lastReadAt);
    }
}
