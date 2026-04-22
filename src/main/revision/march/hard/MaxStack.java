package main.revision.march.hard;

import java.util.EmptyStackException;
import java.util.Stack;

public class MaxStack {

    static class Pair<I extends Number, I1 extends Number> {
        int max;
        int val;

        public Pair(int max, int val) {
            this.max = max;
            this.val = val;
        }

        public class Solution {

            Stack<Pair<Integer, Integer>> stack = new Stack<>();

            // Push method: Ek naya element stack mein daalna
            public void push(int val) {
                if (stack.isEmpty()) {
                    // Agar stack khali hai, toh naya element ko max aur value dono set karke daal
                    // do
                    stack.push(new Pair<>(val, val));
                } else {
                    // Agar stack khali nahi hai, toh current max nikal ke naye element ke saath
                    // compare karo
                    int curMax = stack.peek().max;
                    stack.push(new Pair<>(val, Math.max(val, curMax)));
                }
            }

            // Pop method: Stack ke top element ko hataana
            public void pop() {
                if (stack.isEmpty()) {
                    // Agar stack khali hai, toh exception throw karo
                    throw new EmptyStackException();
                } else {
                    // Top element ko hata do
                    stack.pop();
                }
            }

            // Top method: Stack ke top element ki value return karna
            public int top() {
                if (stack.isEmpty()) {
                    // Agar stack khali hai, toh exception throw karo
                    throw new EmptyStackException();
                } else {
                    // Top element ki value return karo
                    return stack.peek().val;
                }
            }

            // getMax method: Stack mein ab tak ka maximum element return karna
            public int getMax() {
                if (stack.isEmpty()) {
                    // Agar stack khali hai, toh exception throw karo
                    throw new EmptyStackException();
                }
                // Top element ka max value return karo
                return stack.peek().max;
            }

            // popMax method: Stack se maximum element ko hataana aur return karna
            public int popMax() {

                if (stack.isEmpty()) {
                    // Agar stack khali hai, toh exception throw karo
                    throw new EmptyStackException();
                }

                int maxVal = getMax();
                Stack<Integer> buffer = new Stack<>();

                while (!stack.isEmpty() && stack.peek().val != maxVal) {
                    buffer.push(stack.pop().val);
                }

                if (!stack.isEmpty()) {
                    stack.pop();
                }

                while (!buffer.isEmpty()) {
                    push(buffer.pop());
                }

                return maxVal;
            }
        }
    }
}
