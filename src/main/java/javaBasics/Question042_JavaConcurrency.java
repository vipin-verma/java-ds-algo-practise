/**
 * Question 42: Java Concurrency
 * 
 * This file contains Concurrency interview questions covering:
 * - Thread Basics and Lifecycle
 * - Synchronization and Locks
 * - Thread Communication
 * - Concurrent Collections
 * - Best Practices
 */
public class Question042_JavaConcurrency {
    
    public static void main(String[] args) {
        System.out.println("=== Java Concurrency - Interview Questions ===\n");
        
        demonstrateThreadBasics();
        demonstrateSynchronization();
        demonstrateThreadCommunication();
        demonstrateConcurrentCollections();
        
        System.out.println("\n=== Java Concurrency Completed! ===");
    }
    
    private static void demonstrateThreadBasics() {
        System.out.println("1. THREAD BASICS AND LIFECYCLE:\n");
        
        // Q1: What are Threads in Java?
        System.out.println("Q1: What are Threads in Java?");
        System.out.println("    Lightweight sub-processes that run concurrently");
        System.out.println("    Share the same memory space as the parent process");
        System.out.println("    Enable concurrent execution of code\n");
        
        // Q2: What are the different ways to create Threads?
        System.out.println("Q2: What are the different ways to create Threads?");
        System.out.println("    - Extending Thread class");
        System.out.println("    - Implementing Runnable interface");
        System.out.println("    - Using lambda expressions");
        System.out.println("    - Using ExecutorService\n");
        
        // Demonstrate thread creation
        System.out.println("Example: Thread Creation and Lifecycle");
        
        // Method 1: Extending Thread
        MyThread myThread = new MyThread("CustomThread");
        myThread.start();
        
        // Method 2: Implementing Runnable
        Thread runnableThread = new Thread(new MyRunnable(), "RunnableThread");
        runnableThread.start();
        
        // Method 3: Lambda expression
        Thread lambdaThread = new Thread(() -> {
            System.out.println("      Lambda thread running: " + Thread.currentThread().getName());
        }, "LambdaThread");
        lambdaThread.start();
        
        // Method 4: Anonymous class
        Thread anonymousThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("      Anonymous thread running: " + Thread.currentThread().getName());
            }
        }, "AnonymousThread");
        anonymousThread.start();
        
        // Wait for threads to complete
        try {
            myThread.join();
            runnableThread.join();
            lambdaThread.join();
            anonymousThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Demonstrate thread states
        System.out.println("    Thread States Example:");
        Thread stateThread = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "StateThread");
        
        System.out.println("      Initial state: " + stateThread.getState());
        stateThread.start();
        System.out.println("      After start: " + stateThread.getState());
        
        try {
            stateThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("      After completion: " + stateThread.getState());
    }
    
    private static void demonstrateSynchronization() {
        System.out.println("\n2. SYNCHRONIZATION AND LOCKS:\n");
        
        // Q3: What is Synchronization in Java?
        System.out.println("Q3: What is Synchronization in Java?");
        System.out.println("    Mechanism to control access to shared resources");
        System.out.println("    Prevents race conditions and data inconsistency");
        System.out.println("    Ensures thread safety\n");
        
        // Q4: What are the different synchronization mechanisms?
        System.out.println("Q4: What are the different synchronization mechanisms?");
        System.out.println("    - synchronized methods");
        System.out.println("    - synchronized blocks");
        System.out.println("    - volatile keyword");
        System.out.println("    - Lock interface implementations\n");
        
        // Demonstrate synchronization
        System.out.println("Example: Synchronization Mechanisms");
        
        // Shared counter without synchronization
        Counter unsafeCounter = new UnsafeCounter();
        Counter safeCounter = new SafeCounter();
        
        // Create multiple threads to increment counters
        Thread[] unsafeThreads = new Thread[5];
        Thread[] safeThreads = new Thread[5];
        
        for (int i = 0; i < 5; i++) {
            unsafeThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    unsafeCounter.increment();
                }
            }, "UnsafeThread-" + i);
            
            safeThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    safeCounter.increment();
                }
            }, "SafeThread-" + i);
        }
        
        // Start unsafe threads
        for (Thread t : unsafeThreads) {
            t.start();
        }
        
        // Start safe threads
        for (Thread t : safeThreads) {
            t.start();
        }
        
        // Wait for completion
        try {
            for (Thread t : unsafeThreads) {
                t.join();
            }
            for (Thread t : safeThreads) {
                t.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("      Unsafe counter result: " + unsafeCounter.getCount());
        System.out.println("      Safe counter result: " + safeCounter.getCount());
        System.out.println("      Expected result: 5000");
        
        // Demonstrate different lock types
        System.out.println("    Different Lock Types:");
        
        ReentrantLockExample lockExample = new ReentrantLockExample();
        lockExample.demonstrateLock();
        
        ReadWriteLockExample rwLockExample = new ReadWriteLockExample();
        rwLockExample.demonstrateReadWriteLock();
    }
    
    private static void demonstrateThreadCommunication() {
        System.out.println("\n3. THREAD COMMUNICATION:\n");
        
        // Q5: How do threads communicate with each other?
        System.out.println("Q5: How do threads communicate with each other?");
        System.out.println("    - wait() and notify() methods");
        System.out.println("    - BlockingQueue");
        System.out.println("    - CountDownLatch");
        System.out.println("    - CyclicBarrier\n");
        
        // Q6: What is the difference between wait() and sleep()?
        System.out.println("Q6: What is the difference between wait() and sleep()?");
        System.out.println("    wait(): Releases lock, can be interrupted");
        System.out.println("    sleep(): Keeps lock, can be interrupted");
        System.out.println("    wait(): Must be called from synchronized context\n");
        
        // Demonstrate thread communication
        System.out.println("Example: Thread Communication");
        
        // Producer-Consumer with wait/notify
        MessageQueue messageQueue = new MessageQueue(3);
        
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    messageQueue.produce("Message " + i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }, "Producer");
        
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    String message = messageQueue.consume();
                    System.out.println("      Consumed: " + message);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }, "Consumer");
        
        producer.start();
        consumer.start();
        
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Demonstrate CountDownLatch
        System.out.println("    CountDownLatch Example:");
        CountDownLatch latch = new CountDownLatch(3);
        
        for (int i = 0; i < 3; i++) {
            final int workerId = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1000 + workerId * 500);
                    System.out.println("      Worker " + workerId + " completed");
                    latch.countDown();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Worker-" + i).start();
        }
        
        try {
            latch.await();
            System.out.println("      All workers completed!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void demonstrateConcurrentCollections() {
        System.out.println("\n4. CONCURRENT COLLECTIONS:\n");
        
        // Q7: What are Concurrent Collections?
        System.out.println("Q7: What are Concurrent Collections?");
        System.out.println("    Thread-safe collections designed for concurrent access");
        System.out.println("    Provide better performance than synchronized collections");
        System.out.println("    Handle concurrent modifications automatically\n");
        
        // Q8: What are the main Concurrent Collections?
        System.out.println("Q8: What are the main Concurrent Collections?");
        System.out.println("    - ConcurrentHashMap");
        System.out.println("    - CopyOnWriteArrayList");
        System.out.println("    - BlockingQueue implementations");
        System.out.println("    - ConcurrentLinkedQueue\n");
        
        // Demonstrate concurrent collections
        System.out.println("Example: Concurrent Collections");
        
        // ConcurrentHashMap
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        
        Thread[] mapWriters = new Thread[3];
        for (int i = 0; i < 3; i++) {
            final int writerId = i;
            mapWriters[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    String key = "key-" + writerId + "-" + j;
                    concurrentMap.put(key, j);
                }
            }, "MapWriter-" + i);
        }
        
        for (Thread t : mapWriters) {
            t.start();
        }
        
        try {
            for (Thread t : mapWriters) {
                t.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("      ConcurrentHashMap size: " + concurrentMap.size());
        
        // CopyOnWriteArrayList
        CopyOnWriteArrayList<String> copyOnWriteList = new CopyOnWriteArrayList<>();
        
        Thread[] listWriters = new Thread[2];
        for (int i = 0; i < 2; i++) {
            final int writerId = i;
            listWriters[i] = new Thread(() -> {
                for (int j = 0; j < 50; j++) {
                    copyOnWriteList.add("item-" + writerId + "-" + j);
                }
            }, "ListWriter-" + i);
        }
        
        for (Thread t : listWriters) {
            t.start();
        }
        
        try {
            for (Thread t : listWriters) {
                t.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("      CopyOnWriteArrayList size: " + copyOnWriteList.size());
        
        // BlockingQueue
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(5);
        
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String item = "Item-" + i;
                    blockingQueue.put(item);
                    System.out.println("      Produced: " + item);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "QueueProducer");
        
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String item = blockingQueue.take();
                    System.out.println("      Consumed: " + item);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "QueueConsumer");
        
        producer.start();
        consumer.start();
        
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // ===== HELPER CLASSES =====
    
    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }
        
        @Override
        public void run() {
            System.out.println("      Custom thread running: " + getName());
        }
    }
    
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("      Runnable thread running: " + Thread.currentThread().getName());
        }
    }
    
    static class UnsafeCounter {
        private int count = 0;
        
        public void increment() {
            count++;
        }
        
        public int getCount() {
            return count;
        }
    }
    
    static class SafeCounter {
        private int count = 0;
        
        public synchronized void increment() {
            count++;
        }
        
        public synchronized int getCount() {
            return count;
        }
    }
    
    static class ReentrantLockExample {
        private final ReentrantLock lock = new ReentrantLock();
        private int count = 0;
        
        public void demonstrateLock() {
            lock.lock();
            try {
                count++;
                System.out.println("      ReentrantLock: Count incremented to " + count);
            } finally {
                lock.unlock();
            }
        }
    }
    
    static class ReadWriteLockExample {
        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        private final Lock readLock = lock.readLock();
        private final Lock writeLock = lock.writeLock();
        private String data = "Initial Data";
        
        public void demonstrateReadWriteLock() {
            // Multiple readers can read simultaneously
            for (int i = 0; i < 3; i++) {
                new Thread(() -> {
                    readLock.lock();
                    try {
                        System.out.println("      Reader " + Thread.currentThread().getName() + " reads: " + data);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        readLock.unlock();
                    }
                }, "Reader-" + i).start();
            }
            
            // Writer waits for all readers to finish
            new Thread(() -> {
                writeLock.lock();
                try {
                    data = "Updated Data";
                    System.out.println("      Writer updated data to: " + data);
                } finally {
                    writeLock.unlock();
                }
            }, "Writer").start();
        }
    }
    
    static class MessageQueue {
        private final Queue<String> queue;
        private final int maxSize;
        
        public MessageQueue(int maxSize) {
            this.maxSize = maxSize;
            this.queue = new LinkedList<>();
        }
        
        public synchronized void produce(String message) throws InterruptedException {
            while (queue.size() >= maxSize) {
                wait();
            }
            queue.offer(message);
            System.out.println("      Produced: " + message);
            notify();
        }
        
        public synchronized String consume() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            String message = queue.poll();
            notify();
            return message;
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA CONCURRENCY:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are Threads in Java?
 * 2. What is the difference between Process and Thread?
 * 3. What are the different ways to create Threads?
 * 4. What is the Thread lifecycle?
 * 5. What are Thread states?
 * 6. What is the difference between start() and run()?
 * 7. What is the join() method?
 * 8. What is the sleep() method?
 * 9. What is the yield() method?
 * 10. What is the interrupt() method?
 * 11. What is Synchronization?
 * 12. What are race conditions?
 * 13. What is the synchronized keyword?
 * 14. What is the volatile keyword?
 * 15. What is thread safety?
 * 16. What is the difference between synchronized method and block?
 * 17. What is the wait() method?
 * 18. What is the notify() method?
 * 19. What is the notifyAll() method?
 * 20. What is the difference between wait() and sleep()?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. What are the different synchronization mechanisms?
 * 22. What is the Lock interface?
 * 23. What is ReentrantLock?
 * 24. What is ReadWriteLock?
 * 25. What is the difference between Lock and synchronized?
 * 26. What are Concurrent Collections?
 * 27. What is ConcurrentHashMap?
 * 28. What is CopyOnWriteArrayList?
 * 29. What is BlockingQueue?
 * 30. What is the difference between HashMap and ConcurrentHashMap?
 * 31. What is the difference between ArrayList and CopyOnWriteArrayList?
 * 32. What is ThreadLocal?
 * 33. What is InheritableThreadLocal?
 * 34. What is the Executor framework?
 * 35. What is ExecutorService?
 * 36. What is ThreadPoolExecutor?
 * 37. What is ScheduledExecutorService?
 * 38. What is Future?
 * 39. What is CompletableFuture?
 * 40. What is the difference between Runnable and Callable?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you implement custom ThreadPool?
 * 42. How do you handle thread exceptions?
 * 43. How do you implement thread communication?
 * 44. How do you use CountDownLatch?
 * 45. How do you use CyclicBarrier?
 * 46. How do you use Semaphore?
 * 47. How do you use Phaser?
 * 48. How do you implement producer-consumer pattern?
 * 49. How do you implement reader-writer pattern?
 * 50. How do you implement dining philosophers problem?
 * 51. How do you implement sleeping barber problem?
 * 52. How do you handle deadlocks?
 * 53. How do you detect deadlocks?
 * 54. How do you prevent deadlocks?
 * 55. How do you implement custom locks?
 * 56. How do you implement custom concurrent collections?
 * 57. How do you optimize thread performance?
 * 58. How do you handle thread starvation?
 * 59. How do you implement thread scheduling?
 * 60. How do you implement thread monitoring?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you design concurrent architectures?
 * 62. How do you implement lock-free algorithms?
 * 63. How do you implement wait-free algorithms?
 * 64. How do you implement non-blocking algorithms?
 * 65. How do you implement concurrent data structures?
 * 66. How do you implement concurrent algorithms?
 * 67. How do you implement concurrent design patterns?
 * 68. How do you implement concurrent testing?
 * 69. How do you implement concurrent debugging?
 * 70. How do you implement concurrent profiling?
 * 71. How do you implement concurrent monitoring?
 * 72. How do you implement concurrent logging?
 * 73. How do you implement concurrent error handling?
 * 74. How do you implement concurrent recovery?
 * 75. How do you implement concurrent scaling?
 * 76. How do you implement concurrent optimization?
 * 77. How do you implement concurrent security?
 * 78. How do you implement concurrent networking?
 * 79. How do you implement concurrent databases?
 * 80. How do you implement concurrent messaging?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a concurrent web server?
 * 82. How would you implement concurrent request handling?
 * 83. How would you design concurrent database connections?
 * 84. How would you implement concurrent caching?
 * 85. How would you design concurrent message processing?
 * 86. How would you implement concurrent event handling?
 * 87. How would you design concurrent API gateways?
 * 88. How would you implement concurrent load balancing?
 * 89. How would you design concurrent microservices?
 * 90. How would you implement concurrent data pipelines?
 * 91. How would you design concurrent analytics systems?
 * 92. How would you implement concurrent machine learning?
 * 93. How would you design concurrent recommendation engines?
 * 94. How would you implement concurrent search systems?
 * 95. How would you design concurrent gaming servers?
 * 96. How would you implement concurrent trading systems?
 * 97. How would you design concurrent social media platforms?
 * 98. How would you implement concurrent e-commerce systems?
 * 99. How would you design concurrent healthcare systems?
 * 100. How would you implement concurrent autonomous systems?
 */
