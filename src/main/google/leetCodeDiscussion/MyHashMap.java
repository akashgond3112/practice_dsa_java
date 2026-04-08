package main.google.leetCodeDiscussion;

import java.util.LinkedList;

class MyHashMap<K, V> {

    // Internal class to represent a Key-Value pair
    static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] buckets;
    private int capacity = 16; // Initial capacity
    private int size = 0;
    private final double LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new LinkedList[capacity];
    }

    // 1. Hash Function: Calculates the index for a key
    private int getHash(K key) {
        if (key == null)
            return 0;
        return Math.abs(key.hashCode() % capacity);
    }

    // 2. Put Method
    public void put(K key, V value) {
        int index = getHash(key);

        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        // Check if key already exists to update it
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        // Add new entry
        buckets[index].add(new Entry<>(key, value));
        size++;

        // 3. Resizing (Crucial for L5 discussion)
        if ((double) size / capacity >= LOAD_FACTOR) {
            rehash();
        }
    }

    // 4. Get Method
    public V get(K key) {
        int index = getHash(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null; // Key not found
    }

    // 5. Rehashing: Moving elements to a larger array to maintain O(1)
    @SuppressWarnings("unchecked")
    private void rehash() {
        capacity *= 2;
        LinkedList<Entry<K, V>>[] oldBuckets = buckets;
        buckets = new LinkedList[capacity];
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldBuckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }
}
