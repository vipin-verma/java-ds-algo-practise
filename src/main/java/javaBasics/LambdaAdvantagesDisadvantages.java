import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.io.*;

public class LambdaAdvantagesDisadvantages {
    
    public static void main(String[] args) {
        System.out.println("=== Lambda Expressions: फायदे और नुकसान ===\n");
        
        // 1. Code Readability Comparison
        demonstrateCodeReadability();
        
        // 2. Performance Comparison
        demonstratePerformanceComparison();
        
        // 3. Debugging Challenges
        demonstrateDebuggingChallenges();
        
        // 4. Exception Handling Complexity
        demonstrateExceptionHandling();
        
        // 5. Memory Usage Analysis
        demonstrateMemoryUsage();
        
        // 6. Stack Trace Comparison
        demonstrateStackTraces();
        
        // 7. When to Use vs When to Avoid
        demonstrateWhenToUse();
        
        // 8. Best Practices
        demonstrateBestPractices();
        
        // 9. Real-world Scenarios
        demonstrateRealWorldScenarios();
        
        System.out.println("\n=== Lambda Analysis Completed! ===");
    }
    
    private static void demonstrateCodeReadability() {
        System.out.println("1. CODE READABILITY COMPARISON:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        
        // Traditional approach - Multiple lines
        System.out.println("\nTraditional Approach:");
        List<String> filteredNames1 = new ArrayList<>();
        for (String name : names) {
            if (name.length() > 3) {
                String upperName = name.toUpperCase();
                filteredNames1.add(upperName);
            }
        }
        System.out.println("Filtered names: " + filteredNames1);
        
        // Lambda approach - Single line chain
        System.out.println("\nLambda Approach:");
        List<String> filteredNames2 = names.stream()
            .filter(name -> name.length() > 3)
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("Filtered names: " + filteredNames2);
        
        // Complex sorting comparison
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineer", 75000),
            new Person("Bob", 30, "Manager", 85000),
            new Person("Charlie", 35, "Engineer", 80000),
            new Person("David", 28, "Designer", 70000)
        );
        
        System.out.println("\nComplex Sorting Comparison:");
        
        // Traditional sorting - Multiple steps
        System.out.println("Traditional Sorting:");
        List<Person> sorted1 = new ArrayList<>(people);
        Collections.sort(sorted1, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                int jobCompare = p1.getJob().compareTo(p2.getJob());
                if (jobCompare != 0) {
                    return jobCompare;
                }
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
        System.out.println("Sorted: " + sorted1);
        
        // Lambda sorting - Single expression
        System.out.println("\nLambda Sorting:");
        List<Person> sorted2 = people.stream()
            .sorted((p1, p2) -> {
                int jobCompare = p1.getJob().compareTo(p2.getJob());
                return jobCompare != 0 ? jobCompare : Integer.compare(p1.getAge(), p2.getAge());
            })
            .collect(Collectors.toList());
        System.out.println("Sorted: " + sorted2);
        
        System.out.println();
    }
    
    private static void demonstratePerformanceComparison() {
        System.out.println("2. PERFORMANCE COMPARISON:");
        
        // Create large dataset
        List<Integer> numbers = IntStream.range(1, 1000001).boxed().collect(Collectors.toList());
        System.out.println("Processing " + numbers.size() + " numbers...");
        
        // 1. Sum of even numbers - Traditional loop
        System.out.println("\n1. Sum of Even Numbers:");
        
        long startTime = System.currentTimeMillis();
        int traditionalSum = 0;
        for (int n : numbers) {
            if (n % 2 == 0) {
                traditionalSum += n;
            }
        }
        long traditionalTime = System.currentTimeMillis() - startTime;
        System.out.println("Traditional loop: " + traditionalTime + "ms, Sum: " + traditionalSum);
        
        // Lambda with stream
        startTime = System.currentTimeMillis();
        int lambdaSum = numbers.stream()
            .filter(n -> n % 2 == 0)
            .mapToInt(Integer::intValue)
            .sum();
        long lambdaTime = System.currentTimeMillis() - startTime;
        System.out.println("Lambda stream: " + lambdaTime + "ms, Sum: " + lambdaSum);
        
        // 2. Boxing/Unboxing overhead
        System.out.println("\n2. Boxing/Unboxing Overhead:");
        
        startTime = System.currentTimeMillis();
        int sum1 = numbers.stream()
            .mapToInt(Integer::intValue)  // Unboxing - better
            .sum();
        long time1 = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int sum2 = numbers.stream()
            .reduce(0, Integer::sum);    // Boxing - worse
        long time2 = System.currentTimeMillis() - startTime;
        
        System.out.println("Using mapToInt (unboxing): " + time1 + "ms");
        System.out.println("Using reduce (boxing): " + time2 + "ms");
        System.out.println("Performance difference: " + (time2 - time1) + "ms");
        
        // 3. Parallel vs Sequential
        System.out.println("\n3. Parallel vs Sequential:");
        
        startTime = System.currentTimeMillis();
        int sequentialSum = numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int parallelSum = numbers.parallelStream()
            .mapToInt(Integer::intValue)
            .sum();
        long parallelTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Sequential: " + sequentialTime + "ms");
        System.out.println("Parallel: " + parallelTime + "ms");
        System.out.println("Speedup: " + String.format("%.2f", (double)sequentialTime/parallelTime) + "x");
        
        System.out.println();
    }
    
    private static void demonstrateDebuggingChallenges() {
        System.out.println("3. DEBUGGING CHALLENGES:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        System.out.println("\nDebugging Traditional Code:");
        // Traditional code में आसानी से breakpoint लगा सकते हैं
        for (String name : names) {
            if (name.length() > 3) {  // Breakpoint here
                String upperName = name.toUpperCase();
                System.out.println("Processing: " + upperName);  // Breakpoint here
            }
        }
        
        System.out.println("\nDebugging Lambda Code:");
        // Lambda में debugging मुश्किल
        names.stream()
            .filter(name -> name.length() > 3)  // Breakpoint here - less effective
            .map(String::toUpperCase)           // Breakpoint here - less effective
            .forEach(System.out::println);      // Breakpoint here - less effective
        
        // Complex lambda debugging
        System.out.println("\nComplex Lambda Debugging:");
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineer", 75000),
            new Person("Bob", 30, "Manager", 85000),
            new Person("Charlie", 35, "Engineer", 80000)
        );
        
        // Bad: Complex lambda - debugging nightmare
        System.out.println("Bad: Complex lambda (hard to debug):");
        List<String> result1 = people.stream()
            .filter(p -> p.getAge() > 25 && p.getSalary() > 70000)  // Multiple conditions
            .map(p -> p.getName() + " (" + p.getJob() + ")")        // Complex transformation
            .filter(s -> s.contains("Engineer"))                     // Another filter
            .collect(Collectors.toList());
        System.out.println("Result: " + result1);
        
        // Good: Extract to methods - easy to debug
        System.out.println("\nGood: Extract to methods (easy to debug):");
        List<String> result2 = people.stream()
            .filter(LambdaAdvantagesDisadvantages::isExperiencedHighEarner)  // Method - can debug
            .map(LambdaAdvantagesDisadvantages::formatPersonInfo)            // Method - can debug
            .filter(LambdaAdvantagesDisadvantages::isEngineer)               // Method - can debug
            .collect(Collectors.toList());
        System.out.println("Result: " + result2);
        
        System.out.println();
    }
    
    private static void demonstrateExceptionHandling() {
        System.out.println("4. EXCEPTION HANDLING COMPLEXITY:");
        
        List<String> numberStrings = Arrays.asList("1", "2", "abc", "4", "5", "def");
        
        // Traditional approach - Simple exception handling
        System.out.println("\nTraditional Exception Handling:");
        List<Integer> traditionalResult = new ArrayList<>();
        for (String str : numberStrings) {
            try {
                int num = Integer.parseInt(str);
                traditionalResult.add(num);
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid number: " + str);
            }
        }
        System.out.println("Valid numbers: " + traditionalResult);
        
        // Lambda approach - Complex exception handling
        System.out.println("\nLambda Exception Handling:");
        List<Integer> lambdaResult = numberStrings.stream()
            .map(str -> {
                try {
                    return Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid number: " + str);
                    return -1; // Sentinel value
                }
            })
            .filter(n -> n != -1)  // Remove sentinel values
            .collect(Collectors.toList());
        System.out.println("Valid numbers: " + lambdaResult);
        
        // Better approach - Extract exception handling
        System.out.println("\nBetter Approach - Extract Exception Handling:");
        List<Integer> betterResult = numberStrings.stream()
            .map(LambdaAdvantagesDisadvantages::safeParseInt)  // Method handles exception
            .filter(Objects::nonNull)                         // Remove null values
            .collect(Collectors.toList());
        System.out.println("Valid numbers: " + betterResult);
        
        // Exception in stream operations
        System.out.println("\nException in Stream Operations:");
        try {
            List<Integer> crashResult = numberStrings.stream()
                .map(Integer::parseInt)  // This will crash on "abc"
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("Stream crashed with exception: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void demonstrateMemoryUsage() {
        System.out.println("5. MEMORY USAGE ANALYSIS:");
        
        // Lambda objects are different instances
        System.out.println("\nLambda Object Instances:");
        
        Supplier<String> supplier1 = () -> "Hello";
        Supplier<String> supplier2 = () -> "Hello";
        Supplier<String> supplier3 = () -> "Hello";
        
        System.out.println("supplier1 == supplier2: " + (supplier1 == supplier2));
        System.out.println("supplier1.equals(supplier2): " + supplier1.equals(supplier2));
        System.out.println("supplier1.getClass(): " + supplier1.getClass().getSimpleName());
        System.out.println("supplier2.getClass(): " + supplier2.getClass().getSimpleName());
        
        // Memory usage comparison
        System.out.println("\nMemory Usage Comparison:");
        
        // Traditional approach - Single object
        Runnable traditionalTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Traditional task");
            }
        };
        
        // Lambda approach - Multiple objects
        List<Runnable> lambdaTasks = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            lambdaTasks.add(() -> System.out.println("Lambda task " + i));
        }
        
        System.out.println("Created " + lambdaTasks.size() + " lambda tasks");
        System.out.println("Each lambda is a separate object instance");
        
        // Lambda capture cost
        System.out.println("\nLambda Capture Cost:");
        
        final int multiplier = 2;
        final String prefix = "Number: ";
        
        // Lambda captures external variables
        Function<Integer, String> capturedLambda = n -> prefix + (n * multiplier);
        
        // No capture - more efficient
        Function<Integer, String> noCaptureLambda = n -> "Number: " + (n * 2);
        
        System.out.println("Captured lambda result: " + capturedLambda.apply(5));
        System.out.println("No capture lambda result: " + noCaptureLambda.apply(5));
        
        System.out.println();
    }
    
