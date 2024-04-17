package com.example.miniblogback.config.exception.Enum;

import com.example.miniblogback.config.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BoardErrorCode implements ErrorCode {
    NOT_BOARD_LIST(HttpStatus.NOT_FOUND,"게시글 목록이 없습니다."),
    NOT_FOUND_BOARD(HttpStatus.NOT_FOUND,"게시글을 찾을 수가 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
