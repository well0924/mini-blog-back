package com.example.miniblogback.config.security.jwt;

import com.example.miniblogback.config.security.jwt.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Log4j2
@Component
public class JwtTokenProvider implements InitializingBean {

    private static Key key;
    private final Long accessTokenExpired;
    private final Long refreshTokenExpired;
    private final String secretKey;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.access.expiration.seconds}")Long accessTokenExpired,
            @Value("${jwt.refresh.expiration.seconds}")Long refreshTokenExpired) {
        this.secretKey = secretKey;
        //1시간
        this.accessTokenExpired = accessTokenExpired *1000;
        //7일
        this.refreshTokenExpired = refreshTokenExpired * 1000;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        key = Keys.hmacShaKeyFor(secretByteKey);
    }

    //토큰 생성하기.(AccessToken & RefreshToken)
    public TokenDto generateToken(Authentication authentication){
        Long now = System.currentTimeMillis();

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String accessToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS512")
                .setSubject(authentication.getName())
                .claim("auth",authorities)
                .setExpiration(new Date(now + accessTokenExpired))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS512")
                .setSubject(authentication.getName())
                .setExpiration(new Date(now + refreshTokenExpired))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpired(accessTokenExpired)
                .refreshTokenExpired(refreshTokenExpired)
                .build();
    }

    //권한 인증
    public Authentication getAuthentication(String accessToken) {
        //토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    //accessToken 헤더에 싣기.
    public void sendAccessToken(HttpServletResponse response, String accessToken){
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Authorization",accessToken);
        log.info("accessToken 발급::"+accessToken);
    }
    //refreshToken 헤더에 싣기.
    public void sendRefreshToken(HttpServletResponse response,String refreshToken){
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Authorization",refreshToken);
        log.info("refreshToken 발급::"+refreshToken);
    }

    //토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
