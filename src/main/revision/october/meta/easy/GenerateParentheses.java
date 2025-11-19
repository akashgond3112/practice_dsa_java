package main.revision.october.meta.easy;

import java.util.*;

public class GenerateParentheses {
    class Solution {
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
