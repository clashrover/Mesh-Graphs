
public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {
	RedBlackNode<T,E> root;
	public RBTree(){
		root=null;		
	}

    @Override
    public void insert(T key, E value) {
    	if(root==null){
    		root = new RedBlackNode<T,E>(key,value);
    		root.colour=0;
    		return;
    	}
    	RedBlackNode<T,E> parent = treeSearch(key,root);
    	//System.out.println();
		RedBlackNode<T,E> child = new RedBlackNode<T,E>(key,value);
		child.colour=1;
		if(parent.getKey().compareTo(key)==0){
			System.out.println("same value given again");
			return;
		}
		child.setParent(parent);
		if(key.compareTo(parent.getKey()) < 0){
			parent.setLeft(child);
		}else if(key.compareTo(parent.getKey())>0){
			parent.setRight(child);
		}else if(key.compareTo(parent.getKey())==0){
			parent.setRight(child);
		}
		RedBlackNode<T,E> gp = parent.getParent();
		if(gp!=null){
			//System.out.println("gp is "+ gp.getValue());
			RedBlackNode<T,E> uncle;
			if(gp.getKey().compareTo(parent.getKey())<0){
				uncle=gp.getLeft();
			}else{
				uncle=gp.getRight();
			}
			if(parent.colour==1){
				treeBalance(gp,parent,child,uncle);
			}
		}
		return;
    }

    public void treeBalance(RedBlackNode gp, RedBlackNode parent, RedBlackNode child, RedBlackNode uncle){
    	RedBlackNode ggp= gp.getParent();
    	RedBlackNode m=null;
    	boolean x= false;
    	if(uncle!=null && uncle.colour==1){
    		uncle.colour=0;
	   		parent.colour=0;
	   		gp.colour=1;
	   		x=true;
	   		if(ggp==null){
	   			root=gp;
	   			root.colour=0;
	   			return;
	   		}
   		}else if(uncle!=null && uncle.colour==0){
   			m=restructure(gp,parent,child);
   			if(ggp==null){
	   			root=m;
	   			root.colour=0;	
   			}
   			return;
   		}
   		else if(uncle==null){
   			m=restructure(gp,parent,child);
   			if(ggp==null){
	   			root=m;
	   			root.colour=0;	
   			}
   			return;
   		}
   		if(ggp==null){
   			root=m;
   			root.colour=0;
   		}
   		else{
   			RedBlackNode gggp = ggp.getParent();
   			if(x==true){
				if(gggp!=null){
					if(ggp.colour==1 && gp.colour==1){
						RedBlackNode guncle;
						if(gggp.getKey().compareTo(ggp.getKey())<0){
							guncle = gggp.getLeft();
						}else{
							guncle = gggp.getRight();
						}
						treeBalance(gggp,ggp,gp,guncle);
					}
				}
   			}
   		}
    	
    }

    public RedBlackNode restructure(RedBlackNode gp, RedBlackNode parent, RedBlackNode child){
    	RedBlackNode<T,E> ggp = gp.getParent();
    	RedBlackNode m; RedBlackNode h; RedBlackNode l; RedBlackNode n1; RedBlackNode n2;
    	if(gp.getKey().compareTo(parent.getKey())<0){
    		if(parent.getKey().compareTo(child.getKey())<0){
    			l=gp; m = parent ; h=child;  n2=h.getLeft(); n1=m.getLeft();
    		}else{
    			l=gp ; m=child ; h=parent; n1=m.getLeft() ; n2=m.getRight();
    		}
    	}else{
    		if(parent.getKey().compareTo(child.getKey())<0){
    			h=gp; m = child ; l=parent; n1=m.getLeft() ; n2=m.getRight();
    		}else{
    			h=gp ; m=parent ; l=child; n1=l.getRight() ; n2=m.getRight(); //here was the mistake common in assignment 4 and 5
    		}
    	}
    	if(n1!=null){
    		n1.setParent(l);
    	}
    	if(n2!=null){
    		n2.setParent(h);
    	}
    	l.setRight(n1); h.setLeft(n2); 
    	l.setParent(m); h.setParent(m);
    	m.setLeft(l); m.setRight(h) ;  
    	m.setParent(ggp);
    	if(ggp!=null){
    		if(ggp.getKey().compareTo(m.getKey())<0){
    			ggp.setRight(m);
    		}else{
    			ggp.setLeft(m);
    		}	
    	}
    	m.colour=0;
    	h.colour=1;
    	l.colour=1;
    	return m;
    	/*System.out.println("-------");
    	System.out.println("Root is: " +root.getKey() + " " +root.getLeft().getKey());
    	System.out.println(m.getKey());
    			System.out.println(m.getLeft().getKey());
    			System.out.println(m.getRight().getKey());
    			System.out.println("~~~~~~~~");*/
    }


	public RedBlackNode<T,E> treeSearch(T key,RedBlackNode<T,E> r){
		if(r==null){
			return null;
		}
		if(key.compareTo(r.getKey())==0){
			//System.out.println(r.getValue());
			return r;
		}
		else if(key.compareTo(r.getKey())<0){
			//System.out.print("L");
			if(r.getLeft()==null){
				
				return r;
			}else{
				
				return treeSearch(key,r.getLeft());
			}	
		}
		else if(key.compareTo(r.getKey())>0){
			//System.out.print("R");
			if(r.getRight()==null){
				return r;
			}else{
				return treeSearch(key,r.getRight());
			}	
		}
		return null;
	}

    @Override
    public RedBlackNode<T, E> search(T key) {
    	if(root==null){
    		return null;
    	}
        RedBlackNode<T,E> p = treeSearch(key,root);
        if(p.getKey().compareTo(key)==0){
        	return p;
        }else{
        	RedBlackNode<T,E> temp = new RedBlackNode<T,E>();
        	return temp;
        }
    }
}
