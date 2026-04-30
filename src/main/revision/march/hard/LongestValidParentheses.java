package main.revision.march.hard;

public class LongestValidParentheses {

    // 21/04/2026
    class Solution {
        public int longestValidParentheses(String s) {

            int len = 0;
            int open = 0;
            int close = 0;

            for (int i = 0; i < s.length(); i++) {

                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close++;
                }

                if (open == close) {
                    len = Math.max(len, open + close);
                } else if (open > close) {
                    open = close = 0;
                }
            }

            for (int i = s.length() - 1; i >= 0; i--) {

                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close++;
                }

                if (open == close) {
                    len = Math.max(len, open + close);
                } else if (open > close) {
                    open = close = 0;
                }
            }

            return len;
        }
    }

    // 24/04/2026
    class SolutionRevisedOnDayThird {
        public int longestValidParentheses(String s) {
            int len = 0;
            int open = 0;
            int close = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close--;
                }
                if (open == close) {
                    len = Math.max(len, open + close);
                } else if (open > close) {
                    open = close = 0;
                }
            }

            for (int i = s.length() - 1; i > 0; i--) {
                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close--;
                }
                if (open == close) {
                    len = Math.max(len, open + close);
                } else if (open > close) {
                    open = close = 0;
                }
            }

            return len;
        }
    }

    // 30/04/2026
    class SolutionRevisedOnDaySeventh {
        public int longestValidParentheses(String s) {
            int len = 0;
            int open = 0;
            int close = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close--;
                }
                if (open == close) {
                    len = Math.max(len, open + close);
                } else if (open > close) {
                    open = close = 0;
                }
            }

            for (int i = s.length() - 1; i > 0; i--) {
                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close--;
                }
                if (open == close) {
                    len = Math.max(len, open + close);
                } else if (open > close) {
                    open = close = 0;
                }
            }

            return len;
        }
    }
}
