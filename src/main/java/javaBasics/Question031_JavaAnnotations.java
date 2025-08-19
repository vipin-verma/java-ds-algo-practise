import java.lang.annotation.*;
import java.lang.reflect.*;

/**
 * Question 31: Java Annotations
 * 
 * This file contains 50+ Annotations interview questions covering:
 * - Annotation Basics and Types
 * - Built-in Annotations
 * - Custom Annotations
 * - Annotation Processing
 * - Advanced Annotation Concepts
 */
public class Question031_JavaAnnotations {
    
    public static void main(String[] args) {
        System.out.println("=== Java Annotations - Interview Questions ===\n");
        
        demonstrateAnnotationBasics();
        demonstrateBuiltInAnnotations();
        demonstrateCustomAnnotations();
        demonstrateAnnotationProcessing();
        demonstrateAdvancedAnnotations();
        
        System.out.println("\n=== Java Annotations Completed! ===");
    }
    
    // ===== ANNOTATION BASICS =====
    
    private static void demonstrateAnnotationBasics() {
        System.out.println("1. ANNOTATION BASICS:\n");
        
        // Q1: What are Annotations in Java?
        System.out.println("Q1: What are Annotations in Java?");
        System.out.println("    Annotations are metadata about code that provides information");
        System.out.println("    to the compiler, runtime, or other tools.");
        System.out.println("    They start with @ symbol and don't affect program execution.\n");
        
        // Q2: What are the main purposes of Annotations?
        System.out.println("Q2: What are the main purposes of Annotations?");
        System.out.println("    - Compiler instructions");
        System.out.println("    - Runtime processing");
        System.out.println("    - Code generation");
        System.out.println("    - Documentation\n");
        
        // Q3: What are the types of Annotations?
        System.out.println("Q3: What are the types of Annotations?");
        System.out.println("    - Marker: No elements (@Override)");
        System.out.println("    - Single-value: One element (@SuppressWarnings)");
        System.out.println("    - Multi-value: Multiple elements (@RequestMapping)");
        System.out.println("    - Meta-annotations: Annotations on annotations\n");
        
        // Demonstrate annotation basics
        System.out.println("Example: Annotation Types");
        
        // Marker annotation
        @Override
        public String toString() {
            return "Annotation Demo";
        }
        
        // Single-value annotation
        @SuppressWarnings("unused")
        String unusedVariable = "This is unused";
        
        // Multi-value annotation (custom)
        @CustomAnnotation(value = "Test", count = 5)
        String annotatedVariable = "This has custom annotation";
        
        System.out.println("    Annotations applied successfully");
        System.out.println("    toString method overridden");
        System.out.println("    Unused variable warning suppressed");
        System.out.println("    Custom annotation applied");
        System.out.println();
    }
    
    // ===== BUILT-IN ANNOTATIONS =====
    
    private static void demonstrateBuiltInAnnotations() {
        System.out.println("2. BUILT-IN ANNOTATIONS:\n");
        
        // Q4: What are the main built-in annotations?
        System.out.println("Q4: What are the main built-in annotations?");
        System.out.println("    - @Override: Method override");
        System.out.println("    - @Deprecated: Mark as deprecated");
        System.out.println("    - @SuppressWarnings: Suppress warnings");
        System.out.println("    - @FunctionalInterface: Functional interface");
        System.out.println("    - @SafeVarargs: Safe varargs method\n");
        
        // Q5: What is the purpose of @Override annotation?
        System.out.println("Q5: What is the purpose of @Override annotation?");
        System.out.println("    - Ensures method is actually overriding a superclass method");
        System.out.println("    - Compiler error if method doesn't override anything");
        System.out.println("    - Makes code more readable and maintainable");
        System.out.println("    - Prevents accidental method hiding\n");
        
        // Demonstrate built-in annotations
        System.out.println("Example: Built-in Annotations");
        
        // @Override annotation
        @Override
        public String toString() {
            return "Built-in Annotations Demo";
        }
        
        // @Deprecated annotation
        @Deprecated
        public void oldMethod() {
            System.out.println("    This method is deprecated");
        }
        
        // @SuppressWarnings annotation
        @SuppressWarnings({"unused", "rawtypes"})
        public void methodWithWarnings() {
            String unused = "unused variable";
            List list = new ArrayList(); // raw type warning suppressed
        }
        
        // @FunctionalInterface annotation
        @FunctionalInterface
        interface MyFunctionalInterface {
            void doSomething();
            // Can have default methods
            default void defaultMethod() {
                System.out.println("    Default method");
            }
        }
        
        // @SafeVarargs annotation
        @SafeVarargs
        public static <T> void safeMethod(T... args) {
            System.out.println("    Safe varargs method called with " + args.length + " arguments");
        }
        
        // Demonstrate usage
        System.out.println("    toString(): " + toString());
        oldMethod();
        methodWithWarnings();
        safeMethod("Hello", "World", "Annotations");
        
        // Functional interface usage
        MyFunctionalInterface func = () -> System.out.println("    Lambda executed");
        func.doSomething();
        func.defaultMethod();
        System.out.println();
    }
    
