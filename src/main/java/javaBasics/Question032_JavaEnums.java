/**
 * Question 32: Java Enums
 * 
 * This file contains Enum interview questions covering:
 * - Basic Declaration and Usage
 * - Methods and Constructors
 * - Design Patterns
 * - Advanced Concepts
 */
public class Question032_JavaEnums {
    
    public static void main(String[] args) {
        System.out.println("=== Java Enums - Interview Questions ===\n");
        
        demonstrateBasics();
        demonstrateMethods();
        demonstratePatterns();
        
        System.out.println("\n=== Java Enums Completed! ===");
    }
    
    private static void demonstrateBasics() {
        System.out.println("1. ENUM BASICS:\n");
        
        // Q1: What are Enums in Java?
        System.out.println("Q1: What are Enums in Java?");
        System.out.println("    Enums are special classes that represent a group of constants.");
        System.out.println("    They provide type safety and prevent invalid values.\n");
        
        // Q2: How do you declare Enums?
        System.out.println("Q2: How do you declare Enums?");
        System.out.println("    - Use enum keyword instead of class");
        System.out.println("    - List constants separated by commas");
        System.out.println("    - Constants are implicitly public static final\n");
        
        // Demonstrate basic enums
        System.out.println("Example: Basic Enums");
        
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println("    " + day.name() + " (ordinal: " + day.ordinal() + ")");
        }
        
