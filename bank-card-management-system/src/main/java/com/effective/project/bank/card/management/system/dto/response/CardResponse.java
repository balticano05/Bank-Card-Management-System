package com.effective.project.bank.card.management.system.dto.response;

import com.effective.project.bank.card.management.system.dto.nested.CardDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {

    private CardDto card;

}