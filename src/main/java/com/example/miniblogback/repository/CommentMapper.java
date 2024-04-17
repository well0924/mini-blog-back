package com.example.miniblogback.repository;

import com.example.miniblogback.vo.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentDto.CommentResponse>commentList(Long boardId);
    CommentDto.CommentResponse commentDetail(Long commentId);
    Long commentCreate(CommentDto.CommentRequest commentRequest);
    Long commentUpdate(CommentDto.CommentRequest commentRequest);
    void commentDelete(Long commentId);
}
