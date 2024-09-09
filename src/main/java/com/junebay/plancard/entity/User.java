package com.junebay.plancard.entity;

import com.junebay.plancard.enums.AuthProvider;
import com.junebay.plancard.enums.Role;
import lombok.*;

import java.time.LocalDateTime;


/**
 * @author : IAN
 * @date : 2024-09-09
 * @description :
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseDataEntity {

    private long id;
    private String email;
    private String name;
    private String oauth2Id;
    private AuthProvider authProvider;
    private Role role;

    public User update(OAuth2UserInfo oAuth2UserInfo) {
        this.name = oAuth2UserInfo.getName();
        this.oauth2Id = oAuth2UserInfo.getOAuth2Id();

        return this;
    }

}
