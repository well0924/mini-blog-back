package com.example.miniblogback.service;

import com.example.miniblogback.config.exception.CustomExceptionHandler;
import com.example.miniblogback.config.exception.Enum.BoardErrorCode;
import com.example.miniblogback.config.exception.Enum.MemberErrorCode;
import com.example.miniblogback.repository.LikesMapper;
import com.example.miniblogback.vo.dto.BoardDto;
import com.example.miniblogback.vo.dto.LikeDto;
import com.example.miniblogback.vo.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class LikeService {

    private final LikesMapper likesMapper;
    private final MemberService memberService;
    private final BoardService boardService;

    //좋아요 중복처리
    public Optional<LikeDto.LikeResponse>likeDuplicated(@Param("memberId") Long memberId, @Param("boardId") Long boardId){

        Optional<MemberDto.MemberResponse>memberResponse = Optional
                .ofNullable(memberService.memberDetail(memberId)
                .orElseThrow(() -> new CustomExceptionHandler(MemberErrorCode.MEMBER_NOT_FOUND)));
        log.info(memberId);
        log.info(memberResponse.get());
        Optional<BoardDto.BoardResponseDto>boardResponseDto = Optional
                .ofNullable(boardService.boardDetail(boardId)
                .orElseThrow(() -> new CustomExceptionHandler(BoardErrorCode.NOT_FOUND_BOARD)));
        log.info(boardId);
        log.info(boardResponseDto.get());
        return likesMapper
                .duplicatedLikes(boardResponseDto.get().getBoardId(),memberResponse.get().getMemberId());
    }

    //좋아요 추가
    @Transactional
    public Long likePlus(LikeDto.LikeRequest request){
        return likesMapper.plusLike(request);
    }

    //좋아요 감소
    @Transactional
    public Long likeMinus(LikeDto.LikeRequest request){
        return likesMapper.minusLike(request);
    }
}
