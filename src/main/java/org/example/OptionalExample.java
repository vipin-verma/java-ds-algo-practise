package org.example;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> optionalValue = Optional.of("Hello, Optional!");

        // Check if a value is present and print it
        optionalValue.ifPresent(System.out::println);

        // Provide a default value if no value is present
        String result = optionalValue.orElse("Default Value");
        System.out.println(result);
    }
}

