import java.util.Scanner;

/***
 * Complete QuickUnion @ cmabdullah
➜  src git:(master) ✗ javac _3QuickUnionLazyApproach.java
➜  src git:(master) ✗ java _3QuickUnionLazyApproach <tinyUF.txt
Union :4 3
Union :3 8
Union :6 5
Union :9 4
Union :2 1
Connected :8 9
Union :5 0
Union :7 2
Union :6 1
Connected :1 0
Connected :6 7
➜  src git:(master) ✗ 
 * */
class QuickUnionUF{
	private int[] id;
	public QuickUnionUF(int N){
	id = new int[N];
		for (int i = 0; i < N; i++){
			id[i] = i;
		}
	}
	private int root(int i){
		while (i != id[i]){
			i = id[i];
		}
	return i;
	}
	public boolean connected(int p, int q){
	return root(p) == root(q);
	}
	public void union(int p, int q){
	int i = root(p);
	int j = root(q);
	id[i] = j;
	}
}
public class _3QuickUnionLazyApproach {

	public static void main(String[] args){
		
	int N = StdIn.readInt();
	QuickFindUF uf = new QuickFindUF(N);
		while (!StdIn.isEmpty()){
		int p = StdIn.readInt();
		int q = StdIn.readInt();
			if (!uf.connected(p, q)){
			uf.union(p, q);
			StdOut.println("Union :"+p + " " + q);
			}
			else{
				StdOut.println("Connected :"+p + " " + q);
			}
		}
	}

}
