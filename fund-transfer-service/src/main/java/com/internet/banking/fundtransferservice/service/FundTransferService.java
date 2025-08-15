package com.internet.banking.fundtransferservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.internet.banking.fundtransferservice.model.FundTransferEntity;
import com.internet.banking.fundtransferservice.model.FundTransferRequest;
import com.internet.banking.fundtransferservice.model.FundTransferResponse;
import com.internet.banking.fundtransferservice.model.TransactionStatus;
import com.internet.banking.fundtransferservice.model.dto.FundTransferDto;
import com.internet.banking.fundtransferservice.model.mapper.FundTransferMapper;
import com.internet.banking.fundtransferservice.repository.FundTransferRepository;

import java.util.List;
import java.util.UUID;
@Slf4j
@RequiredArgsConstructor
@Service
public class FundTransferService {
    private final FundTransferRepository fundTransferRepository;
    private FundTransferMapper mapper = new FundTransferMapper();
    public FundTransferResponse fundTransfer(FundTransferRequest request) {
        log.info("Sending fund transfer request {}" + request.toString());
        FundTransferEntity entity = new FundTransferEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setStatus(TransactionStatus.PENDING);
        FundTransferEntity optFundTransfer = fundTransferRepository.save(entity);
        FundTransferResponse fundTransferResponse = new FundTransferResponse();
        fundTransferResponse.setTransactionId(UUID.randomUUID().toString());
        fundTransferResponse.setMessage("Success");
        return fundTransferResponse;
    }
    public List<FundTransferDto> readAllTransfers(Pageable pageable) {
        return mapper.convertToDtoList(fundTransferRepository.findAll(pageable).getContent());
    }
}