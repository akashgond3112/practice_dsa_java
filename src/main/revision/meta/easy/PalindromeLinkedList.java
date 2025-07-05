/**
 * @author agond
 * @date Jul 05, 2025
 * @time 11:42:58 AM
 */
package main.revision.meta.easy;

import main.revision.meta.TreeNode;

public class PalindromeLinkedList {

    public boolean isPalindrome(TreeNode head) {

        TreeNode fast = head;
        TreeNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        slow = reverseLinkedList(slow);
        fast = head;

        while (slow != null) {

            if (slow.val != fast.val) {
                return false;
            }

            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public static TreeNode reverseLinkedList(TreeNode head) {
        // Write your code here.
        if (head == null || head.next == null) {
            return head;
        }

        TreeNode newHead = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
