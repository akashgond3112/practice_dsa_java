package main.google.leetCodeDiscussion.l4;

// === ONSITE ROUND 1: URL Shortener with character frequency threshold ===
// Strategy (Hinglish): strings ko base-26 counter ki tarah generate karenge.
// - "Digits" 0..25 map hote hain 'a'..'z' se
// - Isse 3 cheezein milti hain:
//   1) uniqueness (duplicate nahi)
//   2) exhaustiveness (saare combos cover)
//   3) shorter-first ordering (pehle chhote strings)
// - Har increment ke baad threshold T validate karte hain; invalid combo skip hota hai
//
// Complexity:
// - getNext(): O(K) amortized, K = current string length (skip rare cases)
// - space: O(K) for state

public class URLShortener {

    private final int threshold;
    private int[] current; // current "digit" array, index 0 = least significant
    private boolean started;

    public URLShortener(int threshold) {
        // Step 1: max allowed frequency per character save karo
        this.threshold = threshold;

        // Step 2: empty state se start (first valid output increment ke baad aayega)
        this.current = new int[0]; // start before "a"

        // Step 3: started=false means counter abhi initialize nahi hua
        this.started = false;
    }

    public String getNext() {
        // Step-by-step flow:
        // 1) counter increment karo
        // 2) threshold rule se validate karo
        // 3) valid mile to string return, warna next try
        while (true) {
            increment();
            if (isValidString(current)) {
                return buildString(current);
            }
            // invalid case: loop continue hoga aur next candidate banega
        }
    }

    // Increment logic (Hinglish):
    // - Base-26 counter jaisa increment (LSD first)
    // - 25 ('z') pe rollover karke carry आगे pass hota hai
    // - sab rollover ho jaye to length +1
    private void increment() {
        if (!started) {
            // First call pe directly "a" se start
            current = new int[] { 0 }; // first string is "a"
            started = true;
            return;
        }

        int i = 0;
        while (i < current.length) {
            if (current[i] < 25) {
                // simple increment, carry ki zarurat nahi
                current[i]++;
                return;
            } else {
                // rollover: current digit reset aur carry next digit ko
                current[i] = 0; // carry over
                i++;
            }
        }
        // Sab digits rollover: string length badhao (e.g., z -> aa)
        current = new int[current.length + 1]; // all zeros = "aa...a"
    }

    // Validation step:
    // - har character ki frequency count karo
    // - koi bhi freq threshold se upar gayi to invalid
    private boolean isValidString(int[] digits) {
        int[] freq = new int[26];
        for (int d : digits) {
            freq[d]++;
            if (freq[d] > threshold)
                return false;
        }
        return true;
    }

    // Build step:
    // - digits array LSD-first store hota hai
    // - output readable form ke liye reverse direction mein build karte hain
    private String buildString(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            sb.append((char) ('a' + digits[i]));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Demo: threshold=2 => koi bhi char 2 se zyada baar allowed nahi
        URLShortener us = new URLShortener(2);

        // First 30 short codes print karo
        for (int i = 0; i < 30; i++) {
            System.out.println(us.getNext());
        }

        // Expected pattern:
        // a, b, c, ..., z, aa, ab, ...
        // threshold=2 hone ki wajah se "aaa" jaise strings skip ho jayenge
    }
}
