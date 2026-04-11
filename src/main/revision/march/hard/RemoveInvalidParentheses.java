package main.revision.march.hard;

import java.util.*;

public class RemoveInvalidParentheses {

    // 18/03/2026
    class SolutionOnDayFirst {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        public List<String> removeInvalidParentheses(String s) {

            solve(s, getMinInvalid(s));

            return res;
        }

        private int getMinInvalid(String s) {
            int open = 0;
            int close = 0;

            for (char c : s.toCharArray()) {

                if (c == '(') {
                    open++;
                } else if (c == ')') {
                    if (open > 0) {
                        open--;
                    }
                    close++;
                }
            }

            return open + close;
        }

        private void solve(String s, int invalid) {

            if (map.containsKey(s)) {
                return;
            }

            map.put(s, 1);

            if (invalid < 0) {
                return;
            }

            if (getMinInvalid(s) == 0) {
                res.add(s);
            }

            for (int i = 0; i < s.length(); i++) {

                String left = s.substring(0, i);
                String right = s.substring(i + 1);

                solve(left + right, i - 1);
            }
        }
    }

    // 21/03/2026
    class SolutionRevisedOnDayThird {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        public List<String> removeInvalidParentheses(String s) {

            solve(s, getMinInvalid(s));

            return res;
        }

        private int getMinInvalid(String s) {

            int open = 0;
            int close = 0;

            for (char c : s.toCharArray()) {

                if (c == '(') {
                    open++;
                } else {
                    if (open > 0) {
                        open--;
                    }
                    close++;
                }
            }

            return open + close;
        }

        private void solve(String s, int minInValid) {

            if (map.containsKey(s)) {
                return;
            }

            map.put(s, 1);

            if (minInValid < 0) {
                return;
            }

            if (getMinInvalid(s) == 0) {
                res.add(s);
            }

            for (int i = 0; i < s.length(); i++) {

                String left = s.substring(0, i);
                String right = s.substring(i + 1);

                solve(left + right, i - 1);

            }
        }
    }

    // 27/03/2026
    class SolutionRevisedOnDayFifth {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        public List<String> removeInvalidParentheses(String s) {

            solve(s, getMinInvalid(s));

            return res;
        }

        private int getMinInvalid(String s) {
            int open = 0;
            int close = 0;

            for (char c : s.toCharArray()) {
                if (c == '(') {
                    open++;
                } else {
                    if (open > 0) {
                        close--;
                    }
                    close++;
                }
            }

            return open + close;
        }

        private void solve(String s, int invalid) {

            if (map.containsKey(s)) {
                return;
            }

            map.put(s, 1);

            if (invalid < 0) {
                return;
            }

            if (getMinInvalid(s) == 0) {
                res.add(s);
            }

            for (int i = 0; i < s.length(); i++) {

                String left = s.substring(0, i);
                String right = s.substring(i + 1);

                solve(left + right, i - 1);
            }
        }
    }

    // 10/04/2026
    class SolutionRevisedOnDayFourteen {

        public List<String> removeInvalidParentheses(String s) {
            Set<String> res = new HashSet<>();
            Set<String> visited = new HashSet<>();

            solve(s, getMinInvalid(s), res, visited);
            return new ArrayList<>(res);
        }

        private void solve(String s, int minInvaid, Set<String> res, Set<String> visited) {
            if (minInvaid < 0 || visited.contains(s))
                return;

            visited.add(s);

            // If no more removals needed, check validity and add
            if (minInvaid == 0) {
                if (getMinInvalid(s) == 0) {
                    res.add(s);
                }
                return;
            }

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                // only try removing parentheses
                if (c != '(' && c != ')')
                    continue;

                // avoid removing duplicate consecutive parentheses to prevent duplicates
                if (i > 0 && s.charAt(i - 1) == c)
                    continue;

                String next = s.substring(0, i) + s.substring(i + 1);
                solve(next, minInvaid - 1, res, visited);
            }
        }

        private int getMinInvalid(String s) {
            int open = 0;
            int close = 0;

            for (char c : s.toCharArray()) {
                if (c == '(') {
                    open++;
                } else if (c == ')') {
                    if (open > 0) {
                        open--;
                    } else {
                        close++;
                    }
                }
            }

            return open + close;
        }
    }
}
