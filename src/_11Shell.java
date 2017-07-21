/*************************************************************************
 *  Compilation:  javac Shell.java
 *  Execution:    java Shell < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/21sort/tiny.txt
 *                http://algs4.cs.princeton.edu/21sort/words3.txt
 *   
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Shell < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *    
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *  
 *  % java Shell < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 *************************************************************************/
class Sl<Item>{
	

	// This class should not be instantiated.
	public Sl() {
		
	}
 	//Rearranges the array in ascending order, using the natural order.

	public static void sort(Comparable[] a) {
		int N = a.length;

		// 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
		int h = 1;
	while (h < N/3) h = 3*h + 1; 
		while (h >= 1) {
			// h-sort the array
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
					exch(a, j, j-h);
				}
			}
			assert isHsorted(a, h); 
			h /= 3;
		}
		assert isSorted(a);
	}
	/***********************************************************************
	 *  Helper sorting functions
	 ***********************************************************************/
	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w) < 0);
	}
	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	/***********************************************************************
	 *  Check if array is sorted - useful for debugging
	 ***********************************************************************/
	private static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i-1])) return false;
		return true;
	}
	// is the array h-sorted?
	private static boolean isHsorted(Comparable[] a, int h) {
		for (int i = h; i < a.length; i++)
			if (less(a[i], a[i-h])) return false;
		return true;
	}
	// print array to standard output
	public static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.println(a[i]);
		}
	}
}
public class _11Shell {

	public static void main(String[] args) {
		Sl<String> obj = new Sl<String>();
		String[] a = StdIn.readAllStrings();
		obj.sort(a);
		obj.show(a);
	}
}