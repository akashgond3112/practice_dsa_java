/**
 * @author akash
 * @date Aug 08, 2026
 * @time 8:35:12 AM
 */
package main.pattern.slidingwindow.size.variable;

import java.util.*;

public class MinimumWindowSubstring {
    class Solution {
        public String minWindow(String s, String t) {

            int n = s.length();
            int m = t.length();

            Map<Character, Integer> map = new HashMap<>();

            for (char c : t.toCharArray()) {

                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            int required = map.size();
            int formed = 0;
            int min = Integer.MAX_VALUE;

            int left = 0;
            int right = 0;
            int start = 0;

            Map<Character, Integer> count = new HashMap<>();

            while (right < n) {

                char c = s.charAt(right);
                count.put(c, count.getOrDefault(c, 0) + 1);

                if (map.containsKey(c) && Objects.equals(map.get(c), count.get(c))) {
                    formed++;
                }

                while (left <= right && formed == required) {
                    char ch = s.charAt(left);

                    if (right - left + 1 < min) {
                        min = right - left + 1;
                        start = left;
                    }

                    count.put(ch, count.getOrDefault(ch, 0) - 1);

                    if (map.containsKey(ch) && count.get(ch) < map.get(ch)) {
                        formed--;
                    }

                    left++;
                }

                right++;
            }

            return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
        }
    }
}
