package main.google.leetCodeDiscussion.l4;

// === ONSITE ROUND 2: LRU Cache with Time-Based Expiration ===
//
// High-level idea (Hinglish):
// 1) Fast lookup ke liye HashMap use karte hain (key -> node), so get/put average O(1).
// 2) LRU order maintain karne ke liye Doubly Linked List use karte hain.
//    - head ke paas least recently used (LRU) node hota hai
//    - tail ke paas most recently used (MRU) node hota hai
// 3) Expiry ke liye lazy deletion strategy:
//    - get/put pe node check karenge, agar expiry cross ho gayi to usse remove kar denge.
//    - list ka old data generally head side pe hota hai, to eviction ke time clean-up easy ho jata hai.
//
// Complexity:
// - get: O(1) average
// - put: O(1) average
// - space: O(capacity)

import java.util.HashMap;

public class LRUCacheWithExpiry {

    private static class Node {
        int key, value;
        long expiresAt; // absolute expiry timestamp in ms
        Node prev, next;

        Node(int key, int value, long expiresAt) {
            this.key = key;
            this.value = value;
            this.expiresAt = expiresAt;
        }
    }

    private final int capacity;
    private final long expirationMs; // e.g. 5 * 60 * 1000 for 5 minutes
    private final HashMap<Integer, Node> map;
    private final Node head, tail; // sentinel nodes (head=LRU, tail=MRU)

    public LRUCacheWithExpiry(int capacity, long expirationMs) {
        // Step 1: capacity + TTL config save karo
        this.capacity = capacity;
        this.expirationMs = expirationMs;

        // Step 2: hashmap init karo for direct key lookup
        this.map = new HashMap<>();

        // Step 3: sentinel nodes banao (ye real data store nahi karte)
        // Inse insert/remove logic simple aur null-check free ho jata hai.
        head = new Node(0, 0, Long.MAX_VALUE);
        tail = new Node(0, 0, Long.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // Step 1: hashmap se node nikaalo
        Node node = map.get(key);
        if (node == null)
            return -1;

        // Step 2: lazy expiry check - expired hai to cache miss treat karo
        if (isExpired(node)) {
            remove(node);
            map.remove(key);
            return -1;
        }

        // Step 3: valid hit hai -> MRU banane ke liye tail pe move karo
        moveToTail(node);

        // Step 4: value return karo
        return node.value;
    }

    public void put(int key, int value) {
        // Step 1: new expiry timestamp calculate karo (abhi + TTL)
        long expiry = System.currentTimeMillis() + expirationMs;

        // Step 2: agar key already present hai to update-in-place karo
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            node.expiresAt = expiry;
            // Updated key ko recent use maana jayega
            moveToTail(node);
            return;
        }

        // Step 3: agar capacity full hai to LRU side se eviction karo
        if (map.size() >= capacity) {
            evictLRU();
        }

        // Step 4: naya node create karke map + DLL dono mein add karo
        Node newNode = new Node(key, value, expiry);
        map.put(key, newNode);
        addToTail(newNode);
    }

    // Eviction logic (Hinglish):
    // - head.next se start karo (ye current LRU candidate hota hai)
    // - node remove karo from DLL + map
    // - agar removed node expired tha to loop continue (clean-up bonus)
    // - pehla non-expired node remove hote hi stop (single effective eviction)
    private void evictLRU() {
        Node lru = head.next;
        while (lru != tail) {
            remove(lru);
            map.remove(lru.key);
            // expired node mila to agla check karo - stale nodes saaf hote rahenge
            if (!isExpired(lru))
                break;
            lru = head.next;
        }
    }

    private boolean isExpired(Node node) {
        // Current time expiry se bada hai to node invalid maanenge
        return System.currentTimeMillis() > node.expiresAt;
    }

    // ---- Doubly Linked List helper methods (Hinglish) ----

    private void addToTail(Node node) {
        // node ko tail ke just pehle insert karo => MRU position
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    private void remove(Node node) {
        // node ko list se unlink karo
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToTail(Node node) {
        // pehle current position se hatao, phir tail pe daalo
        remove(node);
        addToTail(node);
    }

    // Overloaded constructor:
    // - agar expiry required nahi hai to huge TTL set kar do (practically
    // no-expiry)
    public LRUCacheWithExpiry(int capacity) {
        this(capacity, Long.MAX_VALUE / 2);
    }

    public static void main(String[] args) throws InterruptedException {
        // Demo flow:
        // 1) capacity=2 aur TTL=200ms ka cache banaya
        LRUCacheWithExpiry cache = new LRUCacheWithExpiry(2, 200); // 200ms TTL

        // 2) do entries insert ki
        cache.put(1, 10);
        cache.put(2, 20);

        // 3) key=1 hit -> value 10 milegi
        System.out.println(cache.get(1)); // 10

        // 4) 300ms wait -> dono keys expire ho jayengi
        Thread.sleep(300); // let entries expire

        // 5) expired keys ab miss return karengi
        System.out.println(cache.get(1)); // -1 (expired)
        System.out.println(cache.get(2)); // -1 (expired)

        // 6) fresh insert ke baad value normal milegi
        cache.put(3, 30);
        System.out.println(cache.get(3)); // 30
    }
}
