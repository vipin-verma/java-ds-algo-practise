/**
 * Question 33: Java Inner Classes
 * 
 * This file contains Inner Classes interview questions covering:
 * - Inner Class Types and Declaration
 * - Local and Anonymous Classes
 * - Static Nested Classes
 * - Best Practices and Use Cases
 */
public class Question033_JavaInnerClasses {
    
    public static void main(String[] args) {
        System.out.println("=== Java Inner Classes - Interview Questions ===\n");
        
        demonstrateInnerClassBasics();
        demonstrateStaticNestedClasses();
        demonstrateLocalClasses();
        demonstrateAnonymousClasses();
        
        System.out.println("\n=== Java Inner Classes Completed! ===");
    }
    
    private static void demonstrateInnerClassBasics() {
        System.out.println("1. INNER CLASS BASICS:\n");
        
        // Q1: What are Inner Classes in Java?
        System.out.println("Q1: What are Inner Classes in Java?");
        System.out.println("    Inner classes are classes defined inside another class.");
        System.out.println("    They can access private members of the outer class.");
        System.out.println("    They help in logical grouping and encapsulation.\n");
        
        // Q2: What are the types of Inner Classes?
        System.out.println("Q2: What are the types of Inner Classes?");
        System.out.println("    - Non-static inner classes (regular inner classes)");
        System.out.println("    - Static nested classes");
        System.out.println("    - Local inner classes");
        System.out.println("    - Anonymous inner classes\n");
        
        // Demonstrate inner class basics
        System.out.println("Example: Inner Class Basics");
        
        OuterClass outer = new OuterClass("Outer Value");
        OuterClass.InnerClass inner = outer.new InnerClass("Inner Value");
        
        System.out.println("    Outer value: " + outer.getOuterValue());
        System.out.println("    Inner value: " + inner.getInnerValue());
        System.out.println("    Inner accessing outer: " + inner.accessOuterValue());
        
        // Inner class with private access
        OuterClass.PrivateInnerClass privateInner = outer.new PrivateInnerClass();
        System.out.println("    Private inner accessing outer: " + privateInner.accessOuter());
    }
    
    private static void demonstrateStaticNestedClasses() {
        System.out.println("\n2. STATIC NESTED CLASSES:\n");
        
        // Q3: What are Static Nested Classes?
        System.out.println("Q3: What are Static Nested Classes?");
        System.out.println("    Static nested classes are static members of the outer class.");
        System.out.println("    They don't have access to non-static members of outer class.");
        System.out.println("    They can be instantiated without outer class instance.\n");
        
        // Demonstrate static nested classes
        System.out.println("Example: Static Nested Classes");
        
        // Create static nested class without outer class instance
        OuterClass.StaticNestedClass staticNested = new OuterClass.StaticNestedClass("Static Nested");
        System.out.println("    Static nested value: " + staticNested.getValue());
        
        // Static nested class with static methods
        OuterClass.StaticNestedClass.Utility utility = new OuterClass.StaticNestedClass.Utility();
        System.out.println("    Utility method result: " + utility.process("Test"));
        
        // Static nested class implementing interface
        OuterClass.StaticNestedClass.ComparableItem item1 = new OuterClass.StaticNestedClass.ComparableItem(5);
        OuterClass.StaticNestedClass.ComparableItem item2 = new OuterClass.StaticNestedClass.ComparableItem(3);
        System.out.println("    Comparison result: " + item1.compareTo(item2));
    }
    
    private static void demonstrateLocalClasses() {
        System.out.println("\n3. LOCAL CLASSES:\n");
        
        // Q4: What are Local Classes?
        System.out.println("Q4: What are Local Classes?");
        System.out.println("    Local classes are classes defined inside a method.");
        System.out.println("    They can access final or effectively final local variables.");
        System.out.println("    They have access to the outer class members.\n");
        
        // Demonstrate local classes
        System.out.println("Example: Local Classes");
        
        LocalClassDemo demo = new LocalClassDemo();
        demo.methodWithLocalClass();
        
        // Local class with parameters
        demo.methodWithParameterizedLocalClass("Parameter Value");
        
        // Local class implementing interface
        demo.methodWithInterfaceLocalClass();
    }
    
    private static void demonstrateAnonymousClasses() {
        System.out.println("\n4. ANONYMOUS CLASSES:\n");
        
        // Q5: What are Anonymous Classes?
        System.out.println("Q5: What are Anonymous Classes?");
        System.out.println("    Anonymous classes are classes without a name.");
        System.out.println("    They are defined and instantiated at the same time.");
        System.out.println("    They are useful for one-time implementations.\n");
        
        // Demonstrate anonymous classes
        System.out.println("Example: Anonymous Classes");
        
        AnonymousClassDemo demo = new AnonymousClassDemo();
        
        // Anonymous class extending a class
        demo.createAnonymousExtendingClass();
        
        // Anonymous class implementing an interface
        demo.createAnonymousImplementingInterface();
        
        // Anonymous class as method parameter
        demo.methodWithAnonymousParameter();
        
        // Anonymous class with multiple interfaces
        demo.createAnonymousWithMultipleInterfaces();
    }
    
