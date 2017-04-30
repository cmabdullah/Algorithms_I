/**
➜  src git:(master) ✗ javac _6FixedCapacityStackOfStrings.java
➜  src git:(master) ✗ java _6FixedCapacityStackOfStrings < tobe.txt
Poped : to
Poped : be
Poped : not
Poped : that
Poped : or
Poped : be
➜  src git:(master) ✗ 
 * */
class FixedCapacityStackOfStrings_Sub{
	private String[] s;
	private int N = 0;
	public FixedCapacityStackOfStrings_Sub(int capacity){
		s = new String[capacity];
	}
	public boolean isEmpty(){
		return N == 0;
	}
	public void push(String item){
		s[N++] = item;
	}
	public String pop(){
		return s[--N];
		/**
		 * prevent overflow :D
		String item = s[--N];
		s[N] = null;
		return item;
		**/
	}
}
public class _6FixedCapacityStackOfStrings{

	public static void main(String[] args){
		FixedCapacityStackOfStrings_Sub stack = new FixedCapacityStackOfStrings_Sub(5);
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
