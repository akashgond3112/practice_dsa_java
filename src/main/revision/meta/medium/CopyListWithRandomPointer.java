/**
 * @author agond
 * @date Jul 01, 2025
 * @time 7:45:03 PM
 */
package main.revision.meta.medium;

import main.revision.meta.TreeNode;

public class CopyListWithRandomPointer {

    public TreeNode copyRandomList(TreeNode head) {
        // Base case: empty list
        if (head == null)
            return null;

        // Phase 1: Create and interweave copy nodes
        TreeNode temp = head;
        while (temp != null) {
            TreeNode next = temp.next;
            TreeNode copyNode = new TreeNode(temp.val);

            // Insert copy node between current and next
            copyNode.next = next;
            temp.next = copyNode;
            temp = next;
        }

        // Phase 2: Set random pointers for copy nodes
        temp = head;
        while (temp != null) {
            TreeNode copyNode = temp.next;
            if (temp.random != null) {
                copyNode.random = temp.random.next; // Point to copy of random node
            } else {
                copyNode.random = null;
            }
            temp = temp.next.next;
        }

        // Phase 3: Separate original and copy lists
        TreeNode dummy = new TreeNode(-1); // Dummy node to build copy list
        TreeNode result = dummy;
        temp = head;

        while (temp != null) {
            // Extract copy node
            result.next = temp.next;
            result = result.next;

            // Restore original list connections
            temp.next = temp.next.next;
            temp = temp.next;
        }

        return dummy.next; // Return head of copy list
    }
}
