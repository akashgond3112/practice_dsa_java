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
}
