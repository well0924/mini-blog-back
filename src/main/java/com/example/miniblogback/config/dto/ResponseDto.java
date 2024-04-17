package com.example.miniblogback.config.dto;

import com.example.miniblogback.config.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto<T> {
    private int httpStatus;
    private T data;
    private ErrorCode errorDto;

    @Builder
    public ResponseDto(int httpStatus, T data, ErrorCode errorDto){
        this.httpStatus = httpStatus;
        this.data = data;
        this.errorDto = errorDto;
    }

}
