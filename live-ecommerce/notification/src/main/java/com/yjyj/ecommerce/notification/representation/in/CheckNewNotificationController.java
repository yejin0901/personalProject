package com.yjyj.ecommerce.notification.representation.in;


import com.yjyj.ecommerce.notification.application.service.CheckNewNotificationService;
import com.yjyj.ecommerce.notification.representation.response.CheckNewNotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user-notifications")
@Tag(name = "알림 센터 API")
public class CheckNewNotificationController implements CheckNewNotificationControllerSpec {

    private final CheckNewNotificationService service;

    public CheckNewNotificationController(CheckNewNotificationService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/{userId}/new")
    @Operation(summary = "새로운 알림 체크")
    public CheckNewNotificationResponse checkNew(
            @PathVariable(value = "userId") long userId
    ) {
        boolean hasNew = service.checkNewNotification(userId);
        return new CheckNewNotificationResponse(hasNew);
    }
}
