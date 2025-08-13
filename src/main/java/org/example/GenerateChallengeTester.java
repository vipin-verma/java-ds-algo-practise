package org.example;

import java.util.List;

public class GenerateChallengeTester {
    public static void main(String[] args) {
        GenerateAlphaNumericNumberChallenge1 generator = new GenerateAlphaNumericNumberChallenge1();

        // Test with numberOnly = true
        int numberLength = 2;
        int numberOfChoices = 3;
        boolean numberOnly = true;

        List<String> numbersOnly = generator.generateAlphaNumerics(numberLength, numberOfChoices, numberOnly);
        String correctNumber = generator.selectCorrectAlphaNumeric(numbersOnly);

        System.out.println("Generated Number Only Choices: " + numbersOnly);
        System.out.println("Selected Correct Number: " + correctNumber);

        // Test with numberOnly = false (mixed)
        numberOnly = false;
        List<String> mixedAlphaNum = generator.generateAlphaNumerics(numberLength, numberOfChoices, numberOnly);
        String correctMixed = generator.selectCorrectAlphaNumeric(mixedAlphaNum);

        System.out.println("Generated Mixed Choices: " + mixedAlphaNum);
        System.out.println("Selected Correct Mixed Value: " + correctMixed);
    }
}
