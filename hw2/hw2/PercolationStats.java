package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int timeNum;
    private double[] fractions;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        timeNum = T;
        fractions = new double[T];
        double totalSize = N * N;
        for (int i = 0; i < timeNum; i++) {
            Percolation p = pf.make(N);
            while(!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }
            double openNum = p.numberOfOpenSites();
            fractions[i] = openNum / totalSize;
        }
    }

    public double mean() {
        return StdStats.mean(fractions);
    }

    public double stddev() {
        return StdStats.stddev(fractions);
    }

    public double confidenceLow() {
        double mu = mean();
        double sigma = stddev();
        return mu - (1.96 * sigma / Math.sqrt(timeNum));
    }

    public double confidenceHigh() {
        double mu = mean();
        double sigma = stddev();
        return mu + (1.96 * sigma / Math.sqrt(timeNum));
    }
}
