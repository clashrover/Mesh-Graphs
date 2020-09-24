public class Component{
	private ArrayList<Point> r;
	public Component(ArrayList<Point> r){
		this.r=r;
	}
	public ArrayList<Point> getPoints(){
		return r;
	}
	public String toString(){
		String s="";
		for(int i=0;i<r.size();i++){
			s=s+r.get(i).toString() + " ";
		}
		return s;
	}
}