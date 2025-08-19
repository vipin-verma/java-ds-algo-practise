/**
 * Question 7: Design Patterns Implementation
 * 
 * Problem: Implement and demonstrate various design patterns commonly used
 * in enterprise Java applications.
 * 
 * Requirements:
 * - Should implement multiple design patterns
 * - Should show real-world usage scenarios
 * - Should be thread-safe where applicable
 * - Should demonstrate best practices
 * - Should include performance considerations
 * 
 * Difficulty: Medium
 * Category: Design Patterns, Architecture
 * Experience Level: 4-6 years
 */
public class Question007_DesignPatterns {
    
    public static void main(String[] args) {
        System.out.println("=== Design Patterns Implementation ===\n");
        
        // Test Singleton Pattern
        testSingletonPattern();
        
        // Test Factory Pattern
        testFactoryPattern();
        
        // Test Builder Pattern
        testBuilderPattern();
        
        // Test Observer Pattern
        testObserverPattern();
        
        // Test Strategy Pattern
        testStrategyPattern();
        
        // Test Command Pattern
        testCommandPattern();
        
        // Test Decorator Pattern
        testDecoratorPattern();
        
        // Test Adapter Pattern
        testAdapterPattern();
        
        // Test Composite Pattern
        testCompositePattern();
        
        // Test Template Method Pattern
        testTemplateMethodPattern();
    }
    
