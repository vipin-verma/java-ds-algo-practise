/**
 * Question 5: Thread-Safe Counter Implementation
 * 
 * Problem: Implement a thread-safe counter that can be safely accessed
 * by multiple threads concurrently.
 * 
 * Requirements:
 * - Should be thread-safe for concurrent access
 * - Should support increment, decrement, and get operations
 * - Should be efficient and scalable
 * - Should handle edge cases (overflow, underflow)
 * - Should provide different implementation approaches
 * 
 * Difficulty: Medium
 * Category: Concurrency, Thread Safety
 * Experience Level: 3-5 years
 */
public class Question005_ThreadSafeCounter {
    
    public static void main(String[] args) {
        System.out.println("=== Thread-Safe Counter Implementation ===\n");
        
        // Test basic functionality
        testBasicFunctionality();
        
        // Test thread safety
        testThreadSafety();
        
        // Test performance comparison
        testPerformanceComparison();
        
        // Test edge cases
        testEdgeCases();
        
        // Test advanced features
        testAdvancedFeatures();
    }
    
    private static void testBasicFunctionality() {
        System.out.println("=== Basic Functionality Test ===");
        
        // Test different implementations
        Counter[] counters = {
            new SynchronizedCounter(),
            new AtomicCounter(),
            new ReentrantLockCounter(),
            new StampedLockCounter(),
            new VolatileCounter() // Not thread-safe, for comparison
        };
        
        for (Counter counter : counters) {
            System.out.println("Testing: " + counter.getClass().getSimpleName());
            
            counter.increment();
            counter.increment();
            counter.decrement();
            counter.increment();
            
            System.out.println("Final value: " + counter.getValue());
            System.out.println("---");
        }
    }
    
