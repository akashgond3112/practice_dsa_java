package main.revision.october.meta.easy;

import java.util.*;

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

}
