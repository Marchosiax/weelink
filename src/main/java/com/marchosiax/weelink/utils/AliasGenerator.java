package com.marchosiax.weelink.utils;

import java.security.SecureRandom;
import java.util.Random;

public class AliasGenerator {

    private static final char[] CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY0123456789".toCharArray();
    private static final int DEFAULT_LENGTH = 8;
    private static final Random random = new SecureRandom();

    private static String generate() {
        return generate(DEFAULT_LENGTH);
    }

    public static String generate(int length) {
        var builder = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            var ch = CHARS[random.nextInt(CHARS.length)];
            builder.append(ch);
        }
        return builder.toString();
    }

}
