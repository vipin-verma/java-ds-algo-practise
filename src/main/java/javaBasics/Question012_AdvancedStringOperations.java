import java.util.*;

/**
 * Advanced String Operations - Comprehensive Guide
 * 
 * This class demonstrates various advanced string manipulation techniques
 * commonly asked in Java interviews for experienced developers.
 * 
 * Topics covered:
 * - String pattern matching (KMP, Boyer-Moore)
 * - String manipulation and transformation
 * - Palindrome and anagram operations
 * - String optimization techniques
 * - Performance analysis and memory management
 */
public class Question012_AdvancedStringOperations {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced String Operations - Complete Guide ===\n");
        
        demonstratePatternMatching();
        demonstrateStringManipulation();
        demonstratePalindromeOperations();
        demonstrateAnagramOperations();
        demonstrateStringOptimization();
        demonstratePerformanceAnalysis();
        
        System.out.println("\n=== Advanced String Operations Completed! ===");
    }
    
    // ===== PATTERN MATCHING =====
    
    /**
     * Knuth-Morris-Pratt (KMP) Pattern Matching Algorithm
     * Time Complexity: O(m + n), Space Complexity: O(m)
     * where m = pattern length, n = text length
     */
    public static int kmpSearch(String text, String pattern) {
        if (text == null || pattern == null || pattern.isEmpty()) return -1;
        if (text.isEmpty()) return -1;
        
        int[] lps = computeLPSArray(pattern);
        int i = 0, j = 0;
        
        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            
            if (j == pattern.length()) {
                return i - j; // Pattern found
            } else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1; // Pattern not found
    }
    
    /**
     * Compute Longest Proper Prefix which is also Suffix (LPS)
     */
    private static int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;
        
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
    
    /**
     * Boyer-Moore Pattern Matching Algorithm
     * Time Complexity: O(mn) worst case, O(n/m) best case
     * Space Complexity: O(k) where k is character set size
     */
    public static int boyerMooreSearch(String text, String pattern) {
        if (text == null || pattern == null || pattern.isEmpty()) return -1;
        if (text.isEmpty()) return -1;
        
        int[] badCharTable = buildBadCharTable(pattern);
        int[] goodSuffixTable = buildGoodSuffixTable(pattern);
        
        int i = pattern.length() - 1;
        while (i < text.length()) {
            int j = pattern.length() - 1;
            int k = i;
            
            while (j >= 0 && text.charAt(k) == pattern.charAt(j)) {
                k--;
                j--;
            }
            
            if (j == -1) {
                return k + 1; // Pattern found
            }
            
            int badCharShift = badCharTable[text.charAt(k)];
            int goodSuffixShift = goodSuffixTable[j + 1];
            
            i += Math.max(badCharShift, goodSuffixShift);
        }
        return -1; // Pattern not found
    }
    
    private static int[] buildBadCharTable(String pattern) {
        int[] table = new int[256];
        Arrays.fill(table, pattern.length());
        
        for (int i = 0; i < pattern.length() - 1; i++) {
            table[pattern.charAt(i)] = pattern.length() - 1 - i;
        }
        return table;
    }
    
    private static int[] buildGoodSuffixTable(String pattern) {
        int[] table = new int[pattern.length() + 1];
        int[] border = new int[pattern.length() + 1];
        
        // Case 1: Exact match
        int i = pattern.length();
        int j = pattern.length() + 1;
        border[i] = j;
        
        while (i > 0) {
            while (j <= pattern.length() && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (table[j] == 0) {
                    table[j] = j - i;
                }
                j = border[j];
            }
            i--;
            j--;
            border[i] = j;
        }
        
        // Case 2: Good suffix exists
        j = border[0];
        for (i = 0; i <= pattern.length(); i++) {
            if (table[i] == 0) {
                table[i] = j;
            }
            if (i == j) {
                j = border[j];
            }
        }
        
        return table;
    }
    
    // ===== STRING MANIPULATION =====
    
    /**
     * Convert string to different cases with optimization
     */
    public static String toUpperCaseOptimized(String str) {
        if (str == null) return null;
        
        char[] chars = str.toCharArray();
        boolean hasLowerCase = false;
        
        // Check if conversion is needed
        for (char c : chars) {
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
                break;
            }
        }
        
        if (!hasLowerCase) return str; // No conversion needed
        
        // Perform conversion
        for (int i = 0; i < chars.length; i++) {
            chars[i] = Character.toUpperCase(chars[i]);
        }
        
        return new String(chars);
    }
    
    /**
     * Remove duplicate characters from string efficiently
     * Time Complexity: O(n), Space Complexity: O(k) where k is character set size
     */
    public static String removeDuplicates(String str) {
        if (str == null || str.length() <= 1) return str;
        
        boolean[] seen = new boolean[256];
        StringBuilder result = new StringBuilder();
        
        for (char c : str.toCharArray()) {
            if (!seen[c]) {
                seen[c] = true;
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Find longest common substring between two strings
     * Time Complexity: O(m*n), Space Complexity: O(m*n)
     */
    public static String longestCommonSubstring(String str1, String str2) {
        if (str1 == null || str2 == null) return "";
        
        int m = str1.length();
        int n = str2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;
        int endIndex = 0;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i - 1;
                    }
                }
            }
        }
        
        if (maxLength == 0) return "";
        return str1.substring(endIndex - maxLength + 1, endIndex + 1);
    }
    
    /**
     * Check if strings are rotations of each other
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static boolean areRotations(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        if (str1.length() != str2.length()) return false;
        
        String concatenated = str1 + str1;
        return concatenated.contains(str2);
    }
    
    // ===== PALINDROME OPERATIONS =====
    
    /**
     * Check if string is palindrome with case-insensitive option
     */
    public static boolean isPalindrome(String str, boolean caseInsensitive) {
        if (str == null) return false;
        if (str.isEmpty()) return true;
        
        String processed = caseInsensitive ? str.toLowerCase() : str;
        int left = 0, right = processed.length() - 1;
        
        while (left < right) {
            if (processed.charAt(left) != processed.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    /**
     * Find longest palindromic substring using dynamic programming
     * Time Complexity: O(n²), Space Complexity: O(n²)
     */
    public static String longestPalindromicSubstring(String str) {
        if (str == null || str.length() <= 1) return str;
        
        int n = str.length();
        boolean[][] dp = new boolean[n][n];
        
        // All single characters are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        
        int maxLength = 1;
        int start = 0;
        
        // Check for substrings of length 2
        for (int i = 0; i < n - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                dp[i][i + 1] = true;
                maxLength = 2;
                start = i;
            }
        }
        
        // Check for substrings of length > 2
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    if (len > maxLength) {
                        maxLength = len;
                        start = i;
                    }
                }
            }
        }
        
        return str.substring(start, start + maxLength);
    }
    
    /**
     * Count palindromic substrings in a string
     * Time Complexity: O(n²), Space Complexity: O(n²)
     */
    public static int countPalindromicSubstrings(String str) {
        if (str == null || str.isEmpty()) return 0;
        
        int n = str.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        
        // Single characters
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }
        
        // Substrings of length 2
        for (int i = 0; i < n - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                dp[i][i + 1] = true;
                count++;
            }
        }
        
        // Substrings of length > 2
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
    
    // ===== ANAGRAM OPERATIONS =====
    
    /**
     * Check if two strings are anagrams
     * Time Complexity: O(n), Space Complexity: O(k) where k is character set size
     */
    public static boolean areAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        if (str1.length() != str2.length()) return false;
        
        int[] charCount = new int[256];
        
        for (char c : str1.toCharArray()) {
            charCount[c]++;
        }
        
        for (char c : str2.toCharArray()) {
            charCount[c]--;
            if (charCount[c] < 0) return false;
        }
        
        return true;
    }
    
    /**
     * Find all anagrams of a pattern in a text
     * Time Complexity: O(n), Space Complexity: O(k)
     */
    public static List<Integer> findAllAnagrams(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (text == null || pattern == null || text.length() < pattern.length()) {
            return result;
        }
        
        int[] patternCount = new int[256];
        int[] textCount = new int[256];
        
        // Count characters in pattern
        for (char c : pattern.toCharArray()) {
            patternCount[c]++;
        }
        
        int patternLength = pattern.length();
        int matchCount = 0;
        
        for (int i = 0; i < text.length(); i++) {
            // Add current character
            char currentChar = text.charAt(i);
            textCount[currentChar]++;
            
            if (textCount[currentChar] <= patternCount[currentChar]) {
                matchCount++;
            }
            
            // Remove character from sliding window
            if (i >= patternLength) {
                char removeChar = text.charAt(i - patternLength);
                textCount[removeChar]--;
                
                if (textCount[removeChar] < patternCount[removeChar]) {
                    matchCount--;
                }
            }
            
            // Check if anagram found
            if (matchCount == patternLength) {
                result.add(i - patternLength + 1);
            }
        }
        
        return result;
    }
    
    // ===== STRING OPTIMIZATION =====
    
    /**
     * Optimize string concatenation using StringBuilder
     */
    public static String optimizeConcatenation(String[] strings) {
        if (strings == null || strings.length == 0) return "";
        
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            if (str != null) {
                sb.append(str);
            }
        }
        return sb.toString();
    }
    
    /**
     * Interleave two strings efficiently
     */
    public static String interleaveStrings(String str1, String str2) {
        if (str1 == null) return str2;
        if (str2 == null) return str1;
        
        StringBuilder result = new StringBuilder();
        int maxLength = Math.max(str1.length(), str2.length());
        
        for (int i = 0; i < maxLength; i++) {
            if (i < str1.length()) {
                result.append(str1.charAt(i));
            }
            if (i < str2.length()) {
                result.append(str2.charAt(i));
            }
        }
        
        return result.toString();
    }
    
    // ===== DEMONSTRATION METHODS =====
    
    private static void demonstratePatternMatching() {
        System.out.println("1. PATTERN MATCHING ALGORITHMS:\n");
        
        String text = "ABABCABCABAB";
        String pattern = "ABCAB";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        
        int kmpResult = kmpSearch(text, pattern);
        System.out.println("KMP Search result: " + kmpResult);
        
        int bmResult = boyerMooreSearch(text, pattern);
        System.out.println("Boyer-Moore Search result: " + bmResult);
        
        System.out.println();
    }
    
    private static void demonstrateStringManipulation() {
        System.out.println("2. STRING MANIPULATION:\n");
        
        String str1 = "Hello World";
        System.out.println("Original: " + str1);
        System.out.println("Uppercase: " + toUpperCaseOptimized(str1));
        
        String str2 = "programming";
        System.out.println("\nOriginal: " + str2);
        System.out.println("Without duplicates: " + removeDuplicates(str2));
        
        String str3 = "ABCDGH";
        String str4 = "ACDGHR";
        System.out.println("\nString 1: " + str3);
        System.out.println("String 2: " + str4);
        System.out.println("Longest common substring: " + longestCommonSubstring(str3, str4));
        
        String str5 = "ABCD";
        String str6 = "CDAB";
        System.out.println("\nString 1: " + str5);
        System.out.println("String 2: " + str6);
        System.out.println("Are rotations: " + areRotations(str5, str6));
        
        System.out.println();
    }
    
    private static void demonstratePalindromeOperations() {
        System.out.println("3. PALINDROME OPERATIONS:\n");
        
        String str1 = "Racecar";
        System.out.println("String: " + str1);
        System.out.println("Is palindrome (case-sensitive): " + isPalindrome(str1, false));
        System.out.println("Is palindrome (case-insensitive): " + isPalindrome(str1, true));
        
        String str2 = "forgeeksskeegfor";
        System.out.println("\nString: " + str2);
        System.out.println("Longest palindromic substring: " + longestPalindromicSubstring(str2));
        System.out.println("Count of palindromic substrings: " + countPalindromicSubstrings(str2));
        
        System.out.println();
    }
    
    private static void demonstrateAnagramOperations() {
        System.out.println("4. ANAGRAM OPERATIONS:\n");
        
        String str1 = "listen";
        String str2 = "silent";
        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);
        System.out.println("Are anagrams: " + areAnagrams(str1, str2));
        
        String text = "cbaebabacd";
        String pattern = "abc";
        System.out.println("\nText: " + text);
        System.out.println("Pattern: " + pattern);
        List<Integer> anagramIndices = findAllAnagrams(text, pattern);
        System.out.println("Anagram indices: " + anagramIndices);
        
        System.out.println();
    }
    
    private static void demonstrateStringOptimization() {
        System.out.println("5. STRING OPTIMIZATION:\n");
        
        String[] strings = {"Hello", " ", "World", "!", " How", " are", " you?"};
        System.out.println("Strings to concatenate: " + Arrays.toString(strings));
        String result = optimizeConcatenation(strings);
        System.out.println("Optimized concatenation: " + result);
        
        String str1 = "ABC";
        String str2 = "12345";
        System.out.println("\nString 1: " + str1);
        System.out.println("String 2: " + str2);
        System.out.println("Interleaved: " + interleaveStrings(str1, str2));
        
        System.out.println();
    }
    
    private static void demonstratePerformanceAnalysis() {
        System.out.println("6. PERFORMANCE ANALYSIS:\n");
        
        // Performance comparison for different string operations
        String largeText = generateLargeString(10000);
        String pattern = "ABC";
        
        // KMP performance
        long startTime = System.nanoTime();
        int kmpResult = kmpSearch(largeText, pattern);
        long kmpTime = System.nanoTime() - startTime;
        
        // Boyer-Moore performance
        startTime = System.nanoTime();
        int bmResult = boyerMooreSearch(largeText, pattern);
        long bmTime = System.nanoTime() - startTime;
        
        System.out.println("Large text search (length: " + largeText.length() + "):");
        System.out.println("  KMP Search: " + kmpTime + " ns");
        System.out.println("  Boyer-Moore: " + bmTime + " ns");
        System.out.println("  Results match: " + (kmpResult == bmResult));
        
        System.out.println();
    }
    
    private static String generateLargeString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        // Insert pattern at random position
        int insertPos = random.nextInt(length - 3);
        sb.insert(insertPos, "ABC");
        
        return sb.toString();
    }
}

