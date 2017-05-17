// Exercise 2.4.15 (Solution published at http://algs4.cs.princeton.edu/)
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* ***********************************************************************
 *  Compilation:  javac MaxPQ.java
 *  Execution:    java MaxPQ < input.txt
 *
 *  Generic max priority queue implementation with a binary heap.
 *  Can be used with a comparator instead of the natural order,
 *  but the generic key type must still be Comparable.
 *
 *  % java MaxPQ < tinyPQ.txt
 *  Q X P (6 left on pq)
 *
 *  We use a one-based array to simplify parent and child calculations.
 *
 *************************************************************************/

/**
 *  The <tt>MaxPQ</tt> class represents a priority queue of generic keys.
 *  It supports the usual <em>insert</em> and <em>delete-the-maximum</em>
 *  operations, along with methods for peeking at the maximum key,
 *  testing if the priority queue is empty, and iterating through
 *  the keys.
 *  <p>
 *  The <em>insert</em> and <em>delete-the-maximum</em> operations take
 *  logarithmic amortized time.
 *  The <em>max</em>, <em>size</em>, and <em>is-empty</em> operations take constant time.
 *  Construction takes time proportional to the specified capacity or the number of
 *  items used to initialize the data structure.
 *  <p>
 *  This implementation uses a binary heap.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class MaxPQ<K extends Comparable<? super K>> implements Iterable<K> {
	private K[] pq; // store items at indices 1 to N
	private int N;  // number of items on priority queue
	private Comparator<? super K> comparator;  // optional Comparator

	// helper function to double the size of the heap array
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		if (capacity <= N) throw new IllegalArgumentException ();
		K[] temp = (K[]) new Comparable[capacity];
		for (int i = 1; i <= N; i++) temp[i] = pq[i];
		pq = temp;
	}

	@SuppressWarnings("unchecked")
	/** Create an empty priority queue with the given initial capacity, using the given comparator. */
	public MaxPQ(int initCapacity, Comparator<? super K> comparator) {
		pq = (K[]) new Comparable[initCapacity + 1];
		N = 0;
		this.comparator = comparator;
	}
	/** Create an empty priority queue with the given initial capacity. */
	public MaxPQ(int initCapacity) { this(initCapacity, null); }
	/** Create an empty priority queue using the given comparator. */
	public MaxPQ(Comparator<? super K> comparator) { this(1, comparator); }
	/** Create an empty priority queue. */
	public MaxPQ() { this(1, null); }

	/**
	 * Create a priority queue with the given items.
	 * Takes time proportional to the number of items using sink-based heap construction.
	 */
	public MaxPQ(K[] keys) {
		this(keys.length, null);
		N = keys.length;
		for (int i = 0; i < N; i++)
			pq[i+1] = keys[i];
		for (int k = N/2; k >= 1; k--)
			sink(k);
		//assert isMaxHeap();
	}

	/** Is the priority queue empty? */
	public boolean isEmpty() { return N == 0; }

	/** Return the number of items on the priority queue. */
	public int size() { return N; }

	/**
	 * Return the largest key on the priority queue.
	 * @throws java.util.NoSuchElementException if the priority queue is empty.
	 */
	public K max() {
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}

	/** Add a new key to the priority queue. */
	public void insert(K x) {
		// double size of array if necessary
		if (N >= pq.length - 1) resize(2 * pq.length);

		// add x, and percolate it up to maintain heap invariant
		pq[++N] = x;
		swim(N);
		//assert isMaxHeap();
	}

	/**
	 * Delete and return the largest key on the priority queue.
	 * @throws java.util.NoSuchElementException if the priority queue is empty.
	 */
	public K delMax() {
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		K max = pq[1];
		exch(1, N--);
		sink(1);
		pq[N+1] = null; // avoid loitering and help with garbage collection
		if ((N > 0) && (N == (pq.length - 1) / 4)) resize(pq.length / 2);
		//assert isMaxHeap();
		return max;
	}


	/* *********************************************************************
	 * Helper functions to restore the heap invariant.
	 **********************************************************************/

	private void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}

	private void sink(int k) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	/* *********************************************************************
	 * Helper functions for compares and swaps.
	 **********************************************************************/
	private boolean less(int i, int j) {
		if (comparator == null) {
			return pq[i].compareTo(pq[j]) < 0;
		}
		else {
			return comparator.compare(pq[i], pq[j]) < 0;
		}
	}

	private void exch(int i, int j) {
		K swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	// is pq[1..N] a max heap?
	private boolean isMaxHeap() {
		return isMaxHeap(1);
	}

	// is subtree of pq[1..N] rooted at k a max heap?
	private boolean isMaxHeap(int k) {
		if (k > N) return true;
		int left = 2*k, right = 2*k + 1;
		if (left  <= N && less(k, left))  return false;
		if (right <= N && less(k, right)) return false;
		return isMaxHeap(left) && isMaxHeap(right);
	}


	/* *********************************************************************
	 * Iterator
	 **********************************************************************/

	/**
	 * Return an iterator that iterates over all of the keys on the priority queue
	 * in descending order.
	 * <p>
	 * The iterator doesn't implement <tt>remove()</tt> since it's optional.
	 */
	public Iterator<K> iterator() { return new HeapIterator(); }

	private class HeapIterator implements Iterator<K> {
		// create a new pq
		private MaxPQ<K> copy;

		// add all items to copy of heap
		// takes linear time since already in heap order so no keys move
		public HeapIterator() {
			if (comparator == null) copy = new MaxPQ<K>(size());
			else                    copy = new MaxPQ<K>(size(), comparator);
			for (int i = 1; i <= N; i++)
				copy.insert(pq[i]);
		}

		public boolean hasNext()  { return !copy.isEmpty();                     }
		public void remove()      { throw new UnsupportedOperationException();  }

		public K next() {
			if (!hasNext()) throw new NoSuchElementException();
			return copy.delMax();
		}
	}

	void showHeap() {
		for (int i = 1; i <= N; i++)
			StdOut.print (pq[i] + " ");
		StdOut.println ();
	}

	/**
	 * A test client.
	 */
	public static void main(String[] args) {
		MaxPQ<String> pq = new MaxPQ<>(100);
		StdIn.fromString("10 20 30 40 50 - - - 05 25 35 - - - 70 80 05 - - - - ");
		while (!StdIn.isEmpty()) {
			StdOut.print ("pq:  "); pq.showHeap();
			String item = StdIn.readString();
			if (item.equals("-")) StdOut.println("max: " + pq.delMax());
			else pq.insert(item);
			GraphvizBuilder.binaryHeapToFile (pq.pq, pq.N);
		}
		StdOut.println("(" + pq.size() + " left on pq)");
	}
}
