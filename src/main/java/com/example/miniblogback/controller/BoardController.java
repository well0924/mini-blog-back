
package com.example.miniblogback.controller;

import com.example.miniblogback.config.dto.ResponseDto;
import com.example.miniblogback.config.dto.ResponseUtil;
import com.example.miniblogback.service.BoardService;
import com.example.miniblogback.vo.dto.BoardDto;
import com.example.miniblogback.vo.dto.ScrollDto;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseDto<List<BoardDto.BoardResponseDto>> boardList(@ModelAttribute ScrollDto scrollDto){
        List<BoardDto.BoardResponseDto>list = boardService.list(scrollDto);
        log.info("???"+scrollDto.toString());
        return ResponseUtil.ok(HttpStatus.OK.value(),list);
    }

    @GetMapping("/{id}")
    public ResponseDto<?>boardDetail(@PathVariable("id")Long boardId){
        Optional<BoardDto.BoardResponseDto> detail = boardService.boardDetail(boardId);
        return ResponseUtil.ok(HttpStatus.OK.value(),detail);
    }


    @PostMapping("/create")
    public ResponseDto<?>boardCreate(@RequestBody BoardDto.BoardRequestDto requestDto){
        Long createResult = boardService.boardCreate(requestDto);
        return ResponseUtil.ok(HttpStatus.CREATED.value(),createResult);
    }

    @PutMapping("/{id}")
    public ResponseDto<?>boardUpdate(@PathVariable("id")Long boardId,@RequestBody BoardDto.BoardRequestDto requestDto){
        Long updateResult = boardService.boardUpdate(boardId,requestDto);
        return ResponseUtil.ok(HttpStatus.OK.value(),updateResult);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<?>boardDelete(@PathVariable("id")Long boardId){
        boardService.boardDelete(boardId);
        return ResponseUtil.ok(HttpStatus.NO_CONTENT.value(),null);
    }
}
