/**
 * @author agond
 * @date Jun 29, 2025
 * @time 12:24:11 PM
 */
package main.revision.meta.easy;

public class MoveZeroes {

    public static void moveZeroes(int[] nums) {

        int insertPosition = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != 0) {
                nums[insertPosition] = nums[i];
                insertPosition++;
            }
        }

        while (insertPosition < nums.length) {
            nums[insertPosition++] = 0;
        }

    }

}
