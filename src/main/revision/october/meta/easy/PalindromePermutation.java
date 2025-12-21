package main.revision.october.meta.easy;

public class PalindromePermutation {

    public class Solution {

        public boolean canPermutePalindrome(String str) {
            // Step 1: Null ya empty string check karo
            if (str == null || str.isEmpty()) {
                return true; // Agar string null ya empty hai, toh palindrome permutation possible hai
            }

            // Step 2: Har character ka frequency count karo
            int[] charCount = new int[128]; // ASCII characters ke liye array
            for (char c : str.toCharArray()) {
                charCount[c]++; // Character ka count badhao
            }

            // Step 3: Check karo ki odd frequency wale characters zyada toh nahi hain
            int oddCount = 0; // Odd frequency count karne ke liye variable
            for (int count : charCount) {
                if (count % 2 != 0) { // Agar frequency odd hai
                    oddCount++; // Odd count badhao
                }
                if (oddCount > 1) { // Agar ek se zyada odd frequency wale characters hain
                    return false; // Palindrome permutation possible nahi hai
                }
            }

            // Step 4: Agar odd frequency wale characters ek ya zero hain, toh palindrome
            // permutation possible hai
            return true;
        }
    }

    // revised on 12/7/2025
    public class SolutionRevisedOnSeventhDay {

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

    // revised on 12/21/2025
    public class SolutionRevisedOnFourteenDay {

        public boolean canPermutePalindrome(String str) {
            if (str == null || str.isEmpty()) {
                return false;
            }

            char[] charCount = new char[256];

            for (char c : str.toCharArray()) {
                charCount[c]++;
            }

            int oddCount = 0;
            for (int count : charCount) {

                if (count % 2 != 0) {
                    oddCount++;
                }

                if (oddCount > 0) {
                    return false;
                }
            }

            return true;
        }
    }
}
