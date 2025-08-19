/**
 * Question 34: Java Interfaces
 * 
 * This file contains Interfaces interview questions covering:
 * - Interface Basics and Declaration
 * - Default and Static Methods
 * - Functional Interfaces
 * - Interface Inheritance and Implementation
 */
public class Question034_JavaInterfaces {
    
    public static void main(String[] args) {
        System.out.println("=== Java Interfaces - Interview Questions ===\n");
        
        demonstrateInterfaceBasics();
        demonstrateDefaultMethods();
        demonstrateStaticMethods();
        demonstrateFunctionalInterfaces();
        demonstrateInterfaceInheritance();
        
        System.out.println("\n=== Java Interfaces Completed! ===");
    }
    
    private static void demonstrateInterfaceBasics() {
        System.out.println("1. INTERFACE BASICS:\n");
        
        // Q1: What are Interfaces in Java?
        System.out.println("Q1: What are Interfaces in Java?");
        System.out.println("    Interfaces are abstract types that define a contract.");
        System.out.println("    They contain method signatures without implementation.");
        System.out.println("    Classes implement interfaces to provide behavior.\n");
        
        // Q2: What are the main purposes of Interfaces?
        System.out.println("Q2: What are the main purposes of Interfaces?");
        System.out.println("    - Define contracts for classes");
        System.out.println("    - Enable multiple inheritance");
        System.out.println("    - Provide abstraction");
        System.out.println("    - Support polymorphism\n");
        
        // Demonstrate interface basics
        System.out.println("Example: Interface Basics");
        
        // Implementing interface
        Vehicle car = new Car();
        Vehicle bike = new Bike();
        
        System.out.println("    Car starting: " + car.start());
        System.out.println("    Car stopping: " + car.stop());
        System.out.println("    Bike starting: " + bike.start());
        System.out.println("    Bike stopping: " + bike.stop());
        
        // Interface as type
        Vehicle[] vehicles = {car, bike};
        for (Vehicle vehicle : vehicles) {
            System.out.println("    Vehicle type: " + vehicle.getType());
        }
        
        // Interface with constants
        System.out.println("    Max speed: " + Vehicle.MAX_SPEED);
        System.out.println("    Min speed: " + Vehicle.MIN_SPEED);
    }
    
    private static void demonstrateDefaultMethods() {
        System.out.println("\n2. DEFAULT METHODS:\n");
        
        // Q3: What are Default Methods in Interfaces?
        System.out.println("Q3: What are Default Methods in Interfaces?");
        System.out.println("    Default methods provide implementation in interfaces.");
        System.out.println("    They allow adding new methods without breaking existing code.");
        System.out.println("    Classes can override default methods if needed.\n");
        
        // Demonstrate default methods
        System.out.println("Example: Default Methods");
        
        // Using default methods
        Logger consoleLogger = new ConsoleLogger();
        Logger fileLogger = new FileLogger();
        
        System.out.println("    Console logger:");
        consoleLogger.log("Info message");
        consoleLogger.logError("Error message");
        
        System.out.println("    File logger:");
        fileLogger.log("Info message");
        fileLogger.logError("Error message");
        
        // Overriding default methods
        Logger customLogger = new CustomLogger();
        customLogger.log("Custom message");
        customLogger.logError("Custom error");
    }
    
    private static void demonstrateStaticMethods() {
        System.out.println("\n3. STATIC METHODS:\n");
        
        // Q4: What are Static Methods in Interfaces?
        System.out.println("Q4: What are Static Methods in Interfaces?");
        System.out.println("    Static methods belong to the interface, not implementing classes.");
        System.out.println("    They are called using the interface name.");
        System.out.println("    They provide utility functions related to the interface.\n");
        
        // Demonstrate static methods
        System.out.println("Example: Static Methods");
        
        // Using static methods
        String validEmail = "test@example.com";
        String invalidEmail = "invalid-email";
        
        System.out.println("    Email validation:");
        System.out.println("      " + validEmail + " is valid: " + Validator.isValidEmail(validEmail));
        System.out.println("      " + invalidEmail + " is valid: " + Validator.isValidEmail(invalidEmail));
        
        // Static method with utility functions
        String text = "Hello World";
        System.out.println("    Text utilities:");
        System.out.println("      Original: " + text);
        System.out.println("      Reversed: " + TextUtils.reverse(text));
        System.out.println("      Word count: " + TextUtils.wordCount(text));
    }
    
