/**
 * Question 35: Java Exception Handling
 * 
 * This file contains Exception Handling interview questions covering:
 * - Exception Types and Hierarchy
 * - Try-Catch-Finally Blocks
 * - Custom Exceptions
 * - Exception Propagation
 * - Best Practices and Advanced Concepts
 */
public class Question035_JavaExceptionHandling {
    
    public static void main(String[] args) {
        System.out.println("=== Java Exception Handling - Interview Questions ===\n");
        
        demonstrateExceptionTypes();
        demonstrateTryCatchFinally();
        demonstrateCustomExceptions();
        demonstrateExceptionPropagation();
        demonstrateBestPractices();
        
        System.out.println("\n=== Java Exception Handling Completed! ===");
    }
    
    private static void demonstrateExceptionTypes() {
        System.out.println("1. EXCEPTION TYPES AND HIERARCHY:\n");
        
        // Q1: What are the main types of Exceptions in Java?
        System.out.println("Q1: What are the main types of Exceptions in Java?");
        System.out.println("    - Checked Exceptions (Compile-time exceptions)");
        System.out.println("    - Unchecked Exceptions (Runtime exceptions)");
        System.out.println("    - Errors (System-level problems)\n");
        
        // Q2: What is the difference between Checked and Unchecked Exceptions?
        System.out.println("Q2: What is the difference between Checked and Unchecked Exceptions?");
        System.out.println("    Checked: Must be handled or declared (IOException, SQLException)");
        System.out.println("    Unchecked: Don't need explicit handling (RuntimeException, NullPointerException)\n");
        
        // Demonstrate exception types
        System.out.println("Example: Exception Types");
        
        try {
            // Checked exception - must be handled
            throw new CheckedException("This is a checked exception");
        } catch (CheckedException e) {
            System.out.println("    Caught checked exception: " + e.getMessage());
        }
        
        try {
            // Unchecked exception - RuntimeException
            throw new UncheckedException("This is an unchecked exception");
        } catch (UncheckedException e) {
            System.out.println("    Caught unchecked exception: " + e.getMessage());
        }
        
        // Error example (usually not caught)
        try {
            throw new OutOfMemoryError("Memory error");
        } catch (Error e) {
            System.out.println("    Caught error: " + e.getMessage());
        }
    }
    
