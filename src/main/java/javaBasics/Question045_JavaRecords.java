/**
 * Question 45: Java Records
 * 
 * This file contains Records interview questions covering:
 * - Record Basics and Declaration
 * - Record Features and Benefits
 * - Record Usage Patterns
 * - Best Practices
 */
public class Question045_JavaRecords {
    
    public static void main(String[] args) {
        System.out.println("=== Java Records - Interview Questions ===\n");
        
        demonstrateRecordBasics();
        demonstrateRecordFeatures();
        demonstrateRecordUsage();
        demonstrateBestPractices();
        
        System.out.println("\n=== Java Records Completed! ===");
    }
    
    private static void demonstrateRecordBasics() {
        System.out.println("1. RECORD BASICS AND DECLARATION:\n");
        
        // Q1: What are Java Records?
        System.out.println("Q1: What are Java Records?");
        System.out.println("    Immutable data carriers introduced in Java 14");
        System.out.println("    Automatically generate boilerplate code");
        System.out.println("    Provide a concise way to model data\n");
        
        // Q2: What is the syntax of Java Records?
        System.out.println("Q2: What is the syntax of Java Records?");
        System.out.println("    record RecordName(type1 field1, type2 field2) { }");
        System.out.println("    record RecordName(type1 field1, type2 field2) {");
        System.out.println("        // Custom methods and constructors");
        System.out.println("    }\n");
        
        // Demonstrate record basics
        System.out.println("Example: Basic Record Declaration");
        
        // Basic record
        Person person = new Person("John Doe", 30, "john@example.com");
        System.out.println("    Basic Record:");
        System.out.println("      Person: " + person);
        System.out.println("      Name: " + person.name());
        System.out.println("      Age: " + person.age());
        System.out.println("      Email: " + person.email());
        
        // Record with custom constructor
        Point point = new Point(3, 4);
        System.out.println("    Record with Custom Constructor:");
        System.out.println("      Point: " + point);
        System.out.println("      X: " + point.x());
        System.out.println("      Y: " + point.y());
        System.out.println("      Distance from origin: " + point.distanceFromOrigin());
        
        // Record with validation
        Email email = new Email("user@domain.com");
        System.out.println("    Record with Validation:");
        System.out.println("      Email: " + email);
        System.out.println("      Address: " + email.address());
        System.out.println("      Domain: " + email.domain());
        
        // Record with multiple constructors
        Rectangle rectangle1 = new Rectangle(5, 10);
        Rectangle rectangle2 = new Rectangle(5);
        System.out.println("    Record with Multiple Constructors:");
        System.out.println("      Rectangle 1: " + rectangle1);
        System.out.println("      Rectangle 2: " + rectangle2);
        System.out.println("      Area 1: " + rectangle1.area());
        System.out.println("      Area 2: " + rectangle2.area());
        
        // Record with static methods
        System.out.println("    Record with Static Methods:");
        Circle circle = Circle.create(5.0);
        System.out.println("      Circle: " + circle);
        System.out.println("      Area: " + circle.area());
        System.out.println("      Circumference: " + circle.circumference());
    }
    
