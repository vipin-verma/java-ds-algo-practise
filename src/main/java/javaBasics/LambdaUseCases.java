import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Lambda Expressions - Practical Use Cases with Documentation
 * 
 * This class demonstrates real-world scenarios where lambda expressions
 * provide elegant and efficient solutions to common programming problems.
 */
public class LambdaUseCases {
    
    public static void main(String[] args) {
        System.out.println("=== Lambda Expressions - Practical Use Cases ===\n");
        
        // 1. Event Handling and Callbacks
        demonstrateEventHandling();
        
        // 2. Data Processing and Transformation
        demonstrateDataProcessing();
        
        // 3. Sorting and Filtering
        demonstrateSortingAndFiltering();
        
        // 4. Configuration and Strategy Pattern
        demonstrateConfigurationAndStrategy();
        
        System.out.println("\n=== Lambda Use Cases Demonstration Completed! ===");
    }
    
    /**
     * 1. EVENT HANDLING AND CALLBACKS
     * 
     * Lambda expressions are perfect for event-driven programming where
     * you need to pass behavior as parameters to methods.
     */
    private static void demonstrateEventHandling() {
        System.out.println("1. EVENT HANDLING AND CALLBACKS:\n");
        
        // Button click event handling
        System.out.println("Button Click Event Handling:");
        Button button = new Button("Submit");
        
        // Lambda for single click action - defines what happens when button is clicked
        button.addClickListener(() -> {
            System.out.println("  → Button clicked! Processing form submission...");
            System.out.println("  → Validating user input...");
            System.out.println("  → Sending data to server...");
        });
        
        // Lambda for double click action - defines double-click behavior
        button.addDoubleClickListener(() -> {
            System.out.println("  → Double click detected! Opening advanced options...");
        });
        
        // Lambda for right click action - defines context menu behavior
        button.addRightClickListener(() -> {
            System.out.println("  → Right click detected! Showing context menu...");
        });
        
        // Simulate button interactions
        button.click();
        button.doubleClick();
        button.rightClick();
        
        // Timer-based event handling
        System.out.println("\nTimer-based Event Handling:");
        Timer timer = new Timer();
        
        // Lambda for periodic task execution - defines what happens every timer tick
        timer.scheduleRepeating(() -> {
            System.out.println("  → Timer tick: " + new Date());
            System.out.println("  → Checking for updates...");
        }, 1000); // Every 1 second
        
        // Simulate timer ticks
        for (int i = 0; i < 3; i++) {
            timer.tick();
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
        
        System.out.println();
    }
    
    /**
     * 2. DATA PROCESSING AND TRANSFORMATION
     * 
     * Lambda expressions enable functional programming patterns for
     * data transformation, making code more readable and maintainable.
     */
    private static void demonstrateDataProcessing() {
        System.out.println("2. DATA PROCESSING AND TRANSFORMATION:\n");
        
        // Sample data: List of employees
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 25, "Engineer", 75000),
            new Employee("Bob", 30, "Manager", 90000),
            new Employee("Charlie", 35, "Engineer", 80000),
            new Employee("David", 28, "Designer", 70000),
            new Employee("Eve", 32, "Engineer", 85000)
        );
        
        System.out.println("Original Employee Data:");
        employees.forEach(emp -> System.out.println("  " + emp));
        
        // Lambda for salary increase calculation - transforms each employee
        System.out.println("\nApplying 10% Salary Increase:");
        Function<Employee, Employee> salaryIncrease = employee -> {
            // Create new employee with increased salary
            Employee updated = new Employee(
                employee.getName(),
                employee.getAge(),
                employee.getJob(),
                (int)(employee.getSalary() * 1.10)
            );
            System.out.println("  → " + employee.getName() + ": $" + 
                             employee.getSalary() + " → $" + updated.getSalary());
            return updated;
        };
        
