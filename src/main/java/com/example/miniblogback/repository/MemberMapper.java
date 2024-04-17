package com.example.miniblogback.repository;

import com.example.miniblogback.vo.MemberVo;
import com.example.miniblogback.vo.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    //회원 목록
    List<MemberDto.MemberResponse>memberList();
    //회원가입(소셜 로그인 첫 가입시)
    void createMember(MemberVo request);
    //로그인(회원 이메일을 기준)
    MemberVo memberByEmail(String email);
    //회원 조회(리프레시 토큰 기준)
    Optional<MemberVo> memberByRefreshToken(String refreshToken);
    //회원 단일 조회(회원번호 기준)
    Optional<MemberDto.MemberResponse> memberById(Long memberId);
    //토큰 업데이트
    Long updateRefreshToken(@Param("email")String email,@Param("refreshToken") String refreshToken);
    //회원 이메일 중복체크
    boolean duplicatedEmail(String email);

}
