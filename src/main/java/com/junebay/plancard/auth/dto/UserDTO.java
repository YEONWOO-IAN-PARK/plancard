package com.junebay.plancard.auth.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Select;

/**
 * @author : IAN
 * @date : 2024-11-08
 * @description :
 */
@Getter
@Setter
public class UserDTO {

    private String name;
    private String email;
    private String provider;
    private String providerId;
    private String role;
}