        // Apply salary increase to all employees using the lambda
        List<Employee> updatedEmployees = employees.stream()
            .map(salaryIncrease)  // Uses the lambda to transform each employee
            .collect(Collectors.toList());
        
        // Lambda for job-based grouping - groups employees by their job title
        System.out.println("\nGrouping Employees by Job:");
        Map<String, List<Employee>> jobGroups = employees.stream()
            .collect(Collectors.groupingBy(Employee::getJob));
        
        // Lambda for printing grouped results - processes each group
        jobGroups.forEach((job, empList) -> {
            System.out.println("  → " + job + " (" + empList.size() + " employees):");
            empList.forEach(emp -> System.out.println("    - " + emp.getName() + " - $" + emp.getSalary()));
        });
        
        // Lambda for statistical calculations - processes salary data
        System.out.println("\nStatistical Analysis:");
        DoubleSummaryStatistics salaryStats = employees.stream()
            .mapToDouble(Employee::getSalary)  // Extract salary values
            .summaryStatistics();              // Calculate statistics
        
        System.out.println("  → Average Salary: $" + String.format("%.2f", salaryStats.getAverage()));
        System.out.println("  → Highest Salary: $" + salaryStats.getMax());
        System.out.println("  → Lowest Salary: $" + salaryStats.getMin());
        System.out.println("  → Total Salary Budget: $" + salaryStats.getSum());
        
