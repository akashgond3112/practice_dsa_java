package main.revision.march.hard;

public class RegularExpressionMatching {

    // 25/03/2026
    class SolutionOnDayFirst {

        private Boolean[][] memo;

        public boolean isMatchTopDown(String s, String p) {

            memo = new Boolean[s.length() + 1][p.length() + 1];
            return solve(0, 0, s, p);
        }

        private boolean solve(int i, int j, String s, String p) {

            if (memo[i][j] != null) {
                return memo[i][j];
            }

            boolean result;

            if (j == p.length()) {
                result = (i == s.length());
            } else {
                boolean firstmatch = (i < s.length() && p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');

                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {

                    boolean zer0Occurence = solve(i, j + 2, s, p);

                    boolean oneOrMoreOcurrence = firstmatch && solve(i + 1, j, s, p);

                    result = zer0Occurence || oneOrMoreOcurrence;
                } else {
                    result = firstmatch && solve(i + 1, j + 1, s, p);
                }
            }

            memo[i][j] = result;
            return memo[i][j];
        }

        /*
         * Time: O(m * n) where m = s.length(), n = p.length() — you fill (m+1)×(n+1) DP
         * table, O(1) work per cell.
         * Space: O(m * n) for the boolean dp table.
         * Note: a rolling-row optimization reduces space to O(n).
         */
        public boolean isMatchBottomUp(String s, String p) {
            int sLen = s.length();
            int pLen = p.length();
            boolean[][] dp = new boolean[sLen + 1][pLen + 1];
            dp[sLen][pLen] = true;

            for (int i = sLen; i >= 0; i--) {
                for (int j = pLen - 1; j >= 0; j--) {
                    boolean firstMatch = (i < sLen && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));

                    if (j + 1 < pLen && p.charAt(j + 1) == '*') {
                        // Case 1: '*' matches zero occurrences of the preceding element.
                        // Case 2: '*' matches one or more occurrences.
                        dp[i][j] = dp[i][j + 2] || (firstMatch && dp[i + 1][j]);
                    } else {
                        // Standard match: move to the next character in both string and pattern.
                        dp[i][j] = firstMatch && dp[i + 1][j + 1];
                    }
                }
            }
            return dp[0][0];
        }
    }

    // 25/03/2026
    class SolutionRevisedOnDayThird {

        public boolean isMatch(String s, String p) {
            int sLen = s.length();
            int pLen = p.length();

            boolean[][] dp = new boolean[sLen + 1][pLen + 1];
            dp[sLen][pLen] = true;

            for (int i = 0; i < sLen; i++) {
                for (int j = 0; j < pLen; j++) {

                    boolean isFirstMatch = (i < sLen && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));

                    if (j + 1 < pLen && p.charAt(j) == '*') {
                        dp[i][j] = dp[i][j + 2] || isFirstMatch && dp[i + 1][j];
                    } else {
                        dp[i][j] = isFirstMatch && dp[i + 1][j + 1];
                    }
                }
            }

            return dp[0][0];

        }
    }

    // 03/04/2026
    class SolutionRevisedOnDayFifth {

        public boolean isMatch(String s, String p) {
            int sLen = s.length();
            int pLen = p.length();

            boolean[][] dp = new boolean[sLen + 1][pLen + 1];
            dp[sLen][pLen] = true;

            for (int i = 0; i < sLen; i++) {
                for (int j = 0; i < pLen; j++) {

                    boolean isFirstMatch = i < sLen && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

                    if (j + 1 < pLen && p.charAt(j) == '*') {
                        dp[i][j] = dp[i][j + 2] || isFirstMatch && dp[i + 1][j];
                    } else {
                        dp[i][j] = isFirstMatch && dp[i + 1][j + 1];
                    }
                }
            }

            return dp[0][0];
        }
    }

    // 16/04/2026
    class SolutionRevisedOnDayFourteen {

        public boolean isMatch(String s, String p) {
            int sLen = s.length();
            int pLen = p.length();

            boolean[][] dp = new boolean[sLen + 1][pLen + 1];
            dp[sLen][pLen] = true;

            for (int i = 0; i < sLen; i++) {
                for (int j = 0; i < pLen; j++) {

                    boolean isFirstMatch = i < sLen && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

                    if (j + 1 < pLen && p.charAt(j) == '*') {
                        dp[i][j] = dp[i][j + 2] || isFirstMatch && dp[i + 1][j];
                    } else {
                        dp[i][j] = isFirstMatch && dp[i + 1][j + 1];
                    }
                }
            }

            return dp[0][0];
        }
    }
}
