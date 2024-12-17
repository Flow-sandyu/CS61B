package deque;

import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;


import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testAddRemove() {
        ADbyIE<Integer> B = new ADbyIE<>();
        for (int i = 0; i < 100; i++) {
            B.addFirst(i);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        ArrayDeque<Integer> B = new ArrayDeque<>();
        // ADbyIE<Integer> B = new ADbyIE<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeB = B.size();
                assertEquals(sizeL, sizeB);
            } else if (operationNumber == 2) {
                if (L.size() == 0)
                    continue;
                // getLast
                int lastL = L.getLast();
                int lastB = B.getLast();
                assertEquals(lastL, lastB);
            } else if (operationNumber == 3) {
                if (L.size() == 0)
                    continue;
                // removeLast
                int lastL = L.removeLast();
                int lastB = B.removeLast();
                assertEquals(lastL, lastB);
            }
        }
    }

    @Test
    public void testGrowShrink() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < 16; i++) {
            dq.addLast(i);
        }
        for (int i = -1; i >= -16; i--) {
            dq.addFirst(i);
        }
        for (int i = -16; i < 16; i++) {
            assertEquals(i, (int) dq.get(i + 16));
        }
        for (int i = 0; i < 30; i++) {
            dq.removeFirst();
        }
        assertEquals(2, dq.size());
        dq.printDeque();
    }
}
