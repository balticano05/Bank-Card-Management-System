package com.effective.project.bank.card.management.system.repository;

import com.effective.project.bank.card.management.system.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CardRepository extends JpaRepository<Card, Long> {

    Page<Card> findAllByOwnerId(Long ownerId, Pageable pageable);

    Optional<Card> findCardByEncryptedCardNumber(String encryptedCardNumber);

}