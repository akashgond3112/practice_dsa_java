package main.revision.october.meta.medium;

import main.revision.october.meta.Node;

public class CopyListWithRandomPointer {

    class Solution {
        public Node copyRandomList(Node head) {

            // Base case: empty list
            if (head == null)
                return null;

            // Phase 1: Create and interweave copy nodes
            Node current = head;
            while (current != null) {
                Node next = current.next;
                Node copyNode = new Node(current.val);

                // Insert copy node between current and next
                copyNode.next = next;
                current.next = copyNode;
                current = next;
            }

            // Phase 2: Set random pointers for copy nodes
            current = head;
            while (current != null) {
                Node copyNode = current.next;
                if (current.random != null) {
                    copyNode.random = current.random.next; // Point to copy of random node
                } else {
                    copyNode.random = null;
                }
                current = current.next.next;
            }

            // Phase 3: Separate original and copy lists
            Node dummy = new Node(-1); // Dummy node to build copy list
            Node result = dummy;
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

    // revised on 04/11/2025
    class SolutionRevisonThirdDay {
        public Node copyRandomList(Node head) {

            // Base case: empty list
            if (head == null)
                return null;

            Node current = head;
            while (current != null) {

                Node next = current.next;
                Node copyNode = new Node(current.val);

                copyNode.next = next;
                current.next = copyNode;
                current = next;

            }

            current = head;
            while (current != null) {

                Node copyNode = current.next;
                if (current.random != null) {
                    copyNode.random = current.random.next;
                } else {
                    copyNode.random = null;
                }
                current = current.next.next;
            }

            Node dummy = new Node(-1);
            Node result = dummy;
            current = head;

            while (current != null) {

                result.next = current.next;
                result = result.next;

                current.next = current.next.next;
                current = current.next;
            }

            return dummy.next;
        }
    }

    // revised on 04/11/2025
    class SolutionRevisonSeventhDay {
        public Node copyRandomList(Node head) {

            if (head == null) {
                return null;
            }

            // Step 1
            Node current = head;
            while (current != null) {
                Node next = current.next;
                Node copyNode = new Node(current.val);

                copyNode.next = next;
                current.next = copyNode;
                current = next;
            }

            // step 2
            current = head;
            while (current != null) {
                Node copyNode = current.next;

                if (current.random != null) {
                    copyNode.random = current.random.next;
                } else {
                    copyNode.random = null;
                }

                current = current.next.next;
            }

            // Step 3
            Node dummy = new Node(-1);
            Node result = dummy;
            current = head;

            while (current != null) {

                result.next = current.next;
                result = result.next;

                current.next = current.next.next;
                current = current.next;
            }

            return dummy.next;
        }
    }

    // revised on 04/11/2025
    class SolutionRevisonFourteenDay {
        public Node copyRandomList(Node head) {

            if (head == null) {
                return null;
            }

            // step 1
            Node current = head;
            while (current != null) {

                Node next = current.next;
                Node copyNode = new Node(current.val);

                copyNode.next = next;
                current.next = copyNode;
                current = next;
            }

            // step 2
            current = head;
            while (current != null) {
                Node copyNode = current.next;

                if (current.random != null) {
                    copyNode.random = current.random;
                } else {
                    copyNode.random = null;
                }

                current = current.next;
            }

            Node dummy = new Node(-1);
            Node result = dummy;
            current = head;

            while (current != null) {

                result.next = current.next;
                result = result.next;

                current.next = current.next.next;
                current = current.next;
            }

            return dummy.next;
        }
    }

    // revised on 12/23/2025
    class SolutionRevisonOnDayThirty {
        public Node copyRandomList(Node head) {

            if (head == null) {
                return null;
            }

            Node current = head;
            while (current != null) {
                Node next = current.next;
                Node copyNode = new Node(current.val);

                copyNode.next = next;
                current.next = next;
                current = next;
            }

            current = head;

            while (current != null) {
                Node copyNode = current.next;

                if (current.random != null) {
                    copyNode.random = current.random.next;
                } else {
                    copyNode.random = null;
                }

                current = current.next.next;
            }

            Node dummy = new Node(-1);
            Node result = dummy;
            current = head;

            while (current != null) {
                result.next = current.next;
                result = result.next;

                current.next = current.next.next;
                current = current.next;
            }

            return dummy.next;
        }
    }
}
