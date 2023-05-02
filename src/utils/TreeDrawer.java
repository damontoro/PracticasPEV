package src.utils;

import src.problema.ProblemaRegSim.Symbol;
import src.problema.ProblemaRegSim.Symbol.Symbols;

import java.awt.Color;
import java.awt.Graphics2D;

public class TreeDrawer {
    
    public static void paintTree(BinTree tree, Graphics2D g2d, int x_, int y_) {
        if (tree != null) {
            int x = x_;
            int y = y_;
            drawNode(tree, g2d, x, y);
            if (tree.getLeftChild() != null) {
                int x2 = x - getWidth(tree.getLeftChild().getRightChild()) - 30;
                int y2 = y + 60;
                drawEdge(x, y, x2, y2, g2d);
				drawNode(tree, g2d, x, y);
                paintTree(tree.getLeftChild(), g2d, x2, y2);
            }
            if (tree.getRightChild() != null) {
                int x2 = x + getWidth(tree.getRightChild().getLeftChild()) + 30;
                int y2 = y + 60;
                drawEdge(x, y, x2, y2, g2d);
				drawNode(tree, g2d, x, y);
                paintTree(tree.getRightChild(), g2d, x2, y2);
            }
        }
    }

    private static void drawNode(BinTree tree, Graphics2D g2d, int x, int y) {
        
		//g2d.drawOval(x, y, 40, 40);
		g2d.setColor(Color.WHITE);
		g2d.fillOval(x, y, 40, 40);
		g2d.setColor(Color.BLACK);
        g2d.drawString(
			((Symbol)tree.getElem()).getSymbol() == Symbols.INT ? 
			Integer.toString(((Symbol)tree.getElem()).getValue()) : 
			((Symbol)tree.getElem()).toString(),
			x+10, y+25);
    }

    private static void drawEdge(int x1, int y1, int x2, int y2, Graphics2D g2d) {
        g2d.drawLine(x1+20, y1+20, x2+20, y2+20);
    }

    private static int getHeight(BinTree tree) {
        if (tree == null) {
            return 0;
        } else {
            int leftHeight = getHeight(tree.getLeftChild());
            int rightHeight = getHeight(tree.getRightChild());
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static int getWidth(BinTree tree) {
        if (tree == null) {
            return 0;
        } else {
            int leftWidth = getWidth(tree.getLeftChild());
            int rightWidth = getWidth(tree.getRightChild());
            return leftWidth + rightWidth + 40;
        }
    }

}
