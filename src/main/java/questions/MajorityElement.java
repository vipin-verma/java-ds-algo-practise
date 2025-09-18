package questions;

public class MajorityElement {
    // Function to find the majority element
    public int majorityElement(int[] nums) {
        int count = 0, candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    // Main method to test the code
    public static void main(String[] args) {
        MajorityElement obj = new MajorityElement();
        int[] nums1 = {3, 2, 3};
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2,1,1};
        System.out.println(obj.majorityElement(nums1));  // Output: 3
        System.out.println(obj.majorityElement(nums2));  // Output: 2
    }
}
