package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHeroLite {
    public static final double CONCERT_A = 440.0;
    public static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringS = new GuitarString(CONCERT_C);
        // Harp stringA = new Harp(CONCERT_A);
        // Harp stringS = new Harp(CONCERT_C);

        Drum drumB = new Drum(CONCERT_A);
        Drum drumD = new Drum(CONCERT_C);
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'a') {
                    stringA.pluck();
                } else if (key == 's') {
                    stringS.pluck();
                } else if (key == 'z') {
                    drumB.pluck();
                } else if (key == 'x') {
                    drumD.pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = stringA.sample() + stringS.sample() + drumB.sample() + drumD.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            stringA.tic();
            stringS.tic();
            drumB.tic();
            drumD.tic();
        }
    }
}

