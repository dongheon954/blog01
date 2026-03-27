package org.example.blog.dto;

import org.example.blog.domain.User;
import lombok.*;

public class UserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SignUpRequest {
        private String username;
        private String password;
        private String email;
        private String nickname;
    }

    // === 로그인 요청을 위한 DTO 추가 ===
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Getter
    public static class Response {
        private Long id;
        private String username;
        private String nickname;
        private String email;

        public Response(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.nickname = user.getNickname();
            this.email = user.getEmail();
        }
    }
}