    private static void demonstrateTryCatchFinally() {
        System.out.println("\n2. TRY-CATCH-FINALLY BLOCKS:\n");
        
        // Q3: What is the purpose of try-catch-finally blocks?
        System.out.println("Q3: What is the purpose of try-catch-finally blocks?");
        System.out.println("    try: Contains code that might throw exceptions");
        System.out.println("    catch: Handles specific exceptions");
        System.out.println("    finally: Always executes, used for cleanup\n");
        
        // Q4: What is the order of execution in try-catch-finally?
        System.out.println("Q4: What is the order of execution in try-catch-finally?");
        System.out.println("    1. try block executes");
        System.out.println("    2. If exception occurs, catch block executes");
        System.out.println("    3. finally block always executes\n");
        
        // Demonstrate try-catch-finally
        System.out.println("Example: Try-Catch-Finally");
        
        // Normal execution
        System.out.println("    Normal execution:");
        try {
            int result = 10 / 2;
            System.out.println("      Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("      Caught: " + e.getMessage());
        } finally {
            System.out.println("      Finally block executed");
        }
        
        // Exception execution
        System.out.println("    Exception execution:");
        try {
            int result = 10 / 0; // This will throw ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("      Caught: " + e.getMessage());
        } finally {
            System.out.println("      Finally block executed");
        }
        
        // Multiple catch blocks
        System.out.println("    Multiple catch blocks:");
        try {
            String str = null;
            str.length(); // This will throw NullPointerException
        } catch (ArithmeticException e) {
            System.out.println("      Caught ArithmeticException: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("      Caught NullPointerException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("      Caught general Exception: " + e.getMessage());
        } finally {
            System.out.println("      Finally block executed");
        }
    }
    
    private static void demonstrateCustomExceptions() {
        System.out.println("\n3. CUSTOM EXCEPTIONS:\n");
        
        // Q5: How do you create Custom Exceptions?
        System.out.println("Q5: How do you create Custom Exceptions?");
        System.out.println("    - Extend Exception for checked exceptions");
        System.out.println("    - Extend RuntimeException for unchecked exceptions");
        System.out.println("    - Provide constructors and custom methods\n");
        
        // Q6: When should you use Custom Exceptions?
        System.out.println("Q6: When should you use Custom Exceptions?");
        System.out.println("    - Business logic exceptions");
        System.out.println("    - Application-specific error conditions");
        System.out.println("    - Better error handling and debugging\n");
        
        // Demonstrate custom exceptions
        System.out.println("Example: Custom Exceptions");
        
        try {
            // Using custom checked exception
            validateAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("    Caught custom exception: " + e.getMessage());
            System.out.println("    Error code: " + e.getErrorCode());
        }
        
        try {
            // Using custom unchecked exception
            validateEmail("invalid-email");
        } catch (InvalidEmailException e) {
            System.out.println("    Caught custom runtime exception: " + e.getMessage());
        }
        
        // Custom exception with additional information
        try {
            processTransaction("TXN001", -100);
        } catch (TransactionException e) {
            System.out.println("    Transaction failed: " + e.getMessage());
            System.out.println("    Transaction ID: " + e.getTransactionId());
            System.out.println("    Amount: " + e.getAmount());
        }
    }
    
    private static void demonstrateExceptionPropagation() {
        System.out.println("\n4. EXCEPTION PROPAGATION:\n");
        
        // Q7: How does Exception Propagation work?
        System.out.println("Q7: How does Exception Propagation work?");
        System.out.println("    - Exceptions propagate up the call stack");
        System.out.println("    - Each method can catch or declare exceptions");
        System.out.println("    - Uncaught exceptions terminate the program\n");
        
        // Q8: What is the difference between throws and throw?
        System.out.println("Q8: What is the difference between throws and throw?");
        System.out.println("    throws: Declares that a method might throw exceptions");
        System.out.println("    throw: Actually throws an exception object\n");
        
        // Demonstrate exception propagation
        System.out.println("Example: Exception Propagation");
        
        try {
            methodA();
        } catch (Exception e) {
            System.out.println("    Caught in main: " + e.getMessage());
            System.out.println("    Stack trace:");
            e.printStackTrace();
        }
        
        // Exception chaining
        try {
            processData();
        } catch (DataProcessingException e) {
            System.out.println("    Data processing failed: " + e.getMessage());
            System.out.println("    Root cause: " + e.getCause().getMessage());
        }
    }
    
    private static void demonstrateBestPractices() {
        System.out.println("\n5. BEST PRACTICES AND ADVANCED CONCEPTS:\n");
        
        // Q9: What are the best practices for Exception Handling?
        System.out.println("Q9: What are the best practices for Exception Handling?");
        System.out.println("    - Catch specific exceptions, not general Exception");
        System.out.println("    - Don't ignore exceptions");
        System.out.println("    - Use finally for cleanup");
        System.out.println("    - Log exceptions appropriately\n");
        
        // Q10: What is Exception Chaining?
        System.out.println("Q10: What is Exception Chaining?");
        System.out.println("    - Wrapping one exception in another");
        System.out.println("    - Preserving the original cause");
        System.out.println("    - Using initCause() or constructor chaining\n");
        
        // Demonstrate best practices
        System.out.println("Example: Best Practices");
        
        // Resource management with try-with-resources
        System.out.println("    Try-with-resources:");
        try (ResourceManager resource = new ResourceManager()) {
            resource.useResource();
        } catch (Exception e) {
            System.out.println("      Error using resource: " + e.getMessage());
        }
        
        // Exception chaining
        try {
            throw new RuntimeException("Original error");
        } catch (RuntimeException e) {
            try {
                throw new ChainedException("Wrapped error", e);
            } catch (ChainedException ce) {
                System.out.println("    Chained exception: " + ce.getMessage());
                System.out.println("    Original cause: " + ce.getCause().getMessage());
            }
        }
        
        // Custom exception handling
        try {
            performComplexOperation();
        } catch (ComplexOperationException e) {
            System.out.println("    Complex operation failed: " + e.getMessage());
            System.out.println("    Error details: " + e.getErrorDetails());
            System.out.println("    Timestamp: " + e.getTimestamp());
        }
    }
    
    // ===== HELPER METHODS =====
    
    private static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Invalid age: " + age, "AGE_001");
        }
        System.out.println("    Valid age: " + age);
    }
    