/**
 * INTERVIEW QUESTIONS COVERED:
 * 
 * THEORETICAL:
 * 1. Explain the KMP algorithm and its time complexity.
 * 2. How does Boyer-Moore algorithm improve over naive string matching?
 * 3. What is the difference between substring and subsequence?
 * 4. Explain the concept of LPS (Longest Proper Prefix which is also Suffix).
 * 5. How do you handle case sensitivity in string operations?
 * 6. What are the trade-offs between different string matching algorithms?
 * 
 * PRACTICAL:
 * 1. Implement efficient pattern matching algorithms.
 * 2. Find longest palindromic substring using dynamic programming.
 * 3. Check if strings are rotations of each other.
 * 4. Count all palindromic substrings in a string.
 * 5. Find all anagrams of a pattern in text.
 * 
 * TRICKY SCENARIOS:
 * 1. Handle null and empty string inputs.
 * 2. Deal with case sensitivity in comparisons.
 * 3. Optimize memory usage for large strings.
 * 4. Handle special characters and Unicode.
 * 
 * PERFORMANCE:
 * 1. Compare KMP vs Boyer-Moore performance.
 * 2. Analyze memory usage of different approaches.
 * 3. Optimize string concatenation operations.
 * 4. Benchmark palindrome detection algorithms.
 * 
 * DESIGN PATTERNS:
 * 1. Strategy pattern for different matching algorithms.
 * 2. Template method for string operations.
 * 3. Builder pattern for string construction.
 * 4. Factory pattern for algorithm selection.
 */
