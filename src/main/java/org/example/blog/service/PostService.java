package org.example.blog.service;

import org.example.blog.domain.Category;
import org.example.blog.domain.Post;
import org.example.blog.domain.User;
import org.example.blog.dto.PostDto;
import org.example.blog.repository.CategoryRepository;
import org.example.blog.repository.PostRepository;
import org.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long createPost(Long userId, PostDto.CreateRequest dto) {
        // 1. 작성자 존재 여부 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 유저입니다."));

        // 2. 카테고리 설정 (전달받은 categoryId가 있을 경우에만 조회)
        Category category = null;
        if (dto.getCategoryId() != null) { // DTO의 필드명이 categoryId이므로 getCategoryId() 호출
            category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 카테고리입니다."));
        }

        // 3. 엔티티 생성 및 빌드
        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .status(dto.getStatus() != null ? dto.getStatus() : "PUBLISHED")
                .user(user)
                .category(category)
                .viewCount(0)
                .build();

        return postRepository.save(post).getId();
    }

    // 전체 게시글 최신순 조회
    public List<PostDto.Response> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostDto.Response::new)
                .collect(Collectors.toList());
    }

    // 특정 게시글 상세 조회
    public PostDto.Response getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new PostDto.Response(post);
    }
    // 게시글 수정
    @Transactional
    public Long updatePost(Long id, PostDto.UpdateRequest dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 도메인(Entity) 내부에 업데이트 로직을 두는 '더티 체킹' 방식을 권장합니다.
        post.update(dto.getTitle(), dto.getContent());

        return id;
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postRepository.delete(post);
    }
}