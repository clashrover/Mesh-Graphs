public class ArrayList <T> {
	private T[] arr = (T[])new Object[1];
	private int size=1;
	private int currentSize=0;
	public void add(T e){
		if(isFull()==true){
			T[] arr1 = (T[])new Object[size*2];
			size=size*2;
			for(int i=0;i<currentSize;i++){
				arr1[i]=arr[i];
			}
			arr1[currentSize]=e;
			currentSize++;
			arr=arr1;
		}else{
			arr[currentSize]=e;
			currentSize++;
		}
	}
	public boolean isFull(){
		if(currentSize==size){
			return true;
		}
		return false;
	}
	public T get(int i){
		return arr[i];
	}
	public int size(){
		return this.currentSize;
	}
	public int arrSize(){
		return this.size;
	}
	public void set(int i,T e){
		arr[i]=e;
	}
	public T[] getArray(){
		return arr;
	}
}
