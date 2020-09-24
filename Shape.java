

public class Shape implements ShapeInterface
{
	RBTree <String,Triangle> rbt ;
	RBTree <String,Point> ptStore ;
	RBTree <String,Edge> edgeStore;
	ArrayList<Triangle> triangle;
	ArrayList<Edge> edges;
	int count=0;
	int mesh=1;
	public Shape(){
		triangle = new ArrayList<Triangle>();
		edges=new ArrayList<Edge>();
		rbt = new RBTree();
		ptStore = new RBTree();
		edgeStore =new RBTree();
	}
	//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
	public boolean ADD_TRIANGLE(float [] triangle_coord){
		if(isValid(triangle_coord)==false){
			return false;
		}
		////// getting references of triangle vertices from ptStore tree
		boolean q1=false,q2=false,q3=false; Point x11=null;Point x12=null;Point x13=null;
		Point x1 = new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		RedBlackNode ptNode=ptStore.search(x1.toString());
		if(ptNode==null){
			ptStore.insert(x1.toString(),x1); q1=true;
		}else{
			x11 = (Point) ptNode.getValue();
			if(x11==null){
				ptStore.insert(x1.toString(),x1); q1=true;
			}
		}
		Point x2 = new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		ptNode=ptStore.search(x2.toString());
		if(ptNode==null){
			ptStore.insert(x2.toString(),x2); q2=true;
		}else{
			x12 = (Point) ptNode.getValue();
			if(x12==null){
				ptStore.insert(x2.toString(),x2); q2=true;
			}
		}
		Point x3 = new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		ptNode=ptStore.search(x3.toString());
		if(ptNode==null){
			ptStore.insert(x3.toString(),x1); q3=true;
		}else{
			x13 = (Point) ptNode.getValue();
			if(x13==null){
				ptStore.insert(x3.toString(),x3); q3=true;
			}
		}
		if(q1==false){x1=x11;}
		if(q2==false){x2=x12;}
		if(q3==false){x3=x13;}
		//////////////////

		Edge e1= new Edge(x1,x2);
		Edge e2= new Edge(x2,x3);
		Edge e3= new Edge(x3,x1);
		boolean r1=false,r2=false,r3=false;
		for(int i=0;i<edges.size();i++){
			if(e1.equals(edges.get(i))==true){e1=edges.get(i);r1=true;}
			if(e2.equals(edges.get(i))==true){e2=edges.get(i);r2=true;}
			if(e3.equals(edges.get(i))==true){e3=edges.get(i);r3=true;}
		}
		Triangle t = new Triangle(x1,x2,x3,count,e1,e2,e3);
		x1.ptAdjTriangle.add(t);
		x2.ptAdjTriangle.add(t);
		x3.ptAdjTriangle.add(t);
		for(int i=0;i<triangle.size();i++){
			if(t.isAdjacent(triangle.get(i))==true){
				triangle.get(i).adjTriangle.add(t);
				t.adjTriangle.add(triangle.get(i));
			}

		}
		triangle.add(t);
		e1.arrTriangle.add(t); e2.arrTriangle.add(t); e3.arrTriangle.add(t); // adding traingle t to adjacentTriangle list of edge
		if(r1==false){edges.add(e1);}
		if(r2==false){edges.add(e2);}
		if(r3==false){edges.add(e3);}
		if((e1.arrTriangle.size()>2 || e2.arrTriangle.size()>2 || e3.arrTriangle.size()>2) && mesh<3){
			mesh=3;
		}
		/*for(int i=0;i<edges.size();i++){
			System.out.print(edges.get(i).toString() + " :");
			for(int j=0;j<edges.get(i).arrTriangle.size() ; j++){
				System.out.print(edges.get(i).arrTriangle.get(j).count + "  :::::: ");
			}
			System.out.println();
		}*/
		Point[] ne = (Point[])t.triangle_coord();
		String k = Float.toString(triangle_coord[0]) + "," 
					+Float.toString(triangle_coord[1]) + ","
					+Float.toString(triangle_coord[2]) + ","
					+Float.toString(triangle_coord[3]) + ","
					+Float.toString(triangle_coord[4]) + ","
					+Float.toString(triangle_coord[5]) + ","
					+Float.toString(triangle_coord[6]) + ","
					+Float.toString(triangle_coord[7]) + ","
					+Float.toString(triangle_coord[8]);
		rbt.insert(k,t);
		/*RedBlackNode n = rbt.search(k);
		Object np = n.getValue();
		System.out.println(np);*/
		count++;
		RedBlackNode edgnode = edgeStore.search(e1.toString());
		if(edgnode==null){
			edgeStore.insert(e1.toString(),e1);
		}else{
			Edge xe = (Edge) edgnode.getValue();
			if(xe==null){edgeStore.insert(e1.toString(),e1);}
		}
		edgnode = edgeStore.search(e2.toString());
		if(edgnode==null){
			edgeStore.insert(e2.toString(),e2);
		}else{
			Edge xe = (Edge) edgnode.getValue();
			if(xe==null){edgeStore.insert(e2.toString(),e2);}
		}
		edgnode = edgeStore.search(e3.toString());
		if(edgnode==null){
			edgeStore.insert(e3.toString(),e3);
		}else{
			Edge xe = (Edge) edgnode.getValue();
			if(xe==null){edgeStore.insert(e3.toString(),e3);}
		}
		return true;
	}

