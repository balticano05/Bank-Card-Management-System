package com.effective.project.bank.card.management.system.service;

public interface GammaEncryptionService {

    String encrypt(String cardNumber);

    String decrypt(String encryptedCardNumber);

    String maskCardNumber(String cardNumber);

}