import java.lang.reflect.*;
import java.util.*;

/**
 * Question 30: Java Reflection
 * 
 * This file contains 50+ Reflection interview questions covering:
 * - Reflection Basics and Class Information
 * - Field and Method Access
 * - Constructor and Object Creation
 * - Advanced Reflection Concepts
 * - Reflection Best Practices and Use Cases
 */
public class Question030_JavaReflection {
    
    public static void main(String[] args) {
        System.out.println("=== Java Reflection - Interview Questions ===\n");
        
        demonstrateReflectionBasics();
        demonstrateClassInformation();
        demonstrateFieldAccess();
        demonstrateMethodAccess();
        demonstrateConstructorAccess();
        demonstrateAdvancedReflection();
        
        System.out.println("\n=== Java Reflection Completed! ===");
    }
    
    // ===== REFLECTION BASICS =====
    
    private static void demonstrateReflectionBasics() {
        System.out.println("1. REFLECTION BASICS:\n");
        
        // Q1: What is Reflection in Java?
        System.out.println("Q1: What is Reflection in Java?");
        System.out.println("    Reflection is the ability of a program to examine and modify");
        System.out.println("    its own structure and behavior at runtime.");
        System.out.println("    It allows inspection of classes, interfaces, fields, and methods.\n");
        
        // Q2: What are the main purposes of Reflection?
        System.out.println("Q2: What are the main purposes of Reflection?");
        System.out.println("    - Runtime type information");
        System.out.println("    - Dynamic object creation");
        System.out.println("    - Dynamic method invocation");
        System.out.println("    - Framework development\n");
        
        // Q3: How do you get Class objects?
        System.out.println("Q3: How do you get Class objects?");
        System.out.println("    - Object.getClass()");
        System.out.println("    - Class.forName()");
        System.out.println("    - ClassName.class");
        System.out.println("    - Type.class\n");
        
        // Demonstrate getting Class objects
        System.out.println("Example: Getting Class Objects");
        
        // Method 1: Object.getClass()
        String str = "Hello";
        Class<?> strClass1 = str.getClass();
        System.out.println("    String class via getClass(): " + strClass1.getName());
        
        // Method 2: Class.forName()
        try {
            Class<?> strClass2 = Class.forName("java.lang.String");
            System.out.println("    String class via forName(): " + strClass2.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("    Class not found: " + e.getMessage());
        }
        
        // Method 3: ClassName.class
        Class<String> strClass3 = String.class;
        System.out.println("    String class via .class: " + strClass3.getName());
        
        // Method 4: Type.class
        Class<Integer> intClass = int.class;
        Class<Integer> integerClass = Integer.class;
        System.out.println("    int.class: " + intClass.getName());
        System.out.println("    Integer.class: " + integerClass.getName());
        
        // Check if classes are the same
        System.out.println("    strClass1 == strClass2: " + (strClass1 == strClass2));
        System.out.println("    strClass1 == strClass3: " + (strClass1 == strClass3));
        System.out.println("    int.class == Integer.class: " + (intClass == integerClass));
        System.out.println();
    }
    
    // ===== CLASS INFORMATION =====
    
    private static void demonstrateClassInformation() {
        System.out.println("2. CLASS INFORMATION:\n");
        
        // Q4: What information can you get from a Class object?
        System.out.println("Q4: What information can you get from a Class object?");
        System.out.println("    - Class name, package, modifiers");
        System.out.println("    - Superclass and interfaces");
        System.out.println("    - Fields, methods, constructors");
        System.out.println("    - Annotations and type parameters\n");
        
        // Q5: How do you check class modifiers?
        System.out.println("Q5: How do you check class modifiers?");
        System.out.println("    - getModifiers() returns int");
        System.out.println("    - Modifier.isPublic(), Modifier.isFinal(), etc.");
        System.out.println("    - Modifier.toString() for readable format\n");
        
        // Demonstrate class information
        System.out.println("Example: Class Information");
        
        Class<?> personClass = Person.class;
        
        // Basic class information
        System.out.println("    Class name: " + personClass.getName());
        System.out.println("    Simple name: " + personClass.getSimpleName());
        System.out.println("    Canonical name: " + personClass.getCanonicalName());
        System.out.println("    Package: " + personClass.getPackage().getName());
        
        // Class modifiers
        int modifiers = personClass.getModifiers();
        System.out.println("    Modifiers: " + Modifier.toString(modifiers));
        System.out.println("    Is public: " + Modifier.isPublic(modifiers));
        System.out.println("    Is final: " + Modifier.isFinal(modifiers));
        System.out.println("    Is abstract: " + Modifier.isAbstract(modifiers));
        
        // Inheritance information
        Class<?> superclass = personClass.getSuperclass();
        System.out.println("    Superclass: " + (superclass != null ? superclass.getName() : "none"));
        
        Class<?>[] interfaces = personClass.getInterfaces();
        System.out.println("    Interfaces: " + Arrays.toString(
            Arrays.stream(interfaces).map(Class::getName).toArray()));
        
        // Type parameters
        TypeVariable<?>[] typeParams = personClass.getTypeParameters();
        System.out.println("    Type parameters: " + Arrays.toString(typeParams));
        
        // Annotations
        Annotation[] annotations = personClass.getAnnotations();
        System.out.println("    Annotations: " + Arrays.toString(annotations));
        
        // Class loader
        ClassLoader classLoader = personClass.getClassLoader();
        System.out.println("    Class loader: " + classLoader);
        
        // Array information
        System.out.println("    Is array: " + personClass.isArray());
        System.out.println("    Is primitive: " + personClass.isPrimitive());
        System.out.println("    Is interface: " + personClass.isInterface());
        System.out.println("    Is enum: " + personClass.isEnum());
        System.out.println("    Is annotation: " + personClass.isAnnotation());
        System.out.println();
    }
    
    // ===== FIELD ACCESS =====
    
    private static void demonstrateFieldAccess() {
        System.out.println("3. FIELD ACCESS:\n");
        
        // Q6: How do you access fields using Reflection?
        System.out.println("Q6: How do you access fields using Reflection?");
        System.out.println("    - getField(): Public field");
        System.out.println("    - getDeclaredField(): Any field");
        System.out.println("    - getFields(): All public fields");
        System.out.println("    - getDeclaredFields(): All declared fields\n");
        
        // Q7: How do you get and set field values?
        System.out.println("Q7: How do you get and set field values?");
        System.out.println("    - field.get(object): Get value");
        System.out.println("    - field.set(object, value): Set value");
        System.out.println("    - setAccessible(true): Access private fields");
        System.out.println("    - Handle IllegalAccessException\n");
        
        // Demonstrate field access
        System.out.println("Example: Field Access");
        
        try {
            Person person = new Person("John", 30);
            Class<?> personClass = person.getClass();
            
            // Get public field
            Field nameField = personClass.getField("publicField");
            System.out.println("    Public field: " + nameField.getName());
            System.out.println("    Field value: " + nameField.get(person));
            
            // Get declared field (including private)
            Field ageField = personClass.getDeclaredField("age");
            System.out.println("    Declared field: " + ageField.getName());
            System.out.println("    Field modifiers: " + Modifier.toString(ageField.getModifiers()));
            
            // Access private field
            ageField.setAccessible(true);
            System.out.println("    Private field value: " + ageField.get(person));
            
            // Set field value
            ageField.set(person, 35);
            System.out.println("    New age: " + ageField.get(person));
            
            // Get all fields
            Field[] allFields = personClass.getDeclaredFields();
            System.out.println("    All declared fields:");
            for (Field field : allFields) {
                field.setAccessible(true);
                System.out.println("      " + field.getName() + " = " + field.get(person));
            }
            
            // Get public fields
            Field[] publicFields = personClass.getFields();
            System.out.println("    Public fields:");
            for (Field field : publicFields) {
                System.out.println("      " + field.getName());
            }
            
            // Field type information
            System.out.println("    Field types:");
            for (Field field : allFields) {
                System.out.println("      " + field.getName() + ": " + field.getType().getName());
            }
            
        } catch (Exception e) {
            System.out.println("    Error accessing fields: " + e.getMessage());
        }
        System.out.println();
    }
    
    // ===== METHOD ACCESS =====
    
    private static void demonstrateMethodAccess() {
        System.out.println("4. METHOD ACCESS:\n");
        
        // Q8: How do you access methods using Reflection?
        System.out.println("Q8: How do you access methods using Reflection?");
        System.out.println("    - getMethod(): Public method with parameters");
        System.out.println("    - getDeclaredMethod(): Any method with parameters");
        System.out.println("    - getMethods(): All public methods");
        System.out.println("    - getDeclaredMethods(): All declared methods\n");
        
        // Q9: How do you invoke methods using Reflection?
        System.out.println("Q9: How do you invoke methods using Reflection?");
        System.out.println("    - method.invoke(object, parameters): Invoke method");
        System.out.println("    - Handle InvocationTargetException");
        System.out.println("    - Handle IllegalAccessException");
        System.out.println("    - Pass null for static methods\n");
        
        // Demonstrate method access
        System.out.println("Example: Method Access");
        
        try {
            Person person = new Person("Jane", 25);
            Class<?> personClass = person.getClass();
            
            // Get public method
            Method getNameMethod = personClass.getMethod("getName");
            System.out.println("    Public method: " + getNameMethod.getName());
            
            // Invoke method
            String name = (String) getNameMethod.invoke(person);
            System.out.println("    Method result: " + name);
            
            // Get method with parameters
            Method setAgeMethod = personClass.getMethod("setAge", int.class);
            System.out.println("    Method with params: " + setAgeMethod.getName());
            
            // Invoke method with parameters
            setAgeMethod.invoke(person, 30);
            System.out.println("    Age after setAge: " + person.getAge());
            
            // Get private method
            Method privateMethod = personClass.getDeclaredMethod("privateMethod");
            privateMethod.setAccessible(true);
            System.out.println("    Private method: " + privateMethod.getName());
            
            // Invoke private method
            privateMethod.invoke(person);
            
            // Get all methods
            Method[] allMethods = personClass.getDeclaredMethods();
            System.out.println("    All declared methods:");
            for (Method method : allMethods) {
                System.out.println("      " + method.getName() + "(" + 
                    Arrays.toString(method.getParameterTypes()) + ")");
            }
            
            // Get public methods (including inherited)
            Method[] publicMethods = personClass.getMethods();
            System.out.println("    Public methods (including inherited):");
            for (Method method : publicMethods) {
                System.out.println("      " + method.getName() + "(" + 
                    Arrays.toString(method.getParameterTypes()) + ")");
            }
            
            // Method information
            System.out.println("    Method details:");
            for (Method method : allMethods) {
                System.out.println("      " + method.getName() + ":");
                System.out.println("        Return type: " + method.getReturnType().getName());
                System.out.println("        Modifiers: " + Modifier.toString(method.getModifiers()));
                System.out.println("        Parameters: " + method.getParameterCount());
            }
            
        } catch (Exception e) {
            System.out.println("    Error accessing methods: " + e.getMessage());
        }
        System.out.println();
    }
    
    // ===== CONSTRUCTOR ACCESS =====
    
    private static void demonstrateConstructorAccess() {
        System.out.println("5. CONSTRUCTOR ACCESS:\n");
        
        // Q10: How do you access constructors using Reflection?
        System.out.println("Q10: How do you access constructors using Reflection?");
        System.out.println("    - getConstructor(): Public constructor with parameters");
        System.out.println("    - getDeclaredConstructor(): Any constructor with parameters");
        System.out.println("    - getConstructors(): All public constructors");
        System.out.println("    - getDeclaredConstructors(): All declared constructors\n");
        
        // Q11: How do you create objects using Reflection?
        System.out.println("Q11: How do you create objects using Reflection?");
        System.out.println("    - constructor.newInstance(parameters): Create object");
        System.out.println("    - Handle InstantiationException");
        System.out.println("    - Handle IllegalAccessException");
        System.out.println(" - Handle InvocationTargetException\n");
        
        // Demonstrate constructor access
        System.out.println("Example: Constructor Access");
        
        try {
            Class<?> personClass = Person.class;
            
            // Get default constructor
            Constructor<?> defaultConstructor = personClass.getDeclaredConstructor();
            System.out.println("    Default constructor: " + defaultConstructor.getName());
            
            // Create object using default constructor
            Person person1 = (Person) defaultConstructor.newInstance();
            System.out.println("    Person 1: " + person1.getName() + ", " + person1.getAge());
            
            // Get constructor with parameters
            Constructor<?> paramConstructor = personClass.getDeclaredConstructor(String.class, int.class);
            System.out.println("    Parameter constructor: " + paramConstructor.getName());
            
            // Create object using parameter constructor
            Person person2 = (Person) paramConstructor.newInstance("Bob", 40);
            System.out.println("    Person 2: " + person2.getName() + ", " + person2.getAge());
            
            // Get all constructors
            Constructor<?>[] allConstructors = personClass.getDeclaredConstructors();
            System.out.println("    All constructors:");
            for (Constructor<?> constructor : allConstructors) {
                System.out.println("      " + constructor.getName() + "(" + 
                    Arrays.toString(constructor.getParameterTypes()) + ")");
            }
            
            // Constructor information
            System.out.println("    Constructor details:");
            for (Constructor<?> constructor : allConstructors) {
                System.out.println("      " + constructor.getName() + ":");
                System.out.println("        Modifiers: " + Modifier.toString(constructor.getModifiers()));
                System.out.println("        Parameters: " + constructor.getParameterCount());
                System.out.println("        Exception types: " + 
                    Arrays.toString(constructor.getExceptionTypes()));
            }
            
            // Create object using Class.newInstance() (deprecated)
            try {
                Person person3 = (Person) personClass.newInstance();
                System.out.println("    Person 3 (deprecated): " + person3.getName() + ", " + person3.getAge());
            } catch (Exception e) {
                System.out.println("    newInstance() failed: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("    Error accessing constructors: " + e.getMessage());
        }
        System.out.println();
    }
    
    // ===== ADVANCED REFLECTION =====
    
    private static void demonstrateAdvancedReflection() {
        System.out.println("6. ADVANCED REFLECTION:\n");
        
        // Q12: What are advanced Reflection use cases?
        System.out.println("Q12: What are advanced Reflection use cases?");
        System.out.println("    - Dynamic proxy creation");
        System.out.println("    - Annotation processing");
        System.out.println("    - Generic type information");
        System.out.println("    - Array creation and manipulation\n");
        
        // Q13: How do you handle generics with Reflection?
        System.out.println("Q13: How do you handle generics with Reflection?");
        System.out.println("    - getGenericType() for generic types");
        System.out.println("    - ParameterizedType for generic classes");
        System.out.println("    - TypeVariable for type parameters");
        System.out.println("    - GenericArrayType for generic arrays\n");
        
        // Demonstrate advanced reflection
        System.out.println("Example: Advanced Reflection");
        
        try {
            // Generic type information
            Class<?> listClass = List.class;
            System.out.println("    List class: " + listClass.getName());
            
            // Array creation
            Class<?> stringArrayClass = String[].class;
            System.out.println("    String array class: " + stringArrayClass.getName());
            
            // Create array using reflection
            Object stringArray = Array.newInstance(String.class, 3);
            Array.set(stringArray, 0, "Hello");
            Array.set(stringArray, 1, "World");
            Array.set(stringArray, 2, "Reflection");
            
            System.out.println("    Created array: " + Arrays.toString((String[]) stringArray));
            
            // Array length
            int arrayLength = Array.getLength(stringArray);
            System.out.println("    Array length: " + arrayLength);
            
            // Dynamic proxy creation
            Class<?>[] interfaces = {Runnable.class};
            Runnable proxy = (Runnable) Proxy.newProxyInstance(
                Runnable.class.getClassLoader(),
                interfaces,
                (proxyObj, method, args) -> {
                    System.out.println("    Proxy method called: " + method.getName());
                    return null;
                }
            );
            
            proxy.run();
            
            // Annotation processing
            Class<?> personClass = Person.class;
            Annotation[] annotations = personClass.getAnnotations();
            System.out.println("    Class annotations: " + Arrays.toString(annotations));
            
            // Method annotations
            Method[] methods = personClass.getDeclaredMethods();
            for (Method method : methods) {
                Annotation[] methodAnnotations = method.getAnnotations();
                if (methodAnnotations.length > 0) {
                    System.out.println("    Method " + method.getName() + " annotations: " + 
                        Arrays.toString(methodAnnotations));
                }
            }
            
            // Field annotations
            Field[] fields = personClass.getDeclaredFields();
            for (Field field : fields) {
                Annotation[] fieldAnnotations = field.getAnnotations();
                if (fieldAnnotations.length > 0) {
                    System.out.println("    Field " + field.getName() + " annotations: " + 
                        Arrays.toString(fieldAnnotations));
                }
            }
            
            // Generic type information
            try {
                Field genericField = personClass.getDeclaredField("genericField");
                Type genericType = genericField.getGenericType();
                System.out.println("    Generic field type: " + genericType.getTypeName());
                
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType paramType = (ParameterizedType) genericType;
                    Type[] actualTypes = paramType.getActualTypeArguments();
                    System.out.println("    Actual type arguments: " + Arrays.toString(actualTypes));
                }
            } catch (NoSuchFieldException e) {
                System.out.println("    Generic field not found");
            }
            
        } catch (Exception e) {
            System.out.println("    Error in advanced reflection: " + e.getMessage());
        }
        System.out.println();
    }
    
    // ===== HELPER CLASSES =====
    
    @SuppressWarnings("unused")
    public static class Person {
        public String publicField = "Public Value";
        private String name;
        private int age;
        private List<String> genericField = new ArrayList<>();
        
        public Person() {
            this.name = "Default";
            this.age = 0;
        }
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public int getAge() {
            return age;
        }
        
        public void setAge(int age) {
            this.age = age;
        }
        
        private void privateMethod() {
            System.out.println("    Private method called");
        }
        
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA REFLECTION:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What is Reflection in Java?
 * 2. What are the main purposes of Reflection?
 * 3. How do you get Class objects?
 * 4. What is the difference between getClass() and .class?
 * 5. What is Class.forName() used for?
 * 6. How do you check if a class is public?
 * 7. How do you get the package name of a class?
 * 8. How do you get the superclass of a class?
 * 9. How do you get the interfaces implemented by a class?
 * 10. How do you check if a class is an interface?
 * 11. How do you check if a class is an enum?
 * 12. How do you check if a class is an annotation?
 * 13. How do you check if a class is an array?
 * 14. How do you check if a class is primitive?
 * 15. How do you get the class loader of a class?
 * 16. What is the difference between getName() and getSimpleName()?
 * 17. What is getCanonicalName() used for?
 * 18. How do you get type parameters of a class?
 * 19. How do you get annotations of a class?
 * 20. How do you check class modifiers?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. How do you access fields using Reflection?
 * 22. What is the difference between getField() and getDeclaredField()?
 * 23. How do you get all fields of a class?
 * 24. How do you access private fields?
 * 25. How do you get and set field values?
 * 26. How do you check field modifiers?
 * 27. How do you get field types?
 * 28. How do you handle IllegalAccessException?
 * 29. How do you access methods using Reflection?
 * 30. What is the difference between getMethod() and getDeclaredMethod()?
 * 31. How do you get all methods of a class?
 * 32. How do you invoke methods using Reflection?
 * 33. How do you handle InvocationTargetException?
 * 34. How do you get method return types?
 * 35. How do you get method parameters?
 * 36. How do you access constructors using Reflection?
 * 37. What is the difference between getConstructor() and getDeclaredConstructor()?
 * 38. How do you create objects using Reflection?
 * 39. How do you handle InstantiationException?
 * 40. How do you get constructor parameters?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you handle generics with Reflection?
 * 42. What is ParameterizedType?
 * 43. What is TypeVariable?
 * 44. What is GenericArrayType?
 * 45. How do you create arrays using Reflection?
 * 46. How do you manipulate array elements using Reflection?
 * 47. How do you create dynamic proxies using Reflection?
 * 48. How do you implement InvocationHandler?
 * 49. How do you process annotations using Reflection?
 * 50. How do you get annotation values?
 * 51. How do you check annotation presence?
 * 52. How do you handle type erasure with Reflection?
 * 53. How do you work with primitive types using Reflection?
 * 54. How do you handle array types using Reflection?
 * 55. How do you implement custom reflection utilities?
 * 56. How do you optimize reflection performance?
 * 57. How do you handle security with Reflection?
 * 58. How do you implement reflection caching?
 * 59. How do you handle reflection errors?
 * 60. How do you implement reflection testing?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you implement a custom reflection framework?
 * 62. How do you implement reflection serialization?
 * 63. How do you implement reflection persistence?
 * 64. How do you implement reflection caching strategies?
 * 65. How do you implement reflection validation frameworks?
 * 66. How do you implement reflection transformation engines?
 * 67. How do you implement reflection aggregation systems?
 * 68. How do you implement reflection composition frameworks?
 * 69. How do you implement reflection testing frameworks?
 * 70. How do you implement reflection monitoring?
 * 71. How do you implement reflection debugging?
 * 72. How do you implement reflection profiling?
 * 73. How do you implement reflection optimization?
 * 74. How do you implement reflection security?
 * 75. How do you implement reflection authentication?
 * 76. How do you implement reflection authorization?
 * 77. How do you implement reflection encryption?
 * 78. How do you implement reflection compression?
 * 79. How do you implement reflection decompression?
 * 80. How do you implement reflection integrity checking?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a reflection-based framework?
 * 82. How would you implement a reflection-based ORM?
 * 83. How would you design a reflection-based validation system?
 * 84. How would you implement a reflection-based serialization system?
 * 85. How would you design a reflection-based dependency injection system?
 * 86. How would you implement a reflection-based plugin system?
 * 87. How would you design a reflection-based testing framework?
 * 88. How would you implement a reflection-based monitoring system?
 * 89. How would you design a reflection-based security system?
 * 90. How would you implement a reflection-based logging system?
 * 91. How would you design a reflection-based configuration system?
 * 92. How would you implement a reflection-based event system?
 * 93. How would you design a reflection-based workflow system?
 * 94. How would you implement a reflection-based rule engine?
 * 95. How would you design a reflection-based parser system?
 * 96. How would you implement a reflection-based compiler?
 * 97. How would you design a reflection-based interpreter?
 * 98. How would you implement a reflection-based virtual machine?
 * 99. How would you design a reflection-based operating system?
 * 100. How would you implement a reflection-based network protocol?
 */
