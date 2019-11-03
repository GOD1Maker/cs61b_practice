
package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;

public class Solver {

    private MinPQ<Node> fringe;
    private int moves;
    private Comparator<Node> cmp;
    private Node result;
    private ArrayList<WorldState> arr = new ArrayList<>(100);//store the edtg result to avoid recomputing


    //Constructor which solves the puzzle, computing
    //everything necessary for moves() and solution() to
    //not have to solve the problem again. Solves the
    //puzzle using the A* algorithm. Assumes a solution exists.
    public Solver(WorldState initial){
        cmp = (n1, n2) -> {
            if(n1.count + n1.edtg < n2.count + n2.edtg){
                return -1;
            }else if(n1.count + n1.edtg == n2.count + n2.edtg){
                return 0;
            }else {
                return 1;
            }
        };
        fringe = new MinPQ<Node>(cmp);
        fringe.insert(new Node(initial, 0, null));
        Node tmp = fringe.delMin();
        /**insert all neighbors of deleted node*/
        while(!tmp.ws.isGoal()){
            Iterable<WorldState> i = tmp.ws.neighbors();
            moves = count(tmp) + 1;
            for(WorldState ws : i){
                /**sift out the same item as grandparent*/
                if(tmp.prev != null){
                    //optimization: avoid add the vertex twice in queue
                    if(tmp.prev.ws.equals(ws)){
                        continue;
                    }
                }
                fringe.insert(new Node(ws, moves, tmp));
            }
            tmp = fringe.delMin();
        }
        result = tmp;
        addResult(result);
        arr.add(result.ws);
    }

    //Returns the minimum number of moves to solve the puzzle starting
    //at the initial WorldState.
    public int moves(){
        return arr.size() - 1;
    }

    //Returns a sequence of WorldStates from the initial WorldState
    //to the solution.
    public Iterable<WorldState> solution(){
        return arr;
    }

    private int count(Node n){
        Node a = n;
        int num = 0;
        while (a != null){
            a = a.prev;
            num++;
        }
        return num;
    }

    private Node addResult(Node n){
        if(n.prev == null){
            return n;
        }
        Node x = addResult(n.prev);
        arr.add(n.prev.ws);
        return x;
    }

    private class Node{
        private Node prev;
        private int count;
        private int edtg;
        private WorldState ws;

        public Node(WorldState ws, int count, Node prev){
            this.ws = ws;
            this.count = count;
            this.prev = prev;
            this.edtg = ws.estimatedDistanceToGoal();
        }
    }
}
