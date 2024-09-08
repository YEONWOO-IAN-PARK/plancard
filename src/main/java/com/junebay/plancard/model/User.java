package com.junebay.plancard.model;

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
