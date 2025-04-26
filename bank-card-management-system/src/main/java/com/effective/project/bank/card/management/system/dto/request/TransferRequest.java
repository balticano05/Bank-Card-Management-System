package com.effective.project.bank.card.management.system.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    @NotNull(message = "Source card ID cannot be null")
    @Positive(message = "Source card ID must be positive")
    private Long fromCardId;

    @NotNull(message = "Target card ID cannot be null")
    @Positive(message = "Target card ID must be positive")
    private Long toCardId;


    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private Long amount;

}