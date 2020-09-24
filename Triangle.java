public class Triangle implements TriangleInterface{
	public int count;
	private Point x1;
	private Point x2;
	private Point x3;
	public Edge e1;
	public Edge e2;
	public Edge e3;
	public ArrayList<Triangle> adjTriangle;
    public boolean mark;
    public ArrayList<Triangle> pathlist;
	public Triangle(Point y1,Point y2,Point y3,int count,Edge e1,Edge e2,Edge e3){
		this.x1=y1;this.x2=y2;this.x3=y3;
		adjTriangle = new ArrayList<Triangle>();
		this.e1=e1;this.e2=e2;this.e3=e3;
		this.count=count; mark=false;
        pathlist = new ArrayList<Triangle>();
	} 
    public PointInterface [] triangle_coord(){
    	PointInterface[] pts = new Point[3];
    	pts[0]=x1;pts[1]=x2;pts[2]=x3;
    	return pts;
    }
    public boolean equals(Triangle t){
    	PointInterface[] pts = t.triangle_coord();
    	if((pts[0].equals(x1)==true && pts[1].equals(x2)==true && pts[2].equals(x3)==true) || 
    		(pts[0].equals(x2)==true && pts[1].equals(x3)==true && pts[2].equals(x1)==true) || 
    		(pts[0].equals(x3)==true && pts[1].equals(x1)==true && pts[2].equals(x2)==true) || 
    		(pts[0].equals(x2)==true && pts[1].equals(x1)==true && pts[2].equals(x3)==true) || 
    		(pts[0].equals(x3)==true && pts[1].equals(x2)==true && pts[2].equals(x1)==true) ||
    		(pts[0].equals(x1)==true && pts[1].equals(x3)==true && pts[2].equals(x2)==true)){
    		return true;
    	}
    	return false;
    }
    public boolean isAdjacent(Triangle t){
    	if(t.e1.equals(this.e1)==true || t.e1.equals(this.e2)==true || t.e1.equals(this.e3)==true || 
    		t.e2.equals(this.e1)==true || t.e2.equals(this.e2)==true || t.e2.equals(this.e3)==true || 
    		t.e3.equals(this.e1)==true || t.e3.equals(this.e2)==true || t.e3.equals(this.e3)==true ){
    		return true;
    	}
    	return false;
    }
    public String toString(){
    	String s = x1.toString()+ " " + x2.toString() + " " + x3.toString();
    	return s;
    }
}