package main.revision.march.hard;

import java.util.*;

public class WordBreakII {

    // 22/03/2026
    class Solution {

        List<String> cur = new ArrayList<>();

        public List<String> wordBreak(String s, List<String> wordDict) {

            List<String> result = new ArrayList<>();
            cur.clear();
            Set<String> set = new HashSet<>(wordDict);

            backTrack(s, 0, result, cur, set);

            return result;
        }

        private void backTrack(String s, int index, List<String> res, List<String> cur, Set<String> set) {

            if (index == s.length()) {
                res.add(String.join("", cur));
            }

            for (int j = index; j < s.length(); j++) {

                String w = s.substring(index, j + 1);

                if (set.contains(w)) {
                    cur.add(w);
                    backTrack(s, j + 1, res, cur, set);
                    cur.removeLast();
                }
            }
        }
    }
}
