/**
 * Question 41: Java Memory Management
 * 
 * This file contains Memory Management interview questions covering:
 * - JVM Memory Structure
 * - Garbage Collection
 * - Memory Leaks and Prevention
 * - Performance Tuning
 * - Best Practices
 */
public class Question041_JavaMemoryManagement {
    
    public static void main(String[] args) {
        System.out.println("=== Java Memory Management - Interview Questions ===\n");
        
        demonstrateMemoryStructure();
        demonstrateGarbageCollection();
        demonstrateMemoryLeaks();
        demonstratePerformanceTuning();
        
        System.out.println("\n=== Java Memory Management Completed! ===");
    }
    
    private static void demonstrateMemoryStructure() {
        System.out.println("1. JVM MEMORY STRUCTURE:\n");
        
        // Q1: What are the main memory areas in JVM?
        System.out.println("Q1: What are the main memory areas in JVM?");
        System.out.println("    - Heap: Object storage");
        System.out.println("    - Stack: Method calls and local variables");
        System.out.println("    - Method Area: Class metadata");
        System.out.println("    - Native Method Stack: Native method calls\n");
        
        // Q2: What is the difference between Heap and Stack?
        System.out.println("Q2: What is the difference between Heap and Stack?");
        System.out.println("    Heap: Dynamic memory allocation, shared across threads");
        System.out.println("    Stack: LIFO structure, thread-specific, faster access\n");
        
        // Demonstrate memory usage
        System.out.println("Example: Memory Structure Demonstration");
        
        // Show heap memory usage
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        System.out.println("    Current Memory Usage:");
        System.out.println("      Total Memory: " + (totalMemory / 1024 / 1024) + " MB");
        System.out.println("      Free Memory: " + (freeMemory / 1024 / 1024) + " MB");
        System.out.println("      Used Memory: " + (usedMemory / 1024 / 1024) + " MB");
        
        // Demonstrate stack memory with recursion
        System.out.println("    Stack Memory Example (Recursion):");
        int factorial = calculateFactorial(5);
        System.out.println("      Factorial of 5: " + factorial);
        
        // Demonstrate heap memory with object creation
        System.out.println("    Heap Memory Example (Object Creation):");
        createLargeObjects();
        
        // Show memory after object creation
        long newFreeMemory = runtime.freeMemory();
        long newUsedMemory = totalMemory - newFreeMemory;
        System.out.println("      Memory after object creation: " + (newUsedMemory / 1024 / 1024) + " MB");
    }
    
    private static void demonstrateGarbageCollection() {
        System.out.println("\n2. GARBAGE COLLECTION:\n");
        
        // Q3: What is Garbage Collection in Java?
        System.out.println("Q3: What is Garbage Collection in Java?");
        System.out.println("    Automatic memory management that reclaims unused objects");
        System.out.println("    Runs in background thread to free heap memory\n");
        
        // Q4: What are the different types of Garbage Collectors?
        System.out.println("Q4: What are the different types of Garbage Collectors?");
        System.out.println("    - Serial GC: Single-threaded, good for small applications");
        System.out.println("    - Parallel GC: Multi-threaded, good for throughput");
        System.out.println("    - CMS GC: Concurrent, low pause times");
        System.out.println("    - G1 GC: Low latency, good for large heaps\n");
        
        // Demonstrate garbage collection
        System.out.println("Example: Garbage Collection Demonstration");
        
        // Force garbage collection
        System.out.println("    Before GC:");
        printMemoryInfo();
        
        System.out.println("    Running Garbage Collection...");
        System.gc();
        
        System.out.println("    After GC:");
        printMemoryInfo();
        
        // Demonstrate object lifecycle
        System.out.println("    Object Lifecycle Example:");
        WeakReference<Object> weakRef = new WeakReference<>(new Object());
        System.out.println("      Weak reference created: " + (weakRef.get() != null));
        
        System.gc();
        System.out.println("      After GC: " + (weakRef.get() != null));
    }
    
