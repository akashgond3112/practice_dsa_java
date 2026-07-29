/**
 * @author akash
 * @date Jul 29, 2026
 * @time 4:50:59 PM
 */
package main.pattern.prefixsum;

public class NumberOfWaysToSplitArray {
    class Solution {
        public int waysToSplitArray(int[] nums) {

            int totalSum = 0;
            for (int num : nums) {
                totalSum += num;
            }

            int split = 0;
            int leftSum = 0;
            for (int i = 0; i < nums.length; i++) {
                leftSum += nums[i];
                if (leftSum >= (totalSum - leftSum)) {
                    split++;
                }
            }

            return split;

        }
    }
}
