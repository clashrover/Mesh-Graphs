public class Iterator<T>{
	Position<T> current;
	public Iterator(Position<T> start){
		current=start;
	}
	public boolean hasNext(){
		return current.hasNext();
	}
	public T next(){
		if(current.hasNext()==true){
			current = current.getNext();
			T val = current.getValue();	
			return val;
		}
		return null;
	}
	public Position<T> getCurrent(){
		return current;
	}
}