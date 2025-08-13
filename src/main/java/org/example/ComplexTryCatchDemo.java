package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Custom application exception
class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Utility to read files
class FileUtil {
    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());

    public static String readFile(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        // try-with-resources for automatic close
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to read file: " + path, e);
            throw e;
        }
        return sb.toString();
    }
}

// Simulated JSON parser (throws on invalid JSON)
class JsonParser {
    public static Data parse(String rawJson) throws ServiceException {
        if (rawJson == null || !rawJson.trim().startsWith("{")) {
            throw new ServiceException("Invalid JSON format");
        }
        // Dummy parse
        return new Data(rawJson.trim());
    }
}

// Dummy data holder
class Data {
    private final String payload;
    public Data(String payload) {
        this.payload = payload;
    }
    public String getPayload() {
        return payload;
    }
}

// Simulated DB client
class DatabaseClient {
    private static final Logger logger = Logger.getLogger(DatabaseClient.class.getName());
    private Connection conn;

    public void connect() throws SQLException {
        conn = DriverManager.getConnection("jdbc:h2:mem:demo", "sa", "");
        conn.setAutoCommit(false);
    }

    public void saveData(Data data) throws SQLException {
        String sql = "INSERT INTO DATA_TABLE (PAYLOAD) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, data.getPayload());
            ps.executeUpdate();
        }
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Commit failed", e);
        }
    }

    public void rollback() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Rollback failed", e);
        }
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Connection close failed", e);
            }
        }
    }
}

// Main processor demonstrating nested try-catch
public class ComplexTryCatchDemo {
    private static final Logger logger = Logger.getLogger(ComplexTryCatchDemo.class.getName());

    public static void main(String[] args) {
        String filePath = "input.json";
        DatabaseClient dbClient = new DatabaseClient();

        try {
            // 1. Read raw JSON
            String rawJson = FileUtil.readFile(filePath);

            // 2. Parse into Data object
            Data data = JsonParser.parse(rawJson);

            // 3. Persist to DB
            try {
                dbClient.connect();
                dbClient.saveData(data);
                dbClient.commit();
            } catch (SQLException sqle) {
                logger.log(Level.SEVERE, "Database operation failed", sqle);
                dbClient.rollback();
                throw new ServiceException("Data persistence error", sqle);
            } finally {
                dbClient.close();
            }

            logger.info("Data processed successfully: " + data.getPayload());

        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "I/O error during processing", ioe);
        } catch (ServiceException se) {
            logger.log(Level.SEVERE, "Service error: " + se.getMessage(), se);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error", e);
        } finally {
            logger.info("Processing complete");
        }
    }
}
