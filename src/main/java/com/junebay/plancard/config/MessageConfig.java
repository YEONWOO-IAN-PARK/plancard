package com.junebay.plancard.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author : IAN
 * @date : 2025-04-21
 * @description :
 */
@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");      // messages.properties 파일을 로드
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
