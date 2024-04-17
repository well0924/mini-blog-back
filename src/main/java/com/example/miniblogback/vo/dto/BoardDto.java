package com.example.miniblogback.vo.dto;

import com.example.miniblogback.vo.BaseTime;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    @Setter
    @Getter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardRequestDto{
        private Long boardId;
        private String boardTitle;
        private String boardContents;
        private String boardAuthor;
        private Long categoryId;
    }

    @Setter
    @Getter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardResponseDto extends BaseTime {
        private Long boardId;
        private String boardTitle;
        private String boardAuthor;
        private String boardContents;
        private Long readCount;
        private Long categoryId;
        private String categoryName;
        //좋아요 갯수.
        private Long likeCount;
        //댓글 갯수
        private Long commentCount;
        //댓글 목록
        private List<CommentDto.CommentResponse>commentResponseList = new ArrayList<>();
    }
}
