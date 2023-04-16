package src.vistas;

import java.util.ArrayList;

import src.utils.ArrowDrawer;
import src.utils.BinTree;
import src.utils.TreeDrawer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class SpainMap extends JLabel{
	
	private static final int POINT_SIZE = 10;
	final public ImageIcon icon = new ImageIcon(getClass().getResource("/assets/espana.gif"));
	BinTree fenotipo = null;

	public SpainMap() {
		super();
	}

	public void setFenotipo(BinTree fenotipo) {
		this.fenotipo = fenotipo;
		this.setIcon(icon);
	}

	@Override
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		//TreeDrawer.paintTree(fenotipo, g, 0, 0);
	}
}
