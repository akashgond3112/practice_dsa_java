package main.revision.meta.medium;

/**
 * @author agond
 * @date May 26, 2025
 * @time 4:25:14 PM
 */
public class MinimumRemoveToMakeValidParentheses {

    public String minRemoveToMakeValid(String s) {

        int openCount = 0;

        int n = s.length();
        char[] chars = s.toCharArray();
        // First pass: remove invalid closing parentheses
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (c == '(') {
                openCount++;
            } else if (c == ')') {
                if (openCount == 0) {
                    chars[i] = '#'; // Mark invalid closing parenthesis
                } else {
                    openCount--;
                }
            }
        }

        // Reset openCount for the second pass
        openCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = chars[i];
            if (c == ')') {
                openCount++;
            } else if (c == '(') {
                if (openCount == 0) {
                    chars[i] = '#'; // Mark invalid opening parenthesis
                } else {
                    openCount--;
                }
            }
        }

        // Second pass: remove invalid opening parentheses
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (c != '#') {
                result.append(c);
            }
        }

        return result.toString();
    }
}
