/**
 * @author agond
 * @date Jun 03, 2025
 * @time 8:41:04 PM
 */
package main.revision.meta.medium;

import java.util.*;

public class RandomPickWithWeight {

    private final int[] prefixSum;

    private final int totalWeight;
    private final Random rnd;

    public RandomPickWithWeight(int[] w) {

        prefixSum = new int[w.length];
        prefixSum[0] = w[0];

        for (int i = 1; i < w.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i];
        }

        totalWeight = prefixSum[w.length - 1];
        rnd = new Random();
    }

    public int pickIndex() {

        int target = rnd.nextInt(totalWeight);

        int left = 0;
        int right = prefixSum.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (prefixSum[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
