package main.revision.october.meta.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingRanges {

    public class Solution {
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
}
