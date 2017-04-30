/**
➜  src git:(master) ✗ javac _5ArrayStackOfStrings.java
➜  src git:(master) ✗ java _5ArrayStackOfStrings  <tobe.txt
Poped : to
Poped : be
Poped : not
Poped : that
Poped : or
Poped : be
➜  src git:(master) ✗ 
 * */


class StackOfStringsm{
	private Node first = null;
	private class Node{
	String item;
	Node next; 
	}
	public boolean isEmpty(){
		return first == null;
	}
	public void push(String item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	public String pop(){
		String item = first.item;
		first = first.next;
		return item;
	}
}
public class _5ArrayStackOfStrings  {

	public static void main(String[] args) {
		StackOfStringsm stack = new StackOfStringsm();
		while (!StdIn.isEmpty()){
			String s = StdIn.readString();
			if (s.equals("-")){
				StdOut.println("Poped : "+stack.pop());
			}
			else{
				stack.push(s);
			}
		}
	}
} 