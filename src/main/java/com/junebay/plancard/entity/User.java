package com.junebay.plancard.entity;

import com.junebay.plancard.enums.AuthProvider;
import com.junebay.plancard.enums.Role;
import com.junebay.plancard.oauth2.OAuth2UserInfo;
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

}
