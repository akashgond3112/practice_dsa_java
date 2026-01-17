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

    public class Solution {
        public int calculate(String s) {

            int output = 0; // Final answer store karne ke liye
            int currentNumber = 0; // Abhi tak ka number store karne ke liye
            int sign = 1; // Current sign (+1 ya -1) track karne ke liye
            Deque<CalculatorState> st = new ArrayDeque<>(); // Stack jaisa structure, brackets ke liye

            for (char currentCharacter : s.toCharArray()) {

                if (Character.isDigit(currentCharacter)) {
                    // Agar digit mila toh currentNumber ko update karo
                    currentNumber = currentNumber * 10 + (currentCharacter - '0');
                } else if (currentCharacter == '+' || currentCharacter == '-') {
                    // Agar + ya - mila toh pehle wale number ko output me add karo
                    output += currentNumber * sign;
                    currentNumber = 0; // Naya number start hoga
                    sign = currentCharacter == '+' ? 1 : -1; // Sign update karo
                } else if (currentCharacter == '(') {
                    // Agar '(' mila toh ab tak ka output aur sign stack me daal do
                    st.push(new CalculatorState(output, sign));
                    output = 0; // Naya bracket hai, output reset karo
                    sign = 1; // Sign bhi reset karo
                } else if (currentCharacter == ')') {
                    // Agar ')' mila toh currentNumber ko output me add karo
                    output += currentNumber * sign;
                    currentNumber = 0;
                    // Stack se pehle ka output aur sign nikaalo
                    CalculatorState prev = st.pop();
                    // Bracket ke andar ka output, pehle ke output aur sign ke saath combine karo
                    output = prev.output + (output * prev.sign);
                }
            }

            // Loop ke baad, agar koi number bacha hai toh usko bhi add karo
            return output + (currentNumber * sign);
        }
    }

    // Revised on 27/10/2025
    public class SolutionRevisionThirdDay {
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

    // Revised on 02/11/2025
    public class SolutionRevisionSeventhDay {
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
                    CalculatorState prev = st.poll();
                    output = prev.output + (output * prev.sign);
                }
            }

            return output + (currentNumber * sign);
        }
    }

    // Revised on 11/16/2025
    public class SolutionRevisionFourteenDay {
        public int calculate(String s) {

            int output = 0;
            int currentNumber = 0;
            int sign = 1;

            Deque<CalculatorState> dq = new ArrayDeque<>();

            for (char c : s.toCharArray()) {

                if (Character.isDigit(c)) {
                    currentNumber += c * 10 + c - '0';
                } else if (c == '+' || c == '-') {
                    output += currentNumber * sign;
                    currentNumber = 0;
                    sign = c == '+' ? 1 : -1;
                } else if (c == '(') {

                    dq.push(new CalculatorState(output, sign));
                    output = 0;
                    sign = 1;
                } else if (c == ')') {

                    output += currentNumber * sign;
                    currentNumber = 0;

                    CalculatorState cur = dq.poll();
                    output = cur.output + (output * cur.sign);
                }
            }

            return output + (currentNumber * sign);
        }
    }

    // Revised on 12/15/2025
    public class SolutionRevisionThirtyDay {
        public int calculate(String s) {
            int output = 0;
            int currentNumber = 0;
            int sign = 1;
            Deque<CalculatorState> st = new ArrayDeque<>();

            for (char c : s.toCharArray()) {

                if (Character.isDigit(c)) {
                    currentNumber = currentNumber * 10 + (c - '0');
                } else if (c == '+' || c == '-') {

                    output += currentNumber * sign;
                    currentNumber = 0;
                    sign = c == '+' ? 1 : -1;
                } else if (c == '(') {

                    st.push(new CalculatorState(output, sign));
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
}
