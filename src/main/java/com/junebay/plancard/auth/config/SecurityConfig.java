package com.junebay.plancard.auth.config;

import com.junebay.plancard.auth.jwt.JWTFilter;
import com.junebay.plancard.auth.jwt.JWTUtil;
import com.junebay.plancard.auth.oauth2.CustomSuccessHandler;
import com.junebay.plancard.auth.service.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author : IAN
 * @date : 2024-11-07
 * @description :
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;

    /**
     * Security 설정을 구성하는 클래스.
     * CustomOAuth2UserService, CustomSuccessHandler, JWTUtil을 주입받아 보안 설정에 활용한다.
     */
    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService
                        , CustomSuccessHandler customSuccessHandler
                        , JWTUtil jwtUtil) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CORS 설정을 추가한다.
        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // 허용된 출처 설정
                configuration.setAllowedMethods(Collections.singletonList("*")); // 허용된 HTTP 메서드 설정
                configuration.setAllowCredentials(true); // 쿠키 전송 허용
                configuration.setAllowedHeaders(Collections.singletonList("*")); // 모든 헤더 허용
                configuration.setMaxAge(3600L); // 캐싱 시간 설정
                configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "Access-Token")); // 클라이언트에 노출할 헤더 설정

                return configuration;
            }
        }));

        // CSRF 방어를 비활성화한다.
        http.csrf(AbstractHttpConfigurer::disable);

        // Form 기반 로그인 방식을 비활성화한다.
        http.formLogin(AbstractHttpConfigurer::disable);

        // HTTP Basic 인증 방식을 비활성화한다.
        http.httpBasic(AbstractHttpConfigurer::disable);

        // JWT 필터를 OAuth2LoginAuthenticationFilter 이후에 추가한다.
        http.addFilterAfter(new JWTFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);

        // OAuth2 로그인 설정
        http.oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(customOAuth2UserService)) // 사용자 정보를 처리할 서비스 설정
                .successHandler(customSuccessHandler) // 로그인 성공 핸들러 설정
                .failureUrl("/login?error=true") // 로그인 실패 시 리다이렉트 URL 설정
        );

        // 경로별 접근 권한 설정
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/home").permitAll() // /home 경로는 누구나 접근 가능
                .requestMatchers("/view/home.html").permitAll() // /view/home.html 경로는 누구나 접근 가능
                .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
        );

        // 세션 관리 설정 : STATELESS
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션 상태를 사용하지 않도록 설정

        return http.build();
    }
}
