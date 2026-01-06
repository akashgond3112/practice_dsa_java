package main.revision.october.meta.easy;

public class MaximumAverageSubarrayI {

    // Sliding window approach se maximum average subarray find karenge
    class Solution {
        public double findMaxAverage(int[] nums, int k) {

            int sum = 0;
            // Pehle k elements ka sum nikal lo
            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }

            int maxSum = sum; // Abhi tak ka maximum sum store karenge

            int start = 0;
            int end = k;

            // Window ko slide karte hue array traverse karo
            while (end < nums.length) {

                sum -= nums[start]; // Window ka pehla element minus karo
                start++; // Start pointer aage badhao

                sum += nums[end]; // Window ke end pe naya element add karo
                end++; // End pointer aage badhao

                maxSum = Math.max(maxSum, sum); // Maximum sum update karo agar naya sum bada ho
            }
            // Maximum sum ko k se divide karke average return karo
            return (double) maxSum / k;

        }

    }

    // revised on 12/17/2025
    class SolutionRevisedOnThirdDay {
        public double findMaxAverage(int[] nums, int k) {

            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }

            int maxSum = sum;

            int start = 0;
            int end = k;

            while (end < nums.length) {

                sum -= nums[start];
                start++;

                sum += nums[end];
                end++;

                maxSum = Math.max(maxSum, sum);
            }

            return (double) maxSum / k;
        }
    }

    // revised on 12/23/2025
    class SolutionRevisedOnSeventhDay {
        public double findMaxAverage(int[] nums, int k) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            int maxSum = sum;
            int start = 0;
            int end = k;

            while (end <= nums.length) {

                sum -= nums[start];
                start++;

                sum += nums[end];
                end--;

                maxSum = Math.max(sum, maxSum);
            }

            return (double) maxSum / k;
        }
    }

    // revised on 1/6/2026
    class SolutionRevisedOnFourteenDay {
        public double findMaxAverage(int[] nums, int k) {

            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }

            int maxSum = sum;

            int start = 0;
            int end = k;

            while (end < nums.length) {

                sum -= nums[start];
                start++;

                sum += nums[end];
                end--;

                maxSum = Math.max(maxSum, sum);
            }

            return (double) maxSum / k;
        }
    }
}
