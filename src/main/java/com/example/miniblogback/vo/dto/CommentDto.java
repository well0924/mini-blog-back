package com.example.miniblogback.vo.dto;

import com.example.miniblogback.vo.BaseTime;
import lombok.*;

public class CommentDto {

    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentRequest{
        private String commentContents;
        private Long boardId;
        private Long commentId;
    }

    @Setter
    @Getter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponse extends BaseTime {
        private Long commentId;
        private String commentContents;
        private Long boardId;
    }
}
