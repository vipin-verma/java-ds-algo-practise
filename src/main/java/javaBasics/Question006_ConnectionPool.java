/**
 * Question 6: Database Connection Pool Implementation
 * 
 * Problem: Implement a database connection pool that efficiently manages
 * database connections for a multi-threaded application.
 * 
 * Requirements:
 * - Should manage a pool of database connections
 * - Should handle connection creation, borrowing, and returning
 * - Should be thread-safe
 * - Should handle connection timeouts and validation
 * - Should provide monitoring and statistics
 * - Should be configurable and scalable
 * 
 * Difficulty: Hard
 * Category: System Design, Database, Concurrency
 * Experience Level: 5-8 years
 */
public class Question006_ConnectionPool {
    
    public static void main(String[] args) {
        System.out.println("=== Database Connection Pool Implementation ===\n");
        
        // Test basic functionality
        testBasicFunctionality();
        
        // Test concurrent access
        testConcurrentAccess();
        
        // Test connection validation and cleanup
        testConnectionValidation();
        
        // Test performance and scalability
        testPerformance();
        
        // Test advanced features
        testAdvancedFeatures();
        
        // Test error handling
        testErrorHandling();
    }
    
    private static void testBasicFunctionality() {
        System.out.println("=== Basic Functionality Test ===");
        
        // Create connection pool
        ConnectionPoolConfig config = new ConnectionPoolConfig();
        config.setInitialSize(5);
        config.setMaxSize(10);
        config.setMinIdle(2);
        config.setMaxWaitTime(5000);
        config.setConnectionTimeout(30000);
        config.setValidationQuery("SELECT 1");
        
        ConnectionPool pool = new ConnectionPool(config);
        
        try {
            // Test basic operations
            System.out.println("Pool status: " + pool.getStatus());
            
            // Borrow connections
            Connection conn1 = pool.borrowConnection();
            Connection conn2 = pool.borrowConnection();
            Connection conn3 = pool.borrowConnection();
            
            System.out.println("After borrowing 3 connections: " + pool.getStatus());
            
            // Return connections
            pool.returnConnection(conn1);
            pool.returnConnection(conn2);
            
            System.out.println("After returning 2 connections: " + pool.getStatus());
            
            // Test connection reuse
            Connection conn4 = pool.borrowConnection();
            System.out.println("Reused connection: " + (conn4 == conn1 || conn4 == conn2));
            
            pool.returnConnection(conn3);
            pool.returnConnection(conn4);
            
        } catch (Exception e) {
            System.err.println("Error in basic functionality test: " + e.getMessage());
        }
        
        System.out.println("---");
    }
    
