package com.example.miniblogback.config.dto;

import com.example.miniblogback.config.exception.ErrorCode;


public class ResponseUtil {

    //성공
    public static <T> ResponseDto<T> ok(int httpStatus, T response) {
        return ResponseDto.<T>builder()
                .httpStatus(httpStatus)
                .data(response)
                .build();
    }

    //실패
    public static <T> ResponseDto<T> fail(int httpStatus, ErrorCode errorDto){
        return ResponseDto.<T>builder()
                .httpStatus(httpStatus)
                .errorDto(errorDto)
                .build();
    }
}
