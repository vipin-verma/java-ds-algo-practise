import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * Question 27: Java Streams
 * 
 * This file contains 50+ Streams interview questions covering:
 * - Stream Creation and Operations
 * - Intermediate and Terminal Operations
 * - Stream Collectors and Reduction
 * - Parallel Streams and Performance
 * - Advanced Stream Concepts and Best Practices
 */
public class Question027_JavaStreams {
    
    public static void main(String[] args) {
        System.out.println("=== Java Streams - Interview Questions ===\n");
        
        demonstrateStreamBasics();
        demonstrateIntermediateOperations();
        demonstrateTerminalOperations();
        demonstrateCollectors();
        demonstrateParallelStreams();
        demonstrateAdvancedStreams();
        
        System.out.println("\n=== Java Streams Completed! ===");
    }
    
    // ===== STREAM BASICS =====
    
    private static void demonstrateStreamBasics() {
        System.out.println("1. STREAM BASICS:\n");
        
        // Q1: What are Java Streams?
        System.out.println("Q1: What are Java Streams?");
        System.out.println("    Java Streams are a sequence of elements that support");
        System.out.println("    functional-style operations on collections of data.");
        System.out.println("    They enable parallel processing and functional programming.\n");
        
        // Q2: What are the characteristics of streams?
        System.out.println("Q2: What are the characteristics of streams?");
        System.out.println("    - Lazy evaluation (operations are not executed until terminal operation)");
        System.out.println("    - Immutable (original data source is not modified)");
        System.out.println("    - Can be processed only once");
        System.out.println("    - Support parallel processing\n");
        
        // Q3: How do you create streams?
        System.out.println("Q3: How do you create streams?");
        System.out.println("    - From collections: collection.stream()");
        System.out.println("    - From arrays: Arrays.stream(array)");
        System.out.println("    - From values: Stream.of(values)");
        System.out.println("    - From ranges: IntStream.range(start, end)\n");
        
        // Demonstrate stream creation
        System.out.println("Example: Stream Creation");
        
        // From collection
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        Stream<String> nameStream = names.stream();
        System.out.println("    Collection stream: " + nameStream.count() + " elements");
        
        // From array
        int[] numbers = {1, 2, 3, 4, 5};
        IntStream numberStream = Arrays.stream(numbers);
        System.out.println("    Array stream: " + numberStream.sum() + " sum");
        
        // From values
        Stream<String> valueStream = Stream.of("Apple", "Banana", "Cherry");
        System.out.println("    Value stream: " + valueStream.collect(Collectors.toList()));
        
        // From range
        IntStream rangeStream = IntStream.range(1, 6);
        System.out.println("    Range stream: " + rangeStream.boxed().collect(Collectors.toList()) + "\n");
    }
    
    // ===== INTERMEDIATE OPERATIONS =====
    
    private static void demonstrateIntermediateOperations() {
        System.out.println("2. INTERMEDIATE OPERATIONS:\n");
        
        // Q4: What are intermediate operations?
        System.out.println("Q4: What are intermediate operations?");
        System.out.println("    Intermediate operations return a new stream and are lazy.");
        System.out.println("    They don't execute until a terminal operation is called.");
        System.out.println("    Examples: filter, map, flatMap, distinct, sorted, limit, skip\n");
        
        // Q5: What is the difference between map and flatMap?
        System.out.println("Q5: What is the difference between map and flatMap?");
        System.out.println("    map: Transforms each element to another element");
        System.out.println("    flatMap: Transforms each element to a stream and flattens the result");
        System.out.println("    map: Stream<T> -> Stream<R>");
        System.out.println("    flatMap: Stream<T> -> Stream<Stream<R>> -> Stream<R>\n");
        
        // Demonstrate intermediate operations
        System.out.println("Example: Intermediate Operations");
        
        List<String> words = Arrays.asList("hello", "world", "java", "streams", "programming");
        
        // Filter operation
        List<String> filteredWords = words.stream()
            .filter(word -> word.length() > 4)
            .collect(Collectors.toList());
        System.out.println("    Words longer than 4 chars: " + filteredWords);
        
        // Map operation
        List<Integer> wordLengths = words.stream()
            .map(String::length)
            .collect(Collectors.toList());
        System.out.println("    Word lengths: " + wordLengths);
        
        // FlatMap operation
        List<String> characters = words.stream()
            .flatMap(word -> Arrays.stream(word.split("")))
            .distinct()
            .collect(Collectors.toList());
        System.out.println("    Unique characters: " + characters);
        
        // Distinct and sorted
        List<String> sortedWords = words.stream()
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("    Sorted unique words: " + sortedWords);
        
        // Limit and skip
        List<String> limitedWords = words.stream()
            .skip(1)
            .limit(3)
            .collect(Collectors.toList());
        System.out.println("    Skipped 1, limited to 3: " + limitedWords + "\n");
    }
    
