package com.yjyj.ecommerce.notification.representation.in;


import com.yjyj.ecommerce.notification.application.service.GetUserNotificationsService;
import com.yjyj.ecommerce.notification.representation.response.UserNotificationListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user-notifications")
@Tag(name = "알림 센터 API")
public class UserNotificationListController implements UserNotificationListControllerSpec {

    private final GetUserNotificationsService getUserNotificationsService;

    public UserNotificationListController(GetUserNotificationsService getUserNotificationsService) {
        this.getUserNotificationsService = getUserNotificationsService;
    }

    @Override
    @GetMapping("/{userId}")
    @Operation(summary = "유저 알림 조회")
    public UserNotificationListResponse getNotifications(
            @PathVariable(value = "userId") long userId,
            @RequestParam(value = "pivot", required = false) Instant pivot
    ) {
        return UserNotificationListResponse.of(
                getUserNotificationsService.getUserNotificationsByPivot(userId, pivot)
        );
    }
}
