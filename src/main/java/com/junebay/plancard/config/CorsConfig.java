package com.junebay.plancard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : IAN
 * @date : 2024-09-09
 * @description : Spring MVC에서 CORS 설정을 관리한다.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${cors.mappingPathPattern}")
    private String MAPPING_PATH_PATTERN;
    @Value("${cors.allowed.origins}")
    private String ALLOWED_ORIGINS;
    @Value("${cors.allowed.methods}")
    private String[] ALLOWED_METHODS;   // GET,POST,UPDATE,PATCH,DELETE,OPTIONS
    @Value("${cors.allowed.headers}")
    private String ALLOWED_HEADERS;
    @Value("${cors.allowed.maxAge}")
    private long MAX_AGE;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(MAPPING_PATH_PATTERN)   // URL 패턴을 지정한다.
                .allowedOrigins(ALLOWED_ORIGINS)    // 허용할 출처(Origin)를 설정한다.
                .allowedMethods(ALLOWED_METHODS)    // 허용할 HTTP 메서드 목록을 설정한다.
                .allowedHeaders(ALLOWED_HEADERS)    // 허용할 요청 헤더 목록을 설정한다.
                .allowCredentials(true)             // 자격 증명(쿠키, 인증 정보)을 허용할지 설정한다.
                .maxAge(MAX_AGE);                   // 사전 요청(preflight) 응답의 캐시 시간을 설정한다.
    }
}
