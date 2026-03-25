package org.example.blog.service;

import org.example.blog.domain.Comment;
import org.example.blog.domain.Post;
import org.example.blog.domain.User;
import org.example.blog.dto.CommentDto;
import org.example.blog.repository.CommentRepository;
import org.example.blog.repository.PostRepository;
import org.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createComment(Long userId, CommentDto.CreateRequest dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));

        Comment parent = null;
        if (dto.getParentId() != null) {
            parent = commentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("상위 댓글이 없습니다."));
        }

        Comment comment = Comment.builder()
                .content(dto.getContent())
                .user(user)
                .post(post)
                .parent(parent)
                .build();

        return commentRepository.save(comment).getId();
    }
}