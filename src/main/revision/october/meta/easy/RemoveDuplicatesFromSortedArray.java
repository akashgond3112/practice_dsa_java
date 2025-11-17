package main.revision.october.meta.easy;

public class RemoveDuplicatesFromSortedArray {
    class Solution {
        public int removeDuplicates(int[] nums) {

            int i = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] != nums[j]) {
                    i++;
                    nums[i] = nums[j];
                }
            }

            return i + 1;
        }
    }

    // revised on 11/9/2025 and 11/15/2025
    class SolutionRevisionThirdAndSeventhDay {
        public int removeDuplicates(int[] nums) {
            int i = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] != nums[j]) {
                    i++;
                    nums[i] = nums[j];
                }
            }

            return i + 1;
        }
    }
}
