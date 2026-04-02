package main.revision.march.hard;

import java.util.*;

public class MinimumWindowSubstring {

    // 24/03/2026
    class SolutionOnDayFirst {
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

    // 27/03/2026
    class SolutionRevisedOnDayFirst {
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

            Map<Character, Integer> count = new HashMap<>();

            int minLength = Integer.MAX_VALUE;
            int start = 0;

            while (right < n) {

                char c = s.charAt(right);
                count.put(c, count.getOrDefault(c, 0) + 1);

                if (count.containsKey(c) && count.get(c) == map.get(c)) {
                    formed++;
                }

                while (left <= right && formed == required) {

                    char cur = s.charAt(left);

                    if (right - left + 1 < minLength) {
                        minLength = right - left + 1;
                        start = left;
                    }

                    count.put(cur, count.getOrDefault(cur, 0) - 1);

                    if (map.containsKey(cur) && count.get(cur) < map.get(cur)) {
                        formed--;
                    }
                    left++;

                }
                right++;

            }

            return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
        }
    }

    // 02/04/2026
    class SolutionRevisedOnDayThir {
        public String minWindow(String s, String t) {

            if (t.length() > s.length()) {
                return "";
            }

            Map<Character, Integer> map = new HashMap<>();
            for (char c : t.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            int formed = 0;
            int required = map.size();
            int left = 0;
            int start = 0;
            int min = Integer.MIN_VALUE;

            Map<Character, Integer> winCount = new HashMap<>();

            for (int right = 0; right < s.length(); right++) {

                char c = s.charAt(right);

                winCount.put(c, winCount.getOrDefault(c, 0) + 1);

                if (map.containsKey(c) && map.get(c) == winCount.get(c)) {
                    formed++;
                }

                while (left <= right && formed == required) {
                    char ch = s.charAt(left);

                    if (right - left + 1 < min) {
                        min = right - left + 1;
                        start = left;
                    }

                    winCount.put(ch, winCount.getOrDefault(ch, 0) - 1);

                    if (map.containsKey(ch) && winCount.get(ch) < map.get(ch)) {
                        formed--;
                    }
                    left++;

                }

            }

            return min == Integer.MIN_VALUE ? "" : s.substring(start, start + min);

        }
    }
}
