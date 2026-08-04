/**
 * @author akash
 * @date Aug 02, 2026
 * @time 4:13:47 PM
 */
package main.pattern.slidingwindow.size.variable;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int max = 0;
            int left = 0;

            Map<Character, Integer> map = new HashMap<>();

            for (int right = 0; right < s.length(); right++) {

                char c = s.charAt(right);

                if (map.containsKey(c)) {
                    left = Math.max(left, map.get(c) + 1);
                }

                map.put(c, right);

                max = Math.max(max, right - left + 1);
            }
            return max;
        }
    }
}
