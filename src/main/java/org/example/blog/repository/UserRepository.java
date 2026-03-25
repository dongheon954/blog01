package org.example.blog.repository;

import org.example.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 로그인 시 ID로 유저 찾기
    Optional<User> findByUsername(String username);

    // 중복 가입 방지를 위한 체크
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
