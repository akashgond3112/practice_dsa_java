package main.revision.october.meta.easy;

public class MonotonicArray {
    class Solution {
        public boolean isMonotonic(int[] nums) {

            boolean isDec = true;
            boolean isInc = true;

            for (int i = 1; i < nums.length; i++) {

                if (nums[i] > nums[i - 1]) {
                    isDec = false;
                }

                if (nums[i] < nums[i - 1]) {
                    isInc = false;
                }

                if (!isDec && !isInc)
                    return false;
            }

            return true;

        }
    }

    // revised on 11/30/2025
    class SolutionRevisedOnThirdDay {
        public boolean isMonotonic(int[] nums) {
            boolean isDec = true;
            boolean isInc = true;

            for (int i = 1; i < nums.length; i++) {

                if (nums[i] > nums[i - 1]) {
                    isDec = false;
                }

                if (nums[i] < nums[i - 1]) {
                    isInc = false;
                }

                if (!isDec && !isInc)
                    return false;
            }

            return true;
        }
    }
}
