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

    // === 게시글 수정 요청을 위한 DTO 추가 ===
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateRequest {
        private String title;
        private String content;
        // 필요에 따라 카테고리 수정 등이 필요하면 여기에 추가합니다.
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