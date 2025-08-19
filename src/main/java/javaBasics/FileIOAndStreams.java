import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class FileIOAndStreams {
    
    public static void main(String[] args) {
        System.out.println("=== Java File I/O and Streams ===\n");
        
        // 1. Basic File Operations
        demonstrateBasicFileOperations();
        
        // 2. File Reading and Writing
        demonstrateFileReadingWriting();
        
        // 3. NIO.2 (Modern File Operations)
        demonstrateNIO2();
        
        // 4. Basic Streams
        demonstrateBasicStreams();
        
        // 5. Stream Operations
        demonstrateStreamOperations();
        
        // 6. Collectors
        demonstrateCollectors();
        
        // 7. Parallel Streams
        demonstrateParallelStreams();
        
        // 8. File Processing with Streams
        demonstrateFileProcessingWithStreams();
    }
    
    private static void demonstrateBasicFileOperations() {
        System.out.println("1. BASIC FILE OPERATIONS:");
        
        try {
            // Create a file
            File file = new File("test.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists");
            }
            
            // File properties
            System.out.println("File path: " + file.getAbsolutePath());
            System.out.println("File size: " + file.length() + " bytes");
            System.out.println("File readable: " + file.canRead());
            System.out.println("File writable: " + file.canWrite());
            System.out.println("File executable: " + file.canExecute());
            System.out.println("File is directory: " + file.isDirectory());
            System.out.println("File is file: " + file.isFile());
            
            // Directory operations
            File directory = new File("testdir");
            if (directory.mkdir()) {
                System.out.println("Directory created: " + directory.getName());
            }
            
            // List files in current directory
            File currentDir = new File(".");
            System.out.println("\nFiles in current directory:");
            File[] files = currentDir.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile()) {
                        System.out.println("File: " + f.getName());
                    } else if (f.isDirectory()) {
                        System.out.println("Directory: " + f.getName());
                    }
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void demonstrateFileReadingWriting() {
        System.out.println("2. FILE READING AND WRITING:");
        
        // Writing to file using FileWriter
        try (FileWriter writer = new FileWriter("sample.txt")) {
            writer.write("Hello Java!\n");
            writer.write("This is a sample file.\n");
            writer.write("We are learning File I/O.\n");
            System.out.println("File written successfully");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
        
        // Reading from file using FileReader
        try (FileReader reader = new FileReader("sample.txt")) {
            int character;
            System.out.println("\nFile content (character by character):");
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        // Reading using BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader("sample.txt"))) {
            String line;
            System.out.println("\n\nFile content (line by line):");
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line " + lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        // Writing using BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write("Line 1: Buffered writing\n");
            writer.write("Line 2: More efficient\n");
            writer.write("Line 3: Better performance\n");
            System.out.println("\nBuffered file written successfully");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
        
        // Reading using Scanner
        try (Scanner scanner = new Scanner(new File("output.txt"))) {
            System.out.println("\nReading with Scanner:");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Scanner read: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void demonstrateNIO2() {
        System.out.println("3. NIO.2 (MODERN FILE OPERATIONS):");
        
        try {
            // Create file using NIO.2
            Path path = Paths.get("nio_sample.txt");
            Files.write(path, Arrays.asList("Line 1", "Line 2", "Line 3"));
            System.out.println("File created using NIO.2");
            
            // Read all lines
            List<String> lines = Files.readAllLines(path);
            System.out.println("File content:");
            lines.forEach(System.out::println);
            
            // Check file properties
            System.out.println("\nFile properties:");
            System.out.println("Exists: " + Files.exists(path));
            System.out.println("Size: " + Files.size(path) + " bytes");
            System.out.println("Is readable: " + Files.isReadable(path));
            System.out.println("Is writable: " + Files.isWritable(path));
            System.out.println("Is executable: " + Files.isExecutable(path));
            System.out.println("Is regular file: " + Files.isRegularFile(path));
            System.out.println("Is directory: " + Files.isDirectory(path));
            
            // Copy file
            Path copyPath = Paths.get("nio_sample_copy.txt");
            Files.copy(path, copyPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("\nFile copied successfully");
            
            // Move file
            Path movePath = Paths.get("nio_sample_moved.txt");
            Files.move(copyPath, movePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully");
            
            // Delete file
            Files.delete(movePath);
            System.out.println("File deleted successfully");
            
        } catch (IOException e) {
            System.out.println("NIO.2 Error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void demonstrateBasicStreams() {
        System.out.println("4. BASIC STREAMS:");
        
        // Creating streams from collections
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        System.out.println("Original list: " + names);
        
        // Stream from list
        Stream<String> nameStream = names.stream();
        System.out.println("Stream created from list");
        
        // Stream from array
        String[] nameArray = {"Frank", "Grace", "Henry"};
        Stream<String> arrayStream = Arrays.stream(nameArray);
        System.out.println("Stream created from array");
        
        // Stream of values
        Stream<Integer> numberStream = Stream.of(1, 2, 3, 4, 5);
        System.out.println("Stream of numbers created");
        
        // Stream from range
        IntStream rangeStream = IntStream.range(1, 6);
        System.out.println("Range stream created");
        
        // Stream from iterate
        Stream<Integer> iterateStream = Stream.iterate(0, n -> n + 2).limit(5);
        System.out.println("Iterate stream created");
        
        // Stream from generate
        Stream<Double> generateStream = Stream.generate(Math::random).limit(3);
        System.out.println("Generate stream created");
        
        // Converting streams back to collections
        List<String> filteredNames = names.stream()
            .filter(name -> name.length() > 4)
            .collect(Collectors.toList());
        System.out.println("\nFiltered names (length > 4): " + filteredNames);
        
        System.out.println();
    }
    
    private static void demonstrateStreamOperations() {
        System.out.println("5. STREAM OPERATIONS:");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original numbers: " + numbers);
        
        // Filter operation
        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
        
        // Map operation
        List<Integer> doubledNumbers = numbers.stream()
            .map(n -> n * 2)
            .collect(Collectors.toList());
        System.out.println("Doubled numbers: " + doubledNumbers);
        
        // FlatMap operation
        List<List<Integer>> nestedLists = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9)
        );
        List<Integer> flattened = nestedLists.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println("Flattened list: " + flattened);
        
        // Distinct operation
        List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 3, 4, 5);
        List<Integer> distinct = withDuplicates.stream()
            .distinct()
            .collect(Collectors.toList());
        System.out.println("Distinct numbers: " + distinct);
        
        // Sorted operation
        List<Integer> unsorted = Arrays.asList(5, 2, 8, 1, 9, 3);
        List<Integer> sorted = unsorted.stream()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("Sorted numbers: " + sorted);
        
        // Limit and Skip operations
        List<Integer> limited = numbers.stream()
            .limit(5)
            .collect(Collectors.toList());
        System.out.println("Limited to 5: " + limited);
        
        List<Integer> skipped = numbers.stream()
            .skip(5)
            .collect(Collectors.toList());
        System.out.println("Skipped first 5: " + skipped);
        
        // Reduce operation
        int sum = numbers.stream()
            .reduce(0, Integer::sum);
        System.out.println("Sum of all numbers: " + sum);
        
        int product = numbers.stream()
            .reduce(1, (a, b) -> a * b);
        System.out.println("Product of all numbers: " + product);
        
        // Find operations
        Optional<Integer> firstEven = numbers.stream()
            .filter(n -> n % 2 == 0)
            .findFirst();
        System.out.println("First even number: " + firstEven.orElse(-1));
        
        Optional<Integer> anyEven = numbers.stream()
            .filter(n -> n % 2 == 0)
            .findAny();
        System.out.println("Any even number: " + anyEven.orElse(-1));
        
        // Match operations
        boolean allPositive = numbers.stream()
            .allMatch(n -> n > 0);
        System.out.println("All numbers positive: " + allPositive);
        
        boolean anyNegative = numbers.stream()
            .anyMatch(n -> n < 0);
        System.out.println("Any negative numbers: " + anyNegative);
        
        boolean noneNegative = numbers.stream()
            .noneMatch(n -> n < 0);
        System.out.println("No negative numbers: " + noneNegative);
        
        // Count operation
        long count = numbers.stream()
            .filter(n -> n % 2 == 0)
            .count();
        System.out.println("Count of even numbers: " + count);
        
        System.out.println();
    }
    
    private static void demonstrateCollectors() {
        System.out.println("6. COLLECTORS:");
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineer"),
            new Person("Bob", 30, "Manager"),
            new Person("Charlie", 35, "Engineer"),
            new Person("David", 28, "Designer"),
            new Person("Eve", 32, "Engineer")
        );
        
        System.out.println("People: " + people);
        
        // Collecting to List
        List<String> names = people.stream()
            .map(Person::getName)
            .collect(Collectors.toList());
        System.out.println("Names: " + names);
        
        // Collecting to Set
        Set<String> jobs = people.stream()
            .map(Person::getJob)
            .collect(Collectors.toSet());
        System.out.println("Unique jobs: " + jobs);
        
        // Collecting to Map
        Map<String, Integer> nameToAge = people.stream()
            .collect(Collectors.toMap(Person::getName, Person::getAge));
        System.out.println("Name to age mapping: " + nameToAge);
        
        // Grouping by
        Map<String, List<Person>> groupedByJob = people.stream()
            .collect(Collectors.groupingBy(Person::getJob));
        System.out.println("Grouped by job: " + groupedByJob);
        
        // Partitioning by
        Map<Boolean, List<Person>> partitionedByAge = people.stream()
            .collect(Collectors.partitioningBy(p -> p.getAge() > 30));
        System.out.println("Partitioned by age > 30: " + partitionedByAge);
        
        // Joining strings
        String allNames = people.stream()
            .map(Person::getName)
            .collect(Collectors.joining(", "));
        System.out.println("All names joined: " + allNames);
        
        // Summarizing statistics
        IntSummaryStatistics ageStats = people.stream()
            .mapToInt(Person::getAge)
            .summaryStatistics();
        System.out.println("Age statistics: " + ageStats);
        
        // Custom collector
        String customResult = people.stream()
            .map(Person::getName)
            .collect(Collectors.collectingAndThen(
                Collectors.joining(" | "),
                result -> "Names: [" + result + "]"
            ));
        System.out.println("Custom collector result: " + customResult);
        
        System.out.println();
    }
    
    private static void demonstrateParallelStreams() {
        System.out.println("7. PARALLEL STREAMS:");
        
        List<Integer> numbers = IntStream.range(1, 1000001).boxed().collect(Collectors.toList());
        System.out.println("Processing " + numbers.size() + " numbers");
        
        // Sequential processing
        long startTime = System.currentTimeMillis();
        long sequentialSum = numbers.stream()
            .mapToLong(Integer::longValue)
            .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;
        System.out.println("Sequential sum: " + sequentialSum + " (Time: " + sequentialTime + "ms)");
        
        // Parallel processing
        startTime = System.currentTimeMillis();
        long parallelSum = numbers.parallelStream()
            .mapToLong(Integer::longValue)
            .sum();
        long parallelTime = System.currentTimeMillis() - startTime;
        System.out.println("Parallel sum: " + parallelSum + " (Time: " + parallelTime + "ms)");
        
        // Performance comparison
        if (sequentialTime > 0 && parallelTime > 0) {
            double speedup = (double) sequentialTime / parallelTime;
            System.out.println("Speedup: " + String.format("%.2f", speedup) + "x");
        }
        
        // Parallel stream with custom thread pool
        System.out.println("\nCustom thread pool example:");
        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        try {
            long customSum = customThreadPool.submit(() ->
                numbers.parallelStream()
                    .mapToLong(Integer::longValue)
                    .sum()
            ).get();
            System.out.println("Custom thread pool sum: " + customSum);
        } catch (Exception e) {
            System.out.println("Error in custom thread pool: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void demonstrateFileProcessingWithStreams() {
        System.out.println("8. FILE PROCESSING WITH STREAMS:");
        
        try {
            // Create a sample file with numbers
            List<String> numberLines = Arrays.asList(
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10"
            );
            Files.write(Paths.get("numbers.txt"), numberLines);
            
            // Process file using streams
            System.out.println("Processing numbers.txt:");
            
            // Read and process lines
            try (Stream<String> lines = Files.lines(Paths.get("numbers.txt"))) {
                List<Integer> numbers = lines
                    .map(Integer::parseInt)
                    .filter(n -> n % 2 == 0)
                    .map(n -> n * n)
                    .collect(Collectors.toList());
                System.out.println("Even numbers squared: " + numbers);
            }
            
            // Process directory
            System.out.println("\nProcessing current directory:");
            try (Stream<Path> paths = Files.walk(Paths.get("."), 1)) {
                List<String> fileNames = paths
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(name -> name.endsWith(".txt"))
                    .collect(Collectors.toList());
                System.out.println("Text files in current directory: " + fileNames);
            }
            
            // Clean up
            Files.deleteIfExists(Paths.get("numbers.txt"));
            
        } catch (IOException e) {
            System.out.println("File processing error: " + e.getMessage());
        }
        
        System.out.println("\nFile I/O and Streams demonstration completed!");
    }
}

// Helper class for demonstrations
class Person {
    private String name;
    private int age;
    private String job;
    
    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getJob() { return job; }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", job='" + job + "'}";
    }
}
