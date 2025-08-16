package com.internet.banking.utilityservice.model.mapper;

import org.springframework.beans.BeanUtils;

import com.internet.banking.utilityservice.model.UtilityPaymentEntity;
import com.internet.banking.utilityservice.model.dto.UtilityPaymentDto;

public class UtilityPaymentMapper extends BaseMapper<UtilityPaymentEntity, UtilityPaymentDto> {
    @Override
    public UtilityPaymentEntity convertToEntity(UtilityPaymentDto dto, Object... args) {
        UtilityPaymentEntity entity = new UtilityPaymentEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }

    @Override
    public UtilityPaymentDto convertToDto(UtilityPaymentEntity entity, Object... args) {
        UtilityPaymentDto dto = new UtilityPaymentDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}
