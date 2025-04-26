package com.effective.project.bank.card.management.system.dto.mapper;

import com.effective.project.bank.card.management.system.dto.nested.CardDto;
import com.effective.project.bank.card.management.system.dto.response.CardBalanceResponse;
import com.effective.project.bank.card.management.system.dto.response.CardResponse;
import com.effective.project.bank.card.management.system.dto.response.UserCardsResponse;
import com.effective.project.bank.card.management.system.entity.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {

    public static CardBalanceResponse mapEntityToCardBalanceResponse(Card source) {
        return CardBalanceResponse.builder()
                .encryptedCardNumber(source.getEncryptedCardNumber())
                .amount(source.getBalance())
                .build();
    }

    public static CardResponse mapEntityToCardResponse(Card source) {
        return CardResponse.builder()
                .card(mapEntityToCardDto(source))
                .build();
    }

    public static UserCardsResponse mapEntitiesToUserCardsResponse(List<Card> source) {
        return UserCardsResponse.builder()
                .cards(mapEntitiesToCardDtoList(source))
                .build();
    }

    public static List<CardDto> mapEntitiesToCardDtoList(List<Card> source) {

        if (source == null) return new ArrayList<>();

        return source.stream()
                .map(CardMapper::mapEntityToCardDto)
                .collect(Collectors.toList());
    }

    public static CardDto mapEntityToCardDto(Card source) {
        return CardDto.builder()
                .id(source.getId())
                .encryptedCardNumber(source.getEncryptedCardNumber())
                .expiryDate(source.getExpiryDate())
                .status(source.getStatus())
                .balance(source.getBalance())
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .build();
    }

}