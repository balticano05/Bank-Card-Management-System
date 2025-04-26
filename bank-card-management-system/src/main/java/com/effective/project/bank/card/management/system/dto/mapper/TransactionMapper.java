package com.effective.project.bank.card.management.system.dto.mapper;

import com.effective.project.bank.card.management.system.dto.response.TransactionResponse;
import com.effective.project.bank.card.management.system.entity.Transaction;

public class TransactionMapper {

    public static TransactionResponse mapEntityToTransactionResponse(Transaction source) {
        return TransactionResponse.builder()
                .id(source.getId())
                .amount(source.getAmount())
                .fromCard(source.getFromCard().getEncryptedCardNumber())
                .toCard(source.getToCard().getEncryptedCardNumber())
                .createdAt(source.getCreatedAt())
                .build();
    }

}