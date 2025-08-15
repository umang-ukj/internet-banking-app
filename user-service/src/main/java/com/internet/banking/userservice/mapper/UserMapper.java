package com.internet.banking.userservice.mapper;

import org.springframework.beans.BeanUtils;

import com.internet.banking.userservice.dto.UserDto;
import com.internet.banking.userservice.model.UserEntity;

public class UserMapper extends BaseMapper<UserEntity, UserDto>{
    @Override
    public UserEntity convertToEntity(UserDto dto, Object... args) {
        UserEntity userEntity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, userEntity);
        }
        return userEntity;
    }

    @Override
    public UserDto convertToDto(UserEntity entity, Object... args) {
        UserDto user = new UserDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, user);
        }
        return user;
    }
}
