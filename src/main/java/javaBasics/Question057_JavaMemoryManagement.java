/**
 * Question057: Java Memory Management
 * 
 * This file contains interview questions and demonstration code for Java Memory Management.
 * It covers various memory management concepts, garbage collection, memory leaks, and optimization.
 * 
 * Topics covered:
 * - Garbage Collection (GC)
 * - Memory Leaks Detection
 * - Memory Optimization
 * - JVM Memory Tuning
 * - Memory Monitoring
 * - Reference Types
 * - Memory Pools
 * - GC Algorithms
 * - Memory Profiling
 * - Performance Tuning
 */

import java.lang.ref.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ThreadMXBean;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

public class Question057_JavaMemoryManagement {
    
    public static void main(String[] args) {
        System.out.println("=== Java Memory Management Demo ===\n");
        
        // Demo different memory management concepts
        demoGarbageCollection();
        demoMemoryLeaks();
        demoMemoryOptimization();
        demoJVMMemoryTuning();
        demoMemoryMonitoring();
        demoReferenceTypes();
        demoMemoryPools();
        demoGCAlgorithms();
        demoMemoryProfiling();
        demoPerformanceTuning();
        
        System.out.println("\n=== All Memory Management Demo Completed ===");
    }
    
    // ===== GARBAGE COLLECTION DEMO =====
    
    /**
     * Demonstrates different garbage collection scenarios
     */
    static void demoGarbageCollection() {
        System.out.println("--- Garbage Collection Demo ---");
        
        // Force garbage collection
        System.out.println("Before GC - Free memory: " + getFreeMemory() + " MB");
        
        // Create objects to consume memory
        List<String> objects = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            objects.add("Object-" + i + "-" + UUID.randomUUID().toString());
        }
        
        System.out.println("After creating objects - Free memory: " + getFreeMemory() + " MB");
        
        // Clear references
        objects.clear();
        objects = null;
        
        // Suggest garbage collection
        System.gc();
        
        try {
            Thread.sleep(1000); // Give GC time to run
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("After GC - Free memory: " + getFreeMemory() + " MB");
    }
    
    // ===== MEMORY LEAKS DEMO =====
    
    /**
     * Demonstrates common memory leak scenarios
     */
    static void demoMemoryLeaks() {
        System.out.println("\n--- Memory Leaks Demo ---");
        
        // Static collection memory leak
        MemoryLeakDemo.staticMap.put("key-" + System.currentTimeMillis(), 
            new byte[1024 * 1024]); // 1MB
        
        System.out.println("Static map size: " + MemoryLeakDemo.staticMap.size());
        System.out.println("Memory used by static map: " + 
            (MemoryLeakDemo.staticMap.size() * 1024 * 1024 / (1024 * 1024)) + " MB");
        
        // ThreadLocal memory leak
        MemoryLeakDemo.threadLocal.set(new byte[1024 * 1024]); // 1MB
        
        // Event listener memory leak
        MemoryLeakDemo.addEventListener();
        
        System.out.println("Event listeners added: " + MemoryLeakDemo.eventListeners.size());
    }
    
    // ===== MEMORY OPTIMIZATION DEMO =====
    
    /**
     * Demonstrates memory optimization techniques
     */
    static void demoMemoryOptimization() {
        System.out.println("\n--- Memory Optimization Demo ---");
        
        // Object pooling
        ObjectPool<String> stringPool = new ObjectPool<>(100);
        
        String obj1 = stringPool.acquire();
        String obj2 = stringPool.acquire();
        
        System.out.println("Objects acquired from pool: " + stringPool.getActiveCount());
        
        stringPool.release(obj1);
        stringPool.release(obj2);
        
        System.out.println("Objects returned to pool: " + stringPool.getIdleCount());
        
        // Memory-efficient collections
        MemoryEfficientCollections demo = new MemoryEfficientCollections();
        demo.demonstrate();
    }
    
    // ===== JVM MEMORY TUNING DEMO =====
    