    private static void testConcurrentAccess() {
        System.out.println("=== Concurrent Access Test ===");
        
        ConnectionPoolConfig config = new ConnectionPoolConfig();
        config.setInitialSize(10);
        config.setMaxSize(20);
        config.setMinIdle(5);
        config.setMaxWaitTime(10000);
        
        ConnectionPool pool = new ConnectionPool(config);
        
        int numThreads = 15;
        int operationsPerThread = 100;
        
        Thread[] threads = new Thread[numThreads];
        
        // Start threads that borrow and return connections
        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < operationsPerThread; j++) {
                        Connection conn = pool.borrowConnection();
                        if (conn != null) {
                            // Simulate some work
                            Thread.sleep(10);
                            pool.returnConnection(conn);
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
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
        
        System.out.println("Final pool status: " + pool.getStatus());
        System.out.println("Total connections created: " + pool.getStatistics().getTotalConnectionsCreated());
        System.out.println("Total connections borrowed: " + pool.getStatistics().getTotalConnectionsBorrowed());
        System.out.println("Total connections returned: " + pool.getStatistics().getTotalConnectionsReturned());
        
        System.out.println("---");
    }
    
    private static void testConnectionValidation() {
        System.out.println("=== Connection Validation Test ===");
        
        ConnectionPoolConfig config = new ConnectionPoolConfig();
        config.setInitialSize(3);
        config.setMaxSize(5);
        config.setMinIdle(1);
        config.setConnectionTimeout(10000);
        config.setValidationQuery("SELECT 1");
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        config.setTimeBetweenEvictionRuns(5000);
        
        ConnectionPool pool = new ConnectionPool(config);
        
        try {
            // Borrow and return connections to trigger validation
            for (int i = 0; i < 10; i++) {
                Connection conn = pool.borrowConnection();
                if (conn != null) {
                    Thread.sleep(100);
                    pool.returnConnection(conn);
                }
            }
            
            System.out.println("Pool status after validation: " + pool.getStatus());
            System.out.println("Validation statistics: " + pool.getStatistics());
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("---");
    }
    
    private static void testPerformance() {
        System.out.println("=== Performance Test ===");
        
        ConnectionPoolConfig config = new ConnectionPoolConfig();
        config.setInitialSize(20);
        config.setMaxSize(50);
        config.setMinIdle(10);
        config.setMaxWaitTime(5000);
        
        ConnectionPool pool = new ConnectionPool(config);
        
        int numThreads = 30;
        int operationsPerThread = 1000;
        
        long startTime = System.currentTimeMillis();
        
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < operationsPerThread; j++) {
                        Connection conn = pool.borrowConnection();
                        if (conn != null) {
                            // Simulate database operation
                            Thread.sleep(1);
                            pool.returnConnection(conn);
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }
        
        // Wait for completion
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        int totalOperations = numThreads * operationsPerThread;
        double operationsPerSecond = (double) totalOperations / totalTime * 1000;
        
        System.out.println("Performance test completed in: " + totalTime + "ms");
        System.out.println("Operations per second: " + String.format("%.2f", operationsPerSecond));
        System.out.println("Final pool status: " + pool.getStatus());
        
        System.out.println("---");
    }
    
    private static void testAdvancedFeatures() {
        System.out.println("=== Advanced Features Test ===");
        
        // Test connection pool with custom connection factory
        ConnectionPoolConfig config = new ConnectionPoolConfig();
        config.setInitialSize(5);
        config.setMaxSize(10);
        config.setConnectionFactory(new CustomConnectionFactory());
        
        ConnectionPool pool = new ConnectionPool(config);
        
        try {
            // Test connection borrowing with timeout
            Connection conn = pool.borrowConnection(2000); // 2 second timeout
            if (conn != null) {
                System.out.println("Connection borrowed with timeout: " + conn);
                pool.returnConnection(conn);
            }
            
            // Test pool shutdown
            pool.shutdown();
            System.out.println("Pool shutdown completed");
            
        } catch (Exception e) {
            System.err.println("Error in advanced features test: " + e.getMessage());
        }
        
        System.out.println("---");
    }
    
    private static void testErrorHandling() {
        System.out.println("=== Error Handling Test ===");
        
        ConnectionPoolConfig config = new ConnectionPoolConfig();
        config.setInitialSize(2);
        config.setMaxSize(3);
        config.setMaxWaitTime(1000);
        
        ConnectionPool pool = new ConnectionPool(config);
        
        try {
            // Try to borrow more connections than available
            Connection[] connections = new Connection[5];
            for (int i = 0; i < 5; i++) {
                connections[i] = pool.borrowConnection(500); // 500ms timeout
                if (connections[i] != null) {
                    System.out.println("Borrowed connection " + i);
                } else {
                    System.out.println("Failed to borrow connection " + i + " (timeout)");
                }
            }
            
            // Return available connections
            for (Connection conn : connections) {
                if (conn != null) {
                    pool.returnConnection(conn);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error in error handling test: " + e.getMessage());
        }
        
        System.out.println("---");
    }
    
    /**
     * Connection interface (simplified for demonstration)
     */
    interface Connection {
        boolean isClosed();
        void close();
        boolean isValid(int timeout);
        String getConnectionId();
    }
    
    /**
     * Connection pool configuration
     */
    static class ConnectionPoolConfig {
        private int initialSize = 5;
        private int maxSize = 20;
        private int minIdle = 2;
        private long maxWaitTime = 30000;
        private long connectionTimeout = 30000;
        private String validationQuery = "SELECT 1";
        private boolean testOnBorrow = false;
        private boolean testOnReturn = false;
        private boolean testWhileIdle = false;
        private long timeBetweenEvictionRuns = 60000;
        private ConnectionFactory connectionFactory = new DefaultConnectionFactory();
        
        // Getters and setters
        public int getInitialSize() { return initialSize; }
        public void setInitialSize(int initialSize) { this.initialSize = initialSize; }
        
        public int getMaxSize() { return maxSize; }
        public void setMaxSize(int maxSize) { this.maxSize = maxSize; }
        
        public int getMinIdle() { return minIdle; }
        public void setMinIdle(int minIdle) { this.minIdle = minIdle; }
        
        public long getMaxWaitTime() { return maxWaitTime; }
        public void setMaxWaitTime(long maxWaitTime) { this.maxWaitTime = maxWaitTime; }
        
        public long getConnectionTimeout() { return connectionTimeout; }
        public void setConnectionTimeout(long connectionTimeout) { this.connectionTimeout = connectionTimeout; }
        
        public String getValidationQuery() { return validationQuery; }
        public void setValidationQuery(String validationQuery) { this.validationQuery = validationQuery; }
        
        public boolean isTestOnBorrow() { return testOnBorrow; }
        public void setTestOnBorrow(boolean testOnBorrow) { this.testOnBorrow = testOnBorrow; }
        
        public boolean isTestOnReturn() { return testOnReturn; }
        public void setTestOnReturn(boolean testOnReturn) { this.testOnReturn = testOnReturn; }
        
        public boolean isTestWhileIdle() { return testWhileIdle; }
        public void setTestWhileIdle(boolean testWhileIdle) { this.testWhileIdle = testWhileIdle; }
        
        public long getTimeBetweenEvictionRuns() { return timeBetweenEvictionRuns; }
        public void setTimeBetweenEvictionRuns(long timeBetweenEvictionRuns) { this.timeBetweenEvictionRuns = timeBetweenEvictionRuns; }
        
        public ConnectionFactory getConnectionFactory() { return connectionFactory; }
        public void setConnectionFactory(ConnectionFactory connectionFactory) { this.connectionFactory = connectionFactory; }
    }
    
    /**
     * Connection factory interface
     */
    interface ConnectionFactory {
        Connection createConnection() throws Exception;
        void destroyConnection(Connection connection);
    }
    
    /**
     * Default connection factory implementation
     */
    static class DefaultConnectionFactory implements ConnectionFactory {
        private static int connectionCounter = 0;
        
        @Override
        public Connection createConnection() throws Exception {
            // Simulate connection creation delay
            Thread.sleep(100);
            return new MockConnection("Connection-" + (++connectionCounter));
        }
        
        @Override
        public void destroyConnection(Connection connection) {
            // Simulate connection destruction
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Custom connection factory for testing
     */
    static class CustomConnectionFactory implements ConnectionFactory {
        @Override
        public Connection createConnection() throws Exception {
            Thread.sleep(200); // Slower connection creation
            return new MockConnection("Custom-" + System.currentTimeMillis());
        }
        
        @Override
        public void destroyConnection(Connection connection) {
            // Custom cleanup logic
        }
    }
    
    /**
     * Mock connection implementation
     */
    static class MockConnection implements Connection {
        private final String connectionId;
        private volatile boolean closed = false;
        private final long creationTime;
        
        public MockConnection(String connectionId) {
            this.connectionId = connectionId;
            this.creationTime = System.currentTimeMillis();
        }
        
        @Override
        public boolean isClosed() {
            return closed;
        }
        
        @Override
        public void close() {
            closed = true;
        }
        
        @Override
        public boolean isValid(int timeout) {
            return !closed && (System.currentTimeMillis() - creationTime) < timeout;
        }
        
        @Override
        public String getConnectionId() {
            return connectionId;
        }
        
        @Override
        public String toString() {
            return "MockConnection{id='" + connectionId + "', closed=" + closed + "}";
        }
    }
    
    /**
     * Connection pool statistics
     */
    static class PoolStatistics {
        private final java.util.concurrent.atomic.AtomicLong totalConnectionsCreated = new java.util.concurrent.atomic.AtomicLong(0);
        private final java.util.concurrent.atomic.AtomicLong totalConnectionsBorrowed = new java.util.concurrent.atomic.AtomicLong(0);
        private final java.util.concurrent.atomic.AtomicLong totalConnectionsReturned = new java.util.concurrent.atomic.AtomicLong(0);
        private final java.util.concurrent.atomic.LongAdder activeConnections = new java.util.concurrent.atomic.LongAdder();
        private final java.util.concurrent.atomic.LongAdder idleConnections = new java.util.concurrent.atomic.LongAdder();
        private final java.util.concurrent.atomic.LongAdder totalWaitTime = new java.util.concurrent.atomic.LongAdder();
        private final java.util.concurrent.atomic.LongAdder totalBorrowTime = new java.util.concurrent.atomic.LongAdder();
        
        public long getTotalConnectionsCreated() { return totalConnectionsCreated.get(); }
        public long getTotalConnectionsBorrowed() { return totalConnectionsBorrowed.get(); }
        public long getTotalConnectionsReturned() { return totalConnectionsReturned.get(); }
        public long getActiveConnections() { return activeConnections.sum(); }
        public long getIdleConnections() { return idleConnections.sum(); }
        public double getAverageWaitTime() { 
            long total = totalWaitTime.sum();
            long count = totalConnectionsBorrowed.get();
            return count == 0 ? 0.0 : (double) total / count;
        }
        public double getAverageBorrowTime() {
            long total = totalBorrowTime.sum();
            long count = totalConnectionsBorrowed.get();
            return count == 0 ? 0.0 : (double) total / count;
        }
        
        @Override
        public String toString() {
            return String.format("PoolStatistics{created=%d, borrowed=%d, returned=%d, active=%d, idle=%d, avgWait=%.2fms, avgBorrow=%.2fms}",
                getTotalConnectionsCreated(), getTotalConnectionsBorrowed(), getTotalConnectionsReturned(),
                getActiveConnections(), getIdleConnections(), getAverageWaitTime(), getAverageBorrowTime());
        }
    }
    
    /**
     * Main connection pool implementation
     */
    static class ConnectionPool {
        private final ConnectionPoolConfig config;
        private final java.util.concurrent.BlockingQueue<Connection> idleConnections;
        private final java.util.Set<Connection> activeConnections;
        private final java.util.concurrent.locks.ReentrantLock poolLock = new java.util.concurrent.locks.ReentrantLock();
        private final PoolStatistics statistics = new PoolStatistics();
        private volatile boolean shutdown = false;
        private final java.util.concurrent.ScheduledExecutorService evictionExecutor;
        
        public ConnectionPool(ConnectionPoolConfig config) {
            this.config = config;
            this.idleConnections = new java.util.concurrent.LinkedBlockingQueue<>(config.getMaxSize());
            this.activeConnections = java.util.Collections.newSetFromMap(new java.util.concurrent.ConcurrentHashMap<>());
            this.evictionExecutor = java.util.concurrent.Executors.newScheduledThreadPool(1);
            
            // Initialize pool
            initializePool();
            
            // Start eviction thread
            startEvictionThread();
        }
        
        private void initializePool() {
            try {
                for (int i = 0; i < config.getInitialSize(); i++) {
                    Connection conn = config.getConnectionFactory().createConnection();
                    idleConnections.offer(conn);
                    statistics.totalConnectionsCreated.incrementAndGet();
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize connection pool", e);
            }
        }
        
        private void startEvictionThread() {
            evictionExecutor.scheduleWithFixedDelay(this::evictIdleConnections, 
                config.getTimeBetweenEvictionRuns(), config.getTimeBetweenEvictionRuns(), 
                java.util.concurrent.TimeUnit.MILLISECONDS);
        }
        
        public Connection borrowConnection() throws Exception {
            return borrowConnection(config.getMaxWaitTime());
        }
        
        public Connection borrowConnection(long timeout) throws Exception {
            if (shutdown) {
                throw new IllegalStateException("Connection pool is shutdown");
            }
            
            long startTime = System.currentTimeMillis();
            
            // Try to get idle connection
            Connection connection = idleConnections.poll();
            if (connection != null) {
                if (isConnectionValid(connection)) {
                    activateConnection(connection);
                    return connection;
                } else {
                    // Invalid connection, destroy it
                    config.getConnectionFactory().destroyConnection(connection);
                    statistics.totalConnectionsCreated.incrementAndGet();
                }
            }
            
            // Try to create new connection if under max size
            poolLock.lock();
            try {
                if (getTotalSize() < config.getMaxSize()) {
                    connection = config.getConnectionFactory().createConnection();
                    statistics.totalConnectionsCreated.incrementAndGet();
                    activateConnection(connection);
                    return connection;
                }
            } finally {
                poolLock.unlock();
            }
            
            // Wait for available connection
            connection = idleConnections.poll(timeout, java.util.concurrent.TimeUnit.MILLISECONDS);
            if (connection != null) {
                if (isConnectionValid(connection)) {
                    activateConnection(connection);
                    return connection;
                } else {
                    config.getConnectionFactory().destroyConnection(connection);
                    throw new Exception("No valid connections available");
                }
            }
            
            throw new Exception("Timeout waiting for connection");
        }
        
        public void returnConnection(Connection connection) {
            if (connection == null || shutdown) {
                return;
            }
            
            deactivateConnection(connection);
            
            if (isConnectionValid(connection) && getIdleSize() < config.getMaxSize()) {
                idleConnections.offer(connection);
            } else {
                config.getConnectionFactory().destroyConnection(connection);
            }
        }
        
        private void activateConnection(Connection connection) {
            activeConnections.add(connection);
            idleConnections.remove(connection);
            statistics.totalConnectionsBorrowed.incrementAndGet();
            statistics.activeConnections.increment();
            statistics.idleConnections.decrement();
        }
        
        private void deactivateConnection(Connection connection) {
            activeConnections.remove(connection);
            statistics.totalConnectionsReturned.incrementAndGet();
            statistics.activeConnections.decrement();
            statistics.idleConnections.increment();
        }
        
        private boolean isConnectionValid(Connection connection) {
            if (connection == null || connection.isClosed()) {
                return false;
            }
            
            try {
                return connection.isValid((int) config.getConnectionTimeout());
            } catch (Exception e) {
                return false;
            }
        }
        
        private void evictIdleConnections() {
            if (shutdown) {
                return;
            }
            
            int targetIdleSize = Math.max(config.getMinIdle(), 
                (int) (getTotalSize() * 0.1)); // Keep 10% as idle minimum
            
            while (getIdleSize() > targetIdleSize) {
                Connection connection = idleConnections.poll();
                if (connection != null) {
                    config.getConnectionFactory().destroyConnection(connection);
                    statistics.idleConnections.decrement();
                } else {
                    break;
                }
            }
        }
        
        public void shutdown() {
            shutdown = true;
            evictionExecutor.shutdown();
            
            // Close all connections
            poolLock.lock();
            try {
                for (Connection conn : activeConnections) {
                    try {
                        conn.close();
                    } catch (Exception e) {
                        // Log error
                    }
                }
                activeConnections.clear();
                
                Connection conn;
                while ((conn = idleConnections.poll()) != null) {
                    try {
                        conn.close();
                    } catch (Exception e) {
                        // Log error
                    }
                }
            } finally {
                poolLock.unlock();
            }
        }
        
        public PoolStatus getStatus() {
            return new PoolStatus(
                getTotalSize(),
                getActiveSize(),
                getIdleSize(),
                config.getMaxSize(),
                shutdown
            );
        }
        
        public PoolStatistics getStatistics() {
            return statistics;
        }
        
        private int getTotalSize() {
            return getActiveSize() + getIdleSize();
        }
        
        private int getActiveSize() {
            return activeConnections.size();
        }
        
        private int getIdleSize() {
            return idleConnections.size();
        }
    }
    
    /**
     * Pool status information
     */
    static class PoolStatus {
        private final int totalSize;
        private final int activeSize;
        private final int idleSize;
        private final int maxSize;
        private final boolean shutdown;
        
        public PoolStatus(int totalSize, int activeSize, int idleSize, int maxSize, boolean shutdown) {
            this.totalSize = totalSize;
            this.activeSize = activeSize;
            this.idleSize = idleSize;
            this.maxSize = maxSize;
            this.shutdown = shutdown;
        }
        
        @Override
        public String toString() {
            return String.format("PoolStatus{total=%d, active=%d, idle=%d, max=%d, shutdown=%s}",
                totalSize, activeSize, idleSize, maxSize, shutdown);
        }
    }
    
    /**
     * Interview Questions to Ask:
     * 
     * 1. What are the key components of a connection pool?
     * 2. How would you handle connection leaks?
     * 3. What if the database becomes unavailable?
     * 4. How would you implement connection failover?
     * 5. What if you need to support multiple database types?
     * 6. How would you implement connection load balancing?
     * 7. What if you need to support connection encryption?
     * 8. How would you implement connection pooling for NoSQL databases?
     * 9. What if you need to support connection pooling across JVMs?
     * 10. How would you implement connection health checks?
     * 11. What if you need to support connection pooling for microservices?
     * 12. How would you implement connection pooling for cloud databases?
     * 13. What if you need to support connection pooling for read replicas?
     * 14. How would you implement connection pooling for sharded databases?
     * 15. What if you need to support connection pooling for graph databases?
     */
}
