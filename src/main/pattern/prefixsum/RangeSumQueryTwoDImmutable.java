/**
 * @author akash
 * @date Jul 29, 2026
 * @time 4:51:52 PM
 */
package main.pattern.prefixsum;

public class RangeSumQueryTwoDImmutable {
    public class NumMatrix {
        private int[][] dp;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            dp = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // top + left - topleft - matrix[i][j]
                    dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1] - dp[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // result = total - top -left + topLeft
            return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
        }
    }

}
