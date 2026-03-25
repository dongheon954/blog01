package org.example.blog.dto;

import org.example.blog.domain.Category;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        private String name;
        private Long parentId;
    }

    @Getter
    public static class Response {
        private Long id;
        private String name;
        private List<Response> children;

        public Response(Category category) {
            this.id = category.getId();
            this.name = category.getName();
            this.children = category.getChildren().stream()
                    .map(Response::new)
                    .collect(Collectors.toList());
        }
    }
}