    private static void demonstrateMemoryLeaks() {
        System.out.println("\n3. MEMORY LEAKS AND PREVENTION:\n");
        
        // Q5: What are common causes of Memory Leaks?
        System.out.println("Q5: What are common causes of Memory Leaks?");
        System.out.println("    - Static collections holding objects");
        System.out.println("    - Unclosed resources (files, connections)");
        System.out.println("    - Listener references not removed");
        System.out.println("    - ThreadLocal variables not cleaned up\n");
        
        // Q6: How do you prevent Memory Leaks?
        System.out.println("Q6: How do you prevent Memory Leaks?");
        System.out.println("    - Use try-with-resources for auto-closing");
        System.out.println("    - Remove listeners when done");
        System.out.println("    - Clear collections when no longer needed");
        System.out.println("    - Use WeakReference for caches\n");
        
        // Demonstrate memory leak prevention
        System.out.println("Example: Memory Leak Prevention");
        
        // Good practice: try-with-resources
        try (AutoCloseableResource resource = new AutoCloseableResource()) {
            System.out.println("    Using resource: " + resource.getName());
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
        
        // Good practice: weak references for caches
        Map<String, WeakReference<Object>> cache = new HashMap<>();
        cache.put("key1", new WeakReference<>(new Object()));
        cache.put("key2", new WeakReference<>(new Object()));
        
        System.out.println("    Cache size before GC: " + cache.size());
        System.out.println("    Valid references before GC: " + countValidReferences(cache));
        
        System.gc();
        
        System.out.println("    Cache size after GC: " + cache.size());
        System.out.println("    Valid references after GC: " + countValidReferences(cache));
        
        // Clean up cache
        cache.clear();
        System.out.println("    Cache cleared");
    }
    
    private static void demonstratePerformanceTuning() {
        System.out.println("\n4. PERFORMANCE TUNING:\n");
        
        // Q7: How do you tune JVM memory settings?
        System.out.println("Q7: How do you tune JVM memory settings?");
        System.out.println("    -Xms: Initial heap size");
        System.out.println("    -Xmx: Maximum heap size");
        System.out.println("    -XX:NewRatio: Young/Old generation ratio");
        System.out.println("    -XX:SurvivorRatio: Eden/Survivor ratio\n");
        
        // Q8: What are memory-related JVM flags?
        System.out.println("Q8: What are memory-related JVM flags?");
        System.out.println("    -XX:+PrintGCDetails: Print GC details");
        System.out.println("    -XX:+PrintGCTimeStamps: Print GC timestamps");
        System.out.println("    -XX:+UseG1GC: Use G1 garbage collector");
        System.out.println("    -XX:MaxGCPauseMillis: Target max GC pause time\n");
        
        // Demonstrate performance monitoring
        System.out.println("Example: Performance Monitoring");
        
        // Monitor memory usage over time
        System.out.println("    Memory Usage Monitoring:");
        for (int i = 0; i < 3; i++) {
            printMemoryInfo();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Demonstrate object pooling
        System.out.println("    Object Pooling Example:");
        ObjectPool<String> stringPool = new ObjectPool<>(5);
        
        String obj1 = stringPool.acquire();
        String obj2 = stringPool.acquire();
        
        System.out.println("      Acquired objects: " + obj1 + ", " + obj2);
        System.out.println("      Pool size: " + stringPool.getPoolSize());
        
        stringPool.release(obj1);
        stringPool.release(obj2);
        
        System.out.println("      After release, pool size: " + stringPool.getPoolSize());
    }
    
    // ===== HELPER METHODS =====
    
    private static int calculateFactorial(int n) {
        if (n <= 1) return 1;
        return n * calculateFactorial(n - 1);
    }
    
    private static void createLargeObjects() {
        // Create some objects to demonstrate heap usage
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("Object " + i);
        }
        System.out.println("      Created " + list.size() + " objects");
    }
    
    private static void printMemoryInfo() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();
        
        System.out.println("        Total: " + (totalMemory / 1024 / 1024) + " MB");
        System.out.println("        Used: " + (usedMemory / 1024 / 1024) + " MB");
        System.out.println("        Free: " + (freeMemory / 1024 / 1024) + " MB");
        System.out.println("        Max: " + (maxMemory / 1024 / 1024) + " MB");
    }
    
    private static int countValidReferences(Map<String, WeakReference<Object>> cache) {
        return (int) cache.values().stream()
                .mapToInt(ref -> ref.get() != null ? 1 : 0)
                .sum();
    }
    
    // ===== HELPER CLASSES =====
    
    static class AutoCloseableResource implements AutoCloseable {
        private final String name;
        
        public AutoCloseableResource() {
            this.name = "Resource-" + System.currentTimeMillis();
        }
        
        public String getName() {
            return name;
        }
        
        @Override
        public void close() throws Exception {
            System.out.println("      Closing resource: " + name);
        }
    }
    
    static class ObjectPool<T> {
        private final Queue<T> pool;
        private final int maxSize;
        
        public ObjectPool(int maxSize) {
            this.maxSize = maxSize;
            this.pool = new LinkedList<>();
            
            // Initialize pool with objects
            for (int i = 0; i < maxSize; i++) {
                pool.offer((T) ("PooledObject-" + i));
            }
        }
        
        public T acquire() {
            T obj = pool.poll();
            return obj != null ? obj : (T) "NewObject";
        }
        
        public void release(T obj) {
            if (pool.size() < maxSize) {
                pool.offer(obj);
            }
        }
        
