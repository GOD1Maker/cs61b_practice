package hw4.puzzle;


import org.junit.Test;

import java.util.PriorityQueue;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{

    private int i;
    private int j;
    private int [][] tiles2D;
    private int [][] temp;
    private int [] tile;//store the 1D version of tiles
    private int [][] goals2D;
    private int [] goal;//store the 1D version of goals
    int blank = 0;
    //Constructs a board from an N-by-N array of tiles where
    //tiles[i][j] = tile at row i, column j
    public Board(int[][] tiles){
        tiles2D = new int[tiles.length][tiles.length];
        tile = new int[size() * size()];
        goals2D = new int[size()][size()];
        goal = new int[size() * size()];

        for(int i = 0; i < size(); i++){
            for(int j = 0; j < size(); j++){
                tiles2D[i][j] = tiles[i][j];
                tile[toOneD(i,j)] = tiles2D[i][j];
                goals2D[i][j] = toOneD(i,j) + 1;
                goal[toOneD(i,j)] = toOneD(i,j) + 1;//1,2,3,4,5,6,7,8,9
            }
        }

        goals2D[size()-1][size()-1] = blank;
        goal[size() * size() - 1] = blank;//1,2,3,4,5,6,7,8,0
    }

    private int toOneD(int i, int j){
        return i * size() + j;
    }

    private int[] toTwoD(int n){
        int[] ij = new int[2];
        ij[0] = n / size();//row
        ij[1] = n % size();//col
        return ij;
    }

    //Returns value of tile at row i, column j (or 0 if blank)
    public int tileAt(int i, int j){
        if(i < 0 || i > size() - 1 || j < 0 || j > size() - 1){
            throw new IndexOutOfBoundsException();
        }
        return tiles2D[i][j] == blank ? 0 : tiles2D[i][j];
    }

    //Returns the board size N
    public int size(){
        return tiles2D.length;
    }

    //Returns the neighbors of the current board
    public Iterable<WorldState> neighbors(){
        Queue<WorldState> neighbors = new Queue<>();
        int flag_row = -1;
        int flag_col = -1;
        for (int row = 0; row < size(); row++) {
            for (int col = 0; col < size(); col++) {
                if (tileAt(row, col) == blank) {
                    flag_row = row;
                    flag_col = col;
                }
            }
        }
        int[][] arr = new int[size()][size()];
        for (int row = 0; row < size(); row++) {
            for (int col = 0; col < size(); col++) {
                arr[row][col] = tileAt(row, col);
            }
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (Math.abs(-flag_row + i) + Math.abs(j - flag_col) - 1 == 0) {
                    arr[flag_row][flag_col] = arr[i][j];
                    arr[i][j] = blank;
                    Board neighbor = new Board(arr);
                    neighbors.enqueue(neighbor);
                    arr[i][j] = arr[flag_row][flag_col];
                    arr[flag_row][flag_col] = blank;
                }
            }
        }
        return neighbors;
    }

    public int hamming(){
        int ham = 0;
        //check if the element is same, if not ham++
        for(int i = 0; i < size() * size(); i++){
            if(tile[i] != blank && tile[i] != goal[i]){
                ham++;
            }
        }
        return ham;
    }

    public int manhattan(){
        int man = 0;
        int [] goal_ij;
        int [] tile_ij;
        //find the same element and compute the distance
        for(int i = 0; i < size() * size(); i++){
            for(int j = 0; j < size() * size(); j++){
                if(goal[i] != blank && tile[j] == goal[i]){
                    tile_ij = toTwoD(j);
                    goal_ij = toTwoD(i);
                    man = man + Math.abs(tile_ij[0] - goal_ij[0]) + Math.abs(tile_ij[1] - goal_ij[1]);
                }
            }
        }
        return man;
    }

    //Estimated distance to goal. This method should
    //simply return the results of manhattan() when submitted to Gradescope.
    public int estimatedDistanceToGoal(){
        return manhattan();
    }

    //Returns true if this board's tile values are the same position as y's
    public boolean equals(Object y){
        Board y1 = (Board) y;
        return this.tiles2D == y1.tiles2D;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
