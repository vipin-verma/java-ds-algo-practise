package org.example;

import org.example.GenerateAlphaNumeric;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerateNumberChallenge {
    private static final Logger LOGGER = Logger.getLogger(GenerateAlphaNumeric.class.getName());
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String NUMBERS = "0123456789";
    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String ALPHANUMERICS = NUMBERS + ALPHABETS;

    public static void main(String[] args) {
      //  LOGGER.info("Starting the program...");
        List<String> alphaNumerics = generateAlphaNumerics(3);
      //  LOGGER.info("Generated alphanumeric list: " + alphaNumerics);

        String correctAlphaNumeric = selectCorrectAlphaNumeric(alphaNumerics);
       LOGGER.info("Selected correct alphanumeric: " + correctAlphaNumeric);
    }

    public static List<String> generateAlphaNumerics(int count) {
      //  LOGGER.info("Generating " + count + " unique random alphanumeric strings...");
        List<String> alphaNumerics = new ArrayList<>();

        while (alphaNumerics.size() < count) {
            String alphaNumeric = generateRandomAlphaNumeric();
            if (!alphaNumerics.contains(alphaNumeric)) {
                alphaNumerics.add(alphaNumeric);
               // LOGGER.fine("Added value to list: " + alphaNumeric);
            } else {
               // LOGGER.fine("Duplicate value detected, generating again...");
            }
        }
        LOGGER.info("Final generated alphanumerics: " + alphaNumerics);
        return alphaNumerics;
    }

    private static String generateRandomAlphaNumeric() {
        int length = RANDOM.nextInt(2) + 1; // Length can be either 1 or 2
        String characterPool;
        switch (RANDOM.nextInt(3)) {
            case 0 -> characterPool = NUMBERS; // Only numbers
            case 1 -> characterPool = ALPHABETS; // Only alphabets
            default -> characterPool = ALPHANUMERICS; // Alphanumeric
        }

        return RANDOM.ints(length, 0, characterPool.length())
                .mapToObj(characterPool::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public static String selectCorrectAlphaNumeric(List<String> alphaNumerics) {
      //  LOGGER.info("Selecting the correct alphanumeric from the list...");
        Collections.shuffle(alphaNumerics);
        LOGGER.fine("List after shuffling: " + alphaNumerics);
        return alphaNumerics.get(0);
    }
}


/*****
 * INFO: Final generated alphanumerics: [3, D, 9]
 * INFO: Selected correct alphanumeric: 3
 *INFO: Final generated alphanumerics: [Bj, Cy, 89]
 * INFO: Selected correct alphanumeric: Cy
 *INFO: Final generated alphanumerics: [g, 99, B]
 * INFO: Selected correct alphanumeric: 99
 * */