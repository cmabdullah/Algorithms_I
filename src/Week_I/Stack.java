package Week_I;

import java.util.Scanner;

class StackOfStrings{
	private String[] a; // stack entries
	private int N;      // size
	StackOfStrings(int cap){
			a = new String[cap];  
	}
	public boolean isEmpty(){
		return N == 0;
	}
	public int size(){
		return N;
	}
	public void push(String item){
		a[N++] = item;
	}
	public String pop(){
		return a[--N];
	}
}
public class Stack {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackOfStrings stack = new StackOfStrings(20);

		
		Scanner x = new Scanner(System.in);
		for (int i = 0;i<5; i++){
			
		}
		   while (!StdIn.isEmpty()){
		      String s = StdIn.readString();
		      if (s.equals("-")) 
		    	  StdOut.print(stack.pop());
		      else
		    	  stack.push(s);
		}
	}
}
