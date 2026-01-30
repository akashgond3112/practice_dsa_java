package main.revision.october.meta.hard;

public class SplitArrayLargestSum {

    /**
     * Solution class to solve the "Split Array Largest Sum" problem.
     * <p>
     * Given an array of non-negative integers and an integer k, the goal is to
     * split the array into k or fewer non-empty subarrays
     * such that the largest sum among these subarrays is minimized.
     * <p>
     * The approach uses binary search to efficiently find the minimal possible
     * largest sum.
     * The helper method {@code isPossible} checks if it is feasible to split the
     * array into at most k subarrays
     * with each subarray sum not exceeding a given value.
     *
     * Algorithm Explanation:
     * - The minimum possible largest sum is the maximum element in the array (since
     * every subarray must contain at least one element).
     * - The maximum possible largest sum is the sum of all elements (when the array
     * is not split).
     * - Binary search is performed between these two bounds to find the minimal
     * largest sum for which splitting is possible.
     * - For each candidate sum, {@code isPossible} checks if the array can be split
     * into at most k subarrays with each subarray sum ≤ candidate.
     *
     * Time Complexity: O(N * log(S)), where N is the length of the array and S is
     * the sum of all elements in the array.
     * - The binary search runs in O(log(S)) iterations.
     * - Each call to {@code isPossible} takes O(N) time.
     *
     * Space Complexity: O(1), as only a constant amount of extra space is used.
     */
    class Solution {

        // Helper method to check if it's possible to split the array into k subarrays
        // with each subarray sum <= mid
        private boolean isPossible(int[] nums, int mid, int k) {
            int count = 1; // Subarray count shuru mein 1 se start hota hai
            int tempSum = 0; // Temporary sum har subarray ka

            for (int num : nums) {
                // Agar koi element mid se bada hai, toh split karna impossible hai
                if (num > mid) {
                    return false;
                }
                // Agar current element ko add karne se sum mid se chhota ya barabar hai
                if (tempSum + num <= mid) {
                    tempSum += num; // Current element ko add karte hain
                } else {
                    count++; // Naya subarray shuru karte hain
                    tempSum = num; // Naye subarray ka sum current element se start hota hai
                }
            }
            // Agar subarray count k se kam ya barabar hai, toh possible hai
            return count <= k;
        }

        // Main method to find the minimum largest sum
        public int splitArray(int[] nums, int k) {
            int low = 0; // Minimum possible largest sum
            int high = 0; // Maximum possible largest sum

            // Array ke elements ka maximum aur total sum calculate karte hain
            for (int num : nums) {
                low = Math.max(low, num); // Maximum element ko low banate hain
                high += num; // Total sum ko high banate hain
            }

            int ans = high; // Answer ko initially high set karte hain
            while (low <= high) {
                int mid = low + (high - low) / 2; // Mid calculate karte hain

                // Agar mid ke saath split karna possible hai
                if (isPossible(nums, mid, k)) {
                    ans = mid; // Mid ko answer banate hain
                    high = mid - 1; // High ko mid se ek kam karte hain
                } else {
                    low = mid + 1; // Low ko mid se ek zyada karte hain
                }
            }
            return ans; // Minimum largest sum return karte hain
        }
    }

    // Revised on 04/11/2025
    class SolutionRevisionThirdDay {

        private boolean isPossible(int[] nums, int mid, int k) {

            int count = 0;
            int tempSum = 0;

            for (int num : nums) {

                if (num > mid) {
                    return false;
                }

                if (tempSum + num <= mid) {
                    tempSum += num;
                } else {
                    count++;
                    tempSum = num;
                }
            }

            return count <= k;
        }

        public int splitArray(int[] nums, int k) {

            int low = 0;
            int high = 0;

            for (int num : nums) {
                low = Math.min(low, num);
                high += num;
            }

            int ans = high;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (isPossible(nums, mid, k)) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return ans;
        }
    }

    // Revised on 11/10/2025
    class SolutionRevisionSeventhDay {

        private boolean isPossible(int[] nums, int mid, int k) {

            int count = 0;
            int tempSum = 0;

            for (int num : nums) {

                if (num > mid) {
                    return false;
                }

                if (tempSum + num <= mid) {
                    tempSum += num;
                } else {
                    count++;
                    tempSum = num;
                }
            }

            return count <= k;
        }

        public int splitArray(int[] nums, int k) {

            int low = 0;
            int high = 0;

            for (int num : nums) {
                low = Math.min(low, num);
                high += num;
            }

            int ans = high;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (isPossible(nums, mid, k)) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return ans;
        }
    }

    // Revised on 11/24/2025
    class SolutionRevisionFourteenDay {

        private boolean isPossible(int[] nums, int mid, int k) {

            int count = 0;
            int tempSum = 0;

            for (int num : nums) {

                if (num > mid) {
                    return false;
                }

                if (tempSum + num <= mid) {
                    tempSum += num;
                } else {
                    count++;
                    tempSum += num;
                }
            }

            return count <= k;
        }

        public int splitArray(int[] nums, int k) {
            int low = 0;
            int high = 0;

            for (int num : nums) {
                low = Math.min(low, num);
                high += num;
            }

            int ans = high;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (isPossible(nums, mid, k)) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return ans;
        }

    }

    // Revised on 12/23/2025
    class SolutionRevisionThirtyDay {

        private boolean isPossible(int[] nums, int mid, int k) {
            int count = 1;
            int tempSum = 0;

            for (int num : nums) {
                if (num > mid) {
                    return false;
                }

                if (tempSum + num <= mid) {
                    tempSum += num;
                } else {
                    count++;
                    tempSum = num;
                }
            }

            return count <= k;

        }

        public int splitArray(int[] nums, int k) {
            int low = 0;
            int high = 0;

            for (int num : nums) {
                high += num;
                low = Math.max(low, num);
            }

            int ans = high;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (isPossible(nums, mid, k)) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return ans;
        }
    }
}
