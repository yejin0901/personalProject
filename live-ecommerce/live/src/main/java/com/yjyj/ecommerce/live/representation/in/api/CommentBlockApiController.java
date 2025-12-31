package com.yjyj.ecommerce.live.representation.in.api;


import com.yjyj.ecommerce.live.application.port.in.CommentBlockUseCase;
import com.yjyj.ecommerce.live.domain.user.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments/block")
public class CommentBlockApiController {
    private final CommentBlockUseCase commentBlockUseCase;

    public CommentBlockApiController(CommentBlockUseCase commentBlockUseCase) {
        this.commentBlockUseCase = commentBlockUseCase;
    }

    @PostMapping(params = "commentId")
    void blockComment(
        User user,
        @RequestParam String commentId
    ) {
        commentBlockUseCase.blockComment(user, commentId);
    }
}
