package com.example.miniblogback.vo.dto;

import com.example.miniblogback.vo.BaseTime;
import lombok.*;

public class CategoryDto {

    @Setter
    @ToString
    public static class CategoryRequest{
        private String categoryName;
    }

    @Getter
    @ToString
    @Builder
    @AllArgsConstructor
    public static class CategoryResponse extends BaseTime {
        private Long categoryId;
        private String categoryName;
    }
}
