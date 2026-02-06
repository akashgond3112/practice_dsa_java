package main.revision.october.meta.hard;

public class TrappingRainWater {
    public int trap(int[] height) {
        int total = 0;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];

        int left = 1;
        int right = height.length - 2;
        while (left <= right) {

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

    // Revision 18/10/2025
    /**
     * Calculates the total amount of trapped rain water given an elevation map.
     *
     * @param height an array representing the elevation map where the width of each
     *               bar is 1
     * @return the total amount of trapped rain water
     */
    public int trapRevisionSeventhDay(int[] height) {
        int total = 0;

        int leftMax = height[0];
        int rightMax = height[height.length - 1];

        int left = 1;
        int right = height.length - 2;

        while (left <= right) {

            if (leftMax < rightMax) {

                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    total += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    total += rightMax - height[right];
                }
                right--;
            }
        }

        return total;
    }

    // Revision 01/11/2025
    public static int trapRevisionFourteenDay(int[] height) {

        int total = 0;

        int leftMax = height[0];
        int rightMax = height[height.length - 1];

        int left = 1;
        int right = height.length - 2;

        int iteration = 0;
        while (left <= right) {
            System.out.println("-----------------------------Iteration Start " + iteration
                    + "------------------------------------------");
            System.out.println("left = " + left);
            System.out.println("right = " + right);
            System.out.println("leftMax = " + leftMax);
            System.out.println("rightMax = " + rightMax);
            System.out.println("height[left] = " + height[left]);
            System.out.println("height[right] = " + height[right]);

            if (leftMax < rightMax) {

                if (height[left] >= height[right]) {
                    leftMax = height[left];
                    System.out.println("New leftMax = " + leftMax);
                    System.out.println("No changes to Total.");
                } else {
                    System.out.println("Iteration " + iteration + " Total : " + total + " leftMax : " + leftMax
                            + " height[left] : "
                            + height[left]);

                    total += leftMax - height[left];
                    System.out.println("Total : " + total);
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                    System.out.println("New rightMax = " + rightMax);
                    System.out.println("No changes to Total.");
                } else {
                    System.out.println("Iteration " + iteration
                            + " Total : " + total + " rightMax : " + rightMax + " height[right] : "
                            + height[right]);
                    total += rightMax - height[right];
                    System.out.println("Total : " + total);

                }
                right--;
            }

            iteration++;
            System.out.println("-----------------------------Iteration End " + iteration
                    + "------------------------------------------");

        }

        return total;
    }

    // Revision 11/30/2025
    public int trapRevisionThirtyDay(int[] height) {
        int total = 0;
        int left = 1;
        int right = height.length - 2;

        int leftMax = height[0];
        int rightMax = height[height.length - 1];

        while (left <= right) {

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

    public static void main(String[] args) {
        // Example Inputs
        int[] example1 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };

        // Print Results
        System.out.println("Example 1: " + trapRevisionFourteenDay(example1)); // Output: 3
    }

}
