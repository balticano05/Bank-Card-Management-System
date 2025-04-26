package com.effective.project.bank.card.management.system.dto.nested;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;

    private String encryptedCardNumber;

    private LocalDate expiryDate;

    private String status;

    private Long balance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}