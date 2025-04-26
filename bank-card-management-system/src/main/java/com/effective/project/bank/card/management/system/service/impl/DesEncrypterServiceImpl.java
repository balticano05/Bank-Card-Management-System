package com.effective.project.bank.card.management.system.service.impl;

import com.effective.project.bank.card.management.system.service.DesEncrypterService;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DesEncrypterServiceImpl implements DesEncrypterService {

    private Cipher ecipher;
    private Cipher decipher;

    public DesEncrypterServiceImpl(SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        ecipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        decipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        ecipher.init(Cipher.ENCRYPT_MODE, secretKey);
        decipher.init(Cipher.DECRYPT_MODE, secretKey);

    }

    @Override
    public String encrypt(String str) {

        str = str.trim();

        byte[] utf8;
        byte[] enc;

        try {

            utf8 = str.getBytes("UTF8");
            enc = ecipher.doFinal(utf8);

        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return Base64.getEncoder().encodeToString(enc);
    }

    @Override
    public String decrypt(String str) {

        byte[] dec;
        byte[] utf8;
        String result;

        try {

            dec = Base64.getDecoder().decode(str);
            utf8 = decipher.doFinal(dec);

            result = new String(utf8, "UTF8");

        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
