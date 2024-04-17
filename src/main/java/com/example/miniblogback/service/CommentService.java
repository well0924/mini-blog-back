package com.example.miniblogback.service;

import com.example.miniblogback.config.exception.CustomExceptionHandler;
import com.example.miniblogback.config.exception.Enum.CommentErrorCode;
import com.example.miniblogback.repository.CommentMapper;
import com.example.miniblogback.vo.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    public List<CommentDto.CommentResponse>commentResponseList(Long boardId){
        List<CommentDto.CommentResponse>commentList =commentMapper.commentList(boardId);

        if(commentList.isEmpty()){
            throw new CustomExceptionHandler(CommentErrorCode.NOT_COMMENT_LIST) ;
        }

        return commentList;
    }

    @Transactional(readOnly = true)
    public CommentDto.CommentResponse commentDetail(Long commentId){
        CommentDto.CommentResponse commentResponse = commentMapper.commentDetail(commentId);

        if(commentResponse == null){
            throw new CustomExceptionHandler(CommentErrorCode.NOT_FOUND_COMMENT);
        }

        return commentResponse;
    }

    @Transactional
    public Long commentCreate(CommentDto.CommentRequest commentRequest){
        return commentMapper.commentCreate(commentRequest);
    }

    @Transactional
    public Long commentUpdate(Long commentId,CommentDto.CommentRequest commentRequest){
        commentRequest.setCommentId(commentId);
        return commentMapper.commentUpdate(commentRequest);
    }

    @Transactional
    public void commentDelete(Long commentId){
        commentMapper.commentDelete(commentId);
    }

}
