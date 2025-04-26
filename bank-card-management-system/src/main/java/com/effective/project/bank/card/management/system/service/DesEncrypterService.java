package com.effective.project.bank.card.management.system.service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;

public interface DesEncrypterService {

    String encrypt(String str) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException;

    String decrypt(String str) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException;

}