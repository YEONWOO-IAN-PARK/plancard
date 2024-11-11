package com.junebay.plancard.auth.mapper;

import com.junebay.plancard.auth.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : IAN
 * @date : 2024-11-08
 * @description :
 */
@Mapper
public interface UserMapper {

    UserEntity findByProviderId(String providerId);

    void saveUser(UserEntity userEntity);

    void updateUser(UserEntity existData);
}
