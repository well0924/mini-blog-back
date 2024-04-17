package com.example.miniblogback.vo;


import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVo {
    private Long memberId;
    private String password;
    private String nickName;
    private String email;
    private String role;
    private String provider;
    private String refreshToken;

    //권한
    public String getRole(){
        return this.role;
    }

    public MemberVo update(String nickName, String provider) {
        this.nickName = nickName;
        this.provider = provider;
        return this;
    }
}
