public class Point implements PointInterface{
	public float x,y,z;
	public ArrayList<Triangle> ptAdjTriangle;
	public Point(float x,float y,float z){
		this.x=x;this.y=y;this.z=z;
		ptAdjTriangle = new ArrayList<Triangle>();
	}
	public float getX(){return x;}
   	public float getY(){return y;}
   	public float getZ(){return z;}
   	float[] coord = new float[3];
	//[x,y,z]  3 dimensions first is x second y and third is z.
	// This order should be followed

    public float [] getXYZcoordinate(){
    	coord[0]=x;coord[1]=y;coord[2]=z;
    	return coord;
    }
    public boolean equals(Point p){
    	float[] c= new float[3];
    	c[0]=p.x; c[1]=p.y; c[2]=p.z;
    	if(c[0]==x && c[1]==y && c[2]==z){
    		return true;
    	}
      	return false;
    }
    public String toString(){
        String s = "["; 
        if(x==0){
            s=s+"0, ";
        }else{
            s=s+ Float.toString(x)+ ", ";
        }
        if(y==0){
            s=s+"0, ";
        }else{
            s=s+ Float.toString(y)+ ", ";
        }
        if(z==0){
            s=s+"0";
        }else{
            s=s+ Float.toString(z);
        }
        s=s+"]";
        return s; 
    }

    public int compareTo(Point pt){
    	String s = this.toString();
    	String k= pt.toString();
    	return s.compareTo(k);
    }
}