	public boolean isValid(float[] tc){
		float x1=tc[0];float y1=tc[1];float z1=tc[2];
		float x2=tc[3];float y2=tc[4]; float z2=tc[5];
		float x3=tc[6]; float y3=tc[7]; float z3=tc[8];
		float e1 = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) + (z1-z2)*(z1-z2);
		e1=(float)Math.sqrt(e1);
		float e2 = (x2-x3)*(x2-x3) + (y2-y3)*(y2-y3) + (z2-z3)*(z2-z3);
		e2=(float)Math.sqrt(e2);
		float e3 = (x3-x1)*(x3-x1) + (y3-y1)*(y3-y1) + (z3-z1)*(z3-z1);
		e3=(float)Math.sqrt(e3);
		/*float area2 = ((x2*y3) - (x3*y2)) - ((x1*y3)-(x3*y1)) + ((x1*y2)-(x2*y1));
		if(area2<0){area2 = area2*-1;}
		float sin1 = area2/(e1*e2);
		float sin2 = area2/(e2*e3);
		float sin3 = area2/(e3*e1);
		float dtpdt1 =  ((x1-x2)*(x2-x3)) + ((y1-y2)*(y2-y3)) + ((z1-z2)*(z2-z3));  
		float dtpdt2 =  ((x2-x3)*(x3-x1)) + ((y2-y3)*(y3-y1)) + ((z2-z3)*(z3-z1));  
		float dtpdt3 =  ((x3-x1)*(x1-x2)) + ((y3-y1)*(y1-y2)) + ((z3-z1)*(z1-z2));
		if(dtpdt1<0){dtpdt1=dtpdt1*-1;}
		if(dtpdt2<0){dtpdt2=dtpdt2*-1;}
		if(dtpdt3<0){dtpdt3=dtpdt3*-1;}
		float cos1 = dtpdt1/(e1*e2);
		float cos2 = dtpdt2/(e2*e3);
		float cos3 = dtpdt1/(e3*e1);
		float tan1 = sin1/cos1;
		float tan2 = sin2/cos2;
		float tan3 = sin3/cos3;
		System.out.println(area2 + " " + sin1 + " " + sin2 + " " + sin3 + " " + cos1 + " " + cos2 + " "+ cos3);
		System.out.println(tan1 + " "+ tan2 + " "+tan3);
		if(tan1<0.001 || tan2<0.001 || tan3<0.001) {
			return false;
		} 
		return true;*/
		