    // ===== HELPER CLASSES =====
    
    // Outer class with inner classes
    static class OuterClass {
        private String outerValue;
        private static String staticOuterValue = "Static Outer Value";
        
        public OuterClass(String outerValue) {
            this.outerValue = outerValue;
        }
        
        public String getOuterValue() {
            return outerValue;
        }
        
        // Regular inner class
        class InnerClass {
            private String innerValue;
            
            public InnerClass(String innerValue) {
                this.innerValue = innerValue;
            }
            
            public String getInnerValue() {
                return innerValue;
            }
            
            public String accessOuterValue() {
                return "Inner accessing: " + outerValue; // Can access outer class members
            }
        }
        
        // Private inner class
        private class PrivateInnerClass {
            public String accessOuter() {
                return "Private inner accessing: " + outerValue;
            }
        }
        
        // Static nested class
        static class StaticNestedClass {
            private String value;
            
            public StaticNestedClass(String value) {
                this.value = value;
            }
            
            public String getValue() {
                return value;
            }
            
            // Static nested class can access static members of outer class
            public String accessStaticOuter() {
                return "Accessing static outer: " + staticOuterValue;
            }
            
            // Inner class within static nested class
            class InnerOfStatic {
                public String getInfo() {
                    return "Inner of static: " + value + ", " + accessStaticOuter();
                }
            }
            
            // Static utility class
            static class Utility {
                public String process(String input) {
                    return "Processed: " + input.toUpperCase();
                }
            }
            
            // Static nested class implementing interface
            static class ComparableItem implements Comparable<ComparableItem> {
                private int value;
                
                public ComparableItem(int value) {
                    this.value = value;
                }
                
                @Override
                public int compareTo(ComparableItem other) {
                    return Integer.compare(this.value, other.value);
                }
                
                @Override
                public String toString() {
                    return "ComparableItem{" + value + "}";
                }
            }
        }
    }
    
    // Demo class for local classes
    static class LocalClassDemo {
        private String outerField = "Outer Field";
        
        public void methodWithLocalClass() {
            final String localVariable = "Local Variable";
            
            // Local class definition
            class LocalClass {
                private String localClassField = "Local Class Field";
                
                public void display() {
                    System.out.println("    Local class accessing:");
                    System.out.println("      Local variable: " + localVariable);
                    System.out.println("      Outer field: " + outerField);
                    System.out.println("      Local class field: " + localClassField);
                }
            }
            
            LocalClass local = new LocalClass();
            local.display();
        }
        
        public void methodWithParameterizedLocalClass(String parameter) {
            // Local class with parameter access
            class ParameterizedLocalClass {
                public void display() {
                    System.out.println("    Parameterized local class:");
                    System.out.println("      Parameter: " + parameter);
                    System.out.println("      Outer field: " + outerField);
                }
            }
            
            ParameterizedLocalClass local = new ParameterizedLocalClass();
            local.display();
        }
        
        public void methodWithInterfaceLocalClass() {
            // Local class implementing interface
            class InterfaceLocalClass implements Runnable {
                @Override
                public void run() {
                    System.out.println("    Local class implementing Runnable:");
                    System.out.println("      Outer field: " + outerField);
                }
            }
            
            InterfaceLocalClass local = new InterfaceLocalClass();
            local.run();
        }
    }
    
    // Demo class for anonymous classes
    static class AnonymousClassDemo {
        private String demoField = "Demo Field";
        
        public void createAnonymousExtendingClass() {
            // Anonymous class extending a class
            Object anonymousObject = new Object() {
                private String anonymousField = "Anonymous Field";
                
                @Override
                public String toString() {
                    return "Anonymous extending Object: " + anonymousField + ", " + demoField;
                }
            };
            
            System.out.println("    Anonymous extending class: " + anonymousObject);
        }
        
        public void createAnonymousImplementingInterface() {
            // Anonymous class implementing an interface
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("    Anonymous Runnable executing:");
                    System.out.println("      Demo field: " + demoField);
                }
            };
            
