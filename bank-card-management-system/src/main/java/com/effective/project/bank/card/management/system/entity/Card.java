package com.effective.project.bank.card.management.system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cards")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "encrypted_card_number", nullable = false)
    private String encryptedCardNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Long balance = 0L;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "fromCard", fetch = FetchType.LAZY)
    private List<Transaction> sentTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "toCard", fetch = FetchType.LAZY)
    private List<Transaction> receivedTransactions = new ArrayList<>();

    public void addSentTransaction(Transaction transaction) {
        sentTransactions.add(transaction);
        transaction.setFromCard(this);
    }

    public void addReceivedTransaction(Transaction transaction) {
        receivedTransactions.add(transaction);
        transaction.setToCard(this);
    }

}