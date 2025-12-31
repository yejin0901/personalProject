package com.yjyj.ecommerce.live.representation.in.api;

import com.yjyj.ecommerce.live.domain.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserApiController {
    @GetMapping
    public User getUSer(User user) {
        return user;
    }
}