    private static void testSingletonPattern() {
        System.out.println("=== Singleton Pattern Test ===");
        
        // Test basic singleton
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        
        System.out.println("Same instance: " + (db1 == db2));
        System.out.println("Connection ID: " + db1.getConnectionId());
        
        // Test thread-safe singleton
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                DatabaseConnection instance = DatabaseConnection.getInstance();
                System.out.println("Thread " + Thread.currentThread().getId() + " got instance: " + instance.getConnectionId());
            });
            threads[i].start();
        }
        
        // Wait for threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("---");
    }
    
    private static void testFactoryPattern() {
        System.out.println("=== Factory Pattern Test ===");
        
        // Test different database types
        DatabaseFactory factory = new DatabaseFactory();
        
        Database mysql = factory.createDatabase("mysql");
        Database postgres = factory.createDatabase("postgresql");
        Database oracle = factory.createDatabase("oracle");
        
        System.out.println("MySQL: " + mysql.connect());
        System.out.println("PostgreSQL: " + postgres.connect());
        System.out.println("Oracle: " + oracle.connect());
        
        // Test with invalid type
        try {
            Database invalid = factory.createDatabase("invalid");
            System.out.println("Invalid: " + invalid.connect());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid database type handled: " + e.getMessage());
        }
        
        System.out.println("---");
    }
    
    private static void testBuilderPattern() {
        System.out.println("=== Builder Pattern Test ===");
        
        // Build complex objects
        User user1 = new User.UserBuilder("John", "Doe")
            .age(30)
            .email("john.doe@example.com")
            .phone("123-456-7890")
            .address("123 Main St")
            .build();
        
        User user2 = new User.UserBuilder("Jane", "Smith")
            .age(25)
            .email("jane.smith@example.com")
            .build();
        
        System.out.println("User 1: " + user1);
        System.out.println("User 2: " + user2);
        
        // Test validation
        try {
            User invalidUser = new User.UserBuilder("", "Doe").build();
            System.out.println("Invalid user: " + invalidUser);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
        
        System.out.println("---");
    }
    
    private static void testObserverPattern() {
        System.out.println("=== Observer Pattern Test ===");
        
        // Create subject and observers
        NewsAgency newsAgency = new NewsAgency();
        
        NewsChannel channel1 = new NewsChannel("CNN");
        NewsChannel channel2 = new NewsChannel("BBC");
        NewsChannel channel3 = new NewsChannel("Fox News");
        
        // Register observers
        newsAgency.registerObserver(channel1);
        newsAgency.registerObserver(channel2);
        newsAgency.registerObserver(channel3);
        
        // Publish news
        newsAgency.publishNews("Breaking: New technology breakthrough!");
        newsAgency.publishNews("Sports: Team wins championship!");
        
        // Unregister one observer
        newsAgency.unregisterObserver(channel2);
        newsAgency.publishNews("Politics: New policy announced!");
        
        System.out.println("---");
    }
    
    private static void testStrategyPattern() {
        System.out.println("=== Strategy Pattern Test ===");
        
        // Test different payment strategies
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("Laptop", 999.99));
        cart.addItem(new Item("Mouse", 29.99));
        
        // Test different payment methods
        PaymentStrategy creditCard = new CreditCardPayment("1234-5678-9012-3456");
        PaymentStrategy paypal = new PayPalPayment("user@example.com");
        PaymentStrategy bitcoin = new BitcoinPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        
        cart.setPaymentStrategy(creditCard);
        cart.checkout();
        
        cart.setPaymentStrategy(paypal);
        cart.checkout();
        
        cart.setPaymentStrategy(bitcoin);
        cart.checkout();
        
        System.out.println("---");
    }
    
    private static void testCommandPattern() {
        System.out.println("=== Command Pattern Test ===");
        
        // Create receiver and commands
        Light light = new Light();
        
        Command turnOnCommand = new TurnOnLightCommand(light);
        Command turnOffCommand = new TurnOffLightCommand(light);
        Command dimCommand = new DimLightCommand(light, 50);
        
        // Create invoker
        RemoteControl remote = new RemoteControl();
        
        // Set commands
        remote.setCommand(0, turnOnCommand, turnOffCommand);
        remote.setCommand(1, dimCommand, turnOffCommand);
        
        // Execute commands
        remote.pressOnButton(0);
        remote.pressOnButton(1);
        remote.pressOffButton(1);
        remote.pressOffButton(0);
        
        // Test undo
        remote.undo();
        
        System.out.println("---");
    }
    
    private static void testDecoratorPattern() {
        System.out.println("=== Decorator Pattern Test ===");
        
        // Create base coffee
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("Cost: $" + simpleCoffee.getCost() + ", Description: " + simpleCoffee.getDescription());
        
        // Add milk
        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println("Cost: $" + milkCoffee.getCost() + ", Description: " + milkCoffee.getDescription());
        
        // Add sugar
        Coffee sweetMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println("Cost: $" + sweetMilkCoffee.getCost() + ", Description: " + sweetMilkCoffee.getDescription());
        
        // Add whipped cream
        Coffee whippedSweetMilkCoffee = new WhippedCreamDecorator(sweetMilkCoffee);
        System.out.println("Cost: $" + whippedSweetMilkCoffee.getCost() + ", Description: " + whippedSweetMilkCoffee.getDescription());
        
        System.out.println("---");
    }
    
    private static void testAdapterPattern() {
        System.out.println("=== Adapter Pattern Test ===");
        
        // Test legacy system
        LegacyPaymentSystem legacySystem = new LegacyPaymentSystem();
        legacySystem.processPayment("USD", 100.0);
        
        // Test modern system with adapter
        ModernPaymentSystem modernSystem = new ModernPaymentSystem();
        PaymentAdapter adapter = new PaymentAdapter(modernSystem);
        
        adapter.processPayment("USD", 100.0);
        
        System.out.println("---");
    }
    
    private static void testCompositePattern() {
        System.out.println("=== Composite Pattern Test ===");
        
        // Create file system structure
        FileSystemComponent root = new Directory("root");
        
        FileSystemComponent docs = new Directory("documents");
        FileSystemComponent pics = new Directory("pictures");
        
        FileSystemComponent file1 = new File("report.txt", 1024);
        FileSystemComponent file2 = new File("photo.jpg", 2048);
        FileSystemComponent file3 = new File("presentation.pptx", 5120);
        
        docs.add(file1);
        docs.add(file3);
        pics.add(file2);
        
        root.add(docs);
        root.add(pics);
        
        // Display structure
        root.display(0);
        
        System.out.println("Total size: " + root.getSize() + " bytes");
        
        System.out.println("---");
    }
    
    private static void testTemplateMethodPattern() {
        System.out.println("=== Template Method Pattern Test ===");
        
        // Test different report generators
        ReportGenerator pdfReport = new PDFReportGenerator();
        ReportGenerator htmlReport = new HTMLReportGenerator();
        ReportGenerator xmlReport = new XMLReportGenerator();
        
        System.out.println("=== PDF Report ===");
        pdfReport.generateReport("Sales Data");
        
        System.out.println("\n=== HTML Report ===");
        htmlReport.generateReport("Sales Data");
        
        System.out.println("\n=== XML Report ===");
        xmlReport.generateReport("Sales Data");
        
        System.out.println("---");
    }
    
    // ===== SINGLETON PATTERN =====
    
    /**
     * Thread-safe Singleton implementation using double-checked locking
     */
    static class DatabaseConnection {
        private static volatile DatabaseConnection instance;
        private final String connectionId;
        
        private DatabaseConnection() {
            this.connectionId = "DB-" + System.currentTimeMillis();
        }
        
        public static DatabaseConnection getInstance() {
            if (instance == null) {
                synchronized (DatabaseConnection.class) {
                    if (instance == null) {
                        instance = new DatabaseConnection();
                    }
                }
            }
            return instance;
        }
        
        public String getConnectionId() {
            return connectionId;
        }
    }
    
    // ===== FACTORY PATTERN =====
    
    /**
     * Factory for creating different types of databases
     */
    static class DatabaseFactory {
        public Database createDatabase(String type) {
            switch (type.toLowerCase()) {
                case "mysql":
                    return new MySQLDatabase();
                case "postgresql":
                    return new PostgreSQLDatabase();
                case "oracle":
                    return new OracleDatabase();
                default:
                    throw new IllegalArgumentException("Unknown database type: " + type);
            }
        }
    }
    
    interface Database {
        String connect();
    }
    
    static class MySQLDatabase implements Database {
        @Override
        public String connect() {
            return "Connected to MySQL database";
        }
    }
    
    static class PostgreSQLDatabase implements Database {
        @Override
        public String connect() {
            return "Connected to PostgreSQL database";
        }
    }
    
    static class OracleDatabase implements Database {
        @Override
        public String connect() {
            return "Connected to Oracle database";
        }
    }
    
    // ===== BUILDER PATTERN =====
    
    /**
     * User class with builder pattern
     */
    static class User {
        private final String firstName;
        private final String lastName;
        private final int age;
        private final String email;
        private final String phone;
        private final String address;
        
        private User(UserBuilder builder) {
            this.firstName = builder.firstName;
            this.lastName = builder.lastName;
            this.age = builder.age;
            this.email = builder.email;
            this.phone = builder.phone;
            this.address = builder.address;
        }
        
        // Getters
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public int getAge() { return age; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getAddress() { return address; }
        
        @Override
        public String toString() {
            return String.format("User{firstName='%s', lastName='%s', age=%d, email='%s', phone='%s', address='%s'}",
                firstName, lastName, age, email, phone, address);
        }
        
        static class UserBuilder {
            private final String firstName;
            private final String lastName;
            private int age = 0;
            private String email = "";
            private String phone = "";
            private String address = "";
            
            public UserBuilder(String firstName, String lastName) {
                if (firstName == null || firstName.trim().isEmpty()) {
                    throw new IllegalArgumentException("First name cannot be null or empty");
                }
                if (lastName == null || lastName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Last name cannot be null or empty");
                }
                this.firstName = firstName;
                this.lastName = lastName;
            }
            
            public UserBuilder age(int age) {
                this.age = age;
                return this;
            }
            
            public UserBuilder email(String email) {
                this.email = email;
                return this;
            }
            
            public UserBuilder phone(String phone) {
                this.phone = phone;
                return this;
            }
            
            public UserBuilder address(String address) {
                this.address = address;
                return this;
            }
            
            public User build() {
                return new User(this);
            }
        }
    }
    
    // ===== OBSERVER PATTERN =====
    
    /**
     * Subject interface
     */
    interface Subject {
        void registerObserver(Observer observer);
        void unregisterObserver(Observer observer);
        void notifyObservers(String news);
    }
    
    /**
     * Observer interface
     */
    interface Observer {
        void update(String news);
        String getName();
    }
    
    /**
     * News agency (subject)
     */
    static class NewsAgency implements Subject {
        private final java.util.List<Observer> observers = new java.util.ArrayList<>();
        
        @Override
        public void registerObserver(Observer observer) {
            if (!observers.contains(observer)) {
                observers.add(observer);
            }
        }
        
        @Override
        public void unregisterObserver(Observer observer) {
            observers.remove(observer);
        }
        
        @Override
        public void notifyObservers(String news) {
            for (Observer observer : observers) {
                observer.update(news);
            }
        }
        
        public void publishNews(String news) {
            System.out.println("News Agency publishing: " + news);
            notifyObservers(news);
        }
    }
    
    /**
     * News channel (observer)
     */
    static class NewsChannel implements Observer {
        private final String name;
        
        public NewsChannel(String name) {
            this.name = name;
        }
        
        @Override
        public void update(String news) {
            System.out.println(name + " received news: " + news);
        }
        
        @Override
        public String getName() {
            return name;
        }
    }
    
    // ===== STRATEGY PATTERN =====
    
    /**
     * Payment strategy interface
     */
    interface PaymentStrategy {
        void pay(double amount);
    }
    
    /**
     * Credit card payment strategy
     */
    static class CreditCardPayment implements PaymentStrategy {
        private final String cardNumber;
        
        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }
        
        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Credit Card ending in " + 
                cardNumber.substring(cardNumber.length() - 4));
        }
    }
    
    /**
     * PayPal payment strategy
     */
    static class PayPalPayment implements PaymentStrategy {
        private final String email;
        
        public PayPalPayment(String email) {
            this.email = email;
        }
        
        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using PayPal account: " + email);
        }
    }
    
    /**
     * Bitcoin payment strategy
     */
    static class BitcoinPayment implements PaymentStrategy {
        private final String walletAddress;
        
        public BitcoinPayment(String walletAddress) {
            this.walletAddress = walletAddress;
        }
        
        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Bitcoin wallet: " + walletAddress);
        }
    }
    
    /**
     * Shopping cart using strategy pattern
     */
    static class ShoppingCart {
        private final java.util.List<Item> items = new java.util.ArrayList<>();
        private PaymentStrategy paymentStrategy;
        
        public void addItem(Item item) {
            items.add(item);
        }
        
        public void setPaymentStrategy(PaymentStrategy strategy) {
            this.paymentStrategy = strategy;
        }
        
        public void checkout() {
            double total = items.stream().mapToDouble(Item::getPrice).sum();
            if (paymentStrategy != null) {
                paymentStrategy.pay(total);
            } else {
                System.out.println("No payment strategy set!");
            }
        }
    }
    
    static class Item {
        private final String name;
        private final double price;
        
        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
        
        public String getName() { return name; }
        public double getPrice() { return price; }
    }
    
    // ===== COMMAND PATTERN =====
    
    /**
     * Command interface
     */
    interface Command {
        void execute();
        void undo();
    }
    
    /**
     * Light receiver
     */
    static class Light {
        private boolean isOn = false;
        private int brightness = 100;
        
        public void turnOn() {
            isOn = true;
            System.out.println("Light is ON");
        }
        
        public void turnOff() {
            isOn = false;
            System.out.println("Light is OFF");
        }
        
        public void dim(int level) {
            brightness = Math.max(0, Math.min(100, level));
            System.out.println("Light brightness set to " + brightness + "%");
        }
        
        public boolean isOn() { return isOn; }
        public int getBrightness() { return brightness; }
    }
    
    /**
     * Turn on light command
     */
    static class TurnOnLightCommand implements Command {
        private final Light light;
        
        public TurnOnLightCommand(Light light) {
            this.light = light;
        }
        
        @Override
        public void execute() {
            light.turnOn();
        }
        
        @Override
        public void undo() {
            light.turnOff();
        }
    }
    
    /**
     * Turn off light command
     */
    static class TurnOffLightCommand implements Command {
        private final Light light;
        
        public TurnOffLightCommand(Light light) {
            this.light = light;
        }
        
        @Override
        public void execute() {
            light.turnOff();
        }
        
        @Override
        public void undo() {
            light.turnOn();
        }
    }
    
    /**
     * Dim light command
     */
    static class DimLightCommand implements Command {
        private final Light light;
        private final int level;
        private int previousBrightness;
        
        public DimLightCommand(Light light, int level) {
            this.light = light;
            this.level = level;
        }
        
        @Override
        public void execute() {
            previousBrightness = light.getBrightness();
            light.dim(level);
        }
        
        @Override
        public void undo() {
            light.dim(previousBrightness);
        }
    }
    
    /**
     * Remote control invoker
     */
    static class RemoteControl {
        private final Command[] onCommands = new Command[7];
        private final Command[] offCommands = new Command[7];
        private Command undoCommand;
        
        public void setCommand(int slot, Command onCommand, Command offCommand) {
            onCommands[slot] = onCommand;
            offCommands[slot] = offCommand;
        }
        
        public void pressOnButton(int slot) {
            if (onCommands[slot] != null) {
                onCommands[slot].execute();
                undoCommand = onCommands[slot];
            }
        }
        
        public void pressOffButton(int slot) {
            if (offCommands[slot] != null) {
                offCommands[slot].execute();
                undoCommand = offCommands[slot];
            }
        }
        
        public void undo() {
            if (undoCommand != null) {
                undoCommand.undo();
            }
        }
    }
    
    // ===== DECORATOR PATTERN =====
    
    /**
     * Coffee interface
     */
    interface Coffee {
        double getCost();
        String getDescription();
    }
    
    /**
     * Simple coffee
     */
    static class SimpleCoffee implements Coffee {
        @Override
        public double getCost() {
            return 2.0;
        }
        
        @Override
        public String getDescription() {
            return "Simple coffee";
        }
    }
    
    /**
     * Milk decorator
     */
    static class MilkDecorator implements Coffee {
        private final Coffee coffee;
        
        public MilkDecorator(Coffee coffee) {
            this.coffee = coffee;
        }
        
        @Override
        public double getCost() {
            return coffee.getCost() + 0.5;
        }
        
        @Override
        public String getDescription() {
            return coffee.getDescription() + ", milk";
        }
    }
    
    /**
     * Sugar decorator
     */
    static class SugarDecorator implements Coffee {
        private final Coffee coffee;
        
        public SugarDecorator(Coffee coffee) {
            this.coffee = coffee;
        }
        
        @Override
        public double getCost() {
            return coffee.getCost() + 0.2;
        }
        
        @Override
        public String getDescription() {
            return coffee.getDescription() + ", sugar";
        }
    }
    
    /**
     * Whipped cream decorator
     */
    static class WhippedCreamDecorator implements Coffee {
        private final Coffee coffee;
        
        public WhippedCreamDecorator(Coffee coffee) {
            this.coffee = coffee;
        }
        
        @Override
        public double getCost() {
            return coffee.getCost() + 0.8;
        }
        
        @Override
        public String getDescription() {
            return coffee.getDescription() + ", whipped cream";
        }
    }
    
    // ===== ADAPTER PATTERN =====
    
    /**
     * Legacy payment system
     */
    static class LegacyPaymentSystem {
        public void processPayment(String currency, double amount) {
            System.out.println("Legacy system processing " + currency + " " + amount);
        }
    }
    
    /**
     * Modern payment system
     */
    static class ModernPaymentSystem {
        public void makePayment(String currency, double amount) {
            System.out.println("Modern system processing " + currency + " " + amount);
        }
    }
    
    /**
     * Payment adapter
     */
    static class PaymentAdapter {
        private final ModernPaymentSystem modernSystem;
        
        public PaymentAdapter(ModernPaymentSystem modernSystem) {
            this.modernSystem = modernSystem;
        }
        
        public void processPayment(String currency, double amount) {
            modernSystem.makePayment(currency, amount);
        }
    }
    
    // ===== COMPOSITE PATTERN =====
    
    /**
     * File system component interface
     */
    interface FileSystemComponent {
        void display(int depth);
        int getSize();
        void add(FileSystemComponent component);
        void remove(FileSystemComponent component);
    }
    
    /**
     * File implementation
     */
    static class File implements FileSystemComponent {
        private final String name;
        private final int size;
        
        public File(String name, int size) {
            this.name = name;
            this.size = size;
        }
        
        @Override
        public void display(int depth) {
            System.out.println("  ".repeat(depth) + "- " + name + " (" + size + " bytes)");
        }
        
        @Override
        public int getSize() {
            return size;
        }
        
        @Override
        public void add(FileSystemComponent component) {
            throw new UnsupportedOperationException("Cannot add to file");
        }
        
        @Override
        public void remove(FileSystemComponent component) {
            throw new UnsupportedOperationException("Cannot remove from file");
        }
    }
    
    /**
     * Directory implementation
     */
    static class Directory implements FileSystemComponent {
        private final String name;
        private final java.util.List<FileSystemComponent> children = new java.util.ArrayList<>();
        
        public Directory(String name) {
            this.name = name;
        }
        
        @Override
        public void display(int depth) {
            System.out.println("  ".repeat(depth) + "+ " + name + "/");
            for (FileSystemComponent child : children) {
                child.display(depth + 1);
            }
        }
        
        @Override
        public int getSize() {
            return children.stream().mapToInt(FileSystemComponent::getSize).sum();
        }
        
        @Override
        public void add(FileSystemComponent component) {
            children.add(component);
        }
        
        @Override
        public void remove(FileSystemComponent component) {
            children.remove(component);
        }
    }
    
    // ===== TEMPLATE METHOD PATTERN =====
    
    /**
     * Abstract report generator
     */
    abstract static class ReportGenerator {
        public final void generateReport(String title) {
            System.out.println("=== " + title + " ===");
            collectData();
            analyzeData();
            generateOutput();
            sendReport();
        }
        
        protected abstract void collectData();
        protected abstract void analyzeData();
        protected abstract void generateOutput();
        
        protected void sendReport() {
            System.out.println("Sending report...");
        }
    }
    
    /**
     * PDF report generator
     */
    static class PDFReportGenerator extends ReportGenerator {
        @Override
        protected void collectData() {
            System.out.println("Collecting data for PDF report...");
        }
        
        @Override
        protected void analyzeData() {
            System.out.println("Analyzing data for PDF report...");
        }
        
        @Override
        protected void generateOutput() {
            System.out.println("Generating PDF output...");
        }
    }
    
    /**
     * HTML report generator
     */
    static class HTMLReportGenerator extends ReportGenerator {
        @Override
        protected void collectData() {
            System.out.println("Collecting data for HTML report...");
        }
        
        @Override
        protected void analyzeData() {
            System.out.println("Analyzing data for HTML report...");
        }
        
        @Override
        protected void generateOutput() {
            System.out.println("Generating HTML output...");
        }
    }
    
    /**
     * XML report generator
     */
    static class XMLReportGenerator extends ReportGenerator {
        @Override
        protected void collectData() {
            System.out.println("Collecting data for XML report...");
        }
        
        @Override
        protected void analyzeData() {
            System.out.println("Analyzing data for XML report...");
        }
        
        @Override
        protected void generateOutput() {
            System.out.println("Generating XML output...");
        }
    }
    
    /**
     * Interview Questions to Ask:
     * 
     * 1. When would you use Singleton pattern and what are its drawbacks?
     * 2. How would you implement thread-safe Singleton without synchronization?
     * 3. What is the difference between Factory and Abstract Factory patterns?
     * 4. When would you use Builder pattern instead of constructor?
     * 5. How would you implement Observer pattern for event-driven systems?
     * 6. What are the advantages of Strategy pattern over inheritance?
     * 7. How would you implement Command pattern for undo/redo functionality?
     * 8. When would you use Decorator pattern instead of inheritance?
     * 9. How would you implement Adapter pattern for third-party libraries?
     * 10. What are the benefits of Composite pattern for tree structures?
     * 11. How would you implement Template Method pattern for algorithms?
     * 12. What if you need to combine multiple design patterns?
     * 13. How would you implement these patterns in a microservices architecture?
     * 14. What if you need to make these patterns thread-safe?
     * 15. How would you test these design patterns?
     */
}
