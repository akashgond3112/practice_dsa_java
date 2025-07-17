/**
 * @author agond
 * @date Jul 17, 2025
 * @time 5:28:46 PM
 */
package main.revision.meta.easy;

public class FindPivotIndex {

    public int pivotIndex(int[] nums) {

        int rightSum = 0;
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            rightSum += nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            rightSum -= val;

            if (leftSum == rightSum) {
                return i;
            }
            leftSum = val;
        }

        return -1;
    }
}
