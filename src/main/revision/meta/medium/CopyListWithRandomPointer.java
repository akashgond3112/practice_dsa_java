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
        TreeNode current = head;
        while (current != null) {
            TreeNode next = current.next;
            TreeNode copyNode = new TreeNode(current.val);

            // Insert copy node between current and next
            copyNode.next = next;
            current.next = copyNode;
            current = next;
        }

        // Phase 2: Set random pointers for copy nodes
        current = head;
        while (current != null) {
            TreeNode copyNode = current.next;
            if (current.random != null) {
                copyNode.random = current.random.next; // Point to copy of random node
            } else {
                copyNode.random = null;
            }
            current = current.next.next;
        }

        // Phase 3: Separate original and copy lists
        TreeNode dummy = new TreeNode(-1); // Dummy node to build copy list
        TreeNode result = dummy;
        current = head;

        while (current != null) {
            // Extract copy node
            result.next = current.next;
            result = result.next;

            // Restore original list connections
            current.next = current.next.next;
            current = current.next;
        }

        return dummy.next; // Return head of copy list
    }
}
