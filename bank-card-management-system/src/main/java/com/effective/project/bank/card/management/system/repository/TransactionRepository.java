package com.effective.project.bank.card.management.system.repository;

import com.effective.project.bank.card.management.system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByFromCard_OwnerIdOrToCard_OwnerId(Long fromUserId, Long toUserId);

}