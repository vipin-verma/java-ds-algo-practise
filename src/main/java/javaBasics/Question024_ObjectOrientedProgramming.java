import java.util.*;

/**
 * Question 24: Object-Oriented Programming (OOP)
 * 
 * This file contains 50+ OOP interview questions covering:
 * - Classes and Objects
 * - Inheritance and Polymorphism
 * - Encapsulation and Abstraction
 * - Interfaces and Abstract Classes
 * - OOP Principles and Design Patterns
 * - Advanced OOP Concepts
 */
public class Question024_ObjectOrientedProgramming {
    
    public static void main(String[] args) {
        System.out.println("=== Object-Oriented Programming - Interview Questions ===\n");
        
        demonstrateClassesAndObjects();
        demonstrateInheritance();
        demonstratePolymorphism();
        demonstrateEncapsulation();
        demonstrateAbstraction();
        demonstrateInterfaces();
        demonstrateAbstractClasses();
        demonstrateOOPPrinciples();
        
        System.out.println("\n=== Object-Oriented Programming Completed! ===");
    }
    
    // ===== CLASSES AND OBJECTS =====
    
    private static void demonstrateClassesAndObjects() {
        System.out.println("1. CLASSES AND OBJECTS:\n");
        
        // Q1: What is a class?
        System.out.println("Q1: What is a class?");
        System.out.println("    A class is a blueprint or template for creating objects.");
        System.out.println("    It defines the properties (fields) and behaviors (methods)");
        System.out.println("    that objects of that class will have.\n");
        
        // Q2: What is an object?
        System.out.println("Q2: What is an object?");
        System.out.println("    An object is an instance of a class.");
        System.out.println("    It has state (values of fields) and behavior (methods).");
        System.out.println("    Objects are created using the 'new' keyword.\n");
        
        // Q3: What is the difference between class and object?
        System.out.println("Q3: What is the difference between class and object?");
        System.out.println("    Class: Template/blueprint, exists in memory once");
        System.out.println("    Object: Instance of class, can be multiple instances");
        System.out.println("    Class is like a mold, object is like the product from the mold\n");
        
        // Q4: What is a constructor?
        System.out.println("Q4: What is a constructor?");
        System.out.println("    A constructor is a special method with the same name as the class");
        System.out.println("    It initializes the object's state when created");
        System.out.println("    It can be overloaded with different parameters\n");
        
        // Q5: What is method overloading?
        System.out.println("Q5: What is method overloading?");
        System.out.println("    Method overloading allows multiple methods with the same name");
        System.out.println("    but different parameters (number, type, or order)");
        System.out.println("    It provides compile-time polymorphism\n");
        
        // Demonstrate with examples
        System.out.println("Example: Method Overloading");
        Calculator calc = new Calculator();
        System.out.println("    add(5, 3): " + calc.add(5, 3));
        System.out.println("    add(5.5, 3.2): " + calc.add(5.5, 3.2));
        System.out.println("    add(5, 3, 2): " + calc.add(5, 3, 2) + "\n");
    }
    
    // ===== INHERITANCE =====
    
