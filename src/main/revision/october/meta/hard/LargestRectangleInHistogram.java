package main.revision.october.meta.hard;

import java.util.*;

public class LargestRectangleInHistogram {
    class Solution {
        public int largestRectangleArea(int[] heights) {

            int maxArea = 0;

            // Stack histogram bars ke indices store karta hai
            // Main idea: Hum bars ko increasing height order mein maintain karte hain
            Stack<Integer> st = new Stack<>();

            // Step 1: Har bar ko left se right tak process karo
            for (int i = 0; i < heights.length; i++) {

                // Step 2: Jab humein stack ke top se chota bar milta hai,
                // toh humein stack mein pade bars ke liye "next smaller element" (NSE) mil gaya
                // hai
                while (!st.isEmpty() && heights[st.peek()] > heights[i]) {

                    // Step 3: Bar ko pop karo aur uska maximum rectangle calculate karo
                    int height = st.peek(); // Jis bar ka area calculate karna hai uska index
                    st.pop();

                    // Step 4: "previous smaller element" (PSE) dhoondo
                    // Stack mein neeche wala bar PSE hota hai (left mein pehla chota bar)
                    // Agar stack khali hai, toh left mein koi chota bar nahi hai, isliye PSE = -1
                    int pse = st.isEmpty() ? -1 : st.peek();

                    // Step 5: Is bar ko height maan kar area calculate karo
                    // Width = (i - pse - 1)
                    // - 'i' NSE hai (right boundary, exclusive)
                    // - 'pse' PSE hai (left boundary, exclusive)
                    // - PSE aur NSE ke beech sabki height current bar se badi ya barabar hai
                    maxArea = Math.max(maxArea, heights[height] * (i - pse - 1));
                }

                // Step 6: Current bar ka index stack mein push karo
                st.push(i);
            }

            // Step 7: Stack mein bache hue bars ko process karo
            // In bars ka koi NSE nahi hai (ye right edge tak extend ho sakte hain)
            while (!st.isEmpty()) {
                int nse = heights.length; // NSE array ke bahar hai (right edge)
                int height = st.peek();
                st.pop();

                int pse = st.isEmpty() ? -1 : st.peek();

                // Width ka calculation wahi hai, lekin NSE array ki length hai
                maxArea = Math.max(maxArea, heights[height] * (nse - pse - 1));
            }

            return maxArea;
        }
    }

    // revised on 11/24/2025
    class SolutionRevisedOnThirdDay {
        public int largestRectangleArea(int[] heights) {

            int maxArea = 0;

            Stack<Integer> st = new Stack<>();

            for (int i = 0; i < heights.length; i++) {

                while (!st.isEmpty() && heights[st.peek()] > heights[i]) {

                    int height = heights[i];
                    st.pop();

                    int pse = st.isEmpty() ? 0 : st.peek();

                    maxArea = Math.max(maxArea, heights[height] * (i - pse - 1));
                }
                st.push(i);
            }

            while (!st.isEmpty()) {
                int nse = heights.length;

                int height = st.peek();
                st.pop();

                int pse = st.isEmpty() ? -1 : st.peek();

                maxArea = Math.max(maxArea, heights[height] * (nse - pse - 1));

            }

            return maxArea;
        }
    }

    // revised on 11/30/2025
    class SolutionRevisedOnSeventhDay {
        public int largestRectangleArea(int[] heights) {

            int maxArea = 0;

            Stack<Integer> st = new Stack<>();

            for (int i = 0; i < heights.length; i++) {

                while (!st.isEmpty() && heights[st.peek()] > heights[i]) {

                    int height = st.pop();

                    int pse = st.isEmpty() ? -1 : st.peek();

                    maxArea = Math.max(maxArea, heights[height] * (i - pse - 1));
                }

                st.push(i);
            }

            while (!st.isEmpty()) {
                int nse = heights.length;
                int height = st.peek();
                st.pop();

                int pse = st.isEmpty() ? -1 : st.peek();
                maxArea = Math.max(maxArea, heights[height] * (nse - pse - 1));
            }

            return maxArea;
        }
    }

    // revised on 12/14/2025
    class SolutionRevisedOnFourteenDay {
        public int largestRectangleArea(int[] heights) {

            int maxArea = 0;

            Stack<Integer> st = new Stack<>();

            for (int i = 0; i < heights.length; i++) {

                while (!st.isEmpty() && heights[st.peek()] > heights[i]) {

                    int height = st.pop();

                    int pse = st.isEmpty() ? -1 : st.peek();

                    maxArea = Math.max(maxArea, heights[height] * (i - pse - 1));
                }

                st.push(i);
            }

            while (!st.isEmpty()) {
                int nse = heights.length;
                int height = st.pop();

                int pse = st.isEmpty() ? -1 : st.peek();

                maxArea = Math.max(maxArea, heights[height] * (nse - pse - 1));

            }
            return maxArea;

        }
    }

    // revised on 1/12/2026
    class SolutionRevisedOnDayThirty {
        public int largestRectangleArea(int[] heights) {

            int maxArea = 0;

            Stack<Integer> st = new Stack<>();

            for (int i = 0; i < heights.length; i++) {

                while (!st.isEmpty() && heights[st.peek()] > heights[i]) {

                    int height = st.pop();

                    int pse = st.isEmpty() ? -1 : st.peek();

                    int width = i - pse - 1;
                    maxArea = Math.max(maxArea, heights[height] * width);
                }

                st.push(i);
            }

            while (!st.isEmpty()) {
                int nse = heights.length;
                int height = st.pop();

                int pse = st.isEmpty() ? -1 : st.peek();

                int width = nse - pse - 1;
                maxArea = Math.max(maxArea, heights[height] * width);
            }

            return maxArea;
        }
    }
}
