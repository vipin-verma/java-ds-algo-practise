/**
 * Question 53: Java String Templates
 * 
 * This file contains String Templates interview questions covering:
 * - String Templates Basics and Syntax
 * - String Templates Features and Benefits
 * - String Templates Usage Patterns
 * - Best Practices
 */
public class Question053_JavaStringTemplates {
    
    public static void main(String[] args) {
        System.out.println("=== Java String Templates - Interview Questions ===\n");
        
        demonstrateStringTemplateBasics();
        demonstrateStringTemplateFeatures();
        demonstrateStringTemplateUsage();
        demonstrateBestPractices();
        
        System.out.println("\n=== Java String Templates Completed! ===");
    }
    
    private static void demonstrateStringTemplateBasics() {
        System.out.println("1. STRING TEMPLATES BASICS AND SYNTAX:\n");
        
        // Q1: What are Java String Templates?
        System.out.println("Q1: What are Java String Templates?");
        System.out.println("    Introduced in Java 21 as a preview feature");
        System.out.println("    Provide a more readable way to create strings");
        System.out.println("    Allow embedded expressions in string literals\n");
        
        // Q2: What is the syntax of Java String Templates?
        System.out.println("Q2: What is the syntax of Java String Templates?");
        System.out.println("    String result = STR.\"Hello \{name}, you are \{age} years old\";");
        System.out.println("    String result = STR.\"Value: \{expression}\";");
        System.out.println("    Note: STR is a template processor\n");
        
        // Demonstrate String Template basics
        System.out.println("Example: Basic String Template Declaration");
        
        // Show template processors
        System.out.println("    Template Processors:");
        System.out.println("      - STR: Standard string template processor");
        System.out.println("      - FMT: Formatted string template processor");
        System.out.println("      - RAW: Raw string template processor");
        System.out.println("      - Custom: User-defined template processors");
        
        // Demonstrate basic usage
        System.out.println("    Basic Usage:");
        System.out.println("      - Embed variables: \\{variableName}");
        System.out.println("      - Embed expressions: \\{expression}");
        System.out.println("      - Embed method calls: \\{methodCall()}");
        System.out.println("      - Embed calculations: \\{a + b}");
        
        // Demonstrate template structure
        System.out.println("    Template Structure:");
        System.out.println("      - Template processor (STR, FMT, etc.)");
        System.out.println("      - Dot operator (.)");
        System.out.println("      - Template literal (\"...\")");
        System.out.println("      - Embedded expressions (\\{...})");
    }
    
    private static void demonstrateStringTemplateFeatures() {
        System.out.println("\n2. STRING TEMPLATES FEATURES AND BENEFITS:\n");
        
        // Q3: What are the key features of Java String Templates?
        System.out.println("Q3: What are the key features of Java String Templates?");
        System.out.println("    - Embedded expressions");
        System.out.println("    - Type safety");
        System.out.println("    - Performance optimization");
        System.out.println("    - Custom template processors\n");
        
        // Q4: What are the benefits of using Java String Templates?
        System.out.println("Q4: What are the benefits of using Java String Templates?");
        System.out.println("    - More readable code");
        System.out.println("    - Better performance than concatenation");
        System.out.println("    - Type safety at compile time");
        System.out.println("    - Extensible design\n");
        
        // Demonstrate String Template features
        System.out.println("Example: String Template Features and Benefits");
        
        // Readability features
        System.out.println("    Readability Features:");
        System.out.println("      - Clear expression boundaries");
        System.out.println("      - Natural language flow");
        System.out.println("      - Reduced escape sequences");
        System.out.println("      - Better code formatting");
        
        // Performance features
        System.out.println("    Performance Features:");
        System.out.println("      - Compile-time optimization");
        System.out.println("      - Reduced object creation");
        System.out.println("      - Efficient string building");
        System.out.println("      - StringBuilder optimization");
        
        // Type safety features
        System.out.println("    Type Safety Features:");
        System.out.println("      - Compile-time type checking");
        System.out.println("      - Expression validation");
        System.out.println("      - Error detection");
        System.out.println("      - IDE support");
    }
    
