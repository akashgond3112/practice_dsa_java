/**
 * @author agond
 * @date Jun 09, 2025
 * @time 7:27:03 PM
 */
package main.revision.meta.medium;

import java.util.Stack;

public class BasicCalculatorTwo {

    // Stack Approach
    public int calculate(String s) {
        int currentNo = 0;
        char lastOperation = '+';
        char currentChar;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length() - 1; i++) {

            currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) {

                currentNo = (currentNo * 10) + (currentChar - '0');
            }

            if (!Character.isDigit(currentChar) && currentChar != ' ' || i == s.length() - 1) {

                if (lastOperation == '-') {
                    stack.push(-currentNo);
                } else if (lastOperation == '+') {
                    stack.push(currentNo);
                } else if (lastOperation == '*') {
                    int tmp = stack.pop();
                    stack.push(tmp * currentNo);
                } else if (lastOperation == '/') {
                    int tmp = stack.pop();
                    stack.push(tmp / currentNo);
                }
                lastOperation = currentChar;
                currentNo = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public int calculateNew(String s) {

        int currentNo = 0;
        char currentChar;

        char lastOperation = '+';
        int lastNo = 0;

        int result = 0;

        for (int i = 0; i < s.length() - 1; i++) {

            currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) {

                currentNo = (currentNo * 10) + (currentChar - '0');
            }

            if (!Character.isDigit(currentChar) && currentChar != ' ' || i == s.length() - 1) {

                if (lastOperation == '-' || lastOperation == '+') {
                    result += lastNo;
                    lastNo = (lastOperation == '+') ? currentNo : -currentNo;
                } else if (lastOperation == '*') {
                    lastNo = lastNo * currentNo;
                } else if (lastOperation == '/') {
                    lastNo = lastNo / currentNo;
                }
                lastOperation = currentChar;
                currentNo = 0;
            }
        }

        return result;
    }
}
