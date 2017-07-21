# Solution

Corner cases. Throw a java.lang.NullPointerException if the client attempts to add a null item; throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque; throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator; throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.

## Process of Deque API logic:

```cpp
// This code has almost bug free
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;

        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        n++;
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();

        Node newLast = new Node();
        newLast.item = item;
        newLast.next = null;
        newLast.prev = last;

        if (isEmpty()) {
            first = newLast;
        } else {
            last.next = newLast;
        }
        last = newLast;
        n++;

    }
```



### ref·er·ence
 * [Percolation](coursera.cs.princeton.edu/algs4/assignments/queues.html) :D