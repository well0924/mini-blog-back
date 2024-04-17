package com.example.miniblogback.vo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrollDto {
    //마지막 게시글 번호
    private Long boardId;
    //보여줄 게시글 수
    private Long amount;
    //검색타입
    private String searchType="";
    //검색어
    private String keyword="";

}
