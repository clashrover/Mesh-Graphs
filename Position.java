public class Position<T>{
	private T value;
	private Position<T> next=null;
	public Position(){
		value=null;
	}
	public Position(T e){
		this.value=e;
	}
	public boolean hasNext(){
		if(next!=null){
			return true;
		}
		return false;
	}
	public Position<T> getNext(){
		return next;
	}
	public void setNext(Position<T> p){
		next=p;
	}
	public T getValue(){
		return value;
	}

}
