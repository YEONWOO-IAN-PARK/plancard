package com.junebay.plancard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author : IAN
 * @date : 2024-09-09
 * @description :
 */
public class UserResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TokenInfo {
        private String grantType;
        private String accessToken;
        private long accessTokenExpirationTime;
        private String refreshToken;
        private long refreshTokenExpirationTime;
    }
}
