package src.vistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import src.AlgoritmoGenetico;
import src.patrones.AGobserver;
import src.utils.BinTree;
import src.utils.TreeDrawer;

public class TreeView extends JPanel implements AGobserver{

	BinTree tree;

	public TreeView(AlgoritmoGenetico ag) {
		super();
		ag.addObserver(this);
	}

	@Override
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);

		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// clear with a background color
		g.setColor(Color.BLACK);
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setFont(new java.awt.Font("Arial", 1, 20));

		if(tree != null)
			TreeDrawer.paintTree(tree, g, getWidth()/2, 20);
		//paintTree(graphics);
		
	}

	private void paintTree(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.drawString(tree.toString(), getWidth()/2,20);
		paintNode(graphics, tree, getWidth()/2, 20, 1);
		
	}
	private void paintNode(Graphics graphics, BinTree node, int x, int y, int height) {
		if(node.getLeftChild() != null) {
			graphics.drawLine(x, y, x-50, y+50);
			graphics.drawString(node.getLeftChild().toString(), x-50, y+50);
			paintNode(graphics, node.getLeftChild(), x-50, y+50, height+1);
		}
		if(node.getRightChild() != null) {
			graphics.drawLine(x, y, x+50, y+50);
			graphics.drawString(node.getRightChild().toString(), x+50, y+50);
			paintNode(graphics, node.getRightChild(), x+50, y+50, height+1);
		}
	}

	@Override
	public void onInit(AlgoritmoGenetico ag) {
	}

	@Override
	public void onChange(AlgoritmoGenetico ag) {
	}

	@Override
	public void onError(String err) {
	}

	@Override
	public void onEnd(AlgoritmoGenetico ag) {
		this.tree = (BinTree) ag.getMejorAbs().getGenotipo();
		repaint();
	}
	
}