		float k3 = e1+e2;
		float k2= e1+e3;
		float k1 = e2+e3;
		k3=k3-e3;
		k2=k2-e2;
		k1=k1-e1;
		if(k3<0.001 || k2<0.001 || k3<0.001){
			return false;
		}
		return true;
	}


	public int TYPE_MESH(){
		if(mesh==3){
			return 3;
		}
		boolean x=true;
		boolean y=false;
		boolean z=false;
		for(int i=0;i<edges.size();i++){
			if(edges.get(i).arrTriangle.size()!=2){
				x=false;
			}
			if(edges.get(i).arrTriangle.size()>2){
				y=true; z=false;
			}
			if(y==false && edges.get(i).arrTriangle.size()==1){
				z=true;
			}
		}
		if(x==true){
			mesh=1;
			return  1;
		}
		if(y==true){
			mesh=3;
			return 3;
		}
		if(z==true){
			mesh=2;
			return 2;
		}
		mesh=1;
		return 1;

	}

	public EdgeInterface [] BOUNDARY_EDGES(){
		if(mesh==3){System.out.println("improper mesh"); return null;}
		ArrayList<EdgeInterface> edg = new ArrayList<EdgeInterface>();
		for(int i=0;i<edges.size();i++){
			Edge e = edges.get(i);
			if(e.arrTriangle.size()==1){
				edg.add(e);
			}
		}
		if(edg.size()==0){
			return null;
		}
		Edge[] ans = new Edge[edg.size()];
		for(int i=0;i<edg.size();i++){
			ans[i]=(Edge)edg.get(i);
		}
		//System.out.println();
		Edge[] temp= new Edge[edg.size()];
		mergeSort(ans,temp,0,edg.size()-1);
		/*for(int i=0;i<edg.size();i++){
			System.out.println(ans[i]);
		}*/
		return ans;
	}

	public void	mergeSort(Edge[] A, Edge[] temp, int s,int e){    
		if(s < e){    
			mergeSort(A, temp, s, (s+e)/2);       
			mergeSort(A, temp, 1+(s+e)/2, e); 
			for(int i=s;i<=e;i++){
				temp[i] = A[i];
			}      
			Merge(temp, A , s , e);
		}
	}

	public void Merge(Edge[] temp,Edge[] arr,int s,int e){
		int m=(s+e)/2;
		int i=s;
		int j=m+1;
		int k=s;
		while(i<=m && j<=e){
			if(temp[i].length()<=temp[j].length()){
				arr[k]=temp[i]; i++; k++;
			}else{
				arr[k]=temp[j]; j++; k++;
			}
		}
		while(i<=m){
			arr[k]=temp[i]; i++; k++;
		}
		while(j<=e){
			arr[k]=temp[j]; j++; k++;
		}
	}

	public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){
		String k = Float.toString(triangle_coord[0]) + "," 
					+Float.toString(triangle_coord[1]) + ","
					+Float.toString(triangle_coord[2]) + ","
					+Float.toString(triangle_coord[3]) + ","
					+Float.toString(triangle_coord[4]) + ","
					+Float.toString(triangle_coord[5]) + ","
					+Float.toString(triangle_coord[6]) + ","
					+Float.toString(triangle_coord[7]) + ","
					+Float.toString(triangle_coord[8]);
		RedBlackNode n = rbt.search(k);
		Triangle np = (Triangle)n.getValue();
		if(np.adjTriangle.size()==0){
			return null;
		}
		Triangle[] array = new Triangle[np.adjTriangle.size()];
		for(int i=0;i<np.adjTriangle.size();i++){
			array[i] =  np.adjTriangle.get(i);
			//System.out.println(np.adjTriangle.get(i));
		}
		Triangle[] temp = new Triangle[np.adjTriangle.size()];
		mergeSortTriangle(array,temp,0,np.adjTriangle.size()-1);
		
		/*for(int i=0;i<np.adjTriangle.size() ;i++){
			System.out.print(array[i].count + " ");
		}*/
		return array;
	}

	public void	mergeSortTriangle(Triangle[] A, Triangle[] temp, int s,int e){    
		if(s < e){    
			mergeSortTriangle(A, temp, s, (s+e)/2);       
			mergeSortTriangle(A, temp, 1+(s+e)/2, e); 
			for(int i=s;i<=e;i++){
				temp[i] = A[i];
			}      
			MergeTriangle(temp, A , s , e);
		}
	}

	public void MergeTriangle(Triangle[] temp,Triangle[] arr,int s,int e){
		int m=(s+e)/2;
		int i=s;
		int j=m+1;
		int k=s;
		while(i<=m && j<=e){
			if(temp[i].count<temp[j].count){
				arr[k]=temp[i]; i++; k++;
			}else{
				arr[k]=temp[j]; j++; k++;
			}
		}
		while(i<=m){
			arr[k]=temp[i]; i++; k++;
		}
		while(j<=e){
			arr[k]=temp[j]; j++; k++;
		}
	}

	public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		String k = Float.toString(triangle_coord[0]) + "," 
					+Float.toString(triangle_coord[1]) + ","
					+Float.toString(triangle_coord[2]) + ","
					+Float.toString(triangle_coord[3]) + ","
					+Float.toString(triangle_coord[4]) + ","
					+Float.toString(triangle_coord[5]) + ","
					+Float.toString(triangle_coord[6]) + ","
					+Float.toString(triangle_coord[7]) + ","
					+Float.toString(triangle_coord[8]);
		RedBlackNode n = rbt.search(k);
		if(n==null){
			return null;
		}
		Triangle np = (Triangle)n.getValue();
		if(np==null){
			return null;
		}
		Edge[] a = new Edge[3];
		a[0]=np.e1;a[1]=np.e2;a[2]=np.e3;
		//System.out.println(a[0].toString() + "," + a[1].toString() + "," + a[2].toString());
		return a;
	}

	public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		String k = Float.toString(triangle_coord[0]) + "," 
					+Float.toString(triangle_coord[1]) + ","
					+Float.toString(triangle_coord[2]) + ","
					+Float.toString(triangle_coord[3]) + ","
					+Float.toString(triangle_coord[4]) + ","
					+Float.toString(triangle_coord[5]) + ","
					+Float.toString(triangle_coord[6]) + ","
					+Float.toString(triangle_coord[7]) + ","
					+Float.toString(triangle_coord[8]);
		RedBlackNode n = rbt.search(k);
		if(n==null){
			return null;
		}
		Triangle np = (Triangle)n.getValue();
		if(np==null){
			return null;
		}
		/*System.out.println(np.triangle_coord()[0]);
		System.out.println(np.triangle_coord()[1]);
		System.out.println(np.triangle_coord()[2]);*/
		//System.out.println("done");
		return np.triangle_coord();
	}

	public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		String k = Float.toString(triangle_coord[0]) + "," 
					+Float.toString(triangle_coord[1]) + ","
					+Float.toString(triangle_coord[2]) + ","
					+Float.toString(triangle_coord[3]) + ","
					+Float.toString(triangle_coord[4]) + ","
					+Float.toString(triangle_coord[5]) + ","
					+Float.toString(triangle_coord[6]) + ","
					+Float.toString(triangle_coord[7]) + ","
					+Float.toString(triangle_coord[8]);

		RedBlackNode n = rbt.search(k);
		if(n==null){
			return null;
		}
		Triangle np = (Triangle)n.getValue();
		if(np==null){
			return null;
		}
		Point x1=(Point)np.triangle_coord()[0];
		Point x2=(Point)np.triangle_coord()[1];
		Point x3=(Point)np.triangle_coord()[2];
		ArrayList<Triangle> ext_neigh = new ArrayList<Triangle>();
		for(int i=0;i<x1.ptAdjTriangle.size();i++){
			if(x1.ptAdjTriangle.get(i).equals(np)==true){
				continue;
			}
			ext_neigh.add(x1.ptAdjTriangle.get(i));
		} 
		for(int i=0;i<x2.ptAdjTriangle.size();i++){
			if(x2.ptAdjTriangle.get(i).equals(np)==true){
				continue;
			}
			boolean u=false;
			for(int j=0;j<ext_neigh.size();j++){
				if(x2.ptAdjTriangle.get(i).equals(ext_neigh.get(j))==true){
					u=true; break;
				}
			}
			if(u==false){
				ext_neigh.add(x2.ptAdjTriangle.get(i));
			}
		} 
		for(int i=0;i<x3.ptAdjTriangle.size();i++){
			if(x3.ptAdjTriangle.get(i).equals(np)==true){
				continue;
			}
			boolean u=false;
			for(int j=0;j<ext_neigh.size();j++){
				if(x3.ptAdjTriangle.get(i).equals(ext_neigh.get(j))==true){
					u=true; break;
				}
			}
			if(u==false){
				ext_neigh.add(x3.ptAdjTriangle.get(i));
			}
		}
		if(ext_neigh.size()==0){
			return null;
		}
		Triangle[] ti = new Triangle[ext_neigh.size()];
		for(int i=0;i<ext_neigh.size();i++){
			//System.out.println(ext_neigh.get(i).toString()+ " " +ext_neigh.get(i).count);
			ti[i]=ext_neigh.get(i);
		}
		Triangle[] temp = new Triangle[ext_neigh.size()];
		mergeSortTriangle(ti,temp,0,ext_neigh.size()-1);
		/*for(int i=0;i<ext_neigh.size();i++){
			System.out.println(ti[i].toString()+ " " +ti[i].count);
		}*/
		return ti;
	}

	public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){
		Point s = new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		RedBlackNode np=ptStore.search(s.toString());
		if(np==null){
			return null;
		}else{
			s= (Point) np.getValue();
			if(s==null){
				System.out.println("no such point");
				return null;
			}
		}
		Triangle[] ti = new Triangle[s.ptAdjTriangle.size()];
		for(int i=0;i<s.ptAdjTriangle.size();i++){
			//System.out.println(s.ptAdjTriangle.get(i));
			ti[i]=s.ptAdjTriangle.get(i);
		}
		Triangle[] temp = new Triangle[s.ptAdjTriangle.size()];
		mergeSortTriangle(ti,temp,0,s.ptAdjTriangle.size()-1);
		/*for(int i=0;i<s.ptAdjTriangle.size();i++){
			System.out.println(ti[i].toString() + " " + ti[i].count);
		}*/
		return ti;
		
	}

	public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){
		Point s = new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		RedBlackNode np=ptStore.search(s.toString());
		if(np==null){
			return null;
		}else{
			s= (Point) np.getValue();
			if(s==null){
				return null;
			}
		}
		ArrayList<Point> adjpts = new ArrayList<Point>();
		for(int i=0;i<s.ptAdjTriangle.size();i++){
			Triangle nps = s.ptAdjTriangle.get(i);
			Point[] ps = (Point[])nps.triangle_coord();
			if(ps[0].equals(s)==false){
				boolean pj=false;
				for(int j=0;j<adjpts.size();j++){
					if(adjpts.get(j).equals(ps[0])==true){
						pj=true;break;
					}
				}
				if(pj==false){adjpts.add(ps[0]);}
			} 
			if(ps[1].equals(s)==false){
				boolean pj=false;
				for(int j=0;j<adjpts.size();j++){
					if(adjpts.get(j).equals(ps[1])==true){
						pj=true;break;
					}
				}
				if(pj==false){adjpts.add(ps[1]);}
			} 
			if(ps[2].equals(s)==false){
				boolean pj=false;
				for(int j=0;j<adjpts.size();j++){
					if(adjpts.get(j).equals(ps[2])==true){
						pj=true;break;
					}
				}
				if(pj==false){adjpts.add(ps[2]);}
			}  
		}
		PointInterface[] ans = new PointInterface[adjpts.size()];
		for(int j=0;j<adjpts.size();j++){
			//System.out.println(adjpts.get(j));
			ans[j]=adjpts.get(j);
		}
		return ans;
	}

	public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){
		Point s = new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		RedBlackNode np=ptStore.search(s.toString());
		if(np==null){
			return null;
		}else{
			s= (Point) np.getValue();
			if(s==null){
				return null;
			}
		}
		ArrayList<Edge> adjedg = new ArrayList<Edge>();
		for(int i=0;i<s.ptAdjTriangle.size();i++){
			Triangle nps = s.ptAdjTriangle.get(i);
			Edge e01=nps.e1 ;Edge e02=nps.e2 ;Edge e03=nps.e3 ;
			if((e01.edgeEndPoints()[0].equals(s)==false && e01.edgeEndPoints()[1].equals(s)==true) || (e01.edgeEndPoints()[0].equals(s)==true && e01.edgeEndPoints()[1].equals(s)==false) ){
				boolean pj=false;
				for(int j=0;j<adjedg.size();j++){
					if(adjedg.get(j).equals(e01)==true){
						pj=true;break;
					}
				}
				if(pj==false){adjedg.add(e01);}
			}
			if((e02.edgeEndPoints()[0].equals(s)==false && e02.edgeEndPoints()[1].equals(s)==true) || (e02.edgeEndPoints()[0].equals(s)==true && e02.edgeEndPoints()[1].equals(s)==false) ){
				boolean pj=false;
				for(int j=0;j<adjedg.size();j++){
					if(adjedg.get(j).equals(e02)==true){
						pj=true;break;
					}
				}
				if(pj==false){adjedg.add(e02);}
			}
			if((e03.edgeEndPoints()[0].equals(s)==false && e03.edgeEndPoints()[1].equals(s)==true) || (e03.edgeEndPoints()[0].equals(s)==true && e03.edgeEndPoints()[1].equals(s)==false) ){
				boolean pj=false;
				for(int j=0;j<adjedg.size();j++){
					if(adjedg.get(j).equals(e03)==true){
						pj=true;break;
					}
				}
				if(pj==false){adjedg.add(e03);}
			}
		}

			Edge[] es = new Edge[adjedg.size()];
			for(int i=0;i<adjedg.size();i++){
				es[i]=adjedg.get(i);
				//System.out.println(es[i]);
			}
		return es;
	}

	public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){ 
		return INCIDENT_TRIANGLES(point_coordinates);
	}

	public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){ 
	// to edges will have to be created as we are using string as the key
		Point z1 = new Point(edge_coordinates[0],edge_coordinates[1],edge_coordinates[2]);
		Point z2 = new Point(edge_coordinates[3],edge_coordinates[4],edge_coordinates[5]);
	 	Edge edg1 = new Edge(z1,z2);
	 	Edge edg2 = new Edge(z2,z1);
	 	RedBlackNode rbn1 = edgeStore.search(edg1.toString());
	 	RedBlackNode rbn2 = edgeStore.search(edg2.toString());
	 	if(rbn1!=null){
	 		if(rbn1.getValue()!=null){
	 			Edge edg3 = (Edge)rbn1.getValue();
	 			Triangle[] ti = new Triangle[edg3.arrTriangle.size()];
	 			for(int i=0;i<edg3.arrTriangle.size();i++){
	 				ti[i]= edg3.arrTriangle.get(i);
	 			}
	 			Triangle[] temp = new Triangle[edg3.arrTriangle.size()];
				mergeSortTriangle(ti,temp,0,edg3.arrTriangle.size()-1);
				/*for(int i=0;i<edg3.arrTriangle.size();i++){
					System.out.println(ti[i].toString() + " " + ti[i].count);
				}*/
	 			return ti;
	 		}
	 	}
	 	if(rbn2!=null){
	 		if(rbn2.getValue()!=null){
	 			Edge edg3 = (Edge)rbn2.getValue();
	 			Triangle[] ti = new Triangle[edg3.arrTriangle.size()];
	 			for(int i=0;i<edg3.arrTriangle.size();i++){
	 				ti[i]= edg3.arrTriangle.get(i);
	 			}
	 			Triangle[] temp = new Triangle[edg3.arrTriangle.size()];
				mergeSortTriangle(ti,temp,0,edg3.arrTriangle.size()-1);
				/*for(int i=0;i<edg3.arrTriangle.size();i++){
					System.out.println(ti[i].toString() + " " + ti[i].count);
				}*/
	 			return ti;
	 		}
	 	}
	 	return null;
	}

	public int COUNT_CONNECTED_COMPONENTS(){
		Queue<Triangle> q = new Queue();
		int c=0;
		for(int i=0;i<triangle.size();i++){
			//System.out.println(triangle.get(i));
			if(triangle.get(i).mark==false){
				//System.out.println(triangle.get(i));
				triangle.get(i).mark=true;
				q.enqueue(triangle.get(i));
				bfs(q);c++;
			}
		}
		for(int i=0;i<triangle.size();i++){
			triangle.get(i).mark=false;
		}
		return c;
	}

	public void bfs(Queue<Triangle> q){
		int d=0;
		while(q.hasNext()==true){
			d++;
			Triangle v = q.dequeue();
			for(int i=0;i<v.adjTriangle.size();i++){
				Triangle u =v.adjTriangle.get(i);
				if(u.mark==false){
					u.mark=true;
					q.enqueue(u);
				}
			}
		}
		//System.out.println(d);
	}

	public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){
		String k1 = Float.toString(triangle_coord_1[0]) + "," 
					+Float.toString(triangle_coord_1[1]) + ","
					+Float.toString(triangle_coord_1[2]) + ","
					+Float.toString(triangle_coord_1[3]) + ","
					+Float.toString(triangle_coord_1[4]) + ","
					+Float.toString(triangle_coord_1[5]) + ","
					+Float.toString(triangle_coord_1[6]) + ","
					+Float.toString(triangle_coord_1[7]) + ","
					+Float.toString(triangle_coord_1[8]);
		String k2 = Float.toString(triangle_coord_2[0]) + "," 
					+Float.toString(triangle_coord_2[1]) + ","
					+Float.toString(triangle_coord_2[2]) + ","
					+Float.toString(triangle_coord_2[3]) + ","
					+Float.toString(triangle_coord_2[4]) + ","
					+Float.toString(triangle_coord_2[5]) + ","
					+Float.toString(triangle_coord_2[6]) + ","
					+Float.toString(triangle_coord_2[7]) + ","
					+Float.toString(triangle_coord_2[8]);

		RedBlackNode node1 = rbt.search(k1); RedBlackNode node2 = rbt.search(k2);
		if(node1==null || node2==null){
			return false;
		}else{
			if(node1.getValue()==null || node2.getValue()==null){
				return false;
			}
		}
		Triangle t1 = (Triangle)node1.getValue(); Triangle t2 = (Triangle)node2.getValue();
		Queue<Triangle> q = new Queue();
		t1.mark=true;
		q.enqueue(t1);
		boolean ans= bfSearch(q,t2);
		for(int i=0;i<triangle.size();i++){
			triangle.get(i).mark=false;
		}
		return ans;
	}
	public boolean bfSearch(Queue<Triangle> q, Triangle t){
		while(q.hasNext()==true){
			Triangle v = q.dequeue();
			if(v.equals(t)==true){
				return true;
			}
			for(int i=0;i<v.adjTriangle.size();i++){
				Triangle u =v.adjTriangle.get(i);
				if(u.mark==false){
					u.mark=true;
					q.enqueue(u);
				}
			}
		}
		return false;
	}

	public int MAXIMUM_DIAMETER(){
		ArrayList<Triangle> max_size = new ArrayList<Triangle>();
		for(int i=0;i<triangle.size();i++){
			if(triangle.get(i).mark==true){
				continue;
			}
			ArrayList<Triangle> component = new ArrayList<Triangle>();
			bfs(triangle.get(i),component);
			if(component.size()>max_size.size()){
				max_size=component;
			}
		}
		for(int i=0;i<triangle.size();i++){
			triangle.get(i).mark=false;
		}
		if(max_size.size()==1){
			return 0;
		}
		int ans= getMaxbfs(max_size);
		//System.out.println(ans);
		return ans;
	}

	public void bfs(Triangle t1,ArrayList<Triangle> component){
		Queue<Triangle> q = new Queue();
		t1.mark=true;
		q.enqueue(t1);
		component.add(t1);
		while(q.hasNext()==true){
			Triangle v = q.dequeue();
			for(int i=0;i<v.adjTriangle.size();i++){
				Triangle u =v.adjTriangle.get(i);
				if(u.mark==false){
					u.mark=true;
					q.enqueue(u);
					component.add(u);
				}
			}
		}
	}

	public int getMaxbfs(ArrayList<Triangle>component){
		int max=0;
		for(int j=0;j<component.size();j++){
			int[] ans = new int[triangle.size()];
			for(int c=0;c<component.size();c++){
				ans[component.get(c).count]=2147483647;
			}
			int m=0;
			Queue<Triangle> q = new Queue();
			component.get(j).mark=true;
			ans[component.get(j).count]=0;
			q.enqueue(component.get(j));
			while(q.hasNext()==true){
				Triangle v = q.dequeue();
				for(int i=0;i<v.adjTriangle.size();i++){
					Triangle u =v.adjTriangle.get(i);
					if(u.mark==false){
						u.mark=true;
						q.enqueue(u);
						ans[u.count] = ans[v.count]+1;
					}
				}
			}
			for(int c=0;c<component.size();c++){
				component.get(c).mark=false;
				//System.out.print(ans[component.get(c).count]);
				if(m<ans[component.get(c).count]){
					m=ans[component.get(c).count];
				}
			}
			if(max<m){max=m;}
		}
		//System.out.println(max);
		return max;
	}

	public PointInterface [] CENTROID (){
		ArrayList<Triangle> max_size = new ArrayList<Triangle>();
		ArrayList<Point> ans = new ArrayList<Point>();
		for(int i=0;i<triangle.size();i++){
			if(triangle.get(i).mark==true){
				continue;
			}
			ArrayList<Triangle> component = new ArrayList<Triangle>();
			ans.add(centroidBFS(triangle.get(i),component));
		}
		for(int i=0;i<triangle.size();i++){
			triangle.get(i).mark=false;
		}
		Point[] pts = new Point[ans.size()];
		//System.out.print("[");
		for(int i=0;i<ans.size();i++){
			pts[i]=ans.get(i);
			//System.out.print(pts[i].toString() + ", ");
		}
		//System.out.println("]");
		Point[] temp = new Point[ans.size()];
		mergeSortC(pts,temp,0,ans.size()-1);
		/*for(int i=0;i<ans.size();i++){
			System.out.print(pts[i].toString() + ", ");
		}
		System.out.println();*/
		return pts;
	}

	public void	mergeSortC(Point[] A, Point[] temp, int s,int e){    
		if(s < e){    
			mergeSortC(A, temp, s, (s+e)/2);       
			mergeSortC(A, temp, 1+(s+e)/2, e); 
			for(int i=s;i<=e;i++){
				temp[i] = A[i];
			}      
			MergeC(temp, A , s , e);
		}
	}

	public void MergeC(Point[] temp,Point[] arr,int s,int e){
		int m=(s+e)/2;
		int i=s;
		int j=m+1;
		int k=s;
		while(i<=m && j<=e){
			if(comparing(temp[i],temp[j])<0){
				arr[k]=temp[i]; i++; k++;
			}else{
				arr[k]=temp[j]; j++; k++;
			}
		}
		while(i<=m){
			arr[k]=temp[i]; i++; k++;
		}
		while(j<=e){
			arr[k]=temp[j]; j++; k++;
		}
	}

	public int comparing(Point x,Point y){
		if(x.getX()<y.getX()){
			return -1;
		}else if(x.getX()==y.getX()){
			if(x.getY()<y.getY()){
				return -1;
			}else if(x.getY()==y.getY()){
				if(x.getZ()<y.getZ()){
					return -1;
				}
				return 1;
			}
			return 1;
		}
		return 1;
	}

	public Point centroidBFS(Triangle t1, ArrayList<Triangle> component){
		float x=0; float y=0; float z=0;
		ArrayList<Point> r = new ArrayList<Point>();
		Queue<Triangle> q = new Queue();
		t1.mark=true;
		q.enqueue(t1);
		component.add(t1);
		while(q.hasNext()==true){
			Triangle v = q.dequeue();
			Point[] pk = (Point[])v.triangle_coord();
			boolean r1,r2,r3; r1=false;r2=false;r3=false;
			for(int i=0;i<r.size();i++){
				if(r.get(i).equals(pk[0])==true){r1=true;}if(r.get(i).equals(pk[1])==true){r2=true;}if(r.get(i).equals(pk[2])==true){r3=true;}
			}
			if(r1==false){r.add(pk[0]);x=x+pk[0].getX(); y=y+pk[0].getY(); z=z+pk[0].getZ();}
			if(r2==false){r.add(pk[1]);x=x+pk[1].getX(); y=y+pk[1].getY(); z=z+pk[1].getZ();}
			if(r3==false){r.add(pk[2]);x=x+pk[2].getX(); y=y+pk[2].getY(); z=z+pk[2].getZ();}
			
			for(int i=0;i<v.adjTriangle.size();i++){
				Triangle u =v.adjTriangle.get(i);
				if(u.mark==false){
					u.mark=true;
					q.enqueue(u);
					component.add(u);
				}
			}
		}
		x=x/(r.size()); y=y/r.size() ; z=z/r.size();
		Point ans = new Point(x,y,z);
		return ans;
	}

	public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
		Point s = new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		RedBlackNode np=ptStore.search(s.toString());
		if(np==null){
			return null;
		}else{
			s= (Point) np.getValue();
			if(s==null){
				System.out.println("no such point");
				return null;
			}
		}
		
		Triangle tr = s.ptAdjTriangle.get(0);
		Point ans = bfsCentroid(tr);
		for(int i=0;i<triangle.size();i++){
			triangle.get(i).mark=false;
		}
		//if(ans==null){System.out.println("fwefs");}
		//System.out.println(ans);
		return ans;
	}

	public Point bfsCentroid(Triangle t1){
		float x=0; float y=0; float z=0;
		ArrayList<Point> r = new ArrayList<Point>();
		Queue<Triangle> q = new Queue();
		t1.mark=true;
		q.enqueue(t1);
		while(q.hasNext()==true){
			Triangle v = q.dequeue();
			Point[] pk = (Point[])v.triangle_coord();
			boolean r1,r2,r3; r1=false;r2=false;r3=false;
			for(int i=0;i<r.size();i++){
				if(r.get(i).equals(pk[0])==true){r1=true;}if(r.get(i).equals(pk[1])==true){r2=true;}if(r.get(i).equals(pk[2])==true){r3=true;}
			}
			if(r1==false){r.add(pk[0]);x=x+pk[0].getX(); y=y+pk[0].getY(); z=z+pk[0].getZ();}
			if(r2==false){r.add(pk[1]);x=x+pk[1].getX(); y=y+pk[1].getY(); z=z+pk[1].getZ();}
			if(r3==false){r.add(pk[2]);x=x+pk[2].getX(); y=y+pk[2].getY(); z=z+pk[2].getZ();}
			
			for(int i=0;i<v.adjTriangle.size();i++){
				Triangle u =v.adjTriangle.get(i);
				if(u.mark==false){
					u.mark=true;
					q.enqueue(u);
				}
			}
		}
		/*for(int i=0;i<r.size();i++){
			System.out.println(r.get(i));
		}*/
		//System.out.println("centroid is:");
		x=x/(r.size()); y=y/r.size() ; z=z/r.size();
		Point ans = new Point(x,y,z);
		return ans;
	}

	public 	PointInterface [] CLOSEST_COMPONENTS(){
		Point ans1=null; Point ans2=null;
		ArrayList<Component> components = new ArrayList<Component>();
		for(int i=0;i<triangle.size();i++){
			if(triangle.get(i).mark==true){continue;}
			components.add(bfsCreateComponent(triangle.get(i)));
		}
	
		float min=Float.MAX_VALUE;
		for(int i=0;i<components.size();i++){
			ArrayList<Point> pts1 = components.get(i).getPoints();
			for(int j=i+1;j<components.size();j++){
				ArrayList<Point> pts2 = components.get(j).getPoints();
				for(int k=0;k<pts1.size();k++){
					for(int p=0;p<pts2.size();p++){
						float h = dist(pts1.get(k),pts2.get(p));
						//System.out.println(pts1.get(k).toString()+ " " +pts2.get(p).toString() + " :" +h);
						if(h<min){min=h; ans1=pts1.get(k); ans2=pts2.get(p);}
					}
				}
			}
		}
		for(int i=0;i<triangle.size();i++){
			triangle.get(i).mark=false;
		}
		PointInterface[] pi = new PointInterface[2];
		pi[0]=ans1;pi[1]=ans2;
		//System.out.println(ans1.toString() + " " + ans2.toString());
		return pi;
 	}

 	public float dist(Point x1,Point x2){
 		float x = x1.getX()-x2.getX();
 		float y = x1.getY()-x2.getY();
 		float z = x1.getZ()-x2.getZ();
 		x=x*x; y=y*y; z=z*z;
 		return x+y+z;
 	}

 	public Component bfsCreateComponent(Triangle t1){
 		ArrayList<Point> r = new ArrayList<Point>();
		Queue<Triangle> q = new Queue();
		t1.mark=true;
		q.enqueue(t1);
		while(q.hasNext()==true){
			Triangle v = q.dequeue();
			Point[] pk = (Point[])v.triangle_coord();
			boolean r1,r2,r3; r1=false;r2=false;r3=false;
			for(int i=0;i<r.size();i++){
				if(r.get(i).equals(pk[0])==true){r1=true;}if(r.get(i).equals(pk[1])==true){r2=true;}if(r.get(i).equals(pk[2])==true){r3=true;}
			}
			if(r1==false){r.add(pk[0]);}
			if(r2==false){r.add(pk[1]);}
			if(r3==false){r.add(pk[2]);}
			
			for(int i=0;i<v.adjTriangle.size();i++){
				Triangle u =v.adjTriangle.get(i);
				if(u.mark==false){
					u.mark=true;
					q.enqueue(u);
				}
			}
		}
		Component ans = new Component(r);
		return ans;
 	}

}

