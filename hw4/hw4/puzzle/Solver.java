package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private final Stack<WorldState> solution;

    private static class SearchNode implements Comparable<SearchNode> {
        private final WorldState wState;
        private final int disToInitState;
        private final SearchNode prevNode;
        private final int priority;

        private SearchNode(WorldState s, int dis, SearchNode n) {
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

        public SearchNode getPrevNode() {
            return prevNode;
        }

        @Override
        public int compareTo(SearchNode o) {
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
        MinPQ<SearchNode> fringe = new MinPQ<>();
        SearchNode startNode = new SearchNode(initial, 0, null);
        fringe.insert(startNode);
        SearchNode goal = null;
        while (!fringe.isEmpty()) {
            SearchNode delNode = fringe.delMin();
            //System.out.print(delNode.wState.toString() + " "); //for testing
            if (delNode.getWorldState().isGoal()) {
                goal = delNode;
                break;
            }
            enqChildren(delNode, fringe);
        }
        solution = new Stack<WorldState>();
        while (goal != null) {
            solution.push(goal.getWorldState());
            goal = goal.getPrevNode();
        }
    }

    /* Enqueue all children of the current node. */
    private static void enqChildren(SearchNode n, MinPQ<SearchNode> q) {
        Iterable<WorldState> neighbors = n.getWorldState().neighbors();
        int disToStart = n.getDisToInitState() + 1;
        for (WorldState w : neighbors) {
            SearchNode newNode = new SearchNode(w, disToStart, n);
            if (n.getPrevNode() == null) {
                q.insert(newNode);
                continue;
            }
            if (!w.equals(n.getPrevNode().getWorldState())) {
                q.insert(newNode);
            }
            //boolean isInMinPQ = false;
            //boolean isSmaller = false;
            /* Done: check this one
            for (SearchNode node : q) {
                if (node.getWorldState().equals(newNode.getWorldState())) {
                    isInMinPQ = true;
                    isSmaller = newNode.compareTo(node) < 0;
                    break;
                }
            }
            if (!isInMinPQ) {
                q.insert(newNode);
            } else if (isInMinPQ && isSmaller) {
                q.insert(newNode);
            }*/
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
