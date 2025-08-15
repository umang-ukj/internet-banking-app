package com.internet.banking.fundtransferservice.model.mapper;

import org.springframework.beans.BeanUtils;

import com.internet.banking.fundtransferservice.model.FundTransferEntity;
import com.internet.banking.fundtransferservice.model.dto.FundTransferDto;
public class FundTransferMapper extends BaseMapper<FundTransferEntity, FundTransferDto> {
    @Override
    public FundTransferEntity convertToEntity(FundTransferDto dto, Object... args) {
        FundTransferEntity entity = new FundTransferEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }
    @Override
    public FundTransferDto convertToDto(FundTransferEntity entity, Object... args) {
        FundTransferDto dto = new FundTransferDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}