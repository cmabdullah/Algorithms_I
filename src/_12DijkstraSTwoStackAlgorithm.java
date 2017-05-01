import java.util.*;
/**
*  A generic DijkstraS Two Stack Algorithm, implemented using a singly-linked list. modified by Cm
*/
class StackD<Item>{
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
public class _12DijkstraSTwoStackAlgorithm {

	public static void main(String[] args){
		StackD<String> ops  = new StackD<String>();
		StackD<Double> vals = new StackD<Double>();
		StdOut.println("Press '=' to show result !!");
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if(s.equals("("));
			else if (s.equals("+"))
				ops.push(s);
			else if (s.equals("*"))
				ops.push(s);
			
			else if (s.equals("=")){ //@Cmabdullah
				break;
			}
			else if (s.equals(")")){
				String op = ops.pop();
				if(op.equals("+")) {
					vals.push(vals.pop() + vals.pop());
				}
				else if (op.equals("*")) {
					vals.push(vals.pop() * vals.pop());
				}
			}
			else {
				vals.push(Double.parseDouble(s));
			}
		}
		StdOut.println(vals.pop());
	}
}
