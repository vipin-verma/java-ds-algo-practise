import java.io.*;
import java.util.*;

public class ExceptionHandling {
    
    public static void main(String[] args) {
        System.out.println("=== Java Exception Handling ===\n");
        
        // 1. Basic Exception Handling
        demonstrateBasicExceptionHandling();
        
        // 2. Multiple Catch Blocks
        demonstrateMultipleCatchBlocks();
        
        // 3. Finally Block
        demonstrateFinallyBlock();
        
        // 4. Try-with-Resources
        demonstrateTryWithResources();
        
        // 5. Custom Exceptions
        demonstrateCustomExceptions();
        
        // 6. Checked vs Unchecked Exceptions
        demonstrateCheckedVsUnchecked();
        
        // 7. Exception Propagation
        demonstrateExceptionPropagation();
        
        // 8. Best Practices
        demonstrateBestPractices();
    }
    
    private static void demonstrateBasicExceptionHandling() {
        System.out.println("1. BASIC EXCEPTION HANDLING:");
        
        // ArithmeticException
        try {
            int result = 10 / 0;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
            System.out.println("Exception type: " + e.getClass().getSimpleName());
        }
        
        // ArrayIndexOutOfBoundsException
        try {
            int[] numbers = {1, 2, 3};
            System.out.println("Number at index 5: " + numbers[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
        
        // NumberFormatException
        try {
            String text = "abc";
            int number = Integer.parseInt(text);
            System.out.println("Number: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: " + e.getMessage());
        }
        
        // NullPointerException
        try {
            String str = null;
            System.out.println("Length: " + str.length());
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: " + e.getMessage());
        }
        
        System.out.println("Program continues after exception handling...\n");
    }
    
    private static void demonstrateMultipleCatchBlocks() {
        System.out.println("2. MULTIPLE CATCH BLOCKS:");
        
        try {
            // This could throw multiple types of exceptions
            String input = "abc";
            int index = 5;
            
            if (input == null) {
                throw new NullPointerException("Input is null");
            }
            
            if (index < 0 || index >= input.length()) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            
            char ch = input.charAt(index);
            int number = Integer.parseInt(input);
            
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Generic Exception: " + e.getMessage());
        }
        
        // Using multi-catch (Java 7+)
        try {
            String input = "abc";
            int number = Integer.parseInt(input);
            int result = 10 / number;
        } catch (NumberFormatException | ArithmeticException e) {
            System.out.println("Caught either NumberFormatException or ArithmeticException: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void demonstrateFinallyBlock() {
        System.out.println("3. FINALLY BLOCK:");
        
        // Finally block always executes
        try {
            System.out.println("Inside try block");
            int result = 10 / 2;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed - cleanup code here");
        }
        
        // Finally executes even when exception occurs
        try {
            System.out.println("\nInside try block with exception");
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed even after exception");
        }
        
        // Finally executes even with return statement
        System.out.println("\nFinally with return: " + demonstrateFinallyWithReturn());
        
        System.out.println();
    }
    
    private static int demonstrateFinallyWithReturn() {
        try {
            System.out.println("Inside try block");
            return 10;
        } finally {
            System.out.println("Finally block executed before return");
        }
    }
    
    private static void demonstrateTryWithResources() {
        System.out.println("4. TRY-WITH-RESOURCES:");
        
        // Traditional try-catch with manual resource management
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new StringReader("Hello\nWorld\nJava"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read: " + line);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("Reader closed manually");
                } catch (IOException e) {
                    System.out.println("Error closing reader: " + e.getMessage());
                }
            }
        }
        
        // Try-with-resources (Java 7+) - automatic resource management
        System.out.println("\nUsing try-with-resources:");
        try (BufferedReader autoReader = new BufferedReader(new StringReader("Auto\nResource\nManagement"))) {
            String line;
            while ((line = autoReader.readLine()) != null) {
                System.out.println("Read: " + line);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        // Reader automatically closed here
        
        // Multiple resources in try-with-resources
        System.out.println("\nMultiple resources:");
        try (StringReader stringReader = new StringReader("Multiple");
             BufferedReader buffReader = new BufferedReader(stringReader)) {
            String line = buffReader.readLine();
            System.out.println("Read: " + line);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void demonstrateCustomExceptions() {
        System.out.println("5. CUSTOM EXCEPTIONS:");
        
        try {
            validateAge(15);
        } catch (InvalidAgeException e) {
            System.out.println("Custom exception caught: " + e.getMessage());
            System.out.println("Age provided: " + e.getAge());
        }
        
        try {
            validateEmail("invalid-email");
        } catch (InvalidEmailException e) {
            System.out.println("Custom exception caught: " + e.getMessage());
            System.out.println("Email provided: " + e.getEmail());
        }
        
        try {
            withdrawMoney(1000, 500);
        } catch (InsufficientFundsException e) {
            System.out.println("Custom exception caught: " + e.getMessage());
            System.out.println("Required: " + e.getRequiredAmount());
            System.out.println("Available: " + e.getAvailableAmount());
        }
        
        System.out.println();
    }
    
    private static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be at least 18", age);
        }
        System.out.println("Age " + age + " is valid");
    }
    
    private static void validateEmail(String email) throws InvalidEmailException {
        if (!email.contains("@")) {
            throw new InvalidEmailException("Invalid email format", email);
        }
        System.out.println("Email " + email + " is valid");
    }
    
    private static void withdrawMoney(double available, double required) throws InsufficientFundsException {
        if (required > available) {
            throw new InsufficientFundsException("Insufficient funds", required, available);
        }
        System.out.println("Withdrawal successful: " + required);
    }
    
    private static void demonstrateCheckedVsUnchecked() {
        System.out.println("6. CHECKED VS UNCHECKED EXCEPTIONS:");
        
        // Checked exception - must be handled or declared
        try {
            readFile("nonexistent.txt");
        } catch (IOException e) {
            System.out.println("Checked exception handled: " + e.getMessage());
        }
        
        // Unchecked exception - not required to handle
        try {
            divideNumbers(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("Unchecked exception handled: " + e.getMessage());
        }
        
        // Method that declares it throws checked exception
        try {
            processData();
        } catch (IOException e) {
            System.out.println("Process data exception: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void readFile(String filename) throws IOException {
        // This method declares it throws IOException (checked exception)
        if (!filename.endsWith(".txt")) {
            throw new IOException("File must be a text file");
        }
        System.out.println("Reading file: " + filename);
    }
    
    private static int divideNumbers(int a, int b) {
        // This method doesn't declare exceptions (unchecked exception)
        return a / b;
    }
    
    private static void processData() throws IOException {
        // Simulating some processing that might throw IOException
        throw new IOException("Data processing failed");
    }
    
    private static void demonstrateExceptionPropagation() {
        System.out.println("7. EXCEPTION PROPAGATION:");
        
        try {
            method1();
        } catch (Exception e) {
            System.out.println("Exception caught in main: " + e.getMessage());
            System.out.println("Stack trace:");
            e.printStackTrace();
        }
        
        System.out.println();
    }
    
    private static void method1() throws Exception {
        System.out.println("Method 1 called");
        method2();
    }
    
    private static void method2() throws Exception {
        System.out.println("Method 2 called");
        method3();
    }
    
    private static void method3() throws Exception {
        System.out.println("Method 3 called");
        throw new Exception("Exception thrown in method 3");
    }
    
    private static void demonstrateBestPractices() {
        System.out.println("8. EXCEPTION HANDLING BEST PRACTICES:");
        
        // 1. Don't catch and ignore exceptions
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            // Good: Log the exception
            System.err.println("Error occurred: " + e.getMessage());
            // Good: Re-throw if you can't handle it properly
            // throw new RuntimeException("Unable to process calculation", e);
        }
        
        // 2. Use specific exception types
        try {
            String input = "abc";
            int number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Good: Catch specific exception
            System.out.println("Invalid number format: " + e.getMessage());
        } catch (Exception e) {
            // Good: Generic catch for unexpected exceptions
            System.err.println("Unexpected error: " + e.getMessage());
        }
        
        // 3. Clean up resources properly
        List<String> list = new ArrayList<>();
        try {
            list.add("Item 1");
            list.add("Item 2");
            // Simulate some operation that might fail
            if (Math.random() > 0.5) {
                throw new RuntimeException("Random error");
            }
        } finally {
            // Good: Clean up resources
            list.clear();
            System.out.println("Resources cleaned up");
        }
        
        // 4. Provide meaningful error messages
        try {
            validateInput("");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
        
        System.out.println("\nException handling demonstration completed!");
    }
    
    private static void validateInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        System.out.println("Input validated: " + input);
    }
}

// Custom Exception Classes

class InvalidAgeException extends Exception {
    private int age;
    
    public InvalidAgeException(String message, int age) {
        super(message);
        this.age = age;
    }
    
    public int getAge() {
        return age;
    }
}

class InvalidEmailException extends Exception {
    private String email;
    
    public InvalidEmailException(String message, String email) {
        super(message);
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
}

class InsufficientFundsException extends Exception {
    private double requiredAmount;
    private double availableAmount;
    
    public InsufficientFundsException(String message, double requiredAmount, double availableAmount) {
        super(message);
        this.requiredAmount = requiredAmount;
        this.availableAmount = availableAmount;
    }
    
    public double getRequiredAmount() {
        return requiredAmount;
    }
    
    public double getAvailableAmount() {
        return availableAmount;
    }
}