    // ===== TERMINAL OPERATIONS =====
    
    private static void demonstrateTerminalOperations() {
        System.out.println("3. TERMINAL OPERATIONS:\n");
        
        // Q6: What are terminal operations?
        System.out.println("Q6: What are terminal operations?");
        System.out.println("    Terminal operations produce a result and trigger stream execution.");
        System.out.println("    After a terminal operation, the stream cannot be reused.");
        System.out.println("    Examples: collect, reduce, forEach, count, anyMatch, allMatch\n");
        
        // Q7: What is the difference between findFirst and findAny?
        System.out.println("Q7: What is the difference between findFirst and findAny?");
        System.out.println("    findFirst: Returns the first element in encounter order");
        System.out.println("    findAny: Returns any element (useful for parallel streams)");
        System.out.println("    findFirst: Deterministic, findAny: Non-deterministic in parallel\n");
        
        // Demonstrate terminal operations
        System.out.println("Example: Terminal Operations");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Collect operation
        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("    Even numbers: " + evenNumbers);
        
        // Reduce operation
        int sum = numbers.stream()
            .reduce(0, Integer::sum);
        System.out.println("    Sum of all numbers: " + sum);
        
        // Count operation
        long count = numbers.stream()
            .filter(n -> n > 5)
            .count();
        System.out.println("    Count of numbers > 5: " + count);
        
        // AnyMatch, AllMatch, NoneMatch
        boolean hasNegative = numbers.stream().anyMatch(n -> n < 0);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        boolean noneZero = numbers.stream().noneMatch(n -> n == 0);
        
        System.out.println("    Has negative: " + hasNegative);
        System.out.println("    All positive: " + allPositive);
        System.out.println("    None zero: " + noneZero);
        
        // FindFirst and FindAny
        Optional<Integer> firstEven = numbers.stream()
            .filter(n -> n % 2 == 0)
            .findFirst();
        System.out.println("    First even number: " + firstEven.orElse(-1));
        
        // ForEach operation
        System.out.println("    Numbers using forEach:");
        numbers.stream()
            .filter(n -> n % 2 == 0)
            .forEach(n -> System.out.println("      " + n));
        System.out.println();
    }
    
    // ===== COLLECTORS =====
    
    private static void demonstrateCollectors() {
        System.out.println("4. COLLECTORS:\n");
        
        // Q8: What are collectors in streams?
        System.out.println("Q8: What are collectors in streams?");
        System.out.println("    Collectors are utility classes that help collect stream elements");
        System.out.println("    into different data structures like List, Set, Map, etc.");
        System.out.println("    They provide common reduction operations.\n");
        
        // Q9: What are the main types of collectors?
        System.out.println("Q9: What are the main types of collectors?");
        System.out.println("    - toList(), toSet(), toMap()");
        System.out.println("    - joining(), counting(), summingInt()");
        System.out.println("    - groupingBy(), partitioningBy()");
        System.out.println("    - reducing(), collectingAndThen()\n");
        
        // Demonstrate collectors
        System.out.println("Example: Collectors");
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineering"),
            new Person("Bob", 30, "Sales"),
            new Person("Charlie", 25, "Engineering"),
            new Person("David", 35, "Marketing"),
            new Person("Eve", 28, "Engineering")
        );
        
        // toList collector
        List<String> names = people.stream()
            .map(Person::getName)
            .collect(Collectors.toList());
        System.out.println("    Names list: " + names);
        
