package com.junebay.plancard.auth.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2024-11-08
 * @description :
 */

@Getter
@Setter
public class UserEntity {

    private Long id;
    private String name;
    private String email;
    private String provider;
    private String providerId;
    private String role;
}
