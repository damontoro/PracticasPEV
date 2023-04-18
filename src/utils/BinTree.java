package src.utils;

import java.util.ArrayList;
import java.util.Arrays;

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
	private BinTree(Node root){this.root = root; height = 1;}

	public BinTree(BinTree tree){
		//A constructor copying the tree passed as parameter using the inorder traversal
		root = buildTree(tree.getInOrder(), 0, tree.getInOrder().size() - 1);
	}

	public BinTree(BinTree left, BinTree right, Symbols id, Integer value){
		root = new Node(id, value);
		root.left = left.getRoot();
		root.right = right.getRoot();
		height = Math.max(left.getHeight(), right.getHeight()) + 1;
	}

	public BinTree getRightChild(){return root.right == null ? null : new BinTree(root.right);}
	public BinTree getLeftChild(){return root.left == null ? null : new BinTree(root.left);}
	public int getHeight(){return height;}
	public Symbols getId(){return root.id;}
	public Integer getValue(){return root.value;}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		if(root == null) return "";
		sb.append("(");
		if(getLeftChild() != null) sb.append(getLeftChild().toString());
		sb.append(root.toString());
		if(getRightChild() != null) sb.append(getRightChild().toString());
		sb.append(")");

		if(sb.length() < 4)
			return root.toString();
		
		return sb.toString();
	}

	private Node getRoot(){return root;}

	private ArrayList<Pair<Symbols, Integer>> getInOrder(){
		ArrayList<Pair<Symbols, Integer>> list = new ArrayList<Pair<Symbols, Integer>>();
		if(root == null) return list;

		if(getLeftChild() != null) list.addAll(getLeftChild().getInOrder());
		list.add(new Pair<Symbols, Integer>(root.id, root.value));
		if(getRightChild() != null) list.addAll(getRightChild().getInOrder());

		return list;
	}

	private Node buildTree(ArrayList<Pair<Symbols, Integer>> inOrder, int start, int end) {
        if (start > end) {
            return null;
        }

        // find the middle element in the inorder traversal
        int mid = (start + end) / 2;

        // create a new node with the middle element
        Node node = new Node(inOrder.get(mid).getFirst(), inOrder.get(mid).getSecond());

        // recursively build the left and right subtrees
        node.left = buildTree(inOrder,start, mid - 1);
        node.right = buildTree(inOrder,mid + 1, end);

        return node;
    }

}
