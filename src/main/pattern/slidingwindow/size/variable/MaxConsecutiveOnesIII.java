/**
 * @author akash
 * @date Aug 06, 2026
 * @time 6:41:26 PM
 */
package main.pattern.slidingwindow.size.variable;

public class MaxConsecutiveOnesIII {
    class Solution {
        public int longestOnes(int[] nums, int k) {

            int left = 0;
            int max = 0;
            int zeroes = 0;
            for (int right = 0; right < nums.length; right++) {

                if (nums[right] == 0) {
                    zeroes++;
                }

                while (zeroes > k) {
                    if (nums[left] == 0) {
                        zeroes--;
                    }
                    left++;
                }

                max = Math.max(max, right - left + 1);
            }

            return max;
        }
    }
}
