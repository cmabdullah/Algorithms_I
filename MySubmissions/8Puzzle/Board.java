import java.util.ArrayList;

/**
 * Created by CtheSky on 2016/9/16.
 */
public class Board {
    private int n;
    private int[][] blocks;

    public Board(int[][] blocks) {
        // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        n = blocks.length;
        this.blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                this.blocks[i][j] = blocks[i][j];
    }

    public int dimension() {
        // board dimension n
        return n;
    }

    public int hamming() {
        // number of blocks out of place
        int ham = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (blocks[i][j] != 0 && blocks[i][j] != (i * n + j + 1))
                    ham++;
        return ham;
    }

    public int manhattan() {
        // sum of Manhattan distances between blocks and goal
        int manh = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) continue;
                int goalI = (blocks[i][j] - 1) / n;
                int goalJ = (blocks[i][j] - 1) % n;
                manh += i > goalI ? i - goalI : goalI - i;
                manh += j > goalJ ? j - goalJ : goalJ - j;
            }
        return manh;
    }

    public boolean isGoal() {
        // is this board the goal board?
        return hamming() == 0;
    }

    public Board twin() {
        // a board that is obtained by exchanging any pair of blocks
        int[][] twinBlocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                twinBlocks[i][j] = blocks[i][j];

        Board twin = new Board(twinBlocks);
        if (twinBlocks[0][0] != 0 && twinBlocks[0][1] != 0)
            twin.swap(0, 0, 0, 1);
        else
            twin.swap(1, 0, 1, 1);
        return twin;
    }

    public boolean equals(Object y) {
        // does this board equal y?
        if (y == this) return true;
        if (y == null || y.getClass() != this.getClass()) return false;
        if (n != ((Board) y).n) return false;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (blocks[i][j] != ((Board) y).blocks[i][j])
                    return false;
        return true;
    }

    public Iterable<Board> neighbors() {
        // all neighboring boards
        int blankI = 0, blankJ = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (blocks[i][j] == 0) {
                    blankI = i;
                    blankJ = j;
                }

        ArrayList<Board> neighbors = new ArrayList<>();
        if (blankI > 0) {
            Board neighbor = new Board(blocks);
            neighbor.swap(blankI, blankJ, blankI - 1, blankJ);
            neighbors.add(neighbor);
        }
        if (blankI < n - 1) {
            Board neighbor = new Board(blocks);
            neighbor.swap(blankI, blankJ, blankI + 1, blankJ);
            neighbors.add(neighbor);
        }
        if (blankJ > 0) {
            Board neighbor = new Board(blocks);
            neighbor.swap(blankI, blankJ, blankI, blankJ - 1);
            neighbors.add(neighbor);
        }
        if (blankJ < n -1) {
            Board neighbor = new Board(blocks);
            neighbor.swap(blankI, blankJ, blankI, blankJ + 1);
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    public String toString() {
        // string representation of this board (in the output format specified below)
        StringBuilder sb = new StringBuilder();
        sb.append(n).append('\n');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                sb.append(' ').append(blocks[i][j]).append(' ');
            sb.append('\n');
        }
        return sb.toString();
    }

    private void swap(int i, int j, int x, int y) {
        int temp = blocks[i][j];
        blocks[i][j] = blocks[x][y];
        blocks[x][y] = temp;
    }
    public static void main(String[] args) {
        // unit tests (not graded)
    }
}