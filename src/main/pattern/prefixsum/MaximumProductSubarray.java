/**
 * @author akash
 * @date Jul 28, 2026
 * @time 5:38:29 PM
 */
package main.pattern.prefixsum;

public class MaximumProductSubarray {

    class Solution {
        public int maxProduct(int[] nums) {
            int n = nums.length;
            int max = nums[0];
            int prefix = 0;
            int suffix = 0;

            for (int i = 0; i < n; i++) {
                prefix = nums[i] * (prefix == 0 ? 1 : prefix);
                suffix = nums[n - 1 - i] * (suffix == 0 ? 1 : suffix);
                max = Math.max(max, Math.max(prefix, suffix));
            }
            return max;
        }
    }
}
