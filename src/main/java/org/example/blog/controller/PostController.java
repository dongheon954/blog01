package org.example.blog.controller;

import lombok.RequiredArgsConstructor;
import org.example.blog.dto.PostDto;
import org.example.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성
    // 실제로는 인증(Security)을 통해 userId를 가져오지만,
    // 현재는 테스트를 위해 쿼리 파라미터로 userId를 받도록 구현했습니다.
    @PostMapping
    public ResponseEntity<Long> createPost(@RequestParam Long userId,
                                           @RequestBody PostDto.CreateRequest dto) {
        return ResponseEntity.ok(postService.createPost(userId, dto));
    }

    // 전체 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostDto.Response>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<PostDto.Response> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }
}