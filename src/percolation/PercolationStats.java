/********************************************************************************************
 * Author      : Vijay Prakash Yadav
 * Date        : 14/01/2017
 * brief       : This program uses Percolation class to simulate Monte Carlo simulation
 * 			      for Percolation problem.
 * How to compile
 * compilation : javac-algs4 PercolationStats.java
 * execution   : java-algs4 PercolationStates <n> <Trials>
 * 
 ********************************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {    
    private static int t;
    private double[] x; /* fraction of open sites*/
    
    public PercolationStats(int n, int trials) {
    	if (n <= 0) throw new IllegalArgumentException("n should be >= 1");
    	if (trials <= 0) throw new IllegalAccessError("trials should be >=1");
    	
    	x = new double[trials];   
    }
    /**
     * 
     * @return mean of Percolation thresholds
     */
    public double mean() {     
        return StdStats.mean(x); 
    }
    /**
     * 
     * @return returns stddev of percolation thresholds
     */
    public double stddev() {   
        return StdStats.stddev(x);
    }
    /**
     * 
     * @return returns 95% confidence low interval.
     */
    public double confidenceLo() {    
        return (StdStats.mean(x) - (1.96*StdStats.stddev(x)) / Math.sqrt(t));
    }
    /**
     * 
     * @return returns 95% confidence high interval
     */
    public double confidenceHi() {  
        return (StdStats.mean(x) + (1.96*StdStats.stddev(x)) / Math.sqrt(t));
    }
    
    public static void main(String[] args) {
        /**
         * local varibales
         */
    	int n;
      	int p, q;
      	
      	/* Reading input from stdIn */
        n = StdIn.readInt();
        t = StdIn.readInt();
        
        PercolationStats pt = new PercolationStats(n, t);
        
        for (int i = 0; i < t; i++) {
        	
            Percolation per = new Percolation(n);
            
            while (!per.percolates()) {
                p = StdRandom.uniform(1, n + 1);
                q = StdRandom.uniform(1, n + 1);
                per.open(p, q);
                
            } /* End of while loop*/
            
            pt.x[i] = (double) per.numberOfOpenSites();
            pt.x[i] /= (double) (n*n);
        } /* for loop ends here*/

        StdOut.println("mean\t\t= "+pt.mean());
        StdOut.println("stddev\t\t= "+pt.stddev());
        StdOut.println("95% confidence intervals\t= "+pt.confidenceLo()+","+pt.confidenceHi());
    }
}