    /**
     * Demonstrates JVM memory tuning parameters
     */
    static void demoJVMMemoryTuning() {
        System.out.println("\n--- JVM Memory Tuning Demo ---");
        
        // Get JVM memory information
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapUsage = memoryBean.getNonHeapMemoryUsage();
        
        System.out.println("Heap Memory:");
        System.out.println("  Initial: " + (heapUsage.getInit() / (1024 * 1024)) + " MB");
        System.out.println("  Used: " + (heapUsage.getUsed() / (1024 * 1024)) + " MB");
        System.out.println("  Committed: " + (heapUsage.getCommitted() / (1024 * 1024)) + " MB");
        System.out.println("  Max: " + (heapUsage.getMax() / (1024 * 1024)) + " MB");
        
        System.out.println("Non-Heap Memory:");
        System.out.println("  Initial: " + (nonHeapUsage.getInit() / (1024 * 1024)) + " MB");
        System.out.println("  Used: " + (nonHeapUsage.getUsed() / (1024 * 1024)) + " MB");
        System.out.println("  Committed: " + (nonHeapUsage.getCommitted() / (1024 * 1024)) + " MB");
        System.out.println("  Max: " + (nonHeapUsage.getMax() / (1024 * 1024)) + " MB");
    }
    
    // ===== MEMORY MONITORING DEMO =====
    
    /**
     * Demonstrates memory monitoring capabilities
     */
    static void demoMemoryMonitoring() {
        System.out.println("\n--- Memory Monitoring Demo ---");
        
        MemoryMonitor monitor = new MemoryMonitor();
        monitor.startMonitoring();
        
        // Simulate memory usage
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        monitor.stopMonitoring();
        monitor.printReport();
    }
    
    // ===== REFERENCE TYPES DEMO =====
    
    /**
     * Demonstrates different reference types
     */
    static void demoReferenceTypes() {
        System.out.println("\n--- Reference Types Demo ---");
        
        // Strong reference
        Object strongRef = new Object();
        
        // Weak reference
        WeakReference<Object> weakRef = new WeakReference<>(new Object());
        
        // Soft reference
        SoftReference<Object> softRef = new SoftReference<>(new Object());
        
        // Phantom reference
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantomRef = new PhantomReference<>(new Object(), queue);
        
        System.out.println("Strong reference: " + (strongRef != null));
        System.out.println("Weak reference: " + (weakRef.get() != null));
        System.out.println("Soft reference: " + (softRef.get() != null));
        System.out.println("Phantom reference: " + (phantomRef.get() != null));
        
        // Force GC to see weak reference behavior
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("After GC - Weak reference: " + (weakRef.get() != null));
    }
    
    // ===== MEMORY POOLS DEMO =====
    
    /**
     * Demonstrates different memory pools
     */
    static void demoMemoryPools() {
        System.out.println("\n--- Memory Pools Demo ---");
        
        // Direct memory allocation
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024 * 1024); // 1MB
        
        // Heap memory allocation
        byte[] heapArray = new byte[1024 * 1024]; // 1MB
        
        System.out.println("Direct buffer allocated: " + directBuffer.isDirect());
        System.out.println("Direct buffer capacity: " + (directBuffer.capacity() / (1024 * 1024)) + " MB");
        System.out.println("Heap array length: " + (heapArray.length / (1024 * 1024)) + " MB");
        
