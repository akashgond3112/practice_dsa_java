package main.revision.october.meta.medium;

public class FindPeakElement {
    static class Solution {
        public int findPeakElement(int[] nums) {

            if (nums == null || nums.length == 0) {
                return -1;
            }

            if (nums.length == 1) {
                return 0;
            }

            if (nums[0] > nums[1])
                return 0;

            if (nums[nums.length - 1] > nums[nums.length - 2])
                return nums.length - 1;

            int left = 1;
            int right = nums.length - 2;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                    return mid;
                }
                if (nums[mid] < nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }
    }

    // revised on 29/10/2025
    static class SolutionRevisionThirdDay {
        /**
         * Finds a peak element in the given array. A peak element is an element that is
         * greater than its neighbors.
         *
         * @param nums the input array of integers
         * @return the index of a peak element, or -1 if the array is null or empty
         */
        public int findPeakElement(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1; // Or throw an exception, depending on contract.
            }

            int left = 0;
            int right = nums.length - 1;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < nums[mid + 1]) {
                    // The peak is in the right half (including mid+1)
                    left = mid + 1;
                } else {
                    // The peak is in the left half (including mid)
                    right = mid;
                }
            }
            // When the loop terminates, left == right, pointing to a peak element.
            return left;
        }
    }

    // revised on 04/11/2025
    static class SolutionRevisionSeventhDay {

        public int findPeakElement(int[] nums) {

            if (nums == null || nums.length == 0) {
                return -1;
            }

            int left = 0;
            int right = nums.length - 1;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (nums[mid] < nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }

    // revised on 04/11/2025
    static class SolutionRevisionFourteenDay {

        public int findPeakElement(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int left = 0;
            int right = nums.length - 1;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (nums[mid] < nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }
}
