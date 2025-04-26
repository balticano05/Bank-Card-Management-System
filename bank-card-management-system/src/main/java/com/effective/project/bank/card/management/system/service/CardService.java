package com.effective.project.bank.card.management.system.service;

import com.effective.project.bank.card.management.system.dto.request.CardBalanceRequest;
import com.effective.project.bank.card.management.system.dto.request.CardRequest;
import com.effective.project.bank.card.management.system.dto.request.TransferRequest;
import com.effective.project.bank.card.management.system.dto.response.CardBalanceResponse;
import com.effective.project.bank.card.management.system.dto.response.CardResponse;
import com.effective.project.bank.card.management.system.dto.response.TransactionResponse;
import com.effective.project.bank.card.management.system.dto.response.UserCardsResponse;
import org.springframework.data.domain.Pageable;

public interface CardService {

    CardResponse createCard(Long userId);

    TransactionResponse transferBetweenCards(Long userId, TransferRequest transferRequest);

    UserCardsResponse findUserCards(Long userId, Pageable pageable);

    CardBalanceResponse findCardBalanceByCardNumber(CardRequest cardRequest);

    CardBalanceResponse replenishmentOfFunds(Long cardId, CardBalanceRequest cardBalanceRequest);

    CardResponse activateCard(Long cardId);

    CardResponse blockCard(Long cardId);

    Long deleteCardById(Long cardId);

}