package com.junebay.plancard.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
public class CustomCorsConfigurationSource implements CorsConfigurationSource {

    // 허용된 Origin 값을 저장한다. 생성자에서 주입된 값으로 초기화된다.
    private final String ALLOWED_ORIGIN;

    // 허용된 HTTP 메서드 리스트를 static final 로 선언하여 클래스 레벨 상수로 사용한다.
    // POST, GET, PATCH, UPDATE, OPTIONS, DELETE 메서드만 허용한다.
    @Value("${cors.allowed.methods}")
    private List<String> ALLOWED_METHODS;

    // 모든 헤더를 허용하도록 설정하기 위한 상수.
    @Value("${cors.allowed.headers}")
    private String ALLOWED_HEADERS;

    // CORS preflight 요청의 캐시 시간을 설정하는 상수로 3600초 = 1시간을 의미한다.
    @Value("${cors.allowed.maxAge}")
    private long MAX_AGE;

    // application.properties 에 정의된 base URL 값을 @Value 로 주입받아 ALLOWED_ORIGIN 에 할당한다.
    public CustomCorsConfigurationSource(@Value("${url.base}") String baseUrl) {
        this.ALLOWED_ORIGIN = baseUrl;
    }

    // CorsConfiguration 을 반환하는 메서드. 요청마다 CORS 설정을 적용하기 위해 호출된다.
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        return createCorsConfiguration(); // CORS 설정을 반환하는 별도의 메서드로 위임한다.
    }

    // CORS 설정을 초기화하고 반환하는 메서드. CORS 규칙을 정의한다.
    private CorsConfiguration createCorsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();

        // 주입받은 ALLOWED_ORIGIN 값을 CORS 허용 Origin으로 설정한다.
        config.setAllowedOrigins(Collections.singletonList(ALLOWED_ORIGIN));

        // ALLOWED_METHODS 리스트에 정의된 HTTP 메서드들만 허용하도록 설정한다.
        config.setAllowedMethods(ALLOWED_METHODS);

        // 자격 증명 정보를 허용하도록 설정한다. (예: 쿠키, 인증 정보)
        config.setAllowCredentials(true);

        // 모든 헤더를 허용하도록 설정한다.
        config.setAllowedHeaders(Collections.singletonList(ALLOWED_HEADERS));

        // CORS preflight 요청의 캐시 시간을 설정한다.
        config.setMaxAge(MAX_AGE);

        // 최종적으로 설정된 CorsConfiguration 객체를 반환한다.
        return config;
    }
}
