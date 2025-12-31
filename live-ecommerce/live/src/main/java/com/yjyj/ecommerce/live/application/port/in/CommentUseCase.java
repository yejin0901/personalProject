package com.yjyj.ecommerce.live.application.port.in;


import com.yjyj.ecommerce.live.domain.comment.Comment;
import com.yjyj.ecommerce.live.domain.comment.CommentResponse;
import com.yjyj.ecommerce.live.domain.user.User;
import com.yjyj.ecommerce.live.representation.in.api.dto.CommentRequest;
import java.util.List;

public interface CommentUseCase {
    Comment createComment(User user, CommentRequest commentRequest);

    Comment updateComment(String commentId, User user, CommentRequest commentRequest);

    void deleteComment(String commentId, User user);

    CommentResponse getComment(String commentId);

    List<CommentResponse> listComments(String videoId, String order, String offset, Integer maxSize);

    List<CommentResponse> listComments(User user, String videoId, String order, String offset, Integer maxSize);

    List<CommentResponse> listReplies(String parentId, String offset, Integer maxSize);
}
