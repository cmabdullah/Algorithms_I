/*************************************************************************
 *  Compilation:  javac Stack.java
 *  Execution:    java Stack < input.txt
 *
 *  A generic stack, implemented using a singly-linked list.
 *  Each stack element is of type Item.
 *
 *  This version uses a static nested class Node (to save 8 bytes per
 *  Node), whereas the version in the textbook uses a non-static nested
 *  class (for simplicity).
 *  
 *  % more tobe.txt 
 *  to be or not to - be - - that - - - is
 *
 *  % java Stack < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 *************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;
public class _10IteratorStackLinkedListImplementation<Item> implements Iterable<Item> {
	private int N;				// size of the stack
	private Node<Item> first;	// top of stack
	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}
	/**
	* Initializes an empty stack.
	*/
	public _10IteratorStackLinkedListImplementation() {
		first = null;
		N = 0;
	}
	public boolean isEmpty() {//Is this stack empty? return true if this stack is empty; false otherwise
		return first == null;
	}

	public int size() {//Returns the number of items in the stack.
		return N;
	}
	public void push(Item item) {//Adds the item to this stack.
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	public Item pop() {//Removes and returns the item most recently added to this stack.
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = first.item;		// save item to return
		first = first.next;			// delete first node
		N--;
		return item;				// return the saved item
	}
	/**
	 * Returns (but does not remove) the item most recently added to this stack.
	 * @return the item most recently added to this stack
	 * @throws java.util.NoSuchElementException if this stack is empty
	 */
	public Item peek() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return first.item;
	}
	/**
	 * Returns a string representation of this stack.
	 * @return the sequence of items in the stack in LIFO order, separated by spaces
	*/
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this)
			s.append(item + " ");
			return s.toString();
	}
	/**
	 * Returns an iterator to this stack that iterates through the items in LIFO order.
	 */
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;
		public ListIterator(Node<Item> first) {
			current = first;
		}
		public boolean hasNext(){
			return current != null;
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next; 
			return item;
		}
	}
	/**
	 * Unit tests the <tt>Stack</tt> data type.
	 */
	public static void main(String[] args) {
		_10IteratorStackLinkedListImplementation<String> s = new _10IteratorStackLinkedListImplementation<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")){
				s.push(item);
			}
			else if (!s.isEmpty()){
				StdOut.println(s.pop() + " ");
			}
		}
		StdOut.println("(" + s.size() + " left on stack)");
	}
}