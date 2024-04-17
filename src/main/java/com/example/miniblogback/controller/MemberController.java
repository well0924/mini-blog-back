package com.example.miniblogback.controller;

import com.example.miniblogback.config.dto.ResponseDto;
import com.example.miniblogback.config.dto.ResponseUtil;
import com.example.miniblogback.service.MemberService;
import com.example.miniblogback.vo.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    //회원 목록
    @GetMapping("/list")
    public ResponseDto<?> memberList(){
        List<MemberDto.MemberResponse>list = memberService.memberList();
        return ResponseUtil.ok(HttpStatus.OK.value(),list);
    }
    //회원 단일 조회
    @GetMapping("/{id}")
    public ResponseDto<?>memberDetail(@PathVariable("id")Long memberId){
        Optional<MemberDto.MemberResponse>detail = memberService.memberDetail(memberId);
        return ResponseUtil.ok(HttpStatus.OK.value(),detail);
    }
    //회원 가입
    @PostMapping("/create")
    public ResponseDto<?>memberCreate(@RequestBody MemberDto.MemberRequest request){
        memberService.createMember(request);
        return ResponseUtil.ok(HttpStatus.OK.value(),"MemberCreate!");
    }

    //회원 탈퇴

    //회원 수정

}
