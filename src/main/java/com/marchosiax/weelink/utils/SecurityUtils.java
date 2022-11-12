package com.marchosiax.weelink.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);

    public static String hashSHA256(@NonNull String input) throws NoSuchAlgorithmException {
        var digest = MessageDigest.getInstance("SHA-256");
        byte[] encoded = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encoded);
    }

    public static String encryptAES(String input, String key) {
        try {
            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            var secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
            return Base64.getEncoder().encodeToString(cipher.doFinal(input.getBytes()));
        } catch (Exception e) {
            LOGGER.error("Could  not encrypt AES", e);
            return null;
        }
    }

    public static String decryptAES(String input, String key) {
        try {
            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            var secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
            var plain = cipher.doFinal(Base64.getDecoder().decode(input));
            return new String(plain);
        } catch (Exception e) {
            LOGGER.error("Could  not encrypt AES", e);
            return null;
        }
    }

}
