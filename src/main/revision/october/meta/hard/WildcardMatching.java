package main.revision.october.meta.hard;

public class WildcardMatching {
    class Solution {
        /**
         * Determines if a string matches a wildcard pattern.
         * '?' Matches any single character.
         * '*' Matches any sequence of characters (including the empty sequence).
         *
         * @param s The input string.
         * @param p The wildcard pattern.
         * @return true if the string matches the pattern, false otherwise.
         */
        public boolean isMatch(String s, String p) {
            // Using the most optimal tabular approach.
            return wildcardMatchingUsingTabular(p, s);
        }

        /**
         * Recursive solution for wildcard matching.
         * Note: This can be inefficient and may lead to a StackOverflowError for large
         * inputs.
         */
        public boolean wildCardMatching(String pattern, String text, int i, int j) {
            // Base Case: Both pattern and text are exhausted.
            if (i < 0 && j < 0) {
                return true;
            }
            // Base Case: Pattern is exhausted, but text is not.
            if (i < 0) {
                return false;
            }
            // Base Case: Text is exhausted, but pattern is not.
            if (j < 0) {
                // Check if the remaining pattern consists only of '*'.
                for (int k = 0; k <= i; k++) {
                    if (pattern.charAt(k) != '*') {
                        return false;
                    }
                }
                return true;
            }

            // Recursive step
            if (pattern.charAt(i) == text.charAt(j) || pattern.charAt(i) == '?') {
                return wildCardMatching(pattern, text, i - 1, j - 1);
            } else if (pattern.charAt(i) == '*') {
                // '*' can match the current text character or be an empty sequence.
                return wildCardMatching(pattern, text, i, j - 1) || wildCardMatching(pattern, text, i - 1, j);
            } else {
                return false;
            }
        }

        /**
         * Memoized recursive solution for wildcard matching.
         */
        public boolean wildCardMatchingUsingMemo(String pattern, String text, int i, int j, int[][] dp) {
            // Base Cases
            if (i < 0 && j < 0)
                return true;
            if (i < 0)
                return false;
            if (j < 0) {
                for (int k = 0; k <= i; k++) {
                    if (pattern.charAt(k) != '*')
                        return false;
                }
                return true;
            }

            // Check if this subproblem has already been solved.
            if (dp[i][j] != -1) {
                return dp[i][j] == 1;
            }

            boolean match;
            if (pattern.charAt(i) == text.charAt(j) || pattern.charAt(i) == '?') {
                match = wildCardMatchingUsingMemo(pattern, text, i - 1, j - 1, dp);
            } else if (pattern.charAt(i) == '*') {
                match = wildCardMatchingUsingMemo(pattern, text, i, j - 1, dp) ||
                        wildCardMatchingUsingMemo(pattern, text, i - 1, j, dp);
            } else {
                match = false;
            }

            dp[i][j] = match ? 1 : 0;
            return match;
        }

        /**
         * Tabular (dynamic programming) solution for wildcard matching.
         */
        public boolean wildcardMatchingUsingTabular(String pattern, String text) {
            int pLen = pattern.length();
            int sLen = text.length();
            boolean[][] dp = new boolean[pLen + 1][sLen + 1];

            // Step 1: Ek empty pattern ek empty text ke saath match hota hai.
            dp[0][0] = true;

            // Step 2: Ek empty pattern kisi bhi non-empty text ke saath match nahi hota.
            // Ye default value false hoti hai, isliye yahan loop ki zarurat nahi hai.

            // Step 3: Patterns jaise "a*", "b**" ko handle karna jab text empty ho.
            for (int i = 1; i <= pLen; i++) {
                if (pattern.charAt(i - 1) == '*') {
                    dp[i][0] = dp[i - 1][0];
                }
            }

            // Step 4: DP table ko fill karna.
            for (int i = 1; i <= pLen; i++) {
                for (int j = 1; j <= sLen; j++) {
                    if (pattern.charAt(i - 1) == text.charAt(j - 1) || pattern.charAt(i - 1) == '?') {
                        // Agar current character match ho ya '?' ho, toh diagonal value lete hain.
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (pattern.charAt(i - 1) == '*') {
                        // '*' ya toh empty sequence ko match karega (dp[i-1][j])
                        // ya current character ko match karke pichle match ko extend karega
                        // (dp[i][j-1]).
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }
                    // Agar match nahi hota, toh dp[i][j] default false hi rahega.
                }
            }

            // Final answer DP table ke last cell mein hoga.
            return dp[pLen][sLen];
        }
    }

