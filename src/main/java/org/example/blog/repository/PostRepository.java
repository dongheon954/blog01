package org.example.blog.repository;

import org.example.blog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 최신순으로 전체 게시글 조회
    List<Post> findAllByOrderByCreatedAtDesc();

    // 특정 카테고리에 속한 글만 조회
    List<Post> findByCategoryId(Long categoryId);

    // 제목에 특정 키워드가 포함된 글 검색
    List<Post> findByTitleContaining(String keyword);
}
