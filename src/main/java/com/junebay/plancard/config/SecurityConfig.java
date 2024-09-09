package com.junebay.plancard.config;

import com.junebay.plancard.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author : IAN
 * @date : 2024-09-09
 * @description :
 */
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // disable 설정
        http
                .cors(Customizer.withDefaults())                // 기본 CORS 설정
                .httpBasic(AbstractHttpConfigurer::disable)     // httpBasic을 사용하지 않음
                .csrf(AbstractHttpConfigurer::disable)          // CSRF 비활성화
                .formLogin(FormLoginConfigurer::disable)        // 폼 로그인 비활성화
                .rememberMe(RememberMeConfigurer::disable)      // rememberMe 비활성화
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // 세션을 상태 비저장으로 설정

        // 요청에 대한 권한 설정
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/oauth2/**").permitAll()
                        .anyRequest().authenticated()
                );

        // OAuth2 로그인 설정
        http.oauth2Login( o2 -> o2.authorizationEndpoint(ae -> ae
                        .baseUri("/oauth2/authorize")   // 소셜 로그인 URL 설정
                        .authorizationRequestRepository(cookieAuthorizationRequestRepository))  // 인증 요청을 쿠키에 저장
                .redirectionEndpoint(re -> re.baseUri("/oauth2/callback/*"))   // 소셜 인증 후 리디렉션 URL 설정
                .userInfoEndpoint(ue -> ue.userService(customOAuth2UserService))    // OAuth2User를 반환하는 UserService 설정
                .successHandler(oAuth2AuthenticationSuccessHandler)     // 성공 시 핸들러 설정
                .failureHandler(oAuth2AuthenticationFailureHandler)     // 실패 시 핸들러 설정
        );

        // 로그아웃 설정
        http.logout(logout -> logout
                .clearAuthentication(true)  // 인증 정보를 지우고
                .deleteCookies("JESESSIONID")   // JSESSIONID 쿠키 삭제
        );

        // JWT 필터 설정 - UsernamePasswordAuthenticationFilter 전에 추가
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}