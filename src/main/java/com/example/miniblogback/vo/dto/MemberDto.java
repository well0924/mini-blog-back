package com.example.miniblogback.vo.dto;

import com.example.miniblogback.vo.BaseTime;
import com.example.miniblogback.vo.Role;
import lombok.*;

public class MemberDto {

    @Setter
    @Getter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberRequest{
        private Long memberId;
        private String nickName;
        private String password;
        private String email;
        private Role
                role;
        private String provider;
        private String refreshToken;
    }

    @Getter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberResponse extends BaseTime {
        private Long memberId;
        private String nickName;
        private String email;
        private String role;
        private String socialType;
        private String socialId;
        private String refreshToken;
    }
}
