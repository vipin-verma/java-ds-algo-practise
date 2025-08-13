package org.example;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * This class demonstrates the need for the Stream API in Java by providing
 * various examples of its usage, along with explanations.
 */
public class StreamAPIExamples {

    public static void main(String[] args) {
        // Example 1: Filtering and Mapping
        filteringAndMappingExample();

        // Example 2: Reducing
        reducingExample();

        // Example 3: Collecting and Grouping
        collectingAndGroupingExample();

        // Example 4: Parallel Streams
        parallelStreamsExample();

        // Example 5: Lazy Evaluation and Short-Circuiting
        lazyEvaluationExample();

        // Example 6: Handling Primitives with Streams
        primitiveStreamsExample();

        // Example 7: Stream Generation
        streamGenerationExample();

        // Example 8: Exception Handling in Streams
        exceptionHandlingExample();
    }

    /**
     * Example 1: Demonstrates filtering and mapping using the Stream API.
     * Filters a list of names starting with 'A' and converts them to uppercase.
     */
    private static void filteringAndMappingExample() {
        System.out.println("Example 1: Filtering and Mapping");

        List<String> names = Arrays.asList("Alice", "Bob", "Andrew", "Anna", "Charlie");

        // Using Stream API to filter and map the names
        List<String> result = names.stream()
                .filter(name -> name.startsWith("A"))   // Intermediate operation: filters names starting with 'A'
                .map(String::toUpperCase)               // Intermediate operation: converts names to uppercase
                .collect(Collectors.toList());          // Terminal operation: collects the result into a List

        System.out.println("Names starting with 'A' in uppercase: " + result);
        System.out.println();
    }

    /**
     * Example 2: Demonstrates reducing a stream to a single value.
     * Calculates the product of a list of integers.
     */
    private static void reducingExample() {
        System.out.println("Example 2: Reducing");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Using reduce to calculate the product of all numbers
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);  // Initial value is 1, accumulator multiplies elements

        System.out.println("Product of numbers: " + product); // Output: 120
        System.out.println();
    }

    /**
     * Example 3: Demonstrates collecting and grouping using the Stream API.
     * Groups employees by their department.
     */
    private static void collectingAndGroupingExample() {
        System.out.println("Example 3: Collecting and Grouping");

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "HR", 50000),
                new Employee("Bob", "IT", 60000),
                new Employee("Charlie", "HR", 55000),
                new Employee("David", "IT", 70000),
                new Employee("Eve", "Finance", 65000)
        );

        // Grouping employees by department
        Map<String, List<Employee>> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        // Printing the grouped employees
        employeesByDept.forEach((department, empList) -> {
            System.out.println("Department: " + department);
            empList.forEach(emp -> System.out.println(" - " + emp.getName()));
        });
        System.out.println();
    }

    /**
     * Example 4: Demonstrates the use of parallel streams.
     * Counts active users in a large dataset.
     */
    private static void parallelStreamsExample() {
        System.out.println("Example 4: Parallel Streams");

        // Simulating a large dataset of users
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            users.add(new User(i, i % 2 == 0)); // Every even ID is active
        }

        // Counting active users using a parallel stream
        long activeUserCount = users.parallelStream()
                .filter(User::isActive)
                .count();

        System.out.println("Number of active users: " + activeUserCount);
        System.out.println();
    }

    /**
     * Example 5: Demonstrates lazy evaluation and short-circuiting in streams.
     * Finds the first number greater than 100 in an infinite stream.
     */
    private static void lazyEvaluationExample() {
        System.out.println("Example 5: Lazy Evaluation and Short-Circuiting");

        // Creating an infinite stream of natural numbers
        Stream<Integer> infiniteNumbers = Stream.iterate(1, n -> n + 1);

        // Finding the first number greater than 100
        int result = infiniteNumbers
                .filter(n -> n > 100)   // Intermediate operation
                .findFirst()            // Terminal operation that short-circuits
                .orElse(-1);

        System.out.println("First number greater than 100: " + result);
        System.out.println();
    }

    /**
     * Example 6: Demonstrates handling of primitive streams.
     * Calculates the average of an array of integers.
     */
    private static void primitiveStreamsExample() {
        System.out.println("Example 6: Primitive Streams");

        int[] numbers = {1, 2, 3, 4, 5};

        // Using IntStream to calculate the average
        OptionalDouble average = Arrays.stream(numbers)
                .average();  // Terminal operation that calculates the average

        System.out.println("Average of numbers: " + (average.isPresent() ? average.getAsDouble() : "N/A"));
        System.out.println();
    }

    /**
     * Example 7: Demonstrates different ways of generating streams.
     */
    private static void streamGenerationExample() {
        System.out.println("Example 7: Stream Generation");

        // Creating a stream from values
        Stream<String> streamOfValues = Stream.of("One", "Two", "Three");
        streamOfValues.forEach(System.out::println);

        // Creating a stream from an array
        String[] array = {"Four", "Five", "Six"};
        Arrays.stream(array).forEach(System.out::println);

        // Creating a stream using Stream.builder()
        Stream<String> streamBuilder = Stream.<String>builder()
                .add("Seven")
                .add("Eight")
                .build();
        streamBuilder.forEach(System.out::println);

        System.out.println();
    }

    /**
     * Example 8: Demonstrates exception handling within streams.
     * Reads integers from a list of strings, handling parsing exceptions.
     */
    private static void exceptionHandlingExample() {
        System.out.println("Example 8: Exception Handling in Streams");

        List<String> numberStrings = Arrays.asList("1", "2", "three", "4", "five");

        // Parsing strings to integers with exception handling
        List<Integer> numbers = numberStrings.stream()
                .map(str -> {
                    try {
                        return Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        // Handle the exception by returning a default value or skipping
                        return null; // or use Optional, or filter out
                    }
                })
                .filter(Objects::nonNull) // Remove nulls resulting from parse failures
                .collect(Collectors.toList());

        System.out.println("Parsed numbers: " + numbers);
        System.out.println();
    }

    // Supporting Classes for Examples

    /**
     * Represents an Employee with a name, department, and salary.
     */
    static class Employee {
        private String name;
        private String department;
        private double salary;

        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        // Getters
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
    }

    /**
     * Represents a User with an ID and active status.
     */
    static class User {
        private int id;
        private boolean active;

        public User(int id, boolean active) {
            this.id = id;
            this.active = active;
        }

        // Getter
        public boolean isActive() { return active; }
    }
}
