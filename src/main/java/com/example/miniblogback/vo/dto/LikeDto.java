package com.example.miniblogback.vo.dto;

import com.example.miniblogback.vo.BaseTime;
import lombok.*;

public class LikeDto {

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    public static class LikeRequest{
        private Long boardId;
        private Long memberId;

    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LikeResponse extends BaseTime {
        private Long likeId;
        private Long boardId;
        private Long memberId;
    }
}
