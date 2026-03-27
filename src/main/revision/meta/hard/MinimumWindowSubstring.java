/**
 * @author agond
 * @date Jul 06, 2025
 * @time 9:38:20 AM
 */
package main.revision.meta.hard;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            char c = t.charAt(i);

            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int required = map.size();
        int formed = 0;

        Map<Character, Integer> windowCounts = new HashMap<>();

        int minLength = Integer.MAX_VALUE;
        int start = 0;

        while (right < n) {

            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            if (map.containsKey(c) && windowCounts.get(c).intValue() == map.get(c).intValue()) {
                formed++;
            }

            while (left <= right && formed == required) {
                char ch = s.charAt(left);

                // calculate window size
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }

                windowCounts.put(ch, windowCounts.getOrDefault(ch, 0) - 1);

                if (map.containsKey(ch) && windowCounts.get(ch) < map.get(ch)) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }
}
