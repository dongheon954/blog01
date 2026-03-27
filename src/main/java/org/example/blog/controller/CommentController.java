package org.example.blog.controller;

import lombok.RequiredArgsConstructor;
import org.example.blog.dto.CommentDto;
import org.example.blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<Long> createComment(@RequestParam Long userId,
                                              @RequestBody CommentDto.CreateRequest dto) {
        return ResponseEntity.ok(commentService.createComment(userId, dto));
    }

    // 특정 게시글의 전체 댓글 목록 조회
    @GetMapping
    public ResponseEntity<List<CommentDto.Response>> getCommentsByPost(@RequestParam Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Long> updateComment(@PathVariable Long id,
                                              @RequestBody CommentDto.UpdateRequest dto) {
        return ResponseEntity.ok(commentService.updateComment(id, dto));
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}