import java.lang.annotation.*;
import java.lang.reflect.*;
import java.lang.invoke.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Question 22: Advanced Java Language Features
 * 
 * This class demonstrates advanced Java language features commonly asked
 * in senior Java developer interviews.
 * 
 * Topics Covered:
 * - Reflection API and Dynamic Programming
 * - Annotations (Built-in + Custom)
 * - Enums with Methods and Fields
 * - Inner Classes (Static, Member, Local, Anonymous)
 * - Dynamic Proxies
 * - Records and Sealed Types
 * - VarHandles and Method Handles
 */
public class Question022_AdvancedJavaLanguageFeatures {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Java Language Features ===\n");
        
        demonstrateReflectionAPI();
        demonstrateAnnotations();
        demonstrateEnums();
        demonstrateInnerClasses();
        demonstrateDynamicProxies();
        demonstrateModernJavaFeatures();
        demonstrateVarHandles();
        
        System.out.println("\n=== Advanced Java Language Features Completed! ===");
    }
    
    // ===== REFLECTION API AND DYNAMIC PROGRAMMING =====
    
    private static void demonstrateReflectionAPI() {
        System.out.println("1. REFLECTION API AND DYNAMIC PROGRAMMING:\n");
        
        // Class Information
        System.out.println("  → Class Information:");
        demonstrateClassInformation();
        
        // Method Invocation
        System.out.println("\n  → Method Invocation:");
        demonstrateMethodInvocation();
        
        // Field Access
        System.out.println("\n  → Field Access:");
        demonstrateFieldAccess();
        
        // Constructor Access
        System.out.println("\n  → Constructor Access:");
        demonstrateConstructorAccess();
        
        // Dynamic Class Creation
        System.out.println("\n  → Dynamic Class Creation:");
        demonstrateDynamicClassCreation();
        
        System.out.println();
    }
    
    // ===== ANNOTATIONS =====
    
    private static void demonstrateAnnotations() {
        System.out.println("2. ANNOTATIONS (BUILT-IN + CUSTOM):\n");
        
        // Built-in Annotations
        System.out.println("  → Built-in Annotations:");
        demonstrateBuiltInAnnotations();
        
        // Custom Annotations
        System.out.println("\n  → Custom Annotations:");
        demonstrateCustomAnnotations();
        
        // Annotation Processing
        System.out.println("\n  → Annotation Processing:");
        demonstrateAnnotationProcessing();
        
        // Runtime Annotation Access
        System.out.println("\n  → Runtime Annotation Access:");
        demonstrateRuntimeAnnotationAccess();
        
        System.out.println();
    }
    
    // ===== ENUMS =====
    
    private static void demonstrateEnums() {
        System.out.println("3. ENUMS WITH METHODS AND FIELDS:\n");
        
        // Basic Enum Usage
        System.out.println("  → Basic Enum Usage:");
        demonstrateBasicEnum();
        
        // Enum with Methods
        System.out.println("\n  → Enum with Methods:");
        demonstrateEnumWithMethods();
        
        // Enum with Fields
        System.out.println("\n  → Enum with Fields:");
        demonstrateEnumWithFields();
        
        // Enum Strategy Pattern
        System.out.println("\n  → Enum Strategy Pattern:");
        demonstrateEnumStrategyPattern();
        
        System.out.println();
    }
    
    // ===== INNER CLASSES =====
    
    private static void demonstrateInnerClasses() {
        System.out.println("4. INNER CLASSES:\n");
        
        // Static Inner Class
        System.out.println("  → Static Inner Class:");
        demonstrateStaticInnerClass();
        
        // Member Inner Class
        System.out.println("\n  → Member Inner Class:");
        demonstrateMemberInnerClass();
        
        // Local Inner Class
        System.out.println("\n  → Local Inner Class:");
        demonstrateLocalInnerClass();
        
        // Anonymous Inner Class
        System.out.println("\n  → Anonymous Inner Class:");
        demonstrateAnonymousInnerClass();
        
        System.out.println();
    }
    
    // ===== DYNAMIC PROXIES =====
    
    private static void demonstrateDynamicProxies() {
        System.out.println("5. DYNAMIC PROXIES:\n");
        
        // Interface Proxy
        System.out.println("  → Interface Proxy:");
        demonstrateInterfaceProxy();
        
        // Method Interception
        System.out.println("\n  → Method Interception:");
        demonstrateMethodInterception();
        
        // Performance Monitoring Proxy
        System.out.println("\n  → Performance Monitoring Proxy:");
        demonstratePerformanceProxy();
        
        // Caching Proxy
        System.out.println("\n  → Caching Proxy:");
        demonstrateCachingProxy();
        
        System.out.println();
    }
    
    // ===== MODERN JAVA FEATURES =====
    
    private static void demonstrateModernJavaFeatures() {
        System.out.println("6. MODERN JAVA FEATURES:\n");
        
        // Records (Java 16+)
        System.out.println("  → Records (Java 16+):");
        demonstrateRecords();
        
        // Sealed Classes (Java 17+)
        System.out.println("\n  → Sealed Classes (Java 17+):");
        demonstrateSealedClasses();
        
        // Pattern Matching (Java 16+)
        System.out.println("\n  → Pattern Matching (Java 16+):");
        demonstratePatternMatching();
        
        // Switch Expressions (Java 14+)
        System.out.println("\n  → Switch Expressions (Java 14+):");
        demonstrateSwitchExpressions();
        
        System.out.println();
    }
    
    // ===== VARHANDLES =====
    
    private static void demonstrateVarHandles() {
        System.out.println("7. VARHANDLES AND METHOD HANDLES:\n");
        
        // VarHandle Basics
        System.out.println("  → VarHandle Basics:");
        demonstrateVarHandleBasics();
        
        // Atomic Operations
        System.out.println("\n  → Atomic Operations:");
        demonstrateAtomicOperations();
        
        // Method Handles
        System.out.println("\n  → Method Handles:");
        demonstrateMethodHandles();
        
        // Performance Comparison
        System.out.println("\n  → Performance Comparison:");
        demonstratePerformanceComparison();
        
        System.out.println();
    }
    
    // ===== HELPER METHODS =====
    
    private static void demonstrateClassInformation() {
        try {
            Class<?> clazz = String.class;
            
            System.out.println("    Class: " + clazz.getName());
            System.out.println("    Package: " + clazz.getPackage().getName());
            System.out.println("    Superclass: " + clazz.getSuperclass().getName());
            System.out.println("    Interfaces: " + Arrays.toString(clazz.getInterfaces()));
            System.out.println("    Modifiers: " + Modifier.toString(clazz.getModifiers()));
            System.out.println("    Is Array: " + clazz.isArray());
            System.out.println("    Is Interface: " + clazz.isInterface());
            System.out.println("    Is Enum: " + clazz.isEnum());
            System.out.println("    Is Annotation: " + clazz.isAnnotation());
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateMethodInvocation() {
        try {
            Class<?> clazz = String.class;
            Method method = clazz.getMethod("substring", int.class, int.class);
            
            String instance = "Hello World";
            Object result = method.invoke(instance, 0, 5);
            
            System.out.println("    Method: " + method.getName());
            System.out.println("    Parameters: " + Arrays.toString(method.getParameterTypes()));
            System.out.println("    Return Type: " + method.getReturnType());
            System.out.println("    Invocation Result: " + result);
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateFieldAccess() {
        try {
            Class<?> clazz = ReflectionDemo.class;
            Field field = clazz.getDeclaredField("privateField");
            
            // Make private field accessible
            field.setAccessible(true);
            
            ReflectionDemo instance = new ReflectionDemo();
            Object value = field.get(instance);
            
            System.out.println("    Field: " + field.getName());
            System.out.println("    Type: " + field.getType());
            System.out.println("    Value: " + value);
            
            // Set new value
            field.set(instance, "Modified via Reflection");
            System.out.println("    New Value: " + field.get(instance));
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateConstructorAccess() {
        try {
            Class<?> clazz = ReflectionDemo.class;
            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class);
            
            Object instance = constructor.newInstance("Created via Reflection");
            
            System.out.println("    Constructor: " + constructor.getName());
            System.out.println("    Parameters: " + Arrays.toString(constructor.getParameterTypes()));
            System.out.println("    Instance: " + instance);
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateDynamicClassCreation() {
        try {
            // Create a simple class dynamically
            Class<?> dynamicClass = createDynamicClass();
            
            System.out.println("    Dynamic Class: " + dynamicClass.getName());
            System.out.println("    Methods: " + dynamicClass.getDeclaredMethods().length);
            System.out.println("    Fields: " + dynamicClass.getDeclaredFields().length);
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateBuiltInAnnotations() {
        // @Override
        System.out.println("    @Override - Indicates method overrides superclass method");
        
        // @Deprecated
        System.out.println("    @Deprecated - Marks method as deprecated");
        
        // @SuppressWarnings
        System.out.println("    @SuppressWarnings - Suppresses compiler warnings");
        
        // @FunctionalInterface
        System.out.println("    @FunctionalInterface - Marks interface as functional");
        
        // @SafeVarargs
        System.out.println("    @SafeVarargs - Indicates varargs method is safe");
        
        // Demonstrate usage
        BuiltInAnnotationDemo demo = new BuiltInAnnotationDemo();
        demo.deprecatedMethod();
        demo.varargsMethod("Hello", "World");
    }
    
    private static void demonstrateCustomAnnotations() {
        try {
            // Get annotation from class
            Class<?> clazz = CustomAnnotationDemo.class;
            CustomAnnotation annotation = clazz.getAnnotation(CustomAnnotation.class);
            
            if (annotation != null) {
                System.out.println("    Custom Annotation Found:");
                System.out.println("      Value: " + annotation.value());
                System.out.println("      Priority: " + annotation.priority());
                System.out.println("      Tags: " + Arrays.toString(annotation.tags()));
            }
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateAnnotationProcessing() {
        try {
            // Process annotations at runtime
            Class<?> clazz = CustomAnnotationDemo.class;
            Method[] methods = clazz.getDeclaredMethods();
            
            for (Method method : methods) {
                CustomAnnotation annotation = method.getAnnotation(CustomAnnotation.class);
                if (annotation != null) {
                    System.out.println("    Method: " + method.getName());
                    System.out.println("      Annotation Value: " + annotation.value());
                    System.out.println("      Priority: " + annotation.priority());
                }
            }
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateRuntimeAnnotationAccess() {
        try {
            // Access annotations at runtime
            Class<?> clazz = CustomAnnotationDemo.class;
            Annotation[] annotations = clazz.getAnnotations();
            
            System.out.println("    Runtime Annotations:");
            for (Annotation annotation : annotations) {
                System.out.println("      " + annotation.annotationType().getSimpleName());
            }
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateBasicEnum() {
        System.out.println("    Basic Enum Values:");
        for (BasicEnum value : BasicEnum.values()) {
            System.out.println("      " + value.name() + " (ordinal: " + value.ordinal() + ")");
        }
        
        // Enum comparison
        BasicEnum first = BasicEnum.FIRST;
        BasicEnum second = BasicEnum.SECOND;
        
        System.out.println("    Comparison: " + first.compareTo(second));
        System.out.println("    Equals: " + first.equals(second));
    }
    
    private static void demonstrateEnumWithMethods() {
        System.out.println("    Enum with Methods:");
        for (EnumWithMethods value : EnumWithMethods.values()) {
            System.out.println("      " + value.name() + ": " + value.getDescription());
            System.out.println("        Action: " + value.performAction());
        }
    }
    
    private static void demonstrateEnumWithFields() {
        System.out.println("    Enum with Fields:");
        for (EnumWithFields value : EnumWithFields.values()) {
            System.out.println("      " + value.name() + ":");
            System.out.println("        Code: " + value.getCode());
            System.out.println("        Message: " + value.getMessage());
        }
    }
    
    private static void demonstrateEnumStrategyPattern() {
        System.out.println("    Enum Strategy Pattern:");
        String input = "Hello World";
        
        for (EnumStrategy strategy : EnumStrategy.values()) {
            String result = strategy.process(input);
            System.out.println("      " + strategy.name() + ": " + result);
        }
    }
    
    private static void demonstrateStaticInnerClass() {
        // Static inner class can be instantiated without outer class instance
        StaticInnerClass.Inner inner = new StaticInnerClass.Inner("Static Inner");
        System.out.println("    " + inner.getMessage());
        
        // Access static members
        System.out.println("    Static Field: " + StaticInnerClass.Inner.STATIC_FIELD);
    }
    
    private static void demonstrateMemberInnerClass() {
        // Member inner class requires outer class instance
        MemberInnerClass outer = new MemberInnerClass("Outer");
        MemberInnerClass.Inner inner = outer.new Inner("Member Inner");
        
        System.out.println("    " + inner.getMessage());
        System.out.println("    Outer Field: " + inner.getOuterField());
    }
    
    private static void demonstrateLocalInnerClass() {
        // Local inner class defined inside method
        LocalInnerClassDemo demo = new LocalInnerClassDemo();
        demo.methodWithLocalClass();
    }
    
    private static void demonstrateAnonymousInnerClass() {
        // Anonymous inner class implementing interface
        AnonymousInnerClassDemo demo = new AnonymousInnerClassDemo();
        demo.createAnonymousClass();
    }
    
    private static void demonstrateInterfaceProxy() {
        try {
            // Create proxy for interface
            Calculator calculator = (Calculator) Proxy.newProxyInstance(
                Calculator.class.getClassLoader(),
                new Class<?>[] { Calculator.class },
                new CalculatorInvocationHandler()
            );
            
            int result = calculator.add(5, 3);
            System.out.println("    Proxy Result: " + result);
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateMethodInterception() {
        try {
            // Create proxy with method interception
            Service service = (Service) Proxy.newProxyInstance(
                Service.class.getClassLoader(),
                new Class<?>[] { Service.class },
                new LoggingInvocationHandler(new ServiceImpl())
            );
            
            service.doSomething("Important Task");
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstratePerformanceProxy() {
        try {
            // Create performance monitoring proxy
            ExpensiveService expensiveService = (ExpensiveService) Proxy.newProxyInstance(
                ExpensiveService.class.getClassLoader(),
                new Class<?>[] { ExpensiveService.class },
                new PerformanceMonitoringHandler(new ExpensiveServiceImpl())
            );
            
            expensiveService.performExpensiveOperation();
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateCachingProxy() {
        try {
            // Create caching proxy
            DataService dataService = (DataService) Proxy.newProxyInstance(
                DataService.class.getClassLoader(),
                new Class<?>[] { DataService.class },
                new CachingHandler(new DataServiceImpl())
            );
            
            // First call - should fetch from service
            String data1 = dataService.getData("key1");
            System.out.println("    First call result: " + data1);
            
            // Second call - should return from cache
            String data2 = dataService.getData("key1");
            System.out.println("    Second call result: " + data2);
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateRecords() {
        // Records are immutable data carriers
        PersonRecord person = new PersonRecord("John Doe", 30, "john@example.com");
        
        System.out.println("    Record Person:");
        System.out.println("      Name: " + person.name());
        System.out.println("      Age: " + person.age());
        System.out.println("      Email: " + person.email());
        System.out.println("      toString: " + person);
        System.out.println("      hashCode: " + person.hashCode());
        
        // Records are immutable
        // person.name() = "Jane Doe"; // This would cause compilation error
    }
    
    private static void demonstrateSealedClasses() {
        // Sealed classes restrict inheritance
        System.out.println("    Sealed Class Hierarchy:");
        
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        Shape triangle = new Triangle(3.0, 4.0, 5.0);
        
        System.out.println("      Circle area: " + circle.calculateArea());
        System.out.println("      Rectangle area: " + rectangle.calculateArea());
        System.out.println("      Triangle area: " + triangle.calculateArea());
    }
    
    private static void demonstratePatternMatching() {
        System.out.println("    Pattern Matching:");
        
        Object obj = "Hello World";
        
        // Pattern matching for instanceof (Java 16+)
        if (obj instanceof String str) {
            System.out.println("      String length: " + str.length());
            System.out.println("      String uppercase: " + str.toUpperCase());
        }
        
        // Pattern matching in switch (Java 17+)
        String result = switch (obj) {
            case String s -> "String: " + s;
            case Integer i -> "Integer: " + i;
            case null -> "null";
            default -> "Unknown type";
        };
        
        System.out.println("      Switch result: " + result);
    }
    
    private static void demonstrateSwitchExpressions() {
        System.out.println("    Switch Expressions:");
        
        String day = "MONDAY";
        String result = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> "Unknown";
        };
        
        System.out.println("      " + day + " is a " + result);
        
        // Switch expression with yield
        int number = 3;
        String numberType = switch (number) {
            case 1, 3, 5, 7, 9 -> {
                yield "Odd";
            }
            case 2, 4, 6, 8, 10 -> {
                yield "Even";
            }
            default -> {
                yield "Unknown";
            }
        };
        
        System.out.println("      " + number + " is " + numberType);
    }
    
    private static void demonstrateVarHandleBasics() {
        try {
            // Create VarHandle for field access
            Lookup lookup = MethodHandles.lookup();
            VarHandle nameHandle = lookup.findVarHandle(
                VarHandleDemo.class, "name", String.class);
            
            VarHandleDemo demo = new VarHandleDemo();
            
            // Get value
            String name = (String) nameHandle.get(demo);
            System.out.println("    Current name: " + name);
            
            // Set value
            nameHandle.set(demo, "Modified via VarHandle");
            System.out.println("    New name: " + nameHandle.get(demo));
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateAtomicOperations() {
        try {
            Lookup lookup = MethodHandles.lookup();
            VarHandle counterHandle = lookup.findVarHandle(
                VarHandleDemo.class, "counter", int.class);
            
            VarHandleDemo demo = new VarHandleDemo();
            
            // Atomic operations
            int oldValue = (int) counterHandle.getAndAdd(demo, 5);
            System.out.println("    Old value: " + oldValue + ", New value: " + demo.getCounter());
            
            boolean success = counterHandle.compareAndSet(demo, 5, 10);
            System.out.println("    CAS success: " + success + ", Counter: " + demo.getCounter());
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateMethodHandles() {
        try {
            Lookup lookup = MethodHandles.lookup();
            
            // Find method handle
            MethodHandle methodHandle = lookup.findVirtual(
                String.class, "substring", MethodType.methodType(String.class, int.class, int.class));
            
            // Invoke method handle
            String result = (String) methodHandle.invoke("Hello World", 0, 5);
            System.out.println("    Method handle result: " + result);
            
            // Bind to instance
            MethodHandle boundHandle = methodHandle.bindTo("Hello World");
            String boundResult = (String) boundHandle.invoke(0, 5);
            System.out.println("    Bound method handle result: " + boundResult);
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstratePerformanceComparison() {
        System.out.println("    Performance Comparison:");
        
        // Traditional reflection vs VarHandle
        long startTime = System.nanoTime();
        
        // Traditional reflection
        try {
            Field field = PerformanceDemo.class.getDeclaredField("value");
            field.setAccessible(true);
            PerformanceDemo demo = new PerformanceDemo();
            
            for (int i = 0; i < 100000; i++) {
                field.set(demo, i);
                field.get(demo);
            }
        } catch (Exception e) {
            // Ignore for performance test
        }
        
        long reflectionTime = System.nanoTime() - startTime;
        
        // VarHandle
        startTime = System.nanoTime();
        try {
            Lookup lookup = MethodHandles.lookup();
            VarHandle valueHandle = lookup.findVarHandle(
                PerformanceDemo.class, "value", int.class);
            
            PerformanceDemo demo = new PerformanceDemo();
            
            for (int i = 0; i < 100000; i++) {
                valueHandle.set(demo, i);
                valueHandle.get(demo);
            }
        } catch (Exception e) {
            // Ignore for performance test
        }
        
        long varHandleTime = System.nanoTime() - startTime;
        
        System.out.println("      Reflection time: " + reflectionTime + " ns");
        System.out.println("      VarHandle time: " + varHandleTime + " ns");
        System.out.println("      Performance ratio: " + String.format("%.2f", (double) reflectionTime / varHandleTime));
    }
    
    // ===== UTILITY METHODS =====
    
    private static Class<?> createDynamicClass() {
        // This is a simplified example - in real implementation, you'd use bytecode generation
        return DynamicClass.class;
    }
    
    // ===== HELPER CLASSES =====
    
    static class ReflectionDemo {
        private String privateField = "Private Value";
        
        public ReflectionDemo() {}
        
        public ReflectionDemo(String value) {
            this.privateField = value;
        }
        
        public String getPrivateField() {
            return privateField;
        }
    }
    
    static class BuiltInAnnotationDemo {
        @Override
        public String toString() {
            return "BuiltInAnnotationDemo";
        }
        
        @Deprecated
        public void deprecatedMethod() {
            System.out.println("        This method is deprecated");
        }
        
        @SafeVarargs
        public final <T> void varargsMethod(T... args) {
            System.out.println("        Varargs method called with " + args.length + " arguments");
        }
    }
    
    @CustomAnnotation(value = "Class Level", priority = 1, tags = {"class", "demo"})
    static class CustomAnnotationDemo {
        @CustomAnnotation(value = "Method Level", priority = 2, tags = {"method", "important"})
        public void annotatedMethod() {
            System.out.println("        Annotated method called");
        }
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    @interface CustomAnnotation {
        String value();
        int priority() default 0;
        String[] tags() default {};
    }
    
    enum BasicEnum {
        FIRST, SECOND, THIRD
    }
    
    enum EnumWithMethods {
        ADD("Addition operation") {
            @Override
            public String performAction() {
                return "Adding numbers";
            }
        },
        SUBTRACT("Subtraction operation") {
            @Override
            public String performAction() {
                return "Subtracting numbers";
            }
        },
        MULTIPLY("Multiplication operation") {
            @Override
            public String performAction() {
                return "Multiplying numbers";
            }
        };
        
        private final String description;
        
        EnumWithMethods(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
        
        public abstract String performAction();
    }
    
    enum EnumWithFields {
        SUCCESS(200, "Operation successful"),
        NOT_FOUND(404, "Resource not found"),
        SERVER_ERROR(500, "Internal server error");
        
        private final int code;
        private final String message;
        
        EnumWithFields(int code, String message) {
            this.code = code;
            this.message = message;
        }
        
        public int getCode() { return code; }
        public String getMessage() { return message; }
    }
    
    enum EnumStrategy {
        UPPERCASE {
            @Override
            public String process(String input) {
                return input.toUpperCase();
            }
        },
        LOWERCASE {
            @Override
            public String process(String input) {
                return input.toLowerCase();
            }
        },
        REVERSE {
            @Override
            public String process(String input) {
                return new StringBuilder(input).reverse().toString();
            }
        };
        
        public abstract String process(String input);
    }
    
    static class StaticInnerClass {
        private static final String STATIC_FIELD = "Static Field Value";
        
        static class Inner {
            private String message;
            
            public Inner(String message) {
                this.message = message;
            }
            
            public String getMessage() {
                return message;
            }
        }
    }
    
    static class MemberInnerClass {
        private String outerField;
        
        public MemberInnerClass(String outerField) {
            this.outerField = outerField;
        }
        
        class Inner {
            private String message;
            
            public Inner(String message) {
                this.message = message;
            }
            
            public String getMessage() {
                return message;
            }
            
            public String getOuterField() {
                return outerField;
            }
        }
    }
    
    static class LocalInnerClassDemo {
        public void methodWithLocalClass() {
            class LocalClass {
                private String message;
                
                public LocalClass(String message) {
                    this.message = message;
                }
                
                public String getMessage() {
                    return message;
                }
            }
            
            LocalClass local = new LocalClass("Local Inner Class");
            System.out.println("    " + local.getMessage());
        }
    }
    
    static class AnonymousInnerClassDemo {
        public void createAnonymousClass() {
            // Anonymous inner class implementing Runnable
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("    Anonymous inner class executing");
                }
            };
            
            runnable.run();
        }
    }
    
    interface Calculator {
        int add(int a, int b);
    }
    
    static class CalculatorInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("    Proxy: Calling method " + method.getName());
            System.out.println("    Proxy: Arguments " + Arrays.toString(args));
            
            // Simple implementation
            if (method.getName().equals("add")) {
                return (Integer) args[0] + (Integer) args[1];
            }
            
            return null;
        }
    }
    
    interface Service {
        void doSomething(String task);
    }
    
    static class ServiceImpl implements Service {
        @Override
        public void doSomething(String task) {
            System.out.println("    Service: Executing task: " + task);
        }
    }
    
    static class LoggingInvocationHandler implements InvocationHandler {
        private final Object target;
        
        public LoggingInvocationHandler(Object target) {
            this.target = target;
        }
        
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("    Logging: Before method " + method.getName());
            long startTime = System.currentTimeMillis();
            
            Object result = method.invoke(target, args);
            
            long endTime = System.currentTimeMillis();
            System.out.println("    Logging: After method " + method.getName() + 
                             " (took " + (endTime - startTime) + "ms)");
            
            return result;
        }
    }
    
    interface ExpensiveService {
        void performExpensiveOperation();
    }
    
    static class ExpensiveServiceImpl implements ExpensiveService {
        @Override
        public void performExpensiveOperation() {
            try {
                Thread.sleep(100); // Simulate expensive operation
                System.out.println("    ExpensiveService: Operation completed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    static class PerformanceMonitoringHandler implements InvocationHandler {
        private final Object target;
        
        public PerformanceMonitoringHandler(Object target) {
            this.target = target;
        }
        
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTime = System.nanoTime();
            
            Object result = method.invoke(target, args);
            
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
            
            System.out.println("    Performance: Method " + method.getName() + 
                             " took " + duration + "ms");
            
            return result;
        }
    }
    
    interface DataService {
        String getData(String key);
    }
    
    static class DataServiceImpl implements DataService {
        @Override
        public String getData(String key) {
            System.out.println("    DataService: Fetching data for key: " + key);
            return "Data for " + key;
        }
    }
    
    static class CachingHandler implements InvocationHandler {
        private final Object target;
        private final Map<String, Object> cache = new HashMap<>();
        
        public CachingHandler(Object target) {
            this.target = target;
        }
        
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("getData")) {
                String key = (String) args[0];
                
                if (cache.containsKey(key)) {
                    System.out.println("    Cache: Returning cached data for key: " + key);
                    return cache.get(key);
                }
                
                Object result = method.invoke(target, args);
                cache.put(key, result);
                System.out.println("    Cache: Stored data for key: " + key);
                
                return result;
            }
            
            return method.invoke(target, args);
        }
    }
    
    // Records (Java 16+)
    record PersonRecord(String name, int age, String email) {
        // Records automatically provide:
        // - Constructor
        // - Getters (named after fields)
        // - equals(), hashCode(), toString()
        // - Immutability
    }
    
    // Sealed Classes (Java 17+)
    sealed abstract class Shape permits Circle, Rectangle, Triangle {
        public abstract double calculateArea();
    }
    
    final class Circle extends Shape {
        private final double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }
    
    final class Rectangle extends Shape {
        private final double width;
        private final double height;
        
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        @Override
        public double calculateArea() {
            return width * height;
        }
    }
    
    final class Triangle extends Shape {
        private final double a, b, c;
        
        public Triangle(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        @Override
        public double calculateArea() {
            // Heron's formula
            double s = (a + b + c) / 2;
            return Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }
    }
    
    static class VarHandleDemo {
        public String name = "Default Name";
        public int counter = 0;
        
        public int getCounter() { return counter; }
    }
    
    static class PerformanceDemo {
        public int value = 0;
    }
    
    static class DynamicClass {
        // Placeholder for dynamic class creation
    }

/**
 * INTERVIEW QUESTIONS FOR ADVANCED JAVA LANGUAGE FEATURES:
 * 
 * THEORETICAL QUESTIONS:
 * 1. What is reflection and when would you use it?
 * 2. Explain the difference between compile-time and runtime annotations.
 * 3. What are the benefits and drawbacks of using reflection?
 * 4. How do enums work internally in Java?
 * 5. What are the different types of inner classes and when to use each?
 * 6. Explain the concept of dynamic proxies and their use cases.
 * 7. What are records and how do they differ from regular classes?
 * 8. Explain sealed classes and their purpose.
 * 
 * PRACTICAL QUESTIONS:
 * 9. Implement a custom annotation processor.
 * 10. Create a dynamic proxy for method caching.
 * 11. Design an enum-based state machine.
 * 12. Implement reflection-based object serialization.
 * 13. Create a custom ClassLoader using reflection.
 * 14. Design a plugin system using dynamic proxies.
 * 15. Implement pattern matching for custom types.
 * 16. Create a VarHandle-based concurrent counter.
 * 
 * TRICKY SCENARIOS:
 * 17. What happens if you modify a final field via reflection?
 * 18. How can you break encapsulation using reflection?
 * 19. What are the performance implications of using reflection?
 * 20. How do you handle checked exceptions in reflection?
 * 21. What happens if you access a private field without setAccessible(true)?
 * 22. How can you create an instance of an abstract class using reflection?
 * 23. What are the security implications of using reflection?
 * 24. How do you handle generic type erasure in reflection?
 * 
 * PERFORMANCE QUESTIONS:
 * 25. How does reflection performance compare to direct method calls?
 * 26. What are the overhead costs of using dynamic proxies?
 * 27. How can you optimize reflection-based code?
 * 28. What are the performance implications of using VarHandles?
 * 29. How does annotation processing affect runtime performance?
 * 30. What are the memory costs of using inner classes?
 * 
 * DESIGN PATTERN QUESTIONS:
 * 31. How would you implement the Proxy pattern using dynamic proxies?
 * 32. Design a factory pattern using reflection.
 * 33. Implement the Strategy pattern using enums.
 * 34. How would you create a builder pattern using reflection?
 * 35. Design a visitor pattern using reflection.
 * 36. Implement the Observer pattern using dynamic proxies.
 * 37. Create a command pattern using reflection.
 * 38. How would you implement the Template Method pattern using reflection?
 * 
 * ADVANCED CONCEPTS:
 * 39. Explain the concept of method handles and their advantages.
 * 40. How do VarHandles provide better performance than reflection?
 * 41. What are the implications of using MethodHandles.lookup()?
 * 42. How can you implement custom bytecode generation?
 * 43. Explain the concept of annotation processors.
 * 44. How do you handle generic type information at runtime?
 * 45. What are the security implications of using MethodHandles?
 * 46. How can you implement custom class loading with reflection?
 * 
 * REAL-WORLD SCENARIOS:
 * 47. Design a configuration system using annotations and reflection.
 * 48. Implement a dependency injection framework using reflection.
 * 49. Create a validation framework using annotations.
 * 50. Design a plugin architecture using dynamic proxies.
 * 51. Implement a serialization framework using reflection.
 * 52. Create a testing framework using annotations.
 * 53. Design a logging framework using dynamic proxies.
 * 54. Implement a caching system using reflection.
 * 
 * SYSTEM DESIGN QUESTIONS:
 * 55. How would you design a microservices framework using reflection?
 * 56. Design an API gateway using dynamic proxies.
 * 57. Implement a service mesh using reflection.
 * 58. Create a load balancer using dynamic proxies.
 * 59. Design a circuit breaker using reflection.
 * 60. Implement a rate limiter using dynamic proxies.
 * 61. Create a monitoring system using reflection.
 * 62. Design a configuration management system using annotations.
 * 
 * MONITORING AND DEBUGGING:
 * 63. How would you implement runtime method profiling using reflection?
 * 64. Design a debugging framework using dynamic proxies.
 * 65. Implement performance monitoring using reflection.
 * 66. Create a logging interceptor using dynamic proxies.
 * 67. Design a metrics collector using reflection.
 * 68. Implement error tracking using dynamic proxies.
 * 69. Create a health check system using reflection.
 * 70. Design a tracing system using dynamic proxies.
 * 
 * OPTIMIZATION TECHNIQUES:
 * 71. How can you optimize reflection-based method calls?
 * 72. Implement caching for reflection operations.
 * 73. Design a reflection optimization framework.
 * 74. Create a bytecode optimization system.
 * 75. Implement method inlining using reflection.
 * 76. Design a code generation framework.
 * 77. Create a performance profiling tool.
 * 78. Implement a reflection benchmarking system.
 * 
 * SCALABILITY CONSIDERATIONS:
 * 79. How would you design a reflection system that scales horizontally?
 * 80. Implement distributed reflection processing.
 * 81. Design a reflection caching strategy for distributed systems.
 * 82. Create a reflection load balancing mechanism.
 * 83. Implement reflection-based service discovery.
 * 84. Design a reflection monitoring system for microservices.
 * 85. Create a reflection-based configuration distribution system.
 * 86. Implement reflection-based health checking for distributed systems.
 * 
 * SECURITY AND SAFETY:
 * 87. How can you prevent reflection-based security attacks?
 * 88. Implement access control for reflection operations.
 * 89. Design a security framework for dynamic proxies.
 * 90. Create a validation system for reflection operations.
 * 91. Implement encryption for reflection-based data.
 * 92. Design an authentication mechanism for reflection.
 * 93. Create an authorization framework for dynamic proxies.
 * 94. Implement security policies for reflection usage.
 * 
 * TESTING AND VALIDATION:
 * 95. How would you test reflection-based code?
 * 96. Design a testing framework for dynamic proxies.
 * 97. Implement validation for reflection operations.
 * 98. Create a debugging framework for reflection.
 * 99. Design a performance testing suite for reflection.
 * 100. Implement monitoring for reflection-based systems.
 */
}
