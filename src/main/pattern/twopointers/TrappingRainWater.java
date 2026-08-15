package main.pattern.twopointers;

public class TrappingRainWater {
    class Solution {
        public int trap(int[] height) {
            int total = 0;
            int leftMax = height[0];
            int rightMax = height[height.length - 1];

            int left = 1;
            int right = height.length - 2;
            while (left <= right) {

                if (leftMax < rightMax) {
                    leftMax = Math.max(height[left], leftMax);
                    total += leftMax - height[left];
                    left++;
                } else {
                    rightMax = Math.max(height[right], rightMax);
                    total += rightMax - height[right];
                    right--;
                }
            }

            return total;
        }
    }
}
