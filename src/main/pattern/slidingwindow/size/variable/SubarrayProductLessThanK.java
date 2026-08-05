/**
 * @author akash
 * @date Aug 05, 2026
 * @time 7:31:41 PM
 */
package main.pattern.slidingwindow.size.variable;

public class SubarrayProductLessThanK {
    class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {

            if (k <= 1)
                return 0;

            int count = 0;

            int prod = 1;
            int left = 0;

            for (int right = 0; right < nums.length; right++) {
                prod *= nums[right];

                while (prod >= k) {
                    prod /= nums[left];
                    left++;
                }

                count += right - left + 1;
            }

            return count;

        }
    }
}
