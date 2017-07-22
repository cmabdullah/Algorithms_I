# Algorithms

Installation <br />
------------
Download the libraries for MAC and Linux (algs4.jar,stdlib.jar) <br />
for **windows** see this [libraries](https://github.com/cmabdullah/Algorithms_I/tree/master/libraries)

The last stable release is available on sudo and can be installed with `` sudo ``:: <br />


    $ sudo wget http://algs4.cs.princeton.edu/code/algs4.jar
    $ sudo wget http://algs4.cs.princeton.edu/code/stdlib.jar


* Java wrapper scripts from javac-algs4 and java-algs4


MAC <br />


    $ sudo wget http://algs4.cs.princeton.edu/mac/java-algs4
    $ sudo wget http://algs4.cs.princeton.edu/mac/javac-algs4


Linux <br />


	$ cd /usr/local
	$ sudo mkdir algs4
	$ sudo chmod 755 algs4
	$ cd algs4
	$ pwd
	$ /usr/local/algs4
	$ sudo wget http://algs4.cs.princeton.edu/code/algs4.jar
	$ sudo wget http://algs4.cs.princeton.edu/code/stdlib.jar
	$ sudo wget http://algs4.cs.princeton.edu/linux/javac-algs4
	$ sudo wget http://algs4.cs.princeton.edu/linux/java-algs4
	$ sudo chmod 755 javac-algs4 java-algs4
	$ sudo mv javac-algs4 /usr/local/bin
	$ sudo mv java-algs4 /usr/local/bin


Documentation <br />
-------------


* Documentation of Linux is available online: http://algs4.cs.princeton.edu/linux/
* Documentation of MAC is available online: http://algs4.cs.princeton.edu/mac/
* Documentation of Windows is available online: http://algs4.cs.princeton.edu/windows/


## Very Basic Algorithms that every computer programmer should know.

* 01 BinarySearch
* 02 QuickFind
* 03 Quick UnionLazy Approach
* 04 Quick Union Weighting Approach
* 05 Linked Stack Of Strings
* 06 Fixed Capacity Stack Of Strings
* 07 Resizing Array Stack Of Strings
* 08 Linked Queue Of Strings
* 09 Generic Stack Linked List Implementation
* 10 Iterator Stack Linked List Implementation
* 11 Bag API
* 11 Shell Sort
* 12 DijkstraS Two Stack Algorithm
* 13 MergeSort
* 14 Merge Sort Borrom Up
* 15 Quick Sort
* 16 ~~SelectionSort (upcomming)~~
* 17 Quick Sort 3way
* 18 System Sort
* 19 Minimum Priority Queue
* 20 Maximum Priority Queue
* 21 Heap
* 22 Binary Search Tree(not completed properly)
* 23 Binary Search
* 24 ~~Binary Heap(uc 24)~~
* 25 ~~B tree(uc 33)~~
* 26 ~~Insertion Sort(uc 21)~~
* 27 ~~Kunath Suffel Sort(uc 21)~~
* 28 ~~Brute force (14)~~
* 29 ~~Interval search(running)~~
* 30 ~~RedBlackBST~~
* 31 ~~KD Tree~~


## Input Method
```cpp
public static void main(String[] args){	
	StdOut.println("Enter range :");
	int v = StdIn.readInt();
	String[] a = new String[v];
	heap obj = new heap();
	for (int i = 0;i< v; i++){
	a[i] = StdIn.readString();
}
```

[All Slide](http://algs4.cs.princeton.edu/home/)