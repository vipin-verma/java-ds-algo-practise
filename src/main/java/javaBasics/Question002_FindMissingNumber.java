/**
 * Question 2: Find Missing Number in Array
 * 
 * Problem: Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 * 
 * Requirements:
 * - Array contains n-1 numbers from 0 to n
 * - One number is missing
 * - Should handle edge cases
 * - Should be efficient
 * - Should not use sorting
 * 
 * Difficulty: Easy
 * Category: Array Manipulation
 * Experience Level: 2-3 years
 */
public class Question002_FindMissingNumber {
    
    public static void main(String[] args) {
        System.out.println("=== Find Missing Number Question ===\n");
        
        // Test cases
        int[][] testArrays = {
            {3, 0, 1},           // Missing: 2
            {9, 6, 4, 2, 3, 5, 7, 0, 1}, // Missing: 8
            {0, 1},              // Missing: 2
            {1},                 // Missing: 0
            {0},                 // Missing: 1
            {1, 2, 3, 4, 5},    // Missing: 0
            {0, 1, 2, 3, 4},    // Missing: 5
            {2, 0, 1, 4, 3}     // Missing: 5
        };
        
        for (int[] arr : testArrays) {
            System.out.println("Array: " + java.util.Arrays.toString(arr));
            
            // Multiple approaches
            int missing1 = findMissingNumberSum(arr);
            int missing2 = findMissingNumberXOR(arr);
            int missing3 = findMissingNumberBinarySearch(arr);
            
            System.out.println("Missing (Sum): " + missing1);
            System.out.println("Missing (XOR): " + missing2);
            System.out.println("Missing (Binary Search): " + missing3);
            System.out.println("All methods agree: " + (missing1 == missing2 && missing2 == missing3));
            System.out.println("---");
        }
        
        // Performance test
        performanceTest();
        
        // Edge cases
        testEdgeCases();
    }
    
    /**
     * Approach 1: Using sum formula
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int findMissingNumberSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        
        for (int num : nums) {
            actualSum += num;
        }
        
        return expectedSum - actualSum;
    }
    
    /**
     * Approach 2: Using XOR operation
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Advantage: No overflow issues
     */
    public static int findMissingNumberXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        int n = nums.length;
        int xor = 0;
        
        // XOR all numbers from 0 to n
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        
        // XOR with all numbers in array
        for (int num : nums) {
            xor ^= num;
        }
        
        return xor;
    }
    
    /**
     * Approach 3: Using binary search (if array is sorted)
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int findMissingNumberBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        // First sort the array
        java.util.Arrays.sort(nums);
        
        int left = 0;
        int right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    /**
     * Approach 4: Using HashSet (for comparison)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int findMissingNumberHashSet(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        java.util.Set<Integer> set = new java.util.HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        
        return -1; // Should never reach here
    }
    
    /**
     * Performance comparison between different approaches
     */
    private static void performanceTest() {
        System.out.println("=== Performance Test ===");
        
        // Create large array
        int n = 1000000;
        int[] largeArray = new int[n];
        for (int i = 0; i < n; i++) {
            largeArray[i] = i;
        }
        
        // Remove one number
        int missing = n / 2;
        largeArray[missing] = n;
        
        // Test different approaches
        long startTime = System.currentTimeMillis();
        int result1 = findMissingNumberSum(largeArray);
        long time1 = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int result2 = findMissingNumberXOR(largeArray);
        long time2 = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int result3 = findMissingNumberHashSet(largeArray);
        long time3 = System.currentTimeMillis() - startTime;
        
        System.out.println("Sum Approach: " + time1 + "ms, Result: " + result1);
        System.out.println("XOR Approach: " + time2 + "ms, Result: " + result2);
        System.out.println("HashSet Approach: " + time3 + "ms, Result: " + result3);
        System.out.println("All results match: " + (result1 == result2 && result2 == result3));
    }
    
    /**
     * Test edge cases and error conditions
     */
    private static void testEdgeCases() {
        System.out.println("\n=== Edge Cases Test ===");
        
        try {
            findMissingNumberSum(null);
        } catch (Exception e) {
            System.out.println("Null array handled correctly: " + e.getMessage());
        }
        
        try {
            findMissingNumberSum(new int[0]);
        } catch (Exception e) {
            System.out.println("Empty array handled correctly: " + e.getMessage());
        }
        
        // Test with very large numbers (overflow test)
        int[] overflowArray = {Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE};
        try {
            int result = findMissingNumberSum(overflowArray);
            System.out.println("Overflow test result: " + result);
        } catch (Exception e) {
            System.out.println("Overflow handled: " + e.getMessage());
        }
    }
    
    /**
     * Interview Questions to Ask:
     * 
     * 1. What is the time and space complexity of each approach?
     * 2. Which approach would you prefer and why?
     * 3. How would you handle overflow in the sum approach?
     * 4. What if the array is not sorted?
     * 5. How would you modify the solution if duplicates are allowed?
     * 6. What if multiple numbers are missing?
     * 7. How would you test this solution?
     * 8. Can you optimize this further?
     * 9. What if the array is very large and doesn't fit in memory?
     * 10. How would you make this solution thread-safe?
     */
}
