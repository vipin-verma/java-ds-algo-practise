/**
 * Question 8: Microservices Architecture Implementation
 * 
 * Problem: Design and implement a basic microservices architecture with
 * service discovery, load balancing, and inter-service communication.
 * 
 * Requirements:
 * - Should implement multiple microservices
 * - Should include service discovery mechanism
 * - Should implement load balancing
 * - Should handle inter-service communication
 * - Should include circuit breaker pattern
 * - Should be scalable and fault-tolerant
 * 
 * Difficulty: Hard
 * Category: Microservices, Distributed Systems, Architecture
 * Experience Level: 6-10 years
 */
public class Question008_MicroservicesArchitecture {
    
    public static void main(String[] args) {
        System.out.println("=== Microservices Architecture Implementation ===\n");
        
        // Test service discovery
        testServiceDiscovery();
        
        // Test load balancing
        testLoadBalancing();
        
        // Test inter-service communication
        testInterServiceCommunication();
        
        // Test circuit breaker pattern
        testCircuitBreaker();
        
        // Test fault tolerance
        testFaultTolerance();
        
        // Test scalability
        testScalability();
        
        // Test monitoring and metrics
        testMonitoring();
    }
    
    private static void testServiceDiscovery() {
        System.out.println("=== Service Discovery Test ===");
        
        // Create service registry
        ServiceRegistry registry = new ServiceRegistry();
        
        // Register services
        UserService userService = new UserService("user-service", "localhost", 8081);
        OrderService orderService = new OrderService("order-service", "localhost", 8082);
        PaymentService paymentService = new PaymentService("payment-service", "localhost", 8083);
        
        registry.registerService(userService);
        registry.registerService(orderService);
        registry.registerService(paymentService);
        
        // Discover services
        System.out.println("Available services:");
        registry.getAllServices().forEach(service -> 
            System.out.println("  - " + service.getName() + " at " + service.getHost() + ":" + service.getPort())
        );
        
        // Test service discovery
        Microservice discoveredService = registry.discoverService("user-service");
        System.out.println("Discovered user-service: " + discoveredService);
        
        // Test health check
        registry.checkServiceHealth();
        
        System.out.println("---");
    }
    
    private static void testLoadBalancing() {
        System.out.println("=== Load Balancing Test ===");
        
        // Create load balancer
        LoadBalancer loadBalancer = new LoadBalancer();
        
        // Add multiple instances of the same service
        UserService userService1 = new UserService("user-service", "localhost", 8081);
        UserService userService2 = new UserService("user-service", "localhost", 8084);
        UserService userService3 = new UserService("user-service", "localhost", 8085);
        
        loadBalancer.addInstance(userService1);
        loadBalancer.addInstance(userService2);
        loadBalancer.addInstance(userService3);
        
        // Test different load balancing strategies
        System.out.println("Round Robin Load Balancing:");
        for (int i = 0; i < 6; i++) {
            Microservice instance = loadBalancer.getNextInstance(LoadBalancingStrategy.ROUND_ROBIN);
            System.out.println("  Request " + (i + 1) + " -> " + instance.getHost() + ":" + instance.getPort());
        }
        
        System.out.println("\nRandom Load Balancing:");
        for (int i = 0; i < 5; i++) {
            Microservice instance = loadBalancer.getNextInstance(LoadBalancingStrategy.RANDOM);
            System.out.println("  Request " + (i + 1) + " -> " + instance.getHost() + ":" + instance.getPort());
        }
        
        System.out.println("\nLeast Connections Load Balancing:");
        for (int i = 0; i < 5; i++) {
            Microservice instance = loadBalancer.getNextInstance(LoadBalancingStrategy.LEAST_CONNECTIONS);
            System.out.println("  Request " + (i + 1) + " -> " + instance.getHost() + ":" + instance.getPort());
        }
        
        System.out.println("---");
    }
    
