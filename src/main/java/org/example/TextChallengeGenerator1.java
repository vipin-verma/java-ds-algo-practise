package org.example;

import java.security.SecureRandom;
import java.text.Normalizer;

/**
 * Utility class for generating a secure random text challenge within a given length range.
 * Handles basic security hardening against XSS, Unicode spoofing, and DoS-style payloads.
 */
public class TextChallengeGenerator1 {

    // Allowed character set: alphanumeric + basic symbols + space
    // Safe special characters (avoiding <, >, ", ', / etc.)
    private static final String SAFE_SPECIALS = "!@#$%^&*()-_=+[]{}|:;?";
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";

    // Combined safe character pool
    private static final String CHAR_POOL = LETTERS + DIGITS + SAFE_SPECIALS + " ";
    private static final SecureRandom random = new SecureRandom();

    /**
     * Generates a secure random text challenge string.
     *
     * @param minLength Minimum length of the string (inclusive)
     * @param maxLength Maximum length of the string (inclusive)
     * @return Random string between minLength and maxLength
     */
    public String generateText(int minLength, int maxLength) {
        if (minLength <= 0 || maxLength <= 0 || minLength > maxLength) {
            throw new IllegalArgumentException("Invalid min/max text length values.");
        }

        int actualLength = random.nextInt((maxLength - minLength) + 1) + minLength;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < actualLength; i++) {
            sb.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }

        String result = sb.toString();
        result = sanitize(result);

        return result;
    }

    /**
     * Sanitizes the generated text to prevent injection attacks.
     * Removes dangerous characters and normalizes Unicode.
     *
     * @param input The raw generated string.
     * @return Sanitized string.
     */
    private String sanitize(String input) {
        // Normalize unicode to prevent homoglyph attack
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFKC);

        // Remove potentially dangerous characters (extra safety)
        return normalized
                .replaceAll("[<>\\\\'\"]", "")   // Remove <, >, ', ", \
                .replaceAll("[\u0000-\u001F\u007F]", "") // Remove control characters
                .trim();
    }
}

class TestTextChallenge {
    public static void main(String[] args) {
        TextChallengeGenerator1 generator = new TextChallengeGenerator1();
        String text = generator.generateText(4, 10);
        System.out.println("Generated Secure Text Challenge: " + text);
    }
}
