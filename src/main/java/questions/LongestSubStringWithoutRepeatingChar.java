package questions;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringWithoutRepeatingChar {

    public static void main (String [] args)
    {
        String s = "abcabacbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    private static String  lengthOfLongestSubstring(String s) {
        Map<Character, Integer> freq  = new HashMap<>();

        int left =0 , best = 0;

        for (int right = 0 ; right < s.length() ; right++){
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) +1 );

            while(freq.get(c) > 1)
            {
                char l = s.charAt(left++);
                freq.put(l , freq.get(l) - 1);

                if (freq.get(l) ==0 ) freq.remove(l);

            }

            best = Math.max(best , right - left + 1);

        }

        return s.substring(left -1  , left + best -1 )  ;

    }


}
