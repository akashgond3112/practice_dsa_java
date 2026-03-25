package main.google.leetCodeDiscussion.l4;

// === PHONE INTERVIEW 1: Partition Array with isValid API ===
// Approach (Hinglish):
// - Two-pointer technique use karte hain taaki minimum swaps mein partition ho jaye.
// - Goal: valid strings ko left side pe le aana, invalid ko right side pe.
// - `left` pointer left se valid zone expand karta hai,
//   `right` pointer right se invalid zone expand karta hai.
//
// Complexity:
// - Time: O(n * L), jahan L average string length hai (isValid O(L) le sakta hai)
// - Space: O(1) extra, kyunki in-place swap kar rahe hain

public class PartitionValid {

    // Simulated API (Hinglish):
    // Interview mein isko black box maan ke use karna hai.
    // Yahan demo ke liye prefix 'a' se valid check ho raha hai.
    static boolean isValid(String s) {
        String P = "a"; // example prefix
        return s.startsWith(P);
    }

    public static void partition(String[] arr) {
        // Step 1: do pointers initialize karo
        int left = 0;
        int right = arr.length - 1;

        // Step 2: jab tak pointers cross nahi karte, partition continue karo
        while (left < right) {
            // Step 2a: left pointer ko aage badhao jab tak valid mil rahe hain
            while (left < right && isValid(arr[left])) {
                left++;
            }

            // Step 2b: right pointer ko peeche lao jab tak invalid mil rahe hain
            while (left < right && !isValid(arr[right])) {
                right--;
            }

            // Step 2c: ab left pe invalid aur right pe valid expected hai -> swap karo
            if (left < right) {
                String temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                // Step 2d: swap ke baad dono pointers move karo
                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        // Demo input: valid wo strings jinka prefix 'a' hai
        String[] arr = { "ad", "cat", "awe", "dog", "apple", "bat" };

        // In-place partition call
        partition(arr);

        // Result print: valid values front side pe grouped honge
        System.out.println(java.util.Arrays.toString(arr));
        // Expected pattern: [ad, apple, awe, ... invalid strings ...]
    }
}
