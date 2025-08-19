import java.util.*;

/**
 * Question 23: Java Basics Fundamentals
 * 
 * This file contains 50+ fundamental Java interview questions covering:
 * - Variables and Data Types
 * - Operators and Expressions
 * - Control Statements
 * - Basic Syntax and Concepts
 * - Common Pitfalls and Best Practices
 */
public class Question023_JavaBasicsFundamentals {
    
    public static void main(String[] args) {
        System.out.println("=== Java Basics Fundamentals - Interview Questions ===\n");
        
        demonstrateBasicConcepts();
        demonstrateDataTypes();
        demonstrateOperators();
        demonstrateControlStatements();
        demonstrateCommonPitfalls();
        
        System.out.println("\n=== Java Basics Fundamentals Completed! ===");
    }
    
    // ===== BASIC CONCEPTS =====
    
    private static void demonstrateBasicConcepts() {
        System.out.println("1. BASIC CONCEPTS:\n");
        
        // Q1: What is Java?
        System.out.println("Q1: What is Java?");
        System.out.println("    Java is a high-level, object-oriented programming language");
        System.out.println("    that runs on the Java Virtual Machine (JVM).");
        System.out.println("    Key features: Platform independence, Object-oriented, Secure, Robust.\n");
        
        // Q2: What is JVM?
        System.out.println("Q2: What is JVM?");
        System.out.println("    JVM (Java Virtual Machine) is an abstract machine that provides");
        System.out.println("    runtime environment to execute Java bytecode.");
        System.out.println("    It makes Java platform-independent.\n");
        
        // Q3: What is JDK vs JRE?
        System.out.println("Q3: What is JDK vs JRE?");
        System.out.println("    JDK (Java Development Kit): Contains JRE + development tools");
        System.out.println("    JRE (Java Runtime Environment): Contains JVM + libraries to run Java apps\n");
        
        // Q4: What is the main method?
        System.out.println("Q4: What is the main method?");
        System.out.println("    public static void main(String[] args)");
        System.out.println("    Entry point of Java application");
        System.out.println("    Must be public, static, void, and named 'main'\n");
        
        // Q5: What are packages?
        System.out.println("Q5: What are packages?");
        System.out.println("    Packages are containers for classes, interfaces, and sub-packages");
        System.out.println("    They provide namespace management and access control");
        System.out.println("    Example: java.util, java.io, com.company.project\n");
    }
    
    // ===== DATA TYPES =====
    
    private static void demonstrateDataTypes() {
        System.out.println("2. DATA TYPES:\n");
        
        // Q6: What are primitive data types?
        System.out.println("Q6: What are primitive data types?");
        System.out.println("    byte (8-bit), short (16-bit), int (32-bit), long (64-bit)");
        System.out.println("    float (32-bit), double (64-bit)");
        System.out.println("    char (16-bit Unicode), boolean (true/false)\n");
        
        // Q7: What is the difference between int and Integer?
        System.out.println("Q7: What is the difference between int and Integer?");
        System.out.println("    int: Primitive data type, stored in stack, cannot be null");
        System.out.println("    Integer: Wrapper class, stored in heap, can be null");
        System.out.println("    Integer provides methods like parseInt(), toString()\n");
        
        // Q8: What is autoboxing and unboxing?
        System.out.println("Q8: What is autoboxing and unboxing?");
        System.out.println("    Autoboxing: Automatic conversion of primitive to wrapper");
        System.out.println("    Unboxing: Automatic conversion of wrapper to primitive");
        System.out.println("    Example: Integer i = 10; int x = i;\n");
        
        // Q9: What is the default value of primitive types?
        System.out.println("Q9: What is the default value of primitive types?");
        System.out.println("    Numeric types: 0");
        System.out.println("    boolean: false");
        System.out.println("    char: '\\u0000' (null character)");
        System.out.println("    Reference types: null\n");
        
        // Q10: What is the size of different data types?
        System.out.println("Q10: What is the size of different data types?");
        System.out.println("    byte: 1 byte, short: 2 bytes, int: 4 bytes");
        System.out.println("    long: 8 bytes, float: 4 bytes, double: 8 bytes");
        System.out.println("    char: 2 bytes, boolean: 1 bit (implementation dependent)\n");
    }
    
