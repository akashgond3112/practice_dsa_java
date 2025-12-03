package main.revision.october.meta.easy;

public class FindPivotIndex {

    class Solution {
        public int pivotIndex(int[] nums) {

            int totalSum = 0;

            for (int num : nums)
                totalSum += num;

            int leftSum = 0;
            for (int i = 0; i < nums.length; i++) {

                totalSum -= nums[i];
                if (totalSum == leftSum)
                    return i;

                leftSum += nums[i];
            }

            return -1;
        }
    }

    // revise on 11/24/2025
    class SolutionRevisionOnThirdDay {

        public int pivotIndex(int[] nums) {

            int totalSum = 0;

            for (int num : nums)
                totalSum += num;

            int leftSum = 0;
            for (int i = 0; i < nums.length; i++) {

                totalSum -= nums[i];
                if (totalSum == leftSum)
                    return i;

                leftSum += nums[i];
            }

            return -1;
        }
    }

    // revise on 11/30/2025
    class SolutionRevisionOnSeventhDay {
        public int pivotIndex(int[] nums) {
            int totalSum = 0;

            for (int num : nums) {
                totalSum += num;
            }

            int leftSum = 0;
            for (int i = 0; i < nums.length; i++) {
                totalSum -= nums[i];

                if (totalSum == leftSum) {
                    return i;
                }
                leftSum += nums[i];
            }

            return totalSum;
        }
    }
}
