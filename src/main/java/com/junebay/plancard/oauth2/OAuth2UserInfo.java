package com.junebay.plancard.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * @author : IAN
 * @date : 2024-09-09
 * @description :
 */
@Getter
@AllArgsConstructor
public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;

    public abstract String getOAuthId();
    public abstract String getEmail();
    public abstract String getName();
}
