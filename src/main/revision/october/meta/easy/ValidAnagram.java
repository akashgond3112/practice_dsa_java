package main.revision.october.meta.easy;

public class ValidAnagram {
    class Solution {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                count[t.charAt(i) - 'a']--;
            }

            for (int val : count) {
                if (val != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // revised on 12/9/2025
    public class SolutionRevisedOnThirdDay {
        public boolean isAnagram(String s, String t) {

            if (s.length() != t.length()) {
                return false;
            }

            int[] count = new int[26];

            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                count[t.charAt(i) - 'a']--;
            }

            for (int val : count) {
                if (val != 0) {
                    return false;
                }
            }

            return true;

        }
    }

    // revised on 12/15/2025
    public class SolutionRevisedOnSeventhDay {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            int[] count = new int[26];

            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                count[t.charAt(i) - 'a']--;
            }

            for (int val : count) {
                if (val != 0) {
                    return false;
                }
            }

            return true;

        }
    }

    // revised on 12/29/2025
    public class SolutionRevisedOnFourteenDay {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            int[] count = new int[26];

            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                count[t.charAt(i) - 'a']--;
            }

            for (int val : count) {
                if (val != 0) {
                    return false;
                }
            }

            return true;

        }
    }

    // revised on 1/27/2026
    class SolutionRevisedOnDayThirty {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            int[] count = new int[26];

            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                count[t.charAt(i) - 'a']--;
            }

            for (int val : count) {
                if (val != 0) {
                    return false;
                }
            }

            return true;
        }
    }
}
