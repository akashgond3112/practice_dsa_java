package main.revision.october.meta.hard;

import java.util.*;

public class BestMeetingPoint {

    /**
     * <b>Big O Notation:</b>
     * <ul>
     * <li><b>Time Complexity:</b> O(m * n), jahan 'm' rows ka number hai aur 'n'
     * grid mein columns ka number hai. Yeh grid ke initial traversal se dominate
     * hota hai jisme coordinates collect kiye jaate hain. Distance calculation step
     * O(k) leta hai, jahan 'k' gharon ka number hai, aur k <= m*n.</li>
     * <li><b>Space Complexity:</b> O(k), jahan 'k' gharon ka number hai, row aur
     * column coordinates ko store karne ke liye. Worst case mein (jab poora grid
     * gharon se bhara ho), yeh O(m * n) hota hai.</li>
     * </ul>
     */
    public class Solution {

        public int minTotalDistance(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            List<Integer> rows = new ArrayList<>();
            List<Integer> cols = new ArrayList<>();

            // Saare '1's ke row coordinates collect karo.
            // Yeh list pehle se hi sorted hogi kyunki hum row-by-row ja rahe hain.
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                    }
                }
            }

            // Saare '1's ke column coordinates collect karo.
            // Yeh list bhi sorted hogi kyunki hum column-by-column ja rahe hain.
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < m; i++) {
                    if (grid[i][j] == 1) {
                        cols.add(j);
                    }
                }
            }

            int totalDistance = 0;
            // Row coordinates ke liye median distance calculate karo.
            totalDistance += getMedianDistance(rows);
            // Column coordinates ke liye median distance calculate karo.
            totalDistance += getMedianDistance(cols);

            return totalDistance;
        }

        // Yeh function ek sorted list of points ke liye median tak ka total distance
        // calculate karta hai.
        private int getMedianDistance(List<Integer> points) {
            int distance = 0;
            int i = 0;
            int j = points.size() - 1;
            // Do pointers use karke, ek shuru se aur ek aakhir se,
            // hum pairs ke beech ka difference jodte jaate hain.
            while (i < j) {
                distance += points.get(j) - points.get(i);
                i++;
                j--;
            }
            return distance;
        }
    }

    // revised on 12/14/2025
    public class SolutionRevisedOnthirdDay {

        public int minTotalDistance(int[][] grid) {

            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            List<Integer> rows = new ArrayList<>();
            List<Integer> cols = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                for (int i = 0; i < m; i++) {
                    if (grid[i][j] == 1) {
                        cols.add(j);
                    }
                }
            }

            int totalDistance = 0;

            totalDistance += getMedianDistance(rows);
            totalDistance += getMedianDistance(cols);

            return totalDistance;
        }

        private int getMedianDistance(List<Integer> points) {
            int distance = 0;
            int i = 0;
            int j = points.size() - 1;

            while (i < j) {
                distance += points.get(i) - points.get(j);
                i++;
                j--;
            }

            return distance;
        }
    }

    // revised on 12/20/2025
    public class SolutionRevisedOnSeventhDay {

        public int minTotalDistance(int[][] grid) {

            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            List<Integer> rows = new ArrayList<>();
            List<Integer> cols = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        cols.add(i);
                    }
                }
            }

            int totalDistance = 0;

            totalDistance += getMedianDistance(rows);
            totalDistance += getMedianDistance(cols);

            return totalDistance;
        }

        private int getMedianDistance(List<Integer> points) {

            int distance = 0;
            int i = 0;
            int j = points.size();

            while (i < j) {
                distance += points.get(j) - points.get(i);
                i++;
                j--;
            }

            return distance;
        }
    }

    // revised on 12/20/2025
    public class SolutionRevisedOnFOurteenDay {

        public int minTotalDistance(int[][] grid) {

            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            List<Integer> rows = new ArrayList<>();
            List<Integer> cols = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                    }
                }
            }

            int totalDistance = 0;

            totalDistance += getMedianDistance(rows);
            totalDistance += getMedianDistance(cols);

            return totalDistance;
        }

        private int getMedianDistance(List<Integer> points) {

            int i = 0;
            int j = points.size() - 1;
            int distance = 0;
            while (i < j) {
                distance += points.get(j) - points.get(i);
                i++;
                j--;
            }

            return distance;
        }
    }

    // revised on 2/1/2026
    class SolutionRevisedOnDayThirty {
        public int minTotalDistance(int[][] grid) {

            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            List<Integer> rows = new ArrayList<>();
            List<Integer> cols = new ArrayList<>();

            int m = grid.length;
            int n = grid[0].length;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        cols.add(i);
                    }
                }
            }

            int totalDistance = 0;

            totalDistance += getMedianDistance(rows);
            totalDistance += getMedianDistance(cols);

            return totalDistance;
        }

        private int getMedianDistance(List<Integer> points) {
            int distance = 0;

            int i = 0;
            int j = points.size() - 1;

            while (i < j) {
                distance += points.get(j) - points.get(i);
                i++;
                j--;
            }
            return distance;
        }
    }
}
