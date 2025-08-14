package com.internet.banking.bankingservice.mapper;

import org.springframework.beans.BeanUtils;

import com.internet.banking.bankingservice.dto.UtilityAccountDto;
import com.internet.banking.bankingservice.model.UtilityAccountEntity;

public class UtilityAccountMapper extends BaseMapper<UtilityAccountEntity, UtilityAccountDto> {
    @Override
    public UtilityAccountEntity convertToEntity(UtilityAccountDto dto, Object... args) {
        UtilityAccountEntity entity = new UtilityAccountEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }

    @Override
    public UtilityAccountDto convertToDto(UtilityAccountEntity entity, Object... args) {
        UtilityAccountDto dto = new UtilityAccountDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}
