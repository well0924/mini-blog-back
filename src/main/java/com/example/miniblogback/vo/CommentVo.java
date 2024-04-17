package com.example.miniblogback.vo;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo extends BaseTime{
    private Long commentId;
    private String commentContents;
    private Long boardId;

}
