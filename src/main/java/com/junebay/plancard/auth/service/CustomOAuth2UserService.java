package com.junebay.plancard.auth.service;

import com.junebay.plancard.auth.dto.GoogleResponse;
import com.junebay.plancard.auth.dto.NaverResponse;
import com.junebay.plancard.auth.dto.OAuth2Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * @author : IAN
 * @date : 2024-11-07
 * @description :
 */
@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("OAuth2 User : {}", oAuth2User);
        log.info("OAuth2 User Request : {}", userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        return null;
    }

    /**
     Naver Response Data
     {
        resultcode=00, message=success, response={id=123123123, name=김개똥}
     }

     Google Response Data
     {
        resultcode=00, message=success, id=123123123, name=개발자유미
     }
     */
}
