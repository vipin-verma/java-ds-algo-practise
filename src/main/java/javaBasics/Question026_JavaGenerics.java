import java.util.*;

/**
 * Question 26: Java Generics
 * 
 * This file contains 50+ Generics interview questions covering:
 * - Generic Classes, Methods, and Interfaces
 * - Wildcards and Bounded Generics
 * - Type Erasure and Type Safety
 * - Generic Collections and Arrays
 * - Advanced Generic Concepts and Best Practices
 */
public class Question026_JavaGenerics {
    
    public static void main(String[] args) {
        System.out.println("=== Java Generics - Interview Questions ===\n");
        
        demonstrateGenericClasses();
        demonstrateGenericMethods();
        demonstrateGenericInterfaces();
        demonstrateWildcards();
        demonstrateBoundedGenerics();
        demonstrateTypeErasure();
        demonstrateGenericCollections();
        demonstrateAdvancedGenerics();
        
        System.out.println("\n=== Java Generics Completed! ===");
    }
    
    // ===== GENERIC CLASSES =====
    
    private static void demonstrateGenericClasses() {
        System.out.println("1. GENERIC CLASSES:\n");
        
        // Q1: What are generics in Java?
        System.out.println("Q1: What are generics in Java?");
        System.out.println("    Generics allow you to create classes, interfaces, and methods");
        System.out.println("    that operate on types specified as parameters.");
        System.out.println("    They provide compile-time type safety and eliminate type casting.\n");
        
        // Q2: What is a generic class?
        System.out.println("Q2: What is a generic class?");
        System.out.println("    A generic class is a class that can work with different data types");
        System.out.println("    while maintaining type safety.");
        System.out.println("    Syntax: class ClassName<T> { ... }\n");
        
        // Q3: What are the benefits of using generics?
        System.out.println("Q3: What are the benefits of using generics?");
        System.out.println("    Type safety at compile time");
        System.out.println("    Elimination of type casting");
        System.out.println("    Better code reusability");
        System.out.println("    Improved performance (no boxing/unboxing)\n");
        
        // Demonstrate generic class
        System.out.println("Example: Generic Box Class");
        Box<String> stringBox = new Box<>("Hello Generics!");
        Box<Integer> intBox = new Box<>(42);
        Box<Double> doubleBox = new Box<>(3.14);
        
        System.out.println("    String box: " + stringBox.get());
        System.out.println("    Integer box: " + intBox.get());
        System.out.println("    Double box: " + doubleBox.get());
        
        // Demonstrate generic class with multiple type parameters
        System.out.println("Example: Generic Pair Class");
        Pair<String, Integer> pair1 = new Pair<>("Age", 25);
        Pair<Double, Boolean> pair2 = new Pair<>(3.14, true);
        
        System.out.println("    Pair 1: " + pair1.getFirst() + " -> " + pair1.getSecond());
        System.out.println("    Pair 2: " + pair2.getFirst() + " -> " + pair2.getSecond() + "\n");
    }
    
    // ===== GENERIC METHODS =====
    
    private static void demonstrateGenericMethods() {
        System.out.println("2. GENERIC METHODS:\n");
        
        // Q4: What is a generic method?
        System.out.println("Q4: What is a generic method?");
        System.out.println("    A generic method is a method that can work with different types");
        System.out.println("    while maintaining type safety.");
        System.out.println("    Syntax: <T> returnType methodName(T parameter) { ... }\n");
        
        // Q5: Can you have generic methods in non-generic classes?
        System.out.println("Q5: Can you have generic methods in non-generic classes?");
        System.out.println("    Yes, you can have generic methods in non-generic classes.");
        System.out.println("    The generic type parameter is declared in the method signature.");
        System.out.println("    Example: public static <T> void printArray(T[] array)\n");
        
        // Q6: What is type inference in generics?
        System.out.println("Q6: What is type inference in generics?");
        System.out.println("    Type inference allows the compiler to automatically determine");
        System.out.println("    the generic type based on the context.");
        System.out.println("    Example: List<String> list = new ArrayList<>();\n");
        
        // Demonstrate generic methods
        System.out.println("Example: Generic Methods");
        
        // Generic method to print array
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"Hello", "World", "Generics"};
        Double[] doubleArray = {1.1, 2.2, 3.3};
        
        System.out.println("    Integer array:");
        printArray(intArray);
        
        System.out.println("    String array:");
        printArray(stringArray);
        
        System.out.println("    Double array:");
        printArray(doubleArray);
        
