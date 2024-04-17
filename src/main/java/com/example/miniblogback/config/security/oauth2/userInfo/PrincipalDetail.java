package com.example.miniblogback.config.security.oauth2.userInfo;

import com.example.miniblogback.vo.MemberVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Log4j2
@Getter
@ToString
@AllArgsConstructor
public class PrincipalDetail implements UserDetails {
    private final MemberVo memberVo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority>authorities = new ArrayList<>();
        log.info(authorities);
        authorities.add(()->String.valueOf(memberVo.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return memberVo.getPassword();
    }

    @Override
    public String getUsername() {
        return memberVo.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
