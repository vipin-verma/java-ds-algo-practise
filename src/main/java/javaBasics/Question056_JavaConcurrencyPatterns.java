/**
 * Question056: Java Concurrency Patterns
 * 
 * This file contains interview questions and demonstration code for Java Concurrency Patterns.
 * It covers various concurrency patterns, their implementation, and best practices.
 * 
 * Topics covered:
 * - Producer-Consumer Pattern
 * - Reader-Writer Pattern
 * - Worker Thread Pattern
 * - Future Pattern
 * - Actor Pattern
 * - Pipeline Pattern
 * - Fork-Join Pattern
 * - Thread Pool Pattern
 * - Barrier Pattern
 * - CountDownLatch Pattern
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class Question056_JavaConcurrencyPatterns {
    
    public static void main(String[] args) {
        System.out.println("=== Java Concurrency Patterns Demo ===\n");
        
        // Demo different concurrency patterns
        demoProducerConsumerPattern();
        demoReaderWriterPattern();
        demoWorkerThreadPattern();
        demoFuturePattern();
        demoActorPattern();
        demoPipelinePattern();
        demoForkJoinPattern();
        demoThreadPoolPattern();
        demoBarrierPattern();
        demoCountDownLatchPattern();
        
        System.out.println("\n=== All Concurrency Patterns Demo Completed ===");
    }
    
    // ===== PRODUCER-CONSUMER PATTERN =====
    
    /**
     * Producer-Consumer Pattern Implementation
     * Uses BlockingQueue for thread-safe communication
     */
    static class ProducerConsumerPattern {
        private final BlockingQueue<Integer> queue;
        private final AtomicInteger counter = new AtomicInteger(0);
        private final AtomicBoolean running = new AtomicBoolean(true);
        
        public ProducerConsumerPattern(int capacity) {
            this.queue = new ArrayBlockingQueue<>(capacity);
        }
        
        public void start() {
            // Start producer thread
            Thread producer = new Thread(() -> {
                try {
                    while (running.get()) {
                        int item = counter.incrementAndGet();
                        queue.put(item);
                        System.out.println("Producer produced: " + item);
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            
            // Start consumer thread
            Thread consumer = new Thread(() -> {
                try {
                    while (running.get()) {
                        Integer item = queue.take();
                        System.out.println("Consumer consumed: " + item);
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            
            producer.start();
            consumer.start();
            
            // Let it run for a while
            try {
                Thread.sleep(2000);
                running.set(false);
                producer.join();
                consumer.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    static void demoProducerConsumerPattern() {
        System.out.println("--- Producer-Consumer Pattern Demo ---");
        ProducerConsumerPattern pattern = new ProducerConsumerPattern(5);
        pattern.start();
    }
    
    // ===== READER-WRITER PATTERN =====
    
    /**
     * Reader-Writer Pattern Implementation
     * Multiple readers can read simultaneously, but only one writer at a time
     */
    static class ReaderWriterPattern {
        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        private final ReentrantLock readLock = (ReentrantLock) lock.readLock();
        private final ReentrantLock writeLock = (ReentrantLock) lock.writeLock();
        private String data = "Initial Data";
        private final AtomicInteger readCount = new AtomicInteger(0);
        private final AtomicInteger writeCount = new AtomicInteger(0);
        
        public String read() {
            readLock.lock();
            try {
                readCount.incrementAndGet();
                System.out.println("Reader " + Thread.currentThread().getId() + " reading: " + data);
                Thread.sleep(50);
                return data;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            } finally {
                readCount.decrementAndGet();
                readLock.unlock();
            }
        }
        
        public void write(String newData) {
            writeLock.lock();
            try {
                writeCount.incrementAndGet();
                System.out.println("Writer " + Thread.currentThread().getId() + " writing: " + newData);
                Thread.sleep(100);
                this.data = newData;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                writeCount.decrementAndGet();
                writeLock.unlock();
            }
        }
        
        public void startDemo() {
            // Start multiple readers
            for (int i = 0; i < 3; i++) {
                new Thread(() -> {
                    for (int j = 0; j < 5; j++) {
                        read();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }).start();
            }
            
            // Start a writer
            new Thread(() -> {
                for (int i = 0; i < 3; i++) {
                    write("Updated Data " + (i + 1));
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }).start();
            
            // Let it run for a while
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    static void demoReaderWriterPattern() {
        System.out.println("\n--- Reader-Writer Pattern Demo ---");
        ReaderWriterPattern pattern = new ReaderWriterPattern();
        pattern.startDemo();
    }
    
    // ===== WORKER THREAD PATTERN =====
    
    /**
     * Worker Thread Pattern Implementation
     * Multiple worker threads process tasks from a shared queue
     */
    static class WorkerThreadPattern {
        private final ExecutorService executor;
        private final BlockingQueue<Runnable> taskQueue;
        private final AtomicInteger taskCounter = new AtomicInteger(0);
        
        public WorkerThreadPattern(int workerCount) {
            this.executor = Executors.newFixedThreadPool(workerCount);
            this.taskQueue = new LinkedBlockingQueue<>();
        }
        
        public void submitTask(Runnable task) {
            taskQueue.offer(task);
        }
        
        public void start() {
            // Start worker threads
            for (int i = 0; i < 3; i++) {
                final int workerId = i;
                executor.submit(() -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            Runnable task = taskQueue.take();
                            System.out.println("Worker " + workerId + " executing task");
                            task.run();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                });
            }
            
            // Submit some tasks
            for (int i = 0; i < 10; i++) {
                final int taskId = i;
                submitTask(() -> {
                    System.out.println("Task " + taskId + " completed");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            
            // Let it run for a while
            try {
                Thread.sleep(2000);
                executor.shutdown();
                executor.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                executor.shutdownNow();
            }
        }
    }
    
    static void demoWorkerThreadPattern() {
        System.out.println("\n--- Worker Thread Pattern Demo ---");
        WorkerThreadPattern pattern = new WorkerThreadPattern(3);
        pattern.start();
    }
    
    // ===== FUTURE PATTERN =====
    
    /**
     * Future Pattern Implementation
     * Asynchronous computation with result retrieval
     */
    static class FuturePattern {
        private final ExecutorService executor = Executors.newCachedThreadPool();
        
        public CompletableFuture<String> asyncTask(String input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                    return "Processed: " + input.toUpperCase();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }, executor);
        }
        
        public CompletableFuture<Integer> asyncCalculation(int number) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(300);
                    return number * number;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }, executor);
        }
        
        public void startDemo() {
            System.out.println("Starting async tasks...");
            
            // Start multiple async tasks
            CompletableFuture<String> task1 = asyncTask("hello");
            CompletableFuture<Integer> task2 = asyncCalculation(5);
            CompletableFuture<Integer> task3 = asyncCalculation(10);
            
            // Combine results
            CompletableFuture<Void> combined = CompletableFuture.allOf(task1, task2, task3);
            
            combined.thenRun(() -> {
                try {
                    System.out.println("Task 1 result: " + task1.get());
                    System.out.println("Task 2 result: " + task2.get());
                    System.out.println("Task 3 result: " + task3.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            
            // Wait for completion
            try {
                combined.get(3, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                executor.shutdown();
            }
        }
    }
    
    static void demoFuturePattern() {
        System.out.println("\n--- Future Pattern Demo ---");
        FuturePattern pattern = new FuturePattern();
        pattern.startDemo();
    }
    
    // ===== ACTOR PATTERN =====
    
    /**
     * Actor Pattern Implementation
     * Each actor has its own mailbox and processes messages sequentially
     */
    static class ActorPattern {
        static abstract class Actor {
            private final BlockingQueue<Object> mailbox = new LinkedBlockingQueue<>();
            private final AtomicBoolean running = new AtomicBoolean(true);
            private final Thread thread;
            
            public Actor() {
                this.thread = new Thread(this::processMessages);
                this.thread.start();
            }
            
            private void processMessages() {
                while (running.get()) {
                    try {
                        Object message = mailbox.take();
                        onMessage(message);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
            
            protected abstract void onMessage(Object message);
            
            public void send(Object message) {
                mailbox.offer(message);
            }
            
            public void stop() {
                running.set(false);
                thread.interrupt();
            }
        }
        
        static class CalculatorActor extends Actor {
            @Override
            protected void onMessage(Object message) {
                if (message instanceof CalculationRequest) {
                    CalculationRequest request = (CalculationRequest) message;
                    int result = request.operation.apply(request.a, request.b);
                    System.out.println("Calculator: " + request.a + " " + request.operation + " " + request.b + " = " + result);
                }
            }
        }
        
        static class CalculationRequest {
            final int a, b;
            final Operation operation;
            
            CalculationRequest(int a, int b, Operation operation) {
                this.a = a;
                this.b = b;
                this.operation = operation;
            }
        }
        
        enum Operation {
            ADD("+", (a, b) -> a + b),
            SUBTRACT("-", (a, b) -> a - b),
            MULTIPLY("*", (a, b) -> a * b),
            DIVIDE("/", (a, b) -> a / b);
            
            private final String symbol;
            private final BinaryOperation operation;
            
            Operation(String symbol, BinaryOperation operation) {
                this.symbol = symbol;
                this.operation = operation;
            }
            
            public int apply(int a, int b) {
                return operation.apply(a, b);
            }
            
            @Override
            public String toString() {
                return symbol;
            }
        }
        
        @FunctionalInterface
        interface BinaryOperation {
            int apply(int a, int b);
        }
        
        public void startDemo() {
            CalculatorActor calculator = new CalculatorActor();
            
            // Send calculation requests
            calculator.send(new CalculationRequest(10, 5, Operation.ADD));
            calculator.send(new CalculationRequest(20, 3, Operation.MULTIPLY));
            calculator.send(new CalculationRequest(100, 4, Operation.DIVIDE));
            calculator.send(new CalculationRequest(15, 7, Operation.SUBTRACT));
            
            // Let it process
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                calculator.stop();
            }
        }
    }
    
    static void demoActorPattern() {
        System.out.println("\n--- Actor Pattern Demo ---");
        ActorPattern pattern = new ActorPattern();
        pattern.startDemo();
    }
    
    // ===== PIPELINE PATTERN =====
    
    /**
     * Pipeline Pattern Implementation
     * Data flows through a series of processing stages
     */
    static class PipelinePattern {
        static class PipelineStage<T, R> {
            private final java.util.function.Function<T, R> processor;
            private final BlockingQueue<T> inputQueue;
            private final BlockingQueue<R> outputQueue;
            private final AtomicBoolean running = new AtomicBoolean(true);
            private final Thread thread;
            
            public PipelineStage(java.util.function.Function<T, R> processor, BlockingQueue<T> inputQueue, BlockingQueue<R> outputQueue) {
                this.processor = processor;
                this.inputQueue = inputQueue;
                this.outputQueue = outputQueue;
                this.thread = new Thread(this::process);
                this.thread.start();
            }
            
            private void process() {
                while (running.get()) {
                    try {
                        T input = inputQueue.take();
                        R output = processor.apply(input);
                        outputQueue.put(output);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
            
            public void stop() {
                running.set(false);
                thread.interrupt();
            }
        }
        
        public void startDemo() {
            BlockingQueue<String> stage1Queue = new LinkedBlockingQueue<>();
            BlockingQueue<String> stage2Queue = new LinkedBlockingQueue<>();
            BlockingQueue<String> stage3Queue = new LinkedBlockingQueue<>();
            BlockingQueue<String> finalQueue = new LinkedBlockingQueue<>();
            
            // Create pipeline stages
            PipelineStage<String, String> stage1 = new PipelineStage<>(
                input -> "Stage1: " + input.toUpperCase(),
                stage1Queue, stage2Queue
            );
            
            PipelineStage<String, String> stage2 = new PipelineStage<>(
                input -> "Stage2: " + input + " [PROCESSED]",
                stage2Queue, stage3Queue
            );
            
            PipelineStage<String, String> stage3 = new PipelineStage<>(
                input -> "Stage3: " + input + " [COMPLETED]",
                stage3Queue, finalQueue
            );
            
            // Start consumer for final results
            Thread consumer = new Thread(() -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        String result = finalQueue.take();
                        System.out.println("Final Result: " + result);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            consumer.start();
            
            // Feed data into pipeline
            try {
                for (int i = 0; i < 5; i++) {
                    stage1Queue.put("item" + i);
                    Thread.sleep(100);
                }
                
                // Wait for processing
                Thread.sleep(2000);
                
                // Stop all stages
                stage1.stop();
                stage2.stop();
                stage3.stop();
                consumer.join();
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    static void demoPipelinePattern() {
        System.out.println("\n--- Pipeline Pattern Demo ---");
        PipelinePattern pattern = new PipelinePattern();
        pattern.startDemo();
    }
    
    // ===== FORK-JOIN PATTERN =====
    
    /**
     * Fork-Join Pattern Implementation
     * Recursive task decomposition and parallel execution
     */
    static class ForkJoinPattern {
        static class ArraySumTask extends RecursiveTask<Long> {
            private final int[] array;
            private final int start;
            private final int end;
            private static final int THRESHOLD = 1000;
            
            public ArraySumTask(int[] array, int start, int end) {
                this.array = array;
                this.start = start;
                this.end = end;
            }
            
            @Override
            protected Long compute() {
                int length = end - start;
                
                if (length <= THRESHOLD) {
                    // Base case: compute directly
                    long sum = 0;
                    for (int i = start; i < end; i++) {
                        sum += array[i];
                    }
                    return sum;
                } else {
                    // Fork into subtasks
                    int mid = start + length / 2;
                    ArraySumTask leftTask = new ArraySumTask(array, start, mid);
                    ArraySumTask rightTask = new ArraySumTask(array, mid, end);
                    
                    leftTask.fork();
                    rightTask.fork();
                    
                    return leftTask.join() + rightTask.join();
                }
            }
        }
        
        public void startDemo() {
            // Create a large array
            int[] array = new int[10000];
            for (int i = 0; i < array.length; i++) {
                array[i] = i + 1;
            }
            
            // Create fork-join pool
            ForkJoinPool pool = new ForkJoinPool();
            
            // Submit task
            ArraySumTask task = new ArraySumTask(array, 0, array.length);
            long result = pool.invoke(task);
            
            System.out.println("Array sum: " + result);
            System.out.println("Expected sum: " + ((long) array.length * (array.length + 1)) / 2);
            
            pool.shutdown();
        }
    }
    
    static void demoForkJoinPattern() {
        System.out.println("\n--- Fork-Join Pattern Demo ---");
        ForkJoinPattern pattern = new ForkJoinPattern();
        pattern.startDemo();
    }
    
    // ===== THREAD POOL PATTERN =====
    
    /**
     * Thread Pool Pattern Implementation
     * Reuse threads for multiple tasks to avoid thread creation overhead
     */
    static class ThreadPoolPattern {
        private final ExecutorService executor;
        private final AtomicInteger taskCounter = new AtomicInteger(0);
        
        public ThreadPoolPattern(int corePoolSize, int maxPoolSize, int queueCapacity) {
            this.executor = new ThreadPoolExecutor(
                corePoolSize, maxPoolSize, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueCapacity),
                new ThreadPoolExecutor.CallerRunsPolicy()
            );
        }
        
        public void submitTask(Runnable task) {
            executor.submit(() -> {
                int taskId = taskCounter.incrementAndGet();
                System.out.println("Task " + taskId + " started on thread: " + Thread.currentThread().getName());
                task.run();
                System.out.println("Task " + taskId + " completed on thread: " + Thread.currentThread().getName());
            });
        }
        
        public void startDemo() {
            System.out.println("Submitting tasks to thread pool...");
            
            // Submit multiple tasks
            for (int i = 0; i < 10; i++) {
                final int taskId = i;
                submitTask(() -> {
                    try {
                        Thread.sleep(200);
                        System.out.println("Task " + taskId + " processing completed");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            
            // Shutdown and wait for completion
            executor.shutdown();
            try {
                if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("All tasks completed");
                } else {
                    System.out.println("Some tasks did not complete in time");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                executor.shutdownNow();
            }
        }
    }
    
    static void demoThreadPoolPattern() {
        System.out.println("\n--- Thread Pool Pattern Demo ---");
        ThreadPoolPattern pattern = new ThreadPoolPattern(2, 4, 10);
        pattern.startDemo();
    }
    
    // ===== BARRIER PATTERN =====
    
    /**
     * Barrier Pattern Implementation
     * Multiple threads wait at a barrier until all reach it
     */
    static class BarrierPattern {
        private final CyclicBarrier barrier;
        private final AtomicInteger phase = new AtomicInteger(0);
        
        public BarrierPattern(int parties) {
            this.barrier = new CyclicBarrier(parties, () -> {
                int currentPhase = phase.incrementAndGet();
                System.out.println("=== Phase " + currentPhase + " completed ===");
            });
        }
        
        public void startWorker(String name) {
            new Thread(() -> {
                try {
                    for (int i = 0; i < 3; i++) {
                        System.out.println(name + " working on phase " + (i + 1));
                        Thread.sleep(100 + (int)(Math.random() * 200));
                        
                        System.out.println(name + " waiting at barrier for phase " + (i + 1));
                        barrier.await();
                        
                        System.out.println(name + " continuing after phase " + (i + 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        
        public void startDemo() {
            System.out.println("Starting barrier pattern with 3 workers...");
            
            startWorker("Worker-A");
            startWorker("Worker-B");
            startWorker("Worker-C");
            
            // Let it run
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    static void demoBarrierPattern() {
        System.out.println("\n--- Barrier Pattern Demo ---");
        BarrierPattern pattern = new BarrierPattern(3);
        pattern.startDemo();
    }
    
    // ===== COUNTDOWN LATCH PATTERN =====
    
    /**
     * CountDown Latch Pattern Implementation
     * Main thread waits for multiple worker threads to complete
     */
    static class CountDownLatchPattern {
        private final CountDownLatch latch;
        private final AtomicInteger completedWorkers = new AtomicInteger(0);
        
        public CountDownLatchPattern(int workerCount) {
            this.latch = new CountDownLatch(workerCount);
        }
        
        public void startWorker(String name) {
            new Thread(() -> {
                try {
                    System.out.println(name + " starting work");
                    Thread.sleep(500 + (int)(Math.random() * 1000));
                    
                    System.out.println(name + " completed work");
                    completedWorkers.incrementAndGet();
                    latch.countDown();
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
        
        public void startDemo() {
            System.out.println("Starting countdown latch pattern with 5 workers...");
            
            // Start workers
            for (int i = 0; i < 5; i++) {
                startWorker("Worker-" + (i + 1));
            }
            
            // Main thread waits for all workers
            try {
                System.out.println("Main thread waiting for all workers to complete...");
                latch.await();
                System.out.println("All workers completed! Total completed: " + completedWorkers.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    static void demoCountDownLatchPattern() {
        System.out.println("\n--- CountDown Latch Pattern Demo ---");
        CountDownLatchPattern pattern = new CountDownLatchPattern(5);
        pattern.startDemo();
    }
}

/**
 * Helper Classes for Concurrency Patterns
 */

// User Service for demonstration
class UserService {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    
    public CompletableFuture<User> getUserAsync(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
                return new User(userId, "User" + userId);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }, executor);
    }
    
    public void shutdown() {
        executor.shutdown();
    }
}

class User {
    private final int id;
    private final String name;
    
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}

// Task Service for demonstration
class TaskService {
    private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    
    public void submitTask(Runnable task) {
        taskQueue.offer(task);
    }
    
    public void start() {
        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
    }
    
    public void shutdown() {
        executor.shutdown();
    }
}

// Message Service for demonstration
class MessageService {
    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final Thread processor;
    
    public MessageService() {
        this.processor = new Thread(this::processMessages);
        this.processor.start();
    }
    
    public void sendMessage(String message) {
        messageQueue.offer(message);
    }
    
    private void processMessages() {
        while (running.get()) {
            try {
                String message = messageQueue.take();
                System.out.println("Processing message: " + message);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    public void shutdown() {
        running.set(false);
        processor.interrupt();
    }
}

// Cache Service for demonstration
class CacheService {
    private final java.util.concurrent.ConcurrentHashMap<String, Object> cache = new java.util.concurrent.ConcurrentHashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            cache.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public Object get(String key) {
        lock.readLock().lock();
        try {
            return cache.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public boolean containsKey(String key) {
        lock.readLock().lock();
        try {
            return cache.containsKey(key);
        } finally {
            lock.readLock().unlock();
        }
    }
}

// Workflow Engine for demonstration
class WorkflowEngine {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final List<WorkflowStep> steps = new ArrayList<>();
    
    public void addStep(WorkflowStep step) {
        steps.add(step);
    }
    
    public CompletableFuture<Void> executeWorkflow() {
        CompletableFuture<Void> future = CompletableFuture.completedFuture(null);
        
        for (WorkflowStep step : steps) {
            future = future.thenComposeAsync(v -> step.execute(), executor);
        }
        
        return future;
    }
    
    public void shutdown() {
        executor.shutdown();
    }
}

interface WorkflowStep {
    CompletableFuture<Void> execute();
}

class SimpleWorkflowStep implements WorkflowStep {
    private final String name;
    private final long delay;
    
    public SimpleWorkflowStep(String name, long delay) {
        this.name = name;
        this.delay = delay;
    }
    
    @Override
    public CompletableFuture<Void> execute() {
        return CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Executing step: " + name);
                Thread.sleep(delay);
                System.out.println("Completed step: " + name);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        });
    }
}

/**
 * Java Concurrency Patterns Interview Questions
 * 
 * This section contains 100 interview questions categorized by difficulty level.
 * Each question tests understanding of different concurrency patterns and their implementation.
 */

class ConcurrencyPatternsInterviewQuestions {
    
    // ===== BEGINNER LEVEL QUESTIONS =====
    
    /**
     * 1. What is the Producer-Consumer pattern and when would you use it?
     * 2. Explain the difference between synchronized and ReentrantLock.
     * 3. What is a ThreadPool and why is it useful?
     * 4. How does CountDownLatch work?
     * 5. What is the purpose of CyclicBarrier?
     * 6. Explain the concept of thread-safe collections.
     * 7. What is the difference between volatile and synchronized?
     * 8. How do you create a thread-safe singleton?
     * 9. What is the purpose of ThreadLocal?
     * 10. Explain the concept of deadlock and how to prevent it.
     * 11. What is the difference between Runnable and Callable?
     * 12. How does ExecutorService work?
     * 13. What is the purpose of Future interface?
     * 14. Explain the concept of thread starvation.
     * 15. What is the difference between notify() and notifyAll()?
     * 16. How do you implement a thread-safe counter?
     * 17. What is the purpose of ReadWriteLock?
     * 18. Explain the concept of thread confinement.
     * 19. What is the difference between interrupt() and stop()?
     * 20. How do you handle InterruptedException?
     */
    
    // ===== INTERMEDIATE LEVEL QUESTIONS =====
    
    /**
     * 21. Implement a custom ThreadPool using BlockingQueue.
     * 22. How would you implement a rate limiter using Semaphore?
     * 23. Explain the Actor model and implement a simple actor.
     * 24. How do you implement a custom ReentrantLock?
     * 25. What is the Fork-Join framework and when to use it?
     * 26. Implement a custom CountDownLatch.
     * 27. How would you implement a custom CyclicBarrier?
     * 28. Explain the concept of work stealing in Fork-Join.
     * 29. How do you implement a custom ReadWriteLock?
     * 30. What is the purpose of StampedLock and when to use it?
     * 31. Implement a thread-safe LRU cache.
     * 32. How do you implement a custom Semaphore?
     * 33. Explain the concept of thread pools with different rejection policies.
     * 34. How would you implement a custom BlockingQueue?
     * 35. What is the purpose of CompletableFuture and how to use it?
     * 36. Implement a custom ThreadFactory.
     * 37. How do you implement a custom RejectedExecutionHandler?
     * 38. Explain the concept of thread pool tuning.
     * 39. How would you implement a custom Condition?
     * 40. What is the purpose of Phaser and when to use it?
     */
    
    // ===== ADVANCED LEVEL QUESTIONS =====
    
    /**
     * 41. Implement a custom ExecutorService with priority-based task execution.
     * 42. How would you implement a distributed lock using Redis?
     * 43. Explain the concept of lock-free programming and implement a lock-free stack.
     * 44. How do you implement a custom ForkJoinPool?
     * 45. What is the purpose of VarHandle and how to use it?
     * 46. Implement a custom ThreadLocal with automatic cleanup.
     * 47. How would you implement a custom BlockingDeque?
     * 48. Explain the concept of memory barriers and their importance.
     * 49. How do you implement a custom ConcurrentHashMap?
     * 50. What is the purpose of CompletableFuture's async methods?
     * 51. Implement a custom ThreadPoolExecutor with custom queue policies.
     * 52. How would you implement a custom ReentrantReadWriteLock?
     * 53. Explain the concept of thread pool saturation and how to handle it.
     * 54. How do you implement a custom DelayQueue?
     * 55. What is the purpose of StructuredTaskScope and when to use it?
     * 56. Implement a custom ThreadGroup with custom security policies.
     * 57. How would you implement a custom PriorityBlockingQueue?
     * 58. Explain the concept of thread pool monitoring and metrics.
     * 59. How do you implement a custom TransferQueue?
     * 60. What is the purpose of ScopedValue and how to use it?
     */
    
    // ===== EXPERT LEVEL QUESTIONS =====
    
    /**
     * 61. Implement a custom ExecutorService with work-stealing capabilities.
     * 62. How would you implement a distributed semaphore using Zookeeper?
     * 63. Explain the concept of lock-free data structures and implement a lock-free queue.
     * 64. How do you implement a custom ForkJoinTask with custom splitting strategies?
     * 65. What is the purpose of MemorySegment and how to use it?
     * 66. Implement a custom ThreadLocal with hierarchical inheritance.
     * 67. How would you implement a custom ConcurrentLinkedQueue?
     * 68. Explain the concept of memory ordering and its impact on performance.
     * 69. How do you implement a custom ConcurrentSkipListMap?
     * 70. What is the purpose of Virtual Threads and how to optimize them?
     * 71. Implement a custom ThreadPoolExecutor with adaptive sizing.
     * 72. How would you implement a custom ReentrantReadWriteLock with fairness?
     * 73. Explain the concept of thread pool backpressure and how to implement it.
     * 74. How do you implement a custom SynchronousQueue?
     * 75. What is the purpose of Structured Concurrency and how to implement it?
     * 76. Implement a custom ThreadGroup with custom thread creation policies.
     * 77. How would you implement a custom PriorityBlockingQueue with custom ordering?
     * 78. Explain the concept of thread pool elasticity and how to implement it.
     * 79. How do you implement a custom TransferQueue with custom transfer policies?
     * 80. What is the purpose of ScopedValue with custom scoping rules?
     */
    
    // ===== SYSTEM DESIGN QUESTIONS =====
    
    /**
     * 81. Design a high-performance message queue system using concurrency patterns.
     * 82. How would you design a distributed task scheduler using concurrency patterns?
     * 83. Design a real-time data processing pipeline using concurrency patterns.
     * 84. How would you design a high-throughput web server using concurrency patterns?
     * 85. Design a distributed cache system using concurrency patterns.
     * 86. How would you design a workflow engine using concurrency patterns?
     * 87. Design a real-time analytics system using concurrency patterns.
     * 88. How would you design a high-frequency trading system using concurrency patterns?
     * 89. Design a distributed log processing system using concurrency patterns.
     * 90. How would you design a real-time recommendation engine using concurrency patterns?
     * 91. Design a high-performance database connection pool using concurrency patterns.
     * 92. How would you design a distributed event streaming system using concurrency patterns?
     * 93. Design a real-time monitoring system using concurrency patterns.
     * 94. How would you design a high-throughput API gateway using concurrency patterns?
     * 95. Design a distributed search engine using concurrency patterns.
     * 96. How would you design a real-time chat system using concurrency patterns?
     * 97. Design a high-performance file processing system using concurrency patterns.
     * 98. How would you design a distributed notification system using concurrency patterns?
     * 99. Design a real-time gaming server using concurrency patterns.
     * 100. How would you design a high-throughput data ingestion system using concurrency patterns?
     */
}
