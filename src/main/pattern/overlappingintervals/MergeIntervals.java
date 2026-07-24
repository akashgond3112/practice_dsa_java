/**
 * @author akash
 * @date Jul 23, 2026
 * @time 5:23:38 PM
 */
package main.pattern.overlappingintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    class Solution {
        public int[][] merge(int[][] intervals) {

            if (intervals == null || intervals.length == 0) {
                return new int[0][0];
            }

            if (intervals.length == 1) {
                return intervals;
            }

            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

            List<int[]> mergedIntervals = new ArrayList<>();

            int[] currentInterval = intervals[0];
            mergedIntervals.add(currentInterval);

            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] <= currentInterval[1]) {
                    currentInterval[1] = Math.max(currentInterval[1], intervals[i][1]);
                } else {
                    currentInterval = intervals[i];
                    mergedIntervals.add(currentInterval);
                }
            }
            return mergedIntervals.toArray(new int[mergedIntervals.size()][]);

        }
    }

}
