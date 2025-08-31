package questions;

import java.util.*;

/**
 * SlidingWindowTemplates.java — Ek hi file me sabse zyada poochhe jaane wale Sliding Window ke templates.
 *
 * NOTE (ise copy-paste karke direct use karo):
 *  - Fixed-size Window (size = k)
 *  - Variable-size Window (distinct/duplicates/sum constraints)
 *  - Count subarrays (atMostK / exactlyK)
 *  - Typical interview problems ke ready methods + time complexities
 */
public class SlidingWindowTemplates {

    /* ------------------------------ FIXED SIZE ------------------------------ */
    // Max sum of any length-k subarray. O(n), O(1) space
    static int maxSumFixedWindow(int[] a, int k){
        int n=a.length, sum=0, best=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            sum+=a[i];
            if(i>=k) sum-=a[i-k];      // window slide
            if(i>=k-1) best=Math.max(best, sum);
        }
        return best;
    }

    // Max average of any length-k subarray. O(n)
    static double maxAverageFixedWindow(int[] a, int k){
        int best = maxSumFixedWindow(a,k);
        return best / (double) k;
    }

    /* ----------------------------- VARIABLE SIZE ---------------------------- */
    // Longest substring without repeating characters. O(n)
    static int longestUniqueSubstring(String s){
        int n=s.length(), left=0, best=0; int[] last=new int[256]; Arrays.fill(last,-1);
        for(int r=0;r<n;r++){
            char c=s.charAt(r);
            if(last[c]>=left) left=last[c]+1; // duplicate mila to left ko aage badhao
            last[c]=r;
            best=Math.max(best, r-left+1);
        }
        return best;
    }

    // Minimum length subarray with sum >= target (Assumption: all elements >= 0). O(n)
    static int minLenSubarraySumAtLeastTarget(int target, int[] a){
        int n=a.length, left=0, sum=0, ans=Integer.MAX_VALUE;
        for(int r=0;r<n;r++){
            sum+=a[r];
            while(sum>=target){
                ans=Math.min(ans, r-left+1);
                sum-=a[left++];
            }
        }
        return ans==Integer.MAX_VALUE?0:ans;
    }

    // Longest subarray with at most k zeros (a[i] is 0/1). O(n)
    static int longestOnesWithAtMostKZeroes(int[] a, int k){
        int left=0, zeros=0, best=0;
        for(int r=0;r<a.length;r++){
            if(a[r]==0) zeros++;
            while(zeros>k){
                if(a[left]==0) zeros--;
                left++;
            }
            best=Math.max(best, r-left+1);
        }
        return best;
    }

    /* -------------------------- COUNTING SUBARRAYS -------------------------- */
    // Helper: count subarrays with at most K distinct integers. O(n)
    static long countAtMostKDistinct(int[] a, int K){
        Map<Integer,Integer> freq=new HashMap<>();
        int left=0, distinct=0; long ans=0;
        for(int r=0;r<a.length;r++){
            freq.put(a[r], freq.getOrDefault(a[r],0)+1);
            if(freq.get(a[r])==1) distinct++;
            while(distinct> K){
                int x=a[left++];
                freq.put(x, freq.get(x)-1);
                if(freq.get(x)==0) { freq.remove(x); distinct--; }
            }
            ans += (r-left+1); // current window me end=r wale sab subarrays valid hain
        }
        return ans;
    }

    // Count subarrays with exactly K distinct: atMost(K) - atMost(K-1). O(n)
    static long countExactlyKDistinct(int[] a, int K){
        return countAtMostKDistinct(a,K) - countAtMostKDistinct(a,K-1);
    }

    /* ------------------------------- DEMO MAIN ------------------------------ */
    public static void main(String[] args){
        // Fixed-size demo
        int[] arr={1,12,-5,-6,50,3};
        System.out.println("MaxSum(k=4)="+maxSumFixedWindow(arr,4));
        System.out.println("MaxAvg(k=4)="+maxAverageFixedWindow(arr,4));

        // Variable-size demo
        String s="pwwkew";
        System.out.println("Longest Unique Substring length="+longestUniqueSubstring(s)); // 3 (wke)

        int[] pos={2,3,1,2,4,3};
        System.out.println("Min len sum>=7 = "+minLenSubarraySumAtLeastTarget(7,pos)); // 2 -> [4,3]

        int[] bin={1,1,1,0,0,1,1,0,1,1};
        System.out.println("Longest ones with <=2 zeros = "+longestOnesWithAtMostKZeroes(bin,2));

        int[] a={1,2,1,2,3};
        System.out.println("Subarrays with exactly K=2 distinct = "+countExactlyKDistinct(a,2)); // 7
    }
}

/*
CHEATSHEET (Hinglish):
1) Fixed Window (size k):
   - add a[r]; if (i>=k) sum-=a[i-k]; best update jab (i>=k-1)
   - Use-cases: max/min sum/avg of size-k, max vowels in size-k string, etc.

2) Variable Window (grow-shrink):
   - Right pointer badhao; condition toot-te hi left++ until condition theek
   - Use-cases: longest unique substring, at most K distinct, sum >= target (non-negative arrays only)

3) Counting Subarrays:
   - atMostK pattern: ans += (r-left+1)
   - exactlyK = atMostK(K) - atMostK(K-1)

4) Common Pitfalls:
   - Negative numbers present? sum-based sliding window often kaam nahi karta; prefix-sum/monotonic deque try karo.
   - String me extended charset? last[] size 256/128/65535 decide karo ya Map<Character,Integer> use karo.
   - Off-by-one: (i>=k-1) ya while(lo <= hi) type boundaries dhyan se.
*/