    // ===== OPERATORS =====
    
    private static void demonstrateOperators() {
        System.out.println("3. OPERATORS:\n");
        
        // Q11: What are arithmetic operators?
        System.out.println("Q11: What are arithmetic operators?");
        System.out.println("    +, -, *, /, % (modulo)");
        System.out.println("    ++ (increment), -- (decrement)");
        System.out.println("    +=, -=, *=, /=, %= (compound assignment)\n");
        
        // Q12: What are comparison operators?
        System.out.println("Q12: What are comparison operators?");
        System.out.println("    == (equal), != (not equal)");
        System.out.println("    <, >, <=, >= (relational operators)");
        System.out.println("    instanceof (type checking)\n");
        
        // Q13: What are logical operators?
        System.out.println("Q13: What are logical operators?");
        System.out.println("    && (AND), || (OR), ! (NOT)");
        System.out.println("    & (bitwise AND), | (bitwise OR), ^ (bitwise XOR)");
        System.out.println("    Short-circuit evaluation: && and ||\n");
        
        // Q14: What is the difference between == and equals()?
        System.out.println("Q14: What is the difference between == and equals()?");
        System.out.println("    ==: Compares object references (memory addresses)");
        System.out.println("    equals(): Compares object content (overridden method)");
        System.out.println("    For String: == checks reference, equals() checks content\n");
        
        // Q15: What is the ternary operator?
        System.out.println("Q15: What is the ternary operator?");
        System.out.println("    condition ? value1 : value2");
        System.out.println("    Shorthand for if-else statement");
        System.out.println("    Example: int max = (a > b) ? a : b;\n");
    }
    
    // ===== CONTROL STATEMENTS =====
    
    private static void demonstrateControlStatements() {
        System.out.println("4. CONTROL STATEMENTS:\n");
        
        // Q16: What are the different types of loops?
        System.out.println("Q16: What are the different types of loops?");
        System.out.println("    for loop: for(initialization; condition; increment)");
        System.out.println("    while loop: while(condition)");
        System.out.println("    do-while loop: do { } while(condition)");
        System.out.println("    for-each loop: for(Type item : collection)\n");
        
        // Q17: What is the difference between break and continue?
        System.out.println("Q17: What is the difference between break and continue?");
        System.out.println("    break: Exits the loop or switch statement");
        System.out.println("    continue: Skips current iteration and continues to next");
        System.out.println("    break exits completely, continue skips current iteration\n");
        
        // Q18: What are labeled statements?
        System.out.println("Q18: What are labeled statements?");
        System.out.println("    Labels allow break/continue to target specific loops");
        System.out.println("    outer: for(int i = 0; i < 3; i++) {");
        System.out.println("        for(int j = 0; j < 3; j++) {");
        System.out.println("            if(i == 1 && j == 1) break outer;");
        System.out.println("        }");
        System.out.println("    }\n");
        
        // Q19: What is the switch statement?
        System.out.println("Q19: What is the switch statement?");
        System.out.println("    switch(expression) {");
        System.out.println("        case value1: statement; break;");
        System.out.println("        case value2: statement; break;");
        System.out.println("        default: statement;");
        System.out.println("    }");
        System.out.println("    Expression must be byte, short, char, int, enum, or String\n");
        
        // Q20: What is the enhanced switch (Java 14+)?
        System.out.println("Q20: What is the enhanced switch (Java 14+)?");
        System.out.println("    switch(expression) {");
        System.out.println("        case value1 -> statement;");
        System.out.println("        case value2 -> statement;");
        System.out.println("        default -> statement;");
        System.out.println("    }");
        System.out.println("    Arrow syntax, no break needed, can return values\n");
    }
    
    // ===== COMMON PITFALLS =====
    
