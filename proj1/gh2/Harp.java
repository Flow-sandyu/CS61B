package gh2;

import deque.Deque;
import deque.LinkedListDeque;

// Note: This file will not compile until you complete the Deque implementations
public class Harp {
    /**
     * Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday.
     */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public Harp(double frequency) {
        // Note: Create a buffer with capacity = SR / frequency.
        // Initially fill the buffer array with zeros.
        int capacity = (int) Math.round(SR / frequency) * 2;
        buffer = new LinkedListDeque<Double>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // Note: Replace buffer content with random numbers between -0.5 and 0.5.
        for (int i = 0; i < buffer.size(); i++) {
            buffer.removeFirst();
            buffer.addLast(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // Note: Dequeue the front sample and enqueue a new sample that is
        // the average of the two multiplied by the DECAY factor.
        double first = buffer.removeFirst();
        double second = buffer.get(0);
        buffer.addLast(-DECAY * (first + second) / 2);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // Note: Return the correct thing.
        return buffer.get(0);
    }
}
