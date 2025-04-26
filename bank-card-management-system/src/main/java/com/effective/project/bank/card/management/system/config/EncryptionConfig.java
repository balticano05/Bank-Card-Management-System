package com.effective.project.bank.card.management.system.config;

import com.effective.project.bank.card.management.system.service.impl.DesEncrypterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Configuration
public class EncryptionConfig {

    @Bean
    public SecretKey desSecretKey() throws NoSuchAlgorithmException {

        String password = "my-secure-password";
        byte[] keyBytes;
        DESKeySpec keySpec;
        SecretKeyFactory keyFactory;
        SecretKey secretKey;

        try {
            keyBytes = password.getBytes(StandardCharsets.UTF_8);
            keySpec = new DESKeySpec(keyBytes);
            keyFactory = SecretKeyFactory.getInstance("DES");
            secretKey = keyFactory.generateSecret(keySpec);
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        return secretKey;
    }

    @Bean
    public DesEncrypterServiceImpl desEncrypterService(SecretKey desSecretKey) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException {
        return new DesEncrypterServiceImpl(desSecretKey);
    }

}
