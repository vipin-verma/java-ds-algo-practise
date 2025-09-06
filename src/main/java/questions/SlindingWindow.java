package questions;

import java.util.HashMap;
import java.util.Map;

public class SlindingWindow {

    public static void main(String[] args) {
        String input = "abcabcbb";
        System.out.println("Longest substring without repeat length: "
                + longestSubstringWithoutRepeat(input)); // Output: 3
    }

    private static int longestSubstringWithoutRepeat(String input) {
      int start = 0 , maxlen = 0;

        Map<Character , Integer> map = new HashMap<>();

        for (int end =0 ; end < input.length() ; end ++)
        {
            char rightChar = input.charAt(end);
            map.put(rightChar , map.getOrDefault(rightChar , 0) + 1);

            while (map.get(rightChar) > 1){
                char leftChar = input.charAt(start);

                map.put(leftChar , map.getOrDefault(leftChar, 0) -1);

                if(map.get(leftChar) ==0 ){

                    map.remove(leftChar);
                }
                start++;
            }
            maxlen = Math.max(maxlen , end-start + 1);

        }

        return maxlen;



    }

}