    private static void demonstrateStringTemplateUsage() {
        System.out.println("\n3. STRING TEMPLATES USAGE PATTERNS:\n");
        
        // Q5: How do you use different template processors?
        System.out.println("Q5: How do you use different template processors?");
        System.out.println("    1. STR for standard strings");
        System.out.println("    2. FMT for formatted strings");
        System.out.println("    3. RAW for raw strings");
        System.out.println("    4. Custom for specialized needs\n");
        
        // Q6: How do you create custom template processors?
        System.out.println("Q6: How do you create custom template processors?");
        System.out.println("    - Implement StringTemplate.Processor interface");
        System.out.println("    - Define custom processing logic");
        System.out.println("    - Handle template validation");
        System.out.println("    - Return processed results\n");
        
        // Demonstrate String Template usage patterns
        System.out.println("Example: String Template Usage Patterns");
        
        // Standard template pattern
        System.out.println("    Standard Template Pattern:");
        System.out.println("      // 1. Use STR processor");
        System.out.println("      // 2. Embed expressions");
        System.out.println("      // 3. Handle variables");
        System.out.println("      // 4. Process result");
        
        // Formatted template pattern
        System.out.println("    Formatted Template Pattern:");
        System.out.println("      // 1. Use FMT processor");
        System.out.println("      // 2. Apply formatting");
        System.out.println("      // 3. Handle precision");
        System.out.println("      // 4. Control output");
        
        // Custom template pattern
        System.out.println("    Custom Template Pattern:");
        System.out.println("      // 1. Define custom processor");
        System.out.println("      // 2. Implement processing logic");
        System.out.println("      // 3. Handle validation");
        System.out.println("      // 4. Return custom result");
        
        // Error handling pattern
        System.out.println("    Error Handling Pattern:");
        System.out.println("      // 1. Validate template");
        System.out.println("      // 2. Check expressions");
        System.out.println("      // 3. Handle errors");
        System.out.println("      // 4. Provide fallbacks");
    }
    
    private static void demonstrateBestPractices() {
        System.out.println("\n4. STRING TEMPLATES BEST PRACTICES:\n");
        
        // Q7: What are the best practices for Java String Templates?
        System.out.println("Q7: What are the best practices for Java String Templates?");
        System.out.println("    - Use appropriate template processors");
        System.out.println("    - Keep expressions simple");
        System.out.println("    - Validate template inputs");
        System.out.println("    - Consider performance implications\n");
        
        // Q8: How do you optimize String Template performance?
        System.out.println("Q8: How do you optimize String Template performance?");
        System.out.println("    - Reuse template processors");
        System.out.println("    - Minimize complex expressions");
        System.out.println("    - Use appropriate data types");
        System.out.println("    - Profile performance bottlenecks\n");
        
        // Demonstrate best practices
        System.out.println("Example: String Template Best Practices");
        
        // Template design
        System.out.println("    Template Design:");
        System.out.println("      - Keep templates readable");
        System.out.println("      - Use meaningful variable names");
        System.out.println("      - Avoid deeply nested expressions");
        System.out.println("      - Consider template reusability");
        
        // Expression management
        System.out.println("    Expression Management:");
        System.out.println("      - Keep expressions simple");
        System.out.println("      - Avoid side effects");
        System.out.println("      - Use appropriate operators");
        System.out.println("      - Handle null values");
        
        // Performance considerations
        System.out.println("    Performance Considerations:");
        System.out.println("      - Reuse template processors");
        System.out.println("      - Minimize object creation");
        System.out.println("      - Use efficient data types");
        System.out.println("      - Profile and optimize");
        
        // Error handling
        System.out.println("    Error Handling:");
        System.out.println("      - Validate template inputs");
        System.out.println("      - Handle expression errors");
        System.out.println("      - Provide meaningful error messages");
        System.out.println("      - Test edge cases");
    }
    
    // ===== HELPER CLASSES =====
    
    // Example custom template processor for SQL queries
    public static class SQLTemplateProcessor implements StringTemplate.Processor<String, RuntimeException> {
        @Override
        public String process(StringTemplate stringTemplate) throws RuntimeException {
            StringBuilder sql = new StringBuilder();
            for (int i = 0; i < stringTemplate.fragments().size(); i++) {
                sql.append(stringTemplate.fragments().get(i));
                if (i < stringTemplate.values().size()) {
                    Object value = stringTemplate.values().get(i);
                    if (value instanceof String) {
                        sql.append("'").append(value).append("'");
                    } else {
                        sql.append(value);
                    }
                }
            }
            return sql.toString();
        }
    }
    
