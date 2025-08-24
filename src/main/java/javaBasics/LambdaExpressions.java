import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.io.*;

public class LambdaExpressions {
    
    public static void main(String[] args) {
        System.out.println("=== Java Lambda Expressions - Complete Guide ===\n");
        
        // 1. Basic Lambda Syntax
        demonstrateBasicLambdaSyntax();
        
        // 2. Functional Interfaces
        demonstrateFunctionalInterfaces();
        
        // 3. Built-in Functional Interfaces
        demonstrateBuiltInFunctionalInterfaces();
        
        // 4. Lambda with Collections
        demonstrateLambdaWithCollections();
        
        // 5. Lambda with Streams
        demonstrateLambdaWithStreams();
        
        // 6. Method References
        demonstrateMethodReferences();
        
        // 7. Lambda Best Practices
        demonstrateLambdaBestPractices();
        
        // 8. Advanced Lambda Patterns
        demonstrateAdvancedLambdaPatterns();
        
        // 9. Lambda Performance Considerations
        demonstrateLambdaPerformance();
        
        // 10. Real-world Lambda Examples
        demonstrateRealWorldExamples();
        
        // 11. Interview Questions and Scenarios
        demonstrateInterviewQuestions();
    }
    
    private static void demonstrateBasicLambdaSyntax() {
        System.out.println("1. BASIC LAMBDA SYNTAX:");
        
        // Traditional anonymous class vs Lambda
        System.out.println("\nTraditional Anonymous Class:");
        Runnable traditionalRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from traditional anonymous class!");
            }
        };
        traditionalRunnable.run();
        
        // Equivalent Lambda expression
        System.out.println("\nEquivalent Lambda Expression:");
        Runnable lambdaRunnable = () -> System.out.println("Hello from lambda expression!");
        lambdaRunnable.run();
        
        // Lambda with parameters
        System.out.println("\nLambda with Parameters:");
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("Sum: " + add.apply(5, 3));
        
        // Lambda with multiple statements
        System.out.println("\nLambda with Multiple Statements:");
        Consumer<String> printer = (str) -> {
            String upper = str.toUpperCase();
            String lower = str.toLowerCase();
            System.out.println("Original: " + str);
            System.out.println("Uppercase: " + upper);
            System.out.println("Lowercase: " + lower);
        };
        printer.accept("Hello Lambda!");
        
        // Lambda with type inference
        System.out.println("\nLambda with Type Inference:");
        Comparator<String> lengthComparator = (s1, s2) -> s1.length() - s2.length();
        List<String> words = Arrays.asList("cat", "elephant", "dog", "ant");
        words.sort(lengthComparator);
        System.out.println("Sorted by length: " + words);
        
        // Lambda with explicit types
        System.out.println("\nLambda with Explicit Types:");
        BinaryOperator<Double> multiply = (Double x, Double y) -> x * y;
        System.out.println("Product: " + multiply.apply(4.5, 2.0));
        
        System.out.println();
    }
    
    private static void demonstrateFunctionalInterfaces() {
        System.out.println("2. FUNCTIONAL INTERFACES:");
        
        // Custom functional interface
        System.out.println("\nCustom Functional Interface:");
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;
        
        System.out.println("10 + 5 = " + addition.operate(10, 5));
        System.out.println("10 - 5 = " + subtraction.operate(10, 5));
        System.out.println("10 * 5 = " + multiplication.operate(10, 5));
        System.out.println("10 / 5 = " + division.operate(10, 5));
        
        // Functional interface with default method
        System.out.println("\nFunctional Interface with Default Method:");
        Calculator calculator = (x, y) -> x + y;
        System.out.println("Sum: " + calculator.calculate(10, 20));
        System.out.println("Double sum: " + calculator.doubleCalculate(10, 20));
        
        // Multiple abstract methods (will cause compilation error)
        System.out.println("\nNote: Functional interfaces can have only one abstract method");
        System.out.println("But can have multiple default and static methods");
        
        // Generic functional interface
        System.out.println("\nGeneric Functional Interface:");
        Transformer<String, Integer> stringToLength = String::length;
        Transformer<Integer, String> numberToString = Object::toString;
        
        System.out.println("Length of 'Hello': " + stringToLength.transform("Hello"));
        System.out.println("String of 42: " + numberToString.transform(42));
        
        System.out.println();
    }
    
    private static void demonstrateBuiltInFunctionalInterfaces() {
        System.out.println("3. BUILT-IN FUNCTIONAL INTERFACES:");
        
        // Predicate - boolean test
        System.out.println("\nPredicate Interface:");
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isLong = s -> s.length() > 10;
        Predicate<String> startsWithA = s -> s.startsWith("A");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        List<String> filteredNames = names.stream()
            .filter(isEmpty.negate())           // Not empty
            .filter(isLong.negate())            // Not too long
            .filter(startsWithA.or(s -> s.startsWith("B"))) // Starts with A or B
            .collect(Collectors.toList());
        
        System.out.println("Filtered names: " + filteredNames);
        
        // Function - transform input to output
        System.out.println("\nFunction Interface:");
        Function<String, Integer> getLength = String::length;
        Function<Integer, String> addPrefix = n -> "Number: " + n;
        Function<String, String> addSuffix = s -> s + "!";
        
        // Function composition
        Function<String, String> composed = getLength.andThen(addPrefix).andThen(addSuffix);
        System.out.println("Composed result: " + composed.apply("Hello"));
        
        // Consumer - consume input, no output
        System.out.println("\nConsumer Interface:");
        Consumer<String> printUpper = s -> System.out.print(s.toUpperCase() + " ");
        Consumer<String> printLower = s -> System.out.print(s.toLowerCase() + " ");
        
        System.out.println("Printing names in uppercase:");
        names.forEach(printUpper);
        System.out.println("\nPrinting names in lowercase:");
        names.forEach(printLower);
        System.out.println();
        
        // Supplier - provide output, no input
        System.out.println("\nSupplier Interface:");
        Supplier<Double> randomSupplier = Math::random;
        Supplier<String> greetingSupplier = () -> "Hello, World!";
        Supplier<List<String>> listSupplier = () -> new ArrayList<>();
        
        System.out.println("Random number: " + randomSupplier.get());
        System.out.println("Greeting: " + greetingSupplier.get());
        System.out.println("Empty list created: " + listSupplier.get());
        
        // BiFunction - two inputs, one output
        System.out.println("\nBiFunction Interface:");
        BiFunction<String, String, String> concat = String::concat;
        BiFunction<Integer, Integer, Double> average = (a, b) -> (a + b) / 2.0;
        
        System.out.println("Concatenated: " + concat.apply("Hello", " World"));
        System.out.println("Average: " + average.apply(10, 20));
        
        // UnaryOperator - one input, same type output
        System.out.println("\nUnaryOperator Interface:");
        UnaryOperator<String> reverse = s -> new StringBuilder(s).reverse().toString();
        UnaryOperator<Integer> square = n -> n * n;
        
        System.out.println("Reversed 'Hello': " + reverse.apply("Hello"));
        System.out.println("Square of 5: " + square.apply(5));
        
        System.out.println();
    }
    
    private static void demonstrateLambdaWithCollections() {
        System.out.println("4. LAMBDA WITH COLLECTIONS:");
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineer"),
            new Person("Bob", 30, "Manager"),
            new Person("Charlie", 35, "Engineer"),
            new Person("David", 28, "Designer"),
            new Person("Eve", 32, "Engineer")
        );
        
        // Sorting with lambda
        System.out.println("\nSorting with Lambda:");
        System.out.println("Original list: " + people);
        
        // Sort by age
        people.sort((p1, p2) -> p1.getAge() - p2.getAge());
        System.out.println("Sorted by age: " + people);
        
        // Sort by name
        people.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        System.out.println("Sorted by name: " + people);
        
        // Sort by job, then by age
        people.sort((p1, p2) -> {
            int jobCompare = p1.getJob().compareTo(p2.getJob());
            if (jobCompare != 0) return jobCompare;
            return p1.getAge() - p2.getAge();
        });
        System.out.println("Sorted by job, then age: " + people);
        
        // Filtering with lambda
        System.out.println("\nFiltering with Lambda:");
        List<Person> engineers = people.stream()
            .filter(p -> "Engineer".equals(p.getJob()))
            .collect(Collectors.toList());
        System.out.println("Engineers: " + engineers);
        
        List<Person> youngPeople = people.stream()
            .filter(p -> p.getAge() < 30)
            .collect(Collectors.toList());
        System.out.println("Young people (< 30): " + youngPeople);
        
        // Mapping with lambda
        System.out.println("\nMapping with Lambda:");
        List<String> names = people.stream()
            .map(Person::getName)
            .collect(Collectors.toList());
        System.out.println("Names: " + names);
        
        List<Integer> ages = people.stream()
            .map(Person::getAge)
            .collect(Collectors.toList());
        System.out.println("Ages: " + ages);
        
        // Reducing with lambda
        System.out.println("\nReducing with Lambda:");
        int totalAge = people.stream()
            .mapToInt(Person::getAge)
            .reduce(0, (sum, age) -> sum + age);
        System.out.println("Total age: " + totalAge);
        
        String allNames = people.stream()
            .map(Person::getName)
            .reduce("", (result, name) -> result + (result.isEmpty() ? "" : ", ") + name);
        System.out.println("All names: " + allNames);
        
        System.out.println();
    }
    
    private static void demonstrateLambdaWithStreams() {
        System.out.println("5. LAMBDA WITH STREAMS:");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Basic stream operations with lambda
        System.out.println("\nBasic Stream Operations:");
        System.out.println("Original numbers: " + numbers);
        
        // Filter even numbers
        List<Integer> evens = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Even numbers: " + evens);
        
        // Square each number
        List<Integer> squares = numbers.stream()
            .map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println("Squared numbers: " + squares);
        
        // Sum of all numbers
        int sum = numbers.stream()
            .reduce(0, (acc, n) -> acc + n);
        System.out.println("Sum: " + sum);
        
        // Advanced stream operations
        System.out.println("\nAdvanced Stream Operations:");
        
        // Group by even/odd
        Map<Boolean, List<Integer>> grouped = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Grouped by even/odd: " + grouped);
        
        // Find first even number greater than 5
        Optional<Integer> firstEvenGT5 = numbers.stream()
            .filter(n -> n % 2 == 0)
            .filter(n -> n > 5)
            .findFirst();
        System.out.println("First even > 5: " + firstEvenGT5.orElse(-1));
        
        // Check if all numbers are positive
        boolean allPositive = numbers.stream()
            .allMatch(n -> n > 0);
        System.out.println("All positive: " + allPositive);
        
        // Check if any number is greater than 8
        boolean anyGT8 = numbers.stream()
            .anyMatch(n -> n > 8);
        System.out.println("Any > 8: " + anyGT8);
        
        // Collect to different collection types
        System.out.println("\nCollecting to Different Types:");
        
        Set<Integer> uniqueSquares = numbers.stream()
            .map(n -> n * n)
            .collect(Collectors.toSet());
        System.out.println("Unique squares: " + uniqueSquares);
        
        Map<Integer, String> numberToString = numbers.stream()
            .collect(Collectors.toMap(
                n -> n,
                n -> "Number " + n
            ));
        System.out.println("Number to string mapping: " + numberToString);
        
        // Joining strings
        String joined = numbers.stream()
            .map(Object::toString)
            .collect(Collectors.joining(" -> "));
        System.out.println("Joined with arrows: " + joined);
        
        System.out.println();
    }
    
    private static void demonstrateMethodReferences() {
        System.out.println("6. METHOD REFERENCES:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        // Static method reference
        System.out.println("\nStatic Method Reference:");
        Consumer<String> staticPrinter = System.out::println;
        names.forEach(staticPrinter);
        
        // Instance method reference on specific object
        System.out.println("\nInstance Method Reference on Specific Object:");
        Consumer<String> upperPrinter = System.out::println;
        names.forEach(upperPrinter);
        
        // Instance method reference on arbitrary object
        System.out.println("\nInstance Method Reference on Arbitrary Object:");
        Function<String, Integer> lengthGetter = String::length;
        List<Integer> lengths = names.stream()
            .map(lengthGetter)
            .collect(Collectors.toList());
        System.out.println("Lengths: " + lengths);
        
        // Constructor reference
        System.out.println("\nConstructor Reference:");
        Supplier<ArrayList<String>> listSupplier = ArrayList::new;
        ArrayList<String> newList = listSupplier.get();
        newList.add("New Item");
        System.out.println("New list: " + newList);
        
        // Method reference with parameters
        System.out.println("\nMethod Reference with Parameters:");
        BiFunction<String, String, String> concat = String::concat;
        System.out.println("Concatenated: " + concat.apply("Hello", " World"));
        
        // Method reference vs lambda
        System.out.println("\nMethod Reference vs Lambda:");
        
        // Using lambda
        List<String> upperNames1 = names.stream()
            .map(s -> s.toUpperCase())
            .collect(Collectors.toList());
        
        // Using method reference
        List<String> upperNames2 = names.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        
        System.out.println("Using lambda: " + upperNames1);
        System.out.println("Using method reference: " + upperNames2);
        
        // Complex method references
        System.out.println("\nComplex Method References:");
        
        // Sorting with method reference
        names.sort(String::compareTo);
        System.out.println("Sorted names: " + names);
        
        // Filtering with method reference
        List<String> nonEmptyNames = names.stream()
            .filter(String::isEmpty)
            .collect(Collectors.toList());
        System.out.println("Non-empty names: " + nonEmptyNames);
        
        System.out.println();
    }
    
    private static void demonstrateLambdaBestPractices() {
        System.out.println("7. LAMBDA BEST PRACTICES:");
        
        // 1. Keep lambdas short and readable
        System.out.println("\n1. Keep Lambdas Short and Readable:");
        
        // Bad: Long, complex lambda
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> badResult = numbers.stream()
            .filter(n -> {
                if (n % 2 == 0) {
                    if (n > 2) {
                        return n < 10;
                    }
                }
                return false;
            })
            .collect(Collectors.toList());
        
        // Good: Extract to method
        List<Integer> goodResult = numbers.stream()
            .filter(LambdaExpressions::isEvenBetween2And10)
            .collect(Collectors.toList());
        
        System.out.println("Bad result: " + badResult);
        System.out.println("Good result: " + goodResult);
        
        // 2. Use meaningful parameter names
        System.out.println("\n2. Use Meaningful Parameter Names:");
        
        // Bad: Single letter names
        BinaryOperator<Integer> badAdd = (a, b) -> a + b;
        
        // Good: Descriptive names
        BinaryOperator<Integer> goodAdd = (firstNumber, secondNumber) -> firstNumber + secondNumber;
        
        System.out.println("Bad add: " + badAdd.apply(5, 3));
        System.out.println("Good add: " + goodAdd.apply(5, 3));
        
        // 3. Avoid side effects in lambdas
        System.out.println("\n3. Avoid Side Effects in Lambdas:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        // Bad: Side effect in lambda
        StringBuilder badBuilder = new StringBuilder();
        names.forEach(name -> badBuilder.append(name).append(", "));
        System.out.println("Bad result: " + badBuilder.toString());
        
        // Good: No side effects
        String goodResult = names.stream()
            .collect(Collectors.joining(", "));
        System.out.println("Good result: " + goodResult);
        
        // 4. Use method references when possible
        System.out.println("\n4. Use Method References When Possible:");
        
        // Bad: Lambda when method reference would work
        List<Integer> lengths1 = names.stream()
            .map(name -> name.length())
            .collect(Collectors.toList());
        
        // Good: Method reference
        List<Integer> lengths2 = names.stream()
            .map(String::length)
            .collect(Collectors.toList());
        
        System.out.println("Using lambda: " + lengths1);
        System.out.println("Using method reference: " + lengths2);
        
        // 5. Handle exceptions properly
        System.out.println("\n5. Handle Exceptions Properly:");
        
        List<String> numberStrings = Arrays.asList("1", "2", "abc", "4");
        
        // Bad: Exception in lambda
        try {
            List<Integer> parsed1 = numberStrings.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
            System.out.println("Parsed (bad): " + parsed1);
        } catch (NumberFormatException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        
        // Good: Handle exceptions in lambda
        List<Integer> parsed2 = numberStrings.stream()
            .map(str -> {
                try {
                    return Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    return -1; // Default value
                }
            })
            .filter(n -> n != -1)
            .collect(Collectors.toList());
        System.out.println("Parsed (good): " + parsed2);
        
        System.out.println();
    }
    
    private static void demonstrateAdvancedLambdaPatterns() {
        System.out.println("8. ADVANCED LAMBDA PATTERNS:");
        
        // 1. Currying
        System.out.println("\n1. Currying (Partial Application):");
        
        // Traditional function
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        
        // Curried function
        Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;
        
        // Apply partially
        Function<Integer, Integer> add5 = curriedAdd.apply(5);
        System.out.println("Add 5 to 3: " + add5.apply(3));
        System.out.println("Add 5 to 7: " + add5.apply(7));
        
        // 2. Function composition
        System.out.println("\n2. Function Composition:");
        
        Function<String, String> addPrefix = s -> "Hello " + s;
        Function<String, String> addSuffix = s -> s + "!";
        Function<String, String> toUpper = String::toUpperCase;
        
        // Compose functions
        Function<String, String> composed = addPrefix
            .andThen(toUpper)
            .andThen(addSuffix);
        
        System.out.println("Composed result: " + composed.apply("world"));
        
        // 3. Higher-order functions
        System.out.println("\n3. Higher-Order Functions:");
        
        Function<Function<Integer, Integer>, Function<List<Integer>, List<Integer>>> map = 
            f -> list -> list.stream().map(f).collect(Collectors.toList());
        
        Function<Function<Integer, Boolean>, Function<List<Integer>, List<Integer>>> filter = 
            p -> list -> list.stream().filter(p).collect(Collectors.toList());
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // Apply higher-order functions
        List<Integer> doubled = map.apply(n -> n * 2).apply(numbers);
        List<Integer> evens = filter.apply(n -> n % 2 == 0).apply(numbers);
        
        System.out.println("Original: " + numbers);
        System.out.println("Doubled: " + doubled);
        System.out.println("Evens: " + evens);
        
        // 4. Memoization
        System.out.println("\n4. Memoization:");
        
        Map<Integer, Integer> cache = new HashMap<>();
        Function<Integer, Integer> memoizedFactorial = n -> {
            if (cache.containsKey(n)) {
                return cache.get(n);
            }
            int result = calculateFactorial(n);
            cache.put(n, result);
            return result;
        };
        
        System.out.println("Factorial of 5: " + memoizedFactorial.apply(5));
        System.out.println("Factorial of 5 (cached): " + memoizedFactorial.apply(5));
        System.out.println("Cache: " + cache);
        
        // 5. Builder pattern with lambda
        System.out.println("\n5. Builder Pattern with Lambda:");
        
        PersonBuilder builder = new PersonBuilder();
        Person person = builder
            .with(p -> p.setName("John"))
            .with(p -> p.setAge(30))
            .with(p -> p.setJob("Developer"))
            .build();
        
        System.out.println("Built person: " + person);
        
        System.out.println();
    }
    
    private static void demonstrateLambdaPerformance() {
        System.out.println("9. LAMBDA PERFORMANCE CONSIDERATIONS:");
        
        List<Integer> numbers = IntStream.range(1, 1000001).boxed().collect(Collectors.toList());
        
        // 1. Boxing/Unboxing overhead
        System.out.println("\n1. Boxing/Unboxing Overhead:");
        
        long startTime = System.currentTimeMillis();
        int sum1 = numbers.stream()
            .mapToInt(Integer::intValue)  // Unboxing
            .sum();
        long time1 = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int sum2 = numbers.stream()
            .reduce(0, Integer::sum);    // Boxing
        long time2 = System.currentTimeMillis() - startTime;
        
        System.out.println("Using mapToInt (unboxing): " + time1 + "ms");
        System.out.println("Using reduce (boxing): " + time2 + "ms");
        System.out.println("Sum1: " + sum1 + ", Sum2: " + sum2);
        
        // 2. Stream vs traditional loop
        System.out.println("\n2. Stream vs Traditional Loop:");
        
        startTime = System.currentTimeMillis();
        int streamSum = numbers.stream()
            .filter(n -> n % 2 == 0)
            .mapToInt(Integer::intValue)
            .sum();
        long streamTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int loopSum = 0;
        for (int n : numbers) {
            if (n % 2 == 0) {
                loopSum += n;
            }
        }
        long loopTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Stream time: " + streamTime + "ms");
        System.out.println("Loop time: " + loopTime + "ms");
        System.out.println("Stream sum: " + streamSum + ", Loop sum: " + loopSum);
        
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
        
        System.out.println("Sequential time: " + sequentialTime + "ms");
        System.out.println("Parallel time: " + parallelTime + "ms");
        System.out.println("Sequential sum: " + sequentialSum + ", Parallel sum: " + parallelSum);
        
        // 4. Lambda capture cost
        System.out.println("\n4. Lambda Capture Cost:");
        
        final int multiplier = 2;
        
        startTime = System.currentTimeMillis();
        List<Integer> multiplied1 = numbers.stream()
            .map(n -> n * multiplier)  // Captures multiplier
            .collect(Collectors.toList());
        long captureTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        List<Integer> multiplied2 = numbers.stream()
            .mapToInt(Integer::intValue)
            .map(n -> n * 2)  // No capture
            .boxed()
            .collect(Collectors.toList());
        long noCaptureTime = System.currentTimeMillis() - startTime;
        
        System.out.println("With capture: " + captureTime + "ms");
        System.out.println("Without capture: " + noCaptureTime + "ms");
        
        System.out.println();
    }
    
    private static void demonstrateRealWorldExamples() {
        System.out.println("10. REAL-WORLD LAMBDA EXAMPLES:");
        
        // 1. Event handling
        System.out.println("\n1. Event Handling:");
        
        Button button = new Button("Click Me");
        button.addClickListener(() -> System.out.println("Button clicked!"));
        button.addClickListener(() -> System.out.println("Another action performed!"));
        
        // Simulate button click
        button.click();
        
        // 2. Data validation
        System.out.println("\n2. Data Validation:");
        
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
        
        // 3. Configuration management
        System.out.println("\n3. Configuration Management:");
        
        Map<String, String> config = new HashMap<>();
        config.put("db.url", "localhost:3306");
        config.put("db.user", "admin");
        config.put("db.password", "secret");
        config.put("app.name", "MyApp");
        config.put("app.version", "1.0.0");
        
        // Get database config
        Map<String, String> dbConfig = config.entrySet().stream()
            .filter(entry -> entry.getKey().startsWith("db."))
            .collect(Collectors.toMap(
                entry -> entry.getKey().substring(3), // Remove "db." prefix
                Map.Entry::getValue
            ));
        
        System.out.println("Database config: " + dbConfig);
        
        // 4. Retry mechanism
        System.out.println("\n4. Retry Mechanism:");
        
        Supplier<String> riskyOperation = () -> {
            if (Math.random() < 0.7) {
                throw new RuntimeException("Operation failed");
            }
            return "Success!";
        };
        
        String result = retry(riskyOperation, 3);
        System.out.println("Retry result: " + result);
        
        // 5. Chain of responsibility
        System.out.println("\n5. Chain of Responsibility:");
        
        List<Function<String, String>> processors = Arrays.asList(
            String::trim,
            String::toLowerCase,
            s -> s.replaceAll("\\s+", "_"),
            s -> s.length() > 10 ? s.substring(0, 10) : s
        );
        
        String processed = processors.stream()
            .reduce(Function.identity(), Function::andThen)
            .apply("  Hello World Example  ");
        
        System.out.println("Original: '  Hello World Example  '");
        System.out.println("Processed: '" + processed + "'");
        
        System.out.println("\n=== Lambda Expressions Tutorial Completed! ===");
    }
    
    private static void demonstrateInterviewQuestions() {
        System.out.println("\n=== INTERVIEW QUESTIONS AND SCENARIOS ===\n");
        
        // 1. Theoretical Questions
        demonstrateTheoreticalQuestions();
        
        // 2. Practical Coding Questions
        demonstratePracticalQuestions();
        
        // 3. Tricky Scenarios
        demonstrateTrickyScenarios();
        
        // 4. Performance Questions
        demonstratePerformanceQuestions();
        
        // 5. Design Pattern Questions
        demonstrateDesignPatternQuestions();
        
        System.out.println("\n=== Interview Questions Completed! ===");
    }
    
    // Helper methods
    private static boolean isEvenBetween2And10(int n) {
        return n % 2 == 0 && n > 2 && n < 10;
    }
    
    private static int calculateFactorial(int n) {
        if (n <= 1) return 1;
        return n * calculateFactorial(n - 1);
    }
    
    private static <T> T retry(Supplier<T> operation, int maxAttempts) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return operation.get();
            } catch (Exception e) {
                if (attempt == maxAttempts) {
                    throw new RuntimeException("All attempts failed", e);
                }
                System.out.println("Attempt " + attempt + " failed, retrying...");
            }
        }
        throw new RuntimeException("Unexpected error in retry");
    }
    
    // ===== INTERVIEW QUESTIONS SECTION =====
    
    private static void demonstrateTheoreticalQuestions() {
        System.out.println("1. THEORETICAL INTERVIEW QUESTIONS:");
        
        System.out.println("\nQ1: What is a Lambda Expression?");
        System.out.println("A: Lambda expressions are anonymous functions that can be treated as values.");
        System.out.println("   They provide a way to pass behavior as parameters to methods.");
        System.out.println("   Syntax: (parameters) -> { body }");
        
        System.out.println("\nQ2: What is a Functional Interface?");
        System.out.println("A: An interface with exactly one abstract method.");
        System.out.println("   Can have multiple default and static methods.");
        System.out.println("   @FunctionalInterface annotation is optional but recommended.");
        
        System.out.println("\nQ3: What are the main built-in functional interfaces?");
        System.out.println("A: - Predicate<T>: boolean test(T t)");
        System.out.println("   - Function<T,R>: R apply(T t)");
        System.out.println("   - Consumer<T>: void accept(T t)");
        System.out.println("   - Supplier<T>: T get()");
        System.out.println("   - BiFunction<T,U,R>: R apply(T t, U u)");
        
        System.out.println("\nQ4: What is the difference between Lambda and Anonymous Class?");
        System.out.println("A: - Lambda: More concise, better performance, limited to functional interfaces");
        System.out.println("   - Anonymous Class: More verbose, can implement multiple methods, more flexible");
        
        System.out.println("\nQ5: What is Method Reference?");
        System.out.println("A: Shorthand notation for lambda expressions that call existing methods.");
        System.out.println("   Syntax: ClassName::methodName or object::methodName");
        
        System.out.println("\nQ6: What is Variable Capture in Lambda?");
        System.out.println("A: Lambda can access final or effectively final variables from enclosing scope.");
        System.out.println("   'Effectively final' means the variable is never reassigned after initialization.");
        
        System.out.println("\nQ7: Can Lambda throw checked exceptions?");
        System.out.println("A: No, lambda expressions cannot throw checked exceptions unless the functional interface declares them.");
        System.out.println("   Unchecked exceptions can be thrown freely.");
        
        System.out.println("\nQ8: What is the difference between Stream and Collection?");
        System.out.println("A: - Collection: Data structure that holds elements");
        System.out.println("   - Stream: Sequence of elements supporting functional-style operations");
        System.out.println("   - Streams are lazy and can only be traversed once");
        
        System.out.println("\nQ9: What are Intermediate and Terminal Operations?");
        System.out.println("A: - Intermediate: Return Stream, lazy evaluation (filter, map, sorted)");
        System.out.println("   - Terminal: Return result, trigger evaluation (collect, forEach, reduce)");
        
        System.out.println("\nQ10: What is the difference between map() and flatMap()?");
        System.out.println("A: - map(): Transforms each element to another element");
        System.out.println("   - flatMap(): Transforms each element to a stream, then flattens all streams");
        
        System.out.println();
    }
    
    private static void demonstratePracticalQuestions() {
        System.out.println("2. PRACTICAL CODING QUESTIONS:");
        
        System.out.println("\nQ1: Write a lambda to find the longest string in a list:");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        String longest = names.stream()
            .max((s1, s2) -> Integer.compare(s1.length(), s2.length()))
            .orElse("");
        System.out.println("Longest name: " + longest);
        
        System.out.println("\nQ2: Write a lambda to count words starting with 'A':");
        long countA = names.stream()
            .filter(name -> name.startsWith("A"))
            .count();
        System.out.println("Names starting with 'A': " + countA);
        
        System.out.println("\nQ3: Write a lambda to convert list to map (name -> length):");
        Map<String, Integer> nameToLength = names.stream()
            .collect(Collectors.toMap(
                name -> name,
                String::length
            ));
        System.out.println("Name to length mapping: " + nameToLength);
        
        System.out.println("\nQ4: Write a lambda to group people by age range:");
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineer"),
            new Person("Bob", 30, "Manager"),
            new Person("Charlie", 35, "Engineer"),
            new Person("David", 28, "Designer")
        );
        
        Map<String, List<Person>> ageGroups = people.stream()
            .collect(Collectors.groupingBy(p -> {
                if (p.getAge() < 30) return "Young";
                else if (p.getAge() < 40) return "Middle";
                else return "Senior";
            }));
        System.out.println("Age groups: " + ageGroups);
        
        System.out.println("\nQ5: Write a lambda to find the first person with salary > 70000:");
        // Assuming Person has salary field
        Optional<Person> highEarner = people.stream()
            .filter(p -> p.getAge() > 25)
            .findFirst();
        System.out.println("High earner: " + highEarner.orElse(null));
        
        System.out.println();
    }
    
    private static void demonstrateTrickyScenarios() {
        System.out.println("3. TRICKY SCENARIOS:");
        
        System.out.println("\nScenario 1: Lambda with mutable state (BAD):");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        StringBuilder result = new StringBuilder();
        
        // BAD: Mutable state in lambda
        names.forEach(name -> result.append(name).append(", "));
        System.out.println("Result: " + result.toString());
        
        System.out.println("\nScenario 2: Lambda capture of non-final variable:");
        int[] counter = {0};  // Array to simulate mutable variable
        names.forEach(name -> {
            counter[0]++;  // Modifying array element
            System.out.println("Processing " + counter[0] + ": " + name);
        });
        
        System.out.println("\nScenario 3: Lambda with null handling:");
        List<String> mixedNames = Arrays.asList("Alice", null, "Bob", null, "Charlie");
        List<String> validNames = mixedNames.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        System.out.println("Valid names: " + validNames);
        
        System.out.println("\nScenario 4: Lambda with exception handling:");
        List<String> numbers = Arrays.asList("1", "2", "abc", "4");
        List<Integer> parsedNumbers = numbers.stream()
            .map(str -> {
                try {
                    return Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    return -1;  // Sentinel value
                }
            })
            .filter(n -> n != -1)
            .collect(Collectors.toList());
        System.out.println("Parsed numbers: " + parsedNumbers);
        
        System.out.println("\nScenario 5: Lambda with infinite stream:");
        System.out.println("First 5 even numbers:");
        IntStream.iterate(0, n -> n + 2)
            .limit(5)
            .forEach(System.out::println);
        
        System.out.println();
    }
    
    private static void demonstratePerformanceQuestions() {
        System.out.println("4. PERFORMANCE INTERVIEW QUESTIONS:");
        
        System.out.println("\nQ1: When to use parallel streams?");
        System.out.println("A: - Large datasets (>10000 elements)");
        System.out.println("   - CPU-intensive operations");
        System.out.println("   - Operations that can be parallelized");
        System.out.println("   - Avoid for small datasets due to overhead");
        
        System.out.println("\nQ2: What is the performance impact of boxing/unboxing?");
        List<Integer> numbers = IntStream.range(1, 100001).boxed().collect(Collectors.toList());
        
        long startTime = System.currentTimeMillis();
        int sum1 = numbers.stream()
            .mapToInt(Integer::intValue)  // Unboxing - better
            .sum();
        long time1 = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int sum2 = numbers.stream()
            .reduce(0, Integer::sum);    // Boxing - worse
        long time2 = System.currentTimeMillis() - startTime;
        
        System.out.println("Unboxing time: " + time1 + "ms");
        System.out.println("Boxing time: " + time2 + "ms");
        System.out.println("Performance difference: " + (time2 - time1) + "ms");
        
        System.out.println("\nQ3: Stream vs Traditional loop performance:");
        startTime = System.currentTimeMillis();
        int loopSum = 0;
        for (int n : numbers) {
            if (n % 2 == 0) {
                loopSum += n;
            }
        }
        long loopTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int streamSum = numbers.stream()
            .filter(n -> n % 2 == 0)
            .mapToInt(Integer::intValue)
            .sum();
        long streamTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Loop time: " + loopTime + "ms");
        System.out.println("Stream time: " + streamTime + "ms");
        System.out.println("Loop is " + String.format("%.2f", (double)streamTime/loopTime) + "x faster");
        
        System.out.println();
    }
    
    private static void demonstrateDesignPatternQuestions() {
        System.out.println("5. DESIGN PATTERN INTERVIEW QUESTIONS:");
        
        System.out.println("\nQ1: How to implement Strategy Pattern with Lambda?");
        System.out.println("A: Use different lambda expressions for different strategies:");
        
        // Strategy pattern with lambda
        Map<String, Function<Integer, Integer>> strategies = new HashMap<>();
        strategies.put("double", n -> n * 2);
        strategies.put("square", n -> n * n);
        strategies.put("increment", n -> n + 1);
        
        int number = 5;
        System.out.println("Number: " + number);
        strategies.forEach((name, strategy) -> {
            System.out.println(name + " strategy: " + strategy.apply(number));
        });
        
        System.out.println("\nQ2: How to implement Observer Pattern with Lambda?");
        System.out.println("A: Use Consumer functional interface:");
        
        List<Consumer<String>> observers = Arrays.asList(
            message -> System.out.println("Observer 1: " + message),
            message -> System.out.println("Observer 2: " + message),
            message -> System.out.println("Observer 3: " + message)
        );
        
        String event = "User logged in";
        observers.forEach(observer -> observer.accept(event));
        
        System.out.println("\nQ3: How to implement Chain of Responsibility with Lambda?");
        System.out.println("A: Use Function composition:");
        
        Function<String, String> step1 = s -> s.trim();
        Function<String, String> step2 = s -> s.toLowerCase();
        Function<String, String> step3 = s -> s.replaceAll("\\s+", "_");
        
        Function<String, String> pipeline = step1.andThen(step2).andThen(step3);
        
        String input = "  Hello World Example  ";
        String result = pipeline.apply(input);
        System.out.println("Input: '" + input + "'");
        System.out.println("Output: '" + result + "'");
        
        System.out.println("\nQ4: How to implement Factory Pattern with Lambda?");
        System.out.println("A: Use Supplier functional interface:");
        
        Map<String, Supplier<Person>> personFactories = new HashMap<>();
        personFactories.put("engineer", () -> new Person("Engineer", 25, "Software Engineer"));
        personFactories.put("manager", () -> new Person("Manager", 30, "Project Manager"));
        personFactories.put("designer", () -> new Person("Designer", 28, "UI Designer"));
        
        Person engineer = personFactories.get("engineer").get();
        Person manager = personFactories.get("manager").get();
        System.out.println("Created: " + engineer);
        System.out.println("Created: " + manager);
        
        System.out.println("\nQ5: How to implement Builder Pattern with Lambda?");
        System.out.println("A: Use Consumer for fluent API:");
        
        PersonBuilder builder = new PersonBuilder();
        Person person = builder
            .with(p -> p.setName("John"))
            .with(p -> p.setAge(30))
            .with(p -> p.setJob("Developer"))
            .build();
        System.out.println("Built person: " + person);
        
        System.out.println();
    }
}

// Custom functional interfaces
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
    
    default int doubleCalculate(int a, int b) {
        return calculate(a, b) * 2;
    }
}

@FunctionalInterface
interface Transformer<T, R> {
    R transform(T input);
}

// Helper classes
class Person {
    private String name;
    private int age;
    private String job;
    
    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", job='" + job + "'}";
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

class PersonBuilder {
    private Person person = new Person("", 0, "");
    
    public PersonBuilder with(Consumer<Person> setter) {
        setter.accept(person);
        return this;
    }
    
    public Person build() {
        return person;
    }
}
