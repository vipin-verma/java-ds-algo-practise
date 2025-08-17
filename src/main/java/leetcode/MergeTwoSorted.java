package leetcode;

import java.util.Arrays;

public class MergeTwoSorted {

    public static void main (String [] args)
    {
        int [] nums1 = {1,1,1,0,0};
        int m = 3 ;
        int n=2;
        int [] nums2 = {2,2};
        System.out.println(Arrays.toString(nums1));
        merge (nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));

    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m-1;
        int j = n-1;
        int k = m + n -1 ;

        while (i>= 0 && j>=0)
        {
            if (nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                i--;
            }else
            {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        while (j>=0)
        {
            nums1[k] = nums2[j];
            k--;
            j--;

        }
    }

}

