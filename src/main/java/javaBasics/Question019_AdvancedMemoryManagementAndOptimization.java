import java.util.*;
import java.util.concurrent.*;
import java.lang.ref.*;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Advanced Memory Management and Optimization - Comprehensive Guide
 * 
 * This class demonstrates various advanced memory management techniques
 * commonly asked in Java interviews for experienced developers.
 * 
 * Topics covered:
 * - Memory leak detection and prevention
 * - Garbage collection optimization
 * - Reference types and weak references
 * - Memory pools and object pooling
 * - Off-heap memory management
 * - Performance monitoring and tuning
 */
public class Question019_AdvancedMemoryManagementAndOptimization {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Memory Management and Optimization - Complete Guide ===\n");
        
        demonstrateMemoryLeakDetection();
        demonstrateReferenceTypes();
        demonstrateMemoryPools();
        demonstrateOffHeapMemory();
        demonstrateGarbageCollectionOptimization();
        demonstratePerformanceMonitoring();
        
        System.out.println("\n=== Advanced Memory Management and Optimization Completed! ===");
    }
    
    // ===== MEMORY LEAK DETECTION =====
    
    /**
     * Memory leak detector
     */
    static class MemoryLeakDetector {
        private final Map<String, WeakReference<Object>> objectReferences = new ConcurrentHashMap<>();
        private final AtomicLong objectCounter = new AtomicLong(0);
        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        public void trackObject(String category, Object obj) {
            String id = category + "_" + objectCounter.incrementAndGet();
            objectReferences.put(id, new WeakReference<>(obj));
        }
        
        public void startMonitoring() {
            scheduler.scheduleAtFixedRate(this::checkForLeaks, 5, 5, TimeUnit.SECONDS);
        }
        
        private void checkForLeaks() {
            System.out.println("  Checking for memory leaks...");
            int totalObjects = objectReferences.size();
            int liveObjects = 0;
            int deadObjects = 0;
            
            Iterator<Map.Entry<String, WeakReference<Object>>> iterator = objectReferences.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, WeakReference<Object>> entry = iterator.next();
                WeakReference<Object> ref = entry.getValue();
                
                if (ref.get() != null) {
                    liveObjects++;
                } else {
                    deadObjects++;
                    iterator.remove();
                }
            }
            
            System.out.println("    Total objects: " + totalObjects);
            System.out.println("    Live objects: " + liveObjects);
            System.out.println("    Dead objects: " + deadObjects);
            
            if (deadObjects > totalObjects * 0.8) {
                System.out.println("    WARNING: High number of dead objects detected!");
            }
        }
        
        public void shutdown() {
            scheduler.shutdown();
        }
    }
    
    /**
     * Common memory leak scenarios
     */
    static class MemoryLeakScenarios {
        
        /**
         * Static collection memory leak
         */
        private static final List<Object> staticList = new ArrayList<>();
        
        public static void demonstrateStaticLeak() {
            System.out.println("  Demonstrating static collection memory leak...");
            for (int i = 0; i < 1000; i++) {
                staticList.add(new byte[1024]); // 1KB objects
            }
            System.out.println("    Added 1000 objects to static list");
        }
        
        /**
         * Event listener memory leak
         */
        private static final List<EventListener> eventListeners = new ArrayList<>();
        
        public static void demonstrateEventListenerLeak() {
            System.out.println("  Demonstrating event listener memory leak...");
            for (int i = 0; i < 100; i++) {
                EventListener listener = new EventListener();
                eventListeners.add(listener);
            }
            System.out.println("    Added 100 event listeners");
        }
        
        /**
         * Thread local memory leak
         */
        private static final ThreadLocal<byte[]> threadLocalData = new ThreadLocal<>();
        
        public static void demonstrateThreadLocalLeak() {
            System.out.println("  Demonstrating ThreadLocal memory leak...");
            threadLocalData.set(new byte[1024 * 1024]); // 1MB
            System.out.println("    Set 1MB data in ThreadLocal");
        }
        
        /**
         * Inner class memory leak
         */
        public static class InnerClassLeak {
            private final byte[] data = new byte[1024 * 1024]; // 1MB
            
            public void demonstrateLeak() {
                System.out.println("  Demonstrating inner class memory leak...");
                System.out.println("    Created inner class with 1MB data");
            }
        }
    }
    
    /**
     * Event listener class for demonstration
     */
    static class EventListener {
        private final byte[] data = new byte[1024]; // 1KB
        
        public void onEvent(String event) {
            // Event handling logic
        }
    }
    
    // ===== REFERENCE TYPES =====
    
    /**
     * Reference type demonstrations
     */
    static class ReferenceTypeDemo {
        
        /**
         * Weak reference cache
         */
        private final Map<String, WeakReference<byte[]>> weakCache = new HashMap<>();
        
        public void demonstrateWeakReferences() {
            System.out.println("  Demonstrating Weak References:");
            
            // Add objects to weak cache
            for (int i = 0; i < 5; i++) {
                String key = "key" + i;
                byte[] data = new byte[1024 * 1024]; // 1MB
                weakCache.put(key, new WeakReference<>(data));
                System.out.println("    Added " + key + " to weak cache");
            }
            
            // Check cache before GC
            System.out.println("    Cache size before GC: " + weakCache.size());
            System.out.println("    Live references before GC: " + countLiveReferences());
            
            // Suggest garbage collection
            System.gc();
            
            // Check cache after GC
            System.out.println("    Cache size after GC: " + weakCache.size());
            System.out.println("    Live references after GC: " + countLiveReferences());
        }
        
        private int countLiveReferences() {
            int count = 0;
            for (WeakReference<byte[]> ref : weakCache.values()) {
                if (ref.get() != null) {
                    count++;
                }
            }
            return count;
        }
        
        /**
         * Soft reference cache for memory-sensitive caching
         */
        private final Map<String, SoftReference<byte[]>> softCache = new HashMap<>();
        
        public void demonstrateSoftReferences() {
            System.out.println("  Demonstrating Soft References:");
            
            // Add objects to soft cache
            for (int i = 0; i < 10; i++) {
                String key = "soft_key" + i;
                byte[] data = new byte[1024 * 1024]; // 1MB
                softCache.put(key, new SoftReference<>(data));
            }
            
            System.out.println("    Added 10 objects to soft cache");
            System.out.println("    Soft cache size: " + softCache.size());
        }
        
        /**
         * Phantom reference for cleanup
         */
        private final ReferenceQueue<byte[]> phantomQueue = new ReferenceQueue<>();
        private final Map<PhantomReference<byte[]>, String> phantomRefs = new HashMap<>();
        
        public void demonstratePhantomReferences() {
            System.out.println("  Demonstrating Phantom References:");
            
            for (int i = 0; i < 3; i++) {
                String resourceName = "resource" + i;
                byte[] data = new byte[1024 * 1024]; // 1MB
                PhantomReference<byte[]> phantomRef = new PhantomReference<>(data, phantomQueue);
                phantomRefs.put(phantomRef, resourceName);
                System.out.println("    Created phantom reference for " + resourceName);
            }
            
            // Start cleanup thread
            startCleanupThread();
        }
        
        private void startCleanupThread() {
            Thread cleanupThread = new Thread(() -> {
                try {
                    while (true) {
                        PhantomReference<byte[]> ref = (PhantomReference<byte[]>) phantomQueue.remove();
                        String resourceName = phantomRefs.remove(ref);
                        System.out.println("    Cleaning up resource: " + resourceName);
                        ref.clear();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            cleanupThread.setDaemon(true);
            cleanupThread.start();
        }
    }
    
    // ===== MEMORY POOLS =====
    
    /**
     * Object pool for memory optimization
     */
    static class ObjectPool<T> {
        private final Queue<T> pool;
        private final Supplier<T> factory;
        private final Consumer<T> resetter;
        private final int maxSize;
        private final AtomicLong createdCount = new AtomicLong(0);
        private final AtomicLong reusedCount = new AtomicLong(0);
        
        public ObjectPool(int initialSize, int maxSize, Supplier<T> factory, Consumer<T> resetter) {
            this.maxSize = maxSize;
            this.factory = factory;
            this.resetter = resetter;
            this.pool = new ConcurrentLinkedQueue<>();
            
            // Pre-populate pool
            for (int i = 0; i < initialSize; i++) {
                pool.offer(factory.get());
                createdCount.incrementAndGet();
            }
        }
        
        public T borrow() {
            T obj = pool.poll();
            if (obj != null) {
                reusedCount.incrementAndGet();
                return obj;
            } else {
                createdCount.incrementAndGet();
                return factory.get();
            }
        }
        
        public void returnObject(T obj) {
            if (obj != null && pool.size() < maxSize) {
                resetter.accept(obj);
                pool.offer(obj);
            }
        }
        
        public void printStats() {
            System.out.println("    Pool Statistics:");
            System.out.println("      Pool size: " + pool.size());
            System.out.println("      Objects created: " + createdCount.get());
            System.out.println("      Objects reused: " + reusedCount.get());
            System.out.println("      Reuse rate: " + 
                String.format("%.2f%%", (double) reusedCount.get() / 
                (reusedCount.get() + createdCount.get()) * 100));
        }
    }
    
    /**
     * Memory pool for byte arrays
     */
    static class ByteArrayPool {
        private final Queue<byte[]> smallPool; // 1KB
        private final Queue<byte[]> mediumPool; // 10KB
        private final Queue<byte[]> largePool; // 100KB
        
        public ByteArrayPool() {
            this.smallPool = new ConcurrentLinkedQueue<>();
            this.mediumPool = new ConcurrentLinkedQueue<>();
            this.largePool = new ConcurrentLinkedQueue<>();
        }
        
        public byte[] borrow(int size) {
            if (size <= 1024) {
                byte[] obj = smallPool.poll();
                return obj != null ? obj : new byte[1024];
            } else if (size <= 10240) {
                byte[] obj = mediumPool.poll();
                return obj != null ? obj : new byte[10240];
            } else if (size <= 102400) {
                byte[] obj = largePool.poll();
                return obj != null ? obj : new byte[102400];
            } else {
                return new byte[size];
            }
        }
        
        public void returnObject(byte[] obj) {
            if (obj == null) return;
            
            int size = obj.length;
            if (size == 1024 && smallPool.size() < 100) {
                smallPool.offer(obj);
            } else if (size == 10240 && mediumPool.size() < 50) {
                mediumPool.offer(obj);
            } else if (size == 102400 && largePool.size() < 20) {
                largePool.offer(obj);
            }
        }
        
        public void printStats() {
            System.out.println("    Byte Array Pool Statistics:");
            System.out.println("      Small pool (1KB): " + smallPool.size());
            System.out.println("      Medium pool (10KB): " + mediumPool.size());
            System.out.println("      Large pool (100KB): " + largePool.size());
        }
    }
    
    // ===== OFF-HEAP MEMORY =====
    
    /**
     * Off-heap memory manager
     */
    static class OffHeapMemoryManager {
        private final List<ByteBuffer> allocatedBuffers = new ArrayList<>();
        private final AtomicLong totalAllocated = new AtomicLong(0);
        
        public ByteBuffer allocateDirect(int size) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(size);
            allocatedBuffers.add(buffer);
            totalAllocated.addAndGet(size);
            return buffer;
        }
        
        public void releaseBuffer(ByteBuffer buffer) {
            if (allocatedBuffers.remove(buffer)) {
                totalAllocated.addAndGet(-buffer.capacity());
                // Note: Direct buffers are automatically cleaned up by GC
            }
        }
        
        public void printStats() {
            System.out.println("    Off-Heap Memory Statistics:");
            System.out.println("      Total allocated: " + totalAllocated.get() + " bytes");
            System.out.println("      Active buffers: " + allocatedBuffers.size());
        }
        
        public void cleanup() {
            allocatedBuffers.clear();
            totalAllocated.set(0);
            System.gc(); // Suggest cleanup of direct buffers
        }
    }
    
    // ===== GARBAGE COLLECTION OPTIMIZATION =====
    
    /**
     * Garbage collection monitor
     */
    static class GCMonitor {
        private final List<Long> gcDurations = new ArrayList<>();
        private final AtomicLong totalGCDuration = new AtomicLong(0);
        private final AtomicLong gcCount = new AtomicLong(0);
        
        public void startMonitoring() {
            // Add GC notification listener
            System.out.println("  Starting GC monitoring...");
            
            // Monitor GC through JMX (simplified version)
            startGCMonitoringThread();
        }
        
        private void startGCMonitoringThread() {
            Thread monitorThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(1000);
                        // Simulate GC monitoring
                        if (Math.random() < 0.1) { // 10% chance of GC
                            long duration = (long) (Math.random() * 100);
                            recordGC(duration);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            monitorThread.setDaemon(true);
            monitorThread.start();
        }
        
        private void recordGC(long duration) {
            gcDurations.add(duration);
            totalGCDuration.addAndGet(duration);
            gcCount.incrementAndGet();
            
            if (gcCount.get() % 5 == 0) {
                printGCStats();
            }
        }
        
        private void printGCStats() {
            if (gcDurations.isEmpty()) return;
            
            long avgDuration = totalGCDuration.get() / gcCount.get();
            long maxDuration = gcDurations.stream().mapToLong(Long::longValue).max().orElse(0);
            long minDuration = gcDurations.stream().mapToLong(Long::longValue).min().orElse(0);
            
            System.out.println("    GC Statistics:");
            System.out.println("      Total GC count: " + gcCount.get());
            System.out.println("      Average duration: " + avgDuration + "ms");
            System.out.println("      Max duration: " + maxDuration + "ms");
            System.out.println("      Min duration: " + minDuration + "ms");
        }
    }
    
    /**
     * Memory pressure simulator
     */
    static class MemoryPressureSimulator {
        private final List<byte[]> memoryHolders = new ArrayList<>();
        
        public void createMemoryPressure(int mbSize) {
            System.out.println("  Creating memory pressure: " + mbSize + "MB");
            
            for (int i = 0; i < mbSize; i++) {
                byte[] data = new byte[1024 * 1024]; // 1MB
                memoryHolders.add(data);
                
                if (i % 10 == 0) {
                    System.out.println("    Allocated " + (i + 1) + "MB");
                }
            }
        }
        
        public void releaseMemory() {
            System.out.println("  Releasing memory pressure");
            memoryHolders.clear();
            System.gc();
        }
        
        public void printMemoryUsage() {
            Runtime runtime = Runtime.getRuntime();
            long totalMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();
            long usedMemory = totalMemory - freeMemory;
            long maxMemory = runtime.maxMemory();
            
            System.out.println("    Memory Usage:");
            System.out.println("      Total memory: " + (totalMemory / 1024 / 1024) + "MB");
            System.out.println("      Free memory: " + (freeMemory / 1024 / 1024) + "MB");
            System.out.println("      Used memory: " + (usedMemory / 1024 / 1024) + "MB");
            System.out.println("      Max memory: " + (maxMemory / 1024 / 1024) + "MB");
            System.out.println("      Usage percentage: " + 
                String.format("%.2f%%", (double) usedMemory / maxMemory * 100));
        }
    }
    
    // ===== PERFORMANCE MONITORING =====
    
    /**
     * Memory performance monitor
     */
    static class MemoryPerformanceMonitor {
        private final Map<String, List<Long>> operationTimes = new ConcurrentHashMap<>();
        private final Map<String, List<Long>> memoryUsage = new ConcurrentHashMap<>();
        
        public void startOperation(String operationName) {
            operationTimes.computeIfAbsent(operationName, k -> new ArrayList<>());
            memoryUsage.computeIfAbsent(operationName, k -> new ArrayList<>());
        }
        
        public void endOperation(String operationName) {
            Runtime runtime = Runtime.getRuntime();
            long usedMemory = runtime.totalMemory() - runtime.freeMemory();
            
            List<Long> times = operationTimes.get(operationName);
            List<Long> memory = memoryUsage.get(operationName);
            
            if (times != null && memory != null) {
                times.add(System.nanoTime());
                memory.add(usedMemory);
            }
        }
        
        public void printPerformanceReport() {
            System.out.println("\n=== MEMORY PERFORMANCE REPORT ===");
            
            for (Map.Entry<String, List<Long>> entry : operationTimes.entrySet()) {
                String operation = entry.getKey();
                List<Long> times = entry.getValue();
                List<Long> memory = memoryUsage.get(operation);
                
                if (times != null && memory != null && !times.isEmpty()) {
                    System.out.println("Operation: " + operation);
                    
                    // Time statistics
                    long avgTime = times.stream().mapToLong(Long::longValue).sum() / times.size();
                    System.out.println("  Average time: " + avgTime + " ns");
                    
                    // Memory statistics
                    if (!memory.isEmpty()) {
                        long avgMemory = memory.stream().mapToLong(Long::longValue).sum() / memory.size();
                        System.out.println("  Average memory: " + (avgMemory / 1024 / 1024) + "MB");
                    }
                    
                    System.out.println();
                }
            }
        }
    }
    
    // ===== DEMONSTRATION METHODS =====
    
    private static void demonstrateMemoryLeakDetection() {
        System.out.println("1. MEMORY LEAK DETECTION:\n");
        
        MemoryLeakDetector detector = new MemoryLeakDetector();
        detector.startMonitoring();
        
        // Demonstrate various memory leak scenarios
        MemoryLeakScenarios.demonstrateStaticLeak();
        MemoryLeakScenarios.demonstrateEventListenerLeak();
        MemoryLeakScenarios.demonstrateThreadLocalLeak();
        
        MemoryLeakScenarios.InnerClassLeak innerLeak = new MemoryLeakScenarios.InnerClassLeak();
        innerLeak.demonstrateLeak();
        
        // Wait for monitoring
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        detector.shutdown();
        System.out.println();
    }
    
    private static void demonstrateReferenceTypes() {
        System.out.println("2. REFERENCE TYPES:\n");
        
        ReferenceTypeDemo refDemo = new ReferenceTypeDemo();
        
        refDemo.demonstrateWeakReferences();
        refDemo.demonstrateSoftReferences();
        refDemo.demonstratePhantomReferences();
        
        // Wait for phantom reference cleanup
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println();
    }
    
    private static void demonstrateMemoryPools() {
        System.out.println("3. MEMORY POOLS:\n");
        
        System.out.println("Object Pool demonstration:");
        ObjectPool<StringBuilder> stringBuilderPool = new ObjectPool<>(
            5, 20, StringBuilder::new, StringBuilder::setLength
        );
        
        // Use the pool
        for (int i = 0; i < 10; i++) {
            StringBuilder sb = stringBuilderPool.borrow();
            sb.append("Operation ").append(i);
            stringBuilderPool.returnObject(sb);
        }
        
        stringBuilderPool.printStats();
        
        System.out.println("\nByte Array Pool demonstration:");
        ByteArrayPool byteArrayPool = new ByteArrayPool();
        
        for (int i = 0; i < 5; i++) {
            byte[] data = byteArrayPool.borrow(1024);
            byteArrayPool.returnObject(data);
        }
        
        byteArrayPool.printStats();
        System.out.println();
    }
    
    private static void demonstrateOffHeapMemory() {
        System.out.println("4. OFF-HEAP MEMORY:\n");
        
        OffHeapMemoryManager offHeapManager = new OffHeapMemoryManager();
        
        // Allocate off-heap memory
        ByteBuffer buffer1 = offHeapManager.allocateDirect(1024 * 1024); // 1MB
        ByteBuffer buffer2 = offHeapManager.allocateDirect(1024 * 1024); // 1MB
        
        System.out.println("  Allocated 2MB of off-heap memory");
        
        offHeapManager.printStats();
        
        // Release one buffer
        offHeapManager.releaseBuffer(buffer1);
        System.out.println("  Released one buffer");
        
        offHeapManager.printStats();
        
        offHeapManager.cleanup();
        System.out.println();
    }
    
    private static void demonstrateGarbageCollectionOptimization() {
        System.out.println("5. GARBAGE COLLECTION OPTIMIZATION:\n");
        
        GCMonitor gcMonitor = new GCMonitor();
        gcMonitor.startMonitoring();
        
        MemoryPressureSimulator pressureSim = new MemoryPressureSimulator();
        
        // Show initial memory usage
        pressureSim.printMemoryUsage();
        
        // Create memory pressure
        pressureSim.createMemoryPressure(50);
        pressureSim.printMemoryUsage();
        
        // Release memory
        pressureSim.releaseMemory();
        pressureSim.printMemoryUsage();
        
        // Wait for GC monitoring
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println();
    }
    
    private static void demonstratePerformanceMonitoring() {
        System.out.println("6. PERFORMANCE MONITORING:\n");
        
        MemoryPerformanceMonitor monitor = new MemoryPerformanceMonitor();
        
        // Simulate different operations
        for (int i = 0; i < 5; i++) {
            monitor.startOperation("memory_allocation");
            
            // Allocate memory
            byte[] data = new byte[1024 * 1024]; // 1MB
            
            monitor.endOperation("memory_allocation");
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Simulate object creation
        for (int i = 0; i < 3; i++) {
            monitor.startOperation("object_creation");
            
            // Create objects
            List<String> list = new ArrayList<>();
            for (int j = 0; j < 1000; j++) {
                list.add("Object " + j);
            }
            
            monitor.endOperation("object_creation");
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Print performance report
        monitor.printPerformanceReport();
        
        System.out.println();
    }
}

/**
 * INTERVIEW QUESTIONS COVERED:
 * 
 * THEORETICAL:
 * 1. Explain the different types of references in Java.
 * 2. How does garbage collection work in Java?
 * 3. What are memory leaks and how do you prevent them?
 * 4. Explain the concept of off-heap memory.
 * 5. How do you optimize memory usage in Java applications?
 * 6. What are the trade-offs between different memory management strategies?
 * 
 * PRACTICAL:
 * 1. Implement object pooling for memory optimization.
 * 2. Use weak references for caching.
 * 3. Monitor garbage collection performance.
 * 4. Implement memory leak detection.
 * 5. Optimize memory allocation patterns.
 * 
 * TRICKY SCENARIOS:
 * 1. Handle memory pressure in high-throughput applications.
 * 2. Prevent memory leaks in long-running applications.
 * 3. Optimize memory usage for large data sets.
 * 4. Deal with out-of-memory errors.
 * 
 * PERFORMANCE:
 * 1. Analyze memory allocation patterns.
 * 2. Optimize garbage collection performance.
 * 3. Implement efficient memory pools.
 * 4. Monitor memory usage in production.
 * 
 * DESIGN PATTERNS:
 * 1. Object pool pattern for resource management.
 * 2. Weak reference pattern for caching.
 * 3. Phantom reference pattern for cleanup.
 * 4. Memory pool pattern for allocation optimization.
 * 5. Circuit breaker pattern for memory protection.
 */
