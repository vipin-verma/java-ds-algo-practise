package javaConcepts;

public class BasicJavaConcepts {

    // Instance variables (class level variables)
    private String name;
    private int age;

    // Static variables (class variables)
    public static final String LANGUAGE = "Java";
    public static int totalStudents = 0;

    public static void main(String[] args) {
        System.out.println("=== Basic Java Concepts ===\n");

        // 1. Data Types and Variables
        demonstrateDataTypes();

        // 2. Operators
        demonstrateOperators();

        // 3. Type Casting
        demonstrateTypeCasting();

        // 4. String Operations
        demonstrateStringOperations();

        // 5. Arrays
        demonstrateArrays();

        // 6. Object Creation
        BasicJavaConcepts obj = new BasicJavaConcepts();
        obj.setName("Rahul");
        obj.setAge(25);
        obj.displayInfo();

        // 7. Static vs Instance
        demonstrateStaticVsInstance();
    }

    // Instance method
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("\n=== Object Information ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Language: " + LANGUAGE);
    }

    private static void demonstrateDataTypes() {
        System.out.println("1. DATA TYPES AND VARIABLES:");

        // Primitive data types
        byte byteNum = 127;                    // 8-bit, range: -128 to 127
        short shortNum = 32767;                // 16-bit, range: -32,768 to 32,767
        int intNum = 2147483647;               // 32-bit, range: -2^31 to 2^31-1
        long longNum = 9223372036854775807L;   // 64-bit, range: -2^63 to 2^63-1

        float floatNum = 3.14f;                // 32-bit floating point
        double doubleNum = 3.14159265359;      // 64-bit floating point

        char character = 'A';                  // 16-bit Unicode character
        boolean isTrue = true;                 // true or false

        System.out.println("byte: " + byteNum);
        System.out.println("short: " + shortNum);
        System.out.println("int: " + intNum);
        System.out.println("long: " + longNum);
        System.out.println("float: " + floatNum);
        System.out.println("double: " + doubleNum);
        System.out.println("char: " + character);
        System.out.println("boolean: " + isTrue);

        // Reference data types
        String text = "Hello Java!";
        Integer wrapperInt = 100;
        Double wrapperDouble = 99.9;

        System.out.println("String: " + text);
        System.out.println("Integer wrapper: " + wrapperInt);
        System.out.println("Double wrapper: " + wrapperDouble);
    }

    private static void demonstrateOperators() {
        System.out.println("\n2. OPERATORS:");

        int a = 10, b = 3;

        // Arithmetic operators
        System.out.println("Arithmetic Operators:");
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("Addition: " + a + " + " + b + " = " + (a + b));
        System.out.println("Subtraction: " + a + " - " + b + " = " + (a - b));
        System.out.println("Multiplication: " + a + " * " + b + " = " + (a * b));
        System.out.println("Division: " + a + " / " + b + " = " + (a / b));
        System.out.println("Modulus: " + a + " % " + b + " = " + (a % b));

        // Increment/Decrement operators
        System.out.println("\nIncrement/Decrement Operators:");
        int x = 5;
        System.out.println("Original x: " + x);
        System.out.println("Post-increment x++: " + x++);  // Use then increment
        System.out.println("After post-increment: " + x);
        System.out.println("Pre-increment ++x: " + ++x);   // Increment then use
        System.out.println("After pre-increment: " + x);

        // Comparison operators
        System.out.println("\nComparison Operators:");
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a > b: " + (a > b));
        System.out.println("a < b: " + (a < b));
        System.out.println("a >= b: " + (a >= b));
        System.out.println("a <= b: " + (a <= b));

        // Logical operators
        System.out.println("\nLogical Operators:");
        boolean p = true, q = false;
        System.out.println("p = " + p + ", q = " + q);
        System.out.println("p && q (AND): " + (p && q));
        System.out.println("p || q (OR): " + (p || q));
        System.out.println("!p (NOT): " + (!p));

        // Assignment operators
        System.out.println("\nAssignment Operators:");
        int c = 15;
        System.out.println("Original c: " + c);
        c += 5;  // c = c + 5
        System.out.println("After c += 5: " + c);
        c -= 3;  // c = c - 3
        System.out.println("After c -= 3: " + c);
        c *= 2;  // c = c * 2
        System.out.println("After c *= 2: " + c);
    }

    private static void demonstrateTypeCasting() {
        System.out.println("\n3. TYPE CASTING:");

        // Implicit casting (Widening)
        System.out.println("Implicit Casting (Widening):");
        int intValue = 100;
        long longValue = intValue;      // int to long
        float floatValue = longValue;   // long to float
        double doubleValue = floatValue; // float to double

        System.out.println("int: " + intValue);
        System.out.println("int to long: " + longValue);
        System.out.println("long to float: " + floatValue);
        System.out.println("float to double: " + doubleValue);

        // Explicit casting (Narrowing)
        System.out.println("\nExplicit Casting (Narrowing):");
        double d = 100.04;
        long l = (long) d;    // double to long
        int i = (int) l;      // long to int
        byte b = (byte) i;    // int to byte

        System.out.println("double: " + d);
        System.out.println("double to long: " + l);
        System.out.println("long to int: " + i);
        System.out.println("int to byte: " + b);

        // Character to int conversion
        System.out.println("\nCharacter to Number Conversion:");
        char ch = 'A';
        int charToInt = ch;
        System.out.println("char 'A': " + ch);
        System.out.println("ASCII value: " + charToInt);
    }

    private static void demonstrateStringOperations() {
        System.out.println("\n4. STRING OPERATIONS:");

        String str1 = "Hello";
        String str2 = "World";

        // String concatenation
        String result = str1 + " " + str2;
        System.out.println("Concatenation: " + result);

        // String methods
        System.out.println("Length: " + result.length());
        System.out.println("Uppercase: " + result.toUpperCase());
        System.out.println("Lowercase: " + result.toLowerCase());
        System.out.println("Contains 'World': " + result.contains("World"));
        System.out.println("Substring (0,5): " + result.substring(0, 5));
        System.out.println("Index of 'o': " + result.indexOf('o'));

        // String comparison
        String s1 = "Java";
        String s2 = "Java";
        String s3 = new String("Java");

        System.out.println("\nString Comparison:");
        System.out.println("s1 == s2: " + (s1 == s2));           // Reference comparison
        System.out.println("s1.equals(s2): " + s1.equals(s2));   // Content comparison
        System.out.println("s1 == s3: " + (s1 == s3));           // Reference comparison
        System.out.println("s1.equals(s3): " + s1.equals(s3));   // Content comparison
    }

    private static void demonstrateArrays() {
        System.out.println("\n5. ARRAYS:");

        // Array declaration and initialization
        String[] fruits = new String[3];
        fruits[0] = "Apple";
        int[] numbers = {1, 2, 3, 4, 5};
        fruits[1] = "Banana";
        fruits[2] = "Orange";

        System.out.println("Numbers array:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }

        System.out.println("\n\nFruits array:");
        for (String fruit : fruits) {  // Enhanced for loop
            System.out.print(fruit + " ");
        }

        // 2D array
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("\n\n2D Matrix:");
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    private static void demonstrateStaticVsInstance() {
        System.out.println("\n6. STATIC VS INSTANCE:");

        // Accessing static members
        System.out.println("Static variable: " + BasicJavaConcepts.LANGUAGE);
        System.out.println("Total students: " + totalStudents);

        // Creating multiple objects
        BasicJavaConcepts obj1 = new BasicJavaConcepts();
        BasicJavaConcepts obj2 = new BasicJavaConcepts();

        obj1.setName("Alice");
        obj1.setAge(20);
        obj2.setName("Bob");
        obj2.setAge(22);

        // Static variable is shared across all instances
        totalStudents = 2;

        System.out.println("\nObject 1:");
        obj1.displayInfo();
        System.out.println("Object 2:");
        obj2.displayInfo();
        System.out.println("Total students (shared): " + totalStudents);
    }
}
