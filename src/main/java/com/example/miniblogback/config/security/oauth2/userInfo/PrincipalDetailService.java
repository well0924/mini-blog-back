package com.example.miniblogback.config.security.oauth2.userInfo;

import com.example.miniblogback.repository.MemberMapper;
import com.example.miniblogback.vo.MemberVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;

    public PrincipalDetailService(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVo member = memberMapper.memberByEmail(username);

        return new PrincipalDetail(member);
    }
}
