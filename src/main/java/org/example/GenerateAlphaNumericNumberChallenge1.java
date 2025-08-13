package org.example;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for generating alphanumeric challenges.
 * Supports dynamic configuration for length and number of choices.
 */
public class GenerateAlphaNumericNumberChallenge1 {

    /**
     * Generates a list of alphanumeric strings based on the given length and number of choices.
     *
     * @param numberLength     The maximum length of each alphanumeric string.
     * @param numberOfChoices  The total number of choices to generate.
     * @param numberOnly       Whether to use only numbers or mix with alphabets.
     * @return A list of unique alphanumeric strings.
     */
    public List<String> generateAlphaNumerics(int numberLength, int numberOfChoices, boolean numberOnly) {
        if (numberLength <= 0 || numberOfChoices <= 0) {
            throw new IllegalArgumentException("Both numberLength and numberOfChoices must be greater than 0.");
        }

        List<String> alphaNumerics = new ArrayList<>();
        SecureRandom random = new SecureRandom();
        String numbers = "0123456789";
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String alphanumerics = numbers + alphabets;

        while (alphaNumerics.size() < numberOfChoices) {
            StringBuilder sb = new StringBuilder();
            int length = random.nextInt(numberLength) + 1; // Length between 1 and numberLength

            String characterPool;
            if (numberOnly) {
                characterPool = numbers;
            } else {
                int type = random.nextInt(3);
                characterPool = switch (type) {
                    case 0 -> numbers;
                    case 1 -> alphabets;
                    default -> alphanumerics;
                };
            }

            for (int i = 0; i < length; i++) {
                sb.append(characterPool.charAt(random.nextInt(characterPool.length())));
            }
            String alphaNumeric = sb.toString();
            if (!alphaNumerics.contains(alphaNumeric)) {
                alphaNumerics.add(alphaNumeric);
            }
        }
        return alphaNumerics;
    }

    /**
     * Selects the correct alphanumeric string from the provided list.
     *
     * @param alphaNumerics The list of alphanumeric strings.
     * @return The correct alphanumeric string.
     */
    public String selectCorrectAlphaNumeric(List<String> alphaNumerics) {
        if (alphaNumerics == null || alphaNumerics.isEmpty()) {
            throw new IllegalArgumentException("AlphaNumerics list cannot be null or empty.");
        }
        Collections.shuffle(alphaNumerics);
        return alphaNumerics.get(0); // Return the first element as the correct one
    }
}
