package main.revision.march.hard;

import java.util.*;

public class BasicCalculator {

    static class CalculatorState {
        int sign;
        int output;

        CalculatorState(int sign, int output) {
            this.sign = sign;
            this.output = output;
        }
    }

    // 02/04/2026
    class SolutionOnDayFirst {
        public int calculate(String s) {

            int output = 0;
            int currentNumber = 0;
            int sign = 1;

            Stack<CalculatorState> st = new Stack<>();

            for (char c : s.toCharArray()) {

                if (Character.isDigit(c)) {
                    currentNumber = currentNumber * 10 + (c - '0');
                } else if (c == '+' || c == '-') {

                    output += currentNumber * sign;
                    currentNumber = 0;
                    sign = c == '+' ? 1 : -1;
                } else if (c == '(') {
                    st.push(new CalculatorState(sign, output));
                    output = 0;
                    sign = 1;
                } else if (c == ')') {
                    output += currentNumber * sign;
                    currentNumber = 0;

                    CalculatorState prev = st.pop();

                    output = prev.output + (output * prev.sign);
                }
            }

            return output + (currentNumber * sign);
        }
    }

    // 05/04/2026
    class SolutionRevisedOnDayThird {
        public int calculate(String s) {
            int output = 0;
            int currentNumber = 0;
            int sign = 1;

            Stack<CalculatorState> st = new Stack<>();

            for (char c : s.toCharArray()) {

                if (Character.isDigit(c)) {
                    currentNumber = currentNumber * 10 + c - '0';
                } else if (c == '+' || c == '-') {
                    output = currentNumber * sign;
                    currentNumber = 0;
                    sign = c == '+' ? 1 : -1;
                } else if (c == '(') {
                    st.push(new CalculatorState(sign, output));
                    output = 0;
                    sign = 1;
                } else if (c == ')') {
                    output += currentNumber * sign;
                    currentNumber = 0;

                    CalculatorState prev = st.pop();

                    output += prev.output + (output * prev.sign);

                }
            }

            return output + (currentNumber * sign);
        }
    }

    // 11/04/2026
    class SolutionRevisedOnDaySeventh {
        public int calculate(String s) {

            int output = 0;
            int currentNumber = 0;
            int sign = 1;
            Stack<CalculatorState> st = new Stack<>();

            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    currentNumber += c * 10 + c - '0';
                } else if (c == '+' || c == '-') {
                    output = currentNumber * sign;
                    currentNumber = 0;
                    sign = sign == '+' ? 1 : -1;
                } else if (c == '(') {
                    st.push(new CalculatorState(sign, output));
                    output = 0;
                    sign = 1;
                } else if (c == ')') {
                    output += currentNumber * sign;
                    currentNumber = 0;
                    CalculatorState prev = st.pop();
                    output += prev.output + (output * prev.sign);
                }
            }

            return output + (currentNumber * sign);
        }
    }

    // 25/04/2026
    class SolutionRevisedOnDayFourteen {
        public int calculate(String s) {

            int output = 0;
            int currentNumber = 0;
            int sign = 1;
            Stack<CalculatorState> st = new Stack<>();

            for (char c : s.toCharArray()) {

                if (Character.isDigit(c)) {
                    currentNumber += c * 10 + (c - '0');
                } else if (c == '+' || c == '-') {
                    output += currentNumber * sign;
                    currentNumber = 0;
                    sign = sign == '+' ? 1 : -1;
                } else if (c == '(') {
                    st.push(new CalculatorState(sign, output));
                    output = 0;
                    sign = 1;
                } else if (c == ')') {
                    CalculatorState prevState = st.pop();
                    output += currentNumber * sign;
                    currentNumber = 0;

                    output += prevState.output + (output * prevState.sign);
                }
            }

            return output + (currentNumber * sign);
        }
    }
}
