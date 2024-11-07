package com.junebay.plancard.auth.dto;

/**
 * @author : IAN
 * @date : 2024-11-07
 * @description :
 */
public interface OAuth2Response {

    /**
     * 제공자 (ex. naver, google, ...)
     * @return
     */
    String getProvider();

    /**
     * 제공자에서 발급해주는 아이디(번호)
     * @return
     */
    String getProviderId();

    /**
     * 이메일
     * @return
     */
    String getEmail();

    /**
     * 사용자 실명 (설정한 이름)
     * @return
     */
    String getName();
}
