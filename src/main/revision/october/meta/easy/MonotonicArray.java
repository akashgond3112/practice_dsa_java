package main.revision.october.meta.easy;

public class MonotonicArray {
    class Solution {
        public boolean isMonotonic(int[] nums) {

            // Step 1: Initialize two flags - isDec (for decreasing) and isInc (for
            // increasing)
            boolean isDec = true;
            boolean isInc = true;

            // Step 2: Traverse the array from the second element to the end
            for (int i = 1; i < nums.length; i++) {

                // Step 3: Check if the current element is greater than the previous one
                // Agar bada hai, to decreasing nahi ho sakta
                if (nums[i] > nums[i - 1]) {
                    isDec = false;
                }

                // Step 4: Check if the current element is smaller than the previous one
                // Agar chhota hai, to increasing nahi ho sakta
                if (nums[i] < nums[i - 1]) {
                    isInc = false;
                }

                // Step 5: If neither increasing nor decreasing, return false
                // Agar dono false ho gaye, to monotonic nahi hai
                if (!isDec && !isInc)
                    return false;
            }

            // Step 6: If the loop completes, the array is monotonic
            // Agar loop complete ho gaya, to monotonic hai
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

    // revised on 12/6/2025
    class SolutionRevisedOnSeventhDay {
        public boolean isMonotonic(int[] nums) {
            boolean isInc = true;
            boolean isDec = true;

            for (int i = 1; i < nums.length; i++) {

                if (nums[i] > nums[i - 1]) {
                    isDec = false;
                }

                if (nums[i] < nums[i - 1]) {
                    isInc = false;
                }

                if (!isDec && !isInc) {
                    return false;
                }
            }

            return true;
        }
    }

    // revised on 12/20/2025
    class SolutionRevisedOnFourteenDay {
        public boolean isMonotonic(int[] nums) {
            boolean isInc = true;
            boolean isDec = true;

            for (int i = 1; i < nums.length; i++) {

                if (nums[i] > nums[i - 1]) {
                    isDec = false;
                }

                if (nums[i] < nums[i - 1]) {
                    isInc = false;
                }

                if (!isDec && !isInc) {
                    return false;
                }
            }

            return true;
        }
    }

    // revised on 1/17/2026
    class SolutionRevisedOnDayThirty {
        public boolean isMonotonic(int[] nums) {
            boolean isInc = true;
            boolean isDec = true;

            for (int i = 1; i < nums.length; i++) {

                if (nums[i] > nums[i - 1]) {
                    isDec = false;
                }

                if (nums[i] < nums[i - 1]) {
                    isInc = false;
                }

                if (!isDec && !isInc) {
                    return false;
                }
            }

            return true;
        }
    }

}
