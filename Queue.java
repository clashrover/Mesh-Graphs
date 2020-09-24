public class Queue<T>{
	private Position<T> start;
	private Position<T> probe;
	
	public Queue(){
		probe = new Position<T>();
		start=probe;
	}
	public int size=0;
	public void add(T e){
		size++;
		Position<T> p = new Position<T>(e);
		probe.setNext(p);
		probe=probe.getNext();
	}
	
	public Iterator<T> iterator(){
		return new Iterator<T>(start);
	}
	
	public void enqueue(T e){
		this.add(e);
	}
	public T dequeue(){
		if(size==0){
			return null;
		}
		Position<T> p = start.getNext();
		size--;
		if(size==0){
			start.setNext(null);
			probe=start;
		}else{
			start.setNext(p.getNext());
		}
		return p.getValue();
	}
	public boolean hasNext(){
		if(size!=0){return true;}
		return false;
	}
}
