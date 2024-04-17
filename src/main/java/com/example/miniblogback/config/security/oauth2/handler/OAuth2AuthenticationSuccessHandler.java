package com.example.miniblogback.config.security.oauth2.handler;

import com.example.miniblogback.config.security.jwt.JwtTokenProvider;
import com.example.miniblogback.config.security.jwt.dto.TokenDto;
import com.example.miniblogback.repository.MemberMapper;
import com.example.miniblogback.vo.MemberVo;
import com.example.miniblogback.vo.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberMapper memberMapper;


    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 success!!");

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String userEmail = (String)attributes.get("email");
        String userName = (String)attributes.get("name");
        String provider;
        MemberVo memberVo = memberMapper.memberByEmail(userEmail);
        log.info("?????????::::"+memberVo);
        MemberVo user = new MemberVo();

        if(memberVo == null){
            user = MemberVo
                    .builder()
                    .provider("Google")
                    .nickName(userName)
                    .email(userEmail)
                    .role(Role.ROLE_USER.getRole())
                    .build();

            log.info(user);
            memberMapper.createMember(user);
            log.info(user);
        }
        //rt를 회원 객체에 저장하기.
        if(authentication.getPrincipal()!=null){

            //1.성공이 된 경우에는 토큰(at+rt)을 발급
            TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);

            //2.rt 디비에 저장
            memberMapper.updateRefreshToken(userEmail,tokenDto.getRefreshToken());
            //3.at + rt 를 헤더에 추가해서 보내기.
            jwtTokenProvider.sendAccessToken(response,tokenDto.getAccessToken());
            jwtTokenProvider.sendRefreshToken(response,tokenDto.getRefreshToken());
            log.info(tokenDto);
            getRedirectStrategy().sendRedirect(request,response,getRedirectUrl(tokenDto));
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

    //
    private String getRedirectUrl(TokenDto tokenDto){
        return UriComponentsBuilder
                .fromUriString("http://localhost:8088/page/test")
                .queryParam("accessToken",tokenDto.getAccessToken())
                .queryParam("refreshToken",tokenDto.getRefreshToken())
                .build()
                .toUriString();
    }
}
