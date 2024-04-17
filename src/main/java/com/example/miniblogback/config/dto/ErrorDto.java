package com.example.miniblogback.config.dto;

import com.example.miniblogback.config.exception.CustomExceptionHandler;
import com.example.miniblogback.config.exception.ErrorCode;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ErrorDto {
    private HttpStatus httpStatus;
    private String msg;

    public static ResponseEntity<ErrorDto> toResponseEntity(CustomExceptionHandler ex) {
        ErrorCode errorType = ex.getErrorCode();

        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(ErrorDto.builder()
                        .httpStatus(errorType.getHttpStatus())
                        .msg(errorType.getMessage())
                        .build());
    }
}