    private static void demonstrateRecordFeatures() {
        System.out.println("\n2. RECORD FEATURES AND BENEFITS:\n");
        
        // Q3: What are the key features of Java Records?
        System.out.println("Q3: What are the key features of Java Records?");
        System.out.println("    - Immutable by default");
        System.out.println("    - Automatic getter methods");
        System.out.println("    - Automatic equals(), hashCode(), toString()");
        System.out.println("    - Can have custom methods and constructors\n");
        
        // Q4: What are the benefits of using Records?
        System.out.println("Q4: What are the benefits of using Records?");
        System.out.println("    - Reduce boilerplate code");
        System.out.println("    - Improve readability");
        System.out.println("    - Ensure immutability");
        System.out.println("    - Better performance\n");
        
        // Demonstrate record features
        System.out.println("Example: Record Features and Benefits");
        
        // Immutability
        System.out.println("    Immutability:");
        Student student = new Student("Alice", "Computer Science", 3.8);
        System.out.println("      Student: " + student);
        System.out.println("      GPA: " + student.gpa());
        
        // Try to modify (this would cause compilation error)
        // student.gpa = 4.0; // Compilation error!
        System.out.println("      Records are immutable - cannot modify fields");
        
        // Automatic methods
        System.out.println("    Automatic Methods:");
        Student student2 = new Student("Bob", "Mathematics", 3.9);
        System.out.println("      Student 1: " + student);
        System.out.println("      Student 2: " + student2);
        System.out.println("      Are equal? " + student.equals(student2));
        System.out.println("      Hash codes: " + student.hashCode() + " vs " + student2.hashCode());
        
        // Custom methods
        System.out.println("    Custom Methods:");
        Triangle triangle = new Triangle(3, 4, 5);
        System.out.println("      Triangle: " + triangle);
        System.out.println("      Is right triangle? " + triangle.isRightTriangle());
        System.out.println("      Area: " + triangle.area());
        System.out.println("      Perimeter: " + triangle.perimeter());
        
        // Nested records
        System.out.println("    Nested Records:");
        Address address = new Address("123 Main St", "Anytown", "12345");
        Employee employee = new Employee("Jane Smith", 50000, address);
        System.out.println("      Employee: " + employee);
        System.out.println("      Street: " + employee.address().street());
        System.out.println("      City: " + employee.address().city());
        
        // Record with generics
        System.out.println("    Record with Generics:");
        Pair<String, Integer> pair1 = new Pair<>("Key", 42);
        Pair<Double, String> pair2 = new Pair<>(3.14, "Pi");
        System.out.println("      Pair 1: " + pair1);
        System.out.println("      Pair 2: " + pair2);
        System.out.println("      First element of pair1: " + pair1.first());
        System.out.println("      Second element of pair2: " + pair2.second());
    }
    
    private static void demonstrateRecordUsage() {
        System.out.println("\n3. RECORD USAGE PATTERNS:\n");
        
        // Q5: When should you use Records?
        System.out.println("Q5: When should you use Records?");
        System.out.println("    - Data transfer objects (DTOs)");
        System.out.println("    - Value objects");
        System.out.println("    - Configuration objects");
        System.out.println("    - API response objects\n");
        
        // Q6: What are common usage patterns for Records?
        System.out.println("Q6: What are common usage patterns for Records?");
        System.out.println("    - API responses");
        System.out.println("    - Database results");
        System.out.println("    - Event data");
        System.out.println("    - Configuration settings\n");
        
        // Demonstrate usage patterns
        System.out.println("Example: Record Usage Patterns");
        
        // API Response pattern
        System.out.println("    API Response Pattern:");
        ApiResponse<String> response = new ApiResponse<>(200, "Success", "Data retrieved successfully");
        System.out.println("      Response: " + response);
        System.out.println("      Status: " + response.status());
        System.out.println("      Message: " + response.message());
        System.out.println("      Data: " + response.data());
        
        // Database Result pattern
        System.out.println("    Database Result Pattern:");
        QueryResult<User> queryResult = new QueryResult<>(true, 1, new User("john_doe", "john@example.com"));
        System.out.println("      Query Result: " + queryResult);
        System.out.println("      Success: " + queryResult.success());
        System.out.println("      Count: " + queryResult.count());
        System.out.println("      User: " + queryResult.data());
        
        // Event Data pattern
        System.out.println("    Event Data Pattern:");
        UserEvent userEvent = new UserEvent("USER_CREATED", "user123", System.currentTimeMillis());
        System.out.println("      User Event: " + userEvent);
        System.out.println("      Event Type: " + userEvent.eventType());
        System.out.println("      User ID: " + userEvent.userId());
        System.out.println("      Timestamp: " + userEvent.timestamp());
        
        // Configuration pattern
        System.out.println("    Configuration Pattern:");
        DatabaseConfig dbConfig = new DatabaseConfig("localhost", 5432, "mydb", "user", "password");
        System.out.println("      Database Config: " + dbConfig);
        System.out.println("      Connection String: " + dbConfig.getConnectionString());
        
        // Builder pattern with records
        System.out.println("    Builder Pattern with Records:");
        Product product = Product.builder()
                .name("Laptop")
                .price(999.99)
                .category("Electronics")
                .build();
        System.out.println("      Product: " + product);
        System.out.println("      Name: " + product.name());
        System.out.println("      Price: " + product.price());
        System.out.println("      Category: " + product.category());
        
        // Factory pattern with records
        System.out.println("    Factory Pattern with Records:");
        Shape shape1 = ShapeFactory.createCircle(5.0);
        Shape shape2 = ShapeFactory.createRectangle(4.0, 6.0);
        System.out.println("      Circle: " + shape1);
        System.out.println("      Rectangle: " + shape2);
    }
    
