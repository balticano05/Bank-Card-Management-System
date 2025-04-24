package com.effective.project.bank.card.management.system.service;

import com.effective.project.bank.card.management.system.dto.request.TransferRequest;
import com.effective.project.bank.card.management.system.dto.response.TransactionResponse;

public interface TransactionService {

    TransactionResponse transferBetweenCards(Long userId, TransferRequest transferRequest);

}