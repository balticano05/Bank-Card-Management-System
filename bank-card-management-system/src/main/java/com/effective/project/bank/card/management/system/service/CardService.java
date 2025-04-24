package com.effective.project.bank.card.management.system.service;

import com.effective.project.bank.card.management.system.dto.request.CardRequest;
import com.effective.project.bank.card.management.system.dto.request.TransferRequest;
import com.effective.project.bank.card.management.system.dto.response.CardBalanceResponse;
import com.effective.project.bank.card.management.system.dto.response.CardResponse;
import com.effective.project.bank.card.management.system.dto.response.TransactionResponse;
import com.effective.project.bank.card.management.system.dto.response.UserCardsResponse;
import org.springframework.data.domain.Pageable;

public interface CardService {

    UserCardsResponse findUserCards(Long userId, Pageable pageable);

    CardResponse blockCard(Long cardId);

    TransactionResponse transferBetweenCards(Long userId, TransferRequest transferRequest);

    CardBalanceResponse findCardBalanceByCardNumber(CardRequest cardRequest);

}