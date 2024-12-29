package com.junebay.plancard.auth.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author : IAN
 * @date : 2024-11-08
 * @description :
 */
@Slf4j
@Component
public class JWTUtil {

    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret)  {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getProviderId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("providerId", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public String getCategory(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

    public Boolean isExpired(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration()
                    .before(new Date());
        } catch (JwtException e) {
            // JWT가 유효하지 않거나 잘못된 형식일 경우
            log.warn("Invalid JWT token: " + e.getMessage());
            return true; // 만료된 것으로 간주
        } catch (IllegalArgumentException e) {
            // 토큰이 null 또는 빈 값일 경우
            log.warn("JWT token is null or empty: " + e.getMessage());
            return true; // 만료된 것으로 간주
        }
    }


    public String createJwt(String category, String providerId, String role, Long expireMs) {
        return Jwts.builder()
                .claim("category", category)
                .claim("providerId", providerId)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireMs))
                .signWith(secretKey)
                .compact();
    }

    public Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        // TODO : 테스트 끝난 후 만료시간 정상으로 변경하기
//        cookie.setMaxAge(60 * 60 * 24);
        cookie.setMaxAge(60 * 60 * 1);
//        cookie.setSecure(true);   //로컬 환경은 http 환경이기 때문에 주석처리
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
