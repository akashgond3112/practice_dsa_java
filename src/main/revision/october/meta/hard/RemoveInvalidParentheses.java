package main.revision.october.meta.hard;

import java.util.*;

public class RemoveInvalidParentheses {

    List<String> res = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();

    public List<String> removeInvalidParentheses(String s) {

        solve(s, getMinInValid(s));

        return res;
    }

    private int getMinInValid(String s) {
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

    private void solve(String s, int j) {

        if (map.get(s) != 0) {
            return;
        } else {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        if (j < 0) {
            return;
        }

        if (j == 0) {
            if (getMinInValid(s) == 0) {
                res.add(s);
            }
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i + 1);
            solve(left + right, j - 1);
        }
        return;
    }
}
