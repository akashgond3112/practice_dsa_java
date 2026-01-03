package main.revision.october.meta.medium;

import java.util.*;

class MinimumRemoveToMakeValidParentheses {

    static String minRemoveToMakeValidParenthesisBruteForce(String s) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    s = s.substring(0, i) + '#' + s.substring(i + 1);
                } else {
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            s = s.substring(0, idx) + '#' + s.substring(idx + 1);
        }

        String ans = "";

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) != '#') {
                ans = ans + s.charAt(i);
            }
        }

        return ans;
    }

    static String minRemoveToMakeValidParenthesisOptimal(String s) {

        int count = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                if (count == 0) {
                    s = s.substring(0, i) + '#' + s.substring(i + 1);
                } else {
                    count--;
                }
            }
        }

        String ans = "";

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) != '#') {
                ans = ans + s.charAt(i);
            }
        }

        return ans;
    }

    // Revised on 12.10.2025
    public class SolutionRevisionSeventhDay {
        static String minRemoveToMakeValidParenthesisOptimal(String s) {

            int count = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    count++;
                } else if (s.charAt(i) == ')') {

                    if (count == 0) {
                        s = s.substring(0, i) + '#' + s.substring(i + 1);
                    } else {
                        count--;
                    }
                }
            }

            String ans = "";
            for (int i = 0; i < s.length(); i++) {

                if (s.charAt(i) != '#') {
                    ans = ans + s.charAt(i);
                }
            }

            return s;
        }

    }

    // Revised on 26.10.2025
    public class SolutionRevisionFourteenDay {
        static String minRemoveToMakeValidParenthesisOptimal(String s) {

            int count = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    count++;
                } else if (s.charAt(i) == ')') {
                    if (count == 0) {
                        s = s.substring(0, i) + "#" + s.substring(i + 1);
                    } else {
                        count--;
                    }
                }
            }

            String ans = "";
            for (int i = 0; i < s.length(); i++) {

                if (s.charAt(i) != '#') {
                    ans = ans + s.charAt(i);
                }
            }

            return s;
        }
    }

    // Revised on 11/24/2025
    public class SolutionRevisionThirtyDay {
        static String minRemoveToMakeValidParenthesisOptimal(String s) {

            int count = 0;

            for (int i = 0; i < s.length(); i++) {

                if (s.charAt(i) == '(') {
                    count++;
                } else if (s.charAt(i) == ')') {

                    if (count == 0) {
                        s = s.substring(0, i) + '#' + s.substring(i + 1);
                    } else {
                        count--;
                    }
                }
            }

            String ans = "";

            for (int i = 0; i < s.length(); i++) {

                if (s.charAt(i) != '#') {
                    ans = ans + s.charAt(i);
                }
            }

            return ans;
        }
    }
}