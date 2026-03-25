package org.example.blog.dto;

import org.example.blog.domain.Post;
import lombok.*;
import java.time.LocalDateTime;

public class PostDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateRequest {
        private String title;
        private String content;
        private Long categoryId;
        private String status;
    }

    @Getter
    public static class Response {
        private Long id;
        private String title;
        private String content;
        private String nickname;
        private Integer viewCount;
        private LocalDateTime createdAt;

        public Response(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.nickname = post.getUser().getNickname();
            this.viewCount = post.getViewCount();
            this.createdAt = post.getCreatedAt();
        }
    }
}
