package main.revision.october.meta.easy;

public class MoveZeroes {

    class Solution {
        public void moveZeroes(int[] nums) {
            int left = 0;

            for (int right = 0; right < nums.length; right++) {
                if (nums[right] != 0) {
                    if (left != right) {
                        int temp = nums[right];
                        nums[right] = nums[left];
                        nums[left] = temp;
                    }
                    left++;
                }
            }
        }
    }

    // revised on 03/11/2025
    class SolutionRevisionThirdDay {
        public void moveZeroes(int[] nums) {
            int left = 0;

            for (int right = 0; right < nums.length; right++) {
                if (nums[right] != 0) {
                    if (left != right) {
                        int temp = nums[right];
                        nums[right] = nums[left];
                        nums[left] = temp;
                    }
                    left++;
                }
            }
        }
    }

    // revised on 11/9/2025
    class SolutionRevisionSeventhDay {
        public void moveZeroes(int[] nums) {

            int left = 0;
            for (int right = 0; right < nums.length; right++) {

                if (nums[right] != 0) {
                    if (left != right) {
                        int temp = nums[right];
                        nums[right] = nums[left];
                        nums[left] = temp;
                    }
                    left++;
                }
            }
        }
    }

    // revised on 11/23/2025
    class SolutionRevisionFourteenDay {
        public void moveZeroes(int[] nums) {

            int left = 0;

            for (int right = 0; right < nums.length; right++) {

                if (left != right) {
                    int temp = nums[right];
                    nums[right] = nums[left];
                    nums[left] = temp;
                }
                left++;
            }
        }
    }
}
