package com.internet.banking.bankingservice.mapper;

import org.springframework.beans.BeanUtils;

import com.internet.banking.bankingservice.dto.UserDto;
import com.internet.banking.bankingservice.model.UserEntity;

public class UserMapper extends BaseMapper<UserEntity, UserDto> {
    private BankAccountMapper bankAccountMapper = new BankAccountMapper();

    @Override
    public UserEntity convertToEntity(UserDto dto, Object... args) {
        UserEntity entity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity, "accounts");
            entity.setAccounts(bankAccountMapper.convertToEntityList(dto.getBankAccounts()));
        }
        return entity;
    }

    @Override
    public UserDto convertToDto(UserEntity entity, Object... args) {
        UserDto dto = new UserDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto, "accounts");
            dto.setBankAccounts(bankAccountMapper.convertToDtoList(entity.getAccounts()));
        }
        return dto;
    }
}
