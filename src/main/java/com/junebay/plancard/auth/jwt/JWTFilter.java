package com.junebay.plancard.auth.jwt;

import com.junebay.plancard.auth.dto.CustomOAuth2User;
import com.junebay.plancard.auth.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = null;
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();


        if (cookies == null) {
            filterChain.doFilter(request, response);
        }
        // Refresh Token 가져오기
        else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Access-Token")) {
                    accessToken = cookie.getValue();
                }
                if (cookie.getName().equals("Refresh-Token")) {
                    refreshToken = cookie.getValue();
                }
                if (accessToken != null && refreshToken != null) {
                    break;
                }
            }

            // Refresh Token이 발급되지 않았을 경우 다음 필터로 넘어가 발급을 진행한다.
            if (refreshToken == null) {
                System.out.println("refresh token is null");
                filterChain.doFilter(request, response);

                return;
            }


            // Access Token 검증
            if (accessToken != null) {
                // Access Token이 유효하다면 유저 인증 처리
                if (!jwtUtil.isExpired(accessToken)) {
                    log.warn("Access token is still valid.");
                    authenticateUser(accessToken);
                    filterChain.doFilter(request, response);

                    return;
                }
                // Access Token이 만료되었다면, Refresh Token을 검증하고 통과 시 재발급
                else {
                    if (!jwtUtil.isExpired(refreshToken)) {
                        String providerId = jwtUtil.getProviderId(refreshToken);
                        String role = jwtUtil.getRole(refreshToken);

                        // TODO : 현재 액세스 토큰은 리프레시 토큰과 함께 쿠키에 저장되지만 리액트 서버를 사용할땐 response.setHeader로 액세스 토큰을 전달하면 된다.
                        // 새 Access Token 발급
                        // TODO : 테스트 끝난 후 만료시간 정상으로 변경하기
//                        String newAccessToken = jwtUtil.createJwt("Access-Token", providerId, role, 60 * 60 * 1000L);
                        String newAccessToken = jwtUtil.createJwt("Access-Token", providerId, role, 60 * 5 * 1000L);
                        response.addCookie(jwtUtil.createCookie("Access-Token", newAccessToken));

                        authenticateUser(newAccessToken);
                        filterChain.doFilter(request, response);
                    }
                }
            }

            filterChain.doFilter(request, response);
        }
    }

    private void authenticateUser(String accessToken) {
        // 토큰에서 providerId, role 획득
        String providerId = jwtUtil.getProviderId(accessToken);
        String role = jwtUtil.getRole(accessToken);

        // userDTO를 생성하여 값 setting
        UserDTO userDTO = new UserDTO();
        userDTO.setProviderId(providerId);
        userDTO.setRole(role);

        // UserDetails에 회원 정보 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
