import java.util.Arrays;

public class _23BinarySearch {

	private _23BinarySearch() { }

	public static int rank(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid]) 
				hi = mid - 1;
			else if (key > a[mid]) 
				lo = mid + 1;
			else 
				return mid;
		}
		return -1;
	}

	public static void main(String[] args) {

		// read the integers from a file
		In in = new In(args[0]);
		int[] whitelist = in.readAllInts();

		// sort the array
		Arrays.sort(whitelist);

		// read integer key from standard input; print if not in whitelist
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (rank(key, whitelist) == -1)
				StdOut.println(key);
		}
	}
}
