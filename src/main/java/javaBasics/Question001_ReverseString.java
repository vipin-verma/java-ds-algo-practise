/**
 * Question 1: String Reversal
 * 
 * Problem: Write a method to reverse a string without using StringBuilder.reverse()
 * 
 * Requirements:
 * - Should handle null and empty strings
 * - Should preserve Unicode characters
 * - Should be efficient for large strings
 * - Should not use built-in reverse methods
 * 
 * Difficulty: Easy
 * Category: String Manipulation
 * Experience Level: 2-3 years
 */
public class Question001_ReverseString {
    
    public static void main(String[] args) {
        System.out.println("=== String Reversal Question ===\n");
        
        // Test cases
        String[] testStrings = {
            "Hello World",
            "Java Programming",
            "12345",
            "racecar",
            "",
            null,
            "🚀 Java 🚀",
            "A man, a plan, a canal: Panama"
        };
        
        for (String str : testStrings) {
            try {
                String reversed = reverseString(str);
                System.out.println("Original: " + str);
                System.out.println("Reversed: " + reversed);
                System.out.println("Is Palindrome: " + isPalindrome(str));
                System.out.println("---");
            } catch (Exception e) {
                System.out.println("Error processing: " + str + " - " + e.getMessage());
                System.out.println("---");
            }
        }
        
        // Performance test
        performanceTest();
    }
    
    /**
     * Reverses a string using character array approach
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String reverseString(String str) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return str;
        }
        
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        
        while (left < right) {
            // Swap characters
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            
            left++;
            right--;
        }
        
        return new String(chars);
    }
    
    /**
     * Alternative approach using StringBuilder (for comparison)
     */
    public static String reverseStringWithBuilder(String str) {
        if (str == null) return null;
        return new StringBuilder(str).reverse().toString();
    }
    
    /**
     * Recursive approach to reverse string
     */
    public static String reverseStringRecursive(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        return reverseStringRecursive(str.substring(1)) + str.charAt(0);
    }
    
    /**
     * Checks if a string is palindrome
     */
    public static boolean isPalindrome(String str) {
        if (str == null) return false;
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleaned.equals(reverseString(cleaned));
    }
    
    /**
     * Performance comparison between different approaches
     */
    private static void performanceTest() {
        System.out.println("=== Performance Test ===");
        
        // Create a large string
        StringBuilder largeString = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeString.append("abcdefghijklmnopqrstuvwxyz");
        }
        String testString = largeString.toString();
        
        // Test different approaches
        long startTime = System.currentTimeMillis();
        String result1 = reverseString(testString);
        long time1 = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        String result2 = reverseStringWithBuilder(testString);
        long time2 = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        String result3 = reverseStringRecursive(testString.substring(0, 1000)); // Limit for recursive
        long time3 = System.currentTimeMillis() - startTime;
        
        System.out.println("Character Array Approach: " + time1 + "ms");
        System.out.println("StringBuilder Approach: " + time2 + "ms");
        System.out.println("Recursive Approach (1000 chars): " + time3 + "ms");
        
        // Verify results are same
        System.out.println("Results match: " + result1.equals(result2));
    }
    
    /**
     * Interview Questions to Ask:
     * 
     * 1. What is the time and space complexity of your solution?
     * 2. How would you handle very long strings (memory considerations)?
     * 3. What if the string contains Unicode characters or emojis?
     * 4. Can you optimize this further?
     * 5. How would you test this method?
     * 6. What edge cases should we consider?
     * 7. How would you make this method thread-safe?
     * 8. Can you write a unit test for this?
     */
}
