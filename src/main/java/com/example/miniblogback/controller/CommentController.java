package com.example.miniblogback.controller;

import com.example.miniblogback.config.dto.ResponseDto;
import com.example.miniblogback.config.dto.ResponseUtil;
import com.example.miniblogback.service.CommentService;
import com.example.miniblogback.vo.dto.CommentDto;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/list/{id}")
    public ResponseDto<?> commentList(@PathVariable("id") Long boardId){
        List<CommentDto.CommentResponse>list = commentService.commentResponseList(boardId);
        return ResponseUtil.ok(HttpStatus.OK.value(),list);
    }

    @PostMapping("/create")
    public ResponseDto<?>commentCreate(@RequestBody CommentDto.CommentRequest request){
        Long result = commentService.commentCreate(request);
        return ResponseUtil.ok(HttpStatus.CREATED.value(),result);
    }

    @PutMapping("/{id}")
    public ResponseDto<?>commentUpdate(@PathVariable("id") Long commentId,
                                          @RequestBody CommentDto.CommentRequest request){
        Long result = commentService.commentUpdate(commentId,request);
        return ResponseUtil.ok(HttpStatus.OK.value(),result);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<?>commentDelete(@PathVariable("id")Long commentId){
        commentService.commentDelete(commentId);
        return ResponseUtil.ok(HttpStatus.NO_CONTENT.value(),null);
    }
}
