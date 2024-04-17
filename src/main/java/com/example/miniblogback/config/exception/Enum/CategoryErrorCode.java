package com.example.miniblogback.config.exception.Enum;

import com.example.miniblogback.config.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CategoryErrorCode implements ErrorCode {
    NOT_CATEGORY(HttpStatus.NOT_FOUND,"카테고리가 없습니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
