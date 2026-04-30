package main.revision.march.hard;

public class FirstMissingPositive {

    // 20/04/2026
    class Solution {
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
                if (nums[i] != i) {
                    return i + 1;
                }
            }

            return n + 1;
        }

        private void swap(int[] nums, int i, int correctIndex) {

            int tmp = nums[i];
            nums[i] = nums[correctIndex];
            nums[correctIndex] = tmp;
        }
    }

    // 23/04/2026
    class SolutionRevisedOnDayThird {
        public int firstMissingPositive(int[] nums) {

            int n = nums.length;
            int i = 0;

            while (i < n) {

                int correctIndex = nums[i] - 1;

                if (nums[i] > 0 && nums[i] <= n && nums[i] != correctIndex) {
                    // swap
                    int tmp = nums[i];
                    nums[i] = nums[correctIndex];
                    nums[correctIndex] = tmp;
                } else {
                    i++;
                }
            }

            for (i = 0; i < n; i++) {
                if (nums[i] != i) {
                    return i + 1;
                }
            }

            return n + 1;

        }
    }

    // 29/04/2026
    class SolutionRevisedOnDaySeventh {
        public int firstMissingPositive(int[] nums) {

            int n = nums.length;
            int i = 0;

            while (i < n) {
                int correctIndex = nums[i] - 1;

                if (nums[i] > 0 && nums[i] <= n && nums[i] != correctIndex) {
                    int tmp = nums[i];
                    nums[i] = nums[correctIndex];
                    nums[correctIndex] = tmp;
                }
            }

            for (i = 0; i < n; i++) {
                if (nums[i] != i) {
                    return i + 1;
                }
            }

            return n + 1;
        }
    }
}
