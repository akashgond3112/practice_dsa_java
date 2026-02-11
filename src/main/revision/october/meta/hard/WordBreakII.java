package main.revision.october.meta.hard;

import java.util.*;

public class WordBreakII {
    private List<String> cur = new ArrayList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {

        List<String> res = new ArrayList<>();
        cur.clear();
        Set<String> wordSet = new HashSet<>(wordDict);

        // Recursively build all possible sentences by breaking the string using words
        // from the dictionary
        backtrack(s, 0, res, cur, wordSet);

        return res;
    }

    private void backtrack(String s, int i, List<String> res, List<String> cur, Set<String> wordSet) {

        if (i == s.length()) {
            res.add(String.join(" ", cur));
            return;
        }

        for (int j = i; j < s.length(); j++) {

            String w = s.substring(i, j + 1);

            if (wordSet.contains(w)) {
                cur.add(w);
                backtrack(s, j + 1, res, cur, wordSet);
                cur.remove(cur.size() - 1);
            }
        }
    }

    // Revision 18/10/2025
    public class SolutionRevisionThirdDay {
        public List<String> wordBreakRevisionThirdDay(String s, List<String> wordDict) {

            List<String> res = new ArrayList<>();
            cur.clear();
            Set<String> set = new HashSet<>(wordDict);

            backtrackRevisionThirdDay(s, 0, res, cur, set);

            return res;
        }

        private void backtrackRevisionThirdDay(String s, int i, List<String> res, List<String> cur, Set<String> set) {

            if (i == s.length()) {
                res.add(String.join(" ", cur));
                return;
            }

            for (int j = i; j < s.length(); j++) {

                String w = s.substring(i, j + 1);

                if (set.contains(w)) {
                    cur.add(w);
                    backtrackRevisionThirdDay(s, j + 1, res, cur, set);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }

    // Revision 24/10/2025
    public class SolutionRevisionSeventhDay {

        public List<String> wordBreakRevisionThirdDay(String s, List<String> wordDict) {

            List<String> res = new ArrayList<>();
            cur.clear();
            Set<String> set = new HashSet<>(wordDict);

            dfs(s, 0, res, cur, set);

            return res;
        }

        private void dfs(String s, int i, List<String> res, List<String> cur, Set<String> set) {

            if (i == s.length()) {
                res.add(String.join("", cur));
                return;
            }

            for (int j = i; j < s.length(); j++) {

                String w = s.substring(i, j + 1);

                if (set.contains(w)) {
                    cur.add(w);
                    dfs(s, j + 1, res, cur, set);
                    cur.remove(cur.size() - 1);
                }
            }

        }
    }

    // Revision 11/7/2025
    public class SolutionRevisionFourteenDay {
        public List<String> wordBreakRevisionThirdDay(String s, List<String> wordDict) {

            List<String> res = new ArrayList<>();

            cur.clear();
            Set<String> set = new HashSet<>(wordDict);

            dfs(s, 0, res, cur, set);
            return res;
        }

        public void dfs(String s, int i, List<String> res, List<String> cur, Set<String> set) {

            if (i == s.length()) {
                res.add(String.join("", cur));
                return;
            }

            for (int j = i; j < s.length(); j++) {

                String w = s.substring(i, j + 1);

                if (set.contains(w)) {
                    cur.add(w);
                    dfs(s, j + 1, res, cur, set);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }

    // Revision 12/6/2025
    public class SolutionRevisionThirtyDay {

        public List<String> wordBreakRevisionThirdDay(String s, List<String> wordDict) {

            List<String> res = new ArrayList<>();
            cur.clear();

            Set<String> set = new HashSet<>(wordDict);

            dfs(s, 0, res, cur, set);

            return res;
        }

        public void dfs(String s, int i, List<String> res, List<String> cur, Set<String> set) {

            if (i == s.length()) {
                res.add(String.join("", cur));
                return;
            }

            for (int j = i; j < s.length(); j++) {
                String w = s.substring(i, j + 1);

                if (set.contains(w)) {
                    cur.add(w);
                    dfs(s, j + 1, res, cur, set);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }

}
