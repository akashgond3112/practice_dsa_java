package main.revision.october.meta.medium;

import java.util.*;

public class MaximumSwap {

    class Solution {
        public int maximumSwap(int num) {
            // Step 1: Number ko string mein convert karke uske characters ka array banate
            // hain digits = ['2', '7', '3', '6']
            char[] digits = String.valueOf(num).toCharArray();

            // Step 2: Ek array banate hain jo har digit (0-9) ka rightmost index store
            // karega
            int[] rightMost = new int[10];
            Arrays.fill(rightMost, -1); // Initially sabhi indexes ko -1 se fill karte hain

            // Step 3: Har digit ka rightmost position array mein store karte hain
            // [-1, -1, 0, 2, -1, -1, 3, 1, -1, -1]
            for (int i = 0; i < digits.length; i++) {
                rightMost[digits[i] - '0'] = i; // Digit ko integer mein convert karke uska index store karte hain
            }

            // Step 4: Pehla position dhundhte hain jahan swap karna beneficial ho
            for (int i = 0; i < digits.length; i++) {
                // Current digit se badi digit ko dhundhte hain jo baad mein aaye
                for (int j = 9; j > digits[i] - '0'; j--) {
                    // Agar badi digit exist karti hai aur current position ke baad aati hai
                    if (rightMost[j] > i) {
                        // Step 5: Digits ko swap karte hain
                        char temp = digits[i];
                        digits[i] = digits[rightMost[j]];
                        digits[rightMost[j]] = temp;

                        // Step 6: Array ko wapas integer mein convert karke return karte hain
                        return Integer.parseInt(new String(digits));
                    }
                }
            }

            // Step 7: Agar koi beneficial swap nahi mila to original number return karte
            // hain
            return num;
        }
    }

    // revised on 11/29/2025
    class SolutionRevisonOnThirdDay {
        public int maximumSwap(int num) {

            char[] digits = String.valueOf(num).toCharArray();

            int[] rightMost = new int[10];
            Arrays.fill(rightMost, -1);

            for (int i = 0; i < digits.length; i++) {
                rightMost[digits[i] - '0'] = i;
            }

            for (int i = 0; i < digits.length; i++) {
                for (int j = 9; j > digits[i] - '0'; j--) {

                    if (rightMost[j] > i) {
                        char tmp = digits[i];
                        digits[i] = digits[rightMost[j]];
                        digits[rightMost[j]] = tmp;

                        return Integer.parseInt(new String(digits));
                    }
                }
            }

            return num;
        }
    }

    // revised on 12/5/2025
    class SolutionRevisonOnSeventhDay {
        public int maximumSwap(int num) {

            char[] digits = String.valueOf(num).toCharArray();

            int[] rightMost = new int[10];
            Arrays.fill(rightMost, -1);

            for (int i = 0; i < digits.length; i++) {
                rightMost[digits[i] - '0'] = i;
            }

            for (int i = 0; i < digits.length; i++) {
                for (int j = 9; j > digits[i] - '0'; j--) {

                    if (rightMost[j] > i) {
                        char temp = digits[j];
                        digits[i] = digits[rightMost[j]];
                        digits[rightMost[j]] = temp;

                        return Integer.parseInt(new String(digits));
                    }
                }
            }

            return num;
        }
    }

    // revised on 12/5/2025
    class SolutionRevisonOnFourteenDay {
        public int maximumSwap(int num) {

            char[] digits = String.valueOf(num).toCharArray();

            int[] rightMost = new int[10];
            Arrays.fill(rightMost, -1);

            for (int i = 0; i < digits.length; i++) {
                rightMost[digits[i] - '0'] = i;
            }

            for (int i = 0; i < digits.length; i++) {

                for (int j = 9; j > digits[i] - '0'; j--) {

                    if (rightMost[j] > i) {

                        char temp = digits[i];
                        digits[i] = digits[rightMost[j]];
                        digits[rightMost[j]] = temp;

                        return Integer.parseInt(new String(digits));
                    }
                }
            }

            return num;
        }
    }

    // revised on 1/17/2026
    class SolutionRevisedOnDayThirty {
        public int maximumSwap(int num) {

            char[] digits = String.valueOf(num).toCharArray();

            int[] rightMost = new int[10];
            Arrays.fill(rightMost, -1);

            for (int i = 0; i < digits.length; i++) {
                rightMost[digits[i] - '0'] = i;
            }

            for (int i = 0; i < digits.length; i++) {
                for (int j = 9; j > digits[i] - '0'; j--) {

                    if (rightMost[j] > i) {
                        char temp = digits[i];
                        digits[i] = digits[rightMost[j]];
                        digits[rightMost[j]] = temp;

                        return Integer.parseInt(new String(digits));
                    }
                }
            }

            return num;
        }
    }
}
