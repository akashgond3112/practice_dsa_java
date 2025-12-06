package main.revision.october.meta.easy;

public class ValidPalindrome {
    private boolean isValidPalindrome(String s, int left, int right) {

        while (left < right) {
            // Move left pointer to the right until an alphanumeric character is found
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Move right pointer to the left until an alphanumeric character is found
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare characters at left and right pointers
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            // Move pointers
            left++;
            right--;
        }

        return true;
    }

    // Revised on 18.10.2025
    private boolean isValidPalindromeRev_2(String s, int left, int right) {

        while (left < right) {

            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // Revised on 11/30/2025
    private boolean isValidPalindromeRev_4(String s, int left, int right) {

        while (left < right) {

            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.isLowerCase(s.charAt(left)) != Character.isLowerCase(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
