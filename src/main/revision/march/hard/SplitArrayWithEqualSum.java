package main.revision.march.hard;

public class SplitArrayWithEqualSum {

    // 25/04/2026
    public class Solution {
        public boolean splitArray(int[] nums) {

            int total = 0;

            for (int num : nums) {
                total += num;
            }

            if (total % 3 != 0) {
                return false;
            }

            int targetSum = total / 3;
            int partionFound = 0;
            int curSum = 0;

            for (int i = 0; i < nums.length; i++) {
                curSum += nums[i];

                if (curSum == targetSum * (partionFound + 1)) {
                    partionFound++;
                }
            }

            return partionFound >= 3;
        }

    }

    // 28/04/2026
    public class SolutionRevisedOnDayThird {
        public boolean splitArray(int[] nums) {

            int total = 0;
            for (int num : nums) {
                total += num;
            }

            if (total % 3 != 0) {
                return false;
            }

            int targetSum = total / 3;
            int partitionFound = 0;
            int curSum = 0;

            for (int num : nums) {
                curSum += num;

                if (curSum == targetSum * (partitionFound + 1)) {
                    partitionFound++;
                }
            }

            return partitionFound >= 3;
        }
    }

    // 04/05/2026
    public class SolutionRevisedOnDaySeventh {
        public boolean splitArray(int[] nums) {

            int total = 0;
            for (int num : nums) {
                total += num;
            }

            if (total % 3 != 0) {
                return false;
            }

            int targetSum = total / 3;
            int partitionFound = 0;
            int curSum = 0;

            for (int num : nums) {
                curSum += num;

                if (curSum == targetSum * (partitionFound + 1)) {
                    partitionFound++;
                }
            }

            return partitionFound >= 3;
        }
    }
}
