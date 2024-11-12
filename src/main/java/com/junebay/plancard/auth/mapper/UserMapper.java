package com.junebay.plancard.auth.mapper;

import com.junebay.plancard.auth.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : IAN
 * @date : 2024-11-08
 * @description :
 */
@Mapper
public interface UserMapper {

    UserEntity findByProviderId(@Param("providerId") String providerId);

    void saveUser(UserEntity userEntity);

    void updateUser(UserEntity userEntity);
}
