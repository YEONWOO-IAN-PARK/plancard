package com.junebay.plancard.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * @author : IAN
 * @date : 2024-09-09
 * @description :
 */
@Mapper
public class UserMapper {


    public Optional<User> findByOAuthInfo(String userIdentifier, ProviderInfo providerInfo) {

        return null;
    }

    public User save(User unregisteredUser) {
        return null;
    }

    public Optional<User> findByIdentifier(String identifier) {
        return null;
    }
}
