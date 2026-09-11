/**
 * Utility for counting ordered index pairs (i, j) in an integer array that satisfy:
 *     arr[i] - arr[j] == i - j
 *
 * Rationale:
 * By rearranging the equation we get arr[i] - i == arr[j] - j. Thus indices can be grouped
 * by the value diff = arr[k] - k. If a group has frequency k, it contributes k * k ordered pairs
 * (including pairs where i == j).
 *
 * Behavior:
 * - Null or empty input returns 0.
 * - Pairs are ordered: (i, j) and (j, i) are counted separately when i != j.
 * - Self-pairs (i == j) are included.
 *
 * Complexity:
 * - Time: O(n) — single pass to compute diffs and build frequency map, plus iteration over map values.
 * - Space: O(n) in the worst case (distinct diffs for all indices).
 *
 * Example:
 * Input: [2, 4, 6, 5, 9, 9, 11]
 * Diffs: [2, 3, 4, 2, 5, 4, 5] -> freqs {2:2, 3:1, 4:2, 5:2}
 * Result: 2^2 + 1^2 + 2^2 + 2^2 = 13
 */

/**
 * Count ordered pairs (i, j) such that arr[i] - arr[j] == i - j.
 *
 * @param arr the input integer array; may be null
 * @return the number of ordered pairs (as a long). Returns 0 for null or empty arrays.
 *
 * Notes:
 * - Implementation groups indices by the value (arr[i] - i) and sums the square of each group's frequency.
 * - Uses a HashMap<Integer, Long> to store frequencies to avoid integer overflow for large counts.
 */
package main.interview.google.leetCodeDiscussion;

import java.util.HashMap;
import java.util.Map;

public class ProblemOne {

    /**
     * Count ordered pairs (i, j) such that arr[i] - arr[j] == i - j.
     *
     * Rearranging the condition gives arr[i] - i == arr[j] - j. So indices can be
     * grouped
     * by the value diff = arr[k] - k. If a diff appears k times, it contributes k *
     * k pairs.
     *
     * @param arr integer array; may be null
     * @return number of ordered pairs as a long; returns 0 for null or empty arrays
     */
    public static long countPairs(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0L;
        }

        Map<Integer, Long> frequencyMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int diff = arr[i] - i;
            frequencyMap.put(diff, frequencyMap.getOrDefault(diff, 0L) + 1L);
        }

        long totalPairs = 0L;
        for (long count : frequencyMap.values()) {
            totalPairs += count * count;
        }

        return totalPairs;
    }

    public static void main(String[] args) {
        int[] input1 = { 2, 4, 6, 5, 9, 9, 11 };
        System.out.println("Output 1: " + countPairs(input1)); // Expected: 13

        int[] input2 = { 1, 2, 3 };
        System.out.println("Output 2: " + countPairs(input2)); // Expected: 9

        int[] input3 = { 0, 0, 0 };
        System.out.println("Output 3: " + countPairs(input3)); // Expected: 3
    }
}
