/**
 * @author akash
 * @date Jul 23, 2026
 * @time 4:46:18 PM
 */
package main.pattern.fastandslowpointer;

public class FindTheDuplicateNumber {
    class Solution {
        public int findDuplicate(int[] nums) {

            // Floyd's Tortoise and Hare (cycle detection)
            int slow = nums[0];
            int fast = nums[0];

            // find intersection
            do {
                slow = nums[slow];
                fast = nums[nums[fast]];
            } while (slow != fast);

            // find entrance to the cycle
            slow = nums[0];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }
    }
}
