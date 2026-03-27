package org.example.blog.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작성자 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 수정된 부분: Long categoryId 대신 Category 객체를 사용합니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(length = 20)
    private String status;

    @ColumnDefault("0")
    private Integer viewCount;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (this.status == null) this.status = "PUBLISHED";
        if (this.viewCount == null) this.viewCount = 0;
    }

    // === 비즈니스 로직: 게시글 수정 ===
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        // status나 category 수정이 필요하다면 여기에 추가 파라미터를 받을 수 있습니다.
    }

    // === 비즈니스 로직: 조회수 증가 ===
    public void incrementViewCount() {
        this.viewCount++;
    }
}