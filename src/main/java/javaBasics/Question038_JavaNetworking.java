/**
 * Question 38: Java Networking
 * 
 * This file contains Networking interview questions covering:
 * - Socket Programming
 * - URL and HTTP Connections
 * - Network Protocols
 * - Network Security
 * - Best Practices and Advanced Concepts
 */
public class Question038_JavaNetworking {
    
    public static void main(String[] args) {
        System.out.println("=== Java Networking - Interview Questions ===\n");
        
        demonstrateSocketProgramming();
        demonstrateURLAndHTTP();
        demonstrateNetworkProtocols();
        demonstrateNetworkSecurity();
        demonstrateBestPractices();
        
        System.out.println("\n=== Java Networking Completed! ===");
    }
    
    private static void demonstrateSocketProgramming() {
        System.out.println("1. SOCKET PROGRAMMING:\n");
        
        // Q1: What is Socket Programming in Java?
        System.out.println("Q1: What is Socket Programming in Java?");
        System.out.println("    - Low-level network communication");
        System.out.println("    - TCP/IP and UDP protocols");
        System.out.println("    - Client-server communication model\n");
        
        // Q2: What is the difference between TCP and UDP?
        System.out.println("Q2: What is the difference between TCP and UDP?");
        System.out.println("    TCP: Connection-oriented, reliable, ordered");
        System.out.println("    UDP: Connectionless, unreliable, unordered\n");
        
        // Demonstrate socket programming concepts
        System.out.println("Example: Socket Programming Concepts");
        
        try {
            // Create server socket
            java.net.ServerSocket serverSocket = new java.net.ServerSocket(0); // Random port
            int port = serverSocket.getLocalPort();
            System.out.println("    Server socket created on port: " + port);
            
            // Create client socket
            java.net.Socket clientSocket = new java.net.Socket("localhost", port);
            System.out.println("    Client socket connected to server");
            
            // Accept connection on server
            java.net.Socket serverClientSocket = serverSocket.accept();
            System.out.println("    Server accepted client connection");
            
            // Get socket information
            System.out.println("    Client address: " + clientSocket.getInetAddress());
            System.out.println("    Client port: " + clientSocket.getPort());
            System.out.println("    Local port: " + clientSocket.getLocalPort());
            
            // Check socket status
            System.out.println("    Client connected: " + clientSocket.isConnected());
            System.out.println("    Client bound: " + clientSocket.isBound());
            System.out.println("    Client closed: " + clientSocket.isClosed());
            
            // Close sockets
            clientSocket.close();
            serverClientSocket.close();
            serverSocket.close();
            System.out.println("    All sockets closed");
            
        } catch (java.io.IOException e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateURLAndHTTP() {
        System.out.println("\n2. URL AND HTTP CONNECTIONS:\n");
        
        // Q3: What are the main classes for HTTP communication?
        System.out.println("Q3: What are the main classes for HTTP communication?");
        System.out.println("    - URL and URLConnection");
        System.out.println("    - HttpURLConnection");
        System.out.println("    - HttpClient (Java 11+)");
        System.out.println("    - Third-party libraries (Apache, OkHttp)\n");
        
        // Q4: What is the difference between GET and POST requests?
        System.out.println("Q4: What is the difference between GET and POST requests?");
        System.out.println("    GET: Data in URL, limited size, cacheable");
        System.out.println("    POST: Data in body, unlimited size, not cacheable\n");
        
        // Demonstrate URL and HTTP operations
        System.out.println("Example: URL and HTTP Operations");
        
        try {
            // URL parsing and manipulation
            java.net.URL url = new java.net.URL("https://www.example.com:8080/path?param=value#fragment");
            System.out.println("    URL components:");
            System.out.println("      Protocol: " + url.getProtocol());
            System.out.println("      Host: " + url.getHost());
            System.out.println("      Port: " + url.getPort());
            System.out.println("      Path: " + url.getPath());
            System.out.println("      Query: " + url.getQuery());
            System.out.println("      Fragment: " + url.getRef());
            
            // URL encoding/decoding
            String encodedParam = java.net.URLEncoder.encode("Hello World", "UTF-8");
            String decodedParam = java.net.URLDecoder.decode(encodedParam, "UTF-8");
            System.out.println("    URL encoding/decoding:");
            System.out.println("      Original: Hello World");
            System.out.println("      Encoded: " + encodedParam);
            System.out.println("      Decoded: " + decodedParam);
            
            // HTTP connection setup (without actual connection)
            System.out.println("    HTTP connection setup:");
            System.out.println("      GET request: " + "GET " + url.getPath() + " HTTP/1.1");
            System.out.println("      Host: " + url.getHost());
            System.out.println("      User-Agent: Java-Networking-Demo");
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateNetworkProtocols() {
        System.out.println("\n3. NETWORK PROTOCOLS:\n");
        
        // Q5: What network protocols does Java support?
        System.out.println("Q5: What network protocols does Java support?");
        System.out.println("    - TCP (Transmission Control Protocol)");
        System.out.println("    - UDP (User Datagram Protocol)");
        System.out.println("    - HTTP/HTTPS");
        System.out.println("    - FTP, SMTP, POP3 (with libraries)\n");
        
        // Q6: What is the difference between blocking and non-blocking I/O?
        System.out.println("Q6: What is the difference between blocking and non-blocking I/O?");
        System.out.println("    Blocking: Thread waits for operation to complete");
        System.out.println("    Non-blocking: Thread continues without waiting\n");
        
        // Demonstrate network protocols
        System.out.println("Example: Network Protocols");
        
        try {
            // TCP socket with timeout
            java.net.Socket tcpSocket = new java.net.Socket();
            tcpSocket.setSoTimeout(5000); // 5 seconds timeout
            System.out.println("    TCP socket timeout set to: " + tcpSocket.getSoTimeout() + "ms");
            
            // UDP datagram socket
            java.net.DatagramSocket udpSocket = new java.net.DatagramSocket();
            System.out.println("    UDP socket created on port: " + udpSocket.getLocalPort());
            
            // Datagram packet creation
            String message = "Hello UDP World";
            byte[] buffer = message.getBytes();
            java.net.DatagramPacket packet = new java.net.DatagramPacket(
                buffer, buffer.length, java.net.InetAddress.getLocalHost(), 12345);
            System.out.println("    UDP packet created with message: " + message);
            System.out.println("    Packet length: " + packet.getLength() + " bytes");
            
            // Network interface information
            java.net.NetworkInterface networkInterface = java.net.NetworkInterface.getByName("lo");
            if (networkInterface != null) {
                System.out.println("    Network interface: " + networkInterface.getDisplayName());
                System.out.println("    Interface up: " + networkInterface.isUp());
                System.out.println("    Interface loopback: " + networkInterface.isLoopback());
            }
            
            // Close sockets
            tcpSocket.close();
            udpSocket.close();
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateNetworkSecurity() {
        System.out.println("\n4. NETWORK SECURITY:\n");
        
        // Q7: What security features does Java networking provide?
        System.out.println("Q7: What security features does Java networking provide?");
        System.out.println("    - SSL/TLS support");
        System.out.println("    - Certificate validation");
        System.out.println("    - Secure socket factories");
        System.out.println("    - Trust managers and key managers\n");
        
        // Q8: What is the difference between HTTP and HTTPS?
        System.out.println("Q8: What is the difference between HTTP and HTTPS?");
        System.out.println("    HTTP: Plain text, no encryption");
        System.out.println("    HTTPS: Encrypted with SSL/TLS, secure\n");
        
        // Demonstrate network security
        System.out.println("Example: Network Security");
        
        try {
            // SSL context setup
            javax.net.ssl.SSLContext sslContext = javax.net.ssl.SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);
            System.out.println("    SSL context created with protocol: " + sslContext.getProtocol());
            
            // SSL socket factory
            javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            System.out.println("    SSL socket factory created");
            
            // Trust manager setup
            javax.net.ssl.TrustManagerFactory trustManagerFactory = 
                javax.net.ssl.TrustManagerFactory.getInstance(javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((java.security.KeyStore) null);
            System.out.println("    Trust manager factory initialized");
            
            // Key manager setup
            javax.net.ssl.KeyManagerFactory keyManagerFactory = 
                javax.net.ssl.KeyManagerFactory.getInstance(javax.net.ssl.KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(null, null);
            System.out.println("    Key manager factory initialized");
            
            // Security properties
            System.out.println("    Security properties:");
            System.out.println("      SSL enabled protocols: " + 
                             java.util.Arrays.toString(sslContext.getDefaultSSLParameters().getProtocols()));
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateBestPractices() {
        System.out.println("\n5. BEST PRACTICES AND ADVANCED CONCEPTS:\n");
        
        // Q9: What are the best practices for Java networking?
        System.out.println("Q9: What are the best practices for Java networking?");
        System.out.println("    - Always close sockets and connections");
        System.out.println("    - Use try-with-resources");
        System.out.println("    - Handle exceptions properly");
        System.out.println("    - Set appropriate timeouts\n");
        
        // Q10: How do you handle network failures and retries?
        System.out.println("Q10: How do you handle network failures and retries?");
        System.out.println("    - Implement exponential backoff");
        System.out.println("    - Set maximum retry attempts");
        System.out.println("    - Handle different types of failures");
        System.out.println("    - Log failures for debugging\n");
        
        // Demonstrate best practices
        System.out.println("Example: Best Practices");
        
        try {
            // Connection pooling concept
            System.out.println("    Connection pooling:");
            System.out.println("      Max connections: 10");
            System.out.println("      Connection timeout: 5000ms");
            System.out.println("      Read timeout: 10000ms");
            System.out.println("      Keep-alive: true");
            
            // Retry mechanism
            System.out.println("    Retry mechanism:");
            System.out.println("      Max retries: 3");
            System.out.println("      Initial delay: 1000ms");
            System.out.println("      Exponential backoff: true");
            System.out.println("      Max delay: 10000ms");
            
            // Error handling
            System.out.println("    Error handling:");
            System.out.println("      Network errors: Retry with backoff");
            System.out.println("      Timeout errors: Increase timeout");
            System.out.println("      Security errors: Log and fail fast");
            System.out.println("      Resource errors: Clean up and retry");
            
            // Monitoring and logging
            System.out.println("    Monitoring and logging:");
            System.out.println("      Connection attempts: Logged");
            System.out.println("      Response times: Measured");
            System.out.println("      Error rates: Tracked");
            System.out.println("      Performance metrics: Collected");
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    // ===== HELPER CLASSES =====
    
    // Simple HTTP client implementation
    static class SimpleHttpClient {
        private final int connectTimeout;
        private final int readTimeout;
        
        public SimpleHttpClient(int connectTimeout, int readTimeout) {
            this.connectTimeout = connectTimeout;
            this.readTimeout = readTimeout;
        }
        
        public String sendGetRequest(String urlString) throws Exception {
            java.net.URL url = new java.net.URL(urlString);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            
            try {
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(connectTimeout);
                connection.setReadTimeout(readTimeout);
                connection.setRequestProperty("User-Agent", "Java-Networking-Demo");
                
                int responseCode = connection.getResponseCode();
                System.out.println("      Response code: " + responseCode);
                
                if (responseCode == 200) {
                    java.io.BufferedReader reader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    return response.toString();
                } else {
                    return "Error: HTTP " + responseCode;
                }
            } finally {
                connection.disconnect();
            }
        }
    }
    
    // Network utility class
    static class NetworkUtils {
        public static boolean isPortAvailable(int port) {
            try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(port)) {
                return true;
            } catch (java.io.IOException e) {
                return false;
            }
        }
        
        public static String getLocalHostAddress() {
            try {
                return java.net.InetAddress.getLocalHost().getHostAddress();
            } catch (java.net.UnknownHostException e) {
                return "127.0.0.1";
            }
        }
        
        public static boolean isReachable(String host, int timeout) {
            try {
                java.net.InetAddress address = java.net.InetAddress.getByName(host);
                return address.isReachable(timeout);
            } catch (Exception e) {
                return false;
            }
        }
    }
    
    // Connection pool implementation
    static class ConnectionPool {
        private final java.util.Queue<java.net.Socket> availableConnections;
        private final java.util.Set<java.net.Socket> activeConnections;
        private final int maxConnections;
        
        public ConnectionPool(int maxConnections) {
            this.maxConnections = maxConnections;
            this.availableConnections = new java.util.LinkedList<>();
            this.activeConnections = new java.util.HashSet<>();
        }
        
        public synchronized java.net.Socket getConnection() {
            if (!availableConnections.isEmpty()) {
                java.net.Socket socket = availableConnections.poll();
                activeConnections.add(socket);
                return socket;
            } else if (activeConnections.size() < maxConnections) {
                // Create new connection
                java.net.Socket socket = new java.net.Socket();
                activeConnections.add(socket);
                return socket;
            }
            return null; // Pool is full
        }
        
        public synchronized void releaseConnection(java.net.Socket socket) {
            if (activeConnections.remove(socket)) {
                if (availableConnections.size() < maxConnections / 2) {
                    availableConnections.offer(socket);
                } else {
                    try {
                        socket.close();
                    } catch (java.io.IOException e) {
                        // Log error
                    }
                }
            }
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA NETWORKING:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What is Networking in Java?
 * 2. What is a Socket in Java?
 * 3. What is the difference between TCP and UDP?
 * 4. What is ServerSocket used for?
 * 5. What is Socket used for?
 * 6. How do you create a server in Java?
 * 7. How do you create a client in Java?
 * 8. What is the purpose of accept() method?
 * 9. What is the purpose of connect() method?
 * 10. How do you send data through a socket?
 * 11. How do you receive data through a socket?
 * 12. What is the purpose of getInputStream()?
 * 13. What is the purpose of getOutputStream()?
 * 14. How do you close a socket?
 * 15. What is the purpose of getLocalPort()?
 * 16. What is the purpose of getPort()?
 * 17. What is the purpose of getInetAddress()?
 * 18. How do you check if a socket is connected?
 * 19. How do you check if a socket is closed?
 * 20. What is the purpose of setSoTimeout()?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. What is URL class used for?
 * 22. What is URLConnection used for?
 * 23. What is HttpURLConnection used for?
 * 24. How do you make HTTP GET requests?
 * 25. How do you make HTTP POST requests?
 * 26. How do you handle HTTP responses?
 * 27. How do you set HTTP headers?
 * 28. How do you handle HTTP cookies?
 * 29. What is the purpose of setRequestMethod()?
 * 30. What is the purpose of setRequestProperty()?
 * 31. How do you handle HTTP redirects?
 * 32. How do you implement connection pooling?
 * 33. How do you handle connection timeouts?
 * 34. How do you implement retry mechanisms?
 * 35. How do you handle network failures?
 * 36. What is DatagramSocket used for?
 * 37. What is DatagramPacket used for?
 * 38. How do you send UDP packets?
 * 39. How do you receive UDP packets?
 * 40. How do you handle UDP packet loss?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. What is SSL/TLS in Java networking?
 * 42. How do you implement HTTPS connections?
 * 43. How do you handle SSL certificates?
 * 44. How do you implement custom trust managers?
 * 45. How do you implement custom key managers?
 * 46. How do you handle SSL handshake failures?
 * 47. How do you implement secure socket factories?
 * 48. How do you handle SSL session management?
 * 49. How do you implement client authentication?
 * 50. How do you implement server authentication?
 * 51. What is non-blocking I/O in networking?
 * 52. How do you implement asynchronous networking?
 * 53. How do you use NIO for networking?
 * 54. How do you implement multiplexing?
 * 55. How do you handle multiple connections?
 * 56. How do you implement network protocols?
 * 57. How do you handle protocol versioning?
 * 58. How do you implement custom protocols?
 * 59. How do you handle protocol errors?
 * 60. How do you implement protocol validation?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you design a networking framework?
 * 62. How do you implement network load balancing?
 * 63. How do you design network failover systems?
 * 64. How do you implement network monitoring?
 * 65. How do you design network security frameworks?
 * 66. How do you implement network encryption?
 * 67. How do you design network authentication systems?
 * 68. How do you implement network authorization?
 * 69. How do you design network audit systems?
 * 70. How do you implement network logging?
 * 71. How do you design network performance systems?
 * 72. How do you implement network optimization?
 * 73. How do you design network testing frameworks?
 * 74. How do you implement network debugging?
 * 75. How do you design network profiling systems?
 * 76. How do you implement network benchmarking?
 * 77. How do you design network documentation systems?
 * 78. How do you implement network maintenance?
 * 79. How do you design network deployment systems?
 * 80. How do you implement network evolution?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a networking system for a large-scale application?
 * 82. How would you implement networking in a microservices architecture?
 * 83. How would you design networking for a distributed system?
 * 84. How would you implement networking in a cloud-native application?
 * 85. How would you design networking for a real-time system?
 * 86. How would you implement networking in a high-availability system?
 * 87. How would you design networking for a fault-tolerant system?
 * 88. How would you implement networking in a scalable system?
 * 89. How would you design networking for a secure system?
 * 90. How would you implement networking in a compliant system?
 * 91. How would you design networking for a monitored system?
 * 92. How would you implement networking in a logged system?
 * 93. How would you design networking for a audited system?
 * 94. How would you implement networking in a traced system?
 * 95. How would you design networking for a profiled system?
 * 96. How would you implement networking in a debugged system?
 * 97. How would you design networking for a tested system?
 * 98. How would you implement networking in a deployed system?
 * 99. How would you design networking for a maintained system?
 * 100. How would you implement networking in a evolved system?
 */