    // revised on 12/17/2025
    class SolutionRevisedOnThirdDay {
        public boolean wildcardMatchingUsingTabular(String pattern, String text) {
            int pLen = pattern.length();
            int sLen = text.length();

            boolean[][] dp = new boolean[pLen + 1][sLen + 1];

            dp[0][0] = true;

            for (int i = 0; i <= pLen; i++) {
                if (pattern.charAt(i - 1) == '*') {
                    dp[i][0] = dp[i - 1][0];
                }
            }

            for (int i = 1; i <= pLen; i++) {
                for (int j = 1; j <= sLen; j++) {
                    if (pattern.charAt(i - 1) == text.charAt(j - 1) || pattern.charAt(i - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (pattern.charAt(i - 1) == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }

            return dp[pLen][sLen];

        }
    }

    // revised on 12/17/2025
    class SolutionRevisedOnSeventhDay {
        public boolean wildcardMatchingUsingTabular(String pattern, String text) {
            int pLen = pattern.length();
            int sLen = text.length();

            boolean[][] dp = new boolean[pLen + 1][sLen + 1];

            dp[0][0] = true;

            for (int i = 0; i <= pLen; i++) {
                if (pattern.charAt(i) == '*') {
                    dp[i][0] = dp[i - 1][0];
                }
            }

            for (int i = 1; i <= pLen; i++) {
                for (int j = 1; j <= sLen; j++) {
                    char c = pattern.charAt(i - 1);

                    if (c == text.charAt(j - 1) && c == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (c == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }

            return dp[pLen][sLen];
        }
    }

    // revised on 1/6/2026
    class SolutionRevisedOnFourteenDay {
        public boolean wildcardMatchingUsingTabular(String pattern, String text) {
            int pLen = pattern.length();
            int sLen = text.length();

            boolean[][] dp = new boolean[pLen + 1][sLen + 1];

            dp[0][0] = true;

            for (int i = 1; i <= pLen; i++) {
                if (pattern.charAt(i - 1) == '*') {
                    dp[i][0] = dp[i - 1][0];
                }
            }

            for (int i = 1; i <= pLen; i++) {
                for (int j = 1; j <= sLen; j++) {
                    char c = pattern.charAt(i - 1);

                    if (c == text.charAt(j - 1) && c == '*') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (c == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }

            return dp[pLen][sLen];

        }
    }

    // revised on 2/4/2026
    public class SolutionRevisedOnDayThirty {
        public boolean isMatch(String s, String p) {
            // Using the most optimal tabular approach.
            return wildcardMatchingUsingTabular(p, s);
        }

        public boolean wildcardMatchingUsingTabular(String pattern, String text) {

            int pLen = pattern.length();
            int sLen = text.length();

            boolean[][] dp = new boolean[pLen + 1][sLen];

            dp[0][0] = true;

            for (int i = 0; i <= pLen; i++) {
                if (pattern.charAt(i - 1) == '*') {
                    dp[i][0] = dp[i - 1][0];
                }
            }

            for (int i = 1; i <= pLen; i++) {
                for (int j = 1; j <= sLen; j++) {

                    if (pattern.charAt(i - 1) == text.charAt(j - 1) || pattern.charAt(i - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (pattern.charAt(i - 1) == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }

            return dp[pLen][sLen];
        }
    }
}
