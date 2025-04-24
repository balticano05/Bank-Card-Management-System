package com.effective.project.bank.card.management.system.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    private Long fromCardId;

    private Long toCardId;

    private Long amount;

}