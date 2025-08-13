package org.example;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * This class demonstrates various functional programming concepts in Java.
 */
public class FunctionalProgrammingExamples {

    public static void main(String[] args) {
        // Lambda Expressions Example
        lambdaExpressions();

        // Functional Interfaces Examples
        functionalInterfaces();

        // Stream API Examples
        streamApiExamples();

        // Optional Class Example
        optionalClassExample();

        // Imperative vs Functional Example
        imperativeVsFunctional();

        // Method References Example
        methodReferencesExample();
    }

    /**
     * Demonstrates the use of lambda expressions in Java.
     */
    private static void lambdaExpressions() {
        System.out.println("Lambda Expressions Example:");

        // Example of a lambda expression to implement Runnable
        Runnable runnable = () -> System.out.println("Lambda Runnable running");
        new Thread(runnable).start();

        // Using a lambda expression with a custom functional interface
        GreetingService greetService = message -> System.out.println("Hello, " + message);
        greetService.sayMessage("World");

        System.out.println();
    }

    /**
     * Demonstrates the use of various functional interfaces.
     */
    private static void functionalInterfaces() {
        System.out.println("Functional Interfaces Examples:");

        // Predicate Functional Interface - Checks if a number is even
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4)); // true

        // Function Functional Interface - Squares a number
        Function<Integer, Integer> square = n -> n * n;
        System.out.println("Square of 5: " + square.apply(5)); // 25

        // Consumer Functional Interface - Prints a message
        Consumer<String> printer = message -> System.out.println("Printing: " + message);
        printer.accept("Hello Functional Interfaces!");

        // Supplier Functional Interface - Supplies a random number
        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println("Random number: " + randomSupplier.get());

        System.out.println();
    }

    /**
     * Demonstrates the use of the Stream API in Java.
     */
    private static void streamApiExamples() {
        System.out.println("Stream API Examples:");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        // Filtering names with length greater than 3 and converting to uppercase
        List<String> filteredNames = names.stream()
                .filter(name -> name.length() > 3) // Intermediate operation: filters names
                .map(String::toUpperCase)          // Intermediate operation: converts names to uppercase
                .collect(Collectors.toList());     // Terminal operation: collects results into a list

        System.out.println("Filtered Names: " + filteredNames); // [ALICE, CHARLIE, DAVID]

        // Using reduce to concatenate names
        String concatenatedNames = names.stream()
                .reduce("", (a, b) -> a + " " + b);

        System.out.println("Concatenated Names:" + concatenatedNames); // Alice Bob Charlie David Eve

        System.out.println();
    }

    /**
     * Demonstrates the use of the Optional class to avoid NullPointerException.
     */
    private static void optionalClassExample() {
        System.out.println("Optional Class Example:");

        // Creating an Optional object that may or may not contain a value
        Optional<String> optionalName = Optional.ofNullable(getName());

        // Using ifPresent to execute code only if a value is present
        optionalName.ifPresent(name -> System.out.println("Name is: " + name));

        // Using orElse to provide a default value
        String name = optionalName.orElse("Default Name");
        System.out.println("Name is: " + name);

        System.out.println();
    }

    /**
     * Simulates a method that may return null.
     *
     * @return a String value or null
     */
    private static String getName() {
        // For demonstration, returning null to simulate absence of value
        return null;
    }

    /**
     * Demonstrates the difference between imperative and functional programming styles.
     */
    private static void imperativeVsFunctional() {
        System.out.println("Imperative vs Functional Example:");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Imperative approach to calculate squares
        List<Integer> squaresImperative = new ArrayList<>();
        for (Integer n : numbers) {
            squaresImperative.add(n * n);
        }
        System.out.println("Squares (Imperative): " + squaresImperative); // [1, 4, 9, 16, 25]

        // Functional approach to calculate squares
        List<Integer> squaresFunctional = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Squares (Functional): " + squaresFunctional); // [1, 4, 9, 16, 25]

        System.out.println();
    }

    /**
     * Demonstrates the use of method references in Java.
     */
    private static void methodReferencesExample() {
        System.out.println("Method References Example:");

        List<String> messages = Arrays.asList("Hello", "World", "Method", "References");

        // Using a method reference to print each message
        messages.forEach(System.out::println);

        System.out.println();
    }

    /**
     * A functional interface for greeting service.
     */
    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }
}
