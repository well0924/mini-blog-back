package com.example.miniblogback.config.exception;

import com.example.miniblogback.config.dto.ErrorDto;
import com.example.miniblogback.config.dto.ResponseDto;
import com.example.miniblogback.config.dto.ResponseUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomExceptionHandler.class)
    public ResponseDto<ErrorDto> exception(CustomExceptionHandler customExceptionHandler){
        return ResponseUtil.fail(customExceptionHandler.getHttpStatus().value(),customExceptionHandler.getErrorCode());
    }
}
