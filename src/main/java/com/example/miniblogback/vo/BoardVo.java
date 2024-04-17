package com.example.miniblogback.vo;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardVo extends BaseTime{
    private Long boardId;
    private String boardTitle;
    private String boardAuthor;
    private String boardContents;
    private Long readCount;
}
