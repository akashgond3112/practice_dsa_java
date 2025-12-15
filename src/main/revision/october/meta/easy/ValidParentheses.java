package main.revision.october.meta.easy;

import java.util.Stack;

public class ValidParentheses {

    class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            for (char c : s.toCharArray()) {
                if (c == '(' || c == '{' || c == '[') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char top = stack.pop();
                    if (c == ')' && top != '(') {
                        return false;
                    }
                    if (c == '}' && top != '{') {
                        return false;
                    }
                    if (c == ']' && top != '[') {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
    }

    // Revised on 27/10/2025
    class SolutionRevisionThirdDay {
        public boolean isValid(String s) {
            Stack<Character> st = new Stack<>();

            for (char c : s.toCharArray()) {

                if (c == '(' || c == '{' || c == '[') {
                    st.push(c);
                } else {
                    if (st.isEmpty()) {
                        return false;
                    }

                    char cur = st.pop();

                    if (c == ')' && cur != '(')
                        return false;
                    if (c == '}' && cur != '{')
                        return false;
                    if (c == ']' && cur != '[')
                        return false;
                }
            }

            return st.isEmpty();
        }
    }

    // Revised on 27/10/2025
    class SolutionRevisionFourteenDay {
        public boolean isValid(String s) {
            Stack<Character> st = new Stack<>();

            for (char c : s.toCharArray()) {

                if (c == '(' || c == '{' || c == '[') {
                    st.push(c);
                } else {
                    if (st.isEmpty()) {
                        return false;
                    }

                    char cur = st.pop();

                    if (c == ')' && cur != '(')
                        return false;
                    if (c == '}' && cur != '[')
                        return false;
                    if (c == ']' && cur != '[')
                        return false;
                }
            }

            return st.isEmpty();
        }
    }

    // Revised on 12/15/2025
    class SolutionRevisionThirtyDay {
        public boolean isValid(String s) {
            Stack<Character> st = new Stack<>();

            for (char c : s.toCharArray()) {
                if (c == '(' || c == '{' || c == '[') {
                    st.push(c);
                } else {
                    if (st.isEmpty()) {
                        return false;
                    }
                    char ch = st.pop();

                    if (c == ')' || ch != '(') {
                        return false;
                    }

                    if (c == '}' || ch != '{') {
                        return false;
                    }

                    if (c == ']' || ch != '[') {
                        return false;
                    }
                }
            }

            return st.isEmpty();
        }
    }
}
