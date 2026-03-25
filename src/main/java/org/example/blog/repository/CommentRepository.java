package org.example.blog.repository;

import org.example.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글을 작성순(오래된순)으로 조회
    List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);

    // 특정 유저가 쓴 댓글 목록 조회
    List<Comment> findByUserId(Long userId);
}
