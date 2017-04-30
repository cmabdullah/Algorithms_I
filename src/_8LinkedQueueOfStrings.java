class LinkedQuOfS{
	private Node first, last;
	private class Node{ 
	/* same as in StackOfStrings */  
	String item;
	Node next; 
	}
	public boolean isEmpty(){
		return first == null;
	}
	public void enqueue(String item){
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()){
			first = last;
		}
		else{
			oldlast.next = last;
		}
	}
	public String dequeue(){
		String item = first.item;
		first = first.next;
		if (isEmpty()){
			last = null;
		}
		return item;
	}
}
public class _8LinkedQueueOfStrings{

	public static void main(String[] args){
		LinkedQuOfS queue = new LinkedQuOfS();
		while (!StdIn.isEmpty()){
			String s = StdIn.readString();
			if (s.equals("-")){
				StdOut.println("Dequeue : "+queue.dequeue());
			}
			else{
				queue.enqueue(s);
			}
		}
	}
}