/**
 * @author agond
 * @date Jun 19, 2025
 * @time 8:32:30 PM
 */
package main.revision.meta.hard;

import java.util.*;

public class WordBreakII {

    private Set<String> wordSet;
    private List<String> res;

    public List<String> wordBreak(String s, List<String> wordDict) {

        wordSet = new HashSet<>(wordDict);

        res = new ArrayList<>();

        List<String> cur = new ArrayList<>();
        // backtrack
        backtrack(s, 0, cur);
        return res;
    }

    private void backtrack(String s, int i, List<String> cur) {

        // base case
        if (i == s.length()) {
            res.add(String.join("", cur));
            return;
        }

        for (int j = 0; j < s.length(); j++) {
            String curWor = s.substring(1, j + 1);

            if (wordSet.contains(curWor)) {
                cur.add(curWor);
                backtrack(s, j + 1, cur);
                cur.removeLast();
            }
        }
    }
}