    private static void demonstrateInheritance() {
        System.out.println("2. INHERITANCE:\n");
        
        // Q6: What is inheritance?
        System.out.println("Q6: What is inheritance?");
        System.out.println("    Inheritance allows a class to inherit properties and methods");
        System.out.println("    from another class (parent/superclass)");
        System.out.println("    It promotes code reuse and establishes 'is-a' relationship\n");
        
        // Q7: What are the types of inheritance?
        System.out.println("Q7: What are the types of inheritance?");
        System.out.println("    Single inheritance: One class extends another");
        System.out.println("    Multilevel inheritance: Chain of inheritance");
        System.out.println("    Hierarchical inheritance: Multiple classes extend one class");
        System.out.println("    Multiple inheritance: Not supported in Java (only interfaces)\n");
        
        // Q8: What is the difference between extends and implements?
        System.out.println("Q8: What is the difference between extends and implements?");
        System.out.println("    extends: Used for class inheritance (single inheritance)");
        System.out.println("    implements: Used for interface implementation (multiple)");
        System.out.println("    A class can extend one class and implement multiple interfaces\n");
        
        // Q9: What is method overriding?
        System.out.println("Q9: What is method overriding?");
        System.out.println("    Method overriding allows a subclass to provide a specific");
        System.out.println("    implementation of a method defined in its superclass");
        System.out.println("    It provides runtime polymorphism\n");
        
        // Q10: What are the rules for method overriding?
        System.out.println("Q10: What are the rules for method overriding?");
        System.out.println("    Method signature must be the same");
        System.out.println("    Return type must be the same or covariant");
        System.out.println("    Access modifier cannot be more restrictive");
        System.out.println("    Cannot override final, static, or private methods\n");
        
        // Demonstrate inheritance
        System.out.println("Example: Inheritance and Method Overriding");
        Animal animal = new Animal("Generic Animal");
        Dog dog = new Dog("Buddy", "Golden Retriever");
        
        System.out.println("    Animal sound: " + animal.makeSound());
        System.out.println("    Dog sound: " + dog.makeSound());
        System.out.println("    Dog breed: " + dog.getBreed() + "\n");
    }
    
    // ===== POLYMORPHISM =====
    
    private static void demonstratePolymorphism() {
        System.out.println("3. POLYMORPHISM:\n");
        
        // Q11: What is polymorphism?
        System.out.println("Q11: What is polymorphism?");
        System.out.println("    Polymorphism means 'many forms' - the ability to take");
        System.out.println("    different forms or behaviors based on the context");
        System.out.println("    It allows one interface to be used for different types\n");
        
        // Q12: What are the types of polymorphism?
        System.out.println("Q12: What are the types of polymorphism?");
        System.out.println("    Compile-time polymorphism: Method overloading");
        System.out.println("    Runtime polymorphism: Method overriding");
        System.out.println("    Runtime polymorphism is also called dynamic binding\n");
        
        // Q13: What is dynamic binding?
        System.out.println("Q13: What is dynamic binding?");
        System.out.println("    Dynamic binding means the method call is resolved");
        System.out.println("    at runtime based on the actual object type");
        System.out.println("    It's the foundation of runtime polymorphism\n");
        
        // Q14: What is the difference between compile-time and runtime polymorphism?
        System.out.println("Q14: What is the difference between compile-time and runtime polymorphism?");
        System.out.println("    Compile-time: Method call resolved at compile time");
        System.out.println("    Runtime: Method call resolved at runtime");
        System.out.println("    Compile-time is faster, runtime is more flexible\n");
        
        // Demonstrate polymorphism
        System.out.println("Example: Runtime Polymorphism");
        Animal[] animals = {
            new Animal("Generic Animal"),
            new Dog("Buddy", "Golden Retriever"),
            new Cat("Whiskers", "Persian")
        };
        
        for (Animal a : animals) {
            System.out.println("    " + a.getName() + " says: " + a.makeSound());
        }
        System.out.println();
    }
    
    // ===== ENCAPSULATION =====
    
    private static void demonstrateEncapsulation() {
        System.out.println("4. ENCAPSULATION:\n");
        
        // Q15: What is encapsulation?
        System.out.println("Q15: What is encapsulation?");
        System.out.println("    Encapsulation is the bundling of data and methods");
        System.out.println("    that operate on that data within a single unit (class)");
        System.out.println("    It hides internal state and requires controlled access\n");
        
        // Q16: How is encapsulation achieved in Java?
        System.out.println("Q16: How is encapsulation achieved in Java?");
        System.out.println("    Using access modifiers: private, protected, public, default");
        System.out.println("    Private fields with public getter/setter methods");
        System.out.println("    Data hiding and controlled access to object state\n");
        
        // Q17: What are access modifiers?
        System.out.println("Q17: What are access modifiers?");
        System.out.println("    private: Accessible only within the class");
        System.out.println("    default: Accessible within the package");
        System.out.println("    protected: Accessible within package and subclasses");
        System.out.println("    public: Accessible from anywhere\n");
        
        // Q18: What is the benefit of encapsulation?
        System.out.println("Q18: What is the benefit of encapsulation?");
        System.out.println("    Data hiding and protection");
        System.out.println("    Controlled access to object state");
        System.out.println("    Easier maintenance and modification");
        System.out.println("    Better code organization\n");
        
        // Demonstrate encapsulation
        System.out.println("Example: Encapsulation");
        BankAccount account = new BankAccount("12345", 1000.0);
        System.out.println("    Account: " + account.getAccountNumber());
        System.out.println("    Balance: " + account.getBalance());
        
        account.deposit(500.0);
        System.out.println("    After deposit: " + account.getBalance());
        
        account.withdraw(200.0);
        System.out.println("    After withdrawal: " + account.getBalance());
        
        // account.balance = -1000; // This would cause compilation error
        System.out.println();
    }
    
