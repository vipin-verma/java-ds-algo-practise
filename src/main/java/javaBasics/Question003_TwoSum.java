/**
 * Question 3: Two Sum Problem
 * 
 * Problem: Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 * 
 * Requirements:
 * - Return indices of two numbers that sum to target
 * - Each input has exactly one solution
 * - Cannot use the same element twice
 * - Should handle edge cases efficiently
 * - Return indices in ascending order
 * 
 * Difficulty: Easy
 * Category: Array, Hash Table
 * Experience Level: 2-3 years
 */
public class Question003_TwoSum {
    
    public static void main(String[] args) {
        System.out.println("=== Two Sum Problem ===\n");
        
        // Test cases
        int[][] testArrays = {
            {2, 7, 11, 15},      // Target: 9, Expected: [0, 1]
            {3, 2, 4},           // Target: 6, Expected: [1, 2]
            {3, 3},              // Target: 6, Expected: [0, 1]
            {1, 5, 8, 10, 13},  // Target: 18, Expected: [3, 4]
            {-1, -2, -3, -4},   // Target: -7, Expected: [2, 3]
            {0, 4, 3, 0},       // Target: 0, Expected: [0, 3]
            {1, 2, 3, 4, 5},    // Target: 9, Expected: [3, 4]
        };
        
        int[] targets = {9, 6, 6, 18, -7, 0, 9};
        
        for (int i = 0; i < testArrays.length; i++) {
            int[] nums = testArrays[i];
            int target = targets[i];
            
            System.out.println("Array: " + java.util.Arrays.toString(nums));
            System.out.println("Target: " + target);
            
            // Multiple approaches
            int[] result1 = twoSumBruteForce(nums, target);
            int[] result2 = twoSumHashMap(nums, target);
            int[] result3 = twoSumTwoPointers(nums, target);
            
            System.out.println("Brute Force: " + java.util.Arrays.toString(result1));
            System.out.println("HashMap: " + java.util.Arrays.toString(result2));
            System.out.println("Two Pointers: " + java.util.Arrays.toString(result3));
            
            // Verify results
            if (result1 != null && result2 != null && result3 != null) {
                int sum1 = nums[result1[0]] + nums[result1[1]];
                int sum2 = nums[result2[0]] + nums[result2[1]];
                int sum3 = nums[result3[0]] + nums[result3[1]];
                
                System.out.println("Sums: " + sum1 + ", " + sum2 + ", " + sum3);
                System.out.println("All correct: " + (sum1 == target && sum2 == target && sum3 == target));
            }
            System.out.println("---");
        }
        
        // Performance test
        performanceTest();
        
        // Edge cases
        testEdgeCases();
    }
    
    /**
     * Approach 1: Brute Force
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public static int[] twoSumBruteForce(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        
        return null; // No solution found
    }
    
    /**
     * Approach 2: HashMap (Most Efficient)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[] twoSumHashMap(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            
            map.put(nums[i], i);
        }
        
        return null; // No solution found
    }
    
    /**
     * Approach 3: Two Pointers (requires sorted array)
     * Time Complexity: O(n log n) due to sorting
     * Space Complexity: O(n) to store original indices
     */
    public static int[] twoSumTwoPointers(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        
        // Create array with indices to preserve original positions
        int[][] numsWithIndices = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            numsWithIndices[i][0] = nums[i];  // value
            numsWithIndices[i][1] = i;        // original index
        }
        
        // Sort by values
        java.util.Arrays.sort(numsWithIndices, (a, b) -> Integer.compare(a[0], b[0]));
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int sum = numsWithIndices[left][0] + numsWithIndices[right][0];
            
