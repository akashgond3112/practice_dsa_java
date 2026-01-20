package main.revision.october.meta.hard;

public class FirstMissingPositive {

    private void swap(int[] nums, int i, int correctIndex) {
        // Swap karte hain nums[i] aur nums[correctIndex] ko
        int temp = nums[i];
        nums[i] = nums[correctIndex];
        nums[correctIndex] = temp;
    }

    class Solution {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            int i = 0;

            // Step 1: Array ko cyclic sort karte hain
            while (i < n) {
                int correctIndex = nums[i] - 1;

                // Agar nums[i] valid range mein hai aur correct position par nahi hai, to swap
                // karte hain
                if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[correctIndex]) {
                    swap(nums, i, correctIndex);
                } else {
                    // Agar condition satisfy nahi hoti, to next index par move karte hain
                    i++;
                }
            }

            // Step 2: Sorted array ko traverse karke missing positive number find karte
            // hain
            for (i = 0; i < n; i++) {
                // Agar nums[i] correct position par nahi hai, to missing number return karte
                // hain
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }

            // Step 3: Agar sabhi numbers correct position par hain, to n+1 return karte
            // hain
            return n + 1;
        }
    }

    class SolutionRevisionOnThirdDay {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            int i = 0;

            while (i < n) {

                int corrcectIndex = nums[i] - 1;

                if (nums[i] > 0 && nums[i] < n && nums[i] != nums[corrcectIndex]) {
                    swap(nums, i, corrcectIndex);
                } else {
                    i++;
                }
            }

            for (i = 0; i < n; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }

            return n + 1;
        }
    }

    // revised on 12/5/2025
    class SolutionRevisionOnSeventhDay {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            int i = 0;

            while (i < n) {
                int correctIndex = nums[i] - 1;

                if (nums[i] > 0 && nums[i] < n && nums[i] != nums[correctIndex]) {
                    swap(nums, i, correctIndex);
                } else {
                    i++;
                }
            }

            for (i = 0; i < n; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }

            return n + 1;
        }
    }

    // revised on 12/19/2025
    class SolutionRevisionOnFourteenDay {
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
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }

            return n + 1;
        }
    }

    // revised on 1/17/2026
    class SolutionRevisedOnDayThirty {
        public int firstMissingPositive(int[] nums) {

            int n = nums.length;
            int i = 0;

            // Step 1: Place each number in its correct spot if possible (Cyclic Sort)
            while (i < n) {
                int correctIndex = nums[i] - 1;
                // The number should be in the range [1, n] to be placed.
                // Also, it should not already be in its correct place.
                if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[correctIndex]) {
                    swap(nums, i, correctIndex);
                } else {
                    i++;
                }
            }

            // Step 2: Find the first number that is not in its correct place.
            // You can use a while loop here as well.
            i = 0; // Reset index to start from the beginning of the array
            while (i < n) {
                // The correct number at index i is i + 1.
                if (nums[i] != i + 1) {
                    return i + 1;
                }
                i++;
            }

            // Step 3: If all numbers from 1 to n are present, the missing one is n + 1.
            return n + 1;
        }
    }
}
