import java.util.*;

/**
 * Advanced Array Operations - Comprehensive Guide
 * 
 * This class demonstrates various advanced array manipulation techniques
 * commonly asked in Java interviews for experienced developers.
 * 
 * Topics covered:
 * - Array rotation (left, right, multiple positions)
 * - Search algorithms (binary search variations)
 * - Sorting algorithms (quick sort, merge sort)
 * - Array manipulation (rearrangement, partitioning)
 * - Performance analysis and optimization
 */
public class Question011_AdvancedArrayOperations {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Array Operations - Complete Guide ===\n");
        
        demonstrateArrayRotation();
        demonstrateAdvancedSearching();
        demonstrateSortingAlgorithms();
        demonstrateArrayManipulation();
        demonstratePerformanceAnalysis();
        
        System.out.println("\n=== Advanced Array Operations Completed! ===");
    }
    
    // ===== ARRAY ROTATION =====
    
    /**
     * Rotate array to the left by k positions
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void rotateLeft(int[] arr, int k) {
        if (arr == null || arr.length <= 1) return;
        
        k = k % arr.length; // Handle cases where k > array length
        reverseArray(arr, 0, k - 1);
        reverseArray(arr, k, arr.length - 1);
        reverseArray(arr, 0, arr.length - 1);
    }
    
    /**
     * Rotate array to the right by k positions
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void rotateRight(int[] arr, int k) {
        if (arr == null || arr.length <= 1) return;
        
        k = k % arr.length;
        reverseArray(arr, 0, arr.length - 1);
        reverseArray(arr, 0, k - 1);
        reverseArray(arr, k, arr.length - 1);
    }
    
    /**
     * Rotate array using Juggling algorithm (GCD approach)
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void rotateJuggling(int[] arr, int k) {
        if (arr == null || arr.length <= 1) return;
        
        k = k % arr.length;
        int gcd = gcd(arr.length, k);
        
        for (int i = 0; i < gcd; i++) {
            int temp = arr[i];
            int j = i;
            
            while (true) {
                int next = (j + k) % arr.length;
                if (next == i) break;
                arr[j] = arr[next];
                j = next;
            }
            arr[j] = temp;
        }
    }
    
    private static void reverseArray(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    
    // ===== ADVANCED SEARCHING =====
    
    /**
     * Find element in rotated sorted array
     * Time Complexity: O(log n), Space Complexity: O(1)
     */
    public static int searchInRotatedArray(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) return mid;
            
            // Left half is sorted
            if (arr[left] <= arr[mid]) {
                if (arr[left] <= target && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // Right half is sorted
                if (arr[mid] < target && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
    
    /**
     * Find peak element in array (element greater than neighbors)
     * Time Complexity: O(log n), Space Complexity: O(1)
     */
    public static int findPeakElement(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1) return 0;
        
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    /**
     * Find first and last occurrence of target in sorted array
     * Time Complexity: O(log n), Space Complexity: O(1)
     */
    public static int[] findFirstLastOccurrence(int[] arr, int target) {
        int[] result = {-1, -1};
        if (arr == null || arr.length == 0) return result;
        
        result[0] = findFirstOccurrence(arr, target);
        result[1] = findLastOccurrence(arr, target);
        
        return result;
    }
    
    private static int findFirstOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                right = mid - 1; // Continue searching left
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    private static int findLastOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                left = mid + 1; // Continue searching right
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    // ===== SORTING ALGORITHMS =====
    
    /**
     * Quick Sort implementation
     * Time Complexity: O(n log n) average, O(n²) worst, Space Complexity: O(log n)
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSortHelper(arr, 0, arr.length - 1);
    }
    
    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortHelper(arr, low, pi - 1);
            quickSortHelper(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    /**
     * Merge Sort implementation
     * Time Complexity: O(n log n), Space Complexity: O(n)
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        
        int[] temp = new int[arr.length];
        mergeSortHelper(arr, temp, 0, arr.length - 1);
    }
    
    private static void mergeSortHelper(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, temp, left, mid);
            mergeSortHelper(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
    
    // ===== ARRAY MANIPULATION =====
    
    /**
     * Rearrange array such that even numbers come first, then odd
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void rearrangeEvenOdd(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            while (left < right && arr[left] % 2 == 0) left++;
            while (left < right && arr[right] % 2 == 1) right--;
            
            if (left < right) {
                swap(arr, left, right);
            }
        }
    }
    
    /**
     * Move all zeros to end while maintaining relative order
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void moveZerosToEnd(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        
        int nonZeroIndex = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[nonZeroIndex++] = arr[i];
            }
        }
        
        while (nonZeroIndex < arr.length) {
            arr[nonZeroIndex++] = 0;
        }
    }
    
    /**
     * Find maximum subarray sum (Kadane's Algorithm)
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int maxSubarraySum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // ===== DEMONSTRATION METHODS =====
    
    private static void demonstrateArrayRotation() {
        System.out.println("1. ARRAY ROTATION TECHNIQUES:\n");
        
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Original array: " + Arrays.toString(arr1));
        
        // Left rotation
        int[] arr2 = arr1.clone();
        rotateLeft(arr2, 2);
        System.out.println("Left rotation by 2: " + Arrays.toString(arr2));
        
        // Right rotation
        int[] arr3 = arr1.clone();
        rotateRight(arr3, 3);
        System.out.println("Right rotation by 3: " + Arrays.toString(arr3));
        
        // Juggling rotation
        int[] arr4 = arr1.clone();
        rotateJuggling(arr4, 4);
        System.out.println("Juggling rotation by 4: " + Arrays.toString(arr4));
        
        System.out.println();
    }
    
    private static void demonstrateAdvancedSearching() {
        System.out.println("2. ADVANCED SEARCHING ALGORITHMS:\n");
        
        // Search in rotated array
        int[] rotatedArr = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Rotated array: " + Arrays.toString(rotatedArr));
        System.out.println("Search for 0: index " + searchInRotatedArray(rotatedArr, 0));
        System.out.println("Search for 3: index " + searchInRotatedArray(rotatedArr, 3));
        
        // Find peak element
        int[] peakArr = {1, 3, 20, 4, 1, 0};
        System.out.println("\nPeak array: " + Arrays.toString(peakArr));
        System.out.println("Peak element index: " + findPeakElement(peakArr));
        
        // First and last occurrence
        int[] sortedArr = {1, 2, 2, 2, 3, 4, 5};
        System.out.println("\nSorted array: " + Arrays.toString(sortedArr));
        int[] occurrences = findFirstLastOccurrence(sortedArr, 2);
        System.out.println("First and last occurrence of 2: [" + occurrences[0] + ", " + occurrences[1] + "]");
        
        System.out.println();
    }
    
    private static void demonstrateSortingAlgorithms() {
        System.out.println("3. SORTING ALGORITHMS:\n");
        
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(arr1));
        
        // Quick Sort
        int[] arr2 = arr1.clone();
        quickSort(arr2);
        System.out.println("Quick Sort: " + Arrays.toString(arr2));
        
        // Merge Sort
        int[] arr3 = arr1.clone();
        mergeSort(arr3);
        System.out.println("Merge Sort: " + Arrays.toString(arr3));
        
        System.out.println();
    }
    
    private static void demonstrateArrayManipulation() {
        System.out.println("4. ARRAY MANIPULATION:\n");
        
        // Rearrange even-odd
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("Original array: " + Arrays.toString(arr1));
        rearrangeEvenOdd(arr1);
        System.out.println("Even-Odd rearranged: " + Arrays.toString(arr1));
        
        // Move zeros
        int[] arr2 = {0, 1, 0, 3, 12, 0, 5};
        System.out.println("\nOriginal array: " + Arrays.toString(arr2));
        moveZerosToEnd(arr2);
        System.out.println("Zeros moved to end: " + Arrays.toString(arr2));
        
        // Max subarray sum
        int[] arr3 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("\nArray: " + Arrays.toString(arr3));
        System.out.println("Maximum subarray sum: " + maxSubarraySum(arr3));
        
        System.out.println();
    }
    
    private static void demonstratePerformanceAnalysis() {
        System.out.println("5. PERFORMANCE ANALYSIS:\n");
        
        // Performance comparison for different array sizes
        int[] sizes = {1000, 10000, 100000};
        
        for (int size : sizes) {
            int[] arr = generateRandomArray(size);
            
            // Quick Sort performance
            long startTime = System.nanoTime();
            int[] quickArr = arr.clone();
            quickSort(quickArr);
            long quickTime = System.nanoTime() - startTime;
            
            // Merge Sort performance
            startTime = System.nanoTime();
            int[] mergeArr = arr.clone();
            mergeSort(mergeArr);
            long mergeTime = System.nanoTime() - startTime;
            
            System.out.printf("Array size %d:\n", size);
            System.out.printf("  Quick Sort: %d ns\n", quickTime);
            System.out.printf("  Merge Sort: %d ns\n", mergeTime);
            System.out.println();
        }
    }
    
    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }
}

/**
 * INTERVIEW QUESTIONS COVERED:
 * 
 * THEORETICAL:
 * 1. What is the time complexity of array rotation using reversal algorithm?
 * 2. Explain the difference between left and right rotation.
 * 3. How does the juggling algorithm work for array rotation?
 * 4. What is the time complexity of searching in a rotated sorted array?
 * 5. Explain Kadane's algorithm for maximum subarray sum.
 * 6. What are the advantages and disadvantages of Quick Sort vs Merge Sort?
 * 
 * PRACTICAL:
 * 1. Implement array rotation without extra space.
 * 2. Find the peak element in an array efficiently.
 * 3. Implement Quick Sort with proper partitioning.
 * 4. Rearrange array elements based on certain conditions.
 * 5. Optimize array operations for large datasets.
 * 
 * TRICKY SCENARIOS:
 * 1. Handle edge cases in array rotation (k > array length).
 * 2. Deal with duplicate elements in rotated array search.
 * 3. Implement stable sorting when order matters.
 * 4. Handle memory constraints in large array operations.
 * 
 * PERFORMANCE:
 * 1. Compare different rotation algorithms.
 * 2. Analyze sorting algorithm performance on different data distributions.
 * 3. Optimize search algorithms for specific use cases.
 * 4. Memory usage analysis for different approaches.
 * 
 * DESIGN PATTERNS:
 * 1. Strategy pattern for different sorting algorithms.
 * 2. Template method for search algorithms.
 * 3. Factory pattern for algorithm selection.
 * 4. Builder pattern for complex array operations.
 */