    private static void demonstrateCommonPitfalls() {
        System.out.println("5. COMMON PITFALLS:\n");
        
        // Q21: What is the difference between String and StringBuilder?
        System.out.println("Q21: What is the difference between String and StringBuilder?");
        System.out.println("    String: Immutable, thread-safe, creates new object on modification");
        System.out.println("    StringBuilder: Mutable, not thread-safe, modifies existing object");
        System.out.println("    Use StringBuilder for multiple string concatenations\n");
        
        // Q22: What is the difference between == and equals() for String?
        System.out.println("Q22: What is the difference between == and equals() for String?");
        System.out.println("    String s1 = \"Hello\"; String s2 = \"Hello\";");
        System.out.println("    s1 == s2: true (string pool)");
        System.out.println("    String s3 = new String(\"Hello\");");
        System.out.println("    s1 == s3: false (different objects)\n");
        
        // Q23: What is the problem with floating-point arithmetic?
        System.out.println("Q23: What is the problem with floating-point arithmetic?");
        System.out.println("    double d = 0.1 + 0.2; // Result: 0.30000000000000004");
        System.out.println("    Use BigDecimal for precise decimal calculations");
        System.out.println("    Floating-point numbers are approximations\n");
        
        // Q24: What is the difference between & and &&?
        System.out.println("Q24: What is the difference between & and &&?");
        System.out.println("    &: Bitwise AND, always evaluates both operands");
        System.out.println("    &&: Logical AND, short-circuit evaluation");
        System.out.println("    if(a != null && a.length() > 0) // Safe\n");
        
        // Q25: What is the problem with integer division?
        System.out.println("Q25: What is the problem with integer division?");
        System.out.println("    int a = 5, b = 2;");
        System.out.println("    int result = a / b; // Result: 2 (not 2.5)");
        System.out.println("    double result = (double) a / b; // Result: 2.5\n");
    }
    
    // ===== PRACTICAL EXAMPLES =====
    
