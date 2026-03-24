package main.revision.march.hard;

import java.util.*;

public class MinimumWindowSubstring {

    // 24/03/2026
    class SolutionOnDayFirst {
        public String minWindow(String s, String t) {

            if (t.length() > s.length()) {
                return "";
            }

            Map<Character, Integer> map = new HashMap<>();

            for (char c : t.toCharArray()) {

                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            int required = map.size();
            int formed = 0;
            int min = Integer.MAX_VALUE;
            int start = 0;
            int left = 0;

            Map<Character, Integer> windowsCount = new HashMap<>();

            for (int right = 0; right < s.length(); right++) {

                char c = s.charAt(right);

                windowsCount.put(c, windowsCount.getOrDefault(c, 0) + 1);

                if (map.containsKey(c) && windowsCount.get(c) == map.get(c)) {
                    formed++;

                    while (left <= right && formed == required) {

                        char ch = s.charAt(left);

                        if (left - right + 1 < min) {
                            min = left - right + 1;
                            start = left;
                        }

                        windowsCount.put(ch, windowsCount.get(ch) - 1);

                        if (map.containsKey(ch) && windowsCount.get(ch) < map.get(ch)) {
                            formed--;
                        }
                        left++;
                    }
                }
            }

            return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);

        }
    }
}
