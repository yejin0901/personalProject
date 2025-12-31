package com.yjyj.ecommerce.live.application.port.in;


import com.yjyj.ecommerce.live.domain.user.User;

public interface UserUserCase {
    User getUser(String userId);
}