        // toSet collector
        Set<String> departments = people.stream()
            .map(Person::getDepartment)
            .collect(Collectors.toSet());
        System.out.println("    Unique departments: " + departments);
        
        // toMap collector
        Map<String, Integer> nameAgeMap = people.stream()
            .collect(Collectors.toMap(Person::getName, Person::getAge));
        System.out.println("    Name to age map: " + nameAgeMap);
        
        // joining collector
        String allNames = people.stream()
            .map(Person::getName)
            .collect(Collectors.joining(", "));
        System.out.println("    All names joined: " + allNames);
        
        // counting collector
        long engineeringCount = people.stream()
            .filter(p -> "Engineering".equals(p.getDepartment()))
            .collect(Collectors.counting());
        System.out.println("    Engineering count: " + engineeringCount);
        
        // groupingBy collector
        Map<String, List<Person>> deptGroups = people.stream()
            .collect(Collectors.groupingBy(Person::getDepartment));
        System.out.println("    Grouped by department:");
        deptGroups.forEach((dept, personList) -> {
            System.out.println("      " + dept + ": " + personList.size() + " people");
        });
        
        // partitioningBy collector
        Map<Boolean, List<Person>> agePartition = people.stream()
            .collect(Collectors.partitioningBy(p -> p.getAge() >= 30));
        System.out.println("    Partitioned by age >= 30:");
        System.out.println("      Young (< 30): " + agePartition.get(false).size());
        System.out.println("      Senior (>= 30): " + agePartition.get(true).size());
        
        // averagingInt collector
        double avgAge = people.stream()
            .collect(Collectors.averagingInt(Person::getAge));
        System.out.println("    Average age: " + avgAge);
        
