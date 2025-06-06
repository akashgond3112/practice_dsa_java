/**
 * @author agond
 * @date Jun 06, 2025
 * @time 7:21:57 PM
 */
package main.revision.meta.easy;

import java.util.*;

public class MovingAverageFromDataStream {

    private Queue<Integer> q;
    private int size;
    private int sum;

    MovingAverageFromDataStream(int size) {

        this.q = new LinkedList<>();
        this.size = size;
        this.sum = 0;
    }

    public double nextMove(int val) {

        sum += val;
        q.add(val);

        if (q.size() > size) {
            sum -= q.poll();
        }

        return (double) sum / q.size();
    }
}
