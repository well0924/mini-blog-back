package com.example.miniblogback.controller;

import com.example.miniblogback.config.dto.ResponseDto;
import com.example.miniblogback.config.dto.ResponseUtil;
import com.example.miniblogback.service.LikeService;

import com.example.miniblogback.vo.dto.LikeDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/duplicated/{memberId}/{boardId}")
    public ResponseDto<?> likeDuplicated(@PathVariable(value = "memberId",required = false) Long memberId,
                                         @PathVariable(value = "boardId",required = true) Long boardId){
        
        Optional<LikeDto.LikeResponse> result = likeService.likeDuplicated(memberId,boardId);
        log.info(result);
        return ResponseUtil.ok(HttpStatus.OK.value(),result);
    }

    @PostMapping("/plus-like")
    public ResponseDto<?>plusLike(@RequestBody LikeDto.LikeRequest request){
        Long plusResult = likeService.likePlus(request);
        log.info(plusResult);
        return ResponseUtil.ok(HttpStatus.OK.value(),"좋아요가 추가 되었습니다.");
    }

    @DeleteMapping("/minus-like")
    public ResponseDto<?>minusLike(@RequestBody LikeDto.LikeRequest request){
        Long minusResult = likeService.likeMinus(request);
        log.info(minusResult);
        return ResponseUtil.ok(HttpStatus.OK.value(),"좋아요를 취소했습니다.");
    }
}