        System.out.println();
    }
    
    /**
     * 3. SORTING AND FILTERING
     * 
     * Lambda expressions provide flexible and readable ways to sort
     * and filter collections based on dynamic criteria.
     */
    private static void demonstrateSortingAndFiltering() {
        System.out.println("3. SORTING AND FILTERING:\n");
        
        // Sample data: Products with different attributes
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200.0, "Electronics", 4.5),
            new Product("Book", 25.0, "Education", 4.8),
            new Product("Phone", 800.0, "Electronics", 4.2),
            new Product("Desk", 150.0, "Furniture", 4.0),
            new Product("Headphones", 100.0, "Electronics", 4.6),
            new Product("Chair", 80.0, "Furniture", 4.3)
        );
        
        System.out.println("Original Products:");
        products.forEach(p -> System.out.println("  " + p));
        
        // Lambda for dynamic sorting based on user preference
        System.out.println("\nSorting by Different Criteria:");
        
        // Sort by price (ascending) - lambda compares product prices
        List<Product> priceSorted = products.stream()
            .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
            .collect(Collectors.toList());
        
        System.out.println("  → Sorted by Price (Low to High):");
        priceSorted.forEach(p -> System.out.println("    $" + p.getPrice() + " - " + p.getName()));
        
        // Sort by rating (descending) - lambda compares product ratings
        List<Product> ratingSorted = products.stream()
            .sorted((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()))
            .collect(Collectors.toList());
        
        System.out.println("  → Sorted by Rating (High to Low):");
        ratingSorted.forEach(p -> System.out.println("    " + p.getRating() + "★ - " + p.getName()));
        
        // Lambda for complex filtering with multiple conditions
        System.out.println("\nComplex Filtering:");
        
        // Filter: Electronics with rating > 4.5 and price < 1000
        // This lambda defines the filtering criteria
        Predicate<Product> highRatedElectronics = product -> 
            "Electronics".equals(product.getCategory()) &&
            product.getRating() > 4.5 &&
            product.getPrice() < 1000;
        
        List<Product> filteredProducts = products.stream()
            .filter(highRatedElectronics)  // Apply the filtering lambda
            .collect(Collectors.toList());
        
        System.out.println("  → High-rated Electronics under $1000:");
        filteredProducts.forEach(p -> System.out.println("    " + p.getName() + " - $" + p.getPrice() + " - " + p.getRating() + "★"));
        
        // Lambda for custom comparator with multiple fields
        System.out.println("\nMulti-field Sorting:");
        
        // Sort by category, then by rating, then by price
        // This lambda implements complex sorting logic
        List<Product> multiSorted = products.stream()
            .sorted((p1, p2) -> {
                // First, compare by category
                int categoryCompare = p1.getCategory().compareTo(p2.getCategory());
                if (categoryCompare != 0) return categoryCompare;
                
                // If same category, compare by rating (descending)
                int ratingCompare = Double.compare(p2.getRating(), p1.getRating());
                if (ratingCompare != 0) return ratingCompare;
                
                // If same rating, compare by price (ascending)
                return Double.compare(p1.getPrice(), p2.getPrice());
            })
            .collect(Collectors.toList());
        
        System.out.println("  → Multi-field Sorted (Category → Rating → Price):");
        multiSorted.forEach(p -> System.out.println("    " + p.getCategory() + " | " + p.getRating() + "★ | $" + p.getPrice() + " | " + p.getName()));
        
        System.out.println();
    }
    
    /**
     * 4. CONFIGURATION AND STRATEGY PATTERN
     * 
     * Lambda expressions enable flexible configuration and strategy
     * patterns without creating multiple classes.
     */
    private static void demonstrateConfigurationAndStrategy() {
        System.out.println("4. CONFIGURATION AND STRATEGY PATTERN:\n");
        
        // Strategy pattern with lambda: Different payment strategies
        System.out.println("Payment Strategy Pattern:");
        
        Map<String, PaymentStrategy> paymentStrategies = new HashMap<>();
        
        // Credit card payment strategy - lambda defines credit card processing logic
        paymentStrategies.put("credit", (amount, currency) -> {
            System.out.println("  → Processing credit card payment:");
            System.out.println("    - Amount: " + currency + amount);
            System.out.println("    - Validating card details...");
            System.out.println("    - Charging credit card...");
            return "Credit card payment successful";
        });
        
        // PayPal payment strategy - lambda defines PayPal processing logic
        paymentStrategies.put("paypal", (amount, currency) -> {
            System.out.println("  → Processing PayPal payment:");
            System.out.println("    - Amount: " + currency + amount);
            System.out.println("    - Redirecting to PayPal...");
            System.out.println("    - Processing PayPal transaction...");
            return "PayPal payment successful";
        });
        
        // Bank transfer strategy - lambda defines bank transfer logic
        paymentStrategies.put("bank", (amount, currency) -> {
            System.out.println("  → Processing bank transfer:");
            System.out.println("    - Amount: " + currency + amount);
            System.out.println("    - Initiating bank transfer...");
            System.out.println("    - Waiting for confirmation...");
            return "Bank transfer initiated";
        });
        
        // Test different payment strategies
        double amount = 150.0;
        String currency = "$";
        
        // Lambda for testing each payment strategy
        paymentStrategies.forEach((method, strategy) -> {
            System.out.println("\nTesting " + method.toUpperCase() + " payment:");
            String result = strategy.processPayment(amount, currency);
            System.out.println("  → Result: " + result);
        });
        
        // Configuration pattern with lambda: Different notification strategies
        System.out.println("\nNotification Configuration Pattern:");
        
        NotificationService notificationService = new NotificationService();
        
        // Email notification configuration - lambda defines email sending logic
        notificationService.configureNotification("email", (message, priority) -> {
            System.out.println("  → Sending EMAIL notification:");
            System.out.println("    - Priority: " + priority);
            System.out.println("    - Message: " + message);
            System.out.println("    - Recipient: user@example.com");
            return "Email sent successfully";
        });
        
        // SMS notification configuration - lambda defines SMS sending logic
        notificationService.configureNotification("sms", (message, priority) -> {
            System.out.println("  → Sending SMS notification:");
            System.out.println("    - Priority: " + priority);
            System.out.println("    - Message: " + message);
            System.out.println("    - Phone: +1-555-0123");
            return "SMS sent successfully";
        });
        
        // Push notification configuration - lambda defines push notification logic
        notificationService.configureNotification("push", (message, priority) -> {
            System.out.println("  → Sending PUSH notification:");
            System.out.println("    - Priority: " + priority);
            System.out.println("    - Message: " + message);
            System.out.println("    - Device: Mobile App");
            return "Push notification sent successfully";
        });
        
        // Test different notification methods
        String message = "Your order has been shipped!";
        String priority = "HIGH";
        
        notificationService.sendNotification("email", message, priority);
        notificationService.sendNotification("sms", message, priority);
        notificationService.sendNotification("push", message, priority);
        
        System.out.println();
    }
    
    // ===== HELPER CLASSES =====
    
    /**
     * Button class for event handling demonstration
     */
    static class Button {
        private String text;
        private List<Runnable> clickListeners = new ArrayList<>();
        private List<Runnable> doubleClickListeners = new ArrayList<>();
        private List<Runnable> rightClickListeners = new ArrayList<>();
        
        public Button(String text) {
            this.text = text;
        }
        
        public void addClickListener(Runnable listener) {
            clickListeners.add(listener);
        }
        
        public void addDoubleClickListener(Runnable listener) {
            doubleClickListeners.add(listener);
        }
        
        public void addRightClickListener(Runnable listener) {
            rightClickListeners.add(listener);
        }
        
        public void click() {
            System.out.println("Button '" + text + "' clicked!");
            clickListeners.forEach(Runnable::run);
        }
        
        public void doubleClick() {
            System.out.println("Button '" + text + "' double-clicked!");
            doubleClickListeners.forEach(Runnable::run);
        }
        
        public void rightClick() {
            System.out.println("Button '" + text + "' right-clicked!");
            rightClickListeners.forEach(Runnable::run);
        }
    }
    
    /**
     * Timer class for event handling demonstration
     */
    static class Timer {
        private List<Runnable> listeners = new ArrayList<>();
        private int interval;
        
        public void scheduleRepeating(Runnable task, int intervalMs) {
            this.interval = intervalMs;
            listeners.add(task);
        }
        
        public void tick() {
            listeners.forEach(Runnable::run);
        }
    }
    
    /**
     * Employee class for data processing demonstration
     */
    static class Employee {
        private String name;
        private int age;
        private String job;
        private int salary;
        
        public Employee(String name, int age, String job, int salary) {
            this.name = name;
            this.age = age;
            this.job = job;
            this.salary = salary;
        }
        
        // Getters
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getJob() { return job; }
        public int getSalary() { return salary; }
        
        @Override
        public String toString() {
            return String.format("Employee{name='%s', age=%d, job='%s', salary=$%d}", name, age, job, salary);
        }
    }
    
    /**
     * Product class for sorting and filtering demonstration
     */
    static class Product {
        private String name;
        private double price;
        private String category;
        private double rating;
        
        public Product(String name, double price, String category, double rating) {
            this.name = name;
            this.price = price;
            this.category = category;
            this.rating = rating;
        }
        
        // Getters
        public String getName() { return name; }
        public double getPrice() { return price; }
        public String getCategory() { return category; }
        public double getRating() { return rating; }
        
        @Override
        public String toString() {
            return String.format("Product{name='%s', price=$%.2f, category='%s', rating=%.1f★}", name, price, category, rating);
        }
    }
    
    /**
     * Payment strategy functional interface
     */
    @FunctionalInterface
    interface PaymentStrategy {
        String processPayment(double amount, String currency);
    }
    
    /**
     * Notification service for configuration pattern demonstration
     */
    static class NotificationService {
        private Map<String, BiFunction<String, String, String>> notificationHandlers = new HashMap<>();
        
        public void configureNotification(String type, BiFunction<String, String, String> handler) {
            notificationHandlers.put(type, handler);
        }
        
        public String sendNotification(String type, String message, String priority) {
            BiFunction<String, String, String> handler = notificationHandlers.get(type);
            if (handler != null) {
                return handler.apply(message, priority);
            }
            return "Unknown notification type: " + type;
        }
    }
}
