package main.revision.march.hard;

public class WildcardMatching {

    class Solution {
        public boolean isMatch(String text, String pattern) {
            int pLen = pattern.length();
            int sLen = text.length();

            boolean[][] dp = new boolean[pLen + 1][sLen + 1];
            dp[0][0] = true;

            for (int i = 1; i <= pLen; i++) {
                if (pattern.charAt(i) == '*') {
                    dp[i][0] = dp[i - 1][0];
                }
            }

            for (int i = 1; i <= pLen; i++) {
                for (int j = 1; j < sLen; j++) {

                    if (pattern.charAt(i - 1) == text.charAt(j - 1) || pattern.charAt(i) == '?') {

                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (pattern.charAt(i) == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }

            return dp[pLen][sLen];
        }
    }

    // 05/05/2026
    class SolutionRevisedOnDayThird {
        public boolean isMatch(String text, String pattern) {

            int pLen = pattern.length();
            int sLen = text.length();

            boolean[][] dp = new boolean[pLen + 1][sLen + 1];
            dp[0][0] = true;

            for (int i = 1; i <= pLen; i++) {
                dp[i][0] = dp[i - 1][0];
            }

            for (int i = 1; i <= pLen; i++) {
                for (int j = 1; j <= sLen; j++) {

                    if (pattern.charAt(i - 1) == text.charAt(j - 1) || pattern.charAt(i) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }

            return dp[pLen][sLen];
        }
    }

    // 11/05/2026
    class SolutionRevisedOnDaySeventh {
        public boolean isMatch(String text, String pattern) {

            int pLen = pattern.length();
            int sLen = text.length();

            boolean[][] dp = new boolean[pLen + 1][sLen + 1];
            dp[0][0] = true;

            for (int i = 1; i < pLen; i++) {
                dp[i][0] = dp[i - 1][0];
            }

            for (int i = 1; i <= pLen; i++) {
                for (int j = 1; j <= sLen; j++) {
                    if (pattern.charAt(i) == text.charAt(j) || pattern.charAt(i) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (pattern.charAt(i) == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];

                    }
                }
            }

            return dp[pLen][pLen];
        }
    }
}
