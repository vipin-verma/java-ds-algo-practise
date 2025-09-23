package javaBasics;

import java.util.Random;
import java.util.function.*;

public class FunctionalInterfacesDemoOneClass {
    public static void main(String[] args) {
        System.out.println("\n=== Predicate<T> ===");
        demoPredicate();

        System.out.println("\n=== Consumer<T> ===");
        demoConsumer();

        System.out.println("\n=== Function<T,R> ===");
        demoFunction();

        System.out.println("\n=== Supplier<T> ===");
        demoSupplier();

        System.out.println("\n=== BiPredicate<T,U> ===");
        demoBiPredicate();

        System.out.println("\n=== BiConsumer<T,U> ===");
        demoBiConsumer();

        System.out.println("\n=== BiFunction<T,U,R> ===");
        demoBiFunction();

        System.out.println("\n=== UnaryOperator<T> ===");
        demoUnaryOperator();

        System.out.println("\n=== BinaryOperator<T> ===");
        demoBinaryOperator();
    }

    // 1) Predicate<T>
    private static void demoPredicate() {
        // Before Java 8: Anonymous class
        Predicate<Integer> before = new Predicate<Integer>() {
            @Override public boolean test(Integer n) { return n % 2 == 0; }
        };
        // After Java 8: Lambda
        Predicate<Integer> after = n -> n % 2 == 0;

        System.out.println("before.test(10) => " + before.test(10)); // true
        System.out.println("after.test(11)  => " + after.test(11));  // false

        // Bonus: composition
        Predicate<Integer> gt5 = n -> n > 5;
        System.out.println("after.and(gt5).test(8) => " + after.and(gt5).test(8)); // even and >5
    }

    // 2) Consumer<T>
    private static void demoConsumer() {
        // Before Java 8
        Consumer<String> before = new Consumer<String>() {
            @Override public void accept(String s) { System.out.println(s.toUpperCase()); }
        };
        // After Java 8
        Consumer<String> after = s -> System.out.println(s.toUpperCase());

        before.accept("hello");
        after.accept("world");

        // Bonus: chaining
        Consumer<String> exclaim = s -> System.out.println(s + "!");
        after.andThen(exclaim).accept("java");
    }

    // 3) Function<T,R>
    private static void demoFunction() {
        // Before Java 8
        Function<String, Integer> before = new Function<String, Integer>() {
            @Override public Integer apply(String s) { return s.length(); }
        };
        // After Java 8
        Function<String, Integer> after = s -> s.length();

        System.out.println("before.apply(\"Java\") => " + before.apply("Java"));
        System.out.println("after.apply(\"Lambda\") => " + after.apply("Lambda"));

        // Bonus: compose/andThen
        Function<Integer, Integer> square = n -> n * n;
        System.out.println("after.andThen(square).apply(4) => " + after.andThen(square).apply("abcd")); // (len=4)^2
    }

    // 4) Supplier<T>
    private static void demoSupplier() {
        // Fixed seed for deterministic output
        Random rng = new Random(42);
        // Before Java 8
        Supplier<Integer> before = new Supplier<Integer>() {
            @Override public Integer get() { return rng.nextInt(100); }
        };
        // After Java 8
        Supplier<Integer> after = () -> rng.nextInt(100);

        System.out.println("before.get() => " + before.get());
        System.out.println("after.get()  => " + after.get());
    }

    // 5) BiPredicate<T,U>
    private static void demoBiPredicate() {
        // Before Java 8
        BiPredicate<String, String> before = new BiPredicate<String, String>() {
            @Override public boolean test(String a, String b) { return a.equalsIgnoreCase(b); }
        };
        // After Java 8
        BiPredicate<String, String> after = (a, b) -> a.equalsIgnoreCase(b);

        System.out.println("before.test(\"java\", \"JAVA\") => " + before.test("java", "JAVA"));
        System.out.println("after.test(\"Java\", \"Kotlin\") => " + after.test("Java", "Kotlin"));
    }

    // 6) BiConsumer<T,U>
    private static void demoBiConsumer() {
        // Before Java 8
        BiConsumer<String, Integer> before = new BiConsumer<String, Integer>() {
            @Override public void accept(String name, Integer age) {
                System.out.println(name + " is " + age + " years old");
            }
        };
        // After Java 8
        BiConsumer<String, Integer> after = (name, age) -> System.out.println(name + " is " + age + " years old");

        before.accept("Ravi", 30);
        after.accept("Asha", 28);
    }

    // 7) BiFunction<T,U,R>
    private static void demoBiFunction() {
        // Before Java 8
        BiFunction<Integer, Integer, Integer> before = new BiFunction<Integer, Integer, Integer>() {
            @Override public Integer apply(Integer a, Integer b) { return a + b; }
        };
        // After Java 8
        BiFunction<Integer, Integer, Integer> after = (a, b) -> a + b;

        System.out.println("before.apply(5, 10) => " + before.apply(5, 10));
        System.out.println("after.apply(7,  8) => " + after.apply(7, 8));
    }

    // 8) UnaryOperator<T>
    private static void demoUnaryOperator() {
        // Before Java 8
        UnaryOperator<Integer> before = new UnaryOperator<Integer>() {
            @Override public Integer apply(Integer n) { return n * n; }
        };
        // After Java 8
        UnaryOperator<Integer> after = n -> n * n;

        System.out.println("before.apply(5) => " + before.apply(5));
        System.out.println("after.apply(6)  => " + after.apply(6));
    }

    // 9) BinaryOperator<T>
    private static void demoBinaryOperator() {
        // Before Java 8
        BinaryOperator<Integer> before = new BinaryOperator<Integer>() {
            @Override public Integer apply(Integer a, Integer b) { return a * b; }
        };
        // After Java 8
        BinaryOperator<Integer> after = (a, b) -> a * b;

        System.out.println("before.apply(3, 4) => " + before.apply(3, 4));
        System.out.println("after.apply(7, 9)  => " + after.apply(7, 9));
    }
}

