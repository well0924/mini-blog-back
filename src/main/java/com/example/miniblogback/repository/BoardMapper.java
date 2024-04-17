package com.example.miniblogback.repository;

import com.example.miniblogback.vo.dto.BoardDto;
import com.example.miniblogback.vo.dto.ScrollDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    List<BoardDto.BoardResponseDto>boardList(ScrollDto scrollDto);
    Long boardListCount();
    Optional<BoardDto.BoardResponseDto> boardDetail(Long boardId);
    Long boardCreate(BoardDto.BoardRequestDto dto);
    Long boardUpdate(BoardDto.BoardRequestDto dto);
    void boardDelete(Long boardId);
    void boardReadCount(Long boardId);
}
