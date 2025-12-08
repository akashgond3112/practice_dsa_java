package main.revision.october.meta.medium;

public class CuttingRibbons {

    public static class Solution {

        public static int getMaxLength(int[] nums, int k) {

            int maxLength = 0;
            for (int num : nums) {
                maxLength += num;
            }

            int left = 1;
            int right = maxLength;

            while (left <= right) {

                int mid = left + (right - left) / 2;

                if (cutRibbons(nums, k, mid)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return right;
        }

        private static boolean cutRibbons(int[] nums, int k, int mid) {
            int count = 0;

            for (int num : nums) {
                count += num / mid;
            }

            return count >= k;
        }
    }

    // revision on 12/8/2025
    public static class SolutionRevisedOnThirdDay {

        public static int getMaxLength(int[] nums, int k) {

            int maxLength = 0;
            for (int num : nums) {
                maxLength += num;
            }

            int left = 1;
            int right = maxLength;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (cutRibbons(nums, k, mid)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return right;
        }

        private static boolean cutRibbons(int[] nums, int k, int mid) {

            int count = 0;
            for (int num : nums) {
                count += num / mid;
            }

            return count >= k;
        }
    }
}
