package com.yjyj.ecommerce.live.representation.in.api;


import com.yjyj.ecommerce.live.application.port.in.CommentUseCase;
import com.yjyj.ecommerce.live.domain.comment.CommentResponse;
import com.yjyj.ecommerce.live.domain.user.User;
import com.yjyj.ecommerce.live.representation.in.api.dto.CommandResponse;
import com.yjyj.ecommerce.live.representation.in.api.dto.CommentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
@Tag(name = "라이브 채널 댓글 API")
public class CommentApiController {
    private final CommentUseCase commentUseCase;

    public CommentApiController(CommentUseCase commentUseCase) {
        this.commentUseCase = commentUseCase;
    }

    @PostMapping
    @Operation(summary = "라이브 채널 댓글 생성")
    CommandResponse createComment(
        @ModelAttribute User user,
        @RequestBody CommentRequest commentRequest
    ) {
        var comment = commentUseCase.createComment(user, commentRequest);
        return new CommandResponse(comment.getId());
    }

    @PutMapping("{commentId}")
    @Operation(summary = "라이브 채널 댓글 수정")
    CommandResponse updateComment(
        @ModelAttribute User user,
        @PathVariable String commentId,
        @RequestBody CommentRequest commentRequest
    ) {
        var updateComment = commentUseCase.updateComment(commentId, user, commentRequest);
        return new CommandResponse(updateComment.getId());
    }

    @DeleteMapping("{commentId}")
    @Operation(summary = "라이브 채널 댓글 삭제")
    void deleteComment(
        @ModelAttribute User user,
        @PathVariable String commentId
    ) {
        commentUseCase.deleteComment(commentId, user);
    }

    @GetMapping(params = {"commentId"})
    @Operation(summary = "라이브 채널 댓글 조회")
    CommentResponse getComment(@RequestParam String commentId) {
        return commentUseCase.getComment(commentId);
    }

    @GetMapping(value = "list", params = {"videoId", "order", "offset", "maxSize"})
    @Operation(summary = "라이브 채널 댓글 리스트 조회")
    List<CommentResponse> listComments(
        @ModelAttribute User user,
        @RequestParam String videoId,
        @RequestParam(defaultValue = "time") String order,
        @RequestParam String offset,
        @RequestParam Integer maxSize
    ) {
        return commentUseCase.listComments(user, videoId, order, offset, maxSize);
    }

    @GetMapping(value = "reply", params = {"parentId"})
    @Operation(summary = "라이브 채널 대댓글 리스트 조회")
    List<CommentResponse> listReplyComments(
        @RequestParam String parentId,
        @RequestParam String offset,
        @RequestParam Integer maxSize
    ) {
        return commentUseCase.listReplies(parentId, offset, maxSize);
    }
}
