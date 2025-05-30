/**
 * @author agond
 * @date May 30, 2025
 * @time 8:37:36 PM
 */
package main.revision.meta.medium;

public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int l) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum = sum + nums[j];
                if (sum == l) {
                    count++;
                }
            }
        }

        return count;
    }
}