    // ===== CUSTOM ANNOTATIONS =====
    
    private static void demonstrateCustomAnnotations() {
        System.out.println("3. CUSTOM ANNOTATIONS:\n");
        
        // Q6: How do you create custom annotations?
        System.out.println("Q6: How do you create custom annotations?");
        System.out.println("    - Use @interface keyword");
        System.out.println("    - Define elements with default values");
        System.out.println("    - Use meta-annotations to configure behavior");
        System.out.println("    - Specify retention and target policies\n");
        
        // Q7: What are meta-annotations?
        System.out.println("Q7: What are meta-annotations?");
        System.out.println("    - @Retention: When annotation is available");
        System.out.println("    - @Target: Where annotation can be applied");
        System.out.println("    - @Documented: Include in JavaDoc");
        System.out.println("    - @Inherited: Inherit by subclasses\n");
        
        // Demonstrate custom annotations
        System.out.println("Example: Custom Annotations");
        
        // Apply custom annotations
        @CustomAnnotation(value = "Test Value", count = 10)
        @RuntimeAnnotation(description = "Runtime annotation")
        @DocumentedAnnotation(author = "Developer", version = "1.0")
        @InheritedAnnotation
        public void annotatedMethod() {
            System.out.println("    Method with multiple custom annotations");
        }
        
        // Apply to class
        @CustomAnnotation(value = "Class Level", count = 5)
        @RuntimeAnnotation(description = "Class annotation")
        class AnnotatedClass {
            @CustomAnnotation(value = "Field Level", count = 1)
            private String annotatedField;
            
            @CustomAnnotation(value = "Constructor Level", count = 2)
            public AnnotatedClass() {
                this.annotatedField = "Initialized";
            }
        }
        
        // Apply to field
        @CustomAnnotation(value = "Field Annotation", count = 3)
        private static final String STATIC_FIELD = "Static Value";
        
        // Apply to parameter
        public void methodWithParameter(@CustomAnnotation(value = "Parameter", count = 1) String param) {
            System.out.println("    Parameter: " + param);
        }
        
        // Demonstrate usage
        annotatedMethod();
        AnnotatedClass obj = new AnnotatedClass();
        methodWithParameter("Test Parameter");
        
        System.out.println("    Custom annotations applied successfully");
        System.out.println();
    }
    
    // ===== ANNOTATION PROCESSING =====
    
