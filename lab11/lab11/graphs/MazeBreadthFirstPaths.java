package lab11.graphs;

import edu.princeton.cs.algs4.Queue;


/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private static final int INFINITY = Integer.MAX_VALUE;
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        // distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // Done: Your code here.
        // Don't forget to update distTo, edgeTo, and marked, as well as call announce()

        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < maze.V(); v++) {
            distTo[v] = INFINITY;
        }
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);
        announce();

        while (!q.isEmpty()) {
            int v = q.dequeue();
            announce();
            if (v == t) {
                targetFound = true;
            }
            if (targetFound) {
                return;
            }
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    @Override
    public void solve() {
        bfs();
    }
}

