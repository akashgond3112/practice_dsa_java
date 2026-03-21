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
}
