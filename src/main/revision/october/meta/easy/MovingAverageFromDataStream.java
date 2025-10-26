package main.revision.october.meta.easy;

import java.util.*;

/**
 * MovingAverageFromDataStream maintains a moving average of the last 'size'
 * integers added.
 * It uses a queue to store the window of elements and efficiently updates the
 * sum as new elements arrive.
 *
 * Time Complexity:
 * - nextInt(int val): O(1) per operation (queue operations and sum update are
 * constant time)
 *
 * Space Complexity:
 * - O(size), where 'size' is the window size (queue stores up to 'size'
 * elements)
 *
 * Example usage:
 * MovingAverageFromDataStream ma = new MovingAverageFromDataStream(3);
 * ma.nextInt(1); // returns average of [1]
 * ma.nextInt(10); // returns average of [1, 10]
 * ma.nextInt(3); // returns average of [1, 10, 3]
 * ma.nextInt(5); // returns average of [10, 3, 5]
 */
public class MovingAverageFromDataStream {

    int size;
    int windowSum = 0;

    Queue<Integer> q;

    public MovingAverageFromDataStream(int size) {
        this.size = size;
        this.q = new LinkedList<>();
    }

    double nextInt(int val) {

        q.offer(val);
        windowSum += val;

        if (q.size() > size) {
            int cur = q.poll();
            windowSum -= cur;
        }

        return windowSum * 1.0 / size;
    }

    // Revision 19/10/2025
    double nextIntRevisionThirdrdDay(int val) {

        q.offer(val);
        windowSum += val;

        if (q.size() > size) {
            int cur = q.poll();

            windowSum -= cur;
        }

        return windowSum * 1.0 / size;
    }

    // Revision 25/10/2025
    double nextIntRevisionSeventhDay(int val) {

        q.offer(val);
        windowSum += val;

        if (q.size() > size) {
            int cur = q.poll();
            windowSum -= cur;
        }

        return windowSum * 1.0 / size;
    }
}