    private static void demonstrateAnnotationProcessing() {
        System.out.println("4. ANNOTATION PROCESSING:\n");
        
        // Q8: How do you process annotations at runtime?
        System.out.println("Q8: How do you process annotations at runtime?");
        System.out.println("    - Use reflection to get annotation information");
        System.out.println("    - Check if annotation is present");
        System.out.println("    - Extract annotation values");
        System.out.println("    - Process annotations on classes, methods, fields\n");
        
        // Q9: What is the difference between compile-time and runtime annotations?
        System.out.println("Q9: What is the difference between compile-time and runtime annotations?");
        System.out.println("    - Compile-time: Processed by compiler, not available at runtime");
        System.out.println("    - Runtime: Available at runtime through reflection");
        System.out.println("    - Retention policy determines availability");
        System.out.println("    - Source, Class, and Runtime retention levels\n");
        
        // Demonstrate annotation processing
        System.out.println("Example: Annotation Processing");
        
        try {
            // Get class annotations
            Class<?> clazz = AnnotationDemo.class;
            System.out.println("    Processing annotations on class: " + clazz.getName());
            
            // Check if annotation is present
            if (clazz.isAnnotationPresent(CustomAnnotation.class)) {
                CustomAnnotation annotation = clazz.getAnnotation(CustomAnnotation.class);
                System.out.println("    Custom annotation found:");
                System.out.println("      Value: " + annotation.value());
                System.out.println("      Count: " + annotation.count());
            }
            
            // Get method annotations
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(CustomAnnotation.class)) {
                    CustomAnnotation annotation = method.getAnnotation(CustomAnnotation.class);
                    System.out.println("    Method " + method.getName() + " has annotation:");
                    System.out.println("      Value: " + annotation.value());
                    System.out.println("      Count: " + annotation.count());
                }
                
                if (method.isAnnotationPresent(RuntimeAnnotation.class)) {
                    RuntimeAnnotation annotation = method.getAnnotation(RuntimeAnnotation.class);
                    System.out.println("    Method " + method.getName() + " has runtime annotation:");
                    System.out.println("      Description: " + annotation.description());
                }
            }
            
