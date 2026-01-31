package main.revision.october.meta.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Problem Description
You're given a sorted array of unique integers nums and a range defined by [lower, upper]. Your task is to find all the missing numbers in this range that are not present in nums, and represent them as a list of ranges.

The problem asks you to identify which numbers between lower and upper (inclusive) are missing from the array nums. Instead of listing each missing number individually, you need to group consecutive missing numbers into ranges represented as [start, end].

For example:

If nums = [0, 1, 3, 50, 75] and the range is [0, 99]
Missing numbers are: 2, 4-49, 51-74, 76-99
The output would be: [[2, 2], [4, 49], [51, 74], [76, 99]]
The solution works by checking three key areas:

Before the first element: If nums[0] > lower, then all numbers from lower to nums[0] - 1 are missing
Between consecutive elements: For each pair of consecutive numbers in nums, if they differ by more than 1, the numbers between them are missing
After the last element: If nums[-1] < upper, then all numbers from nums[-1] + 1 to upper are missing
The pairwise function is used to iterate through consecutive pairs of elements in the array, making it easy to identify gaps. When a gap is found (i.e., b - a > 1), we add the range [a + 1, b - 1] to our answer.

The special case of an empty nums array is handled separately - in this case, the entire range [lower, upper] is missing.
*/
public class MissingRanges {

    // Yeh class missing ranges ko dhoondhne ke liye hai
    public class Solution {
        public static List<List<Integer>> missingRanges(int[] arr, int lower, int upper) {

            // Array ki length n mein store karte hain
            int n = arr.length;
            // Result ko store karne ke liye ek list banate hain
            List<List<Integer>> res = new ArrayList<>();

            // Agar array khali hai, toh pura range missing hai
            if (n == 0) {
                res.add(Arrays.asList(lower, upper));
                return res;
            }

            // Agar lower bound array ke first element se chhota hai
            if (lower < arr[0]) {
                res.add(Arrays.asList(lower, arr[0] - 1));
            }

            // Array ke consecutive elements ke beech missing ranges dhoondhte hain
            for (int i = 0; i < n - 1; i++) {
                // Agar consecutive elements ka difference 1 ya usse kam hai, toh skip karte
                // hain
                if (arr[i + 1] - arr[i] <= 1)
                    continue;

                // Missing range ko result mein add karte hain
                res.add(Arrays.asList(arr[i] + 1, arr[i + 1] - 1));
            }

            // Agar upper bound array ke last element se bada hai
            if (upper > arr[n - 1]) {
                res.add(Arrays.asList(arr[n - 1] + 1, upper));
            }

            // Final result return karte hain
            return res;
        }
    }

    // revised on 12/13/2025
    public class SolutionRevisedOnThirdDay {
        public static List<List<Integer>> missingRanges(int[] arr, int lower, int upper) {

            int n = arr.length;
            List<List<Integer>> res = new ArrayList<>();

            if (n == 0) {
                res.add(Arrays.asList(lower, upper));
                return res;
            }

            if (lower < arr[0]) {
                res.add(Arrays.asList(lower, arr[0] - 1));
            }

            for (int i = 0; i < n - 1; i++) {
                if (arr[i + 1] - arr[i] <= 1)
                    continue;

                res.add(Arrays.asList(arr[i] + 1, arr[i + 1] - 1));
            }

            if (upper > arr[n - 1]) {
                res.add(Arrays.asList(arr[n - 1] + 1, upper));
            }

            return res;
        }
    }

    // revised on 12/19/2025
    public class SolutionRevisedOnSeventhDay {
        public static List<List<Integer>> missingRanges(int[] arr, int lower, int upper) {

            int n = arr.length;

            List<List<Integer>> res = new ArrayList<>();

            if (n == 0) {
                res.add(Arrays.asList(lower, upper));
                return res;
            }

            if (lower < arr[0]) {
                res.add(Arrays.asList(lower, arr[0] - 1));
            }

            for (int i = 0; i < n - 1; i++) {

                if (arr[i + 1] - arr[i] <= 1) {
                    continue;
                }

                res.add(Arrays.asList(arr[i] + 1, arr[i + 1] - 1));
            }

            if (upper > arr[n - 1]) {
                res.add(Arrays.asList(arr[n - 1] + 1, upper));
            }

            return res;
        }
    }

    // revised on 12/19/2025
    public class SolutionRevisedOnFourteenDay {
        public static List<List<Integer>> missingRanges(int[] arr, int lower, int upper) {

            int n = arr.length;

            List<List<Integer>> result = new ArrayList<>();

            if (n == 0) {
                result.add(Arrays.asList(lower, upper));
                return result;
            }

            if (lower < arr[0]) {
                result.add(Arrays.asList(lower, arr[0] - 1));
            }

            for (int i = 0; i < n - 1; i++) {

                if (arr[i + 1] - arr[i] <= 1) {
                    continue;
                }

                result.add(Arrays.asList(arr[i + 1], arr[i + 1] - 1));
            }

            if (upper > arr[n - 1]) {
                result.add(Arrays.asList(arr[n - 1] + 1, upper));
            }
            return result;
        }
    }

    // revised on 1/31/2026
    class SolutionRevisedOnDayThirty {
        public static List<List<Integer>> missingRanges(int[] arr, int lower, int upper) {

            int n = arr.length;

            List<List<Integer>> result = new ArrayList<>();

            if (n == 0) {
                result.add(Arrays.asList(lower, upper));
                return result;
            }

            if (lower < arr[0]) {
                result.add(Arrays.asList(lower, arr[0 - 1]));
            }

            for (int i = 0; i < n - 1; i++) {

                if (arr[i + 1] - arr[i] <= 1)
                    continue;

                result.add(Arrays.asList(arr[i] + 1, arr[i + 1] - 1));
            }

            if (upper > arr[n - 1]) {
                result.add(Arrays.asList(arr[n - 1] + 1, upper));
            }

            return result;
        }
    }
}
