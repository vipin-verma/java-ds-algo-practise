import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

/**
 * Advanced Multithreading Operations - Comprehensive Guide
 * 
 * This class demonstrates various advanced multithreading techniques
 * commonly asked in Java interviews for experienced developers.
 * 
 * Topics covered:
 * - Thread creation and management
 * - Synchronization mechanisms
 * - Thread pools and executors
 * - Concurrent collections
 * - Advanced concurrency patterns
 * - Performance analysis and optimization
 */
public class Question016_AdvancedMultithreadingOperations {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Multithreading Operations - Complete Guide ===\n");
        
        demonstrateThreadBasics();
        demonstrateSynchronizationMechanisms();
        demonstrateThreadPools();
        demonstrateConcurrentCollections();
        demonstrateAdvancedPatterns();
        demonstratePerformanceAnalysis();
        
        System.out.println("\n=== Advanced Multithreading Operations Completed! ===");
    }
    
    // ===== THREAD BASICS =====
    
    /**
     * Custom thread class extending Thread
     */
    static class CustomThread extends Thread {
        private final String threadName;
        private final int sleepTime;
        
        public CustomThread(String threadName, int sleepTime) {
            this.threadName = threadName;
            this.sleepTime = sleepTime;
        }
        
        @Override
        public void run() {
            try {
                System.out.println("  " + threadName + " started");
                Thread.sleep(sleepTime);
                System.out.println("  " + threadName + " completed");
            } catch (InterruptedException e) {
                System.out.println("  " + threadName + " interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Custom runnable class implementing Runnable
     */
    static class CustomRunnable implements Runnable {
        private final String taskName;
        private final int sleepTime;
        
        public CustomRunnable(String taskName, int sleepTime) {
            this.taskName = taskName;
            this.sleepTime = sleepTime;
        }
        
        @Override
        public void run() {
            try {
                System.out.println("  " + taskName + " started");
                Thread.sleep(sleepTime);
                System.out.println("  " + taskName + " completed");
            } catch (InterruptedException e) {
                System.out.println("  " + taskName + " interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Callable task that returns a result
     */
    static class CallableTask implements Callable<String> {
        private final String taskName;
        private final int sleepTime;
        
        public CallableTask(String taskName, int sleepTime) {
            this.taskName = taskName;
            this.sleepTime = sleepTime;
        }
        
        @Override
        public String call() throws Exception {
            System.out.println("  " + taskName + " started");
            Thread.sleep(sleepTime);
            System.out.println("  " + taskName + " completed");
            return taskName + " result";
        }
    }
    
    // ===== SYNCHRONIZATION MECHANISMS =====
    
    /**
     * Shared resource class for synchronization demonstration
     */
    static class SharedResource {
        private int counter = 0;
        private final Object lock = new Object();
        private final ReentrantLock reentrantLock = new ReentrantLock();
        private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final AtomicInteger atomicCounter = new AtomicInteger(0);
        
        // Synchronized method
        public synchronized void incrementSynchronized() {
            counter++;
        }
        
        // Synchronized block
        public void incrementSynchronizedBlock() {
            synchronized (lock) {
                counter++;
            }
        }
        
        // ReentrantLock
        public void incrementReentrantLock() {
            reentrantLock.lock();
            try {
                counter++;
            } finally {
                reentrantLock.unlock();
            }
        }
        
        // ReadWriteLock
        public int getCounterReadWriteLock() {
            readWriteLock.readLock().lock();
            try {
                return counter;
            } finally {
                readWriteLock.readLock().unlock();
            }
        }
        
        public void incrementReadWriteLock() {
            readWriteLock.writeLock().lock();
            try {
                counter++;
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
        
        // Atomic operation
        public void incrementAtomic() {
            atomicCounter.incrementAndGet();
        }
        
        public int getCounter() {
            return counter;
        }
        
        public int getAtomicCounter() {
            return atomicCounter.get();
        }
    }
    
    /**
     * Producer-Consumer pattern with BlockingQueue
     */
    static class ProducerConsumer {
        private final BlockingQueue<Integer> queue;
        private final int maxSize;
        private volatile boolean running = true;
        
        public ProducerConsumer(int maxSize) {
            this.maxSize = maxSize;
            this.queue = new ArrayBlockingQueue<>(maxSize);
        }
        
        public void start() {
            Thread producer = new Thread(this::produce);
            Thread consumer = new Thread(this::consume);
            
            producer.start();
            consumer.start();
            
            try {
                Thread.sleep(5000); // Run for 5 seconds
                running = false;
                producer.join();
                consumer.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        private void produce() {
            try {
                while (running) {
                    int item = (int) (Math.random() * 100);
                    queue.put(item);
                    System.out.println("  Produced: " + item);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        private void consume() {
            try {
                while (running) {
                    Integer item = queue.take();
                    System.out.println("  Consumed: " + item);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    // ===== THREAD POOLS =====
    
    /**
     * Thread pool executor demonstration
     */
    static class ThreadPoolDemo {
        private final ExecutorService executor;
        
        public ThreadPoolDemo(int corePoolSize, int maxPoolSize, int queueCapacity) {
            executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueCapacity),
                new ThreadPoolExecutor.CallerRunsPolicy()
            );
        }
        
        public void executeTasks(int numTasks) {
            List<Future<String>> futures = new ArrayList<>();
            
            for (int i = 0; i < numTasks; i++) {
                CallableTask task = new CallableTask("Task-" + i, 1000);
                Future<String> future = executor.submit(task);
                futures.add(future);
            }
            
            // Wait for all tasks to complete
            for (Future<String> future : futures) {
                try {
                    String result = future.get();
                    System.out.println("  Task completed with result: " + result);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("  Task failed: " + e.getMessage());
                }
            }
        }
        
        public void shutdown() {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Scheduled executor demonstration
     */
    static class ScheduledExecutorDemo {
        private final ScheduledExecutorService scheduler;
        
        public ScheduledExecutorDemo() {
            this.scheduler = Executors.newScheduledThreadPool(2);
        }
        
        public void scheduleTasks() {
            // Schedule task to run after 1 second
            scheduler.schedule(() -> System.out.println("  Delayed task executed"), 1, TimeUnit.SECONDS);
            
            // Schedule task to run every 2 seconds
            scheduler.scheduleAtFixedRate(() -> System.out.println("  Fixed rate task executed"), 2, 2, TimeUnit.SECONDS);
            
            // Schedule task to run every 3 seconds with delay
            scheduler.scheduleWithFixedDelay(() -> System.out.println("  Fixed delay task executed"), 3, 3, TimeUnit.SECONDS);
            
            try {
                Thread.sleep(10000); // Run for 10 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            scheduler.shutdown();
        }
    }
    
    // ===== CONCURRENT COLLECTIONS =====
    
    /**
     * Concurrent collections demonstration
     */
    static class ConcurrentCollectionsDemo {
        private final ConcurrentHashMap<String, Integer> concurrentMap;
        private final CopyOnWriteArrayList<String> copyOnWriteList;
        private final ConcurrentLinkedQueue<String> concurrentQueue;
        private final BlockingDeque<String> blockingDeque;
        
        public ConcurrentCollectionsDemo() {
            this.concurrentMap = new ConcurrentHashMap<>();
            this.copyOnWriteList = new CopyOnWriteArrayList<>();
            this.concurrentQueue = new ConcurrentLinkedQueue<>();
            this.blockingDeque = new LinkedBlockingDeque<>();
        }
        
        public void demonstrateOperations() {
            // ConcurrentHashMap operations
            concurrentMap.put("key1", 1);
            concurrentMap.putIfAbsent("key1", 2);
            concurrentMap.computeIfPresent("key1", (k, v) -> v + 10);
            
            // CopyOnWriteArrayList operations
            copyOnWriteList.add("item1");
            copyOnWriteList.add("item2");
            
            // ConcurrentLinkedQueue operations
            concurrentQueue.offer("task1");
            concurrentQueue.offer("task2");
            
            // BlockingDeque operations
            try {
                blockingDeque.putFirst("first");
                blockingDeque.putLast("last");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            System.out.println("  ConcurrentMap: " + concurrentMap);
            System.out.println("  CopyOnWriteList: " + copyOnWriteList);
            System.out.println("  ConcurrentQueue: " + concurrentQueue);
            System.out.println("  BlockingDeque: " + blockingDeque);
        }
    }
    
    // ===== ADVANCED PATTERNS =====
    
    /**
     * CountDownLatch demonstration
     */
    static class CountDownLatchDemo {
        private final CountDownLatch latch;
        private final int numWorkers;
        
        public CountDownLatchDemo(int numWorkers) {
            this.numWorkers = numWorkers;
            this.latch = new CountDownLatch(numWorkers);
        }
        
        public void startWork() {
            System.out.println("  Starting " + numWorkers + " workers...");
            
            for (int i = 0; i < numWorkers; i++) {
                final int workerId = i;
                new Thread(() -> {
                    try {
                        System.out.println("    Worker " + workerId + " started");
                        Thread.sleep(1000 + (int) (Math.random() * 2000));
                        System.out.println("    Worker " + workerId + " completed");
                        latch.countDown();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }
            
            try {
                latch.await();
                System.out.println("  All workers completed!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * CyclicBarrier demonstration
     */
    static class CyclicBarrierDemo {
        private final CyclicBarrier barrier;
        private final int numParties;
        
        public CyclicBarrierDemo(int numParties) {
            this.numParties = numParties;
            this.barrier = new CyclicBarrier(numParties, () -> 
                System.out.println("  All parties reached barrier, executing barrier action!")
            );
        }
        
        public void startParties() {
            System.out.println("  Starting " + numParties + " parties...");
            
            for (int i = 0; i < numParties; i++) {
                final int partyId = i;
                new Thread(() -> {
                    try {
                        System.out.println("    Party " + partyId + " started");
                        Thread.sleep(1000 + (int) (Math.random() * 2000));
                        System.out.println("    Party " + partyId + " waiting at barrier");
                        barrier.await();
                        System.out.println("    Party " + partyId + " crossed barrier");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }
        }
    }
    
    /**
     * Phaser demonstration
     */
    static class PhaserDemo {
        private final Phaser phaser;
        private final int numPhases;
        
        public PhaserDemo(int numPhases) {
            this.numPhases = numPhases;
            this.phaser = new Phaser(1); // Main thread
        }
        
        public void startPhases() {
            System.out.println("  Starting " + numPhases + " phases...");
            
            for (int phase = 0; phase < numPhases; phase++) {
                System.out.println("  Phase " + phase + " starting...");
                
                // Register workers for this phase
                for (int i = 0; i < 3; i++) {
                    final int workerId = i;
                    phaser.register();
                    new Thread(() -> {
                        try {
                            System.out.println("    Worker " + workerId + " in phase " + phase);
                            Thread.sleep(1000);
                            phaser.arriveAndAwaitAdvance();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }).start();
                }
                
                // Wait for all workers to complete this phase
                phaser.arriveAndAwaitAdvance();
                System.out.println("  Phase " + phase + " completed!");
            }
        }
    }
    
    // ===== DEMONSTRATION METHODS =====
    
    private static void demonstrateThreadBasics() {
        System.out.println("1. THREAD BASICS:\n");
        
        System.out.println("Extending Thread class:");
        CustomThread thread1 = new CustomThread("CustomThread-1", 1000);
        CustomThread thread2 = new CustomThread("CustomThread-2", 1500);
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\nImplementing Runnable:");
        Thread runnableThread1 = new Thread(new CustomRunnable("Runnable-1", 1000));
        Thread runnableThread2 = new Thread(new CustomRunnable("Runnable-2", 1500));
        
        runnableThread1.start();
        runnableThread2.start();
        
        try {
            runnableThread1.join();
            runnableThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println();
    }
    
    private static void demonstrateSynchronizationMechanisms() {
        System.out.println("2. SYNCHRONIZATION MECHANISMS:\n");
        
        SharedResource resource = new SharedResource();
        
        // Test different synchronization mechanisms
        System.out.println("Testing synchronization mechanisms with 5 threads each:");
        
        // Synchronized method
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    resource.incrementSynchronized();
                }
            }).start();
        }
        
        // Wait for threads to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("  Final counter value: " + resource.getCounter());
        
        System.out.println("\nProducer-Consumer pattern:");
        ProducerConsumer pc = new ProducerConsumer(5);
        pc.start();
        
        System.out.println();
    }
    
    private static void demonstrateThreadPools() {
        System.out.println("3. THREAD POOLS:\n");
        
        System.out.println("ThreadPoolExecutor demonstration:");
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo(2, 4, 10);
        threadPoolDemo.executeTasks(6);
        threadPoolDemo.shutdown();
        
        System.out.println("\nScheduledExecutor demonstration:");
        ScheduledExecutorDemo scheduledDemo = new ScheduledExecutorDemo();
        scheduledDemo.scheduleTasks();
        
        System.out.println();
    }
    
    private static void demonstrateConcurrentCollections() {
        System.out.println("4. CONCURRENT COLLECTIONS:\n");
        
        ConcurrentCollectionsDemo collectionsDemo = new ConcurrentCollectionsDemo();
        collectionsDemo.demonstrateOperations();
        
        System.out.println();
    }
    
    private static void demonstrateAdvancedPatterns() {
        System.out.println("5. ADVANCED PATTERNS:\n");
        
        System.out.println("CountDownLatch demonstration:");
        CountDownLatchDemo latchDemo = new CountDownLatchDemo(3);
        latchDemo.startWork();
        
        System.out.println("\nCyclicBarrier demonstration:");
        CyclicBarrierDemo barrierDemo = new CyclicBarrierDemo(3);
        barrierDemo.startParties();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\nPhaser demonstration:");
        PhaserDemo phaserDemo = new PhaserDemo(2);
        phaserDemo.startPhases();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println();
    }
    
    private static void demonstratePerformanceAnalysis() {
        System.out.println("6. PERFORMANCE ANALYSIS:\n");
        
        // Performance comparison for different synchronization mechanisms
        SharedResource resource = new SharedResource();
        int numThreads = 10;
        int operationsPerThread = 10000;
        
        // Test synchronized method
        long startTime = System.nanoTime();
        CountDownLatch latch1 = new CountDownLatch(numThreads);
        
        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    resource.incrementSynchronized();
                }
                latch1.countDown();
            }).start();
        }
        
        try {
            latch1.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        long synchronizedTime = System.nanoTime() - startTime;
        
        // Test atomic operations
        startTime = System.nanoTime();
        CountDownLatch latch2 = new CountDownLatch(numThreads);
        
        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    resource.incrementAtomic();
                }
                latch2.countDown();
            }).start();
        }
        
        try {
            latch2.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        long atomicTime = System.nanoTime() - startTime;
        
        System.out.println("Performance comparison:");
        System.out.println("  Synchronized method: " + synchronizedTime + " ns");
        System.out.println("  Atomic operations: " + atomicTime + " ns");
        System.out.println("  Speedup: " + (double) synchronizedTime / atomicTime + "x");
        
        System.out.println();
    }
}

/**
 * INTERVIEW QUESTIONS COVERED:
 * 
 * THEORETICAL:
 * 1. Explain the difference between Thread and Runnable.
 * 2. What are the advantages of using thread pools?
 * 3. How does synchronization work in Java?
 * 4. Explain the difference between synchronized and ReentrantLock.
 * 5. What are concurrent collections and when to use them?
 * 6. How do CountDownLatch and CyclicBarrier differ?
 * 
 * PRACTICAL:
 * 1. Implement producer-consumer pattern using BlockingQueue.
 * 2. Create custom thread pools with specific configurations.
 * 3. Use different synchronization mechanisms for shared resources.
 * 4. Implement scheduled tasks using ScheduledExecutorService.
 * 5. Use concurrent collections for thread-safe operations.
 * 
 * TRICKY SCENARIOS:
 * 1. Handle thread interruption properly.
 * 2. Avoid deadlocks in complex synchronization scenarios.
 * 3. Optimize thread pool sizes for different workloads.
 * 4. Handle exceptions in concurrent operations.
 * 
 * PERFORMANCE:
 * 1. Compare different synchronization mechanisms.
 * 2. Analyze thread pool performance characteristics.
 * 3. Optimize concurrent operations for specific use cases.
 * 4. Benchmark concurrent vs sequential operations.
 * 
 * DESIGN PATTERNS:
 * 1. Producer-Consumer pattern for task processing.
 * 2. Worker thread pattern for parallel execution.
 * 3. Barrier pattern for synchronization points.
 * 4. Thread pool pattern for resource management.
 */
