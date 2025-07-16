/**
 * @author agond
 * @date Jul 16, 2025
 * @time 5:53:28 PM
 */
package main.revision.meta.hard;

public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {

        boolean[][] m = new boolean[s.length() + 1][p.length() + 1];
        return dfs(0, 0, s, p, m);
    }

    private boolean dfs(int i, int j, String s, String p, boolean[][] m) {

        if (i <= s.length() && j <= p.length() && m[i][j]) {
            return m[i][j];
        }

        if (j >= p.length()) {
            return i >= s.length();
        }

        if (i >= s.length()) {

            boolean result = j + 1 < p.length() && p.charAt(j + 1) == '*' && dfs(i, j + 2, s, p, m);

            if (i <= s.length() && j <= p.length()) {
                m[i][j] = result;
            }

            return result;
        }

        boolean isMatch = s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';

        boolean result;

        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            result = dfs(i, j + 2, s, p, m) || (isMatch && dfs(i + 1, j, s, p, m));
        } else if (isMatch) {
            result = dfs(i + 1, j + 1, s, p, m);
        } else {
            result = false;
        }

        if (i <= s.length() && j <= p.length()) {
            m[i][j] = result;
        }

        return result;
    }
}
