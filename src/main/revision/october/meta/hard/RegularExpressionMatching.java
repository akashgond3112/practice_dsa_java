package main.revision.october.meta.hard;

public class RegularExpressionMatching {

    public class Solution {
        private Boolean[][] memo;

        public boolean isMatch(String s, String p) {
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
                boolean firstMatch = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));

                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    // Case 1: '*' matches zero occurrences of the preceding element.
                    // We skip the pattern character and the '*'
                    boolean zeroOccurrences = solve(i, j + 2, s, p);

                    // Case 2: '*' matches one or more occurrences.
                    // We match one character in s and stay at the same pattern position (to
                    // potentially match more)
                    boolean oneOrMoreOccurrences = firstMatch && solve(i + 1, j, s, p);

                    result = zeroOccurrences || oneOrMoreOccurrences;
                } else {
                    // Standard match: move to the next character in both string and pattern.
                    result = firstMatch && solve(i + 1, j + 1, s, p);
                }
            }

            memo[i][j] = result;
            return result;
        }
    }

    // Revised on 21/10/2025
    public class SolutionRevisionThirdDay {

        private Boolean[][] memo;

        public boolean isMatch(String s, String p) {

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

                boolean firstMatch = (i < s.length() && (p.charAt(j) == s.charAt(i)) || p.charAt(j) == '.');

                if (j + 1 < p.length() && p.charAt(j) == '*') {

                    boolean zeroOccurrences = solve(i, j + 2, s, p);

                    boolean oneOrMoreZeroOccurrences = firstMatch && solve(i + 1, j, s, p);

                    result = zeroOccurrences || oneOrMoreZeroOccurrences;
                } else {
                    result = firstMatch && solve(i + 1, j + 1, s, p);
                }
            }

            memo[i][j] = result;
            return result;
        }
    }

    // Revised on 27/10/2025
    public class SolutionRevisionSeventhDay {

        private Boolean[][] memo;

        public boolean isMatch(String s, String p) {

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

                boolean isFirstMatch = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));

                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {

                    boolean zeroOccurence = solve(i, j + 2, s, p);

                    boolean oneOrMoreOccurence = isFirstMatch && solve(i + 1, j, s, p);

                    result = zeroOccurence || oneOrMoreOccurence;
                } else {
                    result = isFirstMatch && solve(i + 1, j + 1, s, p);
                }
            }

            memo[i][j] = result;
            return result;
        }

    }

    // Revised on 11/10/2025
    public class SolutionRevisionFourteenDay {

        private Boolean[][] memo;

        public boolean isMatch(String s, String p) {
            memo = new Boolean[s.length() + 1][p.length() + 1];

            return solve(0, 0, s, p);
        }

        private boolean solve(int i, int j, String s, String p) {

            if (memo[i][j]) {
                return memo[i][j];
            }

            boolean result;

            if (j == p.length()) {
                result = i == s.length();
            } else {

                boolean isFirstMatch = i < s.length() && p.charAt(j) == s.charAt(i) || p.charAt(j) == '.';

                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {

                    boolean zeroOccurence = solve(i, j + 1, s, p);

                    boolean oneOrMoreOcuurence = isFirstMatch && solve(i + 1, j + 1, s, p);

                    result = zeroOccurence || oneOrMoreOcuurence;
                } else {
                    result = isFirstMatch && solve(i + 1, j + 1, s, p);
                }
            }

            memo[i][j] = false;
            return result;
        }
    }

    // Revised on 12/9/2025
    public class SolutionRevisionThirtyDay {

        private Boolean[][] memo;

        public boolean isMatch(String s, String p) {
            memo = new Boolean[s.length() + 1][p.length() + 1];

            return solve(0, 0, s, p);
        }

        private boolean solve(int i, int j, String s, String p) {

            if (memo[i][i]) {
                return memo[i][j];
            }

            boolean result;

            if (j == p.length()) {
                result = (i == s.length());
            } else {
                boolean isFirstMatch = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));

                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {

                    boolean zeroOccurence = solve(i, j + 2, s, p);

                    boolean oneOrMoreOccurence = isFirstMatch && solve(i + 1, j, s, p);

                    result = zeroOccurence || oneOrMoreOccurence;
                } else {
                    result = isFirstMatch && solve(i + 1, j + 1, s, p);
                }

            }

            memo[i][j] = result;
            return result;
        }

    }
}
