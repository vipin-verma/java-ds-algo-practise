package questions;

import java.util.Arrays;

public class TwoPointersTwoSum {

    public static  void main (String [] args)
    {
        int[] arr = {1, 2, 4, 6, 8, 11, 15};
        int target = 10;
        int[] ans = twoSumSorted(arr, target);
        System.out.println(Arrays.toString(ans));

    }

    private static int[] twoSumSorted(int[] arr, int target) {
        int l = 0 , r = arr.length -1 ;
        while (l<r)
        {
            int sum = arr[l] + arr[r];
            if (sum == target ) return new int [] {l,r};
            if (sum < target ) l++ ; else r--;
        }
   return new int [] {-1, -1};
    }
}