    private static void demonstrateFunctionalInterfaces() {
        System.out.println("\n4. FUNCTIONAL INTERFACES:\n");
        
        // Q5: What are Functional Interfaces?
        System.out.println("Q5: What are Functional Interfaces?");
        System.out.println("    Functional interfaces have exactly one abstract method.");
        System.out.println("    They can be used with lambda expressions.");
        System.out.println("    They support functional programming in Java.\n");
        
        // Demonstrate functional interfaces
        System.out.println("Example: Functional Interfaces");
        
        // Using functional interfaces with lambdas
        Calculator calculator = new Calculator();
        
        System.out.println("    Calculator operations:");
        System.out.println("      Addition: " + calculator.calculate(5, 3, (a, b) -> a + b));
        System.out.println("      Subtraction: " + calculator.calculate(5, 3, (a, b) -> a - b));
        System.out.println("      Multiplication: " + calculator.calculate(5, 3, (a, b) -> a * b));
        System.out.println("      Division: " + calculator.calculate(6, 2, (a, b) -> a / b));
        
        // Built-in functional interfaces
        System.out.println("    Built-in functional interfaces:");
        System.out.println("      Predicate: " + Validator.isPositive(5));
        System.out.println("      Function: " + Validator.doubleValue(10));
        System.out.println("      Consumer: ");
        Validator.printMessage("Hello from consumer!");
        
        // Custom functional interface
        Transformer<String, Integer> lengthTransformer = String::length;
        System.out.println("      Custom transformer: " + lengthTransformer.transform("Hello"));
    }
    
    private static void demonstrateInterfaceInheritance() {
        System.out.println("\n5. INTERFACE INHERITANCE:\n");
        
        // Q6: How do Interfaces support inheritance?
        System.out.println("Q6: How do Interfaces support inheritance?");
        System.out.println("    Interfaces can extend other interfaces.");
        System.out.println("    Classes can implement multiple interfaces.");
        System.out.println("    This enables multiple inheritance of behavior.\n");
        
        // Demonstrate interface inheritance
        System.out.println("Example: Interface Inheritance");
        
        // Multiple interface implementation
        SmartDevice smartphone = new Smartphone();
        
        System.out.println("    Smartphone capabilities:");
        System.out.println("      Device info: " + smartphone.getDeviceInfo());
        System.out.println("      Battery level: " + smartphone.getBatteryLevel());
        smartphone.connect();
        smartphone.disconnect();
        
        // Interface extending other interfaces
        AdvancedCalculator advancedCalc = new AdvancedCalculator();
        
        System.out.println("    Advanced calculator:");
        System.out.println("      Basic add: " + advancedCalc.add(5, 3));
        System.out.println("      Advanced power: " + advancedCalc.power(2, 8));
        System.out.println("      Scientific sin: " + advancedCalc.sin(Math.PI / 2));
        
        // Interface with generics
        DataProcessor<String> stringProcessor = new StringDataProcessor();
        System.out.println("    String processor: " + stringProcessor.process("Hello World"));
    }
    
    // ===== HELPER INTERFACES AND CLASSES =====
    
    // Basic interface
    interface Vehicle {
        // Constants (implicitly public static final)
        int MAX_SPEED = 200;
        int MIN_SPEED = 0;
        
        // Abstract methods
        String start();
        String stop();
        String getType();
    }
    
    // Interface with default methods
    interface Logger {
        // Abstract method
        void log(String message);
        
        // Default method
        default void logError(String error) {
            System.out.println("    ERROR: " + error);
        }
    }
    
    // Interface with static methods
    interface Validator {
        // Static method
        static boolean isValidEmail(String email) {
            return email != null && email.contains("@");
        }
        
        // Static method with utility functions
        static boolean isPositive(int number) {
            return number > 0;
        }
        
        static int doubleValue(int number) {
            return number * 2;
        }
        
        static void printMessage(String message) {
            System.out.println("    Message: " + message);
        }
    }
    
    // Interface with static methods for text utilities
    interface TextUtils {
        static String reverse(String text) {
            return new StringBuilder(text).reverse().toString();
        }
        
        static int wordCount(String text) {
            return text.split("\\s+").length;
        }
    }
    
