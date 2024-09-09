package com.junebay.plancard.jwt;

import com.junebay.plancard.config.ExpireTime;
import com.junebay.plancard.dto.UserResponseDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author : IAN
 * @date : 2024-09-09
 * @description : JWT 토큰 공급자 설정
 */
@Component
public class JwtTokenProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class.getName());

    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String AUTHORITIES_KEY = "auth";
    private final String BEARER_TYPE = "Bearer";
    private final String TYPE_ACCESS = "access";
    private final String TYPE_REFRESH = "refresh";

    private final Key key;

    // secretKey는 외부 설정에서 받아와 디코딩한 후 Key 객체로 변환
    public JwtTokenProvider(@Value("${oauth.jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // Authentication 객체에서 토큰을 생성하는 메서드 (유저 정보와 권한 정보 포함)
    public UserResponseDto.TokenInfo generateToken(Authentication authentication) {
        return generateToken(authentication.getName(), authentication.getAuthorities());
    }

    // 사용자 이름과 권한을 받아서 AccessToken과 RefreshToken을 생성하는 메서드
    public UserResponseDto.TokenInfo generateToken(String name, Collection<? extends GrantedAuthority> inputAuthorities) {
        // 권한 가져오기
        String authorities = inputAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(name)                     // 토큰에 사용자 이름 설정
                .claim(AUTHORITIES_KEY, authorities)  // 권한 정보 추가
                .claim("type", TYPE_ACCESS)     // 토큰 타입을 access로 설정
                .setIssuedAt(now)                     // 토큰 발행시간 설정
                .setExpiration(new Date(now.getTime() + ExpireTime.ACCESS_TOKEN_EXPIRE_TIME))   // 만료시간 설정
                .signWith(key, SignatureAlgorithm.HS256)    // 서명 알고리즘과 키 설정
                .compact(); // 토큰 생성

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .claim("type", TYPE_REFRESH)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ExpireTime.REFRESH_TOKEN_EXPIRE_TIME))  // Refresh Token 만료시간 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // 생성된 Access Token과 Refresh Token을 담은 TokenInfo 객체 반환
        return UserResponseDto.TokenInfo.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpirationTime(ExpireTime.ACCESS_TOKEN_EXPIRE_TIME)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(ExpireTime.REFRESH_TOKEN_EXPIRE_TIME)
                .build();
    }

    // JWT 토큰을 복호화하고 사용자 정보를 얻어 Authentication 객체로 변환하는 메서드
    public Authentication getAuthentication(String accessToken) {

        Claims claims = parseClaim(accessToken);    // 토큰 복호화 및 클레임 추출

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 추출
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new).toList();
        
        // UserDetails 객체 생성 (비밀번호는 빈 문자열로 설정)
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        // Authentication 객체 반환
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
 
    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            // 서명 검증 및 토큰 파싱
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }
        return false;
    }

    // JWT 토큰에서 Claims(데이터)를 추출하는 메서드
    private Claims parseClaim(String accessToken) {
        try {
            // 토큰에서 클레임을 파싱하여 반환
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            log.info("JWT Token is Expired.", e);
            return e.getClaims();   // 만료된 경우에도 클레임 반환
        }
    }

    // HTTP 요청 헤더에서 토큰을 추출하는 메서드
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        // 토큰이 Bearer 타입이면 앞에 "Bearer "를 제거하고 반환
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
