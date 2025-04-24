package com.effective.project.bank.card.management.system.service.impl;

import com.effective.project.bank.card.management.system.dto.mapper.CardMapper;
import com.effective.project.bank.card.management.system.dto.mapper.TransactionMapper;
import com.effective.project.bank.card.management.system.dto.request.CardRequest;
import com.effective.project.bank.card.management.system.dto.request.TransferRequest;
import com.effective.project.bank.card.management.system.dto.response.CardBalanceResponse;
import com.effective.project.bank.card.management.system.dto.response.CardResponse;
import com.effective.project.bank.card.management.system.dto.response.TransactionResponse;
import com.effective.project.bank.card.management.system.dto.response.UserCardsResponse;
import com.effective.project.bank.card.management.system.entity.Card;
import com.effective.project.bank.card.management.system.entity.Transaction;
import com.effective.project.bank.card.management.system.exception.type.*;
import com.effective.project.bank.card.management.system.repository.CardRepository;
import com.effective.project.bank.card.management.system.repository.TransactionRepository;
import com.effective.project.bank.card.management.system.repository.UserRepository;
import com.effective.project.bank.card.management.system.service.CardService;
import com.effective.project.bank.card.management.system.service.GammaEncryptionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;

    private final GammaEncryptionService gammaEncryptionService;

    @Override
    public UserCardsResponse findUserCards(Long ownerId, Pageable pageable) {

        Page<Card> cardsPage = cardRepository.findAllByOwnerId(ownerId, pageable);

        cardsPage.forEach(card -> {
            card.setEncryptedCardNumber(gammaEncryptionService.maskCardNumber(gammaEncryptionService.decrypt(card.getEncryptedCardNumber())));
        });

        return CardMapper.mapEntitiesToUserCardsResponse(cardsPage.getContent());
    }

    @Override
    @Transactional
    public CardResponse blockCard(Long cardId) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found."));

        if(card.getStatus().equals("BLOCKED")) {
            throw new CardAlreadyBlockedException("Card already blocked");
        }

        card.setStatus("BLOCKED");

        return CardMapper.mapEntityToCardResponse(card);
    }

    @Override
    @Transactional
    public TransactionResponse transferBetweenCards(Long userId, TransferRequest transferRequest) {

        if (transferRequest.getAmount() <= 0) {
            throw new AmountMustBePositiveException("Amount must be greater than 0.");
        }

        Card fromCard = cardRepository.findById(transferRequest.getFromCardId())
                .orElseThrow(() -> new EntityNotFoundException("From-card not found with such id: " + transferRequest.getFromCardId()));

        Card toCard = cardRepository.findById(transferRequest.getToCardId())
                .orElseThrow(() -> new EntityNotFoundException("To-card not found with such id: " + transferRequest.getToCardId()));

        if (!fromCard.getOwner().getId().equals(userId)) {
            throw new CardsDoNotBelongToUserException("From-card does not belong to user");
        }
        if (!toCard.getOwner().getId().equals(userId)) {
            throw new CardsDoNotBelongToUserException("To-card does not belong to user");
        }

        if (fromCard.getId().equals(toCard.getId())) {
            throw new TransferCardException("Cannot transfer to the same card");
        }

        if ("BLOCKED".equals(fromCard.getStatus())) {
            throw new CardIsBlockedException("From-card is blocked");
        }
        if ("BLOCKED".equals(toCard.getStatus())) {
            throw new CardIsBlockedException("To-card is blocked");
        }

        if (fromCard.getBalance() < transferRequest.getAmount()) {
            throw new InsufficientFundsException("Insufficient funds on from-card");
        }

        fromCard.setBalance(fromCard.getBalance() - transferRequest.getAmount());
        toCard.setBalance(toCard.getBalance() + transferRequest.getAmount());

        Transaction transaction = Transaction.builder()
                .fromCard(fromCard)
                .toCard(toCard)
                .amount(transferRequest.getAmount())
                .build();
        transactionRepository.save(transaction);

        TransactionResponse transactionResponse = TransactionMapper.mapEntityToTransactionResponse(transaction);

        transactionResponse.setFromCardId(gammaEncryptionService.maskCardNumber(gammaEncryptionService.decrypt(transaction.getToCard().getEncryptedCardNumber())));
        transactionResponse.setToCardId(gammaEncryptionService.maskCardNumber(gammaEncryptionService.decrypt(transaction.getToCard().getEncryptedCardNumber())));

        return TransactionMapper.mapEntityToTransactionResponse(transaction);
    }

    @Override
    public CardBalanceResponse findCardBalanceByCardNumber(CardRequest cardRequest) {

        Card card = cardRepository.findCardByEncryptedCardNumber(gammaEncryptionService.encrypt((cardRequest.getCardNumber())))
                .orElseThrow(() -> new EntityNotFoundException("Car not found with number: " + cardRequest.getCardNumber()));

        CardBalanceResponse cardBalanceResponse = CardMapper.mapEntityToCardBalanceResponse(card);
        cardBalanceResponse.setEncryptedCardNumber(gammaEncryptionService.maskCardNumber(gammaEncryptionService.decrypt(card.getEncryptedCardNumber())));

        return cardBalanceResponse;
    }

}