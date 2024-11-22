package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        // 先测试，最后再添加，外面一层循环就行
        SLList<Integer> list = new SLList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int opCount = 10000;
        for (int i = 0; i < 8; i++) {
            int N = 1000 * (int) Math.pow(2, i);
            Ns.addLast(N);
            opCounts.addLast(opCount);

            int addCount = 0;
            if (i == 0) {
                addCount = N;
            } else {
                addCount = Ns.get(i - 1);
            }
            for (int j = 0; j < addCount; j++) {
                list.addLast(j);
            }

            // System.out.println(list.size());
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < opCount; j++) {
                list.getLast(); // 调用需要测试的函数
            }
            times.addLast(sw.elapsedTime());
        }
        printTimingTable(Ns, times, opCounts);
    }

}
