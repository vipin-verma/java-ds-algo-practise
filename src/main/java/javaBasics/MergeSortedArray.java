public class MergeSortedArray {
    
    /**
     * Merges two sorted arrays into the first array
     * nums1 has enough space to hold all elements from nums2
     * 
     * @param nums1 First array with extra space at the end
     * @param m Number of elements in nums1
     * @param nums2 Second array
     * @param n Number of elements in nums2
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Start from the end of nums1 (where the extra space is)
        int p1 = m - 1;  // Pointer for nums1
        int p2 = n - 1;  // Pointer for nums2
        int p = m + n - 1;  // Pointer for the merged result
        
        // Compare elements from both arrays and place larger one at the end
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
        
        // If there are remaining elements in nums2, copy them
        // (remaining elements in nums1 are already in their correct position)
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }
    
    /**
     * Alternative approach using extra space
     */
    public int[] mergeWithExtraSpace(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int i = 0, j = 0, k = 0;
        
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }
        
        // Copy remaining elements from nums1
        while (i < m) {
            result[k++] = nums1[i++];
        }
        
        // Copy remaining elements from nums2
        while (j < n) {
            result[k++] = nums2[j++];
        }
        
        return result;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        MergeSortedArray merger = new MergeSortedArray();
        
        // Test case 1: Given example
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        
        System.out.println("Original nums1: " + java.util.Arrays.toString(nums1));
        System.out.println("Original nums2: " + java.util.Arrays.toString(nums2));
        
        merger.merge(nums1, m, nums2, n);
        System.out.println("After merging: " + java.util.Arrays.toString(nums1));
        
        // Test case 2: Different arrays
        int[] nums3 = {4, 5, 6, 0, 0, 0};
        int m2 = 3;
        int[] nums4 = {1, 2, 3};
        int n2 = 3;
        
        System.out.println("\nTest case 2:");
        System.out.println("Original nums3: " + java.util.Arrays.toString(nums3));
        System.out.println("Original nums4: " + java.util.Arrays.toString(nums4));
        
        merger.merge(nums3, m2, nums4, n2);
        System.out.println("After merging: " + java.util.Arrays.toString(nums3));
        
        // Test case 3: Using extra space approach
        int[] nums5 = {1, 3, 5};
        int[] nums6 = {2, 4, 6};
        
        System.out.println("\nTest case 3 (with extra space):");
        System.out.println("nums5: " + java.util.Arrays.toString(nums5));
        System.out.println("nums6: " + java.util.Arrays.toString(nums6));
        
        int[] merged = merger.mergeWithExtraSpace(nums5, 3, nums6, 3);
        System.out.println("Merged result: " + java.util.Arrays.toString(merged));
    }
}
