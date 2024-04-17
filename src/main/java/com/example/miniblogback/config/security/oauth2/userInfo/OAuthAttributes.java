package com.example.miniblogback.config.security.oauth2.userInfo;

import com.example.miniblogback.vo.MemberVo;
import com.example.miniblogback.vo.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String provider;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .provider("Google")
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public MemberVo toEntity(){
        return MemberVo
                .builder()
                .email(email)
                .provider(provider)
                .nickName(name)
                .role(Role.ROLE_USER.getRole())
                .build();
    }
}
