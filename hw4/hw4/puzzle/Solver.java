package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.Iterator;
import java.util.LinkedList;

public class Solver {
    private LinkedList<WorldState> solution;

    private static class searchNode implements Comparable<searchNode> {
        private final WorldState wState;
        private final int disToInitState;
        private final searchNode prevNode;
        private final int priority;

        public searchNode(WorldState s, int dis, searchNode n) {
            wState = s;
            disToInitState = dis;
            prevNode = n;
            priority = wState.estimatedDistanceToGoal() + disToInitState;
        }

        public WorldState getWorldState() {
            return wState;
        }

        public int getDisToInitState() {
            return disToInitState;
        }

        public searchNode getPrevNode() {
            return prevNode;
        }

        @Override
        public int compareTo(searchNode o) {
            return priority - o.priority;
        }
    }


    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        MinPQ<searchNode> fringe = new MinPQ<>();
        searchNode startNode = new searchNode(initial, 0, null);
        fringe.insert(startNode);
        searchNode goal = null;
        while (!fringe.isEmpty()) {
            searchNode delNode = fringe.delMin();
            // System.out.print(delNode.wState.toString() + " "); for testing
            if (delNode.getWorldState().isGoal()) {
                goal = delNode;
                break;
            }
            enqChildren(delNode, fringe);
        }
        solution = new LinkedList<WorldState>();
        while (goal != null) {
            solution.addFirst(goal.getWorldState());
            goal = goal.getPrevNode();
        }
    }

    /* Enqueue all children of the current node. */
    private static void enqChildren(searchNode n, MinPQ<searchNode> q) {
        Iterable<WorldState> neighbors = n.getWorldState().neighbors();
        int disToStart = n.getDisToInitState() + 1;
        for (WorldState w : neighbors) {
            searchNode newNode = new searchNode(w, disToStart, n);
            boolean isInMinPQ = false;
            boolean isSmaller = false;
            // Done: check this one
            for (searchNode node : q) {
                if (node.getWorldState().equals(newNode.getWorldState())) {
                    isInMinPQ = true;
                    isSmaller = newNode.compareTo(node) < 0;
                    break;
                }
            }
            if (!isInMinPQ && !newNode.getWorldState().equals(n.getWorldState())) {
                q.insert(newNode);
            } else if (isInMinPQ && isSmaller) {
                q.insert(newNode);
            }

        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     */
    public int moves() {
        return solution.size() - 1;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     */
    public Iterable<WorldState> solution() {
        return solution;
    }
}
