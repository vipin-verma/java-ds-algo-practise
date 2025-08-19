// Abstract class - demonstrates abstraction
abstract class Animal {
    // Protected variables - demonstrates encapsulation
    protected String name;
    protected int age;
    
    // Constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract void makeSound();
    
    // Concrete method
    public void sleep() {
        System.out.println(name + " is sleeping...");
    }
    
    // Getter methods - encapsulation
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
}

// Interface - demonstrates multiple inheritance and abstraction
interface Swimmable {
    void swim();
}

interface Flyable {
    void fly();
}

// Single inheritance - Dog extends Animal
class Dog extends Animal {
    private String breed;  // Private variable - encapsulation
    
    public Dog(String name, int age, String breed) {
        super(name, age);  // Call parent constructor
        this.breed = breed;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof! Woof!");
    }
    
    // Method specific to Dog class
    public void fetch() {
        System.out.println(name + " is fetching the ball!");
    }
    
    public String getBreed() {
        return breed;
    }
}

// Multiple inheritance through interfaces
class Duck extends Animal implements Swimmable, Flyable {
    public Duck(String name, int age) {
        super(name, age);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Quack! Quack!");
    }
    
    @Override
    public void swim() {
        System.out.println(name + " is swimming in the pond!");
    }
    
    @Override
    public void fly() {
        System.out.println(name + " is flying in the sky!");
    }
}

// Final class - cannot be inherited
final class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow! Meow!");
    }
    
    // Final method - cannot be overridden
    public final void purr() {
        System.out.println(name + " is purring...");
    }
}

// Main class to demonstrate OOP concepts
public class OOPConcepts {
    
    public static void main(String[] args) {
        System.out.println("=== Object-Oriented Programming Concepts ===\n");
        
        // 1. Inheritance and Polymorphism
        demonstrateInheritanceAndPolymorphism();
        
        // 2. Encapsulation
        demonstrateEncapsulation();
        
        // 3. Abstraction
        demonstrateAbstraction();
        
        // 4. Interface Implementation
        demonstrateInterfaces();
        
        // 5. Method Overriding and Overloading
        demonstrateMethodOverriding();
        demonstrateMethodOverloading();
        
        // 6. Final Keyword
        demonstrateFinalKeyword();
        
        // 7. Super Keyword
        demonstrateSuperKeyword();
    }
    
    private static void demonstrateInheritanceAndPolymorphism() {
        System.out.println("1. INHERITANCE AND POLYMORPHISM:");
        
        // Creating objects of different types
        Animal dog = new Dog("Buddy", 3, "Golden Retriever");
        Animal duck = new Duck("Donald", 2);
        Animal cat = new Cat("Whiskers", 4);
        
        // Polymorphism - same method call, different behavior
        System.out.println("Polymorphic behavior:");
        dog.makeSound();  // Calls Dog's makeSound()
        duck.makeSound(); // Calls Duck's makeSound()
        cat.makeSound();  // Calls Cat's makeSound()
        
        // Using Animal reference but specific behavior
        Animal[] animals = {dog, duck, cat};
        System.out.println("\nLooping through animals:");
        for (Animal animal : animals) {
            animal.makeSound();  // Polymorphic call
            animal.sleep();      // Inherited method
        }
    }
    
    private static void demonstrateEncapsulation() {
        System.out.println("\n2. ENCAPSULATION:");
        
        Dog dog = new Dog("Max", 5, "Labrador");
        
        // Accessing through public methods (getters)
        System.out.println("Dog name: " + dog.getName());
        System.out.println("Dog age: " + dog.getAge());
        System.out.println("Dog breed: " + dog.getBreed());
        
        // Cannot access private variables directly
        // dog.breed = "Poodle";  // This would cause compilation error
        
        // Public methods provide controlled access
        dog.fetch();
    }
    
    private static void demonstrateAbstraction() {
        System.out.println("\n3. ABSTRACTION:");
        
        // Cannot create object of abstract class
        // Animal animal = new Animal("Generic", 1);  // This would cause error
        
        // But we can use abstract class reference
        Animal dog = new Dog("Rex", 2, "German Shepherd");
        
        // Using abstract method
        dog.makeSound();
        
        // Using concrete method from abstract class
        dog.sleep();
        
        System.out.println("Abstract class provides common interface for all animals");
    }
    
    private static void demonstrateInterfaces() {
        System.out.println("\n4. INTERFACES:");
        
        Duck duck = new Duck("Mallard", 1);
        
        // Duck can swim and fly (multiple inheritance through interfaces)
        duck.swim();
        duck.fly();
        
        // Using interface references
        Swimmable swimmable = duck;
        Flyable flyable = duck;
        
        System.out.println("\nUsing interface references:");
        swimmable.swim();
        flyable.fly();
        
        // Demonstrating multiple interface implementation
        System.out.println("\nDuck implements multiple interfaces:");
        System.out.println("Swimmable: " + (duck instanceof Swimmable));
        System.out.println("Flyable: " + (duck instanceof Flyable));
        System.out.println("Animal: " + (duck instanceof Animal));
    }
    
    private static void demonstrateMethodOverriding() {
        System.out.println("\n5. METHOD OVERRIDING:");
        
        Animal dog = new Dog("Rover", 3, "Husky");
        Animal cat = new Cat("Fluffy", 2);
        
        // Overridden methods show different behavior
        System.out.println("Method overriding example:");
        dog.makeSound();  // Dog's version
        cat.makeSound();  // Cat's version
        
        // Runtime polymorphism
        Animal animal = dog;
        animal.makeSound();  // Still calls Dog's version
    }
    
    private static void demonstrateMethodOverloading() {
        System.out.println("\n6. METHOD OVERLOADING:");
        
        Calculator calc = new Calculator();
        
        // Different method calls based on parameters
        System.out.println("Method overloading example:");
        System.out.println("add(5, 3): " + calc.add(5, 3));
        System.out.println("add(5.5, 3.2): " + calc.add(5.5, 3.2));
        System.out.println("add(5, 3, 2): " + calc.add(5, 3, 2));
        System.out.println("add(\"Hello\", \" World\"): " + calc.add("Hello", " World"));
    }
    
    private static void demonstrateFinalKeyword() {
        System.out.println("\n7. FINAL KEYWORD:");
        
        Cat cat = new Cat("Shadow", 1);
        
        // Final method can be called but not overridden
        cat.purr();
        
        // Final class cannot be extended
        System.out.println("Cat class is final and cannot be inherited");
    }
    
    private static void demonstrateSuperKeyword() {
        System.out.println("\n8. SUPER KEYWORD:");
        
        Dog dog = new Dog("Charlie", 4, "Beagle");
        
        // Using super to call parent method
        dog.displayInfo();
    }
}

// Helper class for method overloading demonstration
class Calculator {
    // Method overloading - same name, different parameters
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public String add(String a, String b) {
        return a + b;
    }
}

// Extended Dog class to demonstrate super keyword
class ExtendedDog extends Dog {
    private String color;
    
    public ExtendedDog(String name, int age, String breed, String color) {
        super(name, age, breed);  // Call parent constructor
        this.color = color;
    }
    
    public void displayInfo() {
        // Using super to call parent method
        System.out.println("Color: " + color);
        System.out.println("Breed: " + super.getBreed());  // Using super
        System.out.println("Name: " + getName());          // Direct call
    }
}
