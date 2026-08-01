/**
 * @author akash
 * @date Aug 01, 2026
 * @time 9:54:46 AM
 */
package main.pattern.slidingwindow;

public class PermutationInString {

    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            if (s1.length() > s2.length()) {
                return false;
            }

            int[] freq = new int[26];
            for (int i = 0; i < s1.length(); i++) {
                freq[s1.charAt(i) - 'a']++;
            }

            int left = 0;
            int right = 0;
            int needed = s1.length();

            while (right < s2.length()) {
                if (freq[s2.charAt(right) - 'a']-- > 0) {
                    needed--;
                }
                right++;

                if (needed == 0) {
                    return true;
                }

                if (right - left == s1.length()) {
                    if (freq[s2.charAt(left) - 'a']++ >= 0) {
                        needed++;
                    }
                    left++;
                }
            }

            return false;
        }
    }
}
