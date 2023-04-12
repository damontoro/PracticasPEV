package src.utils;

import src.problema.ProblemaRegSim;
import src.problema.ProblemaRegSim.Literals;

public class BinTree {

	private Node root;
	private int height;

	public static class Node{
		private Literals id; //Either literal, mul, sub, add
		private Integer value; //Esto solo sirve si el id es literal

		Node left;
		Node right;

		public Node(){
			id = null;
			value = null;
			left = null;
			right = null;
		}

		public Node(Literals id, Integer value){
			this.id = id;
			this.value = value;
			left = null;
			right = null;
		}

		public Literals getId(){return id;}
		public Integer getValue(){return value;}
	}
	
	public BinTree(){root = null; height = 0;}
	public BinTree(Literals id, Integer value){root = new Node(id, value); height = 1;}
	public BinTree(Node root){this.root = root; height = 1;}


	public BinTree getRightChild(){return new BinTree(root.right);}
	public BinTree getLeftChild(){return new BinTree(root.left);}
	public Node getRoot(){return root;}

	public void setRightChild(BinTree right){root.right = right.getRoot();}
	public void setLeftChild(BinTree left){root.left = left.getRoot();}


}
