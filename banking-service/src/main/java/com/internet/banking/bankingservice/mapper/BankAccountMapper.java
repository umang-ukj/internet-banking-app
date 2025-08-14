package com.internet.banking.bankingservice.mapper;

import org.springframework.beans.BeanUtils;

import com.internet.banking.bankingservice.dto.BankAccountDto;
import com.internet.banking.bankingservice.model.BankAccountEntity;

public class BankAccountMapper extends BaseMapper<BankAccountEntity, BankAccountDto> {

    @Override
    public BankAccountEntity convertToEntity(BankAccountDto dto, Object... args) {
        BankAccountEntity entity = new BankAccountEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity, "user");
        }
        return entity;
    }

    @Override
    public BankAccountDto convertToDto(BankAccountEntity entity, Object... args) {
        BankAccountDto dto = new BankAccountDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto, "user");
        }
        return dto;
    }
}
