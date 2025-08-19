/**
 * Question 43: Java Functional Programming
 * 
 * This file contains Functional Programming interview questions covering:
 * - Lambda Expressions
 * - Functional Interfaces
 * - Method References
 * - Streams and Collectors
 * - Best Practices
 */
public class Question043_JavaFunctionalProgramming {
    
    public static void main(String[] args) {
        System.out.println("=== Java Functional Programming - Interview Questions ===\n");
        
        demonstrateLambdaExpressions();
        demonstrateFunctionalInterfaces();
        demonstrateMethodReferences();
        demonstrateStreamsAndCollectors();
        
        System.out.println("\n=== Java Functional Programming Completed! ===");
    }
    
    private static void demonstrateLambdaExpressions() {
        System.out.println("1. LAMBDA EXPRESSIONS:\n");
        
        // Q1: What are Lambda Expressions in Java?
        System.out.println("Q1: What are Lambda Expressions in Java?");
        System.out.println("    Anonymous functions that implement functional interfaces");
        System.out.println("    Provide concise syntax for functional programming");
        System.out.println("    Introduced in Java 8\n");
        
        // Q2: What is the syntax of Lambda Expressions?
        System.out.println("Q2: What is the syntax of Lambda Expressions?");
        System.out.println("    (parameters) -> { body }");
        System.out.println("    (parameters) -> expression");
        System.out.println("    () -> expression");
        System.out.println("    parameter -> expression\n");
        
        // Demonstrate lambda expressions
        System.out.println("Example: Lambda Expression Syntax");
        
        // Basic lambda expressions
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;
        
        System.out.println("    Math Operations:");
        System.out.println("      Addition: " + addition.operate(10, 5));
        System.out.println("      Subtraction: " + subtraction.operate(10, 5));
        System.out.println("      Multiplication: " + multiplication.operate(10, 5));
        System.out.println("      Division: " + division.operate(10, 5));
        
        // Lambda with single parameter
        StringProcessor upperCase = str -> str.toUpperCase();
        StringProcessor lowerCase = str -> str.toLowerCase();
        StringProcessor reverse = str -> new StringBuilder(str).reverse().toString();
        
        String testString = "Hello World";
        System.out.println("    String Processing:");
        System.out.println("      Original: " + testString);
        System.out.println("      Upper case: " + upperCase.process(testString));
        System.out.println("      Lower case: " + lowerCase.process(testString));
        System.out.println("      Reversed: " + reverse.process(testString));
        
        // Lambda with no parameters
        NumberGenerator randomGenerator = () -> (int) (Math.random() * 100);
        NumberGenerator constantGenerator = () -> 42;
        
        System.out.println("    Number Generation:");
        System.out.println("      Random number: " + randomGenerator.generate());
        System.out.println("      Constant number: " + constantGenerator.generate());
        
        // Lambda with multiple statements
        ComplexProcessor complexProcessor = (str, count) -> {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < count; i++) {
                result.append(str).append(" ");
            }
            return result.toString().trim();
        };
        