        // Clean up
        directBuffer = null;
        heapArray = null;
    }
    
    // ===== GC ALGORITHMS DEMO =====
    
    /**
     * Demonstrates different GC algorithms
     */
    static void demoGCAlgorithms() {
        System.out.println("\n--- GC Algorithms Demo ---");
        
        // Get GC information
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println("GC Name: " + gcBean.getName());
            System.out.println("  Collection Count: " + gcBean.getCollectionCount());
            System.out.println("  Collection Time: " + gcBean.getCollectionTime() + " ms");
        }
        
        // Demonstrate GC behavior
        GCBehaviorDemo demo = new GCBehaviorDemo();
        demo.demonstrate();
    }
    
    // ===== MEMORY PROFILING DEMO =====
    
    /**
     * Demonstrates memory profiling techniques
     */
    static void demoMemoryProfiling() {
        System.out.println("\n--- Memory Profiling Demo ---");
        
        MemoryProfiler profiler = new MemoryProfiler();
        profiler.startProfiling();
        
        // Create objects for profiling
        for (int i = 0; i < 1000; i++) {
            new ProfiledObject("Object-" + i);
        }
        
        profiler.stopProfiling();
        profiler.printProfile();
    }
    
    // ===== PERFORMANCE TUNING DEMO =====
    
    /**
     * Demonstrates performance tuning techniques
     */
    static void demoPerformanceTuning() {
        System.out.println("\n--- Performance Tuning Demo ---");
        
        PerformanceTuner tuner = new PerformanceTuner();
        
        // Test different configurations
        tuner.testConfiguration("Default", () -> performMemoryIntensiveTask());
        tuner.testConfiguration("Optimized", () -> performOptimizedTask());
        
        tuner.printResults();
    }
    
    // ===== HELPER METHODS =====
    
    private static long getFreeMemory() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.freeMemory() / (1024 * 1024));
    }
    
    private static void performMemoryIntensiveTask() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add("Item-" + i);
        }
        // Simulate work
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void performOptimizedTask() {
        // Use more memory-efficient approach
        try {
            Thread.sleep(50); // Simulate faster execution
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// ===== MEMORY LEAK DEMO CLASSES =====

class MemoryLeakDemo {
    // Static collection that can cause memory leaks
    static final Map<String, Object> staticMap = new ConcurrentHashMap<>();
    
    // ThreadLocal that can cause memory leaks
    static final ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    
    // Event listeners that can cause memory leaks
    static final List<EventListener> eventListeners = new ArrayList<>();
    
    static void addEventListener() {
        eventListeners.add(new EventListener() {
            @Override
            public void onEvent() {
                // Event handling logic
            }
        });
    }
}

interface EventListener {
    void onEvent();
}

// ===== OBJECT POOL IMPLEMENTATION =====

class ObjectPool<T> {
    private final Queue<T> pool;
    private final Set<T> activeObjects;
    private final int maxSize;
    
    public ObjectPool(int maxSize) {
        this.maxSize = maxSize;
        this.pool = new ConcurrentLinkedQueue<>();
        this.activeObjects = ConcurrentHashMap.newKeySet();
    }
    
    public T acquire() {
        T obj = pool.poll();
        if (obj == null) {
            obj = createObject();
        }
        activeObjects.add(obj);
        return obj;
    }
    
    public void release(T obj) {
        if (activeObjects.remove(obj)) {
            if (pool.size() < maxSize) {
                resetObject(obj);
                pool.offer(obj);
            }
        }
    }
    
    public int getActiveCount() {
        return activeObjects.size();
    }
    
    public int getIdleCount() {
        return pool.size();
    }
    
    @SuppressWarnings("unchecked")
    private T createObject() {
        return (T) new String("PooledObject-" + System.currentTimeMillis());
    }
    
    private void resetObject(T obj) {
        // Reset object state if needed
    }
}

// ===== MEMORY EFFICIENT COLLECTIONS =====

class MemoryEfficientCollections {
    public void demonstrate() {
        // Use primitive arrays instead of wrapper objects
        int[] primitiveArray = new int[1000];
        Integer[] wrapperArray = new Integer[1000];
        
        // Use specialized collections
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        
        // Use concurrent collections for thread safety
        Map<String, String> concurrentMap = new ConcurrentHashMap<>();
        
        System.out.println("Memory efficient collections demonstrated");
    }
}

// ===== MEMORY MONITOR =====

class MemoryMonitor {
    private final AtomicLong startTime = new AtomicLong(0);
    private final AtomicLong endTime = new AtomicLong(0);
    private final List<MemorySnapshot> snapshots = new ArrayList<>();
    private volatile boolean monitoring = false;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    public void startMonitoring() {
        startTime.set(System.currentTimeMillis());
        monitoring = true;
        
        scheduler.scheduleAtFixedRate(() -> {
            if (monitoring) {
                takeSnapshot();
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }
    
    public void stopMonitoring() {
        monitoring = false;
        endTime.set(System.currentTimeMillis());
        scheduler.shutdown();
    }
    
    private void takeSnapshot() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        snapshots.add(new MemorySnapshot(System.currentTimeMillis(), usedMemory, freeMemory));
    }
    
    public void printReport() {
        System.out.println("Memory Monitoring Report:");
        System.out.println("Duration: " + ((endTime.get() - startTime.get()) / 1000) + " seconds");
        System.out.println("Snapshots taken: " + snapshots.size());
        
        if (!snapshots.isEmpty()) {
            long maxUsed = snapshots.stream().mapToLong(s -> s.usedMemory).max().orElse(0);
            long minUsed = snapshots.stream().mapToLong(s -> s.usedMemory).min().orElse(0);
            long avgUsed = snapshots.stream().mapToLong(s -> s.usedMemory).sum() / snapshots.size();
            
            System.out.println("Max memory used: " + (maxUsed / (1024 * 1024)) + " MB");
            System.out.println("Min memory used: " + (minUsed / (1024 * 1024)) + " MB");
            System.out.println("Avg memory used: " + (avgUsed / (1024 * 1024)) + " MB");
        }
    }
    
    static class MemorySnapshot {
        final long timestamp;
        final long usedMemory;
        final long freeMemory;
        
        MemorySnapshot(long timestamp, long usedMemory, long freeMemory) {
            this.timestamp = timestamp;
            this.usedMemory = usedMemory;
            this.freeMemory = freeMemory;
        }
    }
}

// ===== GC BEHAVIOR DEMO =====

class GCBehaviorDemo {
    public void demonstrate() {
        System.out.println("Demonstrating GC behavior...");
        
        // Create objects in different generations
        for (int i = 0; i < 10000; i++) {
            new ShortLivedObject();
        }
        
        // Create some long-lived objects
        List<Object> longLivedObjects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            longLivedObjects.add(new LongLivedObject());
        }
        
        // Force minor GC
        System.gc();
        
        System.out.println("GC behavior demonstration completed");
    }
}

class ShortLivedObject {
    private final byte[] data = new byte[1024]; // 1KB
    
    public ShortLivedObject() {
        // Constructor
    }
}

class LongLivedObject {
    private final byte[] data = new byte[1024 * 1024]; // 1MB
    
    public LongLivedObject() {
        // Constructor
    }
}

// ===== MEMORY PROFILER =====

class MemoryProfiler {
    private final Map<String, Integer> objectCounts = new HashMap<>();
    private final Map<String, Long> memoryUsage = new HashMap<>();
    private long startMemory;
    private long endMemory;
    
    public void startProfiling() {
        startMemory = getCurrentMemoryUsage();
        objectCounts.clear();
        memoryUsage.clear();
    }
    
    public void stopProfiling() {
        endMemory = getCurrentMemoryUsage();
    }
    
    public void printProfile() {
        System.out.println("Memory Profiling Results:");
        System.out.println("Memory change: " + ((endMemory - startMemory) / (1024 * 1024)) + " MB");
        System.out.println("Object counts: " + objectCounts);
        System.out.println("Memory usage by type: " + memoryUsage);
    }
    
    private long getCurrentMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}

class ProfiledObject {
    private final String name;
    private final byte[] data;
    
    public ProfiledObject(String name) {
        this.name = name;
        this.data = new byte[1024]; // 1KB
    }
}

// ===== PERFORMANCE TUNER =====

class PerformanceTuner {
    private final Map<String, Long> executionTimes = new HashMap<>();
    
    public void testConfiguration(String name, Runnable task) {
        long startTime = System.currentTimeMillis();
        task.run();
        long endTime = System.currentTimeMillis();
        
        executionTimes.put(name, endTime - startTime);
    }
    
    public void printResults() {
        System.out.println("Performance Tuning Results:");
        executionTimes.forEach((name, time) -> 
            System.out.println(name + ": " + time + " ms"));
    }
}

/**
 * Java Memory Management Interview Questions
 * 
 * This section contains 100 interview questions categorized by difficulty level.
 * Each question tests understanding of Java memory management concepts and practices.
 */

class MemoryManagementInterviewQuestions {
    
    // ===== BEGINNER LEVEL QUESTIONS =====
    
    /**
     * 1. What is garbage collection in Java?
     * 2. Explain the difference between heap and stack memory.
     * 3. What are the different memory areas in JVM?
     * 4. How does the garbage collector work?
     * 5. What is the purpose of System.gc()?
     * 6. Explain the concept of memory leaks.
     * 7. What is the difference between strong and weak references?
     * 8. How do you check memory usage in Java?
     * 9. What is the purpose of finalize() method?
     * 10. Explain the concept of object lifecycle.
     * 11. What is the difference between heap and non-heap memory?
     * 12. How does JVM allocate memory for objects?
     * 13. What is the purpose of memory pools?
     * 14. Explain the concept of memory fragmentation.
     * 15. What is the difference between minor and major GC?
     * 16. How do you prevent memory leaks?
     * 17. What is the purpose of reference counting?
     * 18. Explain the concept of memory barriers.
     * 19. What is the difference between GC and manual memory management?
     * 20. How do you optimize memory usage in Java?
     */
    
    // ===== INTERMEDIATE LEVEL QUESTIONS =====
    
    /**
     * 21. Implement a custom memory pool for object reuse.
     * 22. How would you implement a memory leak detector?
     * 23. Explain the different GC algorithms and their trade-offs.
     * 24. How do you implement a custom reference type?
     * 25. What is the purpose of G1GC and when to use it?
     * 26. Implement a memory-efficient cache.
     * 27. How would you implement a memory profiler?
     * 28. Explain the concept of generational garbage collection.
     * 29. How do you implement a custom memory allocator?
     * 30. What is the purpose of CMS GC and when to use it?
     * 31. Implement a memory leak prevention mechanism.
     * 32. How do you implement a custom garbage collector?
     * 33. Explain the concept of concurrent garbage collection.
     * 34. How would you implement a memory monitoring system?
     * 35. What is the purpose of ZGC and when to use it?
     * 36. Implement a memory-efficient data structure.
     * 37. How do you implement a custom memory pool?
     * 38. Explain the concept of incremental garbage collection.
     * 39. How would you implement a memory leak detector?
     * 40. What is the purpose of Shenandoah GC and when to use it?
     */
    
    // ===== ADVANCED LEVEL QUESTIONS =====
    
    /**
     * 41. Implement a distributed memory management system.
     * 42. How would you implement a custom GC algorithm?
     * 43. Explain the concept of off-heap memory management.
     * 44. How do you implement a memory-efficient serialization?
     * 45. What is the purpose of MemorySegment and how to use it?
     * 46. Implement a custom memory allocator with custom policies.
     * 47. How would you implement a memory leak prevention framework?
     * 48. Explain the concept of memory ordering and its impact.
     * 49. How do you implement a custom memory pool with eviction?
     * 50. What is the purpose of ScopedValue and how to use it?
     * 51. Implement a memory-efficient database connection pool.
     * 52. How would you implement a custom reference queue?
     * 53. Explain the concept of memory pressure and how to handle it.
     * 54. How do you implement a custom memory barrier?
     * 55. What is the purpose of StructuredTaskScope and memory management?
     * 56. Implement a custom memory pool with load balancing.
     * 57. How would you implement a memory leak detection framework?
     * 58. Explain the concept of memory locality and its optimization.
     * 59. How do you implement a custom memory allocator with fragmentation handling?
     * 60. What is the purpose of Virtual Threads and memory management?
     */
    
    // ===== EXPERT LEVEL QUESTIONS =====
    
    /**
     * 61. Implement a distributed memory management system with consistency guarantees.
     * 62. How would you implement a custom GC algorithm with custom heuristics?
     * 63. Explain the concept of memory-mapped files and their optimization.
     * 64. How do you implement a memory-efficient distributed cache?
     * 65. What is the purpose of MemorySegment with custom allocation strategies?
     * 66. Implement a custom memory allocator with custom memory pools.
     * 67. How would you implement a memory leak prevention framework with static analysis?
     * 68. Explain the concept of memory ordering in distributed systems.
     * 69. How do you implement a custom memory pool with custom eviction policies?
     * 70. What is the purpose of ScopedValue with custom scoping rules?
     * 71. Implement a memory-efficient distributed database.
     * 72. How would you implement a custom reference queue with custom policies?
     * 73. Explain the concept of memory pressure in distributed systems.
     * 74. How do you implement a custom memory barrier with custom ordering?
     * 75. What is the purpose of StructuredTaskScope with custom memory management?
     * 76. Implement a custom memory pool with custom load balancing strategies.
     * 77. How would you implement a memory leak detection framework with ML?
     * 78. Explain the concept of memory locality in distributed systems.
     * 79. How do you implement a custom memory allocator with custom fragmentation handling?
     * 80. What is the purpose of Virtual Threads with custom memory management?
     */
    
    // ===== SYSTEM DESIGN QUESTIONS =====
    
    /**
     * 81. Design a high-performance memory management system for a distributed cache.
     * 82. How would you design a memory-efficient database system?
     * 83. Design a real-time memory monitoring system for a microservices architecture.
     * 84. How would you design a memory management system for a gaming engine?
     * 85. Design a distributed memory management system for a big data platform.
     * 86. How would you design a memory-efficient streaming system?
     * 87. Design a real-time memory optimization system for a trading platform.
     * 88. How would you design a memory management system for a mobile app?
     * 89. Design a distributed memory management system for a cloud platform.
     * 90. How would you design a memory-efficient search engine?
     * 91. Design a high-performance memory management system for a web server.
     * 92. How would you design a memory management system for an IoT platform?
     * 93. Design a real-time memory optimization system for a social media platform.
     * 94. How would you design a memory management system for a blockchain platform?
     * 95. Design a distributed memory management system for a machine learning platform.
     * 96. How would you design a memory-efficient recommendation engine?
     * 97. Design a high-performance memory management system for a video streaming platform.
     * 98. How would you design a memory management system for an autonomous vehicle platform?
     * 99. Design a real-time memory optimization system for a financial platform.
     * 100. How would you design a memory management system for a quantum computing platform?
     */
}
