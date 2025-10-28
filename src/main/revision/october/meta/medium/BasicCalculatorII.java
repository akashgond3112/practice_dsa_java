package main.revision.october.meta.medium;

import java.util.*;

public class BasicCalculatorII {

    public int calculate(String s) {

        int currentNumber = 0;
        char currentCharacter;
        char operator = '+';
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            currentCharacter = s.charAt(i);

            if (Character.isDigit(currentCharacter)) {
                currentNumber = currentNumber * 10 + (currentCharacter - '0');
            }

            if (!Character.isDigit(currentCharacter) && currentCharacter != ' ' || i == s.length() - 1) {

                if (operator == '-') {
                    stack.push(-currentNumber);
                } else if (operator == '+') {
                    stack.push(currentNumber);
                } else if (operator == '*') {
                    int top = stack.pop();
                    stack.push(top * currentNumber);
                } else if (operator == '/') {
                    int top = stack.pop();
                    stack.push(top / currentNumber);
                }
            }
            currentNumber = 0;
            operator = currentCharacter;
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    /**
     * Evaluates a mathematical expression string containing non-negative integers
     * and the operators +, -, *, /.
     * The expression may contain spaces and is evaluated using operator precedence.
     *
     * @param s the input string representing the mathematical expression
     * @return the result of evaluating the expression
     */
    public int calculateOptimised(String s) {

        int currentNumber = 0;
        char currentCharacter;
        char operator = '+';
        int result = 0;
        int lastNumber = 0;
        for (int i = 0; i < s.length(); i++) {
            currentCharacter = s.charAt(i);

            if (Character.isDigit(currentCharacter)) {
                currentNumber = currentNumber * 10 + currentCharacter - '0';
            }

            if (!Character.isDigit(currentCharacter) && currentCharacter != ' ' || i == s.length() - 1) {
                if (operator == '-') {
                    result += lastNumber;
                    lastNumber = -currentNumber;
                } else if (operator == '+') {
                    result += lastNumber;
                    lastNumber = currentNumber;
                } else if (operator == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operator == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                currentNumber = 0;
                operator = currentCharacter;
            }

        }

        return result + lastNumber;
    }

    // Revision on 20/10/2025
    public int calculateOptimisedRevisionThirdDay(String s) {

        int currentNumber = 0;
        char currentCharacter;
        char operator = '+';
        int result = 0;
        int lastNumber = 0;

        for (int i = 0; i < s.length(); i++) {
            currentCharacter = s.charAt(i);

            if (Character.isDigit(currentCharacter)) {
                currentNumber = currentNumber * 10 + currentCharacter - '0';
            }

            if (!Character.isDigit(currentCharacter) && currentCharacter != ' ' || i == s.length() - 1) {

                if (operator == '+') {
                    result += lastNumber;
                    lastNumber = currentNumber;
                } else if (operator == '-') {
                    result += lastNumber;
                    lastNumber = -currentNumber;
                } else if (operator == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operator == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                currentNumber = 0;
                operator = currentCharacter;
            }

        }

        return result + lastNumber;
    }

    /**
     * Evaluates a mathematical expression string containing non-negative integers
     * and the operators +, -, *, /.
     * The expression may contain spaces and is evaluated using operator precedence.
     *
     * @param s the input string representing the mathematical expression
     * @return the result of evaluating the expression
     */
    // Revision on 26/10/2025
    public int calculateOptimisedRevisionFourteenDay(String s) {

        int currentNumber = 0;
        char currentCharacter;
        char operator = '+';
        int result = 0;
        int lastNumber = 0;

        for (int i = 0; i < s.length(); i++) {
            currentCharacter = s.charAt(i);

            if (Character.isDigit(currentCharacter)) {
                currentNumber = currentNumber * 10 + currentCharacter - '0';
            }

            if (!Character.isDigit(currentCharacter) && currentCharacter != ' ' || i == s.length() - 1) {

                if (operator == '+') {
                    result += lastNumber;
                    lastNumber = currentNumber;
                } else if (operator == '-') {
                    result -= lastNumber;
                    lastNumber = -currentNumber;
                } else if (operator == '*') {
                    lastNumber *= currentNumber;
                } else if (operator == '/') {
                    lastNumber /= currentNumber;
                }

                currentNumber = 0;
                operator = currentCharacter;
            }
        }

        return result + lastNumber;
    }
}
