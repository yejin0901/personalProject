package com.yjyj.ecommerce.live.representation.in.api;


import com.yjyj.ecommerce.live.application.port.in.CommentBlockUseCase;
import com.yjyj.ecommerce.live.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments/block")
@Tag(name = "라이브 댓글 차단 API")
public class CommentBlockApiController {
    private final CommentBlockUseCase commentBlockUseCase;

    public CommentBlockApiController(CommentBlockUseCase commentBlockUseCase) {
        this.commentBlockUseCase = commentBlockUseCase;
    }

    @PostMapping(params = "commentId")
    @Operation(summary = "라이브 댓글 차단")
    void blockComment(
        @ModelAttribute User user,
        @RequestParam String commentId
    ) {
        commentBlockUseCase.blockComment(user, commentId);
    }
}
