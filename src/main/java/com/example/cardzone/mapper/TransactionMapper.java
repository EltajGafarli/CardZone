package com.example.cardzone.mapper;

import com.example.cardzone.dto.TransactionDto;
import com.example.cardzone.entity.Transactions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDto transactionToTransactionDto(Transactions transactions) {
        TransactionDto transactionDto = new TransactionDto();

        BeanUtils.copyProperties(transactions, transactionDto);

        return transactionDto;
    }

    public Transactions transactionDtoToTransaction(TransactionDto transactionDto) {
        return Transactions
                .builder()
                .amount(transactionDto.getAmount())
                .hasCashback(transactionDto.isHasCashback())
                .type(transactionDto.getType())
                .build();
    }
}