    private static void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new InvalidEmailException("Invalid email format: " + email);
        }
        System.out.println("    Valid email: " + email);
    }
    
    private static void processTransaction(String transactionId, double amount) throws TransactionException {
        if (amount < 0) {
            throw new TransactionException("Invalid amount: " + amount, transactionId, amount);
        }
        System.out.println("    Transaction processed: " + transactionId + ", Amount: " + amount);
    }
    
    private static void methodA() throws Exception {
        System.out.println("    Method A called");
        methodB();
    }
    
    private static void methodB() throws Exception {
        System.out.println("    Method B called");
        methodC();
    }
    
    private static void methodC() throws Exception {
        System.out.println("    Method C called");
        throw new Exception("Exception thrown in method C");
    }
    
    private static void processData() throws DataProcessingException {
        try {
            // Simulate some operation that might fail
            if (Math.random() < 0.5) {
                throw new RuntimeException("Random failure occurred");
            }
            System.out.println("    Data processed successfully");
        } catch (RuntimeException e) {
            throw new DataProcessingException("Failed to process data", e);
        }
    }
    
    private static void performComplexOperation() throws ComplexOperationException {
        try {
            // Simulate complex operation
            if (Math.random() < 0.7) {
                throw new RuntimeException("Complex operation failed");
            }
            System.out.println("    Complex operation completed");
        } catch (RuntimeException e) {
            throw new ComplexOperationException("Complex operation failed", e);
        }
    }
    
    // ===== CUSTOM EXCEPTION CLASSES =====
    
    // Custom checked exception
    static class InvalidAgeException extends Exception {
        private final String errorCode;
        
        public InvalidAgeException(String message, String errorCode) {
            super(message);
            this.errorCode = errorCode;
        }
        
        public String getErrorCode() {
            return errorCode;
        }
    }
    
    // Custom unchecked exception
    static class InvalidEmailException extends RuntimeException {
        public InvalidEmailException(String message) {
            super(message);
        }
    }
    
    // Custom exception with additional fields
    static class TransactionException extends Exception {
        private final String transactionId;
        private final double amount;
        
        public TransactionException(String message, String transactionId, double amount) {
            super(message);
            this.transactionId = transactionId;
            this.amount = amount;
        }
        
        public String getTransactionId() {
            return transactionId;
        }
        
        public double getAmount() {
            return amount;
        }
    }
    
    // Custom exception for data processing
    static class DataProcessingException extends Exception {
        public DataProcessingException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    // Custom exception with chaining
    static class ChainedException extends Exception {
        public ChainedException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    // Custom exception with additional context
    static class ComplexOperationException extends Exception {
        private final String errorDetails;
        private final long timestamp;
        
        public ComplexOperationException(String message, Throwable cause) {
            super(message, cause);
            this.errorDetails = "Complex operation failed due to: " + cause.getMessage();
            this.timestamp = System.currentTimeMillis();
        }
        
        public String getErrorDetails() {
            return errorDetails;
        }
        
        public long getTimestamp() {
            return timestamp;
        }
    }
    
    // Resource manager for try-with-resources
    static class ResourceManager implements AutoCloseable {
        public void useResource() {
            System.out.println("      Using resource...");
        }
        
        @Override
        public void close() {
            System.out.println("      Resource closed");
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA EXCEPTION HANDLING:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are Exceptions in Java?
 * 2. What is the difference between Error and Exception?
 * 3. What are Checked Exceptions?
 * 4. What are Unchecked Exceptions?
 * 5. What is the Exception hierarchy in Java?
 * 6. What is the purpose of try-catch blocks?
 * 7. What is the finally block used for?
 * 8. Can you have multiple catch blocks?
 * 9. What happens if an exception is not caught?
 * 10. What is the difference between throw and throws?
 * 11. Can you catch multiple exceptions in one catch block?
 * 12. What is the order of catch blocks?
 * 13. Can you have a try block without catch?
 * 14. Can you have a try block without finally?
 * 15. What is the purpose of the Exception class?
 * 16. What is the purpose of the RuntimeException class?
 * 17. What is the purpose of the Error class?
 * 18. Can you catch an Error?
 * 19. Can you catch a Throwable?
 * 20. What is the default exception handler?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. How do you create Custom Exceptions?
 * 22. When should you use Custom Exceptions?
 * 23. How do you handle multiple exceptions?
 * 24. What is Exception Propagation?
 * 25. How does the call stack work with exceptions?
 * 26. What is the difference between printStackTrace() and getMessage()?
 * 27. How do you log exceptions properly?
 * 28. What is Exception Chaining?
 * 29. How do you use initCause()?
 * 30. What is the purpose of getCause()?
 * 31. How do you handle checked exceptions in lambda expressions?
 * 32. How do you handle exceptions in streams?
 * 33. What is the try-with-resources statement?
 * 34. How do you implement AutoCloseable?
 * 35. What is the purpose of close() method?
 * 36. How do you handle exceptions in constructors?
 * 37. How do you handle exceptions in static blocks?
 * 38. How do you handle exceptions in finalize()?
 * 39. What is the purpose of suppressExceptions?
 * 40. How do you handle exceptions in nested try-catch blocks?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you implement exception handling patterns?
 * 42. How do you handle exceptions in multi-threaded applications?
 * 43. How do you implement exception recovery mechanisms?
 * 44. How do you handle exceptions in web applications?
 * 45. How do you implement exception monitoring and alerting?
 * 46. How do you handle exceptions in microservices?
 * 47. How do you implement circuit breaker pattern with exceptions?
 * 48. How do you handle exceptions in reactive programming?
 * 49. How do you implement exception retry mechanisms?
 * 50. How do you handle exceptions in batch processing?
 * 51. How do you implement exception aggregation?
 * 52. How do you handle exceptions in event-driven systems?
 * 53. How do you implement exception correlation?
 * 54. How do you handle exceptions in distributed systems?
 * 55. How do you implement exception routing?
 * 56. How do you handle exceptions in message queues?
 * 57. How do you implement exception dead letter queues?
 * 58. How do you handle exceptions in API gateways?
 * 59. How do you implement exception rate limiting?
 * 60. How do you handle exceptions in caching systems?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you design exception handling frameworks?
 * 62. How do you implement exception handling strategies?
 * 63. How do you design exception handling policies?
 * 64. How do you implement exception handling governance?
 * 65. How do you design exception handling patterns?
 * 66. How do you implement exception handling best practices?
 * 67. How do you design exception handling architectures?
 * 68. How do you implement exception handling systems?
 * 69. How do you design exception handling workflows?
 * 70. How do you implement exception handling processes?
 * 71. How do you design exception handling protocols?
 * 72. How do you implement exception handling standards?
 * 73. How do you design exception handling guidelines?
 * 74. How do you implement exception handling rules?
 * 75. How do you design exception handling procedures?
 * 76. How do you implement exception handling methods?
 * 77. How do you design exception handling approaches?
 * 78. How do you implement exception handling techniques?
 * 79. How do you design exception handling solutions?
 * 80. How do you implement exception handling strategies?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design an exception handling system for a large-scale application?
 * 82. How would you implement exception handling in a microservices architecture?
 * 83. How would you design exception handling for a distributed system?
 * 84. How would you implement exception handling in a cloud-native application?
 * 85. How would you design exception handling for a real-time system?
 * 86. How would you implement exception handling in a high-availability system?
 * 87. How would you design exception handling for a fault-tolerant system?
 * 88. How would you implement exception handling in a scalable system?
 * 89. How would you design exception handling for a secure system?
 * 90. How would you implement exception handling in a compliant system?
 * 91. How would you design exception handling for a monitored system?
 * 92. How would you implement exception handling in a logged system?
 * 93. How would you design exception handling for a audited system?
 * 94. How would you implement exception handling in a traced system?
 * 95. How would you design exception handling for a profiled system?
 * 96. How would you implement exception handling in a debugged system?
 * 97. How would you design exception handling for a tested system?
 * 98. How would you implement exception handling in a deployed system?
 * 99. How would you design exception handling for a maintained system?
 * 100. How would you implement exception handling in a evolved system?
 */
