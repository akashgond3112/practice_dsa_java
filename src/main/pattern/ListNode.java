/**
 * @author akash
 * @date Jul 22, 2026
 * @time 6:50:35 PM
 */
package main.pattern;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