    private static void demonstrateStackTraces() {
        System.out.println("6. STACK TRACE COMPARISON:");
        
        System.out.println("\nTraditional Stack Trace:");
        try {
            traditionalMethodWithException();
        } catch (Exception e) {
            System.out.println("Traditional exception stack trace:");
            e.printStackTrace();
        }
        
        System.out.println("\nLambda Stack Trace:");
        try {
            lambdaMethodWithException();
        } catch (Exception e) {
            System.out.println("Lambda exception stack trace:");
            e.printStackTrace();
        }
        
        System.out.println();
    }
    
    private static void demonstrateWhenToUse() {
        System.out.println("7. WHEN TO USE vs WHEN TO AVOID:");
        
        // GOOD: Simple operations
        System.out.println("\nGOOD: Simple Operations");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        // Use lambda for simple filtering
        List<String> longNames = names.stream()
            .filter(name -> name.length() > 4)
            .collect(Collectors.toList());
        System.out.println("Long names: " + longNames);
        
        // Use lambda for simple mapping
        List<Integer> nameLengths = names.stream()
            .map(String::length)
            .collect(Collectors.toList());
        System.out.println("Name lengths: " + nameLengths);
        
        // BAD: Complex business logic
        System.out.println("\nBAD: Complex Business Logic");
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineer", 75000),
            new Person("Bob", 30, "Manager", 85000),
            new Person("Charlie", 35, "Engineer", 80000)
        );
        
