/**
 * @author akash
 * @date Sep 21, 2026
 * @time 6:48:17 PM
 */
package main.interview.stripe;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class IdempotencyKeysSafeRetriesProblem {

    /**
     * <b>Input:</b>
     * requests = [
     * ("key_a", 1000),
     * ("key_b", 500),
     * ("key_a", 1000), # a retry of key_a
     * ]
     * 
     * <b>Output:</b> [1000, 500, 1000]
     * <b>Explanation:</b> key_a is stored on its first use (1000). key_b is new
     * (500). The third
     * request replays key_a, so it returns the stored 1000 instead of charging the
     * customer again.
     * 
     */

    public static class Request {
        String key;
        int amount;
        int ts;

        public Request(String key, int amount) {
            this.key = key;
            this.amount = amount;
        }

        public Request(String key, int amount, int ts) {
            this.key = key;
            this.amount = amount;
            this.ts = ts;
        }

    }

    public static List<Integer> processRequests(List<Request> requests) {

        Map<String, Integer> store = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (Request req : requests) {

            if (!req.key.isEmpty() && store.containsKey(req.key)) {
                result.add(store.get(req.key));
            } else {
                if (!req.key.isEmpty()) {
                    store.put(req.key, req.amount);
                }
                result.add(req.amount);
            }
        }

        return result;

    }

    /**
     * <b>Input:</b> ttl = 3600
     * requests = [
     * ("key_a", 1000, 1000),
     * ("key_a", 2000, 2000),
     * ("key_a", 3000, 5000),
     * ("key_a", 4000, 6000),
     * ]
     * 
     * <b>Output:</b> [1000, 1000, 3000, 3000]
     * <b>Explanation:</b>
     * - t=1000: new key, execute and store 1000.
     * - t=2000: 1000 seconds later, still within the 3600 window, replay 1000.
     * - t=5000: 4000 seconds after creation, past the window, so the key expired:
     * re-execute and store 3000.
     * - t=6000: 1000 seconds after the new entry, live again, replay 3000.
     * 
     */

    public static List<Integer> processRequests(List<Request> requests, long ttl) {

        Map<String, long[]> store = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (Request req : requests) {

            if (!req.key.isEmpty() && store.containsKey(req.key)) {

                long[] entry = store.get(req.key);

                if (req.ts - entry[1] > ttl) {
                    store.put(req.key, new long[] { req.amount, req.ts }); // expired
                    result.add(req.amount);
                } else {
                    result.add((int) entry[0]); // live > replay
                }

            } else {
                if (!req.key.isEmpty())
                    store.put(req.key, new long[] { req.amount, req.ts });
                result.add(req.amount);
            }

        }

        return result;
    }

    /**
     * <b>Input:</b> ttl = 3600
     * requests = [
     * ("key_a", 1000, 1000),
     * ("key_a", 1000, 2000),
     * ("key_a", 2000, 2500),
     * ("key_a", 9999, 9000),
     * ("key_a", 1234, 9500),
     * ("", 5000, 9600),
     * ]
     * 
     * <b>Output:</b> [1000, 1000, -1, 9999, -1, 5000]
     * <b>Explanation:</b>
     * - t=1000: new key, execute and store 1000.
     * - t=2000: live key, same amount, safe replay of 1000.
     * - t=2500: live key, different amount (2000 vs the stored 1000), conflict,
     * return -1.
     * - t=9000: 8000 seconds after creation, the key expired, so re-execute and
     * store 9999.
     * - t=9500: live key, different amount (1234 vs 9999), conflict, return -1.
     * - t=9600: empty key, always execute, return 5000.
     * 
     */
    public static List<Integer> processRequestsExtended(List<Request> requests, long ttl) {

        Map<String, long[]> store = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (Request req : requests) {

            if (!req.key.isEmpty() && store.containsKey(req.key)) {

                long[] entry = store.get(req.key);

                if (req.ts - entry[1] > ttl) {
                    store.put(req.key, new long[] { req.amount, req.ts }); // expired
                    result.add(req.amount);
                } else if (req.amount == entry[0]) {
                    result.add((int) entry[0]); // replay
                } else {
                    result.add(-1); // conflict 409
                }

            } else {
                if (!req.key.isEmpty())
                    store.put(req.key, new long[] { req.amount, req.ts });
                result.add(req.amount);
            }

        }

        return result;
    }

}
