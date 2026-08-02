/**
 * @author akash
 * @date Jul 30, 2026
 * @time 7:13:56 PM
 */
package main.pattern.slidingwindow.size.fixed;

public class NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold {
    class Solution {
        public int numOfSubarrays(int[] nums, int k, int threshold) {
            int count = 0;
            int currentSum = 0;
            int left = 0;

            for (int right = 0; right < nums.length; right++) {
                currentSum += nums[right];

                if (right - left + 1 > k) {
                    currentSum -= nums[left];
                    left++;
                }

                if (right - left + 1 == k && currentSum >= threshold * k) {
                    count++;
                }
            }

            return count;
        }
    }
}
