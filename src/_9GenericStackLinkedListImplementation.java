import java.util.*;
/**
 * @author @bdull@h Kh@n
 * */
/**
*  A generic stack, implemented using a singly-linked list.
*/
class Stack<Item>{
	private Node first = null;
	private class Node{
	Item item;
		Node next; 
	}
	public boolean isEmpty(){
		return first == null;
	}
	public void push(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	public Item pop(){
		Item item = first.item;
		first = first.next;
		return item;
	}
}
public class _9GenericStackLinkedListImplementation {

	public static void main(String[] args) {
		Stack<String> ss = new Stack<String>();
		while (!StdIn.isEmpty()){
			String item = StdIn.readString();
			if (item.equals("-")){
				StdOut.println("Poped : "+ss.pop());
			}
			else{
				ss.push(item);
			}
		}
	}
}