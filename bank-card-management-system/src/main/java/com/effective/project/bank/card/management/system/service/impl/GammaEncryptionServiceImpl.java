package com.effective.project.bank.card.management.system.service.impl;

import com.effective.project.bank.card.management.system.service.GammaEncryptionService;
import org.springframework.stereotype.Service;

@Service
public class GammaEncryptionServiceImpl implements GammaEncryptionService {

    private int encryptionKey;

    public GammaEncryptionServiceImpl() {
        this.encryptionKey = Integer.decode("0x7e");
    }

    public String encrypt(String cardNumber) {

        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < cardNumber.length(); i++) {

            int charCode = cardNumber.charAt(i);
            int encryptedChar = charCode ^ encryptionKey;

            encrypted.append(String.format("%02x", encryptedChar));
        }

        return encrypted.toString();
    }

    public String decrypt(String encryptedCardNumber) {

        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < encryptedCardNumber.length(); i += 2) {

            String hexPair = encryptedCardNumber.substring(i, i + 2);

            int encryptedByte = Integer.parseInt(hexPair, 16);
            int decryptedChar = encryptedByte ^ encryptionKey;

            decrypted.append((char) decryptedChar);
        }

        return decrypted.toString();
    }

    public String maskCardNumber(String cardNumber) {
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

}