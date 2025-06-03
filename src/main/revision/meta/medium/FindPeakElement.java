/**
 * @author agond
 * @date Jun 03, 2025
 * @time 8:05:17 PM
 */
package main.revision.meta.medium;

public class FindPeakElement {

    public int findPeakElement(int[] nums) {

        if (nums.length == 1) {
            return 0;
        } else if (nums[0] > nums[1]) {
            return 0;
        } else if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }

        int low = 1;
        int high = nums.length - 2;

        while (low <= high) {
            int mid = (low + high) / 2;

            // middle should always be grater than both the side.
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] > nums[mid - 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

}
