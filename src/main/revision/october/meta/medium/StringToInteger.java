package main.revision.october.meta.medium;

public class StringToInteger {

    class Solution {
        public int myAtoi(String s) {
            // Trim whitespace
            s = s.trim();

            if (s.isEmpty()) {
                return 0;
            }

            int i = 0;
            boolean isNegative = false;

            // Check for sign
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                isNegative = s.charAt(i) == '-';
                i++;
            }

            // Process digits
            long result = 0; // Using long to check for overflow
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                result = result * 10 + (s.charAt(i) - '0');

                // Check for overflow
                if (result > Integer.MAX_VALUE) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }

                i++;
            }

            return isNegative ? -(int) result : (int) result;
        }
    }

    // revised on 12/7/2025
    class SolutionRevisedOnThirdDay {
        public int myAtoi(String s) {

            // Trim whitespace
            s = s.trim();

            if (s.isEmpty()) {
                return 0;
            }

            int i = 0;
            boolean isNegative = false;

            // Check for sign
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                isNegative = s.charAt(i) == '-';
                i++;
            }

            // Process digits
            long result = 0; // Using long to check for overflow
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                result = result * 10 + (s.charAt(i) - '0');

                // Check for overflow
                if (result > Integer.MAX_VALUE) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }

                i++;
            }

            return isNegative ? -(int) result : (int) result;
        }
    }

    // revised on 12/13/2025
    class SolutionRevisedOnSeventhDay {
        public int myAtoi(String s) {

            s = s.trim();

            if (s.isEmpty()) {
                return 0;
            }

            int i = 0;
            boolean isNegative = false;
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                isNegative = s.charAt(i) == '-';
                i++;
            }

            long result = 0;

            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                result *= 10 + s.charAt(i) - '0';

                if (result > Integer.MAX_VALUE) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                i++;
            }
            return isNegative ? -(int) result : (int) result;
        }
    }

    // revised on 12/27/2025
    class SolutionRevisedOnFourteenDay {
        public int myAtoi(String s) {

            s = s.trim();

            if (s.isEmpty()) {
                return 0;
            }

            int i = 0;
            boolean isNegative = false;

            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                isNegative = s.charAt(i) == '-';
                i++;
            }

            long result = 0;

            while (i < s.length() && Character.isDigit(s.charAt(i))) {

                result *= 10 + s.charAt(i) - '0';

                if (result > Integer.MAX_VALUE) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                i++;
            }

            return isNegative ? -(int) result : (int) result;
        }
    }
}
