package org.example;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateAlphaNumeric {
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
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        while (alphaNumerics.size() < 3) {
            String alphaNumeric = random.ints(2, 0, characters.length())
                    .mapToObj(characters::charAt)
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();
            System.out.println("Generated alphanumeric: " + alphaNumeric);
            if (!alphaNumerics.contains(alphaNumeric)) {
                alphaNumerics.add(alphaNumeric);
                System.out.println("Added alphanumeric to list: " + alphaNumeric);
            } else {
                System.out.println("Duplicate alphanumeric detected, generating again...");
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