package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] openSiteFraction; // fraction of open sites when percolates

    /** Perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0) {
            throw new IllegalArgumentException("Input size " + N + "is smaller than 0");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("Input experiment number "
            + T + "is smaller than 0");
        }
        openSiteFraction = new double[T];
        int[] rdmPercolation = new int[N * N]; // for random percolation shuffle
        for (int i = 0; i < N * N; i += 1) {
            rdmPercolation[i] = i;
        }

        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            StdRandom.shuffle(rdmPercolation);
            for (int j = 0; j < N * N; j++) {
                if (p.percolates()) {
                    openSiteFraction[i] = (double) j / (N * N);
                    break;
                }
                int rdmRow = rdmPercolation[j] / N;
                int rdmCol = rdmPercolation[j] - rdmRow * N;
                p.open(rdmRow, rdmCol);
            }
        }
    }

    /** Sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(openSiteFraction);
    }

    /** Sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(openSiteFraction);
    }

    /** Low endpoint of 95% confidence interval */
    public double confidenceLow() {
        int len = openSiteFraction.length;
        return mean() - 1.96 * stddev() / Math.sqrt(len);
    }

    /** High endpoint of 95% confidence interval */
    public double confidenceHigh() {
        int len = openSiteFraction.length;
        return mean() + 1.96 * stddev() / Math.sqrt(len);
    }
}
