package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int size;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufExcludeBottom;//avoid back wash
    private int numOpenSites = 0;
    private int[][] surroundings = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        if(N <= 0){
            throw new IllegalArgumentException();
        }
        grid = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                grid[i][j] = false;
            }
        }
        size = N;
        top = 0;//virtual top node
        bottom = N * N + 1;//virtual bottom node
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufExcludeBottom = new WeightedQuickUnionUF(N * N + 1);
    }

    private int xyTo1D(int row, int col){
        return row * size + col;
    }

    private void validity(int row, int col){
        if(row < 0 || col < 0 || row >= size || col >= size){
            throw new IllegalArgumentException();
        }
    }
    // open the site (row, col) if it is not open yet
    public void open(int row, int col){
        validity(row, col);
        if(!isOpen(row,col)){
            grid[row][col] = true;
            numOpenSites += 1;
        }

        if(row == 0){
            uf.union(xyTo1D(row,col),top);
            ufExcludeBottom.union(xyTo1D(row,col),top);
        }

        if(row == size - 1){
            uf.union(xyTo1D(row,col),bottom);
        }

        for(int[] surrounding : surroundings){
            int adjRow = surrounding[0] + row;
            int adjCol = surrounding[1] + col;
            if(adjRow >= 0 && adjRow < size){
                if(adjCol >=0 && adjCol < size){
                    if(isOpen(adjRow,adjCol)){
                        uf.union(xyTo1D(row,col),xyTo1D(adjRow,adjCol));
                        ufExcludeBottom.union(xyTo1D(row,col),xyTo1D(adjRow,adjCol));
                    }
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        validity(row,col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        validity(row,col);
        return ufExcludeBottom.connected(xyTo1D(row,col),top);
    }

    // number of open sites
    public int numberOfOpenSites(){
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.connected(top,bottom);
    }

    // use for unit testing (not required)
    public static void main(String[] args){
        int size = 5;
        Percolation p = new Percolation(size);

        p.open(1,0);
        p.open(2,0);
        p.open(3,0);
        p.open(4,0);
        p.open(0,0);
        System.out.println(p.percolates());

    }
}
