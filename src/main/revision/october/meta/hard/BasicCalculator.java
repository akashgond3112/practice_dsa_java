package main.revision.october.meta.hard;

import java.util.Deque;
import java.util.ArrayDeque;

public class BasicCalculator {
    static class CalculatorState {
        int output;
        int sign;

        CalculatorState(int output, int sign) {
            this.output = output;
            this.sign = sign;
        }
    }

    public int calculate(String s) {

        int output = 0;
        int currentNumber = 0;
        int sign = 1;
        Deque<CalculatorState> st = new ArrayDeque<>();

        for (char currentCharacter : s.toCharArray()) {

            if (Character.isDigit(currentCharacter)) {
                currentNumber = currentNumber * 10 + (currentCharacter - '0');
            } else if (currentCharacter == '+' || currentCharacter == '-') {
                output += currentNumber * sign;
                currentNumber = 0;
                sign = currentCharacter == '+' ? 1 : -1;
            } else if (currentCharacter == '(') {
                st.push(new CalculatorState(output, sign));
                output = 0;
                sign = 1;
            } else if (currentCharacter == ')') {
                output += currentNumber * sign;
                currentNumber = 0;
                CalculatorState prev = st.pop();
                output = prev.output + (output * prev.sign);
            }
        }

        return output + (currentNumber * sign);
    }
}
