package main.revision.october.meta.easy;

import java.util.*;

public class GenerateParentheses {
    /**
     * This method generates all combinations of well-formed parentheses for a given
     * number of pairs.
     * 
     * Time Complexity: O(4^n / sqrt(n)) - Catalan number complexity
     * Space Complexity: O(n) - Recursion stack space
     */
    class Solution {
        public List<String> generateParenthesis(int n) {

            List<String> result = new ArrayList<>();

            int opening = 0; // Opening brackets count
            int closing = 0; // Closing brackets count

            // DFS call to generate all valid combinations
            dfs(result, n, opening, closing, "");
            return result;
        }

        private static void dfs(List<String> result, int n, int opening, int closing, String str) {

            // Base case: If opening and closing brackets are equal to n, add to result
            if (opening == n && closing == n) {
                result.add(str);
                return;
            }

            // Step 1: Agar opening brackets ka count n se kam hai, toh ek aur opening
            // bracket add karo
            if (opening < n) {
                dfs(result, n, opening + 1, closing, str + "(");
            }

            // Step 2: Agar closing brackets ka count opening se kam hai, toh ek closing
            // bracket add karo
            if (closing < opening) {
                dfs(result, n, opening, closing + 1, str + ")");
            }
        }
    }

    // revision on 11/22/2025
    class SolutionRevisionOnThirdDay {
        public List<String> generateParenthesis(int n) {

            List<String> result = new ArrayList<>();

            int opening = 0;
            int closing = 0;

            dfs(result, n, opening, closing, "");
            return result;
        }

        private static void dfs(List<String> result, int n, int opening, int closing, String str) {

            if (opening == n && closing == n) {
                result.add(str);
                return;
            }

            if (opening < n) {
                dfs(result, n, opening + 1, closing, str + "(");
            }

            if (closing < opening) {
                dfs(result, n, opening, closing + 1, str + ")");
            }
        }
    }

    // revision on 11/28/2025
    class SolutionRevisionOnSeventhDay {
        public List<String> generateParenthesis(int n) {

            List<String> result = new ArrayList<String>();

            int opening = 0;
            int closing = 0;

            dfs(result, n, opening, closing, "");

            return result;

        }

        private static void dfs(List<String> result, int n, int opening, int closing, String str) {

            if (opening == n && closing == n) {
                result.add(str);
                return;
            }

            if (opening < n) {
                dfs(result, n, opening + 1, closing, "(");
            }

            if (closing < opening) {
                dfs(result, n, opening, closing + 1, ")");
            }
        }
    }

    // revision on 12/12/2025
    class SolutionRevisionOnFourteenDay {
        public List<String> generateParenthesis(int n) {

            List<String> result = new ArrayList<>();

            int opening = 0;
            int closing = 0;

            dfs(result, n, opening, closing, "");

            return result;

        }

        private static void dfs(List<String> result, int n, int opening, int closing, String str) {

            if (opening == n && closing == n) {
                result.add(str);
                return;
            }

            if (opening < n) {
                dfs(result, n, opening + 1, closing, str + "(");
            }

            if (closing < opening) {
                dfs(result, n, opening, closing + 1, str + ")");
            }

        }
    }

    // revision on 1/10/2026
    class SolutionRevisionOnDayThirty {
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();

            int opening = 0;
            int closing = 0;

            dfs(result, n, opening, closing, "");
            return result;
        }

        private static void dfs(List<String> result, int n, int opening, int closing, String str) {

            if (opening == n && closing == n) {
                result.add(str);
                return;
            }

            if (opening < n) {
                dfs(result, n, opening + 1, closing, str + "(");
            }

            if (closing < opening) {
                dfs(result, n, opening, closing + 1, str + ")");
            }
        }
    }
}
