package com.example.miniblogback.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class LikeVo extends BaseTime{
    private Long likeId;
    private Long boardId;
    private Long memberId;
}
