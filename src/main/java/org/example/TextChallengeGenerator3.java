package org.example;

import java.security.SecureRandom;
import java.text.Normalizer;

/**
 * Secure generator for random text-based challenge strings.
 * Accepts min and max length. Filters risky characters to prevent XSS and other input-based attacks.
 */
public class TextChallengeGenerator3 {

    // Only include alphanumeric characters
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * Generates a secure random text challenge within the specified range.
     *
     * @param minLength minimum allowed length (inclusive)
     * @param maxLength maximum allowed length (inclusive)
     * @return generated challenge text
     */
    public String generateText(int minLength, int maxLength) {
        if (minLength <= 0 || maxLength < minLength) {
            throw new IllegalArgumentException("Invalid min/max length. Must be positive and max >= min.");
        }

        SecureRandom random = new SecureRandom();
        int finalLength = random.nextInt((maxLength - minLength) + 1) + minLength;
        StringBuilder sb = new StringBuilder();

        while (sb.length() < finalLength) {
            char ch = CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length()));
            sb.append(ch);
        }

        return sb.toString();
    }
}

class TestTextChallenge1 {
    public static void main(String[] args) {
        TextChallengeGenerator3 generator = new TextChallengeGenerator3();
        String challenge = generator.generateText(10, 50);
        System.out.println("Generated Text Challenge: " + challenge);
    }
}
