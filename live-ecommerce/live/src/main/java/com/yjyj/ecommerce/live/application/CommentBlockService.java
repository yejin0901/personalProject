package com.yjyj.ecommerce.live.application;

import com.yjyj.ecommerce.live.application.port.in.CommentBlockUseCase;
import com.yjyj.ecommerce.live.application.port.out.CommentBlockPort;
import com.yjyj.ecommerce.live.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class CommentBlockService implements CommentBlockUseCase {
    private final CommentBlockPort commentBlockPort;

    public CommentBlockService(CommentBlockPort commentBlockPort) {
        this.commentBlockPort = commentBlockPort;
    }

    @Override
    public void blockComment(User user, String commentId) {
        commentBlockPort.saveUserCommentBlock(user.getId(), commentId);
    }
}
