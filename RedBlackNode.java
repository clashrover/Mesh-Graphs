
public class RedBlackNode<T extends Comparable, E> {
	public int colour;
	private E value;
	public RedBlackNode(){
		value=null;
	}
	private T key;
	private RedBlackNode<T,E> parent=null;
	private RedBlackNode<T,E> right=null;
	private RedBlackNode<T,E> left=null;
	public RedBlackNode(T key,E value){
		this.key = key;
		this.value=value;
	}
	public void setRight(RedBlackNode right){
		this.right = right;
	}
	public void setLeft(RedBlackNode left){
		this.left=left;
	}
	public RedBlackNode getRight(){
		return this.right;
	}
	public RedBlackNode getLeft(){
		return this.left;
	}
	public void setParent(RedBlackNode parent){
		this.parent= parent;
	}
	public RedBlackNode getParent(){
		return this.parent;
	}
	public T getKey(){
		return this.key;
	}

    public E getValue() {
        return this.value;
    }

}
