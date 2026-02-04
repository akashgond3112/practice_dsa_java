package main.revision.october.meta.hard;

public class SplitArrayWithEqualSum {

    public class Solution {
        public boolean splitArray(int[] nums) {
            // Step 1: Calculate total sum of the array
            // Pura array ka sum calculate karte hain
            int totalSum = 0;
            for (int num : nums) {
                totalSum += num;
            }

            // Step 2: Check if total sum is divisible by 3
            // Agar total sum 3 se divisible nahi hai, toh split possible nahi hoga
            if (totalSum % 3 != 0) {
                return false;
            }

            // Step 3: Calculate target sum for each partition
            // Har partition ka target sum nikalte hain
            int targetSum = totalSum / 3;
            int partitionsFound = 0;
            int currentSum = 0;

            // Step 4: Traverse the array and check for partitions
            // Array ko traverse karke partitions dhundhte hain
            for (int i = 0; i < nums.length; i++) {
                currentSum += nums[i];
                // Jab current sum target sum ke barabar ho jaye, ek partition mil gaya
                if (currentSum == targetSum * (partitionsFound + 1)) {
                    partitionsFound++;
                }
            }

            // Step 5: Check if at least 3 partitions are found
            // Agar 3 ya usse zyada partitions milte hain, toh return true
            return partitionsFound >= 3;
        }
    }

    // revised on 12/7/2025
    public class SolutionRevisonOnThirDay {
        public boolean splitArray(int[] nums) {
            int totalSum = 0;
            for (int num : nums) {
                totalSum += num;
            }

            if (totalSum % 3 != 0) {
                return false;
            }

            int targetSum = totalSum / 3;
            int partitionsFound = 0;
            int currentSum = 0;

            for (int i = 0; i < nums.length; i++) {
                currentSum += nums[i];
                if (currentSum == targetSum * (partitionsFound + 1)) {
                    partitionsFound++;
                }
            }

            return partitionsFound >= 3;
        }
    }

    // revised on 12/13/2025
    public class SolutionRevisonOnSeventhDay {
        public boolean splitArray(int[] nums) {

            int totalSum = 0;

            for (int num : nums) {
                totalSum += num;
            }

            if (totalSum % 3 != 0) {
                return false;
            }

            int targetSum = totalSum / 3;
            int partitionFound = 0;
            int currentSum = 0;

            for (int i = 0; i < nums.length; i++) {
                currentSum += nums[i];

                if (currentSum == targetSum * (partitionFound + 1)) {
                    partitionFound++;
                }
            }

            return partitionFound >= 3;
        }
    }

    // revised on 12/27/2025
    public class SolutionRevisonOnFourteenDay {
        public boolean splitArray(int[] nums) {

            int totalSum = 0;
            for (int num : nums) {
                totalSum += num;
            }

            if (totalSum % 3 != 0) {
                return false;
            }

            int targetSum = totalSum / 3;
            int partitionFound = 0;
            int currentSum = 0;

            for (int i = 0; i < nums.length; i++) {

                currentSum += nums[i];

                if (currentSum == targetSum * (partitionFound + 1)) {
                    partitionFound++;
                }
            }

            return partitionFound >= 3;
        }
    }

    // revised on 1/25/2026
    class SolutionRevisedOnDayThirty {
        public boolean splitArray(int[] nums) {

            int totalSum = 0;
            for (int num : nums) {
                totalSum += num;
            }

            if (totalSum % 3 != 0)
                return false;

            int targetSum = totalSum / 3;
            int partitionFound = 0;
            int currentSum = 0;

            for (int num : nums) {

                currentSum += num;

                if (currentSum == targetSum + (partitionFound + 1)) {
                    partitionFound++;
                }
            }

            return partitionFound >= 3;
        }
    }
}
