package main.revision.october.meta.medium;

public class PalindromicSubstrings {

    class Solution {
        public int countSubstrings(String s) {
            int count = 0;

            // Har character ko center maan ke check karo
            for (int i = 0; i < s.length(); i++) {

                // Odd length palindrome ke liye (jaise "aba")
                count += countPalindromes(s, i, i);

                // Even length palindrome ke liye (jaise "aa")
                count += countPalindromes(s, i, i + 1);
            }

            return count;
        }

        // Yeh function left aur right index se expand karke palindrome check karta hai
        private static int countPalindromes(String s, int l, int r) {
            int count = 0;
            // Jab tak left aur right valid hain aur characters same hain
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                count++; // Palindrome mila, count badhao
                l--; // Left mein ek peeche jao
                r++; // Right mein ek aage jao
            }
            return count;
        }
    }

    // revised on 12/17/2025
    class SolutionRevisedOnThirdDay {
        public int countSubstrings(String s) {
            int count = 0;

            for (int i = 0; i < s.length(); i++) {
                count += countPalindromes(s, i, i);
                count += countPalindromes(s, i, i + 1);
            }
            return count;
        }

        private static int countPalindromes(String s, int l, int r) {
            int count = 0;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                count++;
                l--; // Left mein ek peeche jao
                r++; // Right mein ek aage jao
            }
            return count;
        }
    }

    // revised on 12/23/2025
    class SolutionRevisedOnDaySeventh {
        public int countSubstrings(String s) {
            int count = 0;

            for (int i = 0; i < s.length(); i++) {

                count += countPalindromes(s, i, i);
                count += countPalindromes(s, i, i + 1);
            }
            return count;

        }

        private static int countPalindromes(String s, int l, int r) {
            int count = 0;

            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                count++;
                l--; // Left mein ek peeche jao
                r++; // Right mein ek aage jao
            }

            return count;
        }
    }
}
