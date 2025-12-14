package main.revision.october.meta.easy;

public class FindPivotIndex {

    class Solution {
        // Time Complexity: O(n) - We iterate through the array twice: once to calculate
        // total sum, once to find the pivot.
        // Space Complexity: O(1) - We use only a few integer variables, no extra space
        // proportional to input size.

        public int pivotIndex(int[] nums) {
            // Step 1: Calculate the total sum of all elements in the array.
            // This will help us compute the right sum later by subtracting elements.
            int totalSum = 0;
            for (int num : nums)
                totalSum += num;

            // Step 2: Initialize leftSum to 0, which represents the sum of elements to the
            // left of the current index.
            int leftSum = 0;

            // Step 3: Loop through each index in the array to check if it's the pivot.
            for (int i = 0; i < nums.length; i++) {
                // Step 4: Subtract the current element from totalSum to get the sum of elements
                // to the right of i.
                // Now totalSum represents the right sum.
                totalSum -= nums[i];

                // Step 5: Check if the right sum equals the left sum. If yes, this is the pivot
                // index.
                if (totalSum == leftSum)
                    return i;

                // Step 6: Add the current element to leftSum for the next iteration.
                leftSum += nums[i];
            }

            // Step 7: If no pivot index is found, return -1.
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

            return -1;
        }
    }

    // revise on 12/14/2025
    class SolutionRevisionOnFourteenDay {
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

            return -1;

        }
    }
}
