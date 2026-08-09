/**
 * @author akash
 * @date Aug 09, 2026
 * @time 6:37:26 PM
 */
package main.pattern.twopointers;

public class SortColors {
    static class Solution {
        public void sortColors(int[] nums) {
            if (nums == null || nums.length <= 1)
                return;

            int low = 0;
            int mid = 0;
            int high = nums.length - 1;

            while (mid <= high) {
                if (nums[mid] == 0) {
                    int tmp = nums[low];
                    nums[low] = nums[mid];
                    nums[mid] = tmp;
                    low++;
                    mid++;
                } else if (nums[mid] == 1) {
                    mid++;
                } else {
                    int tmp = nums[mid];
                    nums[mid] = nums[high];
                    nums[high] = tmp;
                    high--;
                }
            }
        }
    }
}
