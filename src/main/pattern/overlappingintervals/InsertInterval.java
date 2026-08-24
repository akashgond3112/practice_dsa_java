package main.pattern.overlappingintervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {

    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {

            List<int[]> mergedIntervals = new ArrayList<>();
            int i = 0;
            int n = intervals.length;

            // Phase 1: add all intervals ending before newInterval starts
            while (i < n && intervals[i][1] < newInterval[0]) {
                mergedIntervals.add(intervals[i]);
                i++;
            }

            // Phase 2: merge all overlapping intervals into newInterval
            int start = newInterval[0];
            int end = newInterval[1];
            while (i < n && intervals[i][0] <= end) {
                start = Math.min(start, intervals[i][0]);
                end = Math.max(end, intervals[i][1]);
                i++;
            }
            mergedIntervals.add(new int[] { start, end });

            // Phase 3: add all remaining intervals (start after merged interval's end)
            while (i < n) {
                mergedIntervals.add(intervals[i]);
                i++;
            }
            // Collections.addAll(mergedIntervals, intervals);

            return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
        }
    }
}
