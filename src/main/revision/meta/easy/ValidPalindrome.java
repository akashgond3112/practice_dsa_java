package main.revision.meta.easy;

/**
 * @author agond
 * @date May 29, 2025
 * @time 8:29:26 PM
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;

    }
}
