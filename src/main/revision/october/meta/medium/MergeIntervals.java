package main.revision.october.meta.medium;

import java.util.*;

public class MergeIntervals {

    public class Solution {
        public int[][] merge(int[][] intervals) {

            if (intervals.length <= 1) {
                return intervals;
            }

            Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

            List<int[]> res = new ArrayList<>();

            int[] newInterval = intervals[0];
            res.add(newInterval);

            for (int[] interval : intervals) {

                if (interval[0] <= newInterval[1]) {
                    newInterval[1] = Math.max(newInterval[1], interval[1]);
                } else {
                    newInterval = interval;
                    res.add(newInterval);
                }
            }

            return res.toArray(new int[res.size()][]);
        }
    }

    // Revised on 21/10/2025
    public class SolutionRevisionThirdDay {

        public int[][] merge(int[][] intervals) {

            if (intervals.length <= 1) {
                return intervals;
            }

            Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

            List<int[]> res = new ArrayList<>();

            int[] newInterval = intervals[0];
            res.add(newInterval);

            for (int[] interval : intervals) {

                if (interval[0] <= newInterval[1]) {
                    newInterval[1] = Math.max(newInterval[1], interval[1]);
                } else {
                    newInterval = interval;
                    res.add(newInterval);
                }
            }

            return res.toArray(new int[res.size()][]);
        }
    }

    // Revised on 10/28/2025
    public class SolutionRevisionSeventhDay {

        public int[][] merge(int[][] intervals) {

            if (intervals.length <= 1) {
                return intervals;
            }

            Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

            List<int[]> res = new ArrayList<>();
            res.add(intervals[0]);

            int[] curInterval = intervals[0];

            for (int[] interval : intervals) {

                if (interval[0] <= curInterval[1]) {
                    curInterval[1] = Math.max(interval[1], curInterval[1]);
                } else {
                    curInterval = interval;
                    res.add(curInterval);
                }
            }

            return res.toArray(new int[res.size()][]);
        }
    }

    // Revised on 11/11/2025
    public class SolutionRevisionFourteenDay {

        public int[][] merge(int[][] intervals) {

            if (intervals.length <= 1) {
                return intervals;
            }

            Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

            List<int[]> res = new ArrayList<>();

            res.add(intervals[0]);

            int[] curInterval = intervals[0];

            for (int[] interval : intervals) {

                if (interval[0] <= curInterval[1]) {
                    curInterval[1] = Math.max(interval[1], curInterval[1]);
                } else {
                    curInterval = interval;
                    res.add(curInterval);
                }
            }

            return res.toArray(new int[res.size()][]);
        }
    }

    // Revised on 12/10/2025
    public class SolutionRevisionThirtyDay {

        public int[][] merge(int[][] intervals) {

            if (intervals.length <= 1) {
                return intervals;
            }

            Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

            List<int[]> res = new ArrayList<>();

            res.add(intervals[0]);

            int[] curInterval = intervals[0];

            for (int[] interval : intervals) {

                if (interval[0] <= curInterval[1]) {
                    curInterval[1] = Math.max(interval[1], curInterval[1]);
                } else {
                    curInterval = interval;
                    res.add(curInterval);
                }
            }

            return res.toArray(new int[res.size()][]);
        }
    }

}
