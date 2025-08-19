/**
 * Question 36: Java File I/O
 * 
 * This file contains File I/O interview questions covering:
 * - File and Directory Operations
 * - File Reading and Writing
 * - NIO.2 (New I/O) Features
 * - Stream-based I/O
 * - Best Practices and Advanced Concepts
 */
public class Question036_JavaFileIO {
    
    public static void main(String[] args) {
        System.out.println("=== Java File I/O - Interview Questions ===\n");
        
        demonstrateFileOperations();
        demonstrateFileReading();
        demonstrateFileWriting();
        demonstrateNIOFeatures();
        demonstrateStreamIO();
        
        System.out.println("\n=== Java File I/O Completed! ===");
    }
    
    private static void demonstrateFileOperations() {
        System.out.println("1. FILE AND DIRECTORY OPERATIONS:\n");
        
        // Q1: What are the main classes for File I/O in Java?
        System.out.println("Q1: What are the main classes for File I/O in Java?");
        System.out.println("    - File (Legacy)");
        System.out.println("    - Path, Files, Paths (NIO.2)");
        System.out.println("    - FileInputStream, FileOutputStream");
        System.out.println("    - FileReader, FileWriter\n");
        
        // Q2: What is the difference between File and Path?
        System.out.println("Q2: What is the difference between File and Path?");
        System.out.println("    File: Legacy class, less efficient");
        System.out.println("    Path: Modern interface, more efficient, immutable\n");
        
        // Demonstrate file operations
        System.out.println("Example: File Operations");
        
        try {
            // Create directory
            java.io.File dir = new java.io.File("test_directory");
            if (dir.mkdir()) {
                System.out.println("    Directory created: " + dir.getAbsolutePath());
            }
            
            // Create file
            java.io.File file = new java.io.File(dir, "test.txt");
            if (file.createNewFile()) {
                System.out.println("    File created: " + file.getName());
            }
            
            // File properties
            System.out.println("    File exists: " + file.exists());
            System.out.println("    File size: " + file.length() + " bytes");
            System.out.println("    File readable: " + file.canRead());
            System.out.println("    File writable: " + file.canWrite());
            System.out.println("    File hidden: " + file.isHidden());
            
            // List directory contents
            System.out.println("    Directory contents:");
            java.io.File[] files = dir.listFiles();
            if (files != null) {
                for (java.io.File f : files) {
                    System.out.println("      " + f.getName() + " (" + f.length() + " bytes)");
                }
            }
            
            // Cleanup
            file.delete();
            dir.delete();
            System.out.println("    Cleanup completed");
            
        } catch (java.io.IOException e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateFileReading() {
        System.out.println("\n2. FILE READING:\n");
        
        // Q3: What are the different ways to read files in Java?
        System.out.println("Q3: What are the different ways to read files in Java?");
        System.out.println("    - FileInputStream (bytes)");
        System.out.println("    - FileReader (characters)");
        System.out.println("    - BufferedReader (efficient reading)");
        System.out.println("    - Files.readAllLines() (NIO.2)\n");
        
        // Q4: What is the difference between FileInputStream and FileReader?
        System.out.println("Q4: What is the difference between FileInputStream and FileReader?");
        System.out.println("    FileInputStream: Reads raw bytes");
        System.out.println("    FileReader: Reads characters with encoding\n");
        
        // Demonstrate file reading
        System.out.println("Example: File Reading");
        
        try {
            // Create a test file with content
            java.io.File testFile = new java.io.File("test_read.txt");
            java.io.FileWriter writer = new java.io.FileWriter(testFile);
            writer.write("Hello World\nThis is a test file\nJava File I/O Demo");
            writer.close();
            
            // Read using FileReader
            System.out.println("    Reading with FileReader:");
            java.io.FileReader reader = new java.io.FileReader(testFile);
            java.io.BufferedReader bufferedReader = new java.io.BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("      " + line);
            }
            bufferedReader.close();
            
            // Read using Files.readAllLines (NIO.2)
            System.out.println("    Reading with Files.readAllLines:");
            java.nio.file.Path path = testFile.toPath();
            java.util.List<String> lines = java.nio.file.Files.readAllLines(path);
            for (String l : lines) {
                System.out.println("      " + l);
            }
            
            // Read using Files.readString (Java 11+)
            System.out.println("    Reading with Files.readString:");
            String content = java.nio.file.Files.readString(path);
            System.out.println("      Full content: " + content);
            
            // Cleanup
            testFile.delete();
            
        } catch (java.io.IOException e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateFileWriting() {
        System.out.println("\n3. FILE WRITING:\n");
        
        // Q5: What are the different ways to write files in Java?
        System.out.println("Q5: What are the different ways to write files in Java?");
        System.out.println("    - FileOutputStream (bytes)");
        System.out.println("    - FileWriter (characters)");
        System.out.println("    - BufferedWriter (efficient writing)");
        System.out.println("    - Files.write() (NIO.2)\n");
        
        // Q6: What is the difference between append and overwrite modes?
        System.out.println("Q6: What is the difference between append and overwrite modes?");
        System.out.println("    Overwrite: Replaces existing content");
        System.out.println("    Append: Adds to existing content\n");
        
        // Demonstrate file writing
        System.out.println("Example: File Writing");
        
        try {
            java.io.File testFile = new java.io.File("test_write.txt");
            
            // Write using FileWriter
            System.out.println("    Writing with FileWriter:");
            java.io.FileWriter writer = new java.io.FileWriter(testFile);
            writer.write("First line\n");
            writer.write("Second line\n");
            writer.write("Third line");
            writer.close();
            System.out.println("    File written successfully");
            
            // Append using FileWriter
            System.out.println("    Appending with FileWriter:");
            java.io.FileWriter appendWriter = new java.io.FileWriter(testFile, true);
            appendWriter.write("\nAppended line");
            appendWriter.close();
            System.out.println("    Content appended");
            
            // Write using Files.write (NIO.2)
            System.out.println("    Writing with Files.write:");
            java.nio.file.Path path = testFile.toPath();
            java.util.List<String> newLines = java.util.Arrays.asList(
                "New content line 1",
                "New content line 2",
                "New content line 3"
            );
            java.nio.file.Files.write(path, newLines);
            System.out.println("    File overwritten with new content");
            
            // Write using Files.writeString (Java 11+)
            System.out.println("    Writing with Files.writeString:");
            java.nio.file.Files.writeString(path, "Single string content");
            System.out.println("    File written with single string");
            
            // Cleanup
            testFile.delete();
            
        } catch (java.io.IOException e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateNIOFeatures() {
        System.out.println("\n4. NIO.2 FEATURES:\n");
        
        // Q7: What are the main features of NIO.2?
        System.out.println("Q7: What are the main features of NIO.2?");
        System.out.println("    - Path interface");
        System.out.println("    - Files utility class");
        System.out.println("    - DirectoryStream for iteration");
        System.out.println("    - WatchService for file system events\n");
        
        // Q8: What is the advantage of using NIO.2 over legacy File class?
        System.out.println("Q8: What is the advantage of using NIO.2 over legacy File class?");
        System.out.println("    - Better performance");
        System.out.println("    - More features (symbolic links, attributes)");
        System.out.println("    - Immutable objects");
        System.out.println("    - Better exception handling\n");
        
        // Demonstrate NIO.2 features
        System.out.println("Example: NIO.2 Features");
        
        try {
            // Create directory using NIO.2
            java.nio.file.Path dirPath = java.nio.file.Paths.get("nio_test_dir");
            java.nio.file.Files.createDirectory(dirPath);
            System.out.println("    Directory created: " + dirPath.toAbsolutePath());
            
            // Create file using NIO.2
            java.nio.file.Path filePath = dirPath.resolve("nio_test.txt");
            java.nio.file.Files.createFile(filePath);
            System.out.println("    File created: " + filePath.getFileName());
            
            // Write content using NIO.2
            java.util.List<String> content = java.util.Arrays.asList(
                "NIO.2 is modern",
                "Better than legacy File",
                "More efficient and feature-rich"
            );
            java.nio.file.Files.write(filePath, content);
            System.out.println("    Content written using NIO.2");
            
            // Read file attributes
            System.out.println("    File attributes:");
            System.out.println("      Size: " + java.nio.file.Files.size(filePath) + " bytes");
            System.out.println("      Created: " + java.nio.file.Files.getAttribute(filePath, "creationTime"));
            System.out.println("      Modified: " + java.nio.file.Files.getAttribute(filePath, "lastModifiedTime"));
            System.out.println("      Readable: " + java.nio.file.Files.isReadable(filePath));
            System.out.println("      Writable: " + java.nio.file.Files.isWritable(filePath));
            
            // Copy file
            java.nio.file.Path copyPath = dirPath.resolve("nio_test_copy.txt");
            java.nio.file.Files.copy(filePath, copyPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("    File copied to: " + copyPath.getFileName());
            
            // Move file
            java.nio.file.Path movePath = dirPath.resolve("nio_test_moved.txt");
            java.nio.file.Files.move(filePath, movePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("    File moved to: " + movePath.getFileName());
            
            // List directory contents using DirectoryStream
            System.out.println("    Directory contents using DirectoryStream:");
            try (java.nio.file.DirectoryStream<java.nio.file.Path> stream = 
                    java.nio.file.Files.newDirectoryStream(dirPath)) {
                for (java.nio.file.Path p : stream) {
                    System.out.println("      " + p.getFileName() + " (" + 
                                     java.nio.file.Files.size(p) + " bytes)");
                }
            }
            
            // Cleanup
            java.nio.file.Files.deleteIfExists(copyPath);
            java.nio.file.Files.deleteIfExists(movePath);
            java.nio.file.Files.deleteIfExists(dirPath);
            System.out.println("    Cleanup completed");
            
        } catch (java.io.IOException e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateStreamIO() {
        System.out.println("\n5. STREAM-BASED I/O:\n");
        
        // Q9: What are the advantages of using streams for I/O?
        System.out.println("Q9: What are the advantages of using streams for I/O?");
        System.out.println("    - Buffered reading/writing");
        System.out.println("    - Better performance");
        System.out.println("    - Automatic resource management");
        System.out.println("    - Chaining operations\n");
        
        // Q10: What is the difference between byte streams and character streams?
        System.out.println("Q10: What is the difference between byte streams and character streams?");
        System.out.println("    Byte streams: Handle raw binary data");
        System.out.println("    Character streams: Handle text with encoding\n");
        
        // Demonstrate stream-based I/O
        System.out.println("Example: Stream-based I/O");
        
        try {
            java.io.File testFile = new java.io.File("stream_test.txt");
            
            // Write using BufferedWriter
            System.out.println("    Writing with BufferedWriter:");
            try (java.io.BufferedWriter writer = new java.io.BufferedWriter(
                    new java.io.FileWriter(testFile))) {
                writer.write("Line 1");
                writer.newLine();
                writer.write("Line 2");
                writer.newLine();
                writer.write("Line 3");
                writer.flush(); // Ensure data is written
            }
            System.out.println("    File written with BufferedWriter");
            
            // Read using BufferedReader
            System.out.println("    Reading with BufferedReader:");
            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.FileReader(testFile))) {
                String line;
                int lineNumber = 1;
                while ((line = reader.readLine()) != null) {
                    System.out.println("      Line " + lineNumber + ": " + line);
                    lineNumber++;
                }
            }
            
            // Binary I/O with DataOutputStream
            System.out.println("    Writing binary data with DataOutputStream:");
            java.io.File binaryFile = new java.io.File("binary_test.dat");
            try (java.io.DataOutputStream dataOut = new java.io.DataOutputStream(
                    new java.io.FileOutputStream(binaryFile))) {
                dataOut.writeInt(42);
                dataOut.writeDouble(3.14);
                dataOut.writeUTF("Hello Binary World");
                dataOut.writeBoolean(true);
            }
            System.out.println("    Binary file written");
            
            // Read binary data with DataInputStream
            System.out.println("    Reading binary data with DataInputStream:");
            try (java.io.DataInputStream dataIn = new java.io.DataInputStream(
                    new java.io.FileInputStream(binaryFile))) {
                int intValue = dataIn.readInt();
                double doubleValue = dataIn.readDouble();
                String stringValue = dataIn.readUTF();
                boolean booleanValue = dataIn.readBoolean();
                
                System.out.println("      Int: " + intValue);
                System.out.println("      Double: " + doubleValue);
                System.out.println("      String: " + stringValue);
                System.out.println("      Boolean: " + booleanValue);
            }
            
            // Cleanup
            testFile.delete();
            binaryFile.delete();
            
        } catch (java.io.IOException e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA FILE I/O:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What is File I/O in Java?
 * 2. What are the main classes for File I/O?
 * 3. What is the difference between File and Path?
 * 4. How do you create a file in Java?
 * 5. How do you create a directory in Java?
 * 6. How do you check if a file exists?
 * 7. How do you get file size?
 * 8. How do you check file permissions?
 * 9. How do you delete a file?
 * 10. How do you delete a directory?
 * 11. What is the difference between FileInputStream and FileReader?
 * 12. What is the difference between FileOutputStream and FileWriter?
 * 13. How do you read a file line by line?
 * 14. How do you write text to a file?
 * 15. What is the purpose of flush() method?
 * 16. What is the purpose of close() method?
 * 17. Can you read and write files simultaneously?
 * 18. What happens if you don't close a file?
 * 19. How do you handle file I/O exceptions?
 * 20. What is the default encoding for text files?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. What are the advantages of using BufferedReader?
 * 22. What are the advantages of using BufferedWriter?
 * 23. How do you append to a file?
 * 24. How do you copy a file?
 * 25. How do you move a file?
 * 26. How do you rename a file?
 * 27. How do you list directory contents?
 * 28. How do you filter files by extension?
 * 29. How do you handle file paths on different operating systems?
 * 30. What is the difference between absolute and relative paths?
 * 31. How do you resolve relative paths?
 * 32. How do you normalize file paths?
 * 33. How do you check if a path is absolute?
 * 34. How do you get the parent directory?
 * 35. How do you get the file extension?
 * 36. How do you create temporary files?
 * 37. How do you create temporary directories?
 * 38. How do you handle symbolic links?
 * 39. How do you check file attributes?
 * 40. How do you set file attributes?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. What are the main features of NIO.2?
 * 42. How do you use DirectoryStream?
 * 43. How do you use WatchService?
 * 44. How do you handle file system events?
 * 45. How do you implement file monitoring?
 * 46. How do you handle large files efficiently?
 * 47. How do you implement file compression?
 * 48. How do you implement file encryption?
 * 49. How do you handle concurrent file access?
 * 50. How do you implement file locking?
 * 51. How do you handle file permissions?
 * 52. How do you implement file security?
 * 53. How do you handle file corruption?
 * 54. How do you implement file recovery?
 * 55. How do you handle file versioning?
 * 56. How do you implement file backup?
 * 57. How do you handle file synchronization?
 * 58. How do you implement file replication?
 * 59. How do you handle file migration?
 * 60. How do you implement file archiving?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you design a file management system?
 * 62. How do you implement file caching?
 * 63. How do you design file indexing?
 * 64. How do you implement file search?
 * 65. How do you design file compression algorithms?
 * 66. How do you implement file encryption algorithms?
 * 67. How do you design file synchronization protocols?
 * 68. How do you implement file replication protocols?
 * 69. How do you design file backup strategies?
 * 70. How do you implement file recovery mechanisms?
 * 71. How do you design file versioning systems?
 * 72. How do you implement file archiving systems?
 * 73. How do you design file security frameworks?
 * 74. How do you implement file access control?
 * 75. How do you design file audit systems?
 * 76. How do you implement file monitoring systems?
 * 77. How do you design file analytics systems?
 * 78. How do you implement file reporting systems?
 * 79. How do you design file optimization systems?
 * 80. How do you implement file maintenance systems?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a distributed file system?
 * 82. How would you implement file replication across multiple servers?
 * 83. How would you design a file backup system for a cloud platform?
 * 84. How would you implement file synchronization for mobile applications?
 * 85. How would you design a file versioning system for collaborative editing?
 * 86. How would you implement file compression for data storage systems?
 * 87. How would you design a file encryption system for secure storage?
 * 88. How would you implement file access control for multi-tenant systems?
 * 89. How would you design a file monitoring system for enterprise applications?
 * 90. How would you implement file analytics for business intelligence?
 * 91. How would you design a file migration system for legacy applications?
 * 92. How would you implement file archiving for compliance systems?
 * 93. How would you design a file recovery system for disaster recovery?
 * 94. How would you implement file optimization for performance-critical systems?
 * 95. How would you design a file security framework for financial applications?
 * 96. How would you implement file audit systems for regulatory compliance?
 * 97. How would you design a file management system for microservices architecture?
 * 98. How would you implement file handling for real-time streaming applications?
 * 99. How would you design a file system for edge computing environments?
 * 100. How would you implement file management for blockchain-based storage systems?
 */
