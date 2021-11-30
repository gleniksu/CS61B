package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean grid[][];
    WeightedQuickUnionUF uf;
    WeightedQuickUnionUF ufExclusiveBottom; //to avoid backwash
    private int top; // virtual top node
    private int bottom; // virtual bottom node
    private int size;
    private int openNum;
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void validata(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Out of range.");
        }
    }


    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N <= 0");
        }
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufExclusiveBottom = new WeightedQuickUnionUF(N * N + 1);
        size = N;
        openNum = 0;
        top = 0;
        bottom = N + 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                grid[i][j] = false;
            }
        }
    }


    public void open(int row, int col) {
        validata(row, col);
        if (!isOpen(row, col)) {
         grid[row][col] = true;
         openNum += 1;
        }
        if (row == 0) {
            uf.union(xyTo1D(row, col), top);
            ufExclusiveBottom.union(xyTo1D(row, col), top);
        }
        if (row == size - 1) {
            uf.union(xyTo1D(row, col), bottom);
        }
        for (int[] dir: dirs) {
            int adjacentRow = row + dir[0];
            int adjacentCol = col + dir[1];
            if (adjacentRow > 0 && adjacentCol > 0 && adjacentRow < size && adjacentCol < size) {
                if(isOpen(row, col)){
                    uf.union(xyTo1D(row, col), xyTo1D(adjacentRow, adjacentCol));
                    ufExclusiveBottom.union(xyTo1D(row, col), xyTo1D(adjacentRow, adjacentCol));
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validata(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validata(row, col);
        return ufExclusiveBottom.connected(xyTo1D(row, col),bottom);
    }

    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    public int numberOfOpenSites() {
        return openNum;
    }

    private int xyTo1D(int row, int col) {
        return row * size + col + 1;
    }

    public static void main(String[] ars) {

    }
}