        // Bad: Complex lambda
        List<String> badResult = people.stream()
            .filter(p -> {
                // Complex business logic in lambda - hard to read and debug
                if (p.getAge() > 25) {
                    if (p.getSalary() > 70000) {
                        if ("Engineer".equals(p.getJob()) || "Manager".equals(p.getJob())) {
                            return p.getName().length() > 3;
                        }
                    }
                }
                return false;
            })
            .map(p -> {
                // Complex transformation in lambda
                String title = p.getAge() > 30 ? "Senior " : "Junior ";
                return title + p.getJob() + ": " + p.getName();
            })
            .collect(Collectors.toList());
        System.out.println("Bad result: " + badResult);
        
        // GOOD: Extract complex logic to methods
        System.out.println("\nGOOD: Extract Complex Logic to Methods");
        List<String> goodResult = people.stream()
            .filter(LambdaAdvantagesDisadvantages::isEligibleForPromotion)
            .map(LambdaAdvantagesDisadvantages::formatPromotionTitle)
            .collect(Collectors.toList());
        System.out.println("Good result: " + goodResult);
        
        System.out.println();
    }
    
    private static void demonstrateBestPractices() {
        System.out.println("8. BEST PRACTICES:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        // 1. Keep lambdas short
        System.out.println("\n1. Keep Lambdas Short:");
        
        // Bad: Long lambda
        List<String> badResult = names.stream()
            .filter(name -> {
                if (name.length() > 3) {
                    if (name.startsWith("A") || name.startsWith("B")) {
                        return name.contains("i") || name.contains("o");
                    }
                }
                return false;
            })
            .collect(Collectors.toList());
        System.out.println("Bad result: " + badResult);
        
        // Good: Extract to method
        List<String> goodResult = names.stream()
            .filter(LambdaAdvantagesDisadvantages::isSpecialName)
            .collect(Collectors.toList());
        System.out.println("Good result: " + goodResult);
        
        // 2. Use method references when possible
        System.out.println("\n2. Use Method References When Possible:");
        
        // Bad: Lambda when method reference works
        List<Integer> lengths1 = names.stream()
            .map(name -> name.length())
            .collect(Collectors.toList());
        
        // Good: Method reference
        List<Integer> lengths2 = names.stream()
            .map(String::length)
            .collect(Collectors.toList());
        
        System.out.println("Using lambda: " + lengths1);
        System.out.println("Using method reference: " + lengths2);
        
        // 3. Avoid side effects
        System.out.println("\n3. Avoid Side Effects:");
        
        // Bad: Side effect in lambda
        StringBuilder badBuilder = new StringBuilder();
        names.forEach(name -> badBuilder.append(name).append(", "));
        System.out.println("Bad result: " + badBuilder.toString());
        
        // Good: No side effects
        String goodResult2 = names.stream()
            .collect(Collectors.joining(", "));
        System.out.println("Good result: " + goodResult2);
        
        System.out.println();
    }
    
    private static void demonstrateRealWorldScenarios() {
        System.out.println("9. REAL-WORLD SCENARIOS:");
        
        // Scenario 1: Data validation
        System.out.println("\nScenario 1: Data Validation");
        List<String> emails = Arrays.asList(
            "user@example.com",
            "invalid-email",
            "another@domain.org",
            "no-at-sign"
        );
        
        Predicate<String> emailValidator = email -> 
            email.contains("@") && email.contains(".") && email.length() > 5;
        
        List<String> validEmails = emails.stream()
            .filter(emailValidator)
            .collect(Collectors.toList());
        System.out.println("Valid emails: " + validEmails);
        
        // Scenario 2: Configuration filtering
        System.out.println("\nScenario 2: Configuration Filtering");
        Map<String, String> config = new HashMap<>();
        config.put("db.url", "localhost:3306");
        config.put("db.user", "admin");
        config.put("db.password", "secret");
        config.put("app.name", "MyApp");
        config.put("app.version", "1.0.0");
        
        Map<String, String> dbConfig = config.entrySet().stream()
            .filter(entry -> entry.getKey().startsWith("db."))
            .collect(Collectors.toMap(
                entry -> entry.getKey().substring(3),
                Map.Entry::getValue
            ));
        System.out.println("Database config: " + dbConfig);
        
        // Scenario 3: Event handling
        System.out.println("\nScenario 3: Event Handling");
        Button button = new Button("Submit");
        button.addClickListener(() -> System.out.println("Form submitted!"));
        button.addClickListener(() -> System.out.println("Data saved to database"));
        button.addClickListener(() -> System.out.println("Email notification sent"));
        
        button.click();
        
        System.out.println();
    }
    
    // Helper methods for demonstration
    private static boolean isExperiencedHighEarner(Person p) {
        return p.getAge() > 25 && p.getSalary() > 70000;
    }
    
    private static String formatPersonInfo(Person p) {
        return p.getName() + " (" + p.getJob() + ")";
    }
    
    private static boolean isEngineer(String info) {
        return info.contains("Engineer");
    }
    
    private static boolean isEligibleForPromotion(Person p) {
        if (p.getAge() > 25) {
            if (p.getSalary() > 70000) {
                if ("Engineer".equals(p.getJob()) || "Manager".equals(p.getJob())) {
                    return p.getName().length() > 3;
                }
            }
        }
        return false;
    }
    
    private static String formatPromotionTitle(Person p) {
        String title = p.getAge() > 30 ? "Senior " : "Junior ";
        return title + p.getJob() + ": " + p.getName();
    }
    
    private static boolean isSpecialName(String name) {
        if (name.length() > 3) {
            if (name.startsWith("A") || name.startsWith("B")) {
                return name.contains("i") || name.contains("o");
            }
        }
        return false;
    }
    
    private static Integer safeParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("Skipping invalid number: " + str);
            return null;
        }
    }
    
    private static void traditionalMethodWithException() {
        System.out.println("Traditional method called");
        throw new RuntimeException("Exception in traditional method");
    }
    
    private static void lambdaMethodWithException() {
        System.out.println("Lambda method called");
        Runnable task = () -> {
            System.out.println("Lambda task executed");
            throw new RuntimeException("Exception in lambda");
        };
        task.run();
    }
}

// Helper classes
class Person {
    private String name;
    private int age;
    private String job;
    private int salary;
    
    public Person(String name, int age, String job, int salary) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.salary = salary;
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getJob() { return job; }
    public int getSalary() { return salary; }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", job='" + job + "', salary=" + salary + "}";
    }
}

class Button {
    private String text;
    private List<Runnable> clickListeners = new ArrayList<>();
    
    public Button(String text) {
        this.text = text;
    }
    
    public void addClickListener(Runnable listener) {
        clickListeners.add(listener);
    }
    
    public void click() {
        System.out.println("Button '" + text + "' clicked!");
        clickListeners.forEach(Runnable::run);
    }
}
