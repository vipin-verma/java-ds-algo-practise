package org.example;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for generating a secure and realistic text challenge
 * that avoids XSS-prone characters and adds special characters and spaces.
 */
public class TextChallengeGenerator {

    // Safe special characters (avoiding <, >, ", ', / etc.)
    private static final String SAFE_SPECIALS = "!@#$%^&*()-_=+[]{}|:;?";
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";

    // Combined safe character pool
    private static final String CHAR_POOL = LETTERS + DIGITS + SAFE_SPECIALS + " ";

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Generates a secure text challenge up to the given max length.
     * Ensures no dangerous characters for XSS attacks.
     *
     * @param maxLength Maximum allowed length (must be > 0)
     * @return Secure random challenge string
     */
    public String generateText(int maxLength) {
        if (maxLength <= 0) {
            throw new IllegalArgumentException("Text length must be greater than 0.");
        }

        int length = RANDOM.nextInt(maxLength) + 1; // 1 to maxLength
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(CHAR_POOL.charAt(RANDOM.nextInt(CHAR_POOL.length())));
        }

        return sanitize(sb.toString().trim());
    }

    /**
     * Removes potentially dangerous characters even if mistakenly allowed.
     * Just a double safety net.
     *
     * @param input raw string
     * @return sanitized string
     */
    private String sanitize(String input) {
        return input
                .replaceAll("[<>\"'/\\\\]", "") // remove common XSS vectors
                .replaceAll("\\s{2,}", " ");     // reduce multiple spaces to single
    }
}
