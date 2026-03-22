package main.revision.march.hard;

public class TrappingRainWater {

    // 19/03/2026
    class SolutionOnDayFirst {
        public int trap(int[] height) {

            int total = 0;
            int leftMax = height[0];
            int rightMax = height[height.length - 1];

            int left = 1;
            int right = height.length - 2;

            while (left < right) {

                if (leftMax < rightMax) {
                    if (height[left] >= leftMax) {
                        leftMax = height[left];
                    } else {
                        total += leftMax - height[left];
                    }
                    left++;
                } else {
                    if (height[right] >= rightMax) {
                        rightMax = height[right];
                    } else {
                        total += rightMax - height[right];
                    }
                    right--;
                }

            }
            return total;
        }
    }

    // 22/03/2026
    class SolutionRevisedOnDayThird {
        public int trap(int[] height) {

            int total = 0;
            int leftMax = height[0];
            int rightMax = height[height.length - 1];

            int left = 1;
            int right = height.length - 2;

            while (left < right) {

                if (leftMax < rightMax) {

                    if (height[left] >= leftMax) {
                        leftMax = height[left];
                    } else {
                        total += leftMax - height[left];
                    }
                } else {

                    if (height[right] >= rightMax) {
                        rightMax = height[right];
                    } else {
                        total += rightMax - height[right];
                    }
                }
            }

            return total;
        }
    }
}
