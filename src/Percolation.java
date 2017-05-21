/*----------------------------------------------------------------
 *  Author:        Vijay Prakash Yadav
 *  Written:       13/01/2017
 *
 *  Compilation:   javac-algs4 Percolation.java
 *  Execution:     java-algs4 Percolation n
 *  
 *  Prints number of open sites in percolation system.
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private  int count;
    private int n;
    private int[][] site;
    private WeightedQuickUnionUF obj;
    
    public Percolation(int n) {
        int p, q, i, j;
        
        this.n = n;
        
        if (n <= 0)
            throw new java.lang.IllegalArgumentException("n should be >=1");
        
        site = new int[n+2][n+1];
        obj = new WeightedQuickUnionUF((n+2)*n+2);
        for (i = 1; i <= n; i++)
            for (j = 1; j <= n; j++)
            {
                site[i][j] = 0; 
            }           
        for (i = 1; i <= n; i++) {
            p = i;
            q = n*n+i;
            obj.union(p, n*n+n+1);
            obj.union(q, n*n+n+2);
        }
        count = 0;
    }
    
    /**
     * @brief this function open a site.
     * @param row row index of the site
     * @param col column index of the site
     */
    public void open(int row, int col) {
        int p, q;
        
        if (row < 1 || row > n) throw new ArrayIndexOutOfBoundsException("row index out of bounds");
        
        if (col < 1 || col > n) throw new ArrayIndexOutOfBoundsException("column index out of bounds");
        
        //row -= 1;
        //col -= 1;
        
        
        p = (row-1) *n+col;
        
        if (site[row][col] == 1)
            return;
        else
            site[row][col] = 1;
            
        if (((row - 1) >= 0) && (site[row-1][col] == 1)){
            q = (row-2)*n+col;
            obj.union(p, q); 
        }        
        if (((row + 1) < n) && (site[row + 1][col] == 1)) {
            q = (row)*n+col;
            obj.union(p, q);
        }
        if (((col - 1) >= 0) && (site[row][col - 1] == 1)) {
            q = (row-1)*n +(col - 1);
            obj.union(p, q);
        }
        if (((col + 1) < n) && (site[row][col + 1] == 1)) {
            q = (row-1)*n + col + 1;
            obj.union(p, q);
        }
        count += 1; 
    }
    
    /**
     * 
     * @param row row index
     * @param col column index
     * @return if the given site is open, the function returns true else return false.
     */
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n) throw new ArrayIndexOutOfBoundsException("row index out of bounds");      
        if (col < 1 || col > n) throw new ArrayIndexOutOfBoundsException("column index out of bounds");
        
        return site[row][col] == 1;
    }
    /**
     * 
     * @param row
     * @param col
     * @return returns true/False.
     */
    public boolean isFull(int row, int col) {
        int p;
        
        if (row < 1 || row > n) throw new ArrayIndexOutOfBoundsException("row index out of bounds");      
        if (col < 1 || col > n) throw new ArrayIndexOutOfBoundsException("column index out of bounds");
        //row -= 1;
        //col -= 1;
        
        p = (row-1)*n + col;
        return obj.connected(p, n*n+n+1) && isOpen(row, col);
    
    }
    /**
     * 
     * @return returns true if the system percolates.
     */
    public boolean percolates() {
        
        /*
        int row, i;
        boolean flag1, flag2;
        flag1 = false;
        flag2 = false;
       
        row = 1;
        for(i =1;i<=n;i++){
            if(isOpen(row,i)){
                flag1 = true;
                break;
            }         
        }
        row = n;
        for(i =1; i<=n;i++){
            if(isOpen(row,i)){
                flag2 = true;
                break;
            }
        }
        */
        return obj.connected(n*n+n+1, n*n+n+2);//&& flag1 && flag2;
    
    }
    /**
     * 
     * @return returns number of open sites.
     */
    public int numberOfOpenSites() {
        return count;
    }
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        int p, q;
        int size = 0;
        size = StdIn.readInt();
        
        Percolation per = new Percolation(size);
        StdOut.println("is Percelates "+per.percolates());
        while (!per.percolates()) {
            p = StdRandom.uniform(1, size + 1);
            q = StdRandom.uniform(1, size + 1);
            per.open(p, q);

        }
        StdOut.println("number of open sites =" + per.count+" isFull(1,1)= "+per.isFull(1, 1));
        

        
    }

}
