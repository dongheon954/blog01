package org.example.blog.controller;

import lombok.RequiredArgsConstructor;
import org.example.blog.dto.CommentDto;
import org.example.blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Long> createComment(@RequestParam Long userId,
                                              @RequestBody CommentDto.CreateRequest dto) {
        return ResponseEntity.ok(commentService.createComment(userId, dto));
    }
}