package com.junebay.plancard.auth.dto;

import java.util.Map;

/**
 * @author : IAN
 * @date : 2024-11-18
 * @description :
 */
public class KakaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute;
    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return this.attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) this.attribute.get("kakao_account");
        return kakaoAccount.get("email").toString();
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map<String, Object>) this.attribute.get("properties");
        return properties.get("nickname").toString();
    }
}
