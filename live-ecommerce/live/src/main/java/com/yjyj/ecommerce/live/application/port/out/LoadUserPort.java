package com.yjyj.ecommerce.live.application.port.out;

import com.yjyj.ecommerce.live.domain.user.User;
import java.util.List;
import java.util.Optional;

public interface LoadUserPort {
    Optional<User> loadUser(String userId);

    List<User> loadAllUsers(List<String> userIds);
}
