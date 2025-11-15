package main.revision.october.meta.hard;

import java.util.*;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int required = tMap.size();
        int formed = 0;
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0;

        Map<Character, Integer> windowCounts = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            if (tMap.containsKey(c) && windowCounts.get(c).intValue() == tMap.get(c).intValue()) {
                formed++;
            }

            while (left <= right && formed == required) {
                char ch = s.charAt(left);

                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }

                windowCounts.put(ch, windowCounts.get(ch) - 1);

                if (tMap.containsKey(ch) && windowCounts.get(ch) < tMap.get(ch)) {
                    formed--;
                }

                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }

    // Revision on 20/10/2025
    public String minWindowThirdDay(String s, String t) {

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

    // Revision on 26/10/2025
    public String minWindowSeventhDay(String s, String t) {

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

        Map<Character, Integer> windowsCount = new HashMap<>();
        int minLength = Integer.MAX_VALUE;
        int start = 0;

        while (right < n) {

            char c = s.charAt(start);
            windowsCount.put(c, map.getOrDefault(c, 0) + 1);

            if (map.containsKey(c) && windowsCount.get(c).intValue() == windowsCount.get(c).intValue()) {
                formed++;
            }

            while (left <= right && formed == required) {
                char ch = s.charAt(start);

                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }

                windowsCount.put(ch, map.getOrDefault(ch, 0) - 1);

                if (map.containsKey(ch) && windowsCount.get(ch) < map.get(ch)) {
                    formed--;
                }
                left++;
            }

            right++;
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }

    // Revision on 11/9/2025
    public String minWindowFourteenDay(String s, String t) {

        if (t.length() > s.length()) {
            return "";
        }

        Map<Character, Integer> map = new HashMap<>();

        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int n = s.length();
        int required = 0;
        int formed = map.size();
        int right = 0;
        int left = 0;
        int min = Integer.MAX_VALUE;
        int start = 0;

        Map<Character, Integer> windowsCount = new HashMap<>();

        while (right < n) {

            char c = s.charAt(right);
            windowsCount.put(c, windowsCount.getOrDefault(c, 0) + 1);

            if (map.containsKey(c) && map.get(c) == windowsCount.get(c)) {
                formed++;
            }

            if (left <= right && formed == required) {
                char ch = s.charAt(left);

                if (right - left + 1 < min) {
                    min = right - left + 1;
                    start = left;
                }

                windowsCount.put(ch, windowsCount.getOrDefault(ch, 0) - 1);

                if (map.containsKey(ch) && windowsCount.get(ch) < map.get(ch)) {
                    formed--;
                }
                left++;
            }
            right++;
        }

        return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
    }
}
