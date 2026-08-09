/**
 * @author akash
 * @date Aug 09, 2026
 * @time 6:22:52 PM
 */
package main.pattern.twopointers;

public class TwoSumIIInputArrayIsSorted {
    class Solution {
        public int[] twoSum(int[] nums, int target) {

            int i = 0;
            int j = nums.length - 1;

            while (i < j) {

                if (nums[i] + nums[j] == target) {
                    return new int[] { i + 1, j + 1 };
                }

                if (nums[i] + nums[j] > target) {
                    j--;
                } else {
                    i++;
                }
            }

            return new int[] {};
        }
    }
}
