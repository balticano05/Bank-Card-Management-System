package com.effective.project.bank.card.management.system.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {

    @NotNull(message = "Card number cannot be blank")
    @Pattern(regexp = "^[0-9]{16,19}$", message = "Card number must contain 16 to 19 digits")
    private String cardNumber;

}