    private static void testThreadSafety() {
        System.out.println("=== Thread Safety Test ===");
        
        // Test with multiple threads
        int numThreads = 10;
        int operationsPerThread = 1000;
        
        Counter[] counters = {
            new SynchronizedCounter(),
            new AtomicCounter(),
            new ReentrantLockCounter(),
            new StampedLockCounter()
        };
        
        for (Counter counter : counters) {
            System.out.println("Testing thread safety for: " + counter.getClass().getSimpleName());
            
            Thread[] threads = new Thread[numThreads];
            
            // Start all threads
            for (int i = 0; i < numThreads; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < operationsPerThread; j++) {
                        counter.increment();
                        counter.decrement();
                        counter.increment();
                    }
                });
                threads[i].start();
            }
            
            // Wait for all threads to complete
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            int expectedValue = numThreads * operationsPerThread;
            int actualValue = counter.getValue();
            
            System.out.println("Expected: " + expectedValue + ", Actual: " + actualValue);
            System.out.println("Thread-safe: " + (expectedValue == actualValue));
            System.out.println("---");
        }
    }
    
    private static void testPerformanceComparison() {
        System.out.println("=== Performance Comparison Test ===");
        
        int numThreads = 20;
        int operationsPerThread = 10000;
        
        Counter[] counters = {
            new SynchronizedCounter(),
            new AtomicCounter(),
            new ReentrantLockCounter(),
            new StampedLockCounter()
        };
        
        for (Counter counter : counters) {
            System.out.println("Performance test for: " + counter.getClass().getSimpleName());
            
            Thread[] threads = new Thread[numThreads];
            
            long startTime = System.currentTimeMillis();
            
            // Start all threads
            for (int i = 0; i < numThreads; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < operationsPerThread; j++) {
                        counter.increment();
                        counter.decrement();
                        counter.increment();
                    }
                });
                threads[i].start();
            }
            
            // Wait for all threads to complete
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            
            int totalOperations = numThreads * operationsPerThread * 3;
            double operationsPerSecond = (double) totalOperations / totalTime * 1000;
            
            System.out.println("Total time: " + totalTime + "ms");
            System.out.println("Operations per second: " + String.format("%.2f", operationsPerSecond));
            System.out.println("Final value: " + counter.getValue());
            System.out.println("---");
        }
    }
    
    private static void testEdgeCases() {
        System.out.println("=== Edge Cases Test ===");
        
        // Test overflow handling
        System.out.println("Testing overflow handling:");
        Counter counter = new AtomicCounter();
        
        // Set to near max value
        for (int i = 0; i < Integer.MAX_VALUE - 100; i++) {
            counter.increment();
        }
        
        System.out.println("Near max value: " + counter.getValue());
        
        // Try to overflow
        try {
            for (int i = 0; i < 200; i++) {
                counter.increment();
            }
        } catch (Exception e) {
            System.out.println("Overflow handled: " + e.getMessage());
        }
        
        System.out.println("Final value: " + counter.getValue());
        System.out.println("---");
        
        // Test underflow handling
        System.out.println("Testing underflow handling:");
        Counter negativeCounter = new AtomicCounter();
        
        // Set to near min value
        for (int i = 0; i < 100; i++) {
            negativeCounter.decrement();
        }
        
        System.out.println("Near min value: " + negativeCounter.getValue());
        System.out.println("---");
    }
    
    private static void testAdvancedFeatures() {
        System.out.println("=== Advanced Features Test ===");
        
        // Test with custom bounds
        BoundedCounter boundedCounter = new BoundedCounter(0, 100);
        
        System.out.println("Testing bounded counter (0 to 100):");
        
        // Try to exceed upper bound
        for (int i = 0; i < 150; i++) {
            boundedCounter.increment();
        }
        System.out.println("After exceeding upper bound: " + boundedCounter.getValue());
        
        // Try to go below lower bound
        for (int i = 0; i < 150; i++) {
            boundedCounter.decrement();
        }
        System.out.println("After going below lower bound: " + boundedCounter.getValue());
        
        // Test reset functionality
        boundedCounter.reset();
        System.out.println("After reset: " + boundedCounter.getValue());
        
        System.out.println("---");
        
        // Test statistics counter
        StatisticsCounter statsCounter = new StatisticsCounter();
        
        System.out.println("Testing statistics counter:");
        for (int i = 0; i < 100; i++) {
            statsCounter.increment();
        }
        
        System.out.println("Current value: " + statsCounter.getValue());
        System.out.println("Total increments: " + statsCounter.getTotalIncrements());
        System.out.println("Total decrements: " + statsCounter.getTotalDecrements());
        System.out.println("Peak value: " + statsCounter.getPeakValue());
        System.out.println("Average value: " + String.format("%.2f", statsCounter.getAverageValue()));
    }
    
    /**
     * Counter interface
     */
    interface Counter {
        void increment();
        void decrement();
        int getValue();
        void reset();
    }
    
    /**
     * Implementation 1: Using synchronized keyword
     * Pros: Simple, easy to understand
     * Cons: Can cause contention, not scalable
     */
    static class SynchronizedCounter implements Counter {
        private int value = 0;
        
        @Override
        public synchronized void increment() {
            value++;
        }
        
        @Override
        public synchronized void decrement() {
            value--;
        }
        
        @Override
        public synchronized int getValue() {
            return value;
        }
        
        @Override
        public synchronized void reset() {
            value = 0;
        }
    }
    
    /**
     * Implementation 2: Using AtomicInteger
     * Pros: Lock-free, highly scalable, built-in overflow protection
     * Cons: Limited operations
     */
    static class AtomicCounter implements Counter {
        private final java.util.concurrent.atomic.AtomicInteger value = new java.util.concurrent.atomic.AtomicInteger(0);
        
        @Override
        public void increment() {
            value.incrementAndGet();
        }
        
        @Override
        public void decrement() {
            value.decrementAndGet();
        }
        
        @Override
        public int getValue() {
            return value.get();
        }
        
        @Override
        public void reset() {
            value.set(0);
        }
    }
    
    /**
     * Implementation 3: Using ReentrantLock
     * Pros: More flexible than synchronized, better performance
     * Cons: More complex, manual lock management
     */
    static class ReentrantLockCounter implements Counter {
        private int value = 0;
        private final java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();
        
        @Override
        public void increment() {
            lock.lock();
            try {
                value++;
            } finally {
                lock.unlock();
            }
        }
        
        @Override
        public void decrement() {
            lock.lock();
            try {
                value--;
            } finally {
                lock.unlock();
            }
        }
        
        @Override
        public int getValue() {
            lock.lock();
            try {
                return value;
            } finally {
                lock.unlock();
            }
        }
        
        @Override
        public void reset() {
            lock.lock();
            try {
                value = 0;
            } finally {
                lock.unlock();
            }
        }
    }
    
    /**
     * Implementation 4: Using StampedLock (Java 8+)
     * Pros: Optimistic reads, better performance for read-heavy workloads
     * Cons: More complex, Java 8+ only
     */
    static class StampedLockCounter implements Counter {
        private int value = 0;
        private final java.util.concurrent.locks.StampedLock lock = new java.util.concurrent.locks.StampedLock();
        
        @Override
        public void increment() {
            long stamp = lock.writeLock();
            try {
                value++;
            } finally {
                lock.unlockWrite(stamp);
            }
        }
        
        @Override
        public void decrement() {
            long stamp = lock.writeLock();
            try {
                value--;
            } finally {
                lock.unlockWrite(stamp);
            }
        }
        
        @Override
        public int getValue() {
            long stamp = lock.tryOptimisticRead();
            int currentValue = value;
            
            if (!lock.validate(stamp)) {
                // Fall back to read lock
                stamp = lock.readLock();
                try {
                    currentValue = value;
                } finally {
                    lock.unlockRead(stamp);
                }
            }
            
            return currentValue;
        }
        
        @Override
        public void reset() {
            long stamp = lock.writeLock();
            try {
                value = 0;
            } finally {
                lock.unlockWrite(stamp);
            }
        }
    }
    
    /**
     * Implementation 5: Using volatile (NOT thread-safe for compound operations)
     * This is included to demonstrate what NOT to do
     */
    static class VolatileCounter implements Counter {
        private volatile int value = 0;
        
        @Override
        public void increment() {
            value++; // NOT atomic!
        }
        
        @Override
        public void decrement() {
            value--; // NOT atomic!
        }
        
        @Override
        public int getValue() {
            return value;
        }
        
        @Override
        public void reset() {
            value = 0;
        }
    }
    
    /**
     * Advanced implementation: Bounded counter with bounds checking
     */
    static class BoundedCounter implements Counter {
        private final int minValue;
        private final int maxValue;
        private final java.util.concurrent.atomic.AtomicInteger value;
        
        public BoundedCounter(int minValue, int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.value = new java.util.concurrent.atomic.AtomicInteger(minValue);
        }
        
        @Override
        public void increment() {
            int current;
            do {
                current = value.get();
                if (current >= maxValue) {
                    return; // Cannot increment further
                }
            } while (!value.compareAndSet(current, current + 1));
        }
        
        @Override
        public void decrement() {
            int current;
            do {
                current = value.get();
                if (current <= minValue) {
                    return; // Cannot decrement further
                }
            } while (!value.compareAndSet(current, current - 1));
        }
        
        @Override
        public int getValue() {
            return value.get();
        }
        
        @Override
        public void reset() {
            value.set(minValue);
        }
        
        public boolean isAtMin() {
            return value.get() == minValue;
        }
        
        public boolean isAtMax() {
            return value.get() == maxValue;
        }
    }
    
    /**
     * Advanced implementation: Statistics counter with metrics
     */
    static class StatisticsCounter implements Counter {
        private final java.util.concurrent.atomic.AtomicInteger value = new java.util.concurrent.atomic.AtomicInteger(0);
        private final java.util.concurrent.atomic.AtomicLong totalIncrements = new java.util.concurrent.atomic.AtomicLong(0);
        private final java.util.concurrent.atomic.AtomicLong totalDecrements = new java.util.concurrent.atomic.AtomicLong(0);
        private final java.util.concurrent.atomic.AtomicInteger peakValue = new java.util.concurrent.atomic.AtomicInteger(0);
        private final java.util.concurrent.atomic.AtomicLong sumOfValues = new java.util.concurrent.atomic.AtomicLong(0);
        private final java.util.concurrent.atomic.AtomicLong operationCount = new java.util.concurrent.atomic.AtomicLong(0);
        
        @Override
        public void increment() {
            int newValue = value.incrementAndGet();
            totalIncrements.incrementAndGet();
            updateStatistics(newValue);
        }
        
        @Override
        public void decrement() {
            int newValue = value.decrementAndGet();
            totalDecrements.incrementAndGet();
            updateStatistics(newValue);
        }
        
        @Override
        public int getValue() {
            return value.get();
        }
        
        @Override
        public void reset() {
            value.set(0);
            totalIncrements.set(0);
            totalDecrements.set(0);
            peakValue.set(0);
            sumOfValues.set(0);
            operationCount.set(0);
        }
        
        private void updateStatistics(int newValue) {
            // Update peak value
            int currentPeak;
            do {
                currentPeak = peakValue.get();
                if (newValue <= currentPeak) break;
            } while (!peakValue.compareAndSet(currentPeak, newValue));
            
            // Update sum and count for average calculation
            sumOfValues.addAndGet(newValue);
            operationCount.incrementAndGet();
        }
        
        public long getTotalIncrements() {
            return totalIncrements.get();
        }
        
        public long getTotalDecrements() {
            return totalDecrements.get();
        }
        
        public int getPeakValue() {
            return peakValue.get();
        }
        
        public double getAverageValue() {
            long count = operationCount.get();
            return count == 0 ? 0.0 : (double) sumOfValues.get() / count;
        }
    }
    
    /**
     * Interview Questions to Ask:
     * 
     * 1. What are the different ways to implement thread safety in Java?
     * 2. What is the difference between synchronized and ReentrantLock?
     * 3. When would you use AtomicInteger vs synchronized?
     * 4. What is the performance impact of different synchronization mechanisms?
     * 5. How would you handle overflow/underflow in a counter?
     * 6. What if you need to implement a bounded counter?
     * 7. How would you implement a counter with statistics?
     * 8. What if you need to implement a distributed counter?
     * 9. How would you handle deadlocks in your implementation?
     * 10. What if you need to implement a counter with TTL?
     * 11. How would you implement a counter that can be reset to a specific value?
     * 12. What if you need to implement a counter with different increment/decrement values?
     * 13. How would you implement a counter that can be shared across JVMs?
     * 14. What if you need to implement a counter with priority-based operations?
     * 15. How would you implement a counter that can be paused/resumed?
     */
}
