package main.revision.march.hard;

public class SplitArrayLargestSum {

    class Solution {
        public int splitArray(int[] nums, int k) {

            int low = 0;
            int high = 0;
            int ans = high;

            for (int num : nums) {
                low = Math.min(low, num);
                high += num;
            }

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (isPossible(nums, k, mid)) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return ans;
        }

        private boolean isPossible(int[] nums, int k, int mid) {
            int count = 0;
            int sum = 0;

            for (int num : nums) {

                if (sum + num <= mid) {
                    sum += num;
                } else {
                    count++;
                    sum = num;
                }
            }

            return count <= k;
        }
    }
}
