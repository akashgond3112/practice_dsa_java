package main.revision.october.meta.medium;

public class MaxConsecutiveOnesIII {

    /*
     * Time Complexity: O(n), kyunki har element ko ek baar traverse karte hain.
     * Space Complexity: O(1), kyunki koi extra space use nahi ho raha hai.
     */
    class Solution {
        public int longestOnes(int[] nums, int k) {

            int zeroCount = 0; // Zeroes ka count rakhne ke liye variable
            int start = 0; // Sliding window ka start pointer
            int maxOnes = 0; // Maximum consecutive ones ka count

            // Loop array ke har element ke liye
            for (int end = 0; end < nums.length; end++) {

                if (nums[end] == 0) { // Agar current element 0 hai
                    zeroCount++; // Zero count badhao
                }

                // Jab zeroCount k se zyada ho jaye, tab window ko shrink karo
                while (zeroCount > k) {
                    if (nums[start] == 0) { // Agar start pointer pe 0 hai
                        zeroCount--; // Zero count kam karo
                    }

                    start++; // Window ka start pointer aage badhao
                }

                // Max consecutive ones ka length update karo
                maxOnes = Math.max(maxOnes, end - start + 1);
            }

            return maxOnes; // Maximum length return karo
        }
    }

    // revision on 12/15/2025
    class SolutionRevisedOnThirdDay {
        public int longestOnes(int[] nums, int k) {
            int start = 0;
            int zeroCount = 0;
            int maxOnes = 0;

            for (int end = 0; end < nums.length; end++) {

                if (nums[end] == 0) {
                    zeroCount++;
                }

                if (zeroCount > k) {

                    if (nums[start] == 0) {
                        zeroCount--;
                    }
                    start++;
                }

                maxOnes = Math.max(maxOnes, (end - start) + 1);
            }

            return maxOnes;
        }
    }

    // revision on 12/21/2025
    class SolutionRevisedOnSeventhDay {
        public int longestOnes(int[] nums, int k) {

            int start = 0;
            int maxOnes = 0;
            int zeroCount = 0;

            for (int end = 0; end < nums.length; end++) {

                if (nums[end] == 0) {
                    zeroCount++;
                }

                while (zeroCount > k) {
                    if (start == 0) {
                        zeroCount--;
                    }
                    start++;
                }

                maxOnes = Math.max(maxOnes, end - start + 1);
            }

            return maxOnes;
        }
    }

    // revision on 12/21/2025
    class SolutionRevisedOnFourteenDay {
        public int longestOnes(int[] nums, int k) {

            int zerosCount = 0;
            int start = 0;
            int maxOnes = 0;

            for (int end = 0; end < nums.length; end++) {

                if (nums[end] == 0) {
                    zerosCount++;
                }

                while (zerosCount > k) {

                    if (nums[start] == 0) {
                        zerosCount--;
                    }
                    start++;
                }

                maxOnes = Math.max(maxOnes, end - start + 1);
            }

            return maxOnes;
        }
    }
}
