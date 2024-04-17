package com.example.miniblogback.config.security.oauth2.service;

import com.example.miniblogback.config.security.oauth2.userInfo.OAuthAttributes;
import com.example.miniblogback.repository.MemberMapper;
import com.example.miniblogback.vo.Role;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Log4j2
@Service
@Transactional
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {


    private final MemberMapper memberMapper;

    public CustomOAuth2UserService(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        log.info("delegate::"+delegate);
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        //registeationId 추출
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info("registered::"+registrationId);
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        log.info("userName"+userNameAttributeName);

        //회원 정보
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        log.info("attribute::"+attributes.getAttributes());
        log.info("attribute::"+attributes.getName());
        log.info("attribute::"+attributes.getNameAttributeKey());
        log.info("attribute::"+attributes.getProvider());
        log.info("attribute::"+attributes.getEmail());

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_USER.getRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    //회원 확인
    /*private MemberVo userConfirm(OAuthAttributes attributes) {
        //회원을 확인후 없으면 가입하기.
        Optional<MemberVo> user = Optional.ofNullable(memberMapper.memberByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getProvider()))
                .orElse(attributes.toEntity()));

        log.info(user);
        log.info(user.get().getRole());
        return user.get();
    }*/
}
