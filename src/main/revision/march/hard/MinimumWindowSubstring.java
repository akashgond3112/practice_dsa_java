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
    class SolutionRevisedOnDayThird {
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
    class SolutionRevisedOnDaySeventh {
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
            int min = Integer.MAX_VALUE;

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

            return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);

        }
    }

    // 26/04/2026
    class SolutionRevisedOnDayFourteen {
        public String minWindow(String s, String t) {

            if (s.length() < t.length())
                return "";
            int startIndex = 0;
            int endIndex = 0;
            int max = Integer.MAX_VALUE; // Initialize max with a smaller value
            int count = 0;

            Map<Character, Integer> characterHashMap = new HashMap<>();

            for (char c : t.toCharArray()) {
                characterHashMap.put(c, characterHashMap.getOrDefault(c, 0) + 1);
            }

            count = characterHashMap.size();

            int maxStart = 0; // to track Start index of substring
            int maxEnd = 0; // to track End index of substring

            while (endIndex < s.length()) {
                char ch = s.charAt(endIndex);
                if (characterHashMap.containsKey(ch)) {
                    int freq = characterHashMap.get(s.charAt(endIndex));
                    freq--;
                    characterHashMap.put(ch, freq);
                    if (characterHashMap.get(ch) == 0)
                        count--;

                }

                while (count == 0) {

                    // Get best solution
                    if (max > endIndex - startIndex + 1) {
                        max = endIndex - startIndex + 1;
                        maxStart = startIndex;
                        maxEnd = endIndex + 1;
                    }

                    char tempCharStart = s.charAt(startIndex);
                    if (characterHashMap.containsKey(tempCharStart)) {
                        int freq = characterHashMap.get(tempCharStart);
                        freq++;
                        characterHashMap.put(tempCharStart, freq);
                        if (characterHashMap.get(tempCharStart) > 0) {
                            count++;
                        }
                    }
                    startIndex++;
                }
                endIndex++;
            }

            return s.substring(maxStart, maxEnd);
        }
    }
}
