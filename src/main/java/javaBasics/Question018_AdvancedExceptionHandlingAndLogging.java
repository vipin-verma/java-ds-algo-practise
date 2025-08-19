import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;
import java.io.*;
import java.sql.*;

/**
 * Advanced Exception Handling and Logging - Comprehensive Guide
 * 
 * This class demonstrates various advanced exception handling techniques
 * commonly asked in Java interviews for experienced developers.
 * 
 * Topics covered:
 * - Custom exception classes
 * - Exception handling strategies
 * - Logging frameworks and strategies
 * - Performance monitoring
 * - Error recovery mechanisms
 * - Best practices and patterns
 */
public class Question018_AdvancedExceptionHandlingAndLogging {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Exception Handling and Logging - Complete Guide ===\n");
        
        demonstrateCustomExceptions();
        demonstrateExceptionHandlingStrategies();
        demonstrateLoggingStrategies();
        demonstratePerformanceMonitoring();
        demonstrateErrorRecovery();
        demonstrateBestPractices();
        
        System.out.println("\n=== Advanced Exception Handling and Logging Completed! ===");
    }
    
    // ===== CUSTOM EXCEPTIONS =====
    
    /**
     * Base custom exception class
     */
    static class BaseException extends Exception {
        private final String errorCode;
        private final long timestamp;
        private final Map<String, Object> context;
        
        public BaseException(String message, String errorCode) {
            super(message);
            this.errorCode = errorCode;
            this.timestamp = System.currentTimeMillis();
            this.context = new HashMap<>();
        }
        
        public BaseException(String message, String errorCode, Throwable cause) {
            super(message, cause);
            this.errorCode = errorCode;
            this.timestamp = System.currentTimeMillis();
            this.context = new HashMap<>();
        }
        
        public void addContext(String key, Object value) {
            context.put(key, value);
        }
        
        public String getErrorCode() { return errorCode; }
        public long getTimestamp() { return timestamp; }
        public Map<String, Object> getContext() { return context; }
        
        @Override
        public String toString() {
            return String.format("BaseException{errorCode='%s', timestamp=%d, context=%s, message='%s'}", 
                               errorCode, timestamp, context, getMessage());
        }
    }
    
    /**
     * Business logic exception
     */
    static class BusinessException extends BaseException {
        private final String businessRule;
        
        public BusinessException(String message, String errorCode, String businessRule) {
            super(message, errorCode);
            this.businessRule = businessRule;
        }
        
        public BusinessException(String message, String errorCode, String businessRule, Throwable cause) {
            super(message, errorCode, cause);
            this.businessRule = businessRule;
        }
        
        public String getBusinessRule() { return businessRule; }
    }
    
    /**
     * Validation exception
     */
    static class ValidationException extends BaseException {
        private final List<String> validationErrors;
        
        public ValidationException(String message, String errorCode, List<String> validationErrors) {
            super(message, errorCode);
            this.validationErrors = validationErrors;
        }
        
        public List<String> getValidationErrors() { return validationErrors; }
        
        @Override
        public String toString() {
            return String.format("ValidationException{errorCode='%s', validationErrors=%s, message='%s'}", 
                               getErrorCode(), validationErrors, getMessage());
        }
    }
    
    /**
     * Resource exception
     */
    static class ResourceException extends BaseException {
        private final String resourceType;
        private final String resourceId;
        
        public ResourceException(String message, String errorCode, String resourceType, String resourceId) {
            super(message, errorCode);
            this.resourceType = resourceType;
            this.resourceId = resourceId;
        }
        
        public String getResourceType() { return resourceType; }
        public String getResourceId() { return resourceId; }
    }
    
    // ===== EXCEPTION HANDLING STRATEGIES =====
    
    /**
     * Exception handler with different strategies
     */
    static class ExceptionHandler {
        private final Logger logger;
        private final Map<String, ExceptionStrategy> strategies;
        
        public ExceptionHandler() {
            this.logger = Logger.getLogger(ExceptionHandler.class.getName());
            this.strategies = new HashMap<>();
            initializeStrategies();
        }
        
        private void initializeStrategies() {
            strategies.put("BUSINESS", new BusinessExceptionStrategy());
            strategies.put("VALIDATION", new ValidationExceptionStrategy());
            strategies.put("RESOURCE", new ResourceExceptionStrategy());
            strategies.put("SYSTEM", new SystemExceptionStrategy());
        }
        
        public void handleException(Exception exception) {
            try {
                String strategyKey = determineStrategy(exception);
                ExceptionStrategy strategy = strategies.get(strategyKey);
                
                if (strategy != null) {
                    strategy.handle(exception);
                } else {
                    handleUnknownException(exception);
                }
            } catch (Exception e) {
                logger.severe("Error in exception handler: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        private String determineStrategy(Exception exception) {
            if (exception instanceof BusinessException) return "BUSINESS";
            if (exception instanceof ValidationException) return "VALIDATION";
            if (exception instanceof ResourceException) return "RESOURCE";
            if (exception instanceof SQLException) return "RESOURCE";
            if (exception instanceof IOException) return "SYSTEM";
            return "SYSTEM";
        }
        
        private void handleUnknownException(Exception exception) {
            logger.severe("Unknown exception type: " + exception.getClass().getSimpleName());
            logger.severe("Message: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
    
    /**
     * Exception strategy interface
     */
    interface ExceptionStrategy {
        void handle(Exception exception);
    }
    
    /**
     * Business exception strategy
     */
    static class BusinessExceptionStrategy implements ExceptionStrategy {
        private final Logger logger = Logger.getLogger(BusinessExceptionStrategy.class.getName());
        
        @Override
        public void handle(Exception exception) {
            BusinessException be = (BusinessException) exception;
            logger.warning("Business rule violation: " + be.getBusinessRule());
            logger.warning("Error code: " + be.getErrorCode());
            logger.warning("Context: " + be.getContext());
            
            // Notify business stakeholders
            notifyBusinessStakeholders(be);
        }
        
        private void notifyBusinessStakeholders(BusinessException exception) {
            // Implementation for notifying business stakeholders
            System.out.println("  Notifying business stakeholders about: " + exception.getMessage());
        }
    }
    
    /**
     * Validation exception strategy
     */
    static class ValidationExceptionStrategy implements ExceptionStrategy {
        private final Logger logger = Logger.getLogger(ValidationExceptionStrategy.class.getName());
        
        @Override
        public void handle(Exception exception) {
            ValidationException ve = (ValidationException) exception;
            logger.warning("Validation failed with " + ve.getValidationErrors().size() + " errors");
            
            for (String error : ve.getValidationErrors()) {
                logger.warning("  Validation error: " + error);
            }
            
            // Log validation context
            logger.warning("Validation context: " + ve.getContext());
        }
    }
    
    /**
     * Resource exception strategy
     */
    static class ResourceExceptionStrategy implements ExceptionStrategy {
        private final Logger logger = Logger.getLogger(ResourceExceptionStrategy.class.getName());
        
        @Override
        public void handle(Exception exception) {
            if (exception instanceof ResourceException) {
                ResourceException re = (ResourceException) exception;
                logger.severe("Resource error: " + re.getResourceType() + " - " + re.getResourceId());
            } else if (exception instanceof SQLException) {
                SQLException se = (SQLException) exception;
                logger.severe("Database error: " + se.getSQLState() + " - " + se.getErrorCode());
            }
            
            // Attempt resource recovery
            attemptResourceRecovery(exception);
        }
        
        private void attemptResourceRecovery(Exception exception) {
            System.out.println("  Attempting resource recovery for: " + exception.getClass().getSimpleName());
        }
    }
    
    /**
     * System exception strategy
     */
    static class SystemExceptionStrategy implements ExceptionStrategy {
        private final Logger logger = Logger.getLogger(SystemExceptionStrategy.class.getName());
        
        @Override
        public void handle(Exception exception) {
            logger.severe("System error: " + exception.getMessage());
            logger.severe("Stack trace:");
            
            // Log stack trace
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            logger.severe(sw.toString());
            
            // Attempt system recovery
            attemptSystemRecovery(exception);
        }
        
        private void attemptSystemRecovery(Exception exception) {
            System.out.println("  Attempting system recovery for: " + exception.getClass().getSimpleName());
        }
    }
    
    // ===== LOGGING STRATEGIES =====
    
    /**
     * Advanced logging configuration
     */
    static class AdvancedLogging {
        private final Logger logger;
        private final FileHandler fileHandler;
        private final ConsoleHandler consoleHandler;
        
        public AdvancedLogging(String loggerName, String logFileName) throws IOException {
            this.logger = Logger.getLogger(loggerName);
            this.logger.setLevel(Level.ALL);
            
            // File handler with custom formatter
            this.fileHandler = new FileHandler(logFileName, true);
            this.fileHandler.setFormatter(new CustomFormatter());
            this.fileHandler.setLevel(Level.ALL);
            
            // Console handler
            this.consoleHandler = new ConsoleHandler();
            this.consoleHandler.setLevel(Level.INFO);
            this.consoleHandler.setFormatter(new CustomFormatter());
            
            // Add handlers
            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);
            
            // Remove default handlers
            logger.setUseParentHandlers(false);
        }
        
        public void logBusinessEvent(String event, Map<String, Object> data) {
            LogRecord record = new LogRecord(Level.INFO, "BUSINESS_EVENT: " + event);
            record.setParameters(new Object[]{data});
            logger.log(record);
        }
        
        public void logPerformanceMetric(String metric, long value, String unit) {
            LogRecord record = new LogRecord(Level.INFO, "PERFORMANCE: " + metric + " = " + value + " " + unit);
            record.setParameters(new Object[]{metric, value, unit});
            logger.log(record);
        }
        
        public void logSecurityEvent(String event, String userId, String ipAddress) {
            LogRecord record = new LogRecord(Level.WARNING, "SECURITY: " + event);
            record.setParameters(new Object[]{event, userId, ipAddress});
            logger.log(record);
        }
        
        public void close() {
            fileHandler.close();
            consoleHandler.close();
        }
    }
    
    /**
     * Custom log formatter
     */
    static class CustomFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            StringBuilder sb = new StringBuilder();
            
            // Timestamp
            sb.append(new Date(record.getMillis())).append(" ");
            
            // Level
            sb.append("[").append(record.getLevel()).append("] ");
            
            // Logger name
            sb.append("[").append(record.getLoggerName()).append("] ");
            
            // Message
            sb.append(record.getMessage());
            
            // Parameters
            if (record.getParameters() != null) {
                sb.append(" - Parameters: ");
                for (Object param : record.getParameters()) {
                    sb.append(param).append(" ");
                }
            }
            
            // Exception
            if (record.getThrown() != null) {
                sb.append("\nException: ");
                sb.append(record.getThrown().getMessage());
            }
            
            sb.append("\n");
            return sb.toString();
        }
    }
    
    // ===== PERFORMANCE MONITORING =====
    
    /**
     * Performance monitoring with exception tracking
     */
    static class PerformanceMonitor {
        private final Map<String, Long> startTimes = new ConcurrentHashMap<>();
        private final Map<String, List<Long>> executionTimes = new ConcurrentHashMap<>();
        private final Map<String, Long> exceptionCounts = new ConcurrentHashMap<>();
        
        public void startOperation(String operationName) {
            startTimes.put(operationName, System.nanoTime());
        }
        
        public void endOperation(String operationName) {
            Long startTime = startTimes.remove(operationName);
            if (startTime != null) {
                long executionTime = System.nanoTime() - startTime;
                executionTimes.computeIfAbsent(operationName, k -> new ArrayList<>()).add(executionTime);
            }
        }
        
        public void recordException(String operationName) {
            exceptionCounts.merge(operationName, 1L, Long::sum);
        }
        
        public PerformanceReport generateReport() {
            PerformanceReport report = new PerformanceReport();
            
            for (Map.Entry<String, List<Long>> entry : executionTimes.entrySet()) {
                String operation = entry.getKey();
                List<Long> times = entry.getValue();
                
                if (!times.isEmpty()) {
                    long total = times.stream().mapToLong(Long::longValue).sum();
                    long avg = total / times.size();
                    long min = times.stream().mapToLong(Long::longValue).min().orElse(0);
                    long max = times.stream().mapToLong(Long::longValue).max().orElse(0);
                    
                    report.addOperationStats(operation, times.size(), avg, min, max, 
                                          exceptionCounts.getOrDefault(operation, 0L));
                }
            }
            
            return report;
        }
    }
    
    /**
     * Performance report
     */
    static class PerformanceReport {
        private final Map<String, OperationStats> operationStats = new HashMap<>();
        
        public void addOperationStats(String operation, int count, long avgTime, long minTime, 
                                    long maxTime, long exceptionCount) {
            operationStats.put(operation, new OperationStats(count, avgTime, minTime, maxTime, exceptionCount));
        }
        
        public void printReport() {
            System.out.println("\n=== PERFORMANCE REPORT ===");
            for (Map.Entry<String, OperationStats> entry : operationStats.entrySet()) {
                System.out.println("Operation: " + entry.getKey());
                entry.getValue().printStats();
            }
        }
        
        static class OperationStats {
            private final int count;
            private final long avgTime;
            private final long minTime;
            private final long maxTime;
            private final long exceptionCount;
            
            public OperationStats(int count, long avgTime, long minTime, long maxTime, long exceptionCount) {
                this.count = count;
                this.avgTime = avgTime;
                this.minTime = minTime;
                this.maxTime = maxTime;
                this.exceptionCount = exceptionCount;
            }
            
            public void printStats() {
                System.out.println("  Count: " + count);
                System.out.println("  Avg Time: " + avgTime + " ns");
                System.out.println("  Min Time: " + minTime + " ns");
                System.out.println("  Max Time: " + maxTime + " ns");
                System.out.println("  Exception Count: " + exceptionCount);
                System.out.println("  Success Rate: " + 
                    String.format("%.2f%%", (double)(count - exceptionCount) / count * 100));
                System.out.println();
            }
        }
    }
    
    // ===== ERROR RECOVERY MECHANISMS =====
    
    /**
     * Circuit breaker pattern for error recovery
     */
    static class CircuitBreaker {
        private final String name;
        private final int failureThreshold;
        private final long timeout;
        private final long resetTimeout;
        
        private CircuitState state = CircuitState.CLOSED;
        private int failureCount = 0;
        private long lastFailureTime = 0;
        private long lastStateChangeTime = 0;
        
        public CircuitBreaker(String name, int failureThreshold, long timeout, long resetTimeout) {
            this.name = name;
            this.failureThreshold = failureThreshold;
            this.timeout = timeout;
            this.resetTimeout = resetTimeout;
        }
        
        public <T> T execute(Supplier<T> operation) throws Exception {
            if (canExecute()) {
                try {
                    T result = operation.get();
                    onSuccess();
                    return result;
                } catch (Exception e) {
                    onFailure(e);
                    throw e;
                }
            } else {
                throw new CircuitBreakerOpenException("Circuit breaker is open for: " + name);
            }
        }
        
        private boolean canExecute() {
            if (state == CircuitState.CLOSED) {
                return true;
            }
            
            if (state == CircuitState.OPEN) {
                if (System.currentTimeMillis() - lastStateChangeTime >= resetTimeout) {
                    state = CircuitState.HALF_OPEN;
                    lastStateChangeTime = System.currentTimeMillis();
                    return true;
                }
                return false;
            }
            
            return state == CircuitState.HALF_OPEN;
        }
        
        private void onSuccess() {
            if (state == CircuitState.HALF_OPEN) {
                state = CircuitState.CLOSED;
                lastStateChangeTime = System.currentTimeMillis();
                failureCount = 0;
            }
        }
        
        private void onFailure(Exception e) {
            failureCount++;
            lastFailureTime = System.currentTimeMillis();
            
            if (failureCount >= failureThreshold) {
                state = CircuitState.OPEN;
                lastStateChangeTime = System.currentTimeMillis();
            }
        }
        
        public CircuitState getState() { return state; }
        public int getFailureCount() { return failureCount; }
        
        enum CircuitState { CLOSED, OPEN, HALF_OPEN }
    }
    
    /**
     * Circuit breaker exception
     */
    static class CircuitBreakerOpenException extends Exception {
        public CircuitBreakerOpenException(String message) {
            super(message);
        }
    }
    
    /**
     * Retry mechanism with exponential backoff
     */
    static class RetryMechanism {
        private final int maxRetries;
        private final long initialDelay;
        private final double backoffMultiplier;
        
        public RetryMechanism(int maxRetries, long initialDelay, double backoffMultiplier) {
            this.maxRetries = maxRetries;
            this.initialDelay = initialDelay;
            this.backoffMultiplier = backoffMultiplier;
        }
        
        public <T> T executeWithRetry(Supplier<T> operation) throws Exception {
            Exception lastException = null;
            long delay = initialDelay;
            
            for (int attempt = 0; attempt <= maxRetries; attempt++) {
                try {
                    return operation.get();
                } catch (Exception e) {
                    lastException = e;
                    
                    if (attempt < maxRetries) {
                        System.out.println("  Attempt " + (attempt + 1) + " failed, retrying in " + delay + "ms");
                        Thread.sleep(delay);
                        delay = (long) (delay * backoffMultiplier);
                    }
                }
            }
            
            throw new RuntimeException("Operation failed after " + (maxRetries + 1) + " attempts", lastException);
        }
    }
    
    // ===== DEMONSTRATION METHODS =====
    
    private static void demonstrateCustomExceptions() {
        System.out.println("1. CUSTOM EXCEPTIONS:\n");
        
        try {
            // Business exception
            throw new BusinessException("Invalid transaction amount", "TX001", "Amount must be positive");
        } catch (BusinessException e) {
            e.addContext("userId", "12345");
            e.addContext("amount", -100);
            System.out.println("  Caught BusinessException: " + e);
        }
        
        try {
            // Validation exception
            List<String> errors = Arrays.asList("Email is invalid", "Password too short");
            throw new ValidationException("User validation failed", "VAL001", errors);
        } catch (ValidationException e) {
            System.out.println("  Caught ValidationException: " + e);
        }
        
        try {
            // Resource exception
            throw new ResourceException("Database connection failed", "RES001", "Database", "main-db");
        } catch (ResourceException e) {
            System.out.println("  Caught ResourceException: " + e);
        }
        
        System.out.println();
    }
    
    private static void demonstrateExceptionHandlingStrategies() {
        System.out.println("2. EXCEPTION HANDLING STRATEGIES:\n");
        
        ExceptionHandler handler = new ExceptionHandler();
        
        System.out.println("Handling different exception types:");
        
        // Business exception
        try {
            throw new BusinessException("Insufficient funds", "TX002", "Balance must be sufficient");
        } catch (Exception e) {
            handler.handleException(e);
        }
        
        // Validation exception
        try {
            List<String> errors = Arrays.asList("Invalid phone number", "Missing address");
            throw new ValidationException("Customer validation failed", "VAL002", errors);
        } catch (Exception e) {
            handler.handleException(e);
        }
        
        // Resource exception
        try {
            throw new ResourceException("File not found", "RES002", "File", "config.xml");
        } catch (Exception e) {
            handler.handleException(e);
        }
        
        System.out.println();
    }
    
    private static void demonstrateLoggingStrategies() {
        System.out.println("3. LOGGING STRATEGIES:\n");
        
        try {
            AdvancedLogging logging = new AdvancedLogging("TestLogger", "test.log");
            
            // Log different types of events
            logging.logBusinessEvent("User Registration", Map.of("userId", "12345", "email", "user@example.com"));
            logging.logPerformanceMetric("Response Time", 150, "ms");
            logging.logSecurityEvent("Failed Login", "user123", "192.168.1.100");
            
            logging.close();
            System.out.println("  Logging completed successfully");
        } catch (IOException e) {
            System.out.println("  Error setting up logging: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void demonstratePerformanceMonitoring() {
        System.out.println("4. PERFORMANCE MONITORING:\n");
        
        PerformanceMonitor monitor = new PerformanceMonitor();
        
        // Simulate some operations
        for (int i = 0; i < 5; i++) {
            monitor.startOperation("database_query");
            try {
                Thread.sleep(100 + (int)(Math.random() * 200));
                monitor.endOperation("database_query");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Simulate some failures
        for (int i = 0; i < 2; i++) {
            monitor.startOperation("external_api_call");
            try {
                Thread.sleep(50);
                monitor.endOperation("external_api_call");
            } catch (Exception e) {
                monitor.recordException("external_api_call");
            }
        }
        
        // Generate and print report
        PerformanceReport report = monitor.generateReport();
        report.printReport();
        
        System.out.println();
    }
    
    private static void demonstrateErrorRecovery() {
        System.out.println("5. ERROR RECOVERY MECHANISMS:\n");
        
        System.out.println("Circuit Breaker demonstration:");
        CircuitBreaker circuitBreaker = new CircuitBreaker("test-service", 3, 1000, 5000);
        
        for (int i = 0; i < 5; i++) {
            try {
                String result = circuitBreaker.execute(() -> {
                    if (Math.random() < 0.7) {
                        throw new RuntimeException("Service failure");
                    }
                    return "Success";
                });
                System.out.println("  Operation " + (i + 1) + ": " + result);
            } catch (Exception e) {
                System.out.println("  Operation " + (i + 1) + " failed: " + e.getMessage());
            }
            System.out.println("  Circuit state: " + circuitBreaker.getState());
        }
        
        System.out.println("\nRetry Mechanism demonstration:");
        RetryMechanism retry = new RetryMechanism(3, 100, 2.0);
        
        try {
            String result = retry.executeWithRetry(() -> {
                if (Math.random() < 0.8) {
                    throw new RuntimeException("Temporary failure");
                }
                return "Operation succeeded";
            });
            System.out.println("  Final result: " + result);
        } catch (Exception e) {
            System.out.println("  All retries failed: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void demonstrateBestPractices() {
        System.out.println("6. BEST PRACTICES:\n");
        
        System.out.println("Exception handling best practices:");
        System.out.println("  1. Use specific exception types for different error scenarios");
        System.out.println("  2. Include context information in exceptions");
        System.out.println("  3. Implement proper exception handling strategies");
        System.out.println("  4. Use logging for debugging and monitoring");
        System.out.println("  5. Implement circuit breakers for external dependencies");
        System.out.println("  6. Use retry mechanisms with exponential backoff");
        System.out.println("  7. Monitor performance and error rates");
        System.out.println("  8. Implement proper cleanup in finally blocks");
        System.out.println("  9. Use checked exceptions for recoverable errors");
        System.out.println("  10. Document exception scenarios and handling strategies");
        
        System.out.println("\nLogging best practices:");
        System.out.println("  1. Use appropriate log levels");
        System.out.println("  2. Include relevant context in log messages");
        System.out.println("  3. Use structured logging for better parsing");
        System.out.println("  4. Implement log rotation and retention policies");
        System.out.println("  5. Monitor log volume and performance impact");
        System.out.println("  6. Use correlation IDs for request tracing");
        System.out.println("  7. Implement centralized logging for distributed systems");
        System.out.println("  8. Secure sensitive information in logs");
        System.out.println("  9. Use async logging for performance-critical operations");
        System.out.println("  10. Implement log aggregation and analysis");
        
        System.out.println();
    }
}

/**
 * INTERVIEW QUESTIONS COVERED:
 * 
 * THEORETICAL:
 * 1. Explain the difference between checked and unchecked exceptions.
 * 2. How do you implement custom exception hierarchies?
 * 3. What are the best practices for exception handling?
 * 4. Explain the circuit breaker pattern and its benefits.
 * 5. How do you implement proper logging strategies?
 * 6. What are the trade-offs between different error recovery mechanisms?
 * 
 * PRACTICAL:
 * 1. Implement custom exception classes with context.
 * 2. Create exception handling strategies for different scenarios.
 * 3. Implement circuit breaker pattern for fault tolerance.
 * 4. Set up advanced logging with custom formatters.
 * 5. Implement performance monitoring with exception tracking.
 * 
 * TRICKY SCENARIOS:
 * 1. Handle exceptions in multi-threaded environments.
 * 2. Implement proper cleanup in exception scenarios.
 * 3. Deal with cascading failures in distributed systems.
 * 4. Optimize logging performance for high-throughput applications.
 * 
 * PERFORMANCE:
 * 1. Analyze exception handling overhead.
 * 2. Optimize logging for performance-critical operations.
 * 3. Implement efficient error recovery mechanisms.
 * 4. Monitor and tune circuit breaker parameters.
 * 
 * DESIGN PATTERNS:
 * 1. Strategy pattern for exception handling.
 * 2. Circuit breaker pattern for fault tolerance.
 * 3. Retry pattern with exponential backoff.
 * 4. Observer pattern for exception monitoring.
 * 5. Template method for error recovery.
 */
