package com.yjyj.ecommerce.live.infrastructure.adapter.out.mongo.comment;

import com.yjyj.ecommerce.live.domain.comment.Comment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("comment")
@AllArgsConstructor
@Getter
public class CommentDocument {
    @Id
    private String id;
    private String channelId;
    @Indexed
    private String videoId;
    @Indexed
    private String parentId;
    private String authorId;
    private String text;
    @Indexed
    private LocalDateTime publishedAt;

    public static CommentDocument from(Comment comment) {
        return new CommentDocument(
            comment.getId(),
            comment.getChannelId(),
            comment.getVideoId(),
            comment.getParentId(),
            comment.getAuthorId(),
            comment.getText(),
            comment.getPublishedAt()
        );
    }

    public Comment toDomain() {
        return Comment.builder()
            .id(this.getId())
            .channelId(this.getChannelId())
            .videoId(this.getVideoId())
            .parentId(this.getParentId())
            .text(this.getText())
            .authorId(this.authorId)
            .publishedAt(this.getPublishedAt())
            .build();
    }
}
