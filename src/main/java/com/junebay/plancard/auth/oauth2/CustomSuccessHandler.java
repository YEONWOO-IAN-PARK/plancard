package com.junebay.plancard.auth.oauth2;

import com.junebay.plancard.auth.dto.CustomOAuth2User;
import com.junebay.plancard.auth.jwt.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author : IAN
 * @date : 2024-11-08
 * @description :
 */
@Slf4j
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    public CustomSuccessHandler(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.warn("접근했나?");

        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String providerId = customUserDetails.getProviderId();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(providerId, role, 60 * 60 * 60L);

        response.addCookie(createCookie("Authorization", token));
//        response.sendRedirect("http://localhost:3000/");
        response.sendRedirect("http://localhost:8080/index");
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
//        cookie.setSecure(true);   //로컬 환경은 http 환경이기 때문에 주석처리
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
