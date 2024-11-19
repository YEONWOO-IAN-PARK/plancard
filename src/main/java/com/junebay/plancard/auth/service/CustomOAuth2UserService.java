package com.junebay.plancard.auth.service;

import com.junebay.plancard.auth.dto.*;
import com.junebay.plancard.auth.entity.UserEntity;
import com.junebay.plancard.auth.mapper.UserMapper;
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

    private final UserMapper userMapper;

    public CustomOAuth2UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

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
        else if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        UserEntity existData = userMapper.findByProviderId(oAuth2Response.getProviderId());

        if (existData == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setProvider(oAuth2Response.getProvider());
            userEntity.setProviderId(oAuth2Response.getProviderId());
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setName(oAuth2Response.getName());
            userEntity.setRole("ROLE_USER");

            userMapper.saveUser(userEntity);

            UserDTO userDTO = new UserDTO();
            userDTO.setProvider(oAuth2Response.getProvider());
            userDTO.setProviderId(oAuth2Response.getProviderId());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setEmail(oAuth2Response.getEmail());
            userDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(userDTO);
        }
        else {
            // 기존 사용자가 존재할 경우 새로 로그인한 정보를 업데이트한다. (사용자가 개인정보를 업데이트 했을 경우)
            existData.setProvider(oAuth2Response.getProvider());
            existData.setProviderId(oAuth2Response.getProviderId());
            existData.setName(oAuth2Response.getName());

            userMapper.updateUser(existData);

            UserDTO userDTO = new UserDTO();
            userDTO.setProvider(oAuth2Response.getProvider());
            userDTO.setProviderId(oAuth2Response.getProviderId());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());

            return new CustomOAuth2User(userDTO);
        }
    }

    /**
     //Naver Response Data
     {
        resultcode=00, message="success", response={id="123123123", email="abc@naver.com", mobile=..., name="김개똥", }
     }

     //Google Response Data
     {
        sub="123123123", name="IAN PARK", given_name="IAN", family_name="PARK", email="abc@gmail.com", ...
     }


     //Kakao Response Data
     {
        id="123213", connected_at="2024-11-18T10:47:55Z", properties={key="properties", value={nickname="박연우", profile_image="http://img1.....", thumbnail_image="http://..."}},
        kakao_account={key="kakao_account", value={..., ..., profile={key="profile",value={..., ..., nickname="박연우"}}, ..., ..., email="abc@gmail.com"}}
     }
     */
}
