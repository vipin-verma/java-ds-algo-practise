/**
 * Question 37: Java Serialization
 * 
 * This file contains Serialization interview questions covering:
 * - Serialization Basics and Implementation
 * - Custom Serialization
 * - Externalizable Interface
 * - Serialization with Inheritance
 * - Best Practices and Advanced Concepts
 */
public class Question037_JavaSerialization {
    
    public static void main(String[] args) {
        System.out.println("=== Java Serialization - Interview Questions ===\n");
        
        demonstrateSerializationBasics();
        demonstrateCustomSerialization();
        demonstrateExternalizable();
        demonstrateInheritanceSerialization();
        demonstrateBestPractices();
        
        System.out.println("\n=== Java Serialization Completed! ===");
    }
    
    private static void demonstrateSerializationBasics() {
        System.out.println("1. SERIALIZATION BASICS AND IMPLEMENTATION:\n");
        
        // Q1: What is Serialization in Java?
        System.out.println("Q1: What is Serialization in Java?");
        System.out.println("    - Process of converting object to byte stream");
        System.out.println("    - Enables object persistence and transmission");
        System.out.println("    - Object can be reconstructed from byte stream\n");
        
        // Q2: What is the purpose of Serializable interface?
        System.out.println("Q2: What is the purpose of Serializable interface?");
        System.out.println("    - Marker interface (no methods)");
        System.out.println("    - Indicates class can be serialized");
        System.out.println("    - Required for ObjectOutputStream/ObjectInputStream\n");
        
        // Demonstrate basic serialization
        System.out.println("Example: Basic Serialization");
        
        try {
            // Create a serializable object
            Person person = new Person("John Doe", 30, "john@example.com");
            System.out.println("    Original person: " + person);
            
            // Serialize object to file
            java.io.File file = new java.io.File("person.ser");
            try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
                    new java.io.FileOutputStream(file))) {
                oos.writeObject(person);
                System.out.println("    Person serialized to file");
            }
            
