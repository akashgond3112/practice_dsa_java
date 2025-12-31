package main.revision.october.meta.medium;

import main.revision.october.meta.ListNode;

public class AddTwoNumbers {

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            // Ek dummy node banate hain jo result linked list ka starting point hoga
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;

            // Carry ko initialize karte hain 0 se
            int carry = 0;

            // Jab tak l1, l2 ya carry 0 na ho, loop chalayenge
            while (l1 != null || l2 != null || carry != 0) {

                // Sum ko carry ke saath initialize karte hain
                int sum = carry;

                // Agar l1 null nahi hai, to uska value sum mein add karte hain aur l1 ko next
                // node pe le jaate hain
                if (l1 != null) {
                    sum += l1.val;
                    l1 = l1.next;
                }

                // Agar l2 null nahi hai, to uska value sum mein add karte hain aur l2 ko next
                // node pe le jaate hain
                if (l2 != null) {
                    sum += l2.val;
                    l2 = l2.next;
                }

                // Carry ko update karte hain (sum ko 10 se divide karke)
                carry = sum / 10;

                // Current node ke next ko ek naye node se link karte hain jo sum ka remainder
                // store karega
                current.next = new ListNode(sum % 10);

                // Current pointer ko next node pe le jaate hain
                current = current.next;
            }

            // Dummy node ke next ko return karte hain jo result linked list ka head hoga
            return dummy.next;
        }
    }

    // revised on 12/25/2025
    class SolutionRevisedOnThirdDay {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            int carry = 0;

            while (l1 != null || l2 != null || carry != 0) {
                int sum = carry;

                if (l1 != null) {
                    sum += l1.val;
                    l1 = l1.next;
                }

                if (l2 != null) {
                    sum += l2.val;
                    l2 = l2.next;
                }

                carry = sum / 10;

                current.next = new ListNode(sum % 10);
                current = current.next;
            }

            return dummy.next;
        }

    }

    // revised on 12/31/2025
    class SolutionRevisedOnSeventhDay {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            int carry = 0;

            while (l1 != null || l2 != null || carry != 0) {

                int sum = carry;

                if (l1 != null) {
                    sum += l1.val;
                    l1 = l1.next;
                }

                if (l2 != null) {
                    sum += l2.val;
                    l2 = l2.next;
                }

                carry = sum / 10;

                current.next = new ListNode(sum % 10);
                current = current.next;
            }

            return dummy.next;
        }
    }
}