    // ===== ABSTRACTION =====
    
    private static void demonstrateAbstraction() {
        System.out.println("5. ABSTRACTION:\n");
        
        // Q19: What is abstraction?
        System.out.println("Q19: What is abstraction?");
        System.out.println("    Abstraction is hiding complex implementation details");
        System.out.println("    and showing only necessary features to the user");
        System.out.println("    It focuses on what an object does rather than how\n");
        
        // Q20: How is abstraction achieved in Java?
        System.out.println("Q20: How is abstraction achieved in Java?");
        System.out.println("    Abstract classes and interfaces");
        System.out.println("    Method abstraction (hiding implementation)");
        System.out.println("    Data abstraction (hiding data structure)");
        System.out.println("    Using abstract methods and concrete implementations\n");
        
        // Q21: What is the difference between abstraction and encapsulation?
        System.out.println("Q21: What is the difference between abstraction and encapsulation?");
        System.out.println("    Abstraction: Hiding complexity, showing essential features");
        System.out.println("    Encapsulation: Bundling data and methods, controlling access");
        System.out.println("    Abstraction is about design, encapsulation is about implementation\n");
        
        // Demonstrate abstraction
        System.out.println("Example: Abstraction");
        Shape[] shapes = {
            new Circle(5.0),
            new Rectangle(4.0, 6.0),
            new Triangle(3.0, 4.0, 5.0)
        };
        
        for (Shape shape : shapes) {
            System.out.println("    " + shape.getClass().getSimpleName() + 
                             " area: " + String.format("%.2f", shape.calculateArea()));
        }
        System.out.println();
    }
    
    // ===== INTERFACES =====
    
    private static void demonstrateInterfaces() {
        System.out.println("6. INTERFACES:\n");
        
        // Q22: What is an interface?
        System.out.println("Q22: What is an interface?");
        System.out.println("    An interface is a collection of abstract methods");
        System.out.println("    It defines a contract that implementing classes must follow");
        System.out.println("    It supports multiple inheritance and provides abstraction\n");
        
        // Q23: What are the features of interfaces?
        System.out.println("Q23: What are the features of interfaces?");
        System.out.println("    All methods are public and abstract by default");
        System.out.println("    All fields are public, static, and final by default");
        System.out.println("    Can have default and static methods (Java 8+)");
        System.out.println("    Can have private methods (Java 9+)\n");
        
        // Q24: What is the difference between interface and abstract class?
        System.out.println("Q24: What is the difference between interface and abstract class?");
        System.out.println("    Interface: All methods abstract, multiple inheritance");
        System.out.println("    Abstract class: Can have concrete methods, single inheritance");
        System.out.println("    Interface: No state, Abstract class: Can have state\n");
        
        // Q25: What are functional interfaces?
        System.out.println("Q25: What are functional interfaces?");
        System.out.println("    Functional interfaces have exactly one abstract method");
        System.out.println("    They can be used with lambda expressions");
        System.out.println("    Examples: Runnable, Callable, Comparator, Predicate\n");
        
        // Demonstrate interfaces
        System.out.println("Example: Interface Implementation");
        List<Payable> employees = Arrays.asList(
            new FullTimeEmployee("John", 50000),
            new PartTimeEmployee("Jane", 25, 20)
        );
        
        for (Payable employee : employees) {
            System.out.println("    " + employee.getName() + " salary: $" + 
                             String.format("%.2f", employee.calculateSalary()));
        }
        System.out.println();
    }
    
