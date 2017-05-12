
public class _14MergeSortBorromUp {

	// This class should not be instantiated.
	private _14MergeSortBorromUp() { 
		
	}
	// stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		// copy to aux[]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k]; 
		}
		// merge back to a[]
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {
			if      (i > mid)              a[k] = aux[j++];  // this copying is unnecessary
			else if (j > hi)               a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else                           a[k] = aux[i++];
		}
	}
	/**
	 * Rearranges the array in ascending order, using the natural order.
	 * @param a the array to be sorted
	 */
	public static void sort(Comparable[] a) {
		int N = a.length;
		Comparable[] aux = new Comparable[N];
		for (int n = 1; n < N; n = n+n) {
			for (int i = 0; i < N-n; i += n+n) {
				int lo = i;
				int m  = i+n-1;
				int hi = Math.min(i+n+n-1, N-1);
				merge(a, aux, lo, m, hi);
			}
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
			if (less(a[i], a[i-1]))
				return false;
		return true;
	}
	// print array to standard output
	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.println(a[i]);
		}
	}
	public static void main(String[] args) {
		//String[] a = StdIn.readAllStrings();
		int p = StdIn.readInt();
		String[] a = new String[p];
		for (int i = 0;i< p; i++){
			a[i] = StdIn.readString();
		}
		_14MergeSortBorromUp.sort(a);
		show(a);
	}

}
