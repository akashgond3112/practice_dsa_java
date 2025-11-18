package main.revision.october.meta.easy;

public class SquaresOfASortedArray {

    public int[] sortedSquares(int[] nums) {

        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }

        int head = 0;
        int tail = res.length - 1;

        for (int j = nums.length - 1; j >= 0; j--) {

            if (nums[head] > nums[tail]) {
                res[j] = nums[head];
                head++;
            } else {
                res[j] = nums[tail];
                tail--;
            }
        }

        return res;
    }

    // Revised on 10/22/2025
    public int[] sortedSquaresRevisionThirdDay(int[] nums) {

        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }

        int head = 0;
        int tail = res.length - 1;

        for (int j = nums.length - 1; j >= 0; j--) {

            if (nums[head] > nums[tail]) {
                res[j] = nums[head];
                head++;
            } else {
                res[j] = nums[tail];
                tail--;
            }
        }

        return res;
    }

    // Revised on 10/28/2025
    public int[] sortedSquaresRevisionSeventhDay(int[] nums) {

        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }

        int head = 0;
        int tail = res.length - 1;

        for (int j = nums.length - 1; j >= 0; j--) {

            if (nums[head] > nums[tail]) {
                res[j] = nums[head];
                head++;
            } else {
                res[j] = nums[tail];
                tail--;
            }
        }

        return res;
    }

    // Revised on 11/11/2025
    public int[] sortedSquaresRevisionFourteenDay(int[] nums) {

        int res[] = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }

        int head = 0;
        int tail = nums.length;

        for (int i = nums.length - 1; i > 0; i--) {

            if (nums[head] > nums[tail]) {
                res[i] = nums[head];
                head++;
            } else {
                res[i] = nums[tail];
                tail--;
            }
        }

        return res;
    }
}
