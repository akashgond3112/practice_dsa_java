/**
 * @author agond
 * @date May 31, 2025
 * @time 2:52:33 PM
 */
package main.revision.meta.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {

        Set<String> result = new HashSet<>();
        int minValid = getInvalidParenthesisCount(s);

        solve(s, minValid, result, new HashSet<>());

        return new ArrayList<>(result);
    }

    private int getInvalidParenthesisCount(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {

                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        return stack.size();
    }

    private void solve(String s, int minValid, Set<String> result, Set<String> vis) {

        if (minValid == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '(' && s.charAt(i) != ')')
                continue;

            String next = s.substring(0, i) + s.substring(i + 1);

            if (!vis.contains(next)) {
                vis.add(next);
                solve(next, minValid - 1, result, vis);
            }
        }
    }

    private boolean isValid(String s) {
        int cnt = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                cnt++;
            } else if (c == ')') {
                if (cnt == 0) {
                    return false;
                } else {
                    cnt--;
                }
            }
        }

        return cnt == 0;
    }
}
