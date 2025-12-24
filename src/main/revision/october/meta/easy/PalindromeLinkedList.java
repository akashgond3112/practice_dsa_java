package main.revision.october.meta.easy;

import main.revision.october.meta.ListNode;

public class PalindromeLinkedList {
    class Solution {
        public boolean isPalindrome(ListNode head) {

            // Step 1: Fast and slow pointers ko initialize karo
            ListNode fast = head;
            ListNode slow = head;

            // Step 2: Fast pointer ko 2 steps aur slow pointer ko 1 step move karte raho
            // Jab tak fast ya fast.next null na ho jaye
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            // Step 3: Agar fast null nahi hai, iska matlab linked list odd length ki hai
            // Toh slow ko ek step aage badhao
            if (fast != null) {
                slow = slow.next;
            }

            // Step 4: Slow pointer ke baad wali linked list ko reverse karo
            slow = reverseLinkedList(slow);

            // Step 5: Fast pointer ko wapas head pe le aao
            fast = head;

            // Step 6: Reverse linked list aur original linked list ko compare karo
            while (slow != null) {

                // Agar kisi bhi node ka value match nahi karta, toh palindrome nahi hai
                if (slow.val != fast.val) {
                    return false;
                }

                // Dono pointers ko aage badhao
                slow = slow.next;
                fast = fast.next;
            }

            // Step 7: Agar sab match kar gaya, toh palindrome hai
            return true;
        }

        private ListNode reverseLinkedList(ListNode slow) {

            // Base case: Agar slow null ya last node hai, toh wahi return karo
            if (slow == null || slow.next == null) {
                return slow;
            }

            // Recursive call: Next node ko reverse karo
            ListNode next = reverseLinkedList(slow.next);

            // Current node ko reverse karo
            slow.next.next = slow;
            slow.next = null;

            // Reverse linked list ka head return karo
            return next;
        }
    }

    // revised on 05/11/2025
    class SolutionrevisedOnthirdDay {
        public boolean isPalindrome(ListNode head) {

            ListNode fast = head;
            ListNode slow = head;

            while (fast != null && slow != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            if (fast != null) {
                slow = slow.next;
            }

            slow = reverLinkedList(slow);
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

        private ListNode reverLinkedList(ListNode slow) {

            if (slow == null || slow.next == null) {
                return slow;
            }

            ListNode next = reverLinkedList(slow.next);
            slow.next.next = slow;
            slow.next = null;
            return next;
        }
    }

    // revised on 11/11/2025
    class SolutionrevisedOnSeventhDay {
        public boolean isPalindrome(ListNode head) {

            ListNode fast = head;
            ListNode slow = head;

            while (fast != null && slow != null) {

                fast = fast.next.next;
                slow = slow.next;
            }

            if (fast != null) {
                slow = slow.next;
            }

            slow = reverseLinkedList(slow);

            while (slow != null) {

                if (slow.val != fast.val) {
                    return false;
                }

                slow = slow.next;
                fast = fast.next;
            }

            return true;

        }

        private ListNode reverseLinkedList(ListNode slow) {

            if (slow == null && slow.next == null) {
                return slow;
            }

            ListNode next = reverseLinkedList(slow);
            slow.next.next = slow;
            slow.next = null;
            return next;
        }
    }

    // revised on 11/11/2025
    class SolutionrevisedOnFourteenDay {
        public boolean isPalindrome(ListNode head) {

            ListNode fast = head;
            ListNode slow = head;

            while (fast != null && slow != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            if (fast != null) {
                slow = slow.next;
            }

            slow = reverseLinkedList(slow);

            while (slow != null) {

                if (slow.val != fast.val) {
                    return false;
                }

                slow = slow.next;
                fast = fast.next;
            }

            return true;
        }

        private ListNode reverseLinkedList(ListNode slow) {

            if (slow == null || slow.next == null) {
                return slow;
            }

            ListNode next = reverseLinkedList(slow.next);
            slow.next.next = slow;
            slow.next = null;
            return next;
        }
    }

    // revised on 12/24/2025
    class SolutionRevisedOnThirdDay {
        public boolean isPalindrome(ListNode head) {

            ListNode fast = head;
            ListNode slow = head;

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

        private ListNode reverseLinkedList(ListNode slow) {

            if (slow == null || slow.next == null) {
                return slow;
            }

            ListNode next = reverseLinkedList(slow.next);

            slow.next.next = slow;
            slow.next = null;

            return next;
        }

    }
}
