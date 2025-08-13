package org.example;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateAlphaNumericMoreGeneric {
    public static void main(String[] args) {
        System.out.println("Starting the program...");
        List<String> alphaNumerics = generateAlphaNumerics();
        System.out.println("Generated alphanumeric list: " + alphaNumerics);

        String correctAlphaNumeric = selectCorrectAlphaNumeric(alphaNumerics);
        System.out.println("Selected correct alphanumeric: " + correctAlphaNumeric);
    }

    public static List<String> generateAlphaNumerics() {
        System.out.println("Generating 3 unique random alphanumeric strings...");
        List<String> alphaNumerics = new ArrayList<>();
        SecureRandom random = new SecureRandom();
        String numbers = "0123456789";
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String alphanumerics = numbers + alphabets;

        while (alphaNumerics.size() < 3) {
            StringBuilder sb = new StringBuilder();
            int length = random.nextInt(2) + 1; // Length can be either 1 or 2
            String characterPool;
            int type = random.nextInt(3);
            switch (type) {
                case 0 -> characterPool = numbers; // Only numbers
                case 1 -> characterPool = alphabets; // Only alphabets
                default -> characterPool = alphanumerics; // Alphanumeric
            }

            for (int i = 0; i < length; i++) {
                sb.append(characterPool.charAt(random.nextInt(characterPool.length())));
            }
            String alphaNumeric = sb.toString();
            System.out.println("Generated value: " + alphaNumeric);
            if (!alphaNumerics.contains(alphaNumeric)) {
                alphaNumerics.add(alphaNumeric);
                System.out.println("Added value to list: " + alphaNumeric);
            } else {
                System.out.println("Duplicate value detected, generating again...");
            }
        }
        System.out.println("Final generated alphanumerics: " + alphaNumerics);
        return alphaNumerics;
    }

    public static String selectCorrectAlphaNumeric(List<String> alphaNumerics) {
        System.out.println("Selecting the correct alphanumeric from the list...");
        Collections.shuffle(alphaNumerics);
        System.out.println("List after shuffling: " + alphaNumerics);
        String correctAlphaNumeric = alphaNumerics.get(0);
        System.out.println("Correct alphanumeric selected: " + correctAlphaNumeric);
        return correctAlphaNumeric;
    }
}