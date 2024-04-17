package com.example.miniblogback.service;

import com.example.miniblogback.config.exception.CustomExceptionHandler;
import com.example.miniblogback.config.exception.Enum.MemberErrorCode;
import com.example.miniblogback.repository.MemberMapper;
import com.example.miniblogback.vo.MemberVo;
import com.example.miniblogback.vo.Role;
import com.example.miniblogback.vo.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder cryptPasswordEncoder;

    //회원 목록
    @Transactional(readOnly = true)
    public List<MemberDto.MemberResponse>memberList(){
        return memberMapper.memberList();
    }

    //회원 가입
    @Transactional
    public void createMember(MemberDto.MemberRequest request){
        //이메일 중복처리.
        if(memberMapper.duplicatedEmail(request.getEmail())){
           throw new CustomExceptionHandler(MemberErrorCode.EMAIL_DUPLICATED);
        }

        MemberVo memberVo = MemberVo
                .builder()
                .email(request.getEmail())
                .nickName(request.getNickName())
                .password(cryptPasswordEncoder.encode(request.getPassword()))
                .provider(request.getProvider())
                .role(Role.ROLE_USER.getRole())
                .build();

        memberMapper.createMember(memberVo);
        log.info("result:::"+memberVo);
    }

    //회원 조회
    @Transactional
    public MemberVo memberByEmail(String email) {
        return memberMapper.memberByEmail(email);
    }


    @Transactional
    public Optional<MemberVo>memberByRefreshToken(String refreshToken){
        return memberMapper.memberByRefreshToken(refreshToken);
    }

    //회원 조회
    @Transactional(readOnly = true)
    public Optional<MemberDto.MemberResponse> memberDetail(Long id){
        return memberMapper.memberById(id);
    }


}
