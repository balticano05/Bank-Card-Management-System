package com.effective.project.bank.card.management.system.controller;

import com.effective.project.bank.card.management.system.dto.request.CardRequest;
import com.effective.project.bank.card.management.system.dto.request.TransferRequest;
import com.effective.project.bank.card.management.system.dto.response.CardBalanceResponse;
import com.effective.project.bank.card.management.system.dto.response.CardResponse;
import com.effective.project.bank.card.management.system.dto.response.TransactionResponse;
import com.effective.project.bank.card.management.system.dto.response.UserCardsResponse;
import com.effective.project.bank.card.management.system.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserCardController {

    private final CardService cardService;

    @PostMapping("/{userId}/cards")
    public CardResponse createCard(@PathVariable Long userId) {
        return cardService.createCard(userId);
    }

    @GetMapping("/{userId}/cards")
//    PreAth + ADMIN
    public UserCardsResponse findUserCards(
            @RequestParam Long userID,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return cardService.findUserCards(userID, pageable);
    }

    @PostMapping("/{userId}/cards/{cardId}/block")
    //    ADMIN
    public CardResponse activateCard(Long cardId){
        return cardService.activateCard(cardId);
    }

    @PostMapping("/{userId}/cards/{cardId}/activate")
    //    PreAth + ADMIN
    public CardResponse blockCard(Long cardId){
        return cardService.blockCard(cardId);
    }

    @PostMapping("/{userId}/cards/transfer")
    public TransactionResponse transferBetweenCards(
            @PathVariable Long userId,
            @RequestBody TransferRequest transferRequest){
        return cardService.transferBetweenCards(userId, transferRequest);
    }

    @GetMapping("/{userId}/cards/{cardId}")
    public CardBalanceResponse findCardBalanceByCardNumber(@RequestBody CardRequest cardRequest){
        return cardService.findCardBalanceByCardNumber(cardRequest);
    }

    @DeleteMapping("/{userId}/cards/{cardId}")
    public Long deleteCardById(Long cardId){
        return cardService.deleteCardById(cardId);
    }

}