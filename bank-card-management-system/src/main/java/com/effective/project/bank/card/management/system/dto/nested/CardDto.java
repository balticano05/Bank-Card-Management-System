package com.effective.project.bank.card.management.system.dto.nested;

import com.effective.project.bank.card.management.system.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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