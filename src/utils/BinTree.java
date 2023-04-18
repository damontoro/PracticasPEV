package src.utils;

public class BinTree<T> {

	private Node<T> root;
	private Integer height;

	private static class Node<T>{
		private T elem;

		Node<T> left;
		Node<T> right;

		public Node(T elem){
			this.elem = elem;
			left = null;
			right = null;
		}

		public Node(Node<T> node){
			this.elem = node.elem;
			this.left = node.left != null ? new Node<>(node.left) : null;
			this.right = node.right != null ? new Node<>(node.right) : null;
		}

		public Node(Node<T> left, Node<T> right, T elem){
			this.elem = elem;
			this.left = new Node<>(left);
			this.right = new Node<>(right);
		}

		@Override
		public String toString(){
			return elem.toString();
		}
	}
	
	public BinTree(){
		root = null; 
		height = null;
	}

	public BinTree(BinTree<T> tree){
		root = new Node<T>(tree.root);
		height = tree.getHeight();
	}

	private BinTree(Node<T> root){
		this.root = new Node<T>(root.left, root.right, root.elem);
		height = null;
	}

	public BinTree(T elem){
		root = new Node<T>(elem);
		height = 1;
	}

	public BinTree(BinTree<T> izq, BinTree<T> der, T elem){
		root = new Node<T>(izq.root, der.root, elem);
		height = Math.max(izq.getHeight(), der.getHeight()) + 1;
	}

	public boolean isEmpty(){return root == null;}
	public boolean isLeaf(){return root.left == null && root.right == null;}
	public BinTree<T> getRightChild(){return root.right == null ? null : new BinTree<T>(root.right);}
	public BinTree<T> getLeftChild(){return root.left == null ? null : new BinTree<T>(root.left);}
	public T getElem(){return root.elem;}

	public Integer getHeight(){
		if (height == null)
			height = calculateHeight(root);
		return height;
	}

	private Integer calculateHeight(Node<T> root){
		if(root == null) return 0;
		return Math.max(calculateHeight(root.left), calculateHeight(root.right)) + 1;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		if(root == null) return "";
		sb.append("(");
		if(getLeftChild() != null) sb.append(getLeftChild().toString());
		sb.append(root.toString());
		if(getRightChild() != null) sb.append(getRightChild().toString());
		sb.append(")");
		
		return sb.toString();
	}

}