    // ===== ABSTRACT CLASSES =====
    
    private static void demonstrateAbstractClasses() {
        System.out.println("7. ABSTRACT CLASSES:\n");
        
        // Q26: What is an abstract class?
        System.out.println("Q26: What is an abstract class?");
        System.out.println("    An abstract class is a class that cannot be instantiated");
        System.out.println("    It can contain abstract and concrete methods");
        System.out.println("    It serves as a base class for other classes\n");
        
        // Q27: When to use abstract class vs interface?
        System.out.println("Q27: When to use abstract class vs interface?");
        System.out.println("    Abstract class: When you want to share code among classes");
        System.out.println("    Interface: When you want to define a contract");
        System.out.println("    Abstract class: When you want to declare non-public members");
        System.out.println("    Interface: When you want to achieve multiple inheritance\n");
        
        // Q28: Can abstract class have constructor?
        System.out.println("Q28: Can abstract class have constructor?");
        System.out.println("    Yes, abstract classes can have constructors");
        System.out.println("    They are called when creating concrete subclass instances");
        System.out.println("    They can initialize common fields and perform setup\n");
        
        // Demonstrate abstract classes
        System.out.println("Example: Abstract Class");
        List<Vehicle> vehicles = Arrays.asList(
            new Car("Toyota", "Camry", 4),
            new Motorcycle("Honda", "CBR", 2)
        );
        
        for (Vehicle vehicle : vehicles) {
            System.out.println("    " + vehicle.getBrand() + " " + vehicle.getModel());
            System.out.println("    Wheels: " + vehicle.getWheels());
            vehicle.start();
            vehicle.stop();
            System.out.println();
        }
    }
    
    // ===== OOP PRINCIPLES =====
    
    private static void demonstrateOOPPrinciples() {
        System.out.println("8. OOP PRINCIPLES:\n");
        
        // Q29: What are the SOLID principles?
        System.out.println("Q29: What are the SOLID principles?");
        System.out.println("    S: Single Responsibility Principle");
        System.out.println("    O: Open/Closed Principle");
        System.out.println("    L: Liskov Substitution Principle");
        System.out.println("    I: Interface Segregation Principle");
        System.out.println("    D: Dependency Inversion Principle\n");
        
        // Q30: What is the Single Responsibility Principle?
        System.out.println("Q30: What is the Single Responsibility Principle?");
        System.out.println("    A class should have only one reason to change");
        System.out.println("    It should have only one responsibility");
        System.out.println("    Example: User class handles user data, not email sending\n");
        
        // Demonstrate OOP principles
        System.out.println("Example: SOLID Principles");
        NotificationService emailService = new EmailNotificationService();
        NotificationService smsService = new SMSNotificationService();
        
        UserService userService = new UserService(emailService);
        userService.createUser("john@example.com", "John Doe");
        
        userService.setNotificationService(smsService);
        userService.createUser("jane@example.com", "Jane Doe");
    }
    
    // ===== HELPER CLASSES =====
    
    static class Calculator {
        public int add(int a, int b) {
            return a + b;
        }
        
        public double add(double a, double b) {
            return a + b;
        }
        
        public int add(int a, int b, int c) {
            return a + b + c;
        }
    }
    
    static class Animal {
        protected String name;
        
        public Animal(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
        
        public String makeSound() {
            return "Some sound";
        }
    }
    
    static class Dog extends Animal {
        private String breed;
        
        public Dog(String name, String breed) {
            super(name);
            this.breed = breed;
        }
        
        @Override
        public String makeSound() {
            return "Woof!";
        }
        
        public String getBreed() {
            return breed;
        }
    }
    
    static class Cat extends Animal {
        private String breed;
        
        public Cat(String name, String breed) {
            super(name);
            this.breed = breed;
        }
        
        @Override
        public String makeSound() {
            return "Meow!";
        }
    }
    