        // Generic method to find maximum
        System.out.println("    Maximum of 5, 3, 8: " + findMax(5, 3, 8));
        System.out.println("    Maximum of 'apple', 'banana', 'cherry': " + 
                         findMax("apple", "banana", "cherry"));
        
        // Generic method with wildcard
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<String> stringList = Arrays.asList("a", "b", "c");
        
        System.out.println("    Integer list size: " + getSize(intList));
        System.out.println("    String list size: " + getSize(stringList) + "\n");
    }
    
    // ===== GENERIC INTERFACES =====
    
    private static void demonstrateGenericInterfaces() {
        System.out.println("3. GENERIC INTERFACES:\n");
        
        // Q7: What is a generic interface?
        System.out.println("Q7: What is a generic interface?");
        System.out.println("    A generic interface is an interface that can work with different types");
        System.out.println("    while maintaining type safety.");
        System.out.println("    Syntax: interface InterfaceName<T> { ... }\n");
        
        // Q8: How do you implement a generic interface?
        System.out.println("Q8: How do you implement a generic interface?");
        System.out.println("    You can implement a generic interface with a specific type");
        System.out.println("    or keep it generic in the implementing class.");
        System.out.println("    Example: class MyClass implements MyInterface<String>\n");
        
        // Q9: Can you have multiple type parameters in interfaces?
        System.out.println("Q9: Can you have multiple type parameters in interfaces?");
        System.out.println("    Yes, interfaces can have multiple type parameters.");
        System.out.println("    Syntax: interface InterfaceName<T, U> { ... }");
        System.out.println("    Example: Map<K, V> interface\n");
        
        // Demonstrate generic interfaces
        System.out.println("Example: Generic Interfaces");
        
        // Implementing generic interface with specific type
        StringProcessor stringProcessor = new StringProcessor();
        System.out.println("    Processing string: " + stringProcessor.process("Hello Generics!"));
        
        // Implementing generic interface with generic type
        NumberProcessor<Integer> intProcessor = new NumberProcessor<>();
        NumberProcessor<Double> doubleProcessor = new NumberProcessor<>();
        
        System.out.println("    Processing integer: " + intProcessor.process(42));
        System.out.println("    Processing double: " + doubleProcessor.process(3.14));
        
        // Generic interface with multiple type parameters
        DataProcessor<String, Integer> dataProcessor = new DataProcessor<>();
        String result = dataProcessor.process("Input", 100);
        System.out.println("    Data processing result: " + result + "\n");
    }
    
    // ===== WILDCARDS =====
    
    private static void demonstrateWildcards() {
        System.out.println("4. WILDCARDS:\n");
        
        // Q10: What are wildcards in generics?
        System.out.println("Q10: What are wildcards in generics?");
        System.out.println("    Wildcards allow you to work with unknown types in generics.");
        System.out.println("    They provide flexibility when you don't need to know the exact type.");
        System.out.println("    Types: ?, ? extends T, ? super T\n");
        
        // Q11: What is the difference between ? and ? extends T?
        System.out.println("Q11: What is the difference between ? and ? extends T?");
        System.out.println("    ? (unbounded wildcard): Accepts any type");
        System.out.println("    ? extends T (upper bounded): Accepts T or any subtype of T");
        System.out.println("    ? super T (lower bounded): Accepts T or any supertype of T\n");
        
        // Q12: When to use different types of wildcards?
        System.out.println("Q12: When to use different types of wildcards?");
        System.out.println("    ?: When you only need to read from the collection");
        System.out.println("    ? extends T: When you need to read and want to restrict to T or subtypes");
        System.out.println("    ? super T: When you need to write to the collection\n");
        
        // Demonstrate wildcards
        System.out.println("Example: Wildcards");
        
        // Unbounded wildcard
        List<String> stringList = Arrays.asList("Hello", "World");
        List<Integer> intList = Arrays.asList(1, 2, 3);
        
        System.out.println("    String list size: " + getSizeWithWildcard(stringList));
        System.out.println("    Integer list size: " + getSizeWithWildcard(intList));
        
        // Upper bounded wildcard
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        
        System.out.println("    Sum of integers: " + sumNumbers(numbers));
        System.out.println("    Sum of doubles: " + sumNumbers(doubles));
        
        // Lower bounded wildcard
        List<Number> numberList = new ArrayList<>();
        addNumbers(numberList, 1);
        addNumbers(numberList, 2.5);
        System.out.println("    Number list after adding: " + numberList + "\n");
    }
    
    // ===== BOUNDED GENERICS =====
    
    private static void demonstrateBoundedGenerics() {
        System.out.println("5. BOUNDED GENERICS:\n");
        
        // Q13: What are bounded generics?
        System.out.println("Q13: What are bounded generics?");
        System.out.println("    Bounded generics restrict the types that can be used");
        System.out.println("    as generic type parameters.");
        System.out.println("    Types: <T extends Class>, <T extends Interface>\n");
        
        // Q14: What is the difference between extends and super in bounds?
        System.out.println("Q14: What is the difference between extends and super in bounds?");
        System.out.println("    extends: Upper bound - T must be the specified type or a subtype");
        System.out.println("    super: Lower bound - T must be the specified type or a supertype");
        System.out.println("    extends: Used for reading, super: Used for writing\n");
        
        // Q15: Can you have multiple bounds?
        System.out.println("Q15: Can you have multiple bounds?");
        System.out.println("    Yes, you can have multiple bounds using the & operator.");
        System.out.println("    Syntax: <T extends Class1 & Interface1 & Interface2>");
        System.out.println("    Note: Only one class can be specified, multiple interfaces allowed\n");
        
        // Demonstrate bounded generics
        System.out.println("Example: Bounded Generics");
        
        // Upper bounded generic class
        NumberBox<Integer> intBox = new NumberBox<>(42);
        NumberBox<Double> doubleBox = new NumberBox<>(3.14);
        
        System.out.println("    Integer box value: " + intBox.getValue());
        System.out.println("    Double box value: " + doubleBox.getValue());
        System.out.println("    Integer box is positive: " + intBox.isPositive());
        System.out.println("    Double box is positive: " + doubleBox.isPositive());
        
        // Upper bounded generic method
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        
        System.out.println("    Average of integers: " + calculateAverage(intList));
        System.out.println("    Average of doubles: " + calculateAverage(doubleList));
        
        // Multiple bounds
        List<ComparableNumber> comparableNumbers = Arrays.asList(
            new ComparableNumber(5),
            new ComparableNumber(2),
            new ComparableNumber(8)
        );
        
        ComparableNumber max = findMaximum(comparableNumbers);
        System.out.println("    Maximum comparable number: " + max.getValue() + "\n");
    }
    
    // ===== TYPE ERASURE =====
    
    private static void demonstrateTypeErasure() {
        System.out.println("6. TYPE ERASURE:\n");
        
        // Q16: What is type erasure in Java generics?
        System.out.println("Q16: What is type erasure in Java generics?");
        System.out.println("    Type erasure is the process by which generic type information");
        System.out.println("    is removed at compile time and replaced with raw types.");
        System.out.println("    This ensures backward compatibility with pre-generics code.\n");
        
        // Q17: What happens to generic types at runtime?
        System.out.println("Q17: What happens to generic types at runtime?");
        System.out.println("    At runtime, all generic types are erased to their raw types.");
        System.out.println("    List<String> becomes List, T becomes Object");
        System.out.println("    Type checking is done at compile time only\n");
        
        // Q18: What are the limitations of type erasure?
        System.out.println("Q18: What are the limitations of type erasure?");
        System.out.println("    Cannot use instanceof with generic types");
        System.out.println("    Cannot create arrays of generic types");
        System.out.println("    Cannot use primitive types as generic type parameters");
        System.out.println("    Cannot catch generic exception types\n");
        
        // Demonstrate type erasure
        System.out.println("Example: Type Erasure");
        
        // Generic class with type erasure
        GenericClass<String> stringClass = new GenericClass<>("Hello");
        GenericClass<Integer> intClass = new GenericClass<>(42);
        
        System.out.println("    String class type: " + stringClass.getClass().getName());
        System.out.println("    Integer class type: " + intClass.getClass().getName());
        System.out.println("    Both classes have the same runtime type due to erasure");
        
        // Type erasure in collections
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        
        System.out.println("    String list runtime type: " + stringList.getClass().getName());
        System.out.println("    Integer list runtime type: " + intList.getClass().getName());
        System.out.println("    Both lists have the same runtime type: " + 
                         (stringList.getClass() == intList.getClass()));
        
        // Demonstrate instanceof limitation
        System.out.println("    String list is List: " + (stringList instanceof List));
        // System.out.println("    String list is List<String>: " + (stringList instanceof List<String>)); // Compilation error
        
        // Demonstrate array creation limitation
        // List<String>[] array = new List<String>[10]; // Compilation error
        List<?>[] wildcardArray = new List<?>[10]; // This works
        System.out.println("    Wildcard array created successfully: " + wildcardArray.length + "\n");
    }
    
    // ===== GENERIC COLLECTIONS =====
    
    private static void demonstrateGenericCollections() {
        System.out.println("7. GENERIC COLLECTIONS:\n");
        
        // Q19: How do generics work with collections?
        System.out.println("Q19: How do generics work with collections?");
        System.out.println("    Generics provide type safety for collections at compile time.");
        System.out.println("    They eliminate the need for type casting when retrieving elements.");
        System.out.println("    Examples: List<String>, Map<Integer, String>, Set<Double>\n");
        
        // Q20: What are the benefits of generic collections?
        System.out.println("Q20: What are the benefits of generic collections?");
        System.out.println("    Type safety at compile time");
        System.out.println("    No need for type casting");
        System.out.println("    Better code readability and maintainability");
        System.out.println("    Prevents ClassCastException at runtime\n");
        
        // Demonstrate generic collections
        System.out.println("Example: Generic Collections");
        
        // Generic List
        List<String> stringList = new ArrayList<>();
        stringList.add("Apple");
        stringList.add("Banana");
        stringList.add("Cherry");
        
        // No casting needed
        String first = stringList.get(0);
        System.out.println("    First fruit: " + first);
        
        // Generic Map
        Map<Integer, String> numberMap = new HashMap<>();
        numberMap.put(1, "One");
        numberMap.put(2, "Two");
        numberMap.put(3, "Three");
        
        // No casting needed
        String two = numberMap.get(2);
        System.out.println("    Number 2: " + two);
        
        // Generic Set
        Set<Double> doubleSet = new HashSet<>();
        doubleSet.add(1.1);
        doubleSet.add(2.2);
        doubleSet.add(3.3);
        
        // Generic Queue
        Queue<String> stringQueue = new LinkedList<>();
        stringQueue.offer("First");
        stringQueue.offer("Second");
        stringQueue.offer("Third");
        
        System.out.println("    Queue elements:");
        while (!stringQueue.isEmpty()) {
            System.out.println("      " + stringQueue.poll());
        }
        
        // Generic Stack (using Deque)
        Deque<Integer> intStack = new ArrayDeque<>();
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        
        System.out.println("    Stack elements (LIFO):");
        while (!intStack.isEmpty()) {
            System.out.println("      " + intStack.pop());
        }
        System.out.println();
    }
    
    // ===== ADVANCED GENERICS =====
    
    private static void demonstrateAdvancedGenerics() {
        System.out.println("8. ADVANCED GENERICS:\n");
        
        // Q21: What are recursive type bounds?
        System.out.println("Q21: What are recursive type bounds?");
        System.out.println("    Recursive type bounds allow a type parameter to be bounded");
        System.out.println("    by expressions involving the type parameter itself.");
        System.out.println("    Example: <T extends Comparable<T>>\n");
        
        // Q22: What are generic static methods?
        System.out.println("Q22: What are generic static methods?");
        System.out.println("    Generic static methods are methods that can work with different types");
        System.out.println("    without requiring the class to be generic.");
        System.out.println("    They are useful for utility methods.\n");
        
        // Demonstrate advanced generics
        System.out.println("Example: Advanced Generics");
        
        // Recursive type bounds
        List<ComparableNumber> numbers = Arrays.asList(
            new ComparableNumber(5),
            new ComparableNumber(2),
            new ComparableNumber(8),
            new ComparableNumber(1)
        );
        
        ComparableNumber min = findMinimum(numbers);
        ComparableNumber max = findMaximum(numbers);
        
        System.out.println("    Minimum: " + min.getValue());
        System.out.println("    Maximum: " + max.getValue());
        
        // Generic static methods
        String[] strings = {"Hello", "World", "Generics"};
        Integer[] integers = {1, 2, 3, 4, 5};
        
        System.out.println("    String array reversed:");
        reverseArray(strings);
        printArray(strings);
        
        System.out.println("    Integer array reversed:");
        reverseArray(integers);
        printArray(integers);
        
        // Generic method with constraints
        List<String> stringList = Arrays.asList("apple", "banana", "cherry");
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        
        System.out.println("    String list has duplicates: " + hasDuplicates(stringList));
        System.out.println("    Integer list has duplicates: " + hasDuplicates(intList));
        
        // Generic method with multiple constraints
        List<ComparableNumber> comparableList = Arrays.asList(
            new ComparableNumber(1),
            new ComparableNumber(2),
            new ComparableNumber(3)
        );
        
        boolean isSorted = isSorted(comparableList);
        System.out.println("    Comparable list is sorted: " + isSorted + "\n");
    }
    
    // ===== HELPER METHODS =====
    
    // Generic method to print array
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println("      " + element);
        }
    }
    
    // Generic method to find maximum
    public static <T extends Comparable<T>> T findMax(T a, T b, T c) {
        T max = a;
        if (b.compareTo(max) > 0) max = b;
        if (c.compareTo(max) > 0) max = c;
        return max;
    }
    
    // Generic method with wildcard
    public static int getSize(List<?> list) {
        return list.size();
    }
    
    // Generic method with upper bounded wildcard
    public static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }
    
    // Generic method with lower bounded wildcard
    public static void addNumbers(List<? super Number> list, Number number) {
        list.add(number);
    }
    
    // Generic method with upper bounded wildcard
    public static double calculateAverage(List<? extends Number> numbers) {
        if (numbers.isEmpty()) return 0.0;
        
        double sum = 0.0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum / numbers.size();
    }
    
    // Generic method with multiple bounds
    public static <T extends Comparable<T> & Number> T findMaximum(List<T> list) {
        if (list.isEmpty()) return null;
        
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }
    
    // Generic method with recursive type bounds
    public static <T extends Comparable<T>> T findMinimum(List<T> list) {
        if (list.isEmpty()) return null;
        
        T min = list.get(0);
        for (T item : list) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        return min;
    }
    
    // Generic static method to reverse array
    public static <T> void reverseArray(T[] array) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            T temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
    
    // Generic method with constraints
    public static <T> boolean hasDuplicates(List<T> list) {
        Set<T> set = new HashSet<>(list);
        return set.size() < list.size();
    }
    
    // Generic method with multiple constraints
    public static <T extends Comparable<T>> boolean isSorted(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }
    
    // ===== HELPER CLASSES =====
    
    // Generic class with single type parameter
    static class Box<T> {
        private T content;
        
        public Box(T content) {
            this.content = content;
        }
        
        public T get() {
            return content;
        }
        
        public void set(T content) {
            this.content = content;
        }
    }
    
    // Generic class with multiple type parameters
    static class Pair<T, U> {
        private T first;
        private U second;
        
        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
        
        public T getFirst() { return first; }
        public U getSecond() { return second; }
        
        public void setFirst(T first) { this.first = first; }
        public void setSecond(U second) { this.second = second; }
    }
    
    // Generic interface
    interface Processor<T> {
        T process(T input);
    }
    
    // Implementing generic interface with specific type
    static class StringProcessor implements Processor<String> {
        @Override
        public String process(String input) {
            return input.toUpperCase();
        }
    }
    
    // Implementing generic interface with generic type
    static class NumberProcessor<T extends Number> implements Processor<T> {
        @Override
        public T process(T input) {
            // This is a simplified example - in real implementation you'd do actual processing
            return input;
        }
    }
    
    // Generic interface with multiple type parameters
    interface MultiProcessor<T, U> {
        T process(T input, U parameter);
    }
    
    // Implementing multiple type parameter interface
    static class DataProcessor implements MultiProcessor<String, Integer> {
        @Override
        public String process(String input, Integer parameter) {
            return input + " processed with " + parameter;
        }
    }
    
    // Upper bounded generic class
    static class NumberBox<T extends Number> {
        private T value;
        
        public NumberBox(T value) {
            this.value = value;
        }
        
        public T getValue() {
            return value;
        }
        
        public boolean isPositive() {
            return value.doubleValue() > 0;
        }
    }
    
    // Class with multiple bounds
    static class ComparableNumber implements Comparable<ComparableNumber>, Number {
        private int value;
        
        public ComparableNumber(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
        
        @Override
        public int compareTo(ComparableNumber other) {
            return Integer.compare(this.value, other.value);
        }
        
        // Number interface methods (simplified)
        @Override
        public int intValue() { return value; }
        @Override
        public long longValue() { return value; }
        @Override
        public float floatValue() { return value; }
        @Override
        public double doubleValue() { return value; }
    }
    
    // Generic class for type erasure demonstration
    static class GenericClass<T> {
        private T value;
        
        public GenericClass(T value) {
            this.value = value;
        }
        
        public T getValue() {
            return value;
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA GENERICS:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are generics in Java?
 * 2. What is the purpose of using generics?
 * 3. What is a generic class and how do you create one?
 * 4. What is a generic method and how do you create one?
 * 5. What is a generic interface and how do you create one?
 * 6. What are the benefits of using generics?
 * 7. How do you specify generic type parameters?
 * 8. What is type inference in generics?
 * 9. How do you create generic collections?
 * 10. What is the diamond operator (<>)?
 * 11. How do you implement a generic interface?
 * 12. What are raw types and why should you avoid them?
 * 13. How do you create arrays of generic types?
 * 14. What is the difference between List and List<Object>?
 * 15. How do you use generics with constructors?
 * 16. What is the difference between generic classes and generic methods?
 * 17. How do you specify multiple type parameters?
 * 18. What is the difference between List<String> and List<Integer>?
 * 19. How do you use generics with static methods?
 * 20. What is the difference between generic and non-generic code?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. What are wildcards in generics?
 * 22. What is the difference between ? and ? extends T?
 * 23. What is the difference between ? extends T and ? super T?
 * 24. When should you use unbounded wildcards?
 * 25. When should you use upper bounded wildcards?
 * 26. When should you use lower bounded wildcards?
 * 27. What are bounded generics?
 * 28. How do you specify upper bounds in generics?
 * 29. How do you specify lower bounds in generics?
 * 30. Can you have multiple bounds in generics?
 * 31. What is the difference between extends and super in bounds?
 * 32. How do you implement bounded generics?
 * 33. What are the limitations of bounded generics?
 * 34. How do you use generics with inheritance?
 * 35. What is the difference between List<Number> and List<? extends Number>?
 * 36. How do you use generics with interfaces?
 * 37. What is the difference between generic and non-generic interfaces?
 * 38. How do you implement generic interfaces with specific types?
 * 39. How do you implement generic interfaces with generic types?
 * 40. What are the best practices for using generics?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. What is type erasure in Java generics?
 * 42. How does type erasure work internally?
 * 43. What happens to generic types at runtime?
 * 44. What are the limitations of type erasure?
 * 45. How do you work around type erasure limitations?
 * 46. What is the difference between compile-time and runtime type checking?
 * 47. How do you implement custom generic collections?
 * 48. How do you implement generic algorithms?
 * 49. What are recursive type bounds?
 * 50. How do you implement recursive type bounds?
 * 51. What are generic static methods?
 * 52. How do you implement generic static methods?
 * 53. What are the differences between generic and non-generic static methods?
 * 54. How do you use generics with reflection?
 * 55. What are the challenges of using generics with reflection?
 * 56. How do you implement generic factories?
 * 57. How do you implement generic builders?
 * 58. What are generic type tokens?
 * 59. How do you implement generic type tokens?
 * 60. What are the performance implications of generics?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you implement a custom generic framework?
 * 62. How do you implement generic serialization?
 * 63. How do you implement generic persistence?
 * 64. How do you implement generic caching?
 * 65. How do you implement generic validation?
 * 66. How do you implement generic transformation?
 * 67. How do you implement generic mapping?
 * 68. How do you implement generic filtering?
 * 69. How do you implement generic sorting?
 * 70. How do you implement generic searching?
 * 71. How do you implement generic algorithms?
 * 72. How do you implement generic data structures?
 * 73. How do you implement generic parsers?
 * 74. How do you implement generic formatters?
 * 75. How do you implement generic converters?
 * 76. How do you implement generic adapters?
 * 77. How do you implement generic decorators?
 * 78. How do you implement generic proxies?
 * 79. How do you implement generic observers?
 * 80. How do you implement generic commands?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a generic plugin system?
 * 82. How would you implement a generic dependency injection container?
 * 83. How would you design a generic validation framework?
 * 84. How would you implement a generic configuration system?
 * 85. How would you design a generic event system?
 * 86. How would you implement a generic workflow engine?
 * 87. How would you design a generic rule engine?
 * 88. How would you implement a generic state machine?
 * 89. How would you design a generic parser framework?
 * 90. How would you implement a generic compiler?
 * 91. How would you design a generic interpreter?
 * 92. How would you implement a generic virtual machine?
 * 93. How would you design a generic operating system?
 * 94. How would you implement a generic network protocol?
 * 95. How would you design a generic security system?
 * 96. How would you implement a generic encryption system?
 * 97. How would you design a generic database system?
 * 98. How would you implement a generic file system?
 * 99. How would you design a generic messaging system?
 * 100. How would you implement a generic monitoring system?
 */
