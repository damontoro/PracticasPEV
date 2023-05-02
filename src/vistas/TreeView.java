package src.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import src.AlgoritmoGenetico;
import src.patrones.AGobserver;
import src.problema.ProblemaRegSim;
import src.problema.ProblemaRegSim.Symbol;
import src.utils.BinTree;
import src.utils.TreeDrawer;

public class TreeView extends JPanel implements AGobserver{

	BinTree<Symbol> tree;

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

		
		if(tree != null){
			updatePrefferedSize();
			TreeDrawer.paintTree(tree, g, this.getWidth()/2, 20);
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
		if(ag.getProblema().getClass() == ProblemaRegSim.class){
			this.tree = (BinTree<Symbol>) ag.getMejorAbs().getGenotipo();
		}
		else
			this.tree = null;
		repaint();
	}

	private void updatePrefferedSize() {
        int maxW = 0;
        int maxH = 400;
        maxW += TreeDrawer.getWidth(tree) * 1.5;

        if (maxW > getWidth() || maxH > getHeight()) {
            setPreferredSize(new Dimension(maxW, maxH));
            setSize(new Dimension(maxW, maxH));
        }
    }
}
