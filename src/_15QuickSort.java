import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Knuth;
//cm@bdullah
class SortingHelpersQuick{
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
class q extends SortingHelpersQuick{
	// Quick sort
	public static void sort(Comparable[] a){
		// randomly shuffle the array for probablistic guarantee
		Knuth.shuffle(a);
		// no auxiliary array is needed here
		sort(a, 0, a.length-1);
	}
	private static int partition(Comparable[] a, int lo, int hi){
		// choose a[lo] as partition element
		int i = lo, j = hi+1;
		while (true){
			// increment i as long as a[i] < a[lo]
			while (less(a[++i], a[lo]))
				if (i == hi)	break;
			// decrement j as long as a[j] > a[lo]
			while (less(a[lo], a[--j]))
				if (j == lo)	break;
			// check if pointers cross
			if (i >= j)	break;
			// swap pointer values
			exch(a, i, j);
		}
		// j now points to correct location of partition element
		exch(a, lo, j);
		return j;
	}

	private static void sort(Comparable[] a, int lo, int hi){
		// recursive routine
		if (hi <= lo)	return;

		int CUTOFF = 10;
		if (hi <= lo + CUTOFF - 1)	
			Insertion.sort(a, lo, hi);

		int r = partition(a, lo, hi);
		// r is already in correct location
		sort(a, lo, r-1);
		sort(a, r+1, hi);		
	}

	public static void showq(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.print(a[i]);
		}
	}
}

public class _15QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StdOut.println("Enter range :");
		int p = StdIn.readInt();
		String[] a = new String[p];
		q obj = new q();
		for (int i = 0;i< p; i++){
			a[i] = StdIn.readString();
		}
		obj.sort(a);
		obj.showq(a);
    }
}
