package com.hayan.fintech.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final int AES_KEY_SIZE = 16;

    public String encrypt(String value, String key) throws Exception {
        SecretKey secretKey = getKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedValue = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public String decrypt(String value, String key) throws Exception {
        SecretKey secretKey = getKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedValue = Base64.getDecoder().decode(value);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue);
    }

    private SecretKey getKey(String key) {
        byte[] keyBytes = key.getBytes();
        byte[] adjustedKeyBytes = new byte[AES_KEY_SIZE];

        System.arraycopy(keyBytes, 0, adjustedKeyBytes, 0, Math.min(keyBytes.length, AES_KEY_SIZE));

        return new SecretKeySpec(adjustedKeyBytes, ALGORITHM);
    }
}