package com.junebay.plancard.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : IAN
 * @date : 2024-11-08
 * @description :
 */
@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    /**
     * 애플리케이션의 CORS 설정
     * - 모든 엔드포인트("/**")에서 CORS 요청을 허용
     * - 클라이언트가 "Set-Cookie" 헤더를 접근할 수 있도록 노출
     * - 허용된 출처를 "http://localhost:3000"으로 제한하여 보안을 강화
     *
     * @param registry CORS 설정을 적용할 레지스트리 객체
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .exposedHeaders("Set-Cookie")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
