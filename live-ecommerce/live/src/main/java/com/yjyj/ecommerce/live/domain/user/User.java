package com.yjyj.ecommerce.live.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private String id;
    private String name;
    private String profileImageUrl;

    public static User defaultUser(String id) {
        return User.builder()
            .id(id)
            .name("")
            .profileImageUrl("")
            .build();
    }
}
