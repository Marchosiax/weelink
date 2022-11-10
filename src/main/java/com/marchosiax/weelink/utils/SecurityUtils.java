package com.marchosiax.weelink.utils;

import org.springframework.lang.NonNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityUtils {

    public static String hashSHA256(@NonNull String input) throws NoSuchAlgorithmException {
        var digest = MessageDigest.getInstance("SHA-256");
        byte[] encoded = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encoded);
    }

}
