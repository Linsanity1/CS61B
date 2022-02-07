package hw4.puzzle;


import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private static int BLANK = 0;
    private int[][] tileArray;
    private int size;

    public Board(int[][] tiles) {
        size = tiles.length;
        tileArray = new int[size][size];
        for (int i = 0; i < size; i += 1) {
            System.arraycopy(tiles[i], 0, tileArray[i], 0, tiles[i].length);
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i > size() - 1) {
            throw new IndexOutOfBoundsException("i is not between 0 to N - 1");
        }
        if (j < 0 || j > size() - 1) {
            throw new IndexOutOfBoundsException("j is not between 0 to N - 1");
        }
        return tileArray[i][j];
    }

    public int size() {
        return size;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int numInWrongPos = 0;
        for (int i = 0; i < size(); i += 1) {
            for (int j = 0; j < size(); j += 1) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                if (tileAt(i, j) != i * size() + j + 1) {
                    numInWrongPos += 1;
                }
            }
        }
        return numInWrongPos;
    }

    /* Helper method for manhattan that calculates the dis of num at (i, j) to goal */
    private int disToGoal(int num, int i, int j) {
        int targetRow = (num - 1) / size();
        int targetCol = (num - 1) % size();
        int rowDis = (targetRow - i > 0) ? targetRow - i : i - targetRow;
        int colDis = (targetCol - j > 0) ? targetCol - j : j - targetCol;
        return rowDis + colDis;
    }

    public int manhattan() {
        int totalDis = 0;
        for (int i = 0; i < size(); i += 1) {
            for (int j = 0; j < size(); j += 1) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                totalDis += disToGoal(tileAt(i, j), i, j);
            }
        }
        return totalDis;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (!(y instanceof Board)) {
            return false;
        }
        Board yBoard = (Board) y;
        if (size() != yBoard.size()) {
            return false;
        }
        int N = size();
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (tileAt(i, j) != yBoard.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((tileArray == null) ? 0 : tileArray.hashCode());
        return result;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
