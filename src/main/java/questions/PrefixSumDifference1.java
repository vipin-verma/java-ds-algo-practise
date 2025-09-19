package questions;

import java.util.Arrays;

public class PrefixSumDifference1 {

    private static int[] buildPrefix(int[] nums) {
        int[] prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return prefix;
    }

    private static int rangeSum(int[] prefix, int left, int rightInclusive) {
        return prefix[rightInclusive + 1] - prefix[left];
    }

    private static int[] applyRangeUpdates(int length, int[][] updates) {
        int[] diff = new int[length + 1];
        for (int[] update : updates) {
            int left = update[0];
            int right = update[1];
            int delta = update[2];
            diff[left] += delta;
            if (right + 1 < diff.length) {
                diff[right + 1] -= delta;
            }
        }

        int[] result = new int[length];
        int running = 0;
        for (int i = 0; i < length; i++) {
            running += diff[i];
            result[i] = running;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 5, 7, 11};
        int[] prefix = buildPrefix(nums);
        System.out.println("Numbers       : " + Arrays.toString(nums));
        System.out.println("Prefix sums   : " + Arrays.toString(prefix));
        System.out.println("Sum of [1,3]  : " + rangeSum(prefix, 1, 3));

        int[][] updates = {
                {0, 2, 3},  // add 3 to indices 0..2
                {1, 4, 2},  // add 2 to indices 1..4
                {3, 3, -1}  // subtract 1 from index 3
        };
        int[] afterUpdates = applyRangeUpdates(5, updates);
        System.out.println("Difference updates result: " + Arrays.toString(afterUpdates));
    }
}
