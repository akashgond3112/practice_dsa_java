/**
 * @author agond
 * @date Jul 07, 2025
 * @time 5:29:30 PM
 */
package main.revision.meta.easy;

public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;

    }

}
