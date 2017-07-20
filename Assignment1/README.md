# Solution

Corner cases. By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left site: Throw a java.lang.IndexOutOfBoundsException if any argument to open(), isOpen(), or isFull() is outside its prescribed range. The constructor should throw a java.lang.IllegalArgumentException if n ≤ 0.
Performance requirements. The constructor should take time proportional to n2; all methods should take constant time plus a constant number of calls to the union–find methods union(), find(), connected(), and count().

## Process of open block:

```cpp
// This code has bug
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
```



### ref·er·ence
 * [Percolation](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html) :D

 * [Logic](https://www.youtube.com/watch?v=BiSKunzrC1g) :D