        // summarizingInt collector
        IntSummaryStatistics ageStats = people.stream()
            .collect(Collectors.summarizingInt(Person::getAge));
        System.out.println("    Age statistics: " + ageStats + "\n");
    }
    
    // ===== PARALLEL STREAMS =====
    
    private static void demonstrateParallelStreams() {
        System.out.println("5. PARALLEL STREAMS:\n");
        
        // Q10: What are parallel streams?
        System.out.println("Q10: What are parallel streams?");
        System.out.println("    Parallel streams divide the work into multiple threads");
        System.out.println("    to potentially improve performance on multi-core systems.");
        System.out.println("    Use parallelStream() method to create them.\n");
        
        // Q11: When should you use parallel streams?
        System.out.println("Q11: When should you use parallel streams?");
        System.out.println("    - Large datasets (typically > 10,000 elements)");
        System.out.println("    - CPU-intensive operations");
        System.out.println("    - Independent operations");
        System.out.println("    - When performance testing shows improvement\n");
        
        // Demonstrate parallel streams
        System.out.println("Example: Parallel Streams");
        
        List<Integer> largeList = IntStream.range(1, 1000001)
            .boxed()
            .collect(Collectors.toList());
        
        // Sequential processing
        long startTime = System.currentTimeMillis();
        long sequentialSum = largeList.stream()
            .mapToLong(Integer::longValue)
            .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;
        
        // Parallel processing
        startTime = System.currentTimeMillis();
        long parallelSum = largeList.parallelStream()
            .mapToLong(Integer::longValue)
            .sum();
        long parallelTime = System.currentTimeMillis() - startTime;
        
        System.out.println("    Sequential sum: " + sequentialSum + " (Time: " + sequentialTime + "ms)");
        System.out.println("    Parallel sum: " + parallelSum + " (Time: " + parallelTime + "ms)");
        System.out.println("    Speedup: " + String.format("%.2f", (double) sequentialTime / parallelTime) + "x");
        
        // Parallel stream with custom thread pool
        System.out.println("    Custom thread pool example:");
        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        try {
            long customSum = customThreadPool.submit(() ->
                largeList.parallelStream()
                    .mapToLong(Integer::longValue)
                    .sum()
            ).get();
            System.out.println("    Custom thread pool sum: " + customSum);
        } catch (Exception e) {
            System.out.println("    Error in custom thread pool: " + e.getMessage());
        }
        
        // Parallel stream considerations
        System.out.println("    Parallel stream considerations:");
        System.out.println("    - Thread safety is important");
        System.out.println("    - Order may not be preserved");
        System.out.println("    - Overhead may not be worth it for small datasets");
        System.out.println("    - Use parallel streams carefully with stateful operations\n");
    }
    
    // ===== ADVANCED STREAMS =====
    
    private static void demonstrateAdvancedStreams() {
        System.out.println("6. ADVANCED STREAMS:\n");
        
        // Q12: What are custom collectors?
        System.out.println("Q12: What are custom collectors?");
        System.out.println("    Custom collectors allow you to create your own reduction operations");
        System.out.println("    by implementing the Collector interface.");
        System.out.println("    Useful for complex data transformations.\n");
        
        // Q13: How do you handle exceptions in streams?
        System.out.println("Q13: How do you handle exceptions in streams?");
        System.out.println("    - Use try-catch blocks in lambda expressions");
        System.out.println("    - Create wrapper methods that handle exceptions");
        System.out.println("    - Use Optional to handle potential failures");
        System.out.println("    - Consider using CompletableFuture for async operations\n");
        
        // Demonstrate advanced streams
        System.out.println("Example: Advanced Streams");
        
        // Custom collector for string concatenation with separator
        Collector<String, StringBuilder, String> customJoining = Collector.of(
            StringBuilder::new,                    // supplier
            (sb, str) -> sb.append(str).append("|"), // accumulator
            (sb1, sb2) -> sb1.append(sb2),       // combiner
            sb -> sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "" // finisher
        );
        
        String customJoined = Arrays.asList("A", "B", "C").stream()
            .collect(customJoining);
        System.out.println("    Custom joined: " + customJoined);
        
        // Stream with exception handling
        List<String> urls = Arrays.asList("http://example1.com", "invalid-url", "http://example2.com");
        List<String> validUrls = urls.stream()
            .filter(url -> {
                try {
                    new java.net.URL(url);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            })
            .collect(Collectors.toList());
        System.out.println("    Valid URLs: " + validUrls);
        
        // Stream with custom comparator
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineering"),
            new Person("Bob", 30, "Sales"),
            new Person("Charlie", 25, "Engineering")
        );
        
        List<Person> sortedPeople = people.stream()
            .sorted(Comparator
                .comparing(Person::getDepartment)
                .thenComparing(Person::getAge)
                .thenComparing(Person::getName))
            .collect(Collectors.toList());
        
        System.out.println("    Sorted people (dept, age, name):");
        sortedPeople.forEach(p -> 
            System.out.println("      " + p.getDepartment() + " - " + p.getAge() + " - " + p.getName())
        );
        
        // Stream with custom predicate
        Predicate<Person> seniorEngineer = p -> 
            "Engineering".equals(p.getDepartment()) && p.getAge() >= 30;
        
        List<Person> seniorEngineers = people.stream()
            .filter(seniorEngineer)
            .collect(Collectors.toList());
        System.out.println("    Senior engineers: " + seniorEngineers.size());
        
        // Stream with custom function
        Function<Person, String> personFormatter = p -> 
            String.format("%s (%d) - %s", p.getName(), p.getAge(), p.getDepartment());
        
        List<String> formattedPeople = people.stream()
            .map(personFormatter)
            .collect(Collectors.toList());
        System.out.println("    Formatted people: " + formattedPeople);
        
        // Stream with custom supplier
        Supplier<Random> randomSupplier = Random::new;
        List<Integer> randomNumbers = Stream.generate(randomSupplier)
            .limit(5)
            .map(Random::nextInt)
            .collect(Collectors.toList());
        System.out.println("    Random numbers: " + randomNumbers + "\n");
    }
    
    // ===== HELPER CLASSES =====
    
    static class Person {
        private String name;
        private int age;
        private String department;
        
        public Person(String name, int age, String department) {
            this.name = name;
            this.age = age;
            this.department = department;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getDepartment() { return department; }
        
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA STREAMS:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are Java Streams?
 * 2. What are the characteristics of streams?
 * 3. How do you create streams from collections?
 * 4. How do you create streams from arrays?
 * 5. What is the difference between stream() and parallelStream()?
 * 6. What are intermediate operations?
 * 7. What are terminal operations?
 * 8. What is the difference between map and flatMap?
 * 9. How do you filter elements in a stream?
 * 10. How do you sort elements in a stream?
 * 11. What is the limit() operation?
 * 12. What is the skip() operation?
 * 13. What is the distinct() operation?
 * 14. How do you count elements in a stream?
 * 15. How do you find the first element in a stream?
 * 16. How do you check if any element matches a condition?
 * 17. How do you check if all elements match a condition?
 * 18. How do you check if no element matches a condition?
 * 19. What is the forEach operation?
 * 20. How do you collect stream results into a List?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. What are collectors in streams?
 * 22. How do you collect stream results into a Set?
 * 23. How do you collect stream results into a Map?
 * 24. How do you join strings in a stream?
 * 25. How do you group elements by a property?
 * 26. How do you partition elements by a condition?
 * 27. How do you calculate statistics from a stream?
 * 28. How do you find the maximum element in a stream?
 * 29. How do you find the minimum element in a stream?
 * 30. How do you calculate the sum of numeric elements?
 * 31. How do you calculate the average of numeric elements?
 * 32. What is the reduce operation?
 * 33. How do you handle null values in streams?
 * 34. How do you combine multiple streams?
 * 35. How do you create infinite streams?
 * 36. How do you handle exceptions in streams?
 * 37. What is the difference between findFirst and findAny?
 * 38. How do you use custom comparators in streams?
 * 39. How do you use custom predicates in streams?
 * 40. How do you use custom functions in streams?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. What are custom collectors?
 * 42. How do you implement a custom collector?
 * 43. How do you handle stateful operations in streams?
 * 44. How do you optimize stream performance?
 * 45. When should you use parallel streams?
 * 46. What are the limitations of parallel streams?
 * 47. How do you create custom stream sources?
 * 48. How do you implement stream builders?
 * 49. How do you handle concurrent modifications in streams?
 * 50. How do you implement custom stream operations?
 * 51. How do you use streams with CompletableFuture?
 * 52. How do you implement reactive streams?
 * 53. How do you handle backpressure in streams?
 * 54. How do you implement stream caching?
 * 55. How do you implement stream memoization?
 * 56. How do you implement stream validation?
 * 57. How do you implement stream transformation?
 * 58. How do you implement stream aggregation?
 * 59. How do you implement stream composition?
 * 60. How do you implement stream testing?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you implement a custom stream framework?
 * 62. How do you implement stream serialization?
 * 63. How do you implement stream persistence?
 * 64. How do you implement stream caching strategies?
 * 65. How do you implement stream validation frameworks?
 * 66. How do you implement stream transformation engines?
 * 67. How do you implement stream aggregation systems?
 * 68. How do you implement stream composition frameworks?
 * 69. How do you implement stream testing frameworks?
 * 70. How do you implement stream monitoring?
 * 71. How do you implement stream debugging?
 * 72. How do you implement stream profiling?
 * 73. How do you implement stream optimization?
 * 74. How do you implement stream security?
 * 75. How do you implement stream authentication?
 * 76. How do you implement stream authorization?
 * 77. How do you implement stream encryption?
 * 78. How do you implement stream compression?
 * 79. How do you implement stream decompression?
 * 80. How do you implement stream integrity checking?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a stream processing system?
 * 82. How would you implement a stream analytics platform?
 * 83. How would you design a stream data warehouse?
 * 84. How would you implement a stream ETL system?
 * 85. How would you design a stream machine learning pipeline?
 * 86. How would you implement a stream recommendation system?
 * 87. How would you design a stream fraud detection system?
 * 88. How would you implement a stream anomaly detection system?
 * 89. How would you design a stream monitoring dashboard?
 * 90. How would you implement a stream alerting system?
 * 91. How would you design a stream data governance system?
 * 92. How would you implement a stream data quality system?
 * 93. How would you design a stream data lineage system?
 * 94. How would you implement a stream data catalog?
 * 95. How would you design a stream data marketplace?
 * 96. How would you implement a stream data sharing platform?
 * 97. How would you design a stream data collaboration system?
 * 98. How would you implement a stream data versioning system?
 * 99. How would you design a stream data backup system?
 * 100. How would you implement a stream data recovery system?
 */
