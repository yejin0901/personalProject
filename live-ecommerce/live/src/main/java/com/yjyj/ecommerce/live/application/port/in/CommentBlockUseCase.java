package com.yjyj.ecommerce.live.application.port.in;


import com.yjyj.ecommerce.live.domain.user.User;

public interface CommentBlockUseCase {
    void blockComment(User user, String commentId);
}
