package com.yjyj.ecommerce.live.application;


import com.yjyj.ecommerce.live.application.port.in.UserUserCase;
import com.yjyj.ecommerce.live.application.port.out.LoadUserPort;
import com.yjyj.ecommerce.live.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserUserCase {
    private final LoadUserPort loadUserPort;

    public UserService(LoadUserPort loadUserPort) {
        this.loadUserPort = loadUserPort;
    }

    @Override
    public User getUser(String userId) {
        if (userId == null) {
            return null;
        }
        return loadUserPort.loadUser(userId)
            .orElse(null);
    }
}
