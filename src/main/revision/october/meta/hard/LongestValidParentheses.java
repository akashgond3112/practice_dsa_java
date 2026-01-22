package main.revision.october.meta.hard;

import java.util.*;

public class LongestValidParentheses {

    class Solution {
        // Time O(N) Space O(N)
        public int longestValidParentheses(String s) {
            // Ek stack banate hain jo indices store karega.
            Stack<Integer> st = new Stack<>();

            // Stack mein -1 push karte hain. Yeh ek base ki tarah kaam karega
            // valid substring ki length calculate karne ke liye.
            st.push(-1);

            int len = 0;

            // String ke har character par iterate karte hain.
            for (int i = 0; i < s.length(); i++) {

                char c = s.charAt(i);

                // Agar character opening bracket '(' hai, toh uska index stack mein push karte
                // hain.
                if (c == '(') {
                    st.push(i);
                } else {
                    // Agar character closing bracket ')' hai, toh stack se pop karte hain.
                    st.pop();
                    // Agar pop karne ke baad stack empty ho jaata hai,
                    // iska matlab hai ki current ')' ka koi matching '(' nahi hai.
                    if (st.isEmpty()) {
                        // Toh current ')' ka index push karte hain, jo naye base ka kaam karega.
                        st.push(i);
                    } else {
                        // Agar stack empty nahi hai, toh ek valid substring mili hai.
                        // Uski length current index aur stack ke top element (jo ab naya base hai)
                        // ke difference se milegi.
                        int curLen = i - st.peek();
                        len = Math.max(len, curLen);
                    }
                }
            }

            return len;
        }

        // Time O(N) Space O(1)
        public int longestValidParenthesesOptimal(String s) {

            int len = 0;
            int open = 0;
            int close = 0;

            // Step 1: Left to right pass (Baayein se daayein traverse karna)
            // Open aur close parentheses ko count karte hain.
            // Jab open == close hota hai, toh length calculate karte hain.
            // Jab close > open hota hai, toh counters reset kar dete hain.
            for (int i = 0; i < s.length(); i++) {

                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close++;
                }

                // Jab open aur close barabar hote hain, tab ek valid substring milti hai.
                if (open == close) {
                    int curLen = open + close;
                    len = Math.max(len, curLen);
                } else if (close > open) {
                    // Agar close brackets open se zyada ho jaayein, toh yeh invalid state hai.
                    // Counters ko reset kar dete hain.
                    open = close = 0;
                }
            }

            // Step 2: Right to left pass (Daayein se baayein traverse karna)
            // Yeh "(()" jaise cases ko handle karta hai jo left-to-right pass mein miss ho
            // jaate hain.
            // Reverse direction mein open aur close parentheses count karte hain.
            // Jab open == close hota hai, toh length calculate karte hain.
            // Jab open > close hota hai, toh counters reset kar dete hain.
            open = close = 0;

            for (int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close++;
                }

                // Jab open aur close barabar hote hain, tab ek valid substring milti hai.
                if (open == close) {
                    int curLen = open + close;
                    len = Math.max(len, curLen);
                } else if (open > close) {
                    // Agar open brackets close se zyada ho jaayein (reverse pass mein),
                    // toh yeh invalid state hai. Counters ko reset kar dete hain.
                    open = close = 0;
                }
            }

            return len;
        }
    }

    // revised on 11/30/2025
    class SolutionRevisonOnThirdDay {

        public int longestValidParenthesesOptimal(String s) {
            int len = 0;

            // Left to right pass
            len = Math.max(len, validatePass(s, true));

            // Right to left pass
            len = Math.max(len, validatePass(s, false));

            return len;
        }

        private int validatePass(String s, boolean leftToRight) {
            int len = 0;
            int open = 0;
            int close = 0;

            int start = leftToRight ? 0 : s.length() - 1;
            int end = leftToRight ? s.length() : -1;
            int step = leftToRight ? 1 : -1;

            for (int i = start; i != end; i += step) {
                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close++;
                }

                if (open == close) {
                    int curLen = open + close;
                    len = Math.max(len, curLen);
                } else if ((leftToRight && close > open) || (!leftToRight && open > close)) {
                    open = close = 0;
                }
            }

            return len;
        }
    }

    // revised on 12/6/2025
    class SolutionRevisonOnSeventhDay {

        public int longestValidParenthesesOptimal(String s) {
            int len = 0;

            len = Math.max(len, validatePass(s, true));
            len = Math.max(len, validatePass(s, false));

            return len;
        }

        private int validatePass(String s, boolean leftToRight) {

            int len = 0;
            int open = 0;
            int close = 0;

            int start = leftToRight ? 0 : s.length() - 1;
            int end = !leftToRight ? s.length() - 1 : 0;

            int step = leftToRight ? 1 : -1;

            for (int i = start; i != end; i += step) {

                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close++;
                }

                if (open == close) {
                    int curLen = open + close;
                    len = Math.max(len, curLen);
                } else if ((leftToRight && close > open) || (!leftToRight && open > close)) {
                    open = close = 0;
                }
            }

            return len;
        }
    }

    // revised on 12/20/2025
    class SolutionRevisonOnFourteenDay {

        public int longestValidParenthesesOptimal(String s) {

            int len = 0;

            len = validatePass(s, true);
            len = validatePass(s, false);

            return len;
        }

        private int validatePass(String s, boolean leftToRight) {

            int len = 0;
            int open = 0;
            int close = 0;

            int start = leftToRight ? 0 : s.length() - 1;
            int end = leftToRight ? s.length() - 1 : 0;
            int step = leftToRight ? 1 : -1;

            for (int i = start; i != end; i += step) {

                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close++;
                }

                if (open == close) {
                    len = Math.max(len, open + close);
                } else if (leftToRight && close > open || !leftToRight && close < open) {
                    open = close = 0;
                }
            }

            return len;
        }
    }

    // revised on 12/20/2025
    class SolutionRevisonOnDayThirty {

        public int longestValidParenthesesOptimal(String s) {

            int len = 0;

            len = validatePass(s, true);
            len = validatePass(s, false);

            return len;
        }

        private int validatePass(String s, boolean leftToRight) {
            int len = 0;
            int open = 0;
            int close = 0;

            int start = leftToRight ? 0 : s.length() - 1;
            int end = leftToRight ? s.length() - 1 : 0;
            int step = leftToRight ? 1 : -1;

            for (int i = start; i != end; i += step) {
                char c = s.charAt(i);

                if (c == '(') {
                    open++;
                } else {
                    close++;
                }

                if (open == close) {
                    len = Math.max(len, open + close);
                } else if ((leftToRight && close > open) || (!leftToRight && open > close)) {
                    open = close = 0;
                }
            }

            return len;

        }
    }
}