        DayOfWeek today = DayOfWeek.MONDAY;
        System.out.println("    Today: " + today);
        System.out.println("    Is weekend: " + today.isWeekend());
    }
    
    private static void demonstrateMethods() {
        System.out.println("\n2. ENUM METHODS:\n");
        
        // Q3: What are the main methods of Enum?
        System.out.println("Q3: What are the main methods of Enum?");
        System.out.println("    - name(): Returns the name of the enum constant");
        System.out.println("    - ordinal(): Returns the position in enum declaration");
        System.out.println("    - values(): Returns array of all enum constants");
        System.out.println("    - valueOf(): Returns enum constant by name\n");
        
        // Demonstrate enum methods
        System.out.println("Example: Enum Methods");
        
        System.out.println("    name(): " + DayOfWeek.MONDAY.name());
        System.out.println("    ordinal(): " + DayOfWeek.MONDAY.ordinal());
        System.out.println("    toString(): " + DayOfWeek.MONDAY.toString());
        
        DayOfWeek[] allDays = DayOfWeek.values();
        System.out.println("    All days: " + allDays.length + " total");
        
        DayOfWeek wednesday = DayOfWeek.valueOf("WEDNESDAY");
        System.out.println("    valueOf('WEDNESDAY'): " + wednesday);
    }
    
    private static void demonstratePatterns() {
        System.out.println("\n3. ENUM PATTERNS:\n");
        
        // Q4: What are common Enum design patterns?
        System.out.println("Q4: What are common Enum design patterns?");
        System.out.println("    - Singleton pattern");
        System.out.println("    - Strategy pattern");
        System.out.println("    - State pattern");
        System.out.println("    - Factory pattern\n");
        
        // Demonstrate enum patterns
        System.out.println("Example: Enum Patterns");
        
        // Singleton pattern
        DatabaseConnection conn1 = DatabaseConnection.INSTANCE;
        DatabaseConnection conn2 = DatabaseConnection.INSTANCE;
        System.out.println("    Singleton: " + (conn1 == conn2));
        
        // Strategy pattern
        PaymentType.CREDIT_CARD.process(100.0);
        PaymentType.PAYPAL.process(50.0);
        
        // Factory pattern
        Vehicle car = VehicleType.CAR.createVehicle();
        car.start();
    }
    
    // Basic enum
    enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
        
        public boolean isWeekend() {
            return this == SATURDAY || this == SUNDAY;
        }
        
        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }
    
    // Singleton pattern enum
    enum DatabaseConnection {
        INSTANCE;
        
        private boolean connected = false;
        
        public void connect() {
            if (!connected) {
                System.out.println("    Connecting to database...");
                connected = true;
            }
        }
        
        public boolean isConnected() {
            return connected;
        }
    }
    
    // Strategy pattern enum
    enum PaymentType {
        CREDIT_CARD {
            @Override
            public void process(double amount) {
                System.out.println("    Processing credit card payment: $" + amount);
            }
        },
        PAYPAL {
            @Override
            public void process(double amount) {
                System.out.println("    Processing PayPal payment: $" + amount);
            }
        };
        
        public abstract void process(double amount);
    }
    
    // Factory pattern enum
    enum VehicleType {
        CAR {
            @Override
            public Vehicle createVehicle() {
                return new Car();
            }
        },
        BIKE {
            @Override
            public Vehicle createVehicle() {
                return new Bike();
            }
        };
        
        public abstract Vehicle createVehicle();
    }
    
    interface Vehicle {
        void start();
    }
    
    static class Car implements Vehicle {
        public void start() { System.out.println("    Car starting..."); }
    }
    
    static class Bike implements Vehicle {
        public void start() { System.out.println("    Bike starting..."); }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA ENUMS:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are Enums in Java?
 * 2. What are the main purposes of Enums?
 * 3. How do you declare Enums?
 * 4. What is the difference between Enums and constants?
 * 5. How do you access Enum values?
 * 6. What is the name() method in Enum?
 * 7. What is the ordinal() method in Enum?
 * 8. What is the values() method in Enum?
 * 9. What is the valueOf() method in Enum?
 * 10. How do you compare Enum values?
 * 11. Can you use Enums in switch statements?
 * 12. How do you iterate through Enum values?
 * 13. What is the default toString() behavior of Enum?
 * 14. Can you override methods in Enum?
 * 15. Can you add fields to Enum?
 * 16. Can you add constructors to Enum?
 * 17. What is the default constructor of Enum?
 * 18. Can you make Enum constructors public?
 * 19. Can you create Enum instances using new?
 * 20. What is the inheritance hierarchy of Enum?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. How do you add custom methods to Enums?
 * 22. How do you implement interfaces with Enums?
 * 23. How do you use Enums with generics?
 * 24. How do you create Enum with abstract methods?
 * 25. How do you implement the Singleton pattern with Enums?
 * 26. How do you implement the Strategy pattern with Enums?
 * 27. How do you implement the State pattern with Enums?
 * 28. How do you implement the Command pattern with Enums?
 * 29. How do you implement the Factory pattern with Enums?
 * 30. How do you use Enums with collections?
 * 31. How do you serialize and deserialize Enums?
 * 32. How do you use Enums with annotations?
 * 33. How do you use Enums with reflection?
 * 34. How do you create Enum with nested classes?
 * 35. How do you use Enums with streams?
 * 36. How do you implement Enum with validation?
 * 37. How do you use Enums with databases?
 * 38. How do you implement Enum with caching?
 * 39. How do you use Enums with JSON?
 * 40. How do you implement Enum with configuration?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you implement complex business logic with Enums?
 * 42. How do you implement Enum with dependency injection?
 * 43. How do you implement Enum with event handling?
 * 44. How do you implement Enum with workflow engines?
 * 45. How do you implement Enum with rule engines?
 * 46. How do you implement Enum with state machines?
 * 47. How do you implement Enum with parsers?
 * 48. How do you implement Enum with compilers?
 * 49. How do you implement Enum with interpreters?
 * 50. How do you implement Enum with virtual machines?
 * 51. How do you implement Enum with operating systems?
 * 52. How do you implement Enum with network protocols?
 * 53. How do you implement Enum with security systems?
 * 54. How do you implement Enum with authentication?
 * 55. How do you implement Enum with authorization?
 * 56. How do you implement Enum with encryption?
 * 57. How do you implement Enum with compression?
 * 58. How do you implement Enum with hashing?
 * 59. How do you implement Enum with digital signatures?
 * 60. How do you implement Enum with certificates?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you implement a custom Enum framework?
 * 62. How do you implement Enum-based code generation?
 * 63. How do you implement Enum-based validation engines?
 * 64. How do you implement Enum-based dependency injection containers?
 * 65. How do you implement Enum-based workflow engines?
 * 66. How do you implement Enum-based rule engines?
 * 67. How do you implement Enum-based state machines?
 * 68. How do you implement Enum-based parser systems?
 * 69. How do you implement Enum-based compiler systems?
 * 70. How do you implement Enum-based interpreter systems?
 * 71. How do you implement Enum-based virtual machines?
 * 72. How do you implement Enum-based operating systems?
 * 73. How do you implement Enum-based network protocols?
 * 74. How do you implement Enum-based security frameworks?
 * 75. How do you implement Enum-based authentication systems?
 * 76. How do you implement Enum-based authorization systems?
 * 77. How do you implement Enum-based encryption systems?
 * 78. How do you implement Enum-based compression systems?
 * 79. How do you implement Enum-based hashing systems?
 * 80. How do you implement Enum-based digital signature systems?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design an Enum-based framework?
 * 82. How would you implement an Enum-based validation system?
 * 83. How would you design an Enum-based configuration system?
 * 84. How would you implement an Enum-based dependency injection system?
 * 85. How would you design an Enum-based workflow system?
 * 86. How would you implement an Enum-based rule engine?
 * 87. How would you design an Enum-based state machine?
 * 88. How would you implement an Enum-based parser system?
 * 89. How would you design an Enum-based compiler system?
 * 90. How would you implement an Enum-based interpreter system?
 * 91. How would you design an Enum-based virtual machine?
 * 92. How would you implement an Enum-based operating system?
 * 93. How would you design an Enum-based network protocol?
 * 94. How would you implement an Enum-based security system?
 * 95. How would you design an Enum-based authentication system?
 * 96. How would you implement an Enum-based authorization system?
 * 97. How would you design an Enum-based encryption system?
 * 98. How would you implement an Enum-based compression system?
 * 99. How would you design an Enum-based hashing system?
 * 100. How would you implement an Enum-based digital signature system?
 */
