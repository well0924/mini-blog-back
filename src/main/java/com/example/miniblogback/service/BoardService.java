package com.example.miniblogback.service;

import com.example.miniblogback.config.exception.CustomExceptionHandler;
import com.example.miniblogback.config.exception.Enum.BoardErrorCode;
import com.example.miniblogback.repository.BoardMapper;
import com.example.miniblogback.vo.dto.BoardDto;
import com.example.miniblogback.vo.dto.ScrollDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    @Transactional
    public List<BoardDto.BoardResponseDto>list(ScrollDto scrollDto){

        List<BoardDto.BoardResponseDto>boardList = boardMapper.boardList(scrollDto);
        log.info(scrollDto.toString());

        //게시글이 없는 경우 & 검색결과가 없는 경우
        if(boardList.isEmpty()){
            throw new CustomExceptionHandler(BoardErrorCode.NOT_BOARD_LIST);
        }

        return boardList;
    }

    @Transactional
    public Optional<BoardDto.BoardResponseDto> boardDetail(Long boardId){
        //조회
        Optional<BoardDto.BoardResponseDto> responseDto = boardMapper.boardDetail(boardId);

        //조회수 증가
        responseDto.ifPresentOrElse(
            boardResponseDto -> boardMapper.boardReadCount(boardId)
        ,//객체가 없는 경우에는 예외처리
            ()-> {throw new CustomExceptionHandler(BoardErrorCode.NOT_FOUND_BOARD);
        });

        return responseDto;
    }

    @Transactional
    public Long boardCreate(BoardDto.BoardRequestDto requestDto){
        return boardMapper.boardCreate(requestDto);
    }

    @Transactional
    public Long boardUpdate(Long boardId, BoardDto.BoardRequestDto requestDto){
        requestDto.setBoardId(boardId);
        return boardMapper.boardUpdate(requestDto);
    }

    @Transactional
    public void boardDelete(Long boardId){
        boardMapper.boardDelete(boardId);
    }

    @Transactional
    public Long boardCount(){
        return boardMapper.boardListCount();
    }

}