    private static void demonstrateBestPractices() {
        System.out.println("\n4. BEST PRACTICES:\n");
        
        // Q7: What are the best practices for Java Records?
        System.out.println("Q7: What are the best practices for Java Records?");
        System.out.println("    - Use for data carriers only");
        System.out.println("    - Keep records simple");
        System.out.println("    - Use meaningful names");
        System.out.println("    - Consider validation in constructors\n");
        
        // Q8: When should you NOT use Records?
        System.out.println("Q8: When should you NOT use Records?");
        System.out.println("    - When you need mutable state");
        System.out.println("    - When you need inheritance");
        System.out.println("    - When you need complex behavior");
        System.out.println("    - When you need to extend other classes\n");
        
        // Demonstrate best practices
        System.out.println("Example: Best Practices");
        
        // Good practices
        System.out.println("    Good Practices:");
        
        // Simple data carrier
        Color color = new Color(255, 128, 0);
        System.out.println("      Color: " + color);
        System.out.println("      RGB: " + color.red() + ", " + color.green() + ", " + color.blue());
        
        // Validation in constructor
        Age age = new Age(25);
        System.out.println("      Age: " + age);
        System.out.println("      Is adult? " + age.isAdult());
        
        // Custom methods for derived values
        DateRange dateRange = new DateRange("2024-01-01", "2024-12-31");
        System.out.println("      Date Range: " + dateRange);
        System.out.println("      Duration: " + dateRange.durationInDays() + " days");
        
        // Avoid bad practices
        System.out.println("    Avoid Bad Practices:");
        System.out.println("      - Don't add complex business logic");
        System.out.println("      - Don't make records too large");
        System.out.println("      - Don't use for mutable state");
        System.out.println("      - Don't use for inheritance");
        
        // Performance considerations
        System.out.println("    Performance Considerations:");
        System.out.println("      - Records are optimized for data access");
        System.out.println("      - Automatic methods are efficient");
        System.out.println("      - Good for high-frequency data operations");
        System.out.println("      - Consider for cache keys and values");
        
        // Testing considerations
        System.out.println("    Testing Considerations:");
        System.out.println("      - Test automatic methods");
        System.out.println("      - Test custom constructors");
        System.out.println("      - Test validation logic");
        System.out.println("      - Test custom methods");
        
        // Migration considerations
        System.out.println("    Migration Considerations:");
        System.out.println("      - Start with simple DTOs");
        System.out.println("      - Gradually replace POJOs");
        System.out.println("      - Update tests accordingly");
        System.out.println("      - Consider backward compatibility");
    }
    
    // ===== HELPER RECORDS =====
    
    // Basic record
    record Person(String name, int age, String email) {}
    
    // Record with custom constructor
    record Point(double x, double y) {
        public Point {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("Coordinates must be non-negative");
            }
        }
        
