package main.revision.march.hard;

import java.util.*;

public class LargestRectangleInHistogram {

    // 17/04/2026
    class Solution {
        public int largestRectangleArea(int[] heights) {

            int maxArea = 0;

            Stack<Integer> st = new Stack<>();

            for (int i = 0; i < heights.length; i++) {

                while (!st.isEmpty() && heights[st.peek()] > heights[i]) {

                    int curHeight = st.pop();
                    int pse = st.isEmpty() ? -1 : st.peek();
                    maxArea = Math.max(maxArea, heights[curHeight] * (i - pse - 1));
                }

                st.push(i);
            }

            while (!st.isEmpty()) {
                int nse = heights.length;
                int curHeight = st.pop();
                int pse = st.isEmpty() ? -1 : st.peek();
                maxArea = Math.max(maxArea, heights[curHeight] * (nse - pse - 1));
            }

            return maxArea;
        }
    }

    // 20/04/2026
    class SolutionRevisedOnDayThird {
        public int largestRectangleArea(int[] heights) {
            int maxArea = 0;

            Stack<Integer> st = new Stack<>();

            for (int i = 0; i < heights.length; i++) {

                while (!st.isEmpty()) {

                    int curHeight = st.pop();
                    int pse = st.isEmpty() ? -1 : st.peek();
                    maxArea = Math.max(maxArea, heights[curHeight] * (i - pse - 1));
                }
                st.push(i);
            }

            while (!st.isEmpty()) {

                int nse = heights.length;
                int curHeight = st.pop();
                int pse = st.isEmpty() ? -1 : st.peek();
                maxArea = Math.max(maxArea, heights[curHeight] * (nse - pse - 1));
            }

            return maxArea;
        }

    }
}
