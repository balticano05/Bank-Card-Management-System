package com.effective.project.bank.card.management.system.dto.response;

import com.effective.project.bank.card.management.system.dto.nested.CardDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCardsResponse {

    private List<CardDto> cards;

}