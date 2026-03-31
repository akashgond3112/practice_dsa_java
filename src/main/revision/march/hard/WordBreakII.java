package main.revision.march.hard;

import java.util.*;

public class WordBreakII {

    /*
     * Time Complexity: O(2^n)
     * The loop for (int index = startIndex; index < s.length(); index++) runs at
     * each recursion level.
     * In worst case, you explore all possible partitions of string s.
     * For a string of length n, there are 2^n possible ways to partition it (each
     * position can be a split point or not).
     * Each partition generates a result string via String.join() which is O(n).
     * Total: O(n · 2^n) (or sometimes written as O(2^n) ignoring the linear
     * factor).
     * Space Complexity: O(n + h)
     * Set<String> set: O(n) (all words in dictionary combined).
     * Recursion call stack depth: O(n) (max depth when breaking into single
     * characters).
     * Current list cur: O(n) (stores at most n words in a partition).
     * Result list: O(2^n) if storing all possible partitions (but this is output
     * space, often not counted in Big O analysis).
     * Total (excluding output): O(n)
     */

    // 22/03/2026
    class Solution {

        public List<String> wordBreak(String s, List<String> wordDict) {

            List<String> result = new ArrayList<>();
            List<String> cur = new ArrayList<>();
            Set<String> set = new HashSet<>(wordDict);

            backTrack(s, 0, result, set, cur);

            return result;
        }

        private void backTrack(String s, int startIndex, List<String> res, Set<String> set, List<String> cur) {

            if (startIndex == s.length()) {
                res.add(String.join("", cur));
            }

            for (int index = startIndex; index < s.length(); index++) {

                String w = s.substring(startIndex, index + 1);

                if (set.contains(w)) {
                    cur.add(w);
                    backTrack(s, index + 1, res, set, cur);
                    cur.removeLast();
                }
            }
        }
    }

    // 25/03/2026
    class SolutionRevisedOnDayThird {

        public List<String> wordBreak(String s, List<String> wordDict) {

            List<String> result = new ArrayList<>();
            List<String> cur = new ArrayList<>();
            Set<String> set = new HashSet<>(wordDict);

            backTrack(s, 0, result, set, cur);

            return result;
        }

        private void backTrack(String s, int startIndex, List<String> result, Set<String> set, List<String> cur) {

            if (startIndex == s.length()) {
                result.add(String.join("", cur));
            }

            for (int index = startIndex; index < s.length(); index++) {

                String w = s.substring(startIndex, index + 1);

                if (set.contains(w)) {
                    cur.add(w);
                    backTrack(s, index + 1, result, set, cur);
                    cur.removeLast();
                }
            }
        }
    }

    // 31/03/2026
    class SolutionRevisedOnDayFifth {

        public List<String> wordBreak(String s, List<String> wordDict) {
            List<String> result = new ArrayList<>();
            List<String> cur = new ArrayList<>();
            Set<String> set = new HashSet<>(wordDict);

            backTrack(s, 0, result, set, cur);

            return result;
        }

        private void backTrack(String s, int startIndex, List<String> result, Set<String> set, List<String> cur) {

            if (startIndex == s.length()) {
                result.add(String.join("", cur));
            }

            for (int index = startIndex; index < s.length(); index++) {

                String w = s.substring(startIndex, index + 1);

                if (set.contains(w)) {
                    cur.add(w);
                    backTrack(s, index + 1, result, set, cur);
                    cur.removeLast();
                }
            }
        }
    }
}