    // Example custom template processor for HTML generation
    public static class HTMLTemplateProcessor implements StringTemplate.Processor<String, RuntimeException> {
        @Override
        public String process(StringTemplate stringTemplate) throws RuntimeException {
            StringBuilder html = new StringBuilder();
            for (int i = 0; i < stringTemplate.fragments().size(); i++) {
                html.append(stringTemplate.fragments().get(i));
                if (i < stringTemplate.values().size()) {
                    Object value = stringTemplate.values().get(i);
                    html.append(escapeHtml(value.toString()));
                }
            }
            return html.toString();
        }
        
        private String escapeHtml(String text) {
            return text.replace("&", "&amp;")
                      .replace("<", "&lt;")
                      .replace(">", "&gt;")
                      .replace("\"", "&quot;")
                      .replace("'", "&#39;");
        }
    }
    
    // Example custom template processor for JSON generation
    public static class JSONTemplateProcessor implements StringTemplate.Processor<String, RuntimeException> {
        @Override
        public String process(StringTemplate stringTemplate) throws RuntimeException {
            StringBuilder json = new StringBuilder();
            for (int i = 0; i < stringTemplate.fragments().size(); i++) {
                json.append(stringTemplate.fragments().get(i));
                if (i < stringTemplate.values().size()) {
                    Object value = stringTemplate.values().get(i);
                    json.append(escapeJson(value.toString()));
                }
            }
            return json.toString();
        }
        
        private String escapeJson(String text) {
            return text.replace("\\", "\\\\")
                      .replace("\"", "\\\"")
                      .replace("\n", "\\n")
                      .replace("\r", "\\r")
                      .replace("\t", "\\t");
        }
    }
    
    // Example custom template processor for XML generation
    public static class XMLTemplateProcessor implements StringTemplate.Processor<String, RuntimeException> {
        @Override
        public String process(StringTemplate stringTemplate) throws RuntimeException {
            StringBuilder xml = new StringBuilder();
            for (int i = 0; i < stringTemplate.fragments().size(); i++) {
                xml.append(stringTemplate.fragments().get(i));
                if (i < stringTemplate.values().size()) {
                    Object value = stringTemplate.values().get(i);
                    xml.append(escapeXml(value.toString()));
                }
            }
            return xml.toString();
        }
        
        private String escapeXml(String text) {
            return text.replace("&", "&amp;")
                      .replace("<", "&lt;")
                      .replace(">", "&gt;")
                      .replace("\"", "&quot;")
                      .replace("'", "&#39;");
        }
    }
    
    // Example usage demonstration
    public static void demonstrateCustomProcessors() {
        System.out.println("    Custom Template Processors:");
        
        // SQL processor
        SQLTemplateProcessor SQL = new SQLTemplateProcessor();
        String sqlQuery = SQL."SELECT * FROM users WHERE name = \{userName} AND age > \{minAge}";
        System.out.println("      SQL Query: " + sqlQuery);
        
        // HTML processor
        HTMLTemplateProcessor HTML = new HTMLTemplateProcessor();
        String htmlContent = HTML."<div class=\"user\">Hello, \{userName}! Your age is \{userAge}.</div>";
        System.out.println("      HTML Content: " + htmlContent);
        
        // JSON processor
        JSONTemplateProcessor JSON = new JSONTemplateProcessor();
        String jsonData = JSON."{\"name\": \"\{userName}\", \"age\": \{userAge}, \"active\": \{isActive}}";
        System.out.println("      JSON Data: " + jsonData);
        
        // XML processor
        XMLTemplateProcessor XML = new XMLTemplateProcessor();
        String xmlData = XML."<user><name>\{userName}</name><age>\{userAge}</age></user>";
        System.out.println("      XML Data: " + xmlData);
    }
    
    // Example variables for demonstration
    private static final String userName = "John Doe";
    private static final int userAge = 30;
    private static final int minAge = 18;
    private static final boolean isActive = true;
}