    // Functional interface
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }
    
    // Built-in functional interfaces
    @FunctionalInterface
    interface Predicate<T> {
        boolean test(T t);
    }
    
    @FunctionalInterface
    interface Function<T, R> {
        R apply(T t);
    }
    
    @FunctionalInterface
    interface Consumer<T> {
        void accept(T t);
    }
    
    // Custom functional interface
    @FunctionalInterface
    interface Transformer<T, R> {
        R transform(T t);
    }
    
    // Interface inheritance
    interface Device {
        String getDeviceInfo();
        int getBatteryLevel();
    }
    
    interface Connectable {
        void connect();
        void disconnect();
    }
    
    // Interface extending multiple interfaces
    interface SmartDevice extends Device, Connectable {
        // Inherits methods from both Device and Connectable
    }
    
    // Calculator interfaces
    interface BasicCalculator {
        int add(int a, int b);
        int subtract(int a, int b);
    }
    
    interface ScientificCalculator {
        double power(double base, double exponent);
        double sin(double angle);
    }
    
    // Interface extending other interfaces
    interface AdvancedCalculator extends BasicCalculator, ScientificCalculator {
        // Inherits methods from both interfaces
    }
    
    // Generic interface
    interface DataProcessor<T> {
        T process(T data);
    }
    
    // Implementing classes
    static class Car implements Vehicle {
        @Override
        public String start() {
            return "Car engine started";
        }
        
        @Override
        public String stop() {
            return "Car engine stopped";
        }
        
        @Override
        public String getType() {
            return "Car";
        }
    }
    
    static class Bike implements Vehicle {
        @Override
        public String start() {
            return "Bike engine started";
        }
        
        @Override
        public String stop() {
            return "Bike engine stopped";
        }
        
        @Override
        public String getType() {
            return "Bike";
        }
    }
    
    static class ConsoleLogger implements Logger {
        @Override
        public void log(String message) {
            System.out.println("    CONSOLE: " + message);
        }
    }
    
    static class FileLogger implements Logger {
        @Override
        public void log(String message) {
            System.out.println("    FILE: " + message);
        }
    }
    
    static class CustomLogger implements Logger {
        @Override
        public void log(String message) {
            System.out.println("    CUSTOM: " + message);
        }
        
        @Override
        public void logError(String error) {
            System.out.println("    CUSTOM ERROR: " + error);
        }
    }
    
    static class Calculator {
        public int calculate(int a, int b, MathOperation operation) {
            return operation.operate(a, b);
        }
    }
    
    static class Smartphone implements SmartDevice {
        @Override
        public String getDeviceInfo() {
            return "Smartphone v2.0";
        }
        
        @Override
        public int getBatteryLevel() {
            return 85;
        }
        
        @Override
        public void connect() {
            System.out.println("    Connecting to network...");
        }
        
        @Override
        public void disconnect() {
            System.out.println("    Disconnecting from network...");
        }
    }
    
    static class AdvancedCalculatorImpl implements AdvancedCalculator {
        @Override
        public int add(int a, int b) {
            return a + b;
        }
        
        @Override
        public int subtract(int a, int b) {
            return a - b;
        }
        
        @Override
        public double power(double base, double exponent) {
            return Math.pow(base, exponent);
        }
        
        @Override
        public double sin(double angle) {
            return Math.sin(angle);
        }
    }
    
    static class StringDataProcessor implements DataProcessor<String> {
        @Override
        public String process(String data) {
            return data.toUpperCase();
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA INTERFACES:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are Interfaces in Java?
 * 2. What are the main purposes of Interfaces?
 * 3. How do you declare an Interface?
 * 4. What is the difference between Interface and Abstract Class?
 * 5. Can Interfaces have constructors?
 * 6. Can Interfaces have instance variables?
 * 7. Can Interfaces have static variables?
 * 8. Can Interfaces have final variables?
 * 9. Can Interfaces have private methods?
 * 10. Can Interfaces have protected methods?
 * 11. Can Interfaces have static methods?
 * 12. Can Interfaces have default methods?
 * 13. Can Interfaces be instantiated?
 * 14. Can Interfaces be declared as final?
 * 15. Can Interfaces be declared as abstract?
 * 16. Can Interfaces extend other Interfaces?
 * 17. Can Interfaces extend multiple Interfaces?
 * 18. Can a Class implement multiple Interfaces?
 * 19. Can Interfaces have nested Interfaces?
 * 20. Can Interfaces have nested Classes?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. What are Default Methods in Interfaces?
 * 22. How do you use Default Methods?
 * 23. Can Default Methods be overridden?
 * 24. Can Default Methods be static?
 * 25. Can Default Methods be private?
 * 26. What are Static Methods in Interfaces?
 * 27. How do you call Static Methods in Interfaces?
 * 28. Can Static Methods be overridden?
 * 29. Can Static Methods be private?
 * 30. What are Private Methods in Interfaces?
 * 31. How do you use Private Methods in Interfaces?
 * 32. Can Private Methods be static?
 * 33. Can Private Methods be default?
 * 34. What are Functional Interfaces?
 * 35. How do you identify Functional Interfaces?
 * 36. What are built-in Functional Interfaces?
 * 37. How do you use Predicate interface?
 * 38. How do you use Function interface?
 * 39. How do you use Consumer interface?
 * 40. How do you use Supplier interface?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you create custom Functional Interfaces?
 * 42. How do you use Interfaces with Generics?
 * 43. How do you implement multiple inheritance with Interfaces?
 * 44. How do you resolve conflicts in multiple inheritance?
 * 45. How do you use Interfaces with Lambda expressions?
 * 46. How do you use Interfaces with Method references?
 * 47. How do you use Interfaces with Streams?
 * 48. How do you implement design patterns with Interfaces?
 * 49. How do you use Interfaces for dependency injection?
 * 50. How do you use Interfaces for testing?
 * 51. How do you use Interfaces for mocking?
 * 52. How do you use Interfaces for callbacks?
 * 53. How do you use Interfaces for event handling?
 * 54. How do you use Interfaces for observers?
 * 55. How do you use Interfaces for strategies?
 * 56. How do you use Interfaces for commands?
 * 57. How do you use Interfaces for factories?
 * 58. How do you use Interfaces for builders?
 * 59. How do you use Interfaces for adapters?
 * 60. How do you use Interfaces for decorators?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you implement a custom framework using Interfaces?
 * 62. How do you implement plugin systems using Interfaces?
 * 63. How do you implement event systems using Interfaces?
 * 64. How do you implement callback systems using Interfaces?
 * 65. How do you implement observer systems using Interfaces?
 * 66. How do you implement decorator systems using Interfaces?
 * 67. How do you implement adapter systems using Interfaces?
 * 68. How do you implement factory systems using Interfaces?
 * 69. How do you implement builder systems using Interfaces?
 * 70. How do you implement singleton systems using Interfaces?
 * 71. How do you implement proxy systems using Interfaces?
 * 72. How do you implement chain systems using Interfaces?
 * 73. How do you implement command systems using Interfaces?
 * 74. How do you implement strategy systems using Interfaces?
 * 75. How do you implement state systems using Interfaces?
 * 76. How do you implement visitor systems using Interfaces?
 * 77. How do you implement iterator systems using Interfaces?
 * 78. How do you implement comparator systems using Interfaces?
 * 79. How do you implement filter systems using Interfaces?
 * 80. How do you implement mapper systems using Interfaces?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a framework using Interfaces?
 * 82. How would you implement a plugin system using Interfaces?
 * 83. How would you design an event system using Interfaces?
 * 84. How would you implement a callback system using Interfaces?
 * 85. How would you design an observer system using Interfaces?
 * 86. How would you implement a decorator system using Interfaces?
 * 87. How would you design an adapter system using Interfaces?
 * 88. How would you implement a factory system using Interfaces?
 * 89. How would you design a builder system using Interfaces?
 * 90. How would you implement a singleton system using Interfaces?
 * 91. How would you design a proxy system using Interfaces?
 * 92. How would you implement a chain system using Interfaces?
 * 93. How would you design a command system using Interfaces?
 * 94. How would you implement a strategy system using Interfaces?
 * 95. How would you design a state system using Interfaces?
 * 96. How would you implement a visitor system using Interfaces?
 * 97. How would you design an iterator system using Interfaces?
 * 98. How would you implement a comparator system using Interfaces?
 * 99. How would you design a filter system using Interfaces?
 * 100. How would you implement a mapper system using Interfaces?
 */
