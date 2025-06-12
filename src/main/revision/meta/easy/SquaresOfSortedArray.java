/**
 * @author agond
 * @date Jun 12, 2025
 * @time 6:53:25 PM
 */
package main.revision.meta.easy;

public class SquaresOfSortedArray {

    public int[] sortedSquares(int[] nums) {

        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }

        int left = 0;
        int right = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {

            if (nums[left] > nums[right]) {
                result[i] = nums[left];
                left++;
            } else {
                result[i] = nums[right];
                right--;
            }
        }

        return result;
    }

}
