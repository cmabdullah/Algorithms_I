import java.util.Arrays;

public class _18SystemSort {

	public static void main(String[] args) {
		StdOut.println("Enter range :");
		int p = StdIn.readInt();
		String[] a = new String[p];
		for (int i = 0;i< p; i++){
			a[i] = StdIn.readString();
		}
		Arrays.sort(a);
			for (int i = 0; i < p; i++)
	StdOut.print(a[i]);	
	}
}
