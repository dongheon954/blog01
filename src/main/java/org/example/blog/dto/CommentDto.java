package org.example.blog.dto;

import org.example.blog.domain.Comment;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        private Long postId;
        private Long parentId;
        private String content;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private String content;
    }

    @Getter
    public static class Response {
        private Long id;
        private String nickname;
        private String content;
        private LocalDateTime createdAt;
        private List<Response> children;

        public Response(Comment comment) {
            this.id = comment.getId();
            this.nickname = comment.getUser().getNickname();
            this.content = comment.getContent();
            this.createdAt = comment.getCreatedAt();
            this.children = comment.getChildren().stream()
                    .map(Response::new)
                    .collect(Collectors.toList());
        }
    }
}
