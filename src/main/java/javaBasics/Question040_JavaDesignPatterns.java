/**
 * Question 40: Java Design Patterns
 * 
 * This file contains Design Patterns interview questions covering:
 * - Creational Patterns
 * - Structural Patterns  
 * - Behavioral Patterns
 * - Best Practices
 */
public class Question040_JavaDesignPatterns {
    
    public static void main(String[] args) {
        System.out.println("=== Java Design Patterns - Interview Questions ===\n");
        
        demonstrateCreationalPatterns();
        demonstrateStructuralPatterns();
        demonstrateBehavioralPatterns();
        
        System.out.println("\n=== Java Design Patterns Completed! ===");
    }
    
    private static void demonstrateCreationalPatterns() {
        System.out.println("1. CREATIONAL PATTERNS:\n");
        
        // Q1: What are Creational Design Patterns?
        System.out.println("Q1: What are Creational Design Patterns?");
        System.out.println("    - Deal with object creation mechanisms");
        System.out.println("    - Provide flexibility in object creation");
        System.out.println("    - Hide creation logic from client code\n");
        
        // Demonstrate Singleton Pattern
        System.out.println("Example: Singleton Pattern");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        System.out.println("    Same instance: " + (db1 == db2));
        
        // Demonstrate Factory Pattern
        System.out.println("Example: Factory Pattern");
        Vehicle car = VehicleFactory.createVehicle("CAR");
        Vehicle bike = VehicleFactory.createVehicle("BIKE");
        System.out.println("    Car type: " + car.getType());
        System.out.println("    Bike type: " + bike.getType());
    }
    
    private static void demonstrateStructuralPatterns() {
        System.out.println("\n2. STRUCTURAL PATTERNS:\n");
        
        // Q2: What are Structural Design Patterns?
        System.out.println("Q2: What are Structural Design Patterns?");
        System.out.println("    - Deal with object composition");
        System.out.println("    - Provide ways to compose objects");
        System.out.println("    - Ensure flexibility and efficiency\n");
        
        // Demonstrate Adapter Pattern
        System.out.println("Example: Adapter Pattern");
        LegacySystem legacy = new LegacySystem();
        ModernInterface adapter = new LegacyAdapter(legacy);
        System.out.println("    Legacy data: " + legacy.getData());
        System.out.println("    Modern format: " + adapter.getFormattedData());
        
        // Demonstrate Decorator Pattern
        System.out.println("Example: Decorator Pattern");
        Coffee coffee = new SimpleCoffee();
        Coffee milkCoffee = new MilkDecorator(coffee);
        System.out.println("    Simple coffee: $" + coffee.getCost());
        System.out.println("    Milk coffee: $" + milkCoffee.getCost());
    }
    
    private static void demonstrateBehavioralPatterns() {
        System.out.println("\n3. BEHAVIORAL PATTERNS:\n");
        
        // Q3: What are Behavioral Design Patterns?
        System.out.println("Q3: What are Behavioral Design Patterns?");
        System.out.println("    - Deal with communication between objects");
        System.out.println("    - Provide ways to organize object interactions");
        System.out.println("    - Ensure loose coupling between objects\n");
        
        // Demonstrate Observer Pattern
        System.out.println("Example: Observer Pattern");
        NewsAgency agency = new NewsAgency();
        NewsChannel channel = new NewsChannel("Channel 1");
        agency.addObserver(channel);
        agency.setNews("Breaking News!");
        System.out.println("    Channel news: " + channel.getLatestNews());
        
        // Demonstrate Strategy Pattern
        System.out.println("Example: Strategy Pattern");
        PaymentStrategy creditCard = new CreditCardPayment();
        PaymentStrategy paypal = new PayPalPayment();
        creditCard.processPayment(100.0);
        paypal.processPayment(50.0);
    }
    
    // ===== HELPER CLASSES =====
    
    // Singleton Pattern
    static class DatabaseConnection {
        private static DatabaseConnection instance;
        private final String id = "DB_" + System.currentTimeMillis();
        
        private DatabaseConnection() {}
        
        public static synchronized DatabaseConnection getInstance() {
            if (instance == null) {
                instance = new DatabaseConnection();
            }
            return instance;
        }
    }
    
    // Factory Pattern
    static class VehicleFactory {
        public static Vehicle createVehicle(String type) {
            switch (type.toUpperCase()) {
                case "CAR": return new Car();
                case "BIKE": return new Bike();
                default: throw new IllegalArgumentException("Unknown type: " + type);
            }
        }
    }
    
    abstract static class Vehicle {
        abstract String getType();
    }
    
    static class Car extends Vehicle {
        @Override
        String getType() { return "Car"; }
    }
    
    static class Bike extends Vehicle {
        @Override
        String getType() { return "Bike"; }
    }
    
    // Adapter Pattern
    static class LegacySystem {
        public String getData() { return "legacy_format"; }
    }
    
    interface ModernInterface {
        String getFormattedData();
    }
    
    static class LegacyAdapter implements ModernInterface {
        private final LegacySystem legacy;
        
        public LegacyAdapter(LegacySystem legacy) {
            this.legacy = legacy;
        }
        
        @Override
        public String getFormattedData() {
            return "modern_" + legacy.getData().replace("legacy_", "");
        }
    }
    
    // Decorator Pattern
    interface Coffee {
        double getCost();
    }
    
    static class SimpleCoffee implements Coffee {
        @Override
        public double getCost() { return 2.0; }
    }
    
    static class MilkDecorator implements Coffee {
        private final Coffee coffee;
        
        public MilkDecorator(Coffee coffee) {
            this.coffee = coffee;
        }
        
        @Override
        public double getCost() {
            return coffee.getCost() + 0.5;
        }
    }
    
    // Observer Pattern
    interface Observer {
        void update(String news);
    }
    
    static class NewsAgency {
        private final java.util.List<Observer> observers = new java.util.ArrayList<>();
        private String news;
        
        public void addObserver(Observer observer) {
            observers.add(observer);
        }
        
        public void setNews(String news) {
            this.news = news;
            notifyObservers();
        }
        
