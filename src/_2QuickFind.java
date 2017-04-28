/***
 * Complete quick fiend @ cmabdullah
➜  src git:(master) ✗  javac _2QuickFind.java
➜  src git:(master) ✗ java _2QuickFind < tinyUF.txt
Union 4 3
Union 3 8
Union 6 5
Union 9 4
Union 2 1
Connected :8 9
Union 5 0
Union 7 2
Union 6 1
Connected :1 0
Connected :6 7
➜  src git:(master) ✗ 
 * */
class QuickFindUF{
	private int[] id;
	public QuickFindUF(int N){
		id = new int[N];
		for (int i = 0; i < N; i++){
			id[i] = i;			
		}
	}
	public boolean connected(int p, int q){
		return id[p] == id[q];
	}
	public void union(int p, int q){
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++)
		if (id[i] == pid) id[i] = qid;
	}
}
public class _2QuickFind {

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
