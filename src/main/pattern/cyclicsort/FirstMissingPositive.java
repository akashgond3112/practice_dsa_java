/**
 * @author akash
 * @date Aug 17, 2026
 * @time 6:36:05 PM
 */
package main.pattern.cyclicsort;

public class FirstMissingPositive {
    class Solution {
        private void swap(int[] nums, int i, int correctIndex) {
            // Swap karte hain nums[i] aur nums[correctIndex] ko
            int temp = nums[i];
            nums[i] = nums[correctIndex];
            nums[correctIndex] = temp;
        }

        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            int i = 0;

            while (i < n) {

                int correctIndex = nums[i] - 1;
                if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[correctIndex]) {
                    swap(nums, i, correctIndex);
                } else {
                    i++;
                }
            }

            for (i = 0; i < n; i++) {
                // The correct number at index i is i + 1.
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }

            return n + 1;
        }
    }
}