    private static void demonstratePracticalExamples() {
        System.out.println("6. PRACTICAL EXAMPLES:\n");
        
        // Example 1: Variable scope
        System.out.println("Example 1: Variable Scope");
        int globalVar = 10;
        {
            int localVar = 20;
            System.out.println("    Global: " + globalVar + ", Local: " + localVar);
        }
        // System.out.println(localVar); // Compilation error
        System.out.println("    Global: " + globalVar + "\n");
        
        // Example 2: Type conversion
        System.out.println("Example 2: Type Conversion");
        int intValue = 100;
        long longValue = intValue; // Widening (automatic)
        double doubleValue = intValue; // Widening (automatic)
        
        double d = 100.04;
        int i = (int) d; // Narrowing (explicit cast)
        System.out.println("    Int to Long: " + longValue);
        System.out.println("    Int to Double: " + doubleValue);
        System.out.println("    Double to Int: " + i + "\n");
        
        // Example 3: String operations
        System.out.println("Example 3: String Operations");
        String str1 = "Hello";
        String str2 = "World";
        String result = str1 + " " + str2; // String concatenation
        System.out.println("    Concatenation: " + result);
        System.out.println("    Length: " + result.length());
        System.out.println("    Uppercase: " + result.toUpperCase() + "\n");
        
        // Example 4: Array basics
        System.out.println("Example 4: Array Basics");
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("    Array length: " + numbers.length);
        System.out.println("    First element: " + numbers[0]);
        System.out.println("    Last element: " + numbers[numbers.length - 1] + "\n");
        
        // Example 5: Loop examples
        System.out.println("Example 5: Loop Examples");
        System.out.println("    For loop:");
        for (int j = 0; j < 3; j++) {
            System.out.println("      Iteration " + j);
        }
        
        System.out.println("    While loop:");
        int k = 0;
        while (k < 3) {
            System.out.println("      While iteration " + k);
            k++;
        }
        
        System.out.println("    For-each loop:");
        for (int num : numbers) {
            System.out.println("      Number: " + num);
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA BASICS FUNDAMENTALS:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What is Java and what are its key features?
 * 2. Explain the difference between JDK, JRE, and JVM.
 * 3. What is the main method and why is it important?
 * 4. What are packages and why do we use them?
 * 5. Explain the concept of platform independence in Java.
 * 6. What are the primitive data types in Java?
 * 7. What is the difference between int and Integer?
 * 8. Explain autoboxing and unboxing with examples.
 * 9. What are the default values of primitive data types?
 * 10. What is the size of different data types in Java?
 * 11. What are arithmetic operators and how do they work?
 * 12. Explain comparison operators with examples.
 * 13. What are logical operators and their precedence?
 * 14. What is the difference between == and equals()?
 * 15. Explain the ternary operator with examples.
 * 16. What are the different types of loops in Java?
 * 17. Explain the difference between break and continue.
 * 18. What are labeled statements and when to use them?
 * 19. How does the switch statement work?
 * 20. What is the enhanced switch statement (Java 14+)?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. What is the difference between String and StringBuilder?
 * 22. Explain string pool and string interning.
 * 23. What are the problems with floating-point arithmetic?
 * 24. Explain the difference between & and && operators.
 * 25. What are the issues with integer division?
 * 26. How do you handle null values safely?
 * 27. What is the difference between final, finally, and finalize?
 * 28. Explain the concept of variable scope in Java.
 * 29. What are the different types of type conversion?
 * 30. How do you create and use arrays in Java?
 * 31. What is the difference between arrays and ArrayList?
 * 32. How do you handle exceptions in Java?
 * 33. What are checked and unchecked exceptions?
 * 34. Explain the try-with-resources statement.
 * 35. What is the difference between throw and throws?
 * 36. How do you create custom exceptions?
 * 37. What is the difference between == and equals() for objects?
 * 38. How do you implement equals() and hashCode() methods?
 * 39. What is the toString() method and when to override it?
 * 40. Explain the clone() method and its implementation.
 * 
 * ADVANCED LEVEL (41-60):
 * 41. What is the difference between shallow and deep copying?
 * 42. How do you implement a singleton pattern?
 * 43. What is the difference between static and instance methods?
 * 44. Explain the concept of method overloading and overriding.
 * 45. What is the difference between abstract classes and interfaces?
 * 46. How do you implement the builder pattern?
 * 47. What is the difference between composition and inheritance?
 * 48. How do you implement the factory pattern?
 * 49. Explain the concept of polymorphism in Java.
 * 50. What is the difference between early and late binding?
 * 51. How do you implement the observer pattern?
 * 52. What is the difference between synchronized and volatile?
 * 53. How do you implement thread-safe singleton?
 * 54. What is the difference between wait() and sleep()?
 * 55. How do you implement producer-consumer pattern?
 * 56. What is the difference between HashMap and Hashtable?
 * 57. How do you implement custom collections?
 * 58. What is the difference between fail-fast and fail-safe iterators?
 * 59. How do you implement custom comparators?
 * 60. What is the difference between Comparable and Comparator?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you implement a custom ClassLoader?
 * 62. What is the difference between weak and soft references?
 * 63. How do you implement custom annotations?
 * 64. What is the difference between compile-time and runtime annotations?
 * 65. How do you implement custom annotation processors?
 * 66. What is the difference between reflection and introspection?
 * 67. How do you implement dynamic proxies?
 * 68. What is the difference between method handles and reflection?
 * 69. How do you implement custom bytecode generation?
 * 70. What is the difference between JIT and AOT compilation?
 * 71. How do you implement custom garbage collection?
 * 72. What is the difference between serialization and externalization?
 * 73. How do you implement custom serialization?
 * 74. What is the difference between transient and volatile?
 * 75. How do you implement custom security managers?
 * 76. What is the difference between class loading and linking?
 * 77. How do you implement custom thread pools?
 * 78. What is the difference between ReentrantLock and synchronized?
 * 79. How do you implement custom concurrent collections?
 * 80. What is the difference between CompletableFuture and Future?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a caching system in Java?
 * 82. Design a thread-safe object pool implementation.
 * 83. How would you implement a custom ORM framework?
 * 84. Design a plugin architecture using reflection.
 * 85. How would you implement a custom dependency injection container?
 * 86. Design a custom logging framework.
 * 87. How would you implement a custom validation framework?
 * 88. Design a custom configuration management system.
 * 89. How would you implement a custom event system?
 * 90. Design a custom workflow engine.
 * 91. How would you implement a custom rule engine?
 * 92. Design a custom message queue system.
 * 93. How would you implement a custom load balancer?
 * 94. Design a custom circuit breaker pattern.
 * 95. How would you implement a custom rate limiter?
 * 96. Design a custom distributed lock implementation.
 * 97. How would you implement a custom service discovery?
 * 98. Design a custom API gateway.
 * 99. How would you implement a custom monitoring system?
 * 100. Design a custom testing framework.
 */
