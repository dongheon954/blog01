package org.example.blog.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users") // 'user'는 DB 예약어인 경우가 많아 보통 'users'로 테이블명을 지정합니다.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본 생성자 (안전하게 protected)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String role; // 초기값은 아래 prePersist나 생성자에서 처리 가능

    @CreationTimestamp // INSERT 시점에 현재 시간 자동 입력
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // role의 기본값을 'USER'로 설정하기 위한 메서드
    @PrePersist
    public void prePersist() {
        if (this.role == null) {
            this.role = "USER";
        }
    }
}
