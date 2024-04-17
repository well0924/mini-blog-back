package com.example.miniblogback.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo extends BaseTime{
    private Long categoryId;
    private String categoryName;
}
