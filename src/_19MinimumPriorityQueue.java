/*************************************************************************
 *  Compilation:  javac MinPQ.java
 *  Execution:    java MinPQ < input.txt
 *  
 *  Generic min priority queue implementation with a binary heap.
 *  Can be used with a comparator instead of the natural order.
 *
 *  % java MinPQ < tinyPQ.txt
 *  E A E (6 left on pq)
 *
 *  We use a one-based array to simplify parent and child calculations.
 *
 *  Can be optimized by replacing full exchanges with half exchanges
 *  (ala insertion sort).
 *
 *************************************************************************/

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class _19MinimumPriorityQueue<Key> implements Iterable<Key>{
	private Key[] pq;					// store items at indices 1 to N
	private int N;						// number of items on priority queue
	private Comparator<Key> comparator;  // optional comparator

	//Initializes an empty priority queue with the given initial capacity.
	//initCapacity the initial capacity of the priority queue
	public _19MinimumPriorityQueue(int initCapacity){
		pq = (Key[]) new Object[initCapacity + 1];
		N = 0;
	}

	public _19MinimumPriorityQueue(){//Initializes an empty priority queue.
		this(1);
	}

	 //Initializes an empty priority queue with the given initial capacity, using the given comparator.
	//initCapacity the initial capacity of the priority queue comparator the order to use when comparing keys

	public _19MinimumPriorityQueue(int initCapacity, Comparator<Key> comparator){
		this.comparator = comparator;
		pq = (Key[]) new Object[initCapacity + 1];
		N = 0;
	}


	 //Initializes an empty priority queue using the given comparator.
	 //param comparator the order to use when comparing keys

	public _19MinimumPriorityQueue(Comparator<Key> comparator){
		this(1, comparator);
		}

	/**
	 * Initializes a priority queue from the array of keys.
	 * Takes time proportional to the number of keys, using sink-based heap construction.
	 * keys the array of keys
	 */
	public _19MinimumPriorityQueue(Key[] keys){
		N = keys.length;
		pq = (Key[]) new Object[keys.length + 1];
		for (int i = 0; i < N; i++)
			pq[i+1] = keys[i];
		for (int k = N/2; k >= 1; k--)
			sink(k);
		assert isMinHeap();
	}

	/**
	 * Is the priority queue empty?
	 * true if the priority queue is empty; false otherwise
	 */
	public boolean isEmpty(){
		return N == 0;
	}

	/**
	 * Returns the number of keys on the priority queue.
	 * the number of keys on the priority queue
	 */
	public int size(){
		return N;
	}

	/**
	 * Returns a smallest key on the priority queue.
	 * return a smallest key on the priority queue
	 * throws java.util.NoSuchElementException if priority queue is empty
	 */
	public Key min(){
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}

	// helper function to double the size of the heap array
	private void resize(int capacity){
		assert capacity > N;
		Key[] temp = (Key[]) new Object[capacity];
		for (int i = 1; i <= N; i++) temp[i] = pq[i];
		pq = temp;
	}
	//Adds a new key to the priority queue. x the key to add to the priority queue
	public void insert(Key x){
		// double size of array if necessary
		if (N == pq.length - 1) resize(2 * pq.length);

		// add x, and percolate it up to maintain heap invariant
		pq[++N] = x;
		swim(N);
		assert isMinHeap();
	}
	//Removes and returns a smallest key on the priority queue.
	public Key delMin(){
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");//throws java.util.NoSuchElementException if the priority queue is empty
		exch(1, N);
		Key min = pq[N--];
		sink(1);
		pq[N+1] = null;		// avoid loitering and help with garbage collection
		if ((N > 0) && (N == (pq.length - 1) / 4)) resize(pq.length  / 2);
		assert isMinHeap();
		return min;
	}
	/***********************************************************************
	 * Helper functions to restore the heap invariant.
	 **********************************************************************/
	
	private void swim(int k){
		while (k > 1 && greater(k/2, k)){
			exch(k, k/2);
			k = k/2;
		}
	}

	private void sink(int k){
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && greater(j, j+1)) j++;
			if (!greater(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	/***********************************************************************
	 * Helper functions for compares and swaps.
	 **********************************************************************/
	private boolean greater(int i, int j){
		if (comparator == null){
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
		}
		else {
			return comparator.compare(pq[i], pq[j]) > 0;
		}
	}

	private void exch(int i, int j){
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	// is pq[1..N] a min heap?
	private boolean isMinHeap(){
		return isMinHeap(1);
	}

	// is subtree of pq[1..N] rooted at k a min heap?
	private boolean isMinHeap(int k){
		if (k > N) return true;
		int left = 2*k, right = 2*k + 1;
		if (left  <= N && greater(k, left))  return false;
		if (right <= N && greater(k, right)) return false;
		return isMinHeap(left) && isMinHeap(right);
	}
	/***********************************************************************
	 * Iterators
	 **********************************************************************/
	/**
	 * Returns an iterator that iterates over the keys on the priority queue
	 * in ascending order.
	 * The iterator doesn't implement remove() since it's optional.
	 * return an iterator that iterates over the keys in ascending order
	 */
	public Iterator<Key> iterator(){
		return new HeapIterator();
	}

	private class HeapIterator implements Iterator<Key>{
		// create a new pq
		private _19MinimumPriorityQueue<Key> copy;

		// add all items to copy of heap
		// takes linear time since already in heap order so no keys move
		public HeapIterator(){
			if (comparator == null) 
				copy = new _19MinimumPriorityQueue<Key>(size());
			else 
				copy = new _19MinimumPriorityQueue<Key>(size(), comparator);
			for (int i = 1; i <= N; i++)
				copy.insert(pq[i]);
		}

		public boolean hasNext(){
			return !copy.isEmpty();
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
		
		public Key next(){
			if (!hasNext()) throw new NoSuchElementException();
			return copy.delMin();
		}
	}

	/**
	 * Unit tests the <tt>MinPQ</tt> data type.
	 */
	public static void main(String[] args){
		_19MinimumPriorityQueue<String> pq = new _19MinimumPriorityQueue<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) pq.insert(item);
			else if (!pq.isEmpty()) StdOut.print(pq.delMin() + " ");
		}
		StdOut.println("(" + pq.size() + " left on pq)");
	}
}
