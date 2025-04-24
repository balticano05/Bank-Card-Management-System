package com.effective.project.bank.card.management.system.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardBalanceResponse {

    private String encryptedCardNumber;

    private Long amount;

}