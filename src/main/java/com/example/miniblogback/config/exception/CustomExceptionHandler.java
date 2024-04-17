package com.example.miniblogback.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomExceptionHandler extends RuntimeException{
    private ErrorCode errorCode;
    private String message;
    private HttpStatus httpStatus;

    public CustomExceptionHandler(ErrorCode e){
        this.errorCode =e;
        this.httpStatus = e.getHttpStatus();
        this.message = e.getMessage();
    }
}
