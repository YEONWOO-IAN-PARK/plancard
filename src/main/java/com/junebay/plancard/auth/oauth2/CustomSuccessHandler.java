package com.junebay.plancard.auth.oauth2;

import com.junebay.plancard.auth.dto.CustomOAuth2User;
import com.junebay.plancard.auth.jwt.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String providerId = customUserDetails.getProviderId();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String accessToken = jwtUtil.createJwt("Access-Token", providerId, role, 60 * 60 * 1L);
        String refreshToken = jwtUtil.createJwt("Refresh-Token", providerId, role, 60 * 60 * 24L);

        // TODO : 현재 액세스 토큰은 리프레시 토큰과 함께 쿠키에 저장되지만 리액트 서버를 사용할땐 response.setHeader로 액세스 토큰을 전달하면 된다.
        response.addCookie(jwtUtil.createCookie("Access-Token", accessToken));
        response.addCookie(jwtUtil.createCookie("Refresh-Token", refreshToken));
//        response.sendRedirect("http://localhost:3000/");
        response.sendRedirect("http://localhost:8080/home");
    }

}
