package BinarySearch;

class passed{
	int  start  , end  , mid_indx , key;
	void a(int[] ara, int k){
//		for (int i = 0;i<oo.length;i++){
//			System.out.print(" "+oo[i]);
//		}
		this.key = k;
		start = 0 ;
	     end = 15;
		while (start <= end) {
	         mid_indx = (start + end) / 2;
	         if (key == ara[mid_indx]) {
	             break;
	         }
	         if (key < ara[mid_indx]) {
	             end = (mid_indx - 1 );
	         }
	         else {
	             start = mid_indx + 1;
	         }
	     }
	     if (start > end) {
	    	 System.out.println( key+" is not in the array\n");
	     }
	     else {
	    	 System.out.println( ara[mid_indx]+"is found in the array. \nIt is the "+mid_indx+" th element of the array.\n" );
	     }
	}
}

public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr1 = {1, 4, 6, 8, 9, 11, 14, 15, 20, 25, 33 , 83, 87, 97, 99, 100};
		passed obj = new passed();
		int key = 97;
		obj.a(arr1, key);
	}
}
