README Assignment 6 graphs
By Sagar Sharma
2018CS10378

/////////

class Point{
	ArrayList<Triangle> ptAdjTriangle : stores the triangle containing this point;
	compareTo(Point p){
		converts x,y,z coordinate into string, I have used comma "," to seperate x and y and z coordinate;
	}
	equals(Point p){
		we compare floats x,y,z, of this and p;
	}
	rest all are trivial. 
}

/////////

class Edge{
	Point s,d;  are source and destination;
	ArrayList<Triangle> arrTriangle : stores the triangles having this edge;
	
	public boolean equals(Edge e){
		check if (s==e.s && d==e.d) || (s==e.d && d==e.s)
		if condition satisfied then  
	}
	lenght(){
		return length^2 of edge;
	}
}

/////////

class ArrayList{
	In this i created a resizable array. Size doubles when capacity is reached;
	add(T e){
		add e to the end of array; If capacity is full then resize the array copy previous elements and then add e;
		Complexity: O(1) amortised; O(n) in worst case; O(1) in best case;
	}
	rest all are trivial;
}

/////////

class RedBlackNode{
	consists colour, child reference, parent refernce, array list.
}

class RBTree{
	insert() : O(h) for finding position + O(h) for colouring and recuring up + O(1) restructure thus O(h) in worst case O(1) in best
		O(logn);
	treeBalance : used in insert O(h) in worst case O(1) in best
		O(logn): in worst case
	restructure: O(1);
		O(1);
	search : O(h) in worst case O(1) in best i
}

///////////

class Component{
	It consists an array list of triangles making this component;
}

///////////

CLASS MAXHEAP{
	I used a counter and attached with the person wehn we insert a person to define FIFO.

	INSERT: worst case O(logn)
		best case O(1);
	
	EXTRACTMAX
		worst case O(1)+O(logn) = O(logn);
		best case O(1);
		
	RESTRUCTURELIST called inside INSERT
		worst case O(logn);
		best case O(1);
	
	RESTRUCTUREEXTRACT caled inside EXTRACTMAX
		worst case O(logn);
		best case O(1);
	
	SEARCH O(n);
}

///////////

Class Queue{
	Simple queue; enqueue() => adds the element at the end; dequeue()=> removes element from the front;
}

///////////

class Position{
	trivial class;
}

///////////

class Shape{
QUERIES:

ADD_TRIANGLE X1 Y1 Z1  X2 Y2 Z2 X3 Y3 Z3 {
	Functionality is same as given on webpage;
	What i did is added the point, edges and triangle to their respective rbtree and adjacency list;
	Adding to RBTree time complexity for triangle O(logn); for edges O(logm)<= O(n);
	Adding to adjacency list O(n) because i have to add newly created triangle to adj list of all triangle adjacent to it.
}

TYPE_MESH{
	Checked through all the edges and decided the mesh type; O(m)<= O(n^2);
}


COUNT_CONNECTED_COMPONENTS{
	let no of components be x; no of edges in this component y;
	bfs on unmarked triangles.
	Complexity: O(x*O(x+y)) this gives O(x^2+xm)
}

BOUNDARY_EDGES{ m => no of edges;
	Traverse through all edges to give boundary edges;
	O(m) 
	Sorting using mergesort: O(mlogm);
	Final complexity O(mlogm);
}

NEIGHBORS_OF_TRIANGLE X1 Y1 Z1 X2 Y2 Z2 X3 Y3 Z3{
	find the triangle using red black tree O(logn);
	sorting using mergesort O(nlogn)
	final complexity O(nlogn);
}

EDGE_NEIGHBOR_TRIANGLE X1 Y1 Z1 X2 Y2 Z2 X3 Y3 Z3{
	find triangle using red black tree O(logn)
	return its edges O(1);
}


VERTEX_NEIGHBOR_TRIANGLE X1 Y1 Z1 X2 Y2 Z2 X3 Y3 Z3{
	find triangle using red black tree O(logn)
	return its points O(1);
}

EXTENDED_NEIGHBOR_TRIANGLE X1 Y1 Z1 X2 Y2 Z2 X3 Y3 Z3{
	find the triangle in rbtree O(logn)
	use the ptAdjTriangle to find the extended neighbour O(n^2) to avoid duplicates.
	sort using merge sort O(nlogn);
}

IS_CONNECTED Xa1 Ya1 Za1 Xa2 Ya2 Za2 Xa3 Ya3 Za3 Xb1 Yb1 Zb1 Xb2 Yb2 Zb2 Xb3 Yb3 Zb3{
	find the triangle reference using rbtree O(logn)
	Use bfs. O(m+n);
}

INCIDENT_TRIANGLES X Y Z{
	find point from rbtree O(logn);
	use ptAdjTriangle to give the ans;
	mergesort O(nlogn);
}

NEIGHBORS_OF_POINT X Y Z{
	find the point in rbtree; get triangle from ptAdjlist;O(logn)
	get point of those triangle avoid duplicates.O(n^2)
	
}

EDGE_NEIGHBORS_OF_POINT X1 Y1 Z1{
	find the edge in rbtree; get triangle from ptAdjlist;O(logm)
	get edge of those triangle avoid duplicates.O(n^2)
}

FACE_NEIGHBORS_OF_POINT X1 Y1 Z1{
	same as incident triangle
}

TRIANGLE_NEIGHBOR_OF_EDGE X1 Y1 Z1 X2 Y2 Z2{
	find the edge in edgeStore rbtree. O(longm);
	get arrTriangle of this edge.
	Sorting using mergeSort O(nlogn);
}

MAXIMUM_DIAMETER{
	find max size component using bfs O(nm+n^2);
	bfs on each triangle of max component
	O(nm+n^2). unmark the triangles O(n);
}

CENTROID{
	Divide into components using bfs O(nm+n^2);
	calculate centroid of each component. bfs on the component O(n+m).  handle duplicates O(n). Thus modified bfs is O(n^2+mn); sorting of centroids using mergesort;
	final complexity: O(n^3+m*n^2)
}

CENTROID OF COMPONENT X Y Z{
	find any triangle consisting this point O(logn);
	bfs on using that triangle O(n+m) but due to handling duplicates O(n^2+nm);
	final complexity O(n^2+nm);
}


CLOSEST COMPONENTS{
	divide into components O(n^2+nm);
	for each pair of component calculate shortest distance between them. O(n^2) for each pair of component;
	thus complexity (h*h*n*n) where h is the number of component. Note: that as h goes up O(n^2) will not be the complexity of    		finding shortest distance;
}

}



