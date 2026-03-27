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

import java.util.List;

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

    // 특정 게시글의 댓글 목록 조회
    // 수정 후 (추천)
    public List<CommentDto.Response> getCommentsByPost(Long postId) {
        // 최상위 댓글(parent가 null인 것)만 가져오면,
        // DTO의 children 리스트가 하위 댓글들을 알아서 트리 구조로 엮어줍니다.
        return commentRepository.findByPostIdAndParentIsNull(postId).stream()
                .map(CommentDto.Response::new)
                .toList();
    }

    // 댓글 수정
    @Transactional
    public Long updateComment(Long id, CommentDto.UpdateRequest dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + id));

        // 엔티티 내부에 구현한 update 메서드를 호출 (더티 체킹)
        comment.update(dto.getContent());

        return id;
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        if (!comment.getChildren().isEmpty()) {
            // 자식(대댓글)이 있다면 데이터는 남겨두고 문구만 수정
            comment.update("삭제된 댓글입니다.");
        } else {
            // 자식이 없다면 바로 삭제
            commentRepository.delete(comment);
        }
    }




}