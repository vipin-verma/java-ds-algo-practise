package leetcode;

import java.util.Arrays;

public class RemoveElement {

    public static void main (String [] args) {

        int[] nums = {1, 2, 3, 3, 4, 5, 3};
        int val = 3;

        System.out.println(removeElement(nums , val ));
        System.out.println(Arrays.toString(nums));
    }

    private static int removeElement(int[] nums, int val) {

        int k = 0 ;
        for (int i=0 ; i< nums.length ; i++)
        {
              if (nums[i] != val)
              {
                  nums[k] = nums[i];
                  k++;
              }
        }
        return k;
    }
}
