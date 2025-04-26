package com.effective.project.bank.card.management.system.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardBalanceRequest {

    @NotNull(message = "Balance cannot be null")
    @PositiveOrZero(message = "Balance must be positive or zero")
    private Long balance;

}