            // Get field annotations
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(CustomAnnotation.class)) {
                    CustomAnnotation annotation = field.getAnnotation(CustomAnnotation.class);
                    System.out.println("    Field " + field.getName() + " has annotation:");
                    System.out.println("      Value: " + annotation.value());
                    System.out.println("      Count: " + annotation.count());
                }
            }
            
            // Get constructor annotations
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                if (constructor.isAnnotationPresent(CustomAnnotation.class)) {
                    CustomAnnotation annotation = constructor.getAnnotation(CustomAnnotation.class);
                    System.out.println("    Constructor has annotation:");
                    System.out.println("      Value: " + annotation.value());
                    System.out.println("      Count: " + annotation.count());
                }
            }
            
            // Get all annotations
            Annotation[] allAnnotations = clazz.getAnnotations();
            System.out.println("    All annotations on class:");
            for (Annotation annotation : allAnnotations) {
                System.out.println("      " + annotation.annotationType().getSimpleName());
            }
            
        } catch (Exception e) {
            System.out.println("    Error processing annotations: " + e.getMessage());
        }
        System.out.println();
    }
    
    // ===== ADVANCED ANNOTATIONS =====
    
    private static void demonstrateAdvancedAnnotations() {
        System.out.println("5. ADVANCED ANNOTATIONS:\n");
        
        // Q10: What are advanced annotation use cases?
        System.out.println("Q10: What are advanced annotation use cases?");
        System.out.println("    - Validation frameworks");
        System.out.println("    - Dependency injection");
        System.out.println("    - ORM mapping");
        System.out.println("    - API documentation");
        System.out.println("    - Testing frameworks\n");
        
        // Q11: How do you create annotation processors?
        System.out.println("Q11: How do you create annotation processors?");
        System.out.println("    - Implement AbstractProcessor");
        System.out.println("    - Override process() method");
        System.out.println("    - Use RoundEnvironment to get annotated elements");
        System.out.println("    - Generate code or perform validation\n");
        
        // Demonstrate advanced annotations
        System.out.println("Example: Advanced Annotations");
        
        // Validation annotation usage
        @NotNull
        @Size(min = 3, max = 50)
        @Email
        String email = "test@example.com";
        
        // ORM annotation usage
        @Entity
        @Table(name = "users")
        class User {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            
            @Column(name = "user_name", nullable = false)
            @Size(min = 2, max = 30)
            private String username;
            
            @Column(name = "email_address", unique = true)
            @Email
            private String emailAddress;
            
            @Transient
            private String temporaryField;
        }
        
        // API documentation annotation
        @ApiOperation(value = "Create user", notes = "Creates a new user in the system")
        @ApiResponses({
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 409, message = "User already exists")
        })
        public void createUser(@ApiParam(value = "User data", required = true) User user) {
            System.out.println("    Creating user: " + user.username);
        }
        
        // Testing annotation
        @Test
        @DisplayName("Test user creation")
        @Tag("user-management")
        @Timeout(value = 5, unit = TimeUnit.SECONDS)
        public void testUserCreation() {
            System.out.println("    Running user creation test");
        }
        
        // Dependency injection annotation
        @Component
        @Scope("singleton")
        @Qualifier("userService")
        class UserService {
            @Autowired
            @Lazy
            private UserRepository repository;
            
            @PostConstruct
            public void init() {
                System.out.println("    UserService initialized");
            }
            
            @PreDestroy
            public void cleanup() {
                System.out.println("    UserService cleaning up");
            }
        }
        
        System.out.println("    Advanced annotations applied successfully");
        System.out.println("    Validation annotations: " + email);
        System.out.println("    ORM annotations: User entity defined");
        System.out.println("    API annotations: Documentation added");
        System.out.println("    Testing annotations: Test method defined");
        System.out.println("    DI annotations: Service class defined");
        System.out.println();
    }
    
    // ===== HELPER CLASSES =====
    
    // Custom annotation with multiple elements
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @interface CustomAnnotation {
        String value() default "Default Value";
        int count() default 0;
    }
    
    // Runtime annotation
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    @interface RuntimeAnnotation {
        String description();
    }
    
    // Documented annotation
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Documented
    @interface DocumentedAnnotation {
        String author();
        String version();
    }
    
    // Inherited annotation
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    @interface InheritedAnnotation {
    }
    
    // Validation annotations
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @interface NotNull {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @interface Size {
        int min() default 0;
        int max() default Integer.MAX_VALUE;
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @interface Email {
    }
    
    // ORM annotations
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Entity {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Table {
        String name();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Id {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface GeneratedValue {
        String strategy();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Column {
        String name();
        boolean nullable() default true;
        boolean unique() default false;
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Transient {
    }
    
    // API documentation annotations
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface ApiOperation {
        String value();
        String notes() default "";
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface ApiResponses {
        ApiResponse[] value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface ApiResponse {
        int code();
        String message();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.PARAMETER})
    @interface ApiParam {
        String value();
        boolean required() default false;
    }
    
    // Testing annotations
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Test {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface DisplayName {
        String value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Tag {
        String value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Timeout {
        long value();
        TimeUnit unit();
    }
    
    // Dependency injection annotations
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Component {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Scope {
        String value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Qualifier {
        String value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Autowired {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Lazy {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface PostConstruct {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface PreDestroy {
    }
    
    // Mock classes for demonstration
    enum GenerationType {
        IDENTITY
    }
    
    enum TimeUnit {
        SECONDS
    }
    
    class UserRepository {
    }
    
    class User {
        String username;
    }
    
    // Main demo class
    @CustomAnnotation(value = "Class Level Demo", count = 100)
    @RuntimeAnnotation(description = "Main demo class")
    public static class AnnotationDemo {
        @CustomAnnotation(value = "Field Demo", count = 50)
        private String demoField;
        
        @CustomAnnotation(value = "Constructor Demo", count = 25)
        public AnnotationDemo() {
            this.demoField = "Initialized";
        }
        
        @CustomAnnotation(value = "Method Demo", count = 75)
        @RuntimeAnnotation(description = "Demo method")
        public void demoMethod() {
            System.out.println("    Demo method executed");
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA ANNOTATIONS:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are Annotations in Java?
 * 2. What are the main purposes of Annotations?
 * 3. What are the types of Annotations?
 * 4. What is the difference between annotations and comments?
 * 5. How do you apply annotations to code elements?
 * 6. What is the @Override annotation used for?
 * 7. What is the @Deprecated annotation used for?
 * 8. What is the @SuppressWarnings annotation used for?
 * 9. What is the @FunctionalInterface annotation used for?
 * 10. What is the @SafeVarargs annotation used for?
 * 11. How do you create custom annotations?
 * 12. What is the @interface keyword used for?
 * 13. How do you define annotation elements?
 * 14. How do you set default values for annotation elements?
 * 15. What are meta-annotations?
 * 16. What is the @Retention annotation used for?
 * 17. What is the @Target annotation used for?
 * 18. What is the @Documented annotation used for?
 * 19. What is the @Inherited annotation used for?
 * 20. What are the retention policy values?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. How do you process annotations at runtime?
 * 22. What is the difference between compile-time and runtime annotations?
 * 23. How do you check if an annotation is present?
 * 24. How do you get annotation values using reflection?
 * 25. How do you get all annotations on an element?
 * 26. How do you get annotations by type?
 * 27. How do you process annotations on classes?
 * 28. How do you process annotations on methods?
 * 29. How do you process annotations on fields?
 * 30. How do you process annotations on constructors?
 * 31. How do you process annotations on parameters?
 * 32. How do you create annotation processors?
 * 33. What is AbstractProcessor class?
 * 34. How do you override the process() method?
 * 35. How do you use RoundEnvironment?
 * 36. How do you generate code using annotations?
 * 37. How do you validate using annotations?
 * 38. How do you implement custom validation logic?
 * 39. How do you use annotations for configuration?
 * 40. How do you implement annotation-based frameworks?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you implement validation frameworks using annotations?
 * 42. How do you implement dependency injection using annotations?
 * 43. How do you implement ORM mapping using annotations?
 * 44. How do you implement API documentation using annotations?
 * 45. How do you implement testing frameworks using annotations?
 * 46. How do you implement caching using annotations?
 * 47. How do you implement security using annotations?
 * 48. How do you implement logging using annotations?
 * 49. How do you implement monitoring using annotations?
 * 50. How do you implement metrics using annotations?
 * 51. How do you implement tracing using annotations?
 * 52. How do you implement circuit breakers using annotations?
 * 53. How do you implement rate limiting using annotations?
 * 54. How do you implement retry logic using annotations?
 * 55. How do you implement timeout handling using annotations?
 * 56. How do you implement fallback logic using annotations?
 * 57. How do you implement load balancing using annotations?
 * 58. How do you implement service discovery using annotations?
 * 59. How do you implement configuration management using annotations?
 * 60. How do you implement feature flags using annotations?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you implement a custom annotation framework?
 * 62. How do you implement annotation-based code generation?
 * 63. How do you implement annotation-based validation engines?
 * 64. How do you implement annotation-based dependency injection containers?
 * 65. How do you implement annotation-based ORM systems?
 * 66. How do you implement annotation-based testing frameworks?
 * 67. How do you implement annotation-based monitoring systems?
 * 68. How do you implement annotation-based security frameworks?
 * 69. How do you implement annotation-based caching systems?
 * 70. How do you implement annotation-based logging frameworks?
 * 71. How do you implement annotation-based configuration systems?
 * 72. How do you implement annotation-based event systems?
 * 73. How do you implement annotation-based workflow engines?
 * 74. How do you implement annotation-based rule engines?
 * 75. How do you implement annotation-based parser systems?
 * 76. How do you implement annotation-based compiler systems?
 * 77. How do you implement annotation-based interpreter systems?
 * 78. How do you implement annotation-based virtual machines?
 * 79. How do you implement annotation-based operating systems?
 * 80. How do you implement annotation-based network protocols?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design an annotation-based framework?
 * 82. How would you implement an annotation-based validation system?
 * 83. How would you design an annotation-based configuration system?
 * 84. How would you implement an annotation-based dependency injection system?
 * 85. How would you design an annotation-based ORM system?
 * 86. How would you implement an annotation-based testing framework?
 * 87. How would you design an annotation-based monitoring system?
 * 88. How would you implement an annotation-based security system?
 * 89. How would you design an annotation-based caching system?
 * 90. How would you implement an annotation-based logging system?
 * 91. How would you design an annotation-based event system?
 * 92. How would you implement an annotation-based workflow system?
 * 93. How would you design an annotation-based rule engine?
 * 94. How would you implement an annotation-based parser system?
 * 95. How would you design an annotation-based compiler system?
 * 96. How would you implement an annotation-based interpreter system?
 * 97. How would you design an annotation-based virtual machine?
 * 98. How would you implement an annotation-based operating system?
 * 99. How would you design an annotation-based network protocol?
 * 100. How would you implement an annotation-based distributed system?
 */