    static class BankAccount {
        private String accountNumber;
        private double balance;
        
        public BankAccount(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }
        
        public String getAccountNumber() {
            return accountNumber;
        }
        
        public double getBalance() {
            return balance;
        }
        
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }
        
        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }
    }
    
    abstract static class Shape {
        public abstract double calculateArea();
    }
    
    static class Circle extends Shape {
        private double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }
    
    static class Rectangle extends Shape {
        private double width, height;
        
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        @Override
        public double calculateArea() {
            return width * height;
        }
    }
    
    static class Triangle extends Shape {
        private double a, b, c;
        
        public Triangle(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        @Override
        public double calculateArea() {
            double s = (a + b + c) / 2;
            return Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }
    }
    
    interface Payable {
        String getName();
        double calculateSalary();
    }
    
    static class FullTimeEmployee implements Payable {
        private String name;
        private double salary;
        
        public FullTimeEmployee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }
        
        @Override
        public String getName() {
            return name;
        }
        
        @Override
        public double calculateSalary() {
            return salary;
        }
    }
    
    static class PartTimeEmployee implements Payable {
        private String name;
        private double hourlyRate;
        private int hoursWorked;
        
        public PartTimeEmployee(String name, double hourlyRate, int hoursWorked) {
            this.name = name;
            this.hourlyRate = hourlyRate;
            this.hoursWorked = hoursWorked;
        }
        
        @Override
        public String getName() {
            return name;
        }
        
        @Override
        public double calculateSalary() {
            return hourlyRate * hoursWorked;
        }
    }
    
    abstract static class Vehicle {
        protected String brand;
        protected String model;
        protected int wheels;
        
        public Vehicle(String brand, String model, int wheels) {
            this.brand = brand;
            this.model = model;
            this.wheels = wheels;
        }
        
        public String getBrand() { return brand; }
        public String getModel() { return model; }
        public int getWheels() { return wheels; }
        
        public abstract void start();
        public abstract void stop();
    }
    
    static class Car extends Vehicle {
        public Car(String brand, String model, int wheels) {
            super(brand, model, wheels);
        }
        
        @Override
        public void start() {
            System.out.println("    Car engine started");
        }
        
        @Override
        public void stop() {
            System.out.println("    Car engine stopped");
        }
    }
    
    static class Motorcycle extends Vehicle {
        public Motorcycle(String brand, String model, int wheels) {
            super(brand, model, wheels);
        }
        
        @Override
        public void start() {
            System.out.println("    Motorcycle engine started");
        }
        
        @Override
        public void stop() {
            System.out.println("    Motorcycle engine stopped");
        }
    }
    
    interface NotificationService {
        void sendNotification(String message);
    }
    
    static class EmailNotificationService implements NotificationService {
        @Override
        public void sendNotification(String message) {
            System.out.println("    Sending email: " + message);
        }
    }
    
    static class SMSNotificationService implements NotificationService {
        @Override
        public void sendNotification(String message) {
            System.out.println("    Sending SMS: " + message);
        }
    }
    
    static class UserService {
        private NotificationService notificationService;
        
        public UserService(NotificationService notificationService) {
            this.notificationService = notificationService;
        }
        
        public void setNotificationService(NotificationService notificationService) {
            this.notificationService = notificationService;
        }
        
        public void createUser(String email, String name) {
            // Create user logic
            System.out.println("    Creating user: " + name + " (" + email + ")");
            notificationService.sendNotification("Welcome " + name + "!");
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR OBJECT-ORIENTED PROGRAMMING:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What is Object-Oriented Programming?
 * 2. What are the main principles of OOP?
 * 3. What is a class and how do you create one?
 * 4. What is an object and how do you create one?
 * 5. What is the difference between class and object?
 * 6. What is a constructor and why do we need it?
 * 7. What is method overloading?
 * 8. What are access modifiers in Java?
 * 9. What is the difference between public and private?
 * 10. What is the 'this' keyword used for?
 * 11. What is the 'super' keyword used for?
 * 12. What is inheritance and why do we use it?
 * 13. What is the 'extends' keyword?
 * 14. What is method overriding?
 * 15. What is the difference between overloading and overriding?
 * 16. What is polymorphism?
 * 17. What are the types of polymorphism?
 * 18. What is encapsulation?
 * 19. What is abstraction?
 * 20. What is the difference between abstraction and encapsulation?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. What is an interface and when do you use it?
 * 22. What is the difference between interface and abstract class?
 * 23. What is a functional interface?
 * 24. What are default methods in interfaces?
 * 25. What are static methods in interfaces?
 * 26. What is the 'implements' keyword?
 * 27. Can a class implement multiple interfaces?
 * 28. Can an interface extend another interface?
 * 29. What is an abstract class?
 * 30. When to use abstract class vs interface?
 * 31. Can abstract class have constructor?
 * 32. Can abstract class have concrete methods?
 * 33. What is the difference between final, finally, and finalize?
 * 34. What is the 'final' keyword used for?
 * 35. What is the 'static' keyword used for?
 * 36. What is the difference between static and instance methods?
 * 37. What is the difference between static and instance variables?
 * 38. What is a static block?
 * 39. What is an instance block?
 * 40. What is the order of execution of blocks and constructors?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. What are the SOLID principles?
 * 42. Explain the Single Responsibility Principle.
 * 43. Explain the Open/Closed Principle.
 * 44. Explain the Liskov Substitution Principle.
 * 45. Explain the Interface Segregation Principle.
 * 46. Explain the Dependency Inversion Principle.
 * 47. What is composition vs inheritance?
 * 48. When to use composition over inheritance?
 * 49. What is the difference between early and late binding?
 * 50. What is dynamic method dispatch?
 * 51. What is the difference between compile-time and runtime polymorphism?
 * 52. What is method hiding?
 * 53. What is the difference between method hiding and method overriding?
 * 54. What is covariant return type?
 * 55. What is the difference between shallow and deep copying?
 * 56. How do you implement clone() method?
 * 57. What is the difference between equals() and ==?
 * 58. How do you implement equals() and hashCode() methods?
 * 59. What is the contract between equals() and hashCode()?
 * 60. What is the toString() method and when to override it?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you implement the singleton pattern?
 * 62. How do you implement the factory pattern?
 * 63. How do you implement the builder pattern?
 * 64. How do you implement the observer pattern?
 * 65. How do you implement the strategy pattern?
 * 66. How do you implement the command pattern?
 * 67. How do you implement the template method pattern?
 * 68. How do you implement the adapter pattern?
 * 69. How do you implement the decorator pattern?
 * 70. How do you implement the proxy pattern?
 * 71. What is the difference between aggregation and composition?
 * 72. How do you implement the visitor pattern?
 * 73. How do you implement the state pattern?
 * 74. How do you implement the chain of responsibility pattern?
 * 75. How do you implement the memento pattern?
 * 76. What is the difference between abstract class and concrete class?
 * 77. How do you implement the bridge pattern?
 * 78. How do you implement the flyweight pattern?
 * 79. How do you implement the interpreter pattern?
 * 80. How do you implement the mediator pattern?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a class hierarchy for a banking system?
 * 82. Design a class structure for an e-commerce system.
 * 83. How would you implement a plugin architecture?
 * 84. Design a class hierarchy for a game engine.
 * 85. How would you implement a custom ORM framework?
 * 86. Design a class structure for a social media platform.
 * 87. How would you implement a custom dependency injection container?
 * 88. Design a class hierarchy for a content management system.
 * 89. How would you implement a custom workflow engine?
 * 90. Design a class structure for a real-time chat application.
 * 91. How would you implement a custom rule engine?
 * 92. Design a class hierarchy for a file management system.
 * 93. How would you implement a custom event system?
 * 94. Design a class structure for a task scheduling system.
 * 95. How would you implement a custom validation framework?
 * 96. Design a class hierarchy for a reporting system.
 * 97. How would you implement a custom caching system?
 * 98. Design a class structure for a notification system.
 * 99. How would you implement a custom logging framework?
 * 100. Design a class hierarchy for a configuration management system.
 */
