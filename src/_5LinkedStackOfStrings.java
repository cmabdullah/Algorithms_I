/**
 * @author @bdull@h Kh@n
 * */
/**
➜  src git:(master) ✗ javac _5LinkedStackOfStrings.java
➜  src git:(master) ✗ java _5LinkedStackOfStrings     
d
d
g
h
t
-
Poped : t
-
Poped : h
-
Poped : g
^Z
[1]  + 6003 suspended  java _5LinkedStackOfStrings
➜  src git:(master) ✗ java _5LinkedStackOfStrings < tobe.txt
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
public class _5LinkedStackOfStrings  {

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