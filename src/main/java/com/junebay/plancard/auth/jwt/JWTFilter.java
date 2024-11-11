package com.junebay.plancard.auth.jwt;

import com.junebay.plancard.auth.dto.CustomOAuth2User;
import com.junebay.plancard.auth.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author : IAN
 * @date : 2024-11-08
 * @description :
 */
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            filterChain.doFilter(request, response);
        }
        else {
            for (Cookie cookie : cookies) {

//            System.out.println(cookie.getName());
                if (cookie.getName().equals("Authorization")) {
                    authorization = cookie.getValue();
                }
            }

            if (authorization == null) {
                System.out.println("token is null");
                filterChain.doFilter(request, response);

                return;
            }

            String token = authorization;

            // 토큰 소멸 시간 검증
            if (jwtUtil.isExpired(token)) {
                System.out.println("token is expired");
                filterChain.doFilter(request, response);

                return;
            }

            // 토큰에서 providerId, role 획득
            String providerId = jwtUtil.getProviderId(token);
            String role = jwtUtil.getRole(token);

            // userDTO를 생성하여 값 setting
            UserDTO userDTO = new UserDTO();
            userDTO.setProviderId(providerId);
            userDTO.setRole(role);

            // UserDetails에 회원 정보 담기
            CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);

            Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);
        }
    }
}
