package main.revision.october.meta.medium;

import java.util.*;

public class FindKClosestElements {

    static class Solution {
        /**
         * Finds the k closest elements to a given value x in a sorted array. Optimized
         * solution using binary search to
         * find the starting point of the k-length window directly.
         *
         * @param arr
         *            The sorted input array
         * @param k
         *            Number of closest elements to find
         * @param x
         *            Target value
         * @return List of k closest elements, sorted in ascending order
         *
         *         Time Complexity: O(log(n-k) + k) Space Complexity: O(k)
         */
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            // Binary search ka use karke k-length window ka starting index dhundhna hai
            int left = 0;
            int right = arr.length - k;

            while (left < right) {
                // Mid calculate karte hain
                int mid = left + (right - left) / 2;

                // X se distance compare karte hain window ke boundaries ke elements ka
                if (x - arr[mid] > arr[mid + k] - x) {
                    // Agar right side ka distance kam hai, to window ko right shift karte hain
                    left = mid + 1;
                } else {
                    // Agar left side ka distance kam ya equal hai, to window ko left pe rakhte hain
                    right = mid;
                }
            }

            // Result list banate hain window ke elements ko add karke
            List<Integer> result = new ArrayList<>(k);
            for (int i = 0; i < k; i++) {
                result.add(arr[left + i]);
            }

            return result;
        }
    }

    // revised on 12/23/2025
    class SolutionRevisedOnThirdDay {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {

            int left = 0;
            int right = arr.length - k;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (x - arr[mid] > arr[mid + k] - x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                result.add(arr[left + i]);
            }

            return result;
        }
    }

    // revised on 12/29/2025
    class SolutionRevisedOnSevethDay {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int left = 0;
            int right = arr.length - k;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (x - arr[mid] > arr[mid + k] - x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                result.add(arr[left + i]);
            }

            return result;
        }
    }

    // revised on 1/12/2026
    class SolutionRevisedOnDayFourteen {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {

            int left = 0;
            int right = arr.length - k;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (x - arr[mid] > arr[mid + k] - x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < k; i++) {
                result.add(arr[left + i]);
            }

            return result;
        }
    }
}