        System.out.println("    Complex Processing:");
        System.out.println("      Result: " + complexProcessor.process("Java", 3));
    }
    
    private static void demonstrateFunctionalInterfaces() {
        System.out.println("\n2. FUNCTIONAL INTERFACES:\n");
        
        // Q3: What are Functional Interfaces?
        System.out.println("Q3: What are Functional Interfaces?");
        System.out.println("    Interfaces with exactly one abstract method");
        System.out.println("    Can be used with lambda expressions");
        System.out.println("    Marked with @FunctionalInterface annotation\n");
        
        // Q4: What are built-in Functional Interfaces?
        System.out.println("Q4: What are built-in Functional Interfaces?");
        System.out.println("    - Predicate<T>: boolean test(T t)");
        System.out.println("    - Function<T,R>: R apply(T t)");
        System.out.println("    - Consumer<T>: void accept(T t)");
        System.out.println("    - Supplier<T>: T get()\n");
        
        // Demonstrate functional interfaces
        System.out.println("Example: Built-in Functional Interfaces");
        
        // Predicate examples
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<String> isLong = str -> str.length() > 5;
        
        System.out.println("    Predicate Examples:");
        System.out.println("      Is 5 positive? " + isPositive.test(5));
        System.out.println("      Is 5 even? " + isEven.test(5));
        System.out.println("      Is 'Hello' long? " + isLong.test("Hello"));
        
        // Function examples
        Function<String, Integer> getLength = String::length;
        Function<Integer, String> numberToString = Object::toString;
        Function<String, String> addPrefix = str -> "Number: " + str;
        
        System.out.println("    Function Examples:");
        System.out.println("      Length of 'Java': " + getLength.apply("Java"));
        System.out.println("      Number to string: " + numberToString.apply(42));
        System.out.println("      With prefix: " + addPrefix.apply("42"));
        
        // Consumer examples
        Consumer<String> printUpperCase = str -> System.out.println("      UPPER: " + str.toUpperCase());
        Consumer<String> printLowerCase = str -> System.out.println("      lower: " + str.toLowerCase());
        
        System.out.println("    Consumer Examples:");
        printUpperCase.accept("Hello World");
        printLowerCase.accept("Hello World");
        
        // Supplier examples
        Supplier<Double> randomDouble = Math::random;
        Supplier<String> currentTime = () -> "Current time: " + System.currentTimeMillis();
        
        System.out.println("    Supplier Examples:");
        System.out.println("      Random double: " + randomDouble.get());
        System.out.println("      " + currentTime.get());
        
        // Custom functional interface
        System.out.println("    Custom Functional Interface:");
        Validator<String> emailValidator = email -> email != null && email.contains("@");
        Validator<String> phoneValidator = phone -> phone != null && phone.matches("\\d{10}");
        
        System.out.println("      Email validation: " + emailValidator.validate("test@example.com"));
        System.out.println("      Phone validation: " + phoneValidator.validate("1234567890"));
    }
    
    private static void demonstrateMethodReferences() {
        System.out.println("\n3. METHOD REFERENCES:\n");
        
        // Q5: What are Method References?
        System.out.println("Q5: What are Method References?");
        System.out.println("    Shorthand notation for lambda expressions");
        System.out.println("    Reference to existing methods");
        System.out.println("    More readable than lambda expressions\n");
        
        // Q6: What are the types of Method References?
        System.out.println("Q6: What are the types of Method References?");
        System.out.println("    - Static method reference: Class::staticMethod");
        System.out.println("    - Instance method reference: object::instanceMethod");
        System.out.println("    - Constructor reference: Class::new");
        System.out.println("    - Arbitrary object method reference: Class::instanceMethod\n");
        
        // Demonstrate method references
        System.out.println("Example: Method References");
        
        // Static method references
        Function<String, Integer> parseInt = Integer::parseInt;
        Function<String, String> valueOf = String::valueOf;
        
        System.out.println("    Static Method References:");
        System.out.println("      Parse int: " + parseInt.apply("123"));
        System.out.println("      Value of: " + valueOf.apply(456));
        
        // Instance method references
        String prefix = "Hello ";
        Function<String, String> addPrefix = prefix::concat;
        Consumer<String> printer = System.out::println;
        
        System.out.println("    Instance Method References:");
        System.out.println("      With prefix: " + addPrefix.apply("World"));
        printer.accept("      Printed via method reference");
        
        // Constructor references
        Supplier<StringBuilder> newStringBuilder = StringBuilder::new;
        Function<String, StringBuilder> newStringBuilderWithContent = StringBuilder::new;
        
        System.out.println("    Constructor References:");
        StringBuilder sb1 = newStringBuilder.get();
        StringBuilder sb2 = newStringBuilderWithContent.apply("Initial content");
        System.out.println("      Empty StringBuilder: " + sb1.toString());
        System.out.println("      StringBuilder with content: " + sb2.toString());
        
        // Arbitrary object method references
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, String> toLowerCase = String::toLowerCase;
        Function<String, Integer> length = String::length;
        
        System.out.println("    Arbitrary Object Method References:");
        System.out.println("      Upper case: " + toUpperCase.apply("hello"));
        System.out.println("      Lower case: " + toLowerCase.apply("WORLD"));
        System.out.println("      Length: " + length.apply("Java"));
        
        // Method reference with streams
        System.out.println("    Method References with Streams:");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        names.stream()
              .map(String::toUpperCase)
              .filter(name -> name.length() > 4)
              .forEach(System.out::println);
    }
    
    private static void demonstrateStreamsAndCollectors() {
        System.out.println("\n4. STREAMS AND COLLECTORS:\n");
        
        // Q7: What are Streams in Java?
        System.out.println("Q7: What are Streams in Java?");
        System.out.println("    Sequence of elements supporting functional operations");
        System.out.println("    Enable functional-style operations on collections");
        System.out.println("    Provide lazy evaluation and parallel processing\n");
        
        // Q8: What are the main Stream operations?
        System.out.println("Q8: What are the main Stream operations?");
        System.out.println("    - Intermediate: filter, map, flatMap, sorted, distinct");
        System.out.println("    - Terminal: collect, forEach, reduce, count, anyMatch");
        System.out.println("    - Short-circuit: findFirst, findAny, limit\n");
        
        // Demonstrate streams and collectors
        System.out.println("Example: Streams and Collectors");
        
        // Basic stream operations
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        System.out.println("    Basic Stream Operations:");
        System.out.println("      Original numbers: " + numbers);
        
        // Filter and map
        List<Integer> evenSquares = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());
        
        System.out.println("      Even squares: " + evenSquares);
        
        // Reduce operation
        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        
        System.out.println("      Sum: " + sum);
        System.out.println("      Product: " + product);
        
        // Collectors examples
        System.out.println("    Collectors Examples:");
        
        // Grouping by
        Map<String, List<Integer>> groupedByParity = numbers.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "Even" : "Odd"));
        
        System.out.println("      Grouped by parity: " + groupedByParity);
        
        // Partitioning by
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n > 5));
        
        System.out.println("      Partitioned by > 5: " + partitioned);
        
        // Joining
        String joinedNumbers = numbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" -> "));
        
        System.out.println("      Joined with arrows: " + joinedNumbers);
        
        // Advanced stream operations
        System.out.println("    Advanced Stream Operations:");
        
        // FlatMap example
        List<List<Integer>> nestedLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        
        List<Integer> flattened = nestedLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        
        System.out.println("      Flattened nested lists: " + flattened);
        
        // Parallel streams
        long startTime = System.currentTimeMillis();
        int parallelSum = numbers.parallelStream()
                .mapToInt(Integer::intValue)
                .sum();
        long parallelTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int sequentialSum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;
        
        System.out.println("      Parallel sum: " + parallelSum + " (Time: " + parallelTime + "ms)");
        System.out.println("      Sequential sum: " + sequentialSum + " (Time: " + sequentialTime + "ms)");
        
        // Custom collector
        System.out.println("    Custom Collector:");
        String customResult = numbers.stream()
                .collect(Collector.of(
                        StringBuilder::new,
                        (sb, n) -> sb.append(n).append(" "),
                        (sb1, sb2) -> sb1.append(sb2),
                        StringBuilder::toString
                ));
        
        System.out.println("      Custom collector result: " + customResult);
    }
    
    // ===== HELPER INTERFACES =====
    
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }
    
    @FunctionalInterface
    interface StringProcessor {
        String process(String str);
    }
    
    @FunctionalInterface
    interface NumberGenerator {
        int generate();
    }
    
    @FunctionalInterface
    interface ComplexProcessor {
        String process(String str, int count);
    }
    
    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T t);
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA FUNCTIONAL PROGRAMMING:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are Lambda Expressions in Java?
 * 2. What is the syntax of Lambda Expressions?
 * 3. What are Functional Interfaces?
 * 4. What is the @FunctionalInterface annotation?
 * 5. What are built-in Functional Interfaces?
 * 6. What is the Predicate interface?
 * 7. What is the Function interface?
 * 8. What is the Consumer interface?
 * 9. What is the Supplier interface?
 * 10. What are Method References?
 * 11. What are the types of Method References?
 * 12. What are Streams in Java?
 * 13. What are intermediate operations in Streams?
 * 14. What are terminal operations in Streams?
 * 15. What is the difference between map and flatMap?
 * 16. What is the reduce operation?
 * 17. What are Collectors in Java?
 * 18. What is the forEach method?
 * 19. What is the filter method?
 * 20. What is the sorted method?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. How do you create custom Functional Interfaces?
 * 22. How do you use Lambda expressions with collections?
 * 23. How do you chain Lambda expressions?
 * 24. How do you use Lambda expressions with exceptions?
 * 25. How do you use Lambda expressions with generics?
 * 26. How do you use Method References with constructors?
 * 27. How do you use Method References with static methods?
 * 28. How do you use Method References with instance methods?
 * 29. How do you use Streams with primitive types?
 * 30. How do you use Streams with arrays?
 * 31. How do you use Streams with files?
 * 32. How do you use Streams with databases?
 * 33. How do you use Streams with networking?
 * 34. How do you use Streams with reflection?
 * 35. How do you use Streams with annotations?
 * 36. How do you use Streams with enums?
 * 37. How do you use Streams with inner classes?
 * 38. How do you use Streams with interfaces?
 * 39. How do you use Streams with abstract classes?
 * 40. How do you use Streams with final classes?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you implement custom Collectors?
 * 42. How do you optimize Stream performance?
 * 43. How do you use parallel Streams effectively?
 * 44. How do you handle Stream exceptions?
 * 45. How do you implement Stream caching?
 * 46. How do you implement Stream batching?
 * 47. How do you implement Stream pagination?
 * 48. How do you implement Stream sorting?
 * 49. How do you implement Stream grouping?
 * 50. How do you implement Stream partitioning?
 * 51. How do you implement Stream joining?
 * 52. How do you implement Stream reducing?
 * 53. How do you implement Stream collecting?
 * 54. How do you implement Stream mapping?
 * 55. How do you implement Stream filtering?
 * 56. How do you implement Stream finding?
 * 57. How do you implement Stream matching?
 * 58. How do you implement Stream counting?
 * 59. How do you implement Stream limiting?
 * 60. How do you implement Stream skipping?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you design functional architectures?
 * 62. How do you implement functional design patterns?
 * 63. How do you implement functional testing?
 * 64. How do you implement functional debugging?
 * 65. How do you implement functional profiling?
 * 66. How do you implement functional monitoring?
 * 67. How do you implement functional logging?
 * 68. How do you implement functional error handling?
 * 69. How do you implement functional recovery?
 * 70. How do you implement functional scaling?
 * 71. How do you implement functional optimization?
 * 72. How do you implement functional security?
 * 73. How do you implement functional networking?
 * 74. How do you implement functional databases?
 * 75. How do you implement functional messaging?
 * 76. How do you implement functional caching?
 * 77. How do you implement functional validation?
 * 78. How do you implement functional transformation?
 * 79. How do you implement functional aggregation?
 * 80. How do you implement functional composition?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a functional microservices architecture?
 * 82. How would you implement functional data processing pipelines?
 * 83. How would you design functional API gateways?
 * 84. How would you implement functional event handling?
 * 85. How would you design functional message processing?
 * 86. How would you implement functional workflow engines?
 * 87. How would you design functional rule engines?
 * 88. How would you implement functional decision systems?
 * 89. How would you design functional recommendation engines?
 * 90. How would you implement functional search systems?
 * 91. How would you design functional analytics platforms?
 * 92. How would you implement functional machine learning?
 * 93. How would you design functional blockchain systems?
 * 94. How would you implement functional gaming engines?
 * 95. How would you design functional IoT platforms?
 * 96. How would you implement functional social media?
 * 97. How would you design functional e-commerce systems?
 * 98. How would you implement functional healthcare systems?
 * 99. How would you design functional financial systems?
 * 100. How would you implement functional autonomous systems?
 */
