package questions;

import java.util.*;

public class SlidingWindowPrint {
    public static String longestSubstringWithoutRepeating(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0, bestLen = 0, startIndex = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            // Shrink until no duplicate
            while (freq.get(c) > 1) {
                char L = s.charAt(left++);
                freq.put(L, freq.get(L) - 1);
                if (freq.get(L) == 0) freq.remove(L);
            }

            // Update best substring
            if (right - left + 1 > bestLen) {
                bestLen = right - left + 1;
                startIndex = left;
            }
        }

        return s.substring(startIndex, startIndex + bestLen);
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(longestSubstringWithoutRepeating(s));
        // Output: "abc"
    }
}
