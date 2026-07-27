/**
 * @author akash
 * @date Jul 27, 2026
 * @time 8:09:33 PM
 */
package main.pattern.prefixsum;

public class FindTheMiddleIndexInArray {
    class Solution {
        public int findMiddleIndex(int[] nums) {
            int totalSum = 0;
            for (int num : nums) {
                totalSum += num;
            }

            int leftSum = 0;
            for (int i = 0; i < nums.length; i++) {
                int rightSum = totalSum - leftSum - nums[i];

                if (leftSum == rightSum) {
                    return i;
                }

                leftSum += nums[i];
            }

            return -1;
        }
    }
}
