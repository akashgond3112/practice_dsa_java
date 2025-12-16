package main.revision.october.meta.easy;

public class PalindromeNumber {

    public class Solution {
        public boolean isPalindrome(int x) {
            // Agar number negative hai to palindrome nahi ho sakta
            if (x < 0) {
                return false;
            }

            // Divisor ko initialize karte hain 1 se
            long div = 1;
            // Divisor ko set karte hain taaki wo number ke highest place value tak pahunch
            // jaye
            while (x >= 10 * div) {
                div *= 10;
            }

            // Jab tak number 0 nahi ho jata, har digit ko check karte hain
            while (x != 0) {
                // Agar leftmost aur rightmost digits match nahi karte to palindrome nahi hai
                if (x / div != x % 10) {
                    return false;
                }
                // Leftmost aur rightmost digits ko remove karte hain
                x = (int) (x % div) / 10;
                // Divisor ko adjust karte hain kyunki humne 2 digits remove kar di hain
                div /= 100;
            }

            // Agar saare digits match karte hain to number palindrome hai
            return true;
        }
    }

    // revised on 12/16/2025
    public class SolutionRevisedOnThirdDay {
        public boolean isPalindrome(int x) {

            if (x < 0) {
                return false;
            }

            long div = 1;

            while (x >= 10 * div) {
                div *= 10;
            }

            while (x != 0) {

                if (x / div != x % 10) {
                    return false;
                }

                x = (int) (x % div) / 10;

                div = div / 100;
            }

            return true;
        }
    }
}
