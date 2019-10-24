package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {

    private double[] fractions;
    private int numTrials;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){

        if(N <= 0 || T <= 0 ){
            throw new IllegalArgumentException();
        }
        int totalSites = N * N;
        numTrials = T;
        fractions = new double[T];
        for(int i = 0; i < T; i++){
            int numOpenedSites = 0;
            Percolation p = pf.make(N);
            while(!p.percolates()){
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if(!p.isOpen(row,col)){
                    p.open(row,col);
                    numOpenedSites += 1;
                }
            }
            fractions[i] = (double) numOpenedSites/totalSites;
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return (double)StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        return mean() - (1.96 * Math.sqrt(stddev()) / Math.sqrt(numTrials));
    }

    public double confidenceHigh(){
        return mean() + (1.96 * Math.sqrt(stddev()) / Math.sqrt(numTrials));
    }
}
