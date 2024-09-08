package com.junebay.plancard.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private Long id;
    private ProviderInfo providerInfo;
    private String identifier;
    private Role role;

}
