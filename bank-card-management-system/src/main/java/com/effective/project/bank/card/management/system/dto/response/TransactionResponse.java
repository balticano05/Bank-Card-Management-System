package com.effective.project.bank.card.management.system.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private Long id;

    private String fromCardId;

    private String toCardId;

    private Long amount;

    private LocalDateTime createdAt;

}