    private static void testInterServiceCommunication() {
        System.out.println("=== Inter-Service Communication Test ===");
        
        // Create services
        UserService userService = new UserService("user-service", "localhost", 8081);
        OrderService orderService = new OrderService("order-service", "localhost", 8082);
        PaymentService paymentService = new PaymentService("payment-service", "localhost", 8083);
        
        // Create API gateway
        ApiGateway gateway = new ApiGateway();
        gateway.registerService(userService);
        gateway.registerService(orderService);
        gateway.registerService(paymentService);
        
        // Test service calls through gateway
        System.out.println("Testing service calls through API Gateway:");
        
        // User service call
        String userResponse = gateway.callService("user-service", "GET", "/users/123");
        System.out.println("User service response: " + userResponse);
        
        // Order service call
        String orderResponse = gateway.callService("order-service", "POST", "/orders", 
            "{\"userId\":\"123\",\"items\":[\"laptop\",\"mouse\"]}");
        System.out.println("Order service response: " + orderResponse);
        
        // Payment service call
        String paymentResponse = gateway.callService("payment-service", "POST", "/payments", 
            "{\"orderId\":\"456\",\"amount\":999.99}");
        System.out.println("Payment service response: " + paymentResponse);
        
        System.out.println("---");
    }
    
    private static void testCircuitBreaker() {
        System.out.println("=== Circuit Breaker Pattern Test ===");
        
        // Create circuit breaker
        CircuitBreaker circuitBreaker = new CircuitBreaker(5, 10000, 30000);
        
        // Test successful calls
        System.out.println("Testing successful calls:");
        for (int i = 0; i < 3; i++) {
            try {
                String result = circuitBreaker.execute(() -> "Success " + (i + 1));
                System.out.println("  Call " + (i + 1) + ": " + result);
            } catch (Exception e) {
                System.out.println("  Call " + (i + 1) + " failed: " + e.getMessage());
            }
        }
        
        // Test failing calls to trigger circuit breaker
        System.out.println("\nTesting failing calls to trigger circuit breaker:");
        for (int i = 0; i < 6; i++) {
            try {
                String result = circuitBreaker.execute(() -> {
                    throw new RuntimeException("Service unavailable");
                });
                System.out.println("  Call " + (i + 1) + ": " + result);
            } catch (Exception e) {
                System.out.println("  Call " + (i + 1) + " failed: " + e.getMessage());
            }
        }
        
        // Check circuit breaker state
        System.out.println("Circuit breaker state: " + circuitBreaker.getState());
        
        // Wait for circuit breaker to open
        System.out.println("Waiting for circuit breaker to open...");
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Test circuit breaker in open state
        try {
            String result = circuitBreaker.execute(() -> "Test call");
            System.out.println("Call after timeout: " + result);
        } catch (Exception e) {
            System.out.println("Call after timeout failed: " + e.getMessage());
        }
        
        System.out.println("Circuit breaker state: " + circuitBreaker.getState());
        
        System.out.println("---");
    }
    
