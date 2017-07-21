/******************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch whitelist.txt < input.txt
 *  Dependencies: In.java StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/11model/tinyW.txt
 *                http://algs4.cs.princeton.edu/11model/tinyT.txt
 *                http://algs4.cs.princeton.edu/11model/largeW.txt
 *                http://algs4.cs.princeton.edu/11model/largeT.txt
 *
 *  % java BinarySearch tinyW.txt < tinyT.txt
 *  50
 *  99
 *  13
 *
 *  % java BinarySearch largeW.txt < largeT.txt | more
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [367,966 total values]
 *  
 ******************************************************************************/
import java.util.*;
import java.io.*;
public class _1BinarySearch {

	private _1BinarySearch() {
		
	}
public static int indexOf(int[] a, int key) {
	int lo = 0;
	int hi = a.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if(key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else 
				return mid;
		}
	return -1;
	}
	public static int rank(int key, int[] a) {
		return indexOf(a, key);
	}
	public static void main(String[] args) {
		In in = new In(args[0]);
		int[] whitelist = in.readAllInts();
	
		// sort the array
		Arrays.sort(whitelist);
		// read integer key from standard input; print if not in whitelist
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (_1BinarySearch.indexOf(whitelist, key) == -1)
				StdOut.println(key);
		}
	}
}