        private void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(news);
            }
        }
    }
    
    static class NewsChannel implements Observer {
        private String latestNews;
        
        @Override
        public void update(String news) {
            this.latestNews = news;
        }
        
        public String getLatestNews() {
            return latestNews;
        }
    }
    
    // Strategy Pattern
    interface PaymentStrategy {
        void processPayment(double amount);
    }
    
    static class CreditCardPayment implements PaymentStrategy {
        @Override
        public void processPayment(double amount) {
            System.out.println("      Credit card payment: $" + amount);
        }
    }
    
    static class PayPalPayment implements PaymentStrategy {
        @Override
        public void processPayment(double amount) {
            System.out.println("      PayPal payment: $" + amount);
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA DESIGN PATTERNS:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are Design Patterns in Java?
 * 2. What are the three main categories of Design Patterns?
 * 3. What is the Singleton Pattern?
 * 4. What is the Factory Method Pattern?
 * 5. What is the Abstract Factory Pattern?
 * 6. What is the Builder Pattern?
 * 7. What is the Prototype Pattern?
 * 8. What is the Adapter Pattern?
 * 9. What is the Bridge Pattern?
 * 10. What is the Composite Pattern?
 * 11. What is the Decorator Pattern?
 * 12. What is the Facade Pattern?
 * 13. What is the Observer Pattern?
 * 14. What is the Strategy Pattern?
 * 15. What is the Command Pattern?
 * 16. What is the State Pattern?
 * 17. What is the Template Method Pattern?
 * 18. What is the Iterator Pattern?
 * 19. What is the Mediator Pattern?
 * 20. What is the Memento Pattern?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. When should you use the Singleton Pattern?
 * 22. How do you implement a thread-safe Singleton?
 * 23. What are the advantages of the Factory Pattern?
 * 24. How do you implement the Builder Pattern?
 * 25. What is the difference between Factory and Abstract Factory?
 * 26. How do you implement the Prototype Pattern?
 * 27. When should you use the Adapter Pattern?
 * 28. How do you implement the Bridge Pattern?
 * 29. What are the benefits of the Composite Pattern?
 * 30. How do you implement the Decorator Pattern?
 * 31. When should you use the Facade Pattern?
 * 32. How do you implement the Observer Pattern?
 * 33. What are the advantages of the Strategy Pattern?
 * 34. How do you implement the Command Pattern?
 * 35. When should you use the State Pattern?
 * 36. How do you implement the Template Method Pattern?
 * 37. What is the difference between Strategy and Template Method?
 * 38. How do you implement the Iterator Pattern?
 * 39. When should you use the Mediator Pattern?
 * 40. How do you implement the Memento Pattern?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you combine multiple Design Patterns?
 * 42. How do you implement Design Patterns in microservices?
 * 43. How do you use Design Patterns with dependency injection?
 * 44. How do you implement Design Patterns with generics?
 * 45. How do you use Design Patterns with functional programming?
 * 46. How do you implement Design Patterns with reflection?
 * 47. How do you use Design Patterns with annotations?
 * 48. How do you implement Design Patterns with lambda expressions?
 * 49. How do you use Design Patterns with streams?
 * 50. How do you implement Design Patterns with CompletableFuture?
 * 51. How do you use Design Patterns with reactive programming?
 * 52. How do you implement Design Patterns with event sourcing?
 * 53. How do you use Design Patterns with CQRS?
 * 54. How do you implement Design Patterns with DDD?
 * 55. How do you use Design Patterns with hexagonal architecture?
 * 56. How do you implement Design Patterns with clean architecture?
 * 57. How do you use Design Patterns with SOLID principles?
 * 58. How do you implement Design Patterns with TDD?
 * 59. How do you use Design Patterns with BDD?
 * 60. How do you implement Design Patterns with CI/CD?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you design a framework using Design Patterns?
 * 62. How do you implement Design Patterns for performance?
 * 63. How do you design Design Patterns for scalability?
 * 64. How do you implement Design Patterns for security?
 * 65. How do you design Design Patterns for testing?
 * 66. How do you implement Design Patterns for monitoring?
 * 67. How do you design Design Patterns for logging?
 * 68. How do you implement Design Patterns for error handling?
 * 69. How do you design Design Patterns for caching?
 * 70. How do you implement Design Patterns for serialization?
 * 71. How do you design Design Patterns for networking?
 * 72. How do you implement Design Patterns for database access?
 * 73. How do you design Design Patterns for web services?
 * 74. How do you implement Design Patterns for REST APIs?
 * 75. How do you design Design Patterns for GraphQL?
 * 76. How do you implement Design Patterns for message queues?
 * 77. How do you design Design Patterns for event streaming?
 * 78. How do you implement Design Patterns for machine learning?
 * 79. How do you design Design Patterns for blockchain?
 * 80. How do you implement Design Patterns for edge computing?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a system using Design Patterns for large-scale applications?
 * 82. How would you implement Design Patterns in microservices architecture?
 * 83. How would you design Design Patterns for distributed systems?
 * 84. How would you implement Design Patterns in cloud-native applications?
 * 85. How would you design Design Patterns for real-time systems?
 * 86. How would you implement Design Patterns in high-availability systems?
 * 87. How would you design Design Patterns for fault-tolerant systems?
 * 88. How would you implement Design Patterns in scalable systems?
 * 89. How would you design Design Patterns for secure systems?
 * 90. How would you implement Design Patterns in compliant systems?
 * 91. How would you design Design Patterns for monitored systems?
 * 92. How would you implement Design Patterns in logged systems?
 * 93. How would you design Design Patterns for audited systems?
 * 94. How would you implement Design Patterns in traced systems?
 * 95. How would you design Design Patterns for profiled systems?
 * 96. How would you implement Design Patterns in debugged systems?
 * 97. How would you design Design Patterns for tested systems?
 * 98. How would you implement Design Patterns in deployed systems?
 * 99. How would you design Design Patterns for maintained systems?
 * 100. How would you implement Design Patterns in evolved systems?
 */
