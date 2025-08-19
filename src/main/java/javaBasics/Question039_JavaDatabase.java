/**
 * Question 39: Java Database Programming
 * 
 * This file contains Database Programming interview questions covering:
 * - JDBC Basics and Connection Management
 * - SQL Operations and Prepared Statements
 * - Connection Pooling and Transactions
 * - Database Design and Optimization
 * - Best Practices and Advanced Concepts
 */
public class Question039_JavaDatabase {
    
    public static void main(String[] args) {
        System.out.println("=== Java Database Programming - Interview Questions ===\n");
        
        demonstrateJdbcBasics();
        demonstrateSqlOperations();
        demonstrateConnectionPooling();
        demonstrateTransactions();
        demonstrateBestPractices();
        
        System.out.println("\n=== Java Database Programming Completed! ===");
    }
    
    private static void demonstrateJdbcBasics() {
        System.out.println("1. JDBC BASICS AND CONNECTION MANAGEMENT:\n");
        
        // Q1: What is JDBC in Java?
        System.out.println("Q1: What is JDBC in Java?");
        System.out.println("    - Java Database Connectivity");
        System.out.println("    - API for database access");
        System.out.println("    - Database-independent interface\n");
        
        // Q2: What are the main components of JDBC?
        System.out.println("Q2: What are the main components of JDBC?");
        System.out.println("    - DriverManager: Manages database drivers");
        System.out.println("    - Connection: Database connection");
        System.out.println("    - Statement: SQL execution");
        System.out.println("    - ResultSet: Query results\n");
        
        // Demonstrate JDBC concepts
        System.out.println("Example: JDBC Concepts");
        
        try {
            // Driver registration (not needed for modern JDBC drivers)
            System.out.println("    Driver registration:");
            System.out.println("      Modern JDBC drivers auto-register");
            System.out.println("      Manual registration: Class.forName()");
            
            // Connection string format
            String connectionString = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
            System.out.println("    Connection string format:");
            System.out.println("      " + connectionString);
            System.out.println("      Protocol: jdbc:mysql");
            System.out.println("      Host: localhost");
            System.out.println("      Port: 3306");
            System.out.println("      Database: testdb");
            System.out.println("      Parameters: useSSL=false");
            
            // JDBC URL patterns
            System.out.println("    JDBC URL patterns:");
            System.out.println("      MySQL: jdbc:mysql://host:port/database");
            System.out.println("      PostgreSQL: jdbc:postgresql://host:port/database");
            System.out.println("      Oracle: jdbc:oracle:thin:@host:port:database");
            System.out.println("      SQL Server: jdbc:sqlserver://host:port;database=database");
            
            // Connection properties
            System.out.println("    Connection properties:");
            System.out.println("      autoCommit: true/false");
            System.out.println("      readOnly: true/false");
            System.out.println("      transactionIsolation: READ_COMMITTED");
            System.out.println("      holdability: CLOSE_CURSORS_AT_COMMIT");
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateSqlOperations() {
        System.out.println("\n2. SQL OPERATIONS AND PREPARED STATEMENTS:\n");
        
        // Q3: What are the different types of SQL statements?
        System.out.println("Q3: What are the different types of SQL statements?");
        System.out.println("    - DDL: CREATE, ALTER, DROP (Data Definition)");
        System.out.println("    - DML: INSERT, UPDATE, DELETE (Data Manipulation)");
        System.out.println("    - DQL: SELECT (Data Query)");
        System.out.println("    - DCL: GRANT, REVOKE (Data Control)\n");
        
        // Q4: What is the difference between Statement and PreparedStatement?
        System.out.println("Q4: What is the difference between Statement and PreparedStatement?");
        System.out.println("    Statement: Simple SQL execution");
        System.out.println("    PreparedStatement: Pre-compiled, parameterized, secure\n");
        
        // Demonstrate SQL operations
        System.out.println("Example: SQL Operations");
        
        try {
            // DDL operations
            System.out.println("    DDL Operations:");
            System.out.println("      CREATE TABLE users (");
            System.out.println("        id INT PRIMARY KEY AUTO_INCREMENT,");
            System.out.println("        name VARCHAR(100) NOT NULL,");
            System.out.println("        email VARCHAR(255) UNIQUE,");
            System.out.println("        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP");
            System.out.println("      )");
            
            // DML operations
            System.out.println("    DML Operations:");
            System.out.println("      INSERT INTO users (name, email) VALUES (?, ?)");
            System.out.println("      UPDATE users SET name = ? WHERE id = ?");
            System.out.println("      DELETE FROM users WHERE id = ?");
            
            // DQL operations
            System.out.println("    DQL Operations:");
            System.out.println("      SELECT * FROM users WHERE email = ?");
            System.out.println("      SELECT COUNT(*) FROM users");
            System.out.println("      SELECT name, email FROM users ORDER BY created_at DESC");
            
            // PreparedStatement benefits
            System.out.println("    PreparedStatement Benefits:");
            System.out.println("      - SQL injection prevention");
            System.out.println("      - Better performance (pre-compiled)");
            System.out.println("      - Automatic parameter escaping");
            System.out.println("      - Reusable for multiple executions");
            
            // Parameter types
            System.out.println("    Parameter Types:");
            System.out.println("      setString(int, String)");
            System.out.println("      setInt(int, int)");
            System.out.println("      setLong(int, long)");
            System.out.println("      setDouble(int, double)");
            System.out.println("      setDate(int, Date)");
            System.out.println("      setTimestamp(int, Timestamp)");
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateConnectionPooling() {
        System.out.println("\n3. CONNECTION POOLING AND TRANSACTIONS:\n");
        
        // Q5: What is Connection Pooling and why is it important?
        System.out.println("Q5: What is Connection Pooling and why is it important?");
        System.out.println("    - Reuses database connections");
        System.out.println("    - Improves performance");
        System.out.println("    - Reduces connection overhead");
        System.out.println("    - Manages connection lifecycle\n");
        
        // Q6: What are the benefits of using connection pools?
        System.out.println("Q6: What are the benefits of using connection pools?");
        System.out.println("    - Faster response times");
        System.out.println("    - Better resource utilization");
        System.out.println("    - Connection validation");
        System.out.println("    - Load balancing\n");
        
        // Demonstrate connection pooling
        System.out.println("Example: Connection Pooling");
        
        try {
            // Connection pool configuration
            System.out.println("    Connection Pool Configuration:");
            System.out.println("      Initial pool size: 5");
            System.out.println("      Maximum pool size: 20");
            System.out.println("      Minimum idle connections: 2");
            System.out.println("      Connection timeout: 30 seconds");
            System.out.println("      Idle timeout: 10 minutes");
            System.out.println("      Max lifetime: 1 hour");
            
            // Pool management
            System.out.println("    Pool Management:");
            System.out.println("      - Connection acquisition");
            System.out.println("      - Connection validation");
            System.out.println("      - Connection return");
            System.out.println("      - Pool expansion/shrinking");
            
            // Popular connection pool libraries
            System.out.println("    Popular Connection Pool Libraries:");
            System.out.println("      - HikariCP (Fast, lightweight)");
            System.out.println("      - Apache DBCP (Mature, feature-rich)");
            System.out.println("      - C3P0 (Legacy, but stable)");
            System.out.println("      - Tomcat JDBC Pool (Tomcat-based)");
            
            // Pool monitoring
            System.out.println("    Pool Monitoring:");
            System.out.println("      - Active connections");
            System.out.println("      - Idle connections");
            System.out.println("      - Connection wait time");
            System.out.println("      - Connection failure rate");
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateTransactions() {
        System.out.println("\n4. TRANSACTIONS AND CONCURRENCY:\n");
        
        // Q7: What are database transactions and ACID properties?
        System.out.println("Q7: What are database transactions and ACID properties?");
        System.out.println("    - Atomicity: All or nothing");
        System.out.println("    - Consistency: Valid state transitions");
        System.out.println("    - Isolation: Concurrent execution");
        System.out.println("    - Durability: Permanent changes\n");
        
        // Q8: What are transaction isolation levels?
        System.out.println("Q8: What are transaction isolation levels?");
        System.out.println("    - READ_UNCOMMITTED: Lowest isolation");
        System.out.println("    - READ_COMMITTED: Default level");
        System.out.println("    - REPEATABLE_READ: Consistent reads");
        System.out.println("    - SERIALIZABLE: Highest isolation\n");
        
        // Demonstrate transactions
        System.out.println("Example: Transactions");
        
        try {
            // Transaction management
            System.out.println("    Transaction Management:");
            System.out.println("      - Begin transaction");
            System.out.println("      - Execute SQL statements");
            System.out.println("      - Commit or rollback");
            System.out.println("      - Handle exceptions");
            
            // Transaction boundaries
            System.out.println("    Transaction Boundaries:");
            System.out.println("      try {");
            System.out.println("        connection.setAutoCommit(false);");
            System.out.println("        // Execute SQL statements");
            System.out.println("        connection.commit();");
            System.out.println("      } catch (Exception e) {");
            System.out.println("        connection.rollback();");
            System.out.println("        throw e;");
            System.out.println("      } finally {");
            System.out.println("        connection.setAutoCommit(true);");
            System.out.println("      }");
            
            // Savepoints
            System.out.println("    Savepoints:");
            System.out.println("      - Create savepoint: Savepoint sp = connection.setSavepoint()");
            System.out.println("      - Rollback to savepoint: connection.rollback(sp)");
            System.out.println("      - Release savepoint: connection.releaseSavepoint(sp)");
            
            // Concurrency issues
            System.out.println("    Concurrency Issues:");
            System.out.println("      - Dirty reads: Uncommitted data");
            System.out.println("      - Non-repeatable reads: Data changes between reads");
            System.out.println("      - Phantom reads: New rows appear");
            System.out.println("      - Lost updates: Overwritten changes");
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateBestPractices() {
        System.out.println("\n5. BEST PRACTICES AND ADVANCED CONCEPTS:\n");
        
        // Q9: What are the best practices for JDBC programming?
        System.out.println("Q9: What are the best practices for JDBC programming?");
        System.out.println("    - Use try-with-resources");
        System.out.println("    - Always close resources");
        System.out.println("    - Use PreparedStatement for user input");
        System.out.println("    - Handle exceptions properly\n");
        
        // Q10: How do you optimize database performance in Java?
        System.out.println("Q10: How do you optimize database performance in Java?");
        System.out.println("    - Use connection pooling");
        System.out.println("    - Optimize SQL queries");
        System.out.println("    - Use batch operations");
        System.out.println("    - Implement caching strategies\n");
        
        // Demonstrate best practices
        System.out.println("Example: Best Practices");
        
        try {
            // Resource management
            System.out.println("    Resource Management:");
            System.out.println("      - Use try-with-resources");
            System.out.println("      - Close ResultSet, Statement, Connection");
            System.out.println("      - Handle SQLExceptions properly");
            System.out.println("      - Log database operations");
            
            // SQL optimization
            System.out.println("    SQL Optimization:");
            System.out.println("      - Use appropriate indexes");
            System.out.println("      - Avoid SELECT *");
            System.out.println("      - Use LIMIT for large result sets");
            System.out.println("      - Optimize JOIN operations");
            
            // Batch operations
            System.out.println("    Batch Operations:");
            System.out.println("      - addBatch() for multiple statements");
            System.out.println("      - executeBatch() for execution");
            System.out.println("      - getGeneratedKeys() for auto-generated IDs");
            System.out.println("      - Batch size optimization");
            
            // Error handling
            System.out.println("    Error Handling:");
            System.out.println("      - SQLException: Database errors");
            System.out.println("      - SQLWarning: Database warnings");
            System.out.println("      - DataTruncation: Data truncation");
            System.out.println("      - BatchUpdateException: Batch errors");
            
            // Security considerations
            System.out.println("    Security Considerations:");
            System.out.println("      - Use PreparedStatement");
            System.out.println("      - Validate user input");
            System.out.println("      - Use least privilege principle");
            System.out.println("      - Encrypt sensitive data");
            
        } catch (Exception e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    // ===== HELPER CLASSES =====
    
    // Database connection manager
    static class DatabaseManager {
        private static final String URL = "jdbc:mysql://localhost:3306/testdb";
        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";
        
        public static java.sql.Connection getConnection() throws java.sql.SQLException {
            return java.sql.DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        
        public static void closeConnection(java.sql.Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (java.sql.SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
    
    // User entity class
    static class User {
        private int id;
        private String name;
        private String email;
        private java.sql.Timestamp createdAt;
        
        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
        
        public User(int id, String name, String email, java.sql.Timestamp createdAt) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.createdAt = createdAt;
        }
        
        // Getters and setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public java.sql.Timestamp getCreatedAt() { return createdAt; }
        public void setCreatedAt(java.sql.Timestamp createdAt) { this.createdAt = createdAt; }
        
        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "', email='" + email + "', createdAt=" + createdAt + "}";
        }
    }
    
    // User DAO (Data Access Object)
    static class UserDAO {
        public void createUser(User user) throws java.sql.SQLException {
            String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
            
            try (java.sql.Connection connection = DatabaseManager.getConnection();
                 java.sql.PreparedStatement pstmt = connection.prepareStatement(sql, 
                     java.sql.Statement.RETURN_GENERATED_KEYS)) {
                
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getEmail());
                
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    try (java.sql.ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            user.setId(rs.getInt(1));
                        }
                    }
                }
            }
        }
        
        public User getUserById(int id) throws java.sql.SQLException {
            String sql = "SELECT * FROM users WHERE id = ?";
            
            try (java.sql.Connection connection = DatabaseManager.getConnection();
                 java.sql.PreparedStatement pstmt = connection.prepareStatement(sql)) {
                
                pstmt.setInt(1, id);
                
                try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getTimestamp("created_at")
                        );
                    }
                }
            }
            return null;
        }
        
        public java.util.List<User> getAllUsers() throws java.sql.SQLException {
            String sql = "SELECT * FROM users ORDER BY created_at DESC";
            java.util.List<User> users = new java.util.ArrayList<>();
            
            try (java.sql.Connection connection = DatabaseManager.getConnection();
                 java.sql.PreparedStatement pstmt = connection.prepareStatement(sql);
                 java.sql.ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at")
                    ));
                }
            }
            return users;
        }
        
        public void updateUser(User user) throws java.sql.SQLException {
            String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
            
            try (java.sql.Connection connection = DatabaseManager.getConnection();
                 java.sql.PreparedStatement pstmt = connection.prepareStatement(sql)) {
                
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getEmail());
                pstmt.setInt(3, user.getId());
                
                pstmt.executeUpdate();
            }
        }
        
        public void deleteUser(int id) throws java.sql.SQLException {
            String sql = "DELETE FROM users WHERE id = ?";
            
            try (java.sql.Connection connection = DatabaseManager.getConnection();
                 java.sql.PreparedStatement pstmt = connection.prepareStatement(sql)) {
                
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        }
    }
    
    // Transaction manager
    static class TransactionManager {
        public static void executeInTransaction(java.util.function.Consumer<java.sql.Connection> operation) {
            java.sql.Connection connection = null;
            try {
                connection = DatabaseManager.getConnection();
                connection.setAutoCommit(false);
                
                operation.accept(connection);
                
                connection.commit();
            } catch (Exception e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (java.sql.SQLException rollbackEx) {
                        System.err.println("Error during rollback: " + rollbackEx.getMessage());
                    }
                }
                throw new RuntimeException("Transaction failed", e);
            } finally {
                if (connection != null) {
                    try {
                        connection.setAutoCommit(true);
                        connection.close();
                    } catch (java.sql.SQLException e) {
                        System.err.println("Error closing connection: " + e.getMessage());
                    }
                }
            }
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA DATABASE PROGRAMMING:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What is JDBC in Java?
 * 2. What are the main components of JDBC?
 * 3. How do you connect to a database using JDBC?
 * 4. What is the purpose of DriverManager?
 * 5. What is the purpose of Connection interface?
 * 6. What is the purpose of Statement interface?
 * 7. What is the purpose of ResultSet interface?
 * 8. How do you execute a SELECT query?
 * 9. How do you execute an INSERT query?
 * 10. How do you execute an UPDATE query?
 * 11. How do you execute a DELETE query?
 * 12. What is the difference between Statement and PreparedStatement?
 * 13. How do you handle database exceptions?
 * 14. How do you close database resources?
 * 15. What is the purpose of setAutoCommit()?
 * 16. How do you get the number of affected rows?
 * 17. How do you get auto-generated keys?
 * 18. What is the purpose of getConnection()?
 * 19. How do you check if a connection is closed?
 * 20. What is the purpose of isReadOnly()?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. What is Connection Pooling?
 * 22. What are the benefits of connection pooling?
 * 23. How do you implement connection pooling?
 * 24. What is the purpose of DataSource?
 * 25. How do you configure connection pool properties?
 * 26. What are database transactions?
 * 27. What are ACID properties?
 * 28. How do you manage transactions in JDBC?
 * 29. What is the purpose of commit() and rollback()?
 * 30. What are transaction isolation levels?
 * 31. How do you handle transaction exceptions?
 * 32. What is the purpose of setTransactionIsolation()?
 * 33. How do you use savepoints?
 * 34. What is batch processing in JDBC?
 * 35. How do you implement batch operations?
 * 36. What is the purpose of addBatch() and executeBatch()?
 * 37. How do you handle batch exceptions?
 * 38. What is the purpose of getGeneratedKeys()?
 * 39. How do you implement connection validation?
 * 40. What is the purpose of isValid()?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you implement database connection pooling?
 * 42. How do you handle connection pool monitoring?
 * 43. How do you implement connection pool failover?
 * 44. How do you handle database clustering?
 * 45. How do you implement read-write splitting?
 * 46. How do you handle database sharding?
 * 47. How do you implement database replication?
 * 48. How do you handle database load balancing?
 * 49. How do you implement database caching?
 * 50. How do you handle database performance tuning?
 * 51. How do you implement database security?
 * 52. How do you handle database encryption?
 * 53. How do you implement database auditing?
 * 54. How do you handle database backup and recovery?
 * 55. How do you implement database migration?
 * 56. How do you handle database versioning?
 * 57. How do you implement database testing?
 * 58. How do you handle database debugging?
 * 59. How do you implement database profiling?
 * 60. How do you handle database monitoring?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you design a database access layer?
 * 62. How do you implement database abstraction?
 * 63. How do you design database connection management?
 * 64. How do you implement database resource management?
 * 65. How do you design database error handling?
 * 66. How do you implement database recovery mechanisms?
 * 67. How do you design database security frameworks?
 * 68. How do you implement database access control?
 * 69. How do you design database audit systems?
 * 70. How do you implement database logging?
 * 71. How do you design database performance systems?
 * 72. How do you implement database optimization?
 * 73. How do you design database testing frameworks?
 * 74. How do you implement database debugging?
 * 75. How do you design database profiling systems?
 * 76. How do you implement database benchmarking?
 * 77. How do you design database documentation systems?
 * 78. How do you implement database maintenance?
 * 79. How do you design database deployment systems?
 * 80. How do you implement database evolution?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a database system for a large-scale application?
 * 82. How would you implement database access in a microservices architecture?
 * 83. How would you design database systems for distributed applications?
 * 84. How would you implement database access in cloud-native applications?
 * 85. How would you design database systems for real-time applications?
 * 86. How would you implement database access in high-availability systems?
 * 87. How would you design database systems for fault-tolerant applications?
 * 88. How would you implement database access in scalable systems?
 * 89. How would you design database systems for secure applications?
 * 90. How would you implement database access in compliant systems?
 * 91. How would you design database systems for monitored applications?
 * 92. How would you implement database access in logged systems?
 * 93. How would you design database systems for audited applications?
 * 94. How would you implement database access in traced systems?
 * 95. How would you design database systems for profiled applications?
 * 96. How would you implement database access in debugged systems?
 * 97. How would you design database systems for tested applications?
 * 98. How would you implement database access in deployed systems?
 * 99. How would you design database systems for maintained applications?
 * 100. How would you implement database access in evolved systems?
 */
