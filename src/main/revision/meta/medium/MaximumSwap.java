/**
 * @author agond
 * @date Jul 17, 2025
 * @time 6:32:42 PM
 */
package main.revision.meta.medium;

import java.util.*;

public class MaximumSwap {
    public int maximumSwap(int num) {
        char[] digits = String.valueOf(num).toCharArray();

        // Store the rightmost index of each digit (0-9)
        int[] rightMost = new int[10];
        Arrays.fill(rightMost, -1);

        // Fill the rightmost positions
        for (int i = 0; i < digits.length; i++) {
            rightMost[digits[i] - '0'] = i;
        }

        // Try to find the first position where we can make a beneficial swap
        for (int i = 0; i < digits.length; i++) {
            // Look for a larger digit that appears later in the number
            for (int j = 9; j > digits[i] - '0'; j--) {
                // Check if this larger digit exists and appears after current position
                if (rightMost[j] > i) {
                    // Swap the digits
                    char temp = digits[i];
                    digits[i] = digits[rightMost[j]];
                    digits[rightMost[j]] = temp;

                    // Convert back to integer and return
                    return Integer.parseInt(new String(digits));
                }
            }
        }

        // No beneficial swap found
        return num;
    }
}
