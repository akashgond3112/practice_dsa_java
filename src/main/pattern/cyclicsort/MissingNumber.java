/**
 * @author akash
 * @date Aug 16, 2026
 * @time 7:27:09 AM
 */
package main.pattern.cyclicsort;

public class MissingNumber {
    class Solution {
        public int missingNumber(int[] nums) {
            int n = nums.length;
            int expectedSum = n * (n + 1) / 2;
            int actualSum = 0;
            for (int num : nums) {
                actualSum += num;
            }
            return expectedSum - actualSum;
        }
    }
}
