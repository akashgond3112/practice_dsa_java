package main.revision.october.meta.medium;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            // Ek HashMap banate hain jo character aur unki index ko store karega
            HashMap<Character, Integer> mp = new HashMap<>();
            int l = 0, res = 0; // 'l' left pointer hai aur 'res' result ko store karega

            // String ke har character ke liye loop chalayenge
            for (int r = 0; r < s.length(); r++) {
                // Agar current character pehle se map mein hai
                if (mp.containsKey(s.charAt(r))) {
                    // Left pointer ko update karte hain taki repeating character ko skip kar sake
                    l = Math.max(mp.get(s.charAt(r)) + 1, l);
                }
                // Current character ko map mein daalte hain ya update karte hain
                mp.put(s.charAt(r), r);
                // Result ko update karte hain agar naya substring zyada lamba ho
                res = Math.max(res, r - l + 1);
            }
            return res; // Longest substring ki length return karte hain
        }
    }

    // revised on 12/22/2025
    class SolutionRevisedOnThirdDay {
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int l = 0;
            int result = 0;

            for (int r = 0; r < s.length(); r++) {
                char c = s.charAt(r);
                if (map.containsKey(c)) {
                    l = Math.max(l, map.get(c) + 1);
                }

                map.put(c, r);
                result = Math.max(result, r - l + 1);
            }

            return result;
        }
    }

    // revised on 12/28/2025
    class SolutionRevisedOnSeventhDay {
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int left = 0;
            int result = 0;

            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);

                if (map.containsKey(c)) {
                    left = Math.max(left, map.get(c) + 1);
                }

                map.put(c, right);
                result = Math.max(result, right - left + 1);
            }

            return result;

        }
    }

    // revised on 1/11/2026
    class SolutionRevisedOnDayFourteen {
        public int lengthOfLongestSubstring(String s) {

            Map<Character, Integer> map = new HashMap<>();
            int left = 0;
            int result = 0;

            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);

                if (map.containsKey(c)) {
                    left = Math.max(left, map.get(c) + 1);
                }

                map.put(c, right);
                result = Math.max(result, (right - left) + 1);
            }

            return result;
        }
    }
}
