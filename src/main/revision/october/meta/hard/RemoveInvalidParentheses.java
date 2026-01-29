package main.revision.october.meta.hard;

import java.util.*;

public class RemoveInvalidParentheses {

    class Solution {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        public List<String> removeInvalidParentheses(String s) {

            solve(s, getMinInvalid(s));

            return res;
        }

        private void solve(String s, int i) {
            // Check if already visited
            if (map.containsKey(s)) {
                return;
            }
            map.put(s, 1);

            if (i < 0) {
                return;
            }

            if (i == 0) {
                if (getMinInvalid(s) == 0) {
                    res.add(s);
                }
                return;
            }

            for (int j = 0; j < s.length(); j++) {
                String left = s.substring(0, j);
                String right = s.substring(j + 1);
                solve(left + right, i - 1);
            }
        }

        private int getMinInvalid(String s) {
            Stack<Character> st = new Stack<>();
            int i = 0;

            while (i < s.length()) {

                if (s.charAt(i) == '(') {
                    st.push('(');
                } else if (s.charAt(i) == ')') {

                    if (st.size() > 0 && st.peek() == '(') {
                        st.pop();
                    } else {
                        st.push(')');
                    }
                }
                i++;
            }

            return st.size();
        }
    }

    // revised on 31/10/2025
    class SolutionRevisionFourteenDay {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        public List<String> removeInvalidParentheses(String s) {

            solve(s, getMinInValid(s));

            return res;
        }

        private int getMinInValid(String s) {
            int open = 0;
            int close = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    open++;
                } else if (s.charAt(i) == ')') {
                    if (open > 0) {
                        open--;
                    } else {
                        close++;
                    }
                }
            }

            return open + close;
        }

        private void solve(String s, int i) {
            // Check if already visited
            if (map.containsKey(s)) {
                return;
            }
            map.put(s, 1);

            if (i < 0) {
                return;
            }

            if (getMinInValid(s) == 0) {
                if (getMinInValid(s) == 0) {
                    res.add(s);
                }
                return;
            }

            for (int j = 0; j < s.length(); j++) {
                String left = s.substring(0, j);
                String right = s.substring(j + 1);
                solve(left + right, i - 1);
            }
        }
    }

    // revised on 11/29/2025
    class SolutionRevisionThirtyDay {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        public List<String> removeInvalidParentheses(String s) {

            solve(s, getMinInValid(s));
            return res;
        }

        private int getMinInValid(String s) {
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

        private void solve(String s, int invalidPar) {

            if (map.containsKey(s))
                return;

            map.put(s, 1);

            if (invalidPar < 0) {
                return;
            }

            if (getMinInValid(s) == 0) {
                res.add(s);
            }

            for (int i = 0; i < s.length(); i++) {

                String left = s.substring(0, i);
                String right = s.substring(1 + 1);

                solve(left + right, i - 1);
            }
        }
    }
}