            anonymousRunnable.run();
        }
        
        public void methodWithAnonymousParameter() {
            // Anonymous class as method parameter
            processAnonymous(new Runnable() {
                @Override
                public void run() {
                    System.out.println("    Anonymous parameter executing:");
                    System.out.println("      Demo field: " + demoField);
                }
            });
        }
        
        private void processAnonymous(Runnable runnable) {
            System.out.println("    Processing anonymous parameter...");
            runnable.run();
        }
        
        public void createAnonymousWithMultipleInterfaces() {
            // Anonymous class implementing multiple interfaces
            Object multiInterface = new Object() implements Runnable, Comparable<Object> {
                @Override
                public void run() {
                    System.out.println("    Multi-interface anonymous running");
                }
                
                @Override
                public int compareTo(Object other) {
                    return 0;
                }
                
                @Override
                public String toString() {
                    return "Multi-interface anonymous object";
                }
            };
            
            System.out.println("    Multi-interface: " + multiInterface);
            if (multiInterface instanceof Runnable) {
                ((Runnable) multiInterface).run();
            }
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA INNER CLASSES:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are Inner Classes in Java?
 * 2. What are the types of Inner Classes?
 * 3. What is the difference between inner and nested classes?
 * 4. How do you create an instance of an inner class?
 * 5. Can inner classes access private members of outer class?
 * 6. Can outer class access private members of inner class?
 * 7. What is the this reference in inner classes?
 * 8. How do you access outer class from inner class?
 * 9. Can inner classes be static?
 * 10. Can inner classes be abstract?
 * 11. Can inner classes be final?
 * 12. Can inner classes have static members?
 * 13. Can inner classes be inherited?
 * 14. Can inner classes implement interfaces?
 * 15. Can inner classes extend other classes?
 * 16. What is the scope of inner classes?
 * 17. Can inner classes be declared in interfaces?
 * 18. Can inner classes be declared in methods?
 * 19. Can inner classes be declared in static methods?
 * 20. Can inner classes be declared in constructors?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. What are Static Nested Classes?
 * 22. How do you create an instance of static nested class?
 * 23. Can static nested classes access non-static outer members?
 * 24. Can static nested classes have static members?
 * 25. What is the difference between inner and static nested classes?
 * 26. When should you use static nested classes?
 * 27. When should you use inner classes?
 * 28. How do you access outer class from static nested class?
 * 29. Can static nested classes be inherited?
 * 30. Can static nested classes implement interfaces?
 * 31. What are Local Classes?
 * 32. Where can local classes be declared?
 * 33. Can local classes access local variables?
 * 34. What are effectively final variables?
 * 35. Can local classes have static members?
 * 36. Can local classes be inherited?
 * 37. Can local classes implement interfaces?
 * 38. What is the scope of local classes?
 * 39. Can local classes be declared in static methods?
 * 40. Can local classes access method parameters?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. What are Anonymous Classes?
 * 42. How do you create anonymous classes?
 * 43. Can anonymous classes have constructors?
 * 44. Can anonymous classes have static members?
 * 45. Can anonymous classes implement multiple interfaces?
 * 46. Can anonymous classes extend classes and implement interfaces?
 * 47. What is the scope of anonymous classes?
 * 48. Can anonymous classes access local variables?
 * 49. How do you use anonymous classes with collections?
 * 50. How do you use anonymous classes with event handling?
 * 51. How do you use anonymous classes with callbacks?
 * 52. How do you use anonymous classes with functional interfaces?
 * 53. How do you use anonymous classes with streams?
 * 54. How do you use anonymous classes with threads?
 * 55. How do you use anonymous classes with executors?
 * 56. How do you use anonymous classes with comparators?
 * 57. How do you use anonymous classes with filters?
 * 58. How do you use anonymous classes with mappers?
 * 59. How do you use anonymous classes with reducers?
 * 60. How do you use anonymous classes with collectors?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you implement design patterns with inner classes?
 * 62. How do you implement callbacks with inner classes?
 * 63. How do you implement event handling with inner classes?
 * 64. How do you implement observers with inner classes?
 * 65. How do you implement decorators with inner classes?
 * 66. How do you implement adapters with inner classes?
 * 67. How do you implement factories with inner classes?
 * 68. How do you implement builders with inner classes?
 * 69. How do you implement singletons with inner classes?
 * 70. How do you implement proxies with inner classes?
 * 71. How do you implement chains with inner classes?
 * 72. How do you implement commands with inner classes?
 * 73. How do you implement strategies with inner classes?
 * 74. How do you implement states with inner classes?
 * 75. How do you implement visitors with inner classes?
 * 76. How do you implement iterators with inner classes?
 * 77. How do you implement comparators with inner classes?
 * 78. How do you implement filters with inner classes?
 * 79. How do you implement mappers with inner classes?
 * 80. How do you implement reducers with inner classes?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a framework using inner classes?
 * 82. How would you implement a plugin system using inner classes?
 * 83. How would you design an event system using inner classes?
 * 84. How would you implement a callback system using inner classes?
 * 85. How would you design an observer system using inner classes?
 * 86. How would you implement a decorator system using inner classes?
 * 87. How would you design an adapter system using inner classes?
 * 88. How would you implement a factory system using inner classes?
 * 89. How would you design a builder system using inner classes?
 * 90. How would you implement a singleton system using inner classes?
 * 91. How would you design a proxy system using inner classes?
 * 92. How would you implement a chain system using inner classes?
 * 93. How would you design a command system using inner classes?
 * 94. How would you implement a strategy system using inner classes?
 * 95. How would you design a state system using inner classes?
 * 96. How would you implement a visitor system using inner classes?
 * 97. How would you design an iterator system using inner classes?
 * 98. How would you implement a comparator system using inner classes?
 * 99. How would you design a filter system using inner classes?
 * 100. How would you implement a mapper system using inner classes?
 */