        public double distanceFromOrigin() {
            return Math.sqrt(x * x + y * y);
        }
    }
    
    // Record with validation
    record Email(String address) {
        public Email {
            if (address == null || !address.contains("@")) {
                throw new IllegalArgumentException("Invalid email address");
            }
        }
        
        public String domain() {
            return address.substring(address.indexOf("@") + 1);
        }
    }
    
    // Record with multiple constructors
    record Rectangle(double length, double width) {
        public Rectangle(double side) {
            this(side, side);
        }
        
        public double area() {
            return length * width;
        }
    }
    
    // Record with static methods
    record Circle(double radius) {
        public static Circle create(double radius) {
            if (radius <= 0) {
                throw new IllegalArgumentException("Radius must be positive");
            }
            return new Circle(radius);
        }
        
        public double area() {
            return Math.PI * radius * radius;
        }
        
        public double circumference() {
            return 2 * Math.PI * radius;
        }
    }
    
    // Record with custom methods
    record Student(String name, String major, double gpa) {
        public boolean isHonorStudent() {
            return gpa >= 3.5;
        }
    }
    
    // Record with validation and custom methods
    record Triangle(double a, double b, double c) {
        public Triangle {
            if (a <= 0 || b <= 0 || c <= 0) {
                throw new IllegalArgumentException("Sides must be positive");
            }
            if (a + b <= c || a + c <= b || b + c <= a) {
                throw new IllegalArgumentException("Invalid triangle");
            }
        }
        
        public boolean isRightTriangle() {
            double[] sides = {a, b, c};
            java.util.Arrays.sort(sides);
            return Math.abs(sides[0] * sides[0] + sides[1] * sides[1] - sides[2] * sides[2]) < 0.001;
        }
        
        public double area() {
            double s = (a + b + c) / 2;
            return Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }
        
        public double perimeter() {
            return a + b + c;
        }
    }
    
    // Nested records
    record Address(String street, String city, String zipCode) {}
    record Employee(String name, double salary, Address address) {}
    
    // Record with generics
    record Pair<T, U>(T first, U second) {}
    
    // API Response pattern
    record ApiResponse<T>(int status, String message, T data) {}
    
    // Database Result pattern
    record QueryResult<T>(boolean success, int count, T data) {}
    record User(String username, String email) {}
    
    // Event Data pattern
    record UserEvent(String eventType, String userId, long timestamp) {}
    
    // Configuration pattern
    record DatabaseConfig(String host, int port, String database, String username, String password) {
        public String getConnectionString() {
            return String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
        }
    }
    
    // Builder pattern with records
    record Product(String name, double price, String category) {
        public static ProductBuilder builder() {
            return new ProductBuilder();
        }
        
        static class ProductBuilder {
            private String name;
            private double price;
            private String category;
            
            public ProductBuilder name(String name) {
                this.name = name;
                return this;
            }
            
            public ProductBuilder price(double price) {
                this.price = price;
                return this;
            }
            
            public ProductBuilder category(String category) {
                this.category = category;
                return this;
            }
            
            public Product build() {
                return new Product(name, price, category);
            }
        }
    }
    
    // Factory pattern with records
    record Shape(String type, double... dimensions) {}
    
    static class ShapeFactory {
        public static Shape createCircle(double radius) {
            return new Shape("Circle", radius);
        }
        
        public static Shape createRectangle(double length, double width) {
            return new Shape("Rectangle", length, width);
        }
    }
    
    // Best practices examples
    record Color(int red, int green, int blue) {
        public Color {
            if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
                throw new IllegalArgumentException("RGB values must be between 0 and 255");
            }
        }
    }
    
    record Age(int years) {
        public Age {
            if (years < 0 || years > 150) {
                throw new IllegalArgumentException("Age must be between 0 and 150");
            }
        }
        
        public boolean isAdult() {
            return years >= 18;
        }
    }
    
    record DateRange(String startDate, String endDate) {
        public int durationInDays() {
            // Simplified calculation - in real world, use proper date parsing
            return 365; // Placeholder
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA RECORDS:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are Java Records?
 * 2. When were Java Records introduced?
 * 3. What is the purpose of Java Records?
 * 4. What is the basic syntax of a Record?
 * 5. How do you declare a Record?
 * 6. What are the components of a Record?
 * 7. What is the difference between Record and Class?
 * 8. What is the difference between Record and Interface?
 * 9. What is the difference between Record and Enum?
 * 10. What is the difference between Record and Annotation?
 * 11. What is the difference between Record and Abstract Class?
 * 12. What is the difference between Record and Final Class?
 * 13. What is the difference between Record and Sealed Class?
 * 14. What is the difference between Record and Inner Class?
 * 15. What is the difference between Record and Anonymous Class?
 * 16. What is the difference between Record and Local Class?
 * 17. What is the difference between Record and Static Class?
 * 18. What is the difference between Record and Generic Class?
 * 19. What is the difference between Record and Array?
 * 20. What is the difference between Record and Collection?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. How do you create a Record with custom constructor?
 * 22. How do you add custom methods to a Record?
 * 23. How do you add static methods to a Record?
 * 24. How do you add validation to a Record?
 * 25. How do you create a Record with multiple constructors?
 * 26. How do you use Records with generics?
 * 27. How do you create nested Records?
 * 28. How do you use Records with inheritance?
 * 29. How do you use Records with interfaces?
 * 30. How do you use Records with annotations?
 * 31. How do you use Records with reflection?
 * 32. How do you use Records with serialization?
 * 33. How do you use Records with JSON?
 * 34. How do you use Records with XML?
 * 35. How do you use Records with databases?
 * 36. How do you use Records with APIs?
 * 37. How do you use Records with testing?
 * 38. How do you use Records with mocking?
 * 39. How do you use Records with validation?
 * 40. How do you use Records with transformation?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you implement Builder pattern with Records?
 * 42. How do you implement Factory pattern with Records?
 * 43. How do you implement Strategy pattern with Records?
 * 44. How do you implement Command pattern with Records?
 * 45. How do you implement Observer pattern with Records?
 * 46. How do you implement State pattern with Records?
 * 47. How do you implement Visitor pattern with Records?
 * 48. How do you implement Decorator pattern with Records?
 * 49. How do you implement Adapter pattern with Records?
 * 50. How do you implement Proxy pattern with Records?
 * 51. How do you implement Chain of Responsibility with Records?
 * 52. How do you implement Template Method with Records?
 * 53. How do you implement Mediator pattern with Records?
 * 54. How do you implement Memento pattern with Records?
 * 55. How do you implement Interpreter pattern with Records?
 * 56. How do you implement Bridge pattern with Records?
 * 57. How do you implement Composite pattern with Records?
 * 58. How do you implement Flyweight pattern with Records?
 * 59. How do you implement Prototype pattern with Records?
 * 60. How do you implement Singleton pattern with Records?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you design Record-based architectures?
 * 62. How do you implement Record-based frameworks?
 * 63. How do you implement Record-based libraries?
 * 64. How do you implement Record-based tools?
 * 65. How do you implement Record-based APIs?
 * 66. How do you implement Record-based services?
 * 67. How do you implement Record-based microservices?
 * 68. How do you implement Record-based event systems?
 * 69. How do you implement Record-based messaging?
 * 70. How do you implement Record-based caching?
 * 71. How do you implement Record-based validation?
 * 72. How do you implement Record-based transformation?
 * 73. How do you implement Record-based aggregation?
 * 74. How do you implement Record-based composition?
 * 75. How do you implement Record-based inheritance?
 * 76. How do you implement Record-based polymorphism?
 * 77. How do you implement Record-based design patterns?
 * 78. How do you implement Record-based testing?
 * 79. How do you implement Record-based debugging?
 * 80. How do you implement Record-based profiling?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a Record-based microservices architecture?
 * 82. How would you implement Record-based API gateways?
 * 83. How would you design Record-based event systems?
 * 84. How would you implement Record-based message processing?
 * 85. How would you design Record-based workflow engines?
 * 86. How would you implement Record-based rule engines?
 * 87. How would you design Record-based decision systems?
 * 88. How would you implement Record-based recommendation engines?
 * 89. How would you design Record-based search systems?
 * 90. How would you implement Record-based analytics platforms?
 * 91. How would you design Record-based machine learning?
 * 92. How would you implement Record-based blockchain systems?
 * 93. How would you design Record-based gaming engines?
 * 94. How would you implement Record-based IoT platforms?
 * 95. How would you design Record-based social media?
 * 96. How would you implement Record-based e-commerce systems?
 * 97. How would you design Record-based healthcare systems?
 * 98. How would you implement Record-based financial systems?
 * 99. How would you design Record-based autonomous systems?
 * 100. How would you implement Record-based quantum computing?
 */
