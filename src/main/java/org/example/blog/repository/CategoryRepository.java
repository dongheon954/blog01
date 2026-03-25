package org.example.blog.repository;

import org.example.blog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    // 최상위 카테고리만 조회 (parent_id가 null인 경우)
    List<Category> findByParentIsNull();
}