            if (sum == target) {
                int index1 = numsWithIndices[left][1];
                int index2 = numsWithIndices[right][1];
                // Return indices in ascending order
                return index1 < index2 ? new int[]{index1, index2} : new int[]{index2, index1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return null; // No solution found
    }
    
    /**
     * Approach 4: Binary Search (for sorted arrays)
     * Time Complexity: O(n log n)
     * Space Complexity: O(1)
     */
    public static int[] twoSumBinarySearch(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        
        // Array must be sorted for binary search
        java.util.Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 1; i++) {
            int complement = target - nums[i];
            
            // Binary search for complement
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                
                if (nums[mid] == complement) {
                    return new int[]{i, mid};
                } else if (nums[mid] < complement) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return null; // No solution found
    }
    
    /**
     * Performance comparison between different approaches
     */
    private static void performanceTest() {
        System.out.println("=== Performance Test ===");
        
        // Create large array
        int size = 100000;
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = i;
        }
        
        int target = size - 1; // Target that will be found at the end
        
        // Test different approaches
        long startTime = System.currentTimeMillis();
        int[] result1 = twoSumHashMap(largeArray, target);
        long time1 = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int[] result2 = twoSumBruteForce(largeArray, target);
        long time2 = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int[] result3 = twoSumTwoPointers(largeArray, target);
        long time3 = System.currentTimeMillis() - startTime;
        
        System.out.println("HashMap Approach: " + time1 + "ms");
        System.out.println("Brute Force Approach: " + time2 + "ms");
        System.out.println("Two Pointers Approach: " + time3 + "ms");
        System.out.println("HashMap is " + String.format("%.2f", (double)time2/time1) + "x faster than Brute Force");
        
        // Verify results
        if (result1 != null && result2 != null && result3 != null) {
            int sum1 = largeArray[result1[0]] + largeArray[result1[1]];
            int sum2 = largeArray[result2[0]] + largeArray[result2[1]];
            int sum3 = largeArray[result3[0]] + largeArray[result3[1]];
            
            System.out.println("All results sum to target: " + (sum1 == target && sum2 == target && sum3 == target));
        }
    }
    
    /**
     * Test edge cases and error conditions
     */
    private static void testEdgeCases() {
        System.out.println("\n=== Edge Cases Test ===");
        
        // Test null array
        try {
            int[] result = twoSumHashMap(null, 10);
            System.out.println("Null array handled: " + (result == null));
        } catch (Exception e) {
            System.out.println("Null array error: " + e.getMessage());
        }
        
        // Test empty array
        try {
            int[] result = twoSumHashMap(new int[0], 10);
            System.out.println("Empty array handled: " + (result == null));
        } catch (Exception e) {
            System.out.println("Empty array error: " + e.getMessage());
        }
        
        // Test single element array
        try {
            int[] result = twoSumHashMap(new int[]{5}, 10);
            System.out.println("Single element array handled: " + (result == null));
        } catch (Exception e) {
            System.out.println("Single element array error: " + e.getMessage());
        }
        
        // Test no solution
        int[] noSolution = {1, 2, 3, 4};
        int[] result = twoSumHashMap(noSolution, 100);
        System.out.println("No solution handled: " + (result == null));
        
        // Test duplicate elements
        int[] duplicates = {3, 3, 3, 3};
        int[] result2 = twoSumHashMap(duplicates, 6);
        System.out.println("Duplicate elements handled: " + (result2 != null));
        if (result2 != null) {
            System.out.println("Duplicate result: " + java.util.Arrays.toString(result2));
        }
    }
    
    /**
     * Interview Questions to Ask:
     * 
     * 1. What is the time and space complexity of each approach?
     * 2. Which approach would you prefer for production code and why?
     * 3. How would you handle the case where multiple solutions exist?
     * 4. What if the array contains duplicate elements?
     * 5. How would you modify the solution to return all possible pairs?
     * 6. What if the array is very large and doesn't fit in memory?
     * 7. How would you test this solution?
     * 8. Can you optimize this further?
     * 9. What if the array is already sorted?
     * 10. How would you make this solution thread-safe?
     * 11. What if we need to find three numbers that sum to target?
     * 12. How would you handle overflow in the sum calculation?
     */
}
