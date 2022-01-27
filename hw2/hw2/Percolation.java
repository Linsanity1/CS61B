package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int size; // size-by-size grid
    private final boolean[][] grid; // false stands for blocked; true stands for open
    private int openSite; // stores the number of open sites
    // store the status of connection by this data structure
    private final WeightedQuickUnionUF status;

    /* Hash site (row, col) to an integer
       size * size and size * size + 1 are reserved for top and bottom
     */
    private int siteHash(int row, int col) {
        return row * size + col;
    }

    /** Create N-by-N grid with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Input size is smaller than 0");
        }
        size = N;
        grid = new boolean[size][size];
        status = new WeightedQuickUnionUF(size * size + 2);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
    }

    // Validate that the site (row, col) is a valid index
    private void validate(int row, int col) {
        if (row >= size) {
            throw new IndexOutOfBoundsException("Index of row"
            + row + "is not between 0 and " + (size - 1));
        } else if (col >= size) {
            throw new IndexOutOfBoundsException("Index of col" + col
            + "is not between 0 and " + (size - 1));
        }
    }

    /* Is the site at the row edge?
       0 for not, 1 for top row, 2 for bottom row
     */
    private int rowEdge(int row) {
        if (row == 0) {
            return 1;
        } else if (row == size - 1) {
            return 2;
        } else {
            return 0;
        }
    }

    /* Is the site at the col edge?
       0 for not, 1 for left-most col, 2 for right-most col
     */
    private int colEdge(int col) {
        if (col == 0) {
            return 1;
        } else if (col == size - 1) {
            return 2;
        } else {
            return 0;
        }
    }

    /* Is the site at the corner?
       0 for not, 1 for left-top, 2 for right-top,
       3 for left-bottom, 4 for right-bottom
     */
    private int isAtCorner(int row, int col) {
        if (rowEdge(row) == 1) {
            if (colEdge(col) == 1) {
                return 1;
            } else if (colEdge(col) == 2) {
                return 2;
            }
        } else if (rowEdge(row) == 2) {
            if (colEdge(col) == 1) {
                return 3;
            } else if (colEdge(col) == 2) {
                return 4;
            }
        }
        return 0;
    }

    /* Connect corner site to adjacent sites */
    private void connectCornerSite(int row, int col) {
        int site = siteHash(row, col);
        switch (isAtCorner(row, col)) {
            case 1:
                if (isOpen(row, col + 1)) {
                    status.union(site, siteHash(row, col + 1));
                }
                if (isOpen(row + 1, col)) {
                    status.union(site, siteHash(row + 1, col));
                }
                break;
            case 2:
                if (isOpen(row, col - 1)) {
                    status.union(site, siteHash(row, col - 1));
                }
                if (isOpen(row + 1, col)) {
                    status.union(site, siteHash(row + 1, col));
                }
                break;
            case 3:
                if (isOpen(row - 1, col)) {
                    status.union(site, siteHash(row - 1, col));
                }
                if (isOpen(row, col + 1)) {
                    status.union(site, siteHash(row, col + 1));
                }
                break;
            case 4:
                if (isOpen(row - 1, col)) {
                    status.union(site, siteHash(row - 1, col));
                }
                if (isOpen(row, col - 1)) {
                    status.union(site, siteHash(row, col - 1));
                }
                break;
            default:
                break;
        }
    }

    /* Is the site at edge but not corner?
       0 for not, 1 for top edge, 2 for bot edge,
       3 for left edge, 4 for right edge
     */
    private int isAtEdge(int row, int col) {
        if (colEdge(col) == 0) {
            if (rowEdge(row) == 1) {
                return 1;
            } else if (rowEdge(row) == 2) {
                return 2;
            }
        }
        if (rowEdge(row) == 0) {
            if (colEdge(col) == 1) {
                return 3;
            } else if (colEdge(col) == 2) {
                return 4;
            }
        }
        return 0;
    }

    /* Connect corner site to adjacent sites */
    private void connectEdgeSite(int row, int col) {
        int site = siteHash(row, col);
        switch (isAtEdge(row, col)) {
            case 1:
                if (isOpen(row, col + 1)) {
                    status.union(site, siteHash(row, col + 1));
                }
                if (isOpen(row + 1, col)) {
                    status.union(site, siteHash(row + 1, col));
                }
                if (isOpen(row, col - 1)) {
                    status.union(site, siteHash(row, col - 1));
                }
                break;
            case 2:
                if (isOpen(row, col - 1)) {
                    status.union(site, siteHash(row, col - 1));
                }
                if (isOpen(row - 1, col)) {
                    status.union(site, siteHash(row - 1, col));
                }
                if (isOpen(row, col + 1)) {
                    status.union(site, siteHash(row, col + 1));
                }
                break;
            case 3:
                if (isOpen(row - 1, col)) {
                    status.union(site, siteHash(row - 1, col));
                }
                if (isOpen(row + 1, col)) {
                    status.union(site, siteHash(row + 1, col));
                }
                if (isOpen(row, col + 1)) {
                    status.union(site, siteHash(row, col + 1));
                }
                break;
            case 4:
                if (isOpen(row - 1, col)) {
                    status.union(site, siteHash(row - 1, col));
                }
                if (isOpen(row + 1, col)) {
                    status.union(site, siteHash(row + 1, col));
                }
                if (isOpen(row, col - 1)) {
                    status.union(site, siteHash(row, col - 1));
                }
                break;
            default:
                break;
        }
    }

    /* Connect middle site to adjacent sites */
    private void connectMidSite(int row, int col) {
        int site = siteHash(row, col);
        if (isOpen(row, col + 1)) {
            status.union(site, siteHash(row, col + 1));
        }
        if (isOpen(row + 1, col)) {
            status.union(site, siteHash(row + 1, col));
        }
        if (isOpen(row, col - 1)) {
            status.union(site, siteHash(row, col - 1));
        }
        if (isOpen(row - 1, col)) {
            status.union(site, siteHash(row - 1, col));
        }
    }

    // Connect helper method
    private void connectSite(int row, int col) {
        int site = siteHash(row, col);
        int top = size * size;
        int bottom = size * size + 1;

        // connect to virtual sites
        if (rowEdge(row) == 1) {
            status.union(top, site);
        } else if (rowEdge(row) == 2) {
            status.union(bottom, site);
        }

        // connect the site to adjacent sites
        if (isAtCorner(row, col) != 0) {
            connectCornerSite(row, col);
        } else if (isAtEdge(row, col) != 0) {
            connectEdgeSite(row, col);
        } else {
            connectMidSite(row, col);
        }
    }

    /** Open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        validate(row, col);
        grid[row][col] = true;
        openSite += 1;
        connectSite(row, col);
    }

    /** Is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    /** Is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        validate(row, col);
        int intSite = siteHash(row, col);
        return status.connected(intSite, size * size);
    }

    /** Number of open sites. */
    public int numberOfOpenSites() {
        return openSite;
    }

    /** Does the system percolate? */
    public boolean percolates() {
        return status.connected(size * size, size * size + 1);
    }

    /** Main for unit testing */
    public static void main(String[] args) {

    }




}