            // Deserialize object from file
            try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                    new java.io.FileInputStream(file))) {
                Person deserializedPerson = (Person) ois.readObject();
                System.out.println("    Deserialized person: " + deserializedPerson);
                System.out.println("    Objects equal: " + person.equals(deserializedPerson));
            }
            
            // Cleanup
            file.delete();
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateCustomSerialization() {
        System.out.println("\n2. CUSTOM SERIALIZATION:\n");
        
        // Q3: What are the methods for custom serialization?
        System.out.println("Q3: What are the methods for custom serialization?");
        System.out.println("    - writeObject(ObjectOutputStream out)");
        System.out.println("    - readObject(ObjectInputStream in)");
        System.out.println("    - Both methods must be private\n");
        
        // Q4: When should you use custom serialization?
        System.out.println("Q4: When should you use custom serialization?");
        System.out.println("    - When default serialization is insufficient");
        System.out.println("    - For security (encrypting sensitive data)");
        System.out.println("    - For performance optimization");
        System.out.println("    - When handling non-serializable fields\n");
        
        // Demonstrate custom serialization
        System.out.println("Example: Custom Serialization");
        
        try {
            // Create object with custom serialization
            SecurePerson securePerson = new SecurePerson("Jane Smith", "secret123", 25);
            System.out.println("    Original secure person: " + securePerson.getName() + 
                             ", Age: " + securePerson.getAge());
            
            // Serialize with custom logic
            java.io.File file = new java.io.File("secure_person.ser");
            try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
                    new java.io.FileOutputStream(file))) {
                oos.writeObject(securePerson);
                System.out.println("    Secure person serialized (password encrypted)");
            }
            
            // Deserialize with custom logic
            try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                    new java.io.FileInputStream(file))) {
                SecurePerson deserializedSecurePerson = (SecurePerson) ois.readObject();
                System.out.println("    Deserialized secure person: " + 
                                 deserializedSecurePerson.getName() + 
                                 ", Age: " + deserializedSecurePerson.getAge());
                System.out.println("    Password restored: " + deserializedSecurePerson.getPassword());
            }
            
            // Cleanup
            file.delete();
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateExternalizable() {
        System.out.println("\n3. EXTERNALIZABLE INTERFACE:\n");
        
        // Q5: What is the difference between Serializable and Externalizable?
        System.out.println("Q5: What is the difference between Serializable and Externalizable?");
        System.out.println("    Serializable: Automatic serialization");
        System.out.println("    Externalizable: Manual control over serialization");
        System.out.println("    Externalizable requires public no-arg constructor\n");
        
        // Q6: When should you use Externalizable?
        System.out.println("Q6: When should you use Externalizable?");
        System.out.println("    - When you need complete control over serialization");
        System.out.println("    - For performance optimization");
        System.out.println("    - When default serialization is not suitable");
        System.out.println("    - For custom data formats\n");
        
        // Demonstrate Externalizable
        System.out.println("Example: Externalizable Interface");
        
        try {
            // Create externalizable object
            ExternalPerson externalPerson = new ExternalPerson("Bob Wilson", 35, "bob@example.com");
            System.out.println("    Original external person: " + externalPerson);
            
            // Serialize using Externalizable
            java.io.File file = new java.io.File("external_person.ser");
            try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
                    new java.io.FileOutputStream(file))) {
                oos.writeObject(externalPerson);
                System.out.println("    External person serialized");
            }
            
            // Deserialize using Externalizable
            try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                    new java.io.FileInputStream(file))) {
                ExternalPerson deserializedExternalPerson = (ExternalPerson) ois.readObject();
                System.out.println("    Deserialized external person: " + deserializedExternalPerson);
            }
            
            // Cleanup
            file.delete();
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateInheritanceSerialization() {
        System.out.println("\n4. SERIALIZATION WITH INHERITANCE:\n");
        
        // Q7: How does serialization work with inheritance?
        System.out.println("Q7: How does serialization work with inheritance?");
        System.out.println("    - Parent class must be serializable");
        System.out.println("    - All non-transient fields are serialized");
        System.out.println("    - Constructor is not called during deserialization\n");
        
        // Q8: What happens if parent class is not serializable?
        System.out.println("Q8: What happens if parent class is not serializable?");
        System.out.println("    - Parent fields are not serialized");
        System.out.println("    - Parent constructor is called during deserialization");
        System.out.println("    - May cause NotSerializableException\n");
        
        // Demonstrate inheritance serialization
        System.out.println("Example: Inheritance Serialization");
        
        try {
            // Create objects with inheritance
            Student student = new Student("Alice Johnson", 20, "alice@school.edu", "Computer Science");
            System.out.println("    Original student: " + student);
            
            // Serialize student
            java.io.File file = new java.io.File("student.ser");
            try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
                    new java.io.FileOutputStream(file))) {
                oos.writeObject(student);
                System.out.println("    Student serialized");
            }
            
            // Deserialize student
            try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                    new java.io.FileInputStream(file))) {
                Student deserializedStudent = (Student) ois.readObject();
                System.out.println("    Deserialized student: " + deserializedStudent);
                System.out.println("    Objects equal: " + student.equals(deserializedStudent));
            }
            
            // Cleanup
            file.delete();
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateBestPractices() {
        System.out.println("\n5. BEST PRACTICES AND ADVANCED CONCEPTS:\n");
        
        // Q9: What are the best practices for serialization?
        System.out.println("Q9: What are the best practices for serialization?");
        System.out.println("    - Use serialVersionUID for version control");
        System.out.println("    - Mark sensitive fields as transient");
        System.out.println("    - Implement proper equals() and hashCode()");
        System.out.println("    - Handle serialization exceptions properly\n");
        
        // Q10: What is serialVersionUID and why is it important?
        System.out.println("Q10: What is serialVersionUID and why is it important?");
        System.out.println("    - Unique identifier for serializable class");
        System.out.println("    - Ensures compatibility between versions");
        System.out.println("    - Prevents InvalidClassException");
        System.out.println("    - Should be explicitly declared\n");
        
        // Demonstrate best practices
        System.out.println("Example: Best Practices");
        
        try {
            // Create object with best practices
            BestPracticePerson bestPerson = new BestPracticePerson("Charlie Brown", 28, "charlie@example.com");
            System.out.println("    Original best practice person: " + bestPerson);
            
            // Serialize with version control
            java.io.File file = new java.io.File("best_person.ser");
            try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
                    new java.io.FileOutputStream(file))) {
                oos.writeObject(bestPerson);
                System.out.println("    Best practice person serialized");
            }
            
            // Deserialize with version control
            try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
                    new java.io.FileInputStream(file))) {
                BestPracticePerson deserializedBestPerson = (BestPracticePerson) ois.readObject();
                System.out.println("    Deserialized best practice person: " + deserializedBestPerson);
            }
            
            // Demonstrate transient field
            System.out.println("    Transient field (password) is not serialized");
            System.out.println("    Original password: " + bestPerson.getPassword());
            
            // Cleanup
            file.delete();
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    // ===== HELPER CLASSES =====
    
    // Basic serializable class
    static class Person implements java.io.Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private int age;
        private String email;
        
        public Person(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }
        
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + ", email='" + email + "'}";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && 
                   java.util.Objects.equals(name, person.name) && 
                   java.util.Objects.equals(email, person.email);
        }
        
        @Override
        public int hashCode() {
            return java.util.Objects.hash(name, age, email);
        }
    }
    
    // Class with custom serialization
    static class SecurePerson implements java.io.Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private transient String password; // Won't be serialized
        private int age;
        
        public SecurePerson(String name, String password, int age) {
            this.name = name;
            this.password = password;
            this.age = age;
        }
        
        // Custom serialization
        private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
            out.defaultWriteObject(); // Serialize non-transient fields
            // Encrypt password before serialization
            String encryptedPassword = encryptPassword(password);
            out.writeObject(encryptedPassword);
        }
        
        // Custom deserialization
        private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
            in.defaultReadObject(); // Deserialize non-transient fields
            // Decrypt password after deserialization
            String encryptedPassword = (String) in.readObject();
            this.password = decryptPassword(encryptedPassword);
        }
        
        private String encryptPassword(String password) {
            // Simple encryption (for demo purposes)
            return "ENCRYPTED_" + password;
        }
        
        private String decryptPassword(String encryptedPassword) {
            // Simple decryption (for demo purposes)
            return encryptedPassword.replace("ENCRYPTED_", "");
        }
        
        public String getName() { return name; }
        public String getPassword() { return password; }
        public int getAge() { return age; }
        
        @Override
        public String toString() {
            return "SecurePerson{name='" + name + "', age=" + age + "}";
        }
    }
    
    // Class implementing Externalizable
    static class ExternalPerson implements java.io.Externalizable {
        private String name;
        private int age;
        private String email;
        
        // Required public no-arg constructor
        public ExternalPerson() {
            System.out.println("    ExternalPerson no-arg constructor called");
        }
        
        public ExternalPerson(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }
        
        @Override
        public void writeExternal(java.io.ObjectOutput out) throws java.io.IOException {
            // Custom serialization logic
            out.writeUTF(name);
            out.writeInt(age);
            out.writeUTF(email);
        }
        
        @Override
        public void readExternal(java.io.ObjectInput in) throws java.io.IOException, ClassNotFoundException {
            // Custom deserialization logic
            this.name = in.readUTF();
            this.age = in.readInt();
            this.email = in.readUTF();
        }
        
        @Override
        public String toString() {
            return "ExternalPerson{name='" + name + "', age=" + age + ", email='" + email + "'}";
        }
    }
    
    // Parent class for inheritance demonstration
    static class PersonBase implements java.io.Serializable {
        private static final long serialVersionUID = 1L;
        protected String name;
        protected int age;
        protected String email;
        
        public PersonBase(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }
        
        @Override
        public String toString() {
            return "PersonBase{name='" + name + "', age=" + age + ", email='" + email + "'}";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PersonBase that = (PersonBase) obj;
            return age == that.age && 
                   java.util.Objects.equals(name, that.name) && 
                   java.util.Objects.equals(email, that.email);
        }
        
        @Override
        public int hashCode() {
            return java.util.Objects.hash(name, age, email);
        }
    }
    
    // Child class demonstrating inheritance serialization
    static class Student extends PersonBase {
        private static final long serialVersionUID = 1L;
        private String major;
        
        public Student(String name, int age, String email, String major) {
            super(name, age, email);
            this.major = major;
        }
        
        @Override
        public String toString() {
            return "Student{" + super.toString().replace("PersonBase", "Person") + 
                   ", major='" + major + "'}";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!super.equals(obj)) return false;
            if (getClass() != obj.getClass()) return false;
            Student student = (Student) obj;
            return java.util.Objects.equals(major, student.major);
        }
        
        @Override
        public int hashCode() {
            return java.util.Objects.hash(super.hashCode(), major);
        }
    }
    
    // Class demonstrating best practices
    static class BestPracticePerson implements java.io.Serializable {
        private static final long serialVersionUID = 1L; // Explicit version control
        private final String name; // Final field
        private final int age; // Final field
        private transient String password; // Sensitive field marked transient
        
        public BestPracticePerson(String name, int age, String password) {
            this.name = name;
            this.age = age;
            this.password = password;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getPassword() { return password; }
        
        @Override
        public String toString() {
            return "BestPracticePerson{name='" + name + "', age=" + age + "}";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            BestPracticePerson that = (BestPracticePerson) obj;
            return age == that.age && java.util.Objects.equals(name, that.name);
        }
        
        @Override
        public int hashCode() {
            return java.util.Objects.hash(name, age);
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA SERIALIZATION:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What is Serialization in Java?
 * 2. What is the purpose of Serializable interface?
 * 3. How do you serialize an object in Java?
 * 4. How do you deserialize an object in Java?
 * 5. What is the difference between serialization and deserialization?
 * 6. What classes are used for serialization?
 * 7. What is ObjectOutputStream used for?
 * 8. What is ObjectInputStream used for?
 * 9. Can you serialize all objects in Java?
 * 10. What happens if you try to serialize a non-serializable object?
 * 11. What is the purpose of writeObject() method?
 * 12. What is the purpose of readObject() method?
 * 13. Can you serialize static fields?
 * 14. Can you serialize final fields?
 * 15. What is the purpose of transient keyword?
 * 16. Can you serialize private fields?
 * 17. Can you serialize protected fields?
 * 18. Can you serialize public fields?
 * 19. What is the default serialization mechanism?
 * 20. Can you serialize arrays?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. How do you implement custom serialization?
 * 22. What are the custom serialization methods?
 * 23. When should you use custom serialization?
 * 24. How do you handle non-serializable fields?
 * 25. What is the purpose of writeExternal() method?
 * 26. What is the purpose of readExternal() method?
 * 27. What is the difference between Serializable and Externalizable?
 * 28. When should you use Externalizable?
 * 29. How does serialization work with inheritance?
 * 30. What happens if parent class is not serializable?
 * 31. How do you handle serialization exceptions?
 * 32. What is NotSerializableException?
 * 33. How do you implement serialization with generics?
 * 34. How do you handle serialization with collections?
 * 35. How do you implement serialization with maps?
 * 36. How do you handle serialization with enums?
 * 37. How do you implement serialization with inner classes?
 * 38. How do you handle serialization with anonymous classes?
 * 39. How do you implement serialization with lambda expressions?
 * 40. How do you handle serialization with streams?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. What is serialVersionUID and why is it important?
 * 42. How do you handle version compatibility in serialization?
 * 43. How do you implement backward compatibility?
 * 44. How do you implement forward compatibility?
 * 45. How do you handle serialization with security?
 * 46. How do you implement encrypted serialization?
 * 47. How do you handle serialization with compression?
 * 48. How do you implement custom serialization formats?
 * 49. How do you handle serialization with databases?
 * 50. How do you implement serialization with network protocols?
 * 51. How do you handle serialization with web services?
 * 52. How do you implement serialization with REST APIs?
 * 53. How do you handle serialization with JSON?
 * 54. How do you implement serialization with XML?
 * 55. How do you handle serialization with binary protocols?
 * 56. How do you implement serialization with message queues?
 * 57. How do you handle serialization with caching systems?
 * 58. How do you implement serialization with distributed systems?
 * 59. How do you handle serialization with microservices?
 * 60. How do you implement serialization with cloud platforms?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you design a serialization framework?
 * 62. How do you implement serialization strategies?
 * 63. How do you design serialization protocols?
 * 64. How do you implement serialization formats?
 * 65. How do you design serialization security?
 * 66. How do you implement serialization encryption?
 * 67. How do you design serialization compression?
 * 68. How do you implement serialization optimization?
 * 69. How do you design serialization validation?
 * 70. How do you implement serialization verification?
 * 71. How do you design serialization monitoring?
 * 72. How do you implement serialization logging?
 * 73. How do you design serialization error handling?
 * 74. How do you implement serialization recovery?
 * 75. How do you design serialization testing?
 * 76. How do you implement serialization debugging?
 * 77. How do you design serialization profiling?
 * 78. How do you implement serialization benchmarking?
 * 79. How do you design serialization documentation?
 * 80. How do you implement serialization maintenance?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a serialization system for a large-scale application?
 * 82. How would you implement serialization in a microservices architecture?
 * 83. How would you design serialization for a distributed system?
 * 84. How would you implement serialization in a cloud-native application?
 * 85. How would you design serialization for a real-time system?
 * 86. How would you implement serialization in a high-availability system?
 * 87. How would you design serialization for a fault-tolerant system?
 * 88. How would you implement serialization in a scalable system?
 * 89. How would you design serialization for a secure system?
 * 90. How would you implement serialization in a compliant system?
 * 91. How would you design serialization for a monitored system?
 * 92. How would you implement serialization in a logged system?
 * 93. How would you design serialization for a audited system?
 * 94. How would you implement serialization in a traced system?
 * 95. How would you design serialization for a profiled system?
 * 96. How would you implement serialization in a debugged system?
 * 97. How would you design serialization for a tested system?
 * 98. How would you implement serialization in a deployed system?
 * 99. How would you design serialization for a maintained system?
 * 100. How would you implement serialization in a evolved system?
 */
