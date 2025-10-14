package main.revision.october.meta.easy;

public class ValidPalindromeTwo {

    public boolean validPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return isValidPalindrome(s, left + 1, right) ||
                        isValidPalindrome(s, left, right - 1);
            }
        }

        return true;
    }

    private boolean isValidPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }

        return true;
    }

    // Revised on 08.10.2025
    public boolean validPalindrome_Rev_1(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return isValidPalindrome(s, left + 1, right) || isValidPalindrome(s, left, right - 1);
            }
        }

        return true;
    }

}