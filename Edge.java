public class Edge implements EdgeInterface{
	private Point s;
	private Point d;
	public ArrayList<Triangle> arrTriangle;
	public Edge(Point p1,Point p2){
		s=p1; d=p2; arrTriangle = new ArrayList<Triangle>();
	}
	public PointInterface [] edgeEndPoints(){
		PointInterface[] p = new PointInterface[2];
		p[0]=s; p[1]=d;
		return p;
	}
	public boolean equals(Edge edg){
		Point x1 = edg.getSource(); Point x2 = edg.getDest();
		if(x1.equals(this.s)==true && x2.equals(this.d)==true){
			return true;
		}
		if(x1.equals(this.d)==true && x2.equals(this.s)==true){
			return true;
		}
		return false;
	}
	public String toString(){
		String str = "[" +s.toString() +  "," + d.toString() + "]";
		return str; 
	}
	public Point getSource(){
		return this.s;
	}
	public Point getDest(){
		return this.d;
	}
	public float length(){
		float x = s.getX()-d.getX();
		float y = s.getY()-d.getY();
		float z =s.getZ()-d.getZ();
		x=x*x; y=y*y; z=z*z;
		return x+y+z;
	}
}