package com.effective.project.bank.card.management.system.controller;

import com.effective.project.bank.card.management.system.dto.request.CardBalanceRequest;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserCardController {

    private final CardService cardService;

    @PostMapping("/{userId}/cards/create")
    @PreAuthorize("hasRole('ADMIN')")
    public CardResponse createCard(@PathVariable Long userId) {
        return cardService.createCard(userId);
    }

    @PutMapping("/{userId}/cards/{cardId}/replenishment")
    @PreAuthorize("hasRole('ADMIN') or @customSecurityService.isOwner(authentication, #userId)")
    public CardBalanceResponse replenishmentOfFunds(
            @PathVariable Long userId,
            @PathVariable Long cardId,
            @RequestBody CardBalanceRequest cardBalanceRequest) {
        return cardService.replenishmentOfFunds(cardId, cardBalanceRequest);
    }

    @GetMapping("/{userId}/cards")
    @PreAuthorize("hasRole('ADMIN') or @customSecurityService.isOwner(authentication, #userId)")
    public UserCardsResponse findUserCards(
            @PathVariable Long userId,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return cardService.findUserCards(userId, pageable);
    }

    @PatchMapping("/{userId}/cards/{cardId}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public CardResponse activateCard(@PathVariable Long cardId) {
        return cardService.activateCard(cardId);
    }

    @PatchMapping("/{userId}/cards/{cardId}/block")
    @PreAuthorize("hasRole('ADMIN') or @customSecurityService.isOwner(authentication, #userId)")
    public CardResponse blockCard(
            @PathVariable Long userId,
            @PathVariable Long cardId) {
        return cardService.blockCard(cardId);
    }

    @PostMapping("/{userId}/cards/transfer")
    @PreAuthorize("@customSecurityService.isOwner(authentication, #userId)")
    public TransactionResponse transferBetweenCards(
            @PathVariable Long userId,
            @RequestBody TransferRequest transferRequest) {
        return cardService.transferBetweenCards(userId, transferRequest);
    }

    @PostMapping("/{userId}/cards/{cardId}")
    @PreAuthorize("@customSecurityService.isOwner(authentication, #userId)")
    public CardBalanceResponse findCardBalanceByCardNumber(
            @PathVariable Long userId,
            @RequestBody CardRequest cardRequest) {
        return cardService.findCardBalanceByCardNumber(cardRequest);
    }

    @DeleteMapping("/{userId}/cards/{cardId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Long deleteCardById(@PathVariable Long cardId) {
        return cardService.deleteCardById(cardId);
    }

}