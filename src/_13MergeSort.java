//import edu.princeton.cs.algs4.Insertion;
//cm@bdullah
class SortingHelpers{
	protected static boolean less(Comparable p, Comparable q){
		return p.compareTo(q) < 0;
	}
	protected static void exch(Comparable[] a, int i, int j){
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	protected static boolean isSorted(Comparable[] a, int lo, int hi){
		for (int i = lo; i < hi; i++)
			if (less(a[i+1], a[i]))
			return false;
		return true;
	}
}
class x extends SortingHelpers{
	/* An implementation of the Merge sort algorithm */
	// This class should not be instantiated.
	x(){	
	}
	
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
		// precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid+1, hi);
		// copy a into aux
		for (int i = lo; i <= hi; i++)
			aux[i] = a[i];
		
		// merge back to a[]
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++){
			if (i > mid)					a[k] = aux[j++];
			else if (j > hi)				a[k] = aux[i++];
			else if (less(aux[i], aux[j]))	a[k] = aux[i++];
			else							a[k] = aux[j++];	
		}
		// postcondition: a[lo .. hi] is sorted
		assert isSorted(a, lo, hi);
	}
	
	//mergesort a[lo..hi] using auxiliary array aux[lo..hi]
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}
	public static void sort(Comparable[] a){
		// create an auxiliary array to hold data during merge
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length-1);
	}	
	// print array to standard output
	public static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.print(a[i]);
		}
	}
}
public class _13MergeSort {

    public static void main(String[] args) {
		// TODO Auto-generated method stub
		StdOut.println("Enter range :");
		int p = StdIn.readInt();
		String[] a = new String[p];
		x obj = new x();
		for (int i = 0;i< p; i++){
			a[i] = StdIn.readString();
		}
		obj.sort(a);
		obj.show(a);
    }
}
