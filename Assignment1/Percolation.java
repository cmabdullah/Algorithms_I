import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/***
➜ javac Percolation.java
➜ ls
Percolation.class     Percolation.java      PercolationStats.java inputCm.txt
➜ java Percolation <  inputCm.txt
true
**/
public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF backwashChecked;
    private int gridSize;
    private final int source_Top;
    private final int sink_Bottom;
    private boolean[][] sites;
    private int openSiteNumber;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        uf = new WeightedQuickUnionUF(n * n + 2);
        backwashChecked = new WeightedQuickUnionUF(n * n + 1);

        sites = new boolean[n][n];
        openSiteNumber = 0;

        source_Top = 0;
        sink_Bottom = n * n + 1;
        gridSize = n;
    }

    private int encode(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize) throw new IndexOutOfBoundsException();
        return (row - 1) * gridSize + col;
    }

    public void open(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize) throw new IndexOutOfBoundsException();
        if (isOpen(row, col)) return;

        sites[row - 1][col - 1] = true;
        openSiteNumber++;

        if (row == 1) {
            uf.union(encode(row, col), source_Top);
            backwashChecked.union(encode(row, col), source_Top);
        }

        if (row == gridSize) {
            uf.union(encode(row, col), sink_Bottom);
        }

        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(encode(row - 1, col), encode(row, col));
            backwashChecked.union(encode(row - 1, col), encode(row, col));
        }

        if (row < gridSize && isOpen(row + 1, col)) {
            uf.union(encode(row + 1, col), encode(row, col));
            backwashChecked.union(encode(row + 1, col), encode(row, col));
        }

        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(encode(row, col - 1), encode(row, col));
            backwashChecked.union(encode(row, col - 1), encode(row, col));
        }

        if (col < gridSize && isOpen(row, col + 1)) {
            uf.union(encode(row, col + 1), encode(row, col));
            backwashChecked.union(encode(row, col + 1), encode(row, col));
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize) throw new IndexOutOfBoundsException();
        return sites[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize) throw new IndexOutOfBoundsException();
        return backwashChecked.connected(encode(row, col), source_Top);
    }

    public int numberOfOpenSites() {
        return openSiteNumber;
    }

    public boolean percolates() {
        return numberOfOpenSites() > 0 && uf.connected(source_Top, sink_Bottom);
    }

    public static void main(String[] args) {
        int row, col, temp ,gridSize;
        gridSize = StdIn.readInt();
        Percolation percolation = new Percolation(gridSize);
        while (!StdIn.isEmpty()) {
            row = StdIn.readInt();
            col = StdIn.readInt();
            percolation.open(row, col);
           // StdOut.println("numberOfOpenSites : "+percolation.numberOfOpenSites());
           // StdOut.println("percolationStatus : "+percolation.percolates());
        }

        StdOut.print(percolation.percolates());
    }
}