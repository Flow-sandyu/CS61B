package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> studentArrayDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> arrayDequeSolution = new ArrayDequeSolution<>();
        String message = "";
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 5);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                studentArrayDeque.addLast(randVal);
                arrayDequeSolution.addLast(randVal);
                message += "addLast(" + randVal + ")\n";
            } else if (operationNumber == 1) {
                if (studentArrayDeque.size() == 0)
                    continue;
                // removeFirst
                Integer lastL = studentArrayDeque.removeFirst();
                Integer lastB = arrayDequeSolution.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message, lastB, lastL);
            } else if (operationNumber == 2) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                studentArrayDeque.addFirst(randVal);
                arrayDequeSolution.addFirst(randVal);
                message += "addFirst(" + randVal + ")\n";
            } else if (operationNumber == 3) {
                if (studentArrayDeque.size() == 0)
                    continue;
                // removeLast
                Integer lastL = studentArrayDeque.removeLast();
                Integer lastB = arrayDequeSolution.removeLast();
                message += "removeLast()\n";
                assertEquals(message, lastB, lastL);
            }
        }
    }

}
