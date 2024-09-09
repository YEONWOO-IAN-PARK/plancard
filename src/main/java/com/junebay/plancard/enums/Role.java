package com.junebay.plancard.enums;

import lombok.Getter;

/**
 * @author : IAN
 * @date : 2024-09-09
 * @description :
 */
@Getter
public enum Role {

    ROLE_ADMIN("운영자"), ROLE_USER("사용자");

    private String description;

    Role(String description) {
        this.description = description;
    }

}
