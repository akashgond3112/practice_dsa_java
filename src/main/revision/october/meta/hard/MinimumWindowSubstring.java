package main.revision.october.meta.hard;

import java.util.*;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        // Step 1: Quick reject
        // Hinglish: "Agar t ka length, s se bada hai to koi valid window nahi milega —
        // seedha empty return karo"
        if (t.length() > s.length()) {
            return "";
        }

        // Step 2: Build frequency map for characters in t
        // Hinglish: "tMap mein har character ka required count store kar rahe hain (jo
        // hume window mein chahiye)"
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        // Step 3: Initialize sliding window variables
        // Hinglish: "required = kitne unique characters ka sahi count chahiye; formed =
        // abhi tak kitne characters correct hue"
        int required = tMap.size();
        int formed = 0; // abhi tak matched unique char groups
        int left = 0; // window ka left pointer
        int minLength = Integer.MAX_VALUE; // best window length
        int start = 0; // best window ka start index

        // windowCounts -> current window ke character counts
        // Hinglish: "ye map current window ke char counts rakhta hai, compare karne ke
        // liye"
        Map<Character, Integer> windowCounts = new HashMap<>();

        // Step 4: Expand the window by moving 'right'
        // Hinglish: "Right pointer se window expand karte jao aur har char ka count
        // update karo"
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            // Step 4.1: If this char is required and now its count matches required count,
            // increment formed
            // Hinglish: "Agar ye char tMap mein tha aur ab window mein required count poora
            // ho gaya to formed++ karo"
            if (tMap.containsKey(c) && windowCounts.get(c).intValue() == tMap.get(c).intValue()) {
                formed++;
            }

            // Step 5: When all required unique chars are formed, try to shrink from the
            // left
            // Hinglish: "Jab formed == required ho jaye, left se shrink kar ke smallest
            // valid window find karo"
            while (left <= right && formed == required) {
                char ch = s.charAt(left);

                // Step 5.1: Update minimum window if current is smaller
                // Hinglish: "Agar current window chhota hai to usko best window bana lo"
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }

                // Step 5.2: Remove leftmost char from windowCounts (we are shrinking window)
                // Hinglish: "Left se char hata rahe hain, count ghata rahe hain"
                windowCounts.put(ch, windowCounts.get(ch) - 1);

                // Step 5.3: If a required char count fell below required, decrement formed
                // Hinglish: "Agar jo char t mein required tha uska count ab kam ho gaya to
                // formed-- karo"
                if (tMap.containsKey(ch) && windowCounts.get(ch) < tMap.get(ch)) {
                    formed--;
                }

                // Step 5.4: Move left forward to continue shrinking
                // Hinglish: "Left aage badhao taaki aur chhota window try kar sako"
                left++;
            }
        }

        // Step 6: Return result (either empty or the best substring)
        // Hinglish: "Agar minLength update nahi hua matlab koi valid window nahi mila,
        // warna substring return karo"
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

    // Revision on 12/8/2025
    public String minWindowDayThirty(String s, String t) {

        if (t.length() > s.length()) {
            return "";
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int required = map.size();
        int formed = 0;
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0;

        Map<Character, Integer> windowCounts = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {

            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            if (map.containsKey(c) && windowCounts.get(c).intValue() == map.get(c).intValue()) {
                formed++;
            }

            while (left <= right && formed == required) {

                char cur = s.charAt(left);

                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }

                windowCounts.put(cur, windowCounts.get(cur) - 1);

                if (map.containsKey(cur) && windowCounts.get(cur) < map.get(cur)) {
                    formed--;
                }

                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }
}
