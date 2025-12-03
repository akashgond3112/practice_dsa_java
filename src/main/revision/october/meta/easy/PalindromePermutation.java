package main.revision.october.meta.easy;

public class PalindromePermutation {

    public class Solution {

        public boolean canPermutePalindrome(String str) {
            if (str == null || str.isEmpty()) {
                return true;
            }

            // Count frequency of each character
            int[] charCount = new int[128]; // ASCII characters
            for (char c : str.toCharArray()) {
                charCount[c]++;
            }

            // At most one character can have odd frequency for palindrome permutation
            int oddCount = 0;
            for (int count : charCount) {
                if (count % 2 != 0) {
                    oddCount++;
                }
                if (oddCount > 1) {
                    return false;
                }
            }

            return true;
        }
    }

    // revised on 12/1/2025
    public class SolutionRevisedOnThirdDay {

        public boolean canPermutePalindrome(String str) {

            if (str == null || str.isEmpty()) {
                return false;
            }

            int[] charCount = new int[128];
            for (char c : str.toCharArray()) {
                charCount[c]++;
            }

            int oddCount = 0;
            for (int count : charCount) {

                if (count % 2 != 0) {
                    oddCount++;
                }

                if (oddCount > 1) {
                    return false;
                }
            }

            return true;
        }
    }
}
