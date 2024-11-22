package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    AListNoResizing<Integer> L = new AListNoResizing<>();
    BuggyAList<Integer> B = new BuggyAList<>();

    @Test
    public void testThreeAddThreeRemove() {
        L.addLast(4);
        L.addLast(5);
        L.addLast(6);
        B.addLast(4);
        B.addLast(5);
        B.addLast(6);
        for (int i = 0; i < 3; i += 1) {
            assertEquals(L.removeLast(), B.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

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
}