/*
 * COMPREHENSIVE INTERVIEW QUESTIONS FOR JAVA STRING TEMPLATES:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What are Java String Templates?
 * 2. What is the syntax of Java String Templates?
 * 3. What are the key features of Java String Templates?
 * 4. What are the benefits of using Java String Templates?
 * 5. How do you use different template processors?
 * 6. How do you create custom template processors?
 * 7. What are the best practices for Java String Templates?
 * 8. How do you optimize String Template performance?
 * 9. What is STR template processor?
 * 10. What is FMT template processor?
 * 11. What is RAW template processor?
 * 12. What is a template literal?
 * 13. What are embedded expressions?
 * 14. How do you escape characters in templates?
 * 15. What is the difference between templates and concatenation?
 * 16. What is the difference between templates and String.format?
 * 17. What is the difference between templates and StringBuilder?
 * 18. What is the difference between templates and interpolation?
 * 19. What is the difference between templates and placeholders?
 * 20. What is the difference between templates and macros?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. How do you handle null values in templates?
 * 22. How do you handle exceptions in templates?
 * 23. How do you validate template inputs?
 * 24. How do you test template processors?
 * 25. How do you debug template issues?
 * 26. How do you handle template performance?
 * 27. How do you use templates with collections?
 * 28. How do you handle template security?
 * 29. How do you use templates with databases?
 * 30. How do you handle template localization?
 * 31. How do you use templates with APIs?
 * 32. How do you handle template versioning?
 * 33. How do you use templates with frameworks?
 * 34. How do you handle template compatibility?
 * 35. How do you use templates with IDEs?
 * 36. How do you handle template deployment?
 * 37. How do you use templates with CI/CD?
 * 38. How do you handle template testing?
 * 39. How do you use templates with logging?
 * 40. How do you handle template maintenance?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you implement custom template loaders?
 * 42. How do you create template factories?
 * 43. How do you implement template resolvers?
 * 44. How do you create template validators?
 * 45. How do you implement template transformers?
 * 46. How do you create template analyzers?
 * 47. How do you implement template optimizers?
 * 48. How do you create template profilers?
 * 49. How do you implement template debuggers?
 * 50. How do you create template testing frameworks?
 * 51. How do you implement template mocking?
 * 52. How do you create template stubbing?
 * 53. How do you implement template verification?
 * 54. How do you create template validation tools?
 * 55. How do you implement template analysis?
 * 56. How do you create template visualization?
 * 57. How do you implement template metrics?
 * 58. How do you create template reporting?
 * 59. How do you implement template alerts?
 * 60. How do you create template dashboards?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you design template-based architectures?
 * 62. How do you implement template-based frameworks?
 * 63. How do you create template-based libraries?
 * 64. How do you implement template-based tools?
 * 65. How do you create template-based APIs?
 * 66. How do you implement template-based services?
 * 67. How do you create template-based microservices?
 * 68. How do you implement template-based event systems?
 * 69. How do you create template-based messaging?
 * 70. How do you implement template-based caching?
 * 71. How do you create template-based validation?
 * 72. How do you implement template-based transformation?
 * 73. How do you create template-based aggregation?
 * 74. How do you implement template-based composition?
 * 75. How do you create template-based inheritance?
 * 76. How do you implement template-based polymorphism?
 * 77. How do you create template-based design patterns?
 * 78. How do you implement template-based testing?
 * 79. How do you create template-based debugging?
 * 80. How do you implement template-based profiling?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a template-based microservices architecture?
 * 82. How would you implement template-based API gateways?
 * 83. How would you design template-based event systems?
 * 84. How would you implement template-based message processing?
 * 85. How would you design template-based workflow engines?
 * 86. How would you implement template-based rule engines?
 * 87. How would you design template-based decision systems?
 * 88. How would you implement template-based recommendation engines?
 * 89. How would you design template-based search systems?
 * 90. How would you implement template-based analytics platforms?
 * 91. How would you design template-based machine learning?
 * 92. How would you implement template-based blockchain systems?
 * 93. How would you design template-based gaming engines?
 * 94. How would you implement template-based IoT platforms?
 * 95. How would you design template-based social media?
 * 96. How would you implement template-based e-commerce systems?
 * 97. How would you design template-based healthcare systems?
 * 98. How would you implement template-based financial systems?
 * 99. How would you design template-based autonomous systems?
 * 100. How would you implement template-based quantum computing?
 */
