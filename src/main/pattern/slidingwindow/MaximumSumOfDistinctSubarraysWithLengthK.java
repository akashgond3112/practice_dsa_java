/**
 * @author akash
 * @date Jul 30, 2026
 * @time 6:50:26 PM
 */
package main.pattern.slidingwindow;

import java.util.*;

public class MaximumSumOfDistinctSubarraysWithLengthK {

    class Solution {
        public long maximumSubarraySum(int[] nums, int k) {

            Map<Integer, Integer> map = new HashMap<>();
            long maxSum = 0;
            long currentSum = 0;
            int left = 0;

            for (int right = 0; right < nums.length; right++) {

                // Add current element to the window
                currentSum += nums[right];
                map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

                // Shrink window if it exceeds size k or has duplicates
                while (right - left + 1 > k || map.get(nums[right]) > 1) {
                    currentSum -= nums[left];
                    map.put(nums[left], map.get(nums[left]) - 1);
                    if (map.get(nums[left]) == 0) {
                        map.remove(nums[left]);
                    }
                    left++;
                }

                // Update max if window has exactly k distinct elements
                if (right - left + 1 == k) {
                    maxSum = Math.max(maxSum, currentSum);
                }
            }

            return maxSum;

        }
    }

}
