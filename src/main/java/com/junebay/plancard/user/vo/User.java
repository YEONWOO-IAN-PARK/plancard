package com.junebay.plancard.user.vo;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-07
 * @description :
 */
public class User {

    private long id;
    private String email;
    private String password;
    private String provider;
    private String providerId;
    private String username;
    private String profileImage;
    private String role;
    private String isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