        public int getPoolSize() {
            return pool.size();
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA MEMORY MANAGEMENT:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What is JVM Memory Management?
 * 2. What are the main memory areas in JVM?
 * 3. What is the difference between Heap and Stack?
 * 4. What is the Method Area in JVM?
 * 5. What is the Native Method Stack?
 * 6. What is Garbage Collection?
 * 7. How does Garbage Collection work?
 * 8. What is the difference between Young and Old Generation?
 * 9. What is Eden Space?
 * 10. What are Survivor Spaces?
 * 11. What is the difference between Minor and Major GC?
 * 12. What is Full GC?
 * 13. What are memory leaks?
 * 14. How do you identify memory leaks?
 * 15. What is OutOfMemoryError?
 * 16. What causes OutOfMemoryError?
 * 17. What is StackOverflowError?
 * 18. What causes StackOverflowError?
 * 19. What is memory fragmentation?
 * 20. How do you prevent memory issues?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. What are the different Garbage Collectors in Java?
 * 22. What is Serial Garbage Collector?
 * 23. What is Parallel Garbage Collector?
 * 24. What is CMS Garbage Collector?
 * 25. What is G1 Garbage Collector?
 * 26. How do you choose a Garbage Collector?
 * 27. What are JVM memory flags?
 * 28. What does -Xms flag do?
 * 29. What does -Xmx flag do?
 * 30. What does -XX:NewRatio do?
 * 31. What does -XX:SurvivorRatio do?
 * 32. What are Weak References?
 * 33. What are Soft References?
 * 34. What are Phantom References?
 * 35. How do you use WeakHashMap?
 * 36. What is object finalization?
 * 37. Why is finalization deprecated?
 * 38. How do you implement object pooling?
 * 39. What are memory-efficient collections?
 * 40. How do you monitor memory usage?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you tune JVM memory settings?
 * 42. How do you analyze GC logs?
 * 43. How do you use JVM profiling tools?
 * 44. How do you implement custom memory management?
 * 45. How do you handle large object allocations?
 * 46. How do you optimize string memory usage?
 * 47. How do you implement memory-efficient caching?
 * 48. How do you handle memory pressure?
 * 49. How do you implement memory leak detection?
 * 50. How do you optimize collection memory usage?
 * 51. How do you handle off-heap memory?
 * 52. How do you implement memory pools?
 * 53. How do you optimize array memory usage?
 * 54. How do you handle memory-mapped files?
 * 55. How do you implement memory-efficient serialization?
 * 56. How do you optimize thread memory usage?
 * 57. How do you handle memory in concurrent applications?
 * 58. How do you implement memory-efficient algorithms?
 * 59. How do you optimize reflection memory usage?
 * 60. How do you handle memory in microservices?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you design memory-efficient architectures?
 * 62. How do you implement custom garbage collection?
 * 63. How do you optimize memory for high-frequency trading?
 * 64. How do you handle memory in real-time systems?
 * 65. How do you implement memory-efficient databases?
 * 66. How do you optimize memory for big data processing?
 * 67. How do you handle memory in distributed systems?
 * 68. How do you implement memory-efficient messaging?
 * 69. How do you optimize memory for machine learning?
 * 70. How do you handle memory in cloud environments?
 * 71. How do you implement memory-efficient APIs?
 * 72. How do you optimize memory for mobile applications?
 * 73. How do you handle memory in embedded systems?
 * 74. How do you implement memory-efficient networking?
 * 75. How do you optimize memory for gaming applications?
 * 76. How do you handle memory in IoT devices?
 * 77. How do you implement memory-efficient security?
 * 78. How do you optimize memory for blockchain?
 * 79. How do you handle memory in quantum computing?
 * 80. How do you implement memory-efficient AI systems?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a memory-efficient microservices architecture?
 * 82. How would you implement memory management for a distributed cache?
 * 83. How would you design memory-efficient data processing pipelines?
 * 84. How would you implement memory management for a real-time streaming system?
 * 85. How would you design memory-efficient API gateways?
 * 86. How would you implement memory management for a message queue system?
 * 87. How would you design memory-efficient database systems?
 * 88. How would you implement memory management for a search engine?
 * 89. How would you design memory-efficient recommendation systems?
 * 90. How would you implement memory management for a content delivery network?
 * 91. How would you design memory-efficient analytics platforms?
 * 92. How would you implement memory management for a machine learning pipeline?
 * 93. How would you design memory-efficient blockchain networks?
 * 94. How would you implement memory management for a gaming server?
 * 95. How would you design memory-efficient IoT platforms?
 * 96. How would you implement memory management for a social media platform?
 * 97. How would you design memory-efficient e-commerce systems?
 * 98. How would you implement memory management for a financial trading system?
 * 99. How would you design memory-efficient healthcare systems?
 * 100. How would you implement memory management for an autonomous vehicle system?
 */
