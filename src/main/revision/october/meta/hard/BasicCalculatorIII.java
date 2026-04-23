package main.revision.october.meta.hard;

import java.util.Stack;

public class BasicCalculatorIII {

    class CalculatorState {
        int output;
        int sign;
        char lastOp; // Last operator before parenthesis

        CalculatorState(int output, int sign, char lastOp) {
            this.output = output;
            this.sign = sign;
            this.lastOp = lastOp;
        }
    }

    class SolutionRevisedWithMultiplyDivide {
        public int calculate(String s) {
            int output = 0;
            int currentNumber = 0;
            int lastNumber = 0; // For handling * and /
            char operator = '+';
            Stack<CalculatorState> st = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (Character.isDigit(c)) {
                    currentNumber = currentNumber * 10 + (c - '0');
                }

                if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || i == s.length() - 1) {

                    if (c == ' ')
                        continue;

                    // Process based on previous operator
                    if (operator == '+') {
                        output += lastNumber;
                        lastNumber = currentNumber;
                    } else if (operator == '-') {
                        output += lastNumber;
                        lastNumber = -currentNumber;
                    } else if (operator == '*') {
                        lastNumber = lastNumber * currentNumber;
                    } else if (operator == '/') {
                        lastNumber = lastNumber / currentNumber;
                    }

                    if (c == '(') {
                        // Save state before entering parenthesis
                        CalculatorState cs = new CalculatorState(output, lastNumber, operator);
                        st.push(cs);
                        output = 0;
                        lastNumber = 0;
                        operator = '+';
                    } else if (c == ')') {
                        // Restore state and apply result
                        output += lastNumber;
                        CalculatorState prev = st.pop();
                        currentNumber = output;
                        output = prev.output;
                        lastNumber = prev.sign;
                        operator = prev.lastOp;

                        // Apply the result from parenthesis
                        if (operator == '+') {
                            output += lastNumber;
                            lastNumber = currentNumber;
                        } else if (operator == '-') {
                            output += lastNumber;
                            lastNumber = -currentNumber;
                        } else if (operator == '*') {
                            lastNumber = lastNumber * currentNumber;
                        } else if (operator == '/') {
                            lastNumber = lastNumber / currentNumber;
                        }
                        operator = '+';
                    } else {
                        operator = c;
                    }

                    currentNumber = 0;
                }
            }

            return output + lastNumber;
        }
    }
}
