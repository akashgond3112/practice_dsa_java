package main.pattern.cyclicsort;

/**
 * @author akash
 * @date Aug 17, 2026
 * @time 6:35:40 PM
 */
public class SetMismatch {
    class Solution {
        public int[] findErrorNums(int[] nums) {
            int n = nums.length;
            int[] freq = new int[n + 1]; // 1..n

            // count occurrences
            for (int x : nums) {
                freq[x]++;
            }

            int dup = -1, missing = -1;

            // find duplicate and missing
            for (int i = 1; i <= n; i++) {
                if (freq[i] == 2) {
                    dup = i;
                } else if (freq[i] == 0) {
                    missing = i;
                }
            }

            return new int[] { dup, missing };
        }
    }
}
