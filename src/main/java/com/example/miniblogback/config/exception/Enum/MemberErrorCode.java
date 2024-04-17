package com.example.miniblogback.config.exception.Enum;

import com.example.miniblogback.config.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"회원을 찾을수 없습니다."),
    EMAIL_DUPLICATED(HttpStatus.CONFLICT,"이미 있는 이메일입니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
