package com.junebay.plancard.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

import javax.management.MXBean;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Order(1)
public class SecurityConfig {

    private static final String PERMITTED_ROLES[] = {"USER", "ADMIN"};

    private final CustomCorsConfigurationSource corsConfigSource;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final UserService userService;
    private final OAuth2SuccessHandler successHandler;
    private final OAuth2FailureHandler failureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(c -> c.configurationSource(corsConfigSource))
                .csrf(CsrfConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                // OAuth2 사용으로 인해 기본 로그인 비활성화
                .formLogin(FormLoginConfigurer::disable)
                .authorizeHttpRequests(r -> r.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                        .anyRequest().hasAnyRole(PERMITTED_ROLES))
                // OAuth2 로그인 설정
                .oauth2Login(c -> c.successHandler(successHandler)
                        .failureHandler(failureHandler)
                        .userInfoEndpoint(epConfig -> epConfig.userService(customOAuth2UserService))
                );

        return http.build();
    }
}
