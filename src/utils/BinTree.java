package src.utils;

import src.problema.ProblemaRegSim;
import src.problema.ProblemaRegSim.Symbols;

public class BinTree {

	private Node root;
	private int height;

	public static class Node{
		private Symbols id; //Either literal, mul, sub, add
		private Integer value; //Esto solo sirve si el id es literal

		Node left;
		Node right;

		public Node(){
			id = null;
			value = null;
			left = null;
			right = null;
		}

		public Node(Symbols id, Integer value){
			this.id = id;
			this.value = value;
			left = null;
			right = null;
		}

		@Override
		public String toString(){
			if(id == Symbols.INT) return value.toString();
			else return id.toString();
		}

		public Symbols getId(){return id;}
		public Integer getValue(){return value;}
		public void setId(Symbols id){this.id = id;}
		public void setValue(Integer value){this.value = value;}
	}
	
	public BinTree(){root = null; height = 0;}
	public BinTree(Symbols id, Integer value){root = new Node(id, value); height = 1;}
	public BinTree(Node root){this.root = root; height = 1;}

	public BinTree(BinTree left, BinTree right, Symbols id){
		root = new Node(id, null);
		root.left = left.getRoot();
		root.right = right.getRoot();
		height = Math.max(left.getHeight(), right.getHeight()) + 1;
	}


	public BinTree getRightChild(){return root.right == null ? new BinTree() : new BinTree(root.right);}
	public BinTree getLeftChild(){return root.left == null ? new BinTree() : new BinTree(root.left);}
	public Node getRoot(){return root;}
	public int getHeight(){return height;}

	@Override
	public String toString(){
		if(root == null) return "";
		return '(' + getLeftChild().toString() + root.toString() + getRightChild().toString() + ')';
	}

}
