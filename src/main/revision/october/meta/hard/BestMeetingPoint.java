package main.revision.october.meta.hard;

import java.util.*;

public class BestMeetingPoint {

    /**
     * Yeh class grid mein best meeting point dhundne ka solution deti hai.
     * "Best" meeting point woh (row, col) point hai jo saare gharon (grid mein '1'
     * se dikhaya gaya hai)
     * se uss point tak ka total Manhattan distance sabse kam karta hai.
     *
     * <p>
     * Do points (x1, y1) aur (x2, y2) ke beech ka Manhattan distance |x1 - x2| +
     * |y1 - y2| hota hai.
     * Ek meeting point (x, y) tak ka total distance saare gharon (xi, yi) se
     * Manhattan distances ka sum hota hai:
     * Sum(|x - xi| + |y - yi|).
     *
     * <p>
     * Isko hum do alag-alag one-dimensional problems mein tod sakte hain:
     * 1. Sum(|x - xi|) ko minimize karna
     * 2. Sum(|y - yi|) ko minimize karna
     *
     * <p>
     * 1D problem ka sabse accha solution coordinates ka median chunna hota hai.
     * Isliye, best meeting point ka x-coordinate saare gharon ke row coordinates ka
     * median hoga,
     * aur y-coordinate saare gharon ke column coordinates ka median hoga.
     *
     * <p>
     * <b>Algorithm ke Steps:</b>
     * <ol>
     * <li><b>Coordinates Jama Karna:</b> Grid ko traverse karke saare gharon (jahan
     * grid value 1 hai) ke row aur column coordinates ko collect karo. Inhe do alag
     * lists, `rows` aur `cols` mein store karo.
     * Note: Row-major aur phir column-major order mein traverse karne se,
     * coordinates ki collected lists apne aap sorted ho jayengi.</li>
     * <li><b>Rows ke liye 1D Distance Calculate Karna:</b> Har row coordinate se
     * unke median tak ka distance ka sum calculate karo. Yeh median ko explicitly
     * dhundhe bina efficiently kiya jaata hai. Ek sorted list mein median tak ke
     * distances ka sum, sabse bade aur sabse chhote element ke difference, doosre
     * sabse bade aur doosre sabse chhote element ke difference, aur aise hi aage,
     * jab tak pointers beech mein mil nahi jaate, ke sum ke barabar hota hai.</li>
     * <li><b>Columns ke liye 1D Distance Calculate Karna:</b> Yahi calculation
     * column coordinates ke liye bhi karo.</li>
     * <li><b>Distances ko Jodna:</b> Minimum total Manhattan distance step 2 aur 3
     * ke results ka sum hai.</li>
     * </ol>
     *
     * <p>
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
}