    private static void testFaultTolerance() {
        System.out.println("=== Fault Tolerance Test ===");
        
        // Create fault-tolerant service
        FaultTolerantService faultTolerantService = new FaultTolerantService();
        
        // Test retry mechanism
        System.out.println("Testing retry mechanism:");
        try {
            String result = faultTolerantService.executeWithRetry(() -> {
                if (Math.random() < 0.7) {
                    throw new RuntimeException("Temporary failure");
                }
                return "Success after retry";
            }, 3, 1000);
            System.out.println("  Result: " + result);
        } catch (Exception e) {
            System.out.println("  Failed after retries: " + e.getMessage());
        }
        
        // Test timeout mechanism
        System.out.println("\nTesting timeout mechanism:");
        try {
            String result = faultTolerantService.executeWithTimeout(() -> {
                try {
                    Thread.sleep(3000); // Simulate slow operation
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return "Operation completed";
            }, 2000);
            System.out.println("  Result: " + result);
        } catch (Exception e) {
            System.out.println("  Timeout occurred: " + e.getMessage());
        }
        
        System.out.println("---");
    }
    
    private static void testScalability() {
        System.out.println("=== Scalability Test ===");
        
        // Create scalable service manager
        ScalableServiceManager scalableManager = new ScalableServiceManager();
        
        // Add initial instances
        scalableManager.addServiceInstance(new UserService("user-service", "localhost", 8081));
        scalableManager.addServiceInstance(new UserService("user-service", "localhost", 8082));
        
        System.out.println("Initial instances: " + scalableManager.getServiceInstanceCount("user-service"));
        
        // Simulate high load
        System.out.println("Simulating high load...");
        scalableManager.simulateLoad("user-service", 0.8); // 80% load
        
        // Auto-scale based on load
        scalableManager.autoScale("user-service");
        System.out.println("Instances after auto-scaling: " + scalableManager.getServiceInstanceCount("user-service"));
        
        // Simulate low load
        System.out.println("Simulating low load...");
        scalableManager.simulateLoad("user-service", 0.2); // 20% load
        
        // Auto-scale down
        scalableManager.autoScale("user-service");
        System.out.println("Instances after scale-down: " + scalableManager.getServiceInstanceCount("user-service"));
        
        System.out.println("---");
    }
    
    private static void testMonitoring() {
        System.out.println("=== Monitoring and Metrics Test ===");
        
        // Create monitoring system
        MonitoringSystem monitoring = new MonitoringSystem();
        
        // Register services for monitoring
        monitoring.registerService("user-service");
        monitoring.registerService("order-service");
        monitoring.registerService("payment-service");
        
        // Simulate some metrics
        monitoring.recordMetric("user-service", "response_time", 150);
        monitoring.recordMetric("user-service", "response_time", 120);
        monitoring.recordMetric("user-service", "response_time", 180);
        
        monitoring.recordMetric("order-service", "response_time", 200);
        monitoring.recordMetric("order-service", "response_time", 250);
        
        monitoring.recordMetric("payment-service", "response_time", 300);
        monitoring.recordMetric("payment-service", "response_time", 280);
        
        // Get metrics
        System.out.println("Service Metrics:");
        for (String serviceName : monitoring.getRegisteredServices()) {
            System.out.println("  " + serviceName + ":");
            System.out.println("    Response time - Avg: " + 
                String.format("%.2f", monitoring.getAverageMetric(serviceName, "response_time")) + "ms");
            System.out.println("    Response time - Max: " + 
                monitoring.getMaxMetric(serviceName, "response_time") + "ms");
            System.out.println("    Response time - Min: " + 
                monitoring.getMinMetric(serviceName, "response_time") + "ms");
        }
        
        // Test alerts
        monitoring.setAlertThreshold("user-service", "response_time", 200);
        monitoring.recordMetric("user-service", "response_time", 250);
        
        System.out.println("---");
    }
    
    // ===== CORE INTERFACES AND CLASSES =====
    
    /**
     * Microservice interface
     */
    interface Microservice {
        String getName();
        String getHost();
        int getPort();
        String getStatus();
        String handleRequest(String method, String path, String body);
    }
    
    /**
     * Base microservice implementation
     */
    abstract static class BaseMicroservice implements Microservice {
        protected final String name;
        protected final String host;
        protected final int port;
        protected String status = "HEALTHY";
        protected int activeConnections = 0;
        
        public BaseMicroservice(String name, String host, int port) {
            this.name = name;
            this.host = host;
            this.port = port;
        }
        
        @Override
        public String getName() { return name; }
        
        @Override
        public String getHost() { return host; }
        
        @Override
        public int getPort() { return port; }
        
        @Override
        public String getStatus() { return status; }
        
        public int getActiveConnections() { return activeConnections; }
        
        public void incrementConnections() { activeConnections++; }
        
        public void decrementConnections() { activeConnections--; }
        
        @Override
        public String toString() {
            return String.format("%s{name='%s', host='%s', port=%d, status='%s', connections=%d}",
                getClass().getSimpleName(), name, host, port, status, activeConnections);
        }
    }
    
    /**
     * User service implementation
     */
    static class UserService extends BaseMicroservice {
        public UserService(String name, String host, int port) {
            super(name, host, port);
        }
        
        @Override
        public String handleRequest(String method, String path, String body) {
            incrementConnections();
            try {
                if ("GET".equals(method) && path.startsWith("/users/")) {
                    String userId = path.substring(7);
                    return "{\"userId\":\"" + userId + "\",\"name\":\"John Doe\",\"email\":\"john@example.com\"}";
                }
                return "{\"error\":\"Invalid request\"}";
            } finally {
                decrementConnections();
            }
        }
    }
    
    /**
     * Order service implementation
     */
    static class OrderService extends BaseMicroservice {
        public OrderService(String name, String host, int port) {
            super(name, host, port);
        }
        
        @Override
        public String handleRequest(String method, String path, String body) {
            incrementConnections();
            try {
                if ("POST".equals(method) && "/orders".equals(path)) {
                    return "{\"orderId\":\"456\",\"status\":\"created\",\"items\":[\"laptop\",\"mouse\"]}";
                }
                return "{\"error\":\"Invalid request\"}";
            } finally {
                decrementConnections();
            }
        }
    }
    
    /**
     * Payment service implementation
     */
    static class PaymentService extends BaseMicroservice {
        public PaymentService(String name, String host, int port) {
            super(name, host, port);
        }
        
        @Override
        public String handleRequest(String method, String path, String body) {
            incrementConnections();
            try {
                if ("POST".equals(method) && "/payments".equals(path)) {
                    return "{\"paymentId\":\"789\",\"status\":\"completed\",\"amount\":999.99}";
                }
                return "{\"error\":\"Invalid request\"}";
            } finally {
                decrementConnections();
            }
        }
    }
    
    // ===== SERVICE DISCOVERY =====
    
    /**
     * Service registry for service discovery
     */
    static class ServiceRegistry {
        private final java.util.Map<String, Microservice> services = new java.util.concurrent.ConcurrentHashMap<>();
        
        public void registerService(Microservice service) {
            services.put(service.getName(), service);
            System.out.println("Registered service: " + service.getName());
        }
        
        public void unregisterService(String serviceName) {
            services.remove(serviceName);
            System.out.println("Unregistered service: " + serviceName);
        }
        
        public Microservice discoverService(String serviceName) {
            return services.get(serviceName);
        }
        
        public java.util.Collection<Microservice> getAllServices() {
            return services.values();
        }
        
        public void checkServiceHealth() {
            System.out.println("Checking service health...");
            services.values().forEach(service -> {
                if ("HEALTHY".equals(service.getStatus())) {
                    System.out.println("  " + service.getName() + " is healthy");
                } else {
                    System.out.println("  " + service.getName() + " is unhealthy");
                }
            });
        }
    }
    
    // ===== LOAD BALANCING =====
    
    /**
     * Load balancing strategies
     */
    enum LoadBalancingStrategy {
        ROUND_ROBIN, RANDOM, LEAST_CONNECTIONS
    }
    
    /**
     * Load balancer implementation
     */
    static class LoadBalancer {
        private final java.util.List<Microservice> instances = new java.util.ArrayList<>();
        private int roundRobinIndex = 0;
        private final java.util.Random random = new java.util.Random();
        
        public void addInstance(Microservice instance) {
            instances.add(instance);
        }
        
        public void removeInstance(Microservice instance) {
            instances.remove(instance);
        }
        
        public Microservice getNextInstance(LoadBalancingStrategy strategy) {
            if (instances.isEmpty()) {
                throw new IllegalStateException("No instances available");
            }
            
            switch (strategy) {
                case ROUND_ROBIN:
                    return getNextRoundRobin();
                case RANDOM:
                    return getNextRandom();
                case LEAST_CONNECTIONS:
                    return getNextLeastConnections();
                default:
                    throw new IllegalArgumentException("Unknown strategy: " + strategy);
            }
        }
        
        private Microservice getNextRoundRobin() {
            Microservice instance = instances.get(roundRobinIndex);
            roundRobinIndex = (roundRobinIndex + 1) % instances.size();
            return instance;
        }
        
        private Microservice getNextRandom() {
            return instances.get(random.nextInt(instances.size()));
        }
        
        private Microservice getNextLeastConnections() {
            return instances.stream()
                .min(java.util.Comparator.comparingInt(Microservice::getActiveConnections))
                .orElse(instances.get(0));
        }
    }
    
    // ===== API GATEWAY =====
    
    /**
     * API Gateway for routing requests
     */
    static class ApiGateway {
        private final java.util.Map<String, Microservice> services = new java.util.HashMap<>();
        
        public void registerService(Microservice service) {
            services.put(service.getName(), service);
        }
        
        public String callService(String serviceName, String method, String path) {
            return callService(serviceName, method, path, null);
        }
        
        public String callService(String serviceName, String method, String path, String body) {
            Microservice service = services.get(serviceName);
            if (service == null) {
                return "{\"error\":\"Service not found: " + serviceName + "\"}";
            }
            
            try {
                return service.handleRequest(method, path, body);
            } catch (Exception e) {
                return "{\"error\":\"Service call failed: " + e.getMessage() + "\"}";
            }
        }
    }
    
    // ===== CIRCUIT BREAKER =====
    
    /**
     * Circuit breaker states
     */
    enum CircuitState {
        CLOSED, OPEN, HALF_OPEN
    }
    
    /**
     * Circuit breaker implementation
     */
    static class CircuitBreaker {
        private final int failureThreshold;
        private final long timeoutDuration;
        private final long resetTimeout;
        
        private CircuitState state = CircuitState.CLOSED;
        private int failureCount = 0;
        private long lastFailureTime = 0;
        
        public CircuitBreaker(int failureThreshold, long timeoutDuration, long resetTimeout) {
            this.failureThreshold = failureThreshold;
            this.timeoutDuration = timeoutDuration;
            this.resetTimeout = resetTimeout;
        }
        
        public <T> T execute(java.util.function.Supplier<T> supplier) throws Exception {
            if (state == CircuitState.OPEN) {
                if (System.currentTimeMillis() - lastFailureTime > resetTimeout) {
                    state = CircuitState.HALF_OPEN;
                } else {
                    throw new RuntimeException("Circuit breaker is OPEN");
                }
            }
            
            try {
                T result = supplier.get();
                onSuccess();
                return result;
            } catch (Exception e) {
                onFailure();
                throw e;
            }
        }
        
        private void onSuccess() {
            failureCount = 0;
            state = CircuitState.CLOSED;
        }
        
        private void onFailure() {
            failureCount++;
            lastFailureTime = System.currentTimeMillis();
            
            if (failureCount >= failureThreshold) {
                state = CircuitState.OPEN;
            }
        }
        
        public CircuitState getState() {
            return state;
        }
    }
    
    // ===== FAULT TOLERANCE =====
    
    /**
     * Fault-tolerant service implementation
     */
    static class FaultTolerantService {
        
        public <T> T executeWithRetry(java.util.function.Supplier<T> supplier, int maxRetries, long delayMs) throws Exception {
            Exception lastException = null;
            
            for (int attempt = 1; attempt <= maxRetries; attempt++) {
                try {
                    return supplier.get();
                } catch (Exception e) {
                    lastException = e;
                    if (attempt < maxRetries) {
                        try {
                            Thread.sleep(delayMs);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException("Retry interrupted", ie);
                        }
                    }
                }
            }
            
            throw new RuntimeException("Failed after " + maxRetries + " attempts", lastException);
        }
        
        public <T> T executeWithTimeout(java.util.function.Supplier<T> supplier, long timeoutMs) throws Exception {
            java.util.concurrent.Future<T> future = java.util.concurrent.Executors.newSingleThreadExecutor().submit(supplier::get);
            
            try {
                return future.get(timeoutMs, java.util.concurrent.TimeUnit.MILLISECONDS);
            } catch (java.util.concurrent.TimeoutException e) {
                future.cancel(true);
                throw new RuntimeException("Operation timed out after " + timeoutMs + "ms");
            }
        }
    }
    
    // ===== SCALABILITY =====
    
    /**
     * Scalable service manager
     */
    static class ScalableServiceManager {
        private final java.util.Map<String, java.util.List<Microservice>> serviceInstances = new java.util.HashMap<>();
        private final java.util.Map<String, Double> serviceLoads = new java.util.HashMap<>();
        
        public void addServiceInstance(Microservice instance) {
            serviceInstances.computeIfAbsent(instance.getName(), k -> new java.util.ArrayList<>()).add(instance);
        }
        
        public void removeServiceInstance(String serviceName, Microservice instance) {
            java.util.List<Microservice> instances = serviceInstances.get(serviceName);
            if (instances != null) {
                instances.remove(instance);
            }
        }
        
        public int getServiceInstanceCount(String serviceName) {
            java.util.List<Microservice> instances = serviceInstances.get(serviceName);
            return instances != null ? instances.size() : 0;
        }
        
        public void simulateLoad(String serviceName, double load) {
            serviceLoads.put(serviceName, load);
        }
        
        public void autoScale(String serviceName) {
            double load = serviceLoads.getOrDefault(serviceName, 0.0);
            java.util.List<Microservice> instances = serviceInstances.get(serviceName);
            
            if (instances == null) return;
            
            if (load > 0.7 && instances.size() < 5) {
                // Scale up
                Microservice newInstance = createNewInstance(serviceName);
                addServiceInstance(newInstance);
                System.out.println("Scaled up " + serviceName + " to " + instances.size() + " instances");
            } else if (load < 0.3 && instances.size() > 1) {
                // Scale down
                instances.remove(instances.size() - 1);
                System.out.println("Scaled down " + serviceName + " to " + instances.size() + " instances");
            }
        }
        
        private Microservice createNewInstance(String serviceName) {
            int port = 8080 + (int)(Math.random() * 1000);
            switch (serviceName) {
                case "user-service":
                    return new UserService(serviceName, "localhost", port);
                case "order-service":
                    return new OrderService(serviceName, "localhost", port);
                case "payment-service":
                    return new PaymentService(serviceName, "localhost", port);
                default:
                    throw new IllegalArgumentException("Unknown service: " + serviceName);
            }
        }
    }
    
    // ===== MONITORING =====
    
    /**
     * Monitoring system for metrics collection
     */
    static class MonitoringSystem {
        private final java.util.Set<String> registeredServices = new java.util.HashSet<>();
        private final java.util.Map<String, java.util.Map<String, java.util.List<Double>>> metrics = new java.util.HashMap<>();
        private final java.util.Map<String, java.util.Map<String, Double>> alertThresholds = new java.util.HashMap<>();
        
        public void registerService(String serviceName) {
            registeredServices.add(serviceName);
            metrics.put(serviceName, new java.util.HashMap<>());
            alertThresholds.put(serviceName, new java.util.HashMap<>());
        }
        
        public void recordMetric(String serviceName, String metricName, double value) {
            if (!metrics.containsKey(serviceName)) {
                registerService(serviceName);
            }
            
            metrics.get(serviceName).computeIfAbsent(metricName, k -> new java.util.ArrayList<>()).add(value);
            
            // Check for alerts
            checkAlert(serviceName, metricName, value);
        }
        
        public double getAverageMetric(String serviceName, String metricName) {
            java.util.List<Double> values = metrics.get(serviceName).get(metricName);
            if (values == null || values.isEmpty()) return 0.0;
            
            return values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        }
        
        public double getMaxMetric(String serviceName, String metricName) {
            java.util.List<Double> values = metrics.get(serviceName).get(metricName);
            if (values == null || values.isEmpty()) return 0.0;
            
            return values.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
        }
        
        public double getMinMetric(String serviceName, String metricName) {
            java.util.List<Double> values = metrics.get(serviceName).get(metricName);
            if (values == null || values.isEmpty()) return 0.0;
            
            return values.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
        }
        
        public void setAlertThreshold(String serviceName, String metricName, double threshold) {
            alertThresholds.get(serviceName).put(metricName, threshold);
        }
        
        private void checkAlert(String serviceName, String metricName, double value) {
            Double threshold = alertThresholds.get(serviceName).get(metricName);
            if (threshold != null && value > threshold) {
                System.out.println("ALERT: " + serviceName + " " + metricName + " exceeded threshold " + threshold + " (value: " + value + ")");
            }
        }
        
        public java.util.Set<String> getRegisteredServices() {
            return registeredServices;
        }
    }
    
    /**
     * Interview Questions to Ask:
     * 
     * 1. What are the key principles of microservices architecture?
     * 2. How would you handle data consistency across microservices?
     * 3. What if a microservice becomes unavailable?
     * 4. How would you implement distributed tracing?
     * 5. What if you need to handle eventual consistency?
     * 6. How would you implement service mesh?
     * 7. What if you need to support multiple versions of a service?
     * 8. How would you implement event-driven communication?
     * 9. What if you need to handle distributed transactions?
     * 10. How would you implement blue-green deployment?
     * 11. What if you need to support canary releases?
     * 12. How would you implement service discovery in Kubernetes?
     * 13. What if you need to handle cross-cutting concerns?
     * 14. How would you implement API versioning?
     * 15. What if you need to support polyglot persistence?
     * 16. How would you implement distributed caching?
     * 17. What if you need to handle service degradation?
     * 18. How would you implement rate limiting?
     * 19. What if you need to support multi-tenancy?
     * 20. How would you implement chaos engineering?
     */
}
