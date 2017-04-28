/***
 * http://algs4.cs.princeton.edu/15uf/WeightedQuickUnionUF.java.html
* Complete _4QuickUnionWeighting @ cmabdullah
➜  src git:(master) javac _4QuickUnionWeighting.java
➜  src git:(master) java _4QuickUnionWeighting <tinyUF.txt
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
➜  src git:(master)
*/
class QuickUnion{
	private int[] id;
    private int[] sz;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components
	public QuickUnion(int N) {
		// TODO Auto-generated constructor stub
		count = N;
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++){
			id[i] = i;
			sz[i] = 1;
		}
	}
    public int count() {
        return count;
    }
	private int root(int i){
		while (i != id[i]){
			id[i] = id[id[i]];
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
		if (i == j) return;
		if (sz[i] < sz[j]){
			id[i] = j; sz[j] += sz[i];
		}
		else {
			id[j] = i;
			sz[i] += sz[j];
		}
		count--;
	}
}
public class _4QuickUnionWeighting {

	public static void main(String[] args){
		
	int N = StdIn.readInt();
	QuickUnion uf = new QuickUnion(N);
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
