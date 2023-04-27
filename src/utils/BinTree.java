package src.utils;

public class BinTree<T> {

	private T elem;
	private BinTree<T> left;
	private BinTree<T> right;

	private Integer height;
	private Integer numNodes;

	
	public BinTree(){
		this.elem = null; 
		this.height = null;
		this.numNodes = null;
	}

	public BinTree(BinTree<T> tree){
		this.elem = tree.getElem();
		this.right = tree.getRightChild();
		this.left = tree.getLeftChild();
		this.height = tree.getHeight();
		this.numNodes = tree.getNumNodes();
	}

	public BinTree(T elem){
		this.elem = elem;
		this.height = 1;
		this.numNodes = 1;
	}

	public BinTree(BinTree<T> izq, BinTree<T> der, T elem){
		this.elem = elem;
		this.left = izq;
		this.right = der;
		this.height = Math.max(izq.getHeight(), der.getHeight()) + 1;
		this.numNodes = izq.getNumNodes() + der.getNumNodes() + 1;
	}

	//public boolean isEmpty(){return root == null;}
	public boolean isLeaf(){return left == null && right == null;}
	public BinTree<T> getRightChild(){return right;}
	public BinTree<T> getLeftChild(){return left;}
	public T getElem(){return elem;}

	public void setTree(BinTree<T> tree){
		this.elem = tree.elem;
		this.left = tree.left;
		this.right = tree.right;
		this.height = null;
		this.numNodes = null;
	}

	public Integer getHeight(){
		if (height == null)
			height = calculateHeight(this);
		return height;
	}

	public Integer getNumNodes(){
		if(numNodes == null)
			numNodes = calculateNumNodes(this);
		return numNodes;
	}

	private Integer calculateHeight(BinTree<T> root){
		if(root == null) return 0;
		return Math.max(calculateHeight(left), calculateHeight(right)) + 1;
	}

	private Integer calculateNumNodes(BinTree<T> root){
		if(root == null) return 0;
		return calculateNumNodes(left) + calculateNumNodes(right) + 1;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("(");
		if(right != null) sb.append(right.toString());
		sb.append(elem.toString());
		if(left != null) sb.append(left.toString());
		sb.append(")");
		
		return sb.toString();
	}

}
