package src.vistas;

import java.util.ArrayList;

import src.problema.ProblemaTSP;
import src.utils.ArrowDrawer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SpainMap extends JLabel{
	
	private static final int POINT_SIZE = 10;
	private static final ImageIcon icon = new ImageIcon("assets/espana.gif");
	ArrayList<Integer> fenotipo = null;

	public SpainMap() {
		super();
		this.setIcon(icon);
	}

	public void setFenotipo(ArrayList<Integer> fenotipo) {
		this.fenotipo = fenotipo;
	}

	@Override
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		if(fenotipo == null) return;
		graphics.fillOval((int)points[ProblemaTSP.Ciudad.MADRID.getValue()].getX() - POINT_SIZE/2, 
											(int)points[ProblemaTSP.Ciudad.MADRID.getValue()].getY()  - POINT_SIZE/2, 
											POINT_SIZE, POINT_SIZE);

		ArrowDrawer.drawArrow(points[ProblemaTSP.Ciudad.MADRID.getValue()], points[fenotipo.get(0)], g);
		
		for (int i = 0; i < fenotipo.size() - 1; i++) {
			graphics.fillOval((int)points[i].getX() - POINT_SIZE/3, 
											(int)points[i].getY()  - POINT_SIZE/3, 
											POINT_SIZE, POINT_SIZE);
			ArrowDrawer.drawArrow(points[fenotipo.get(i)], points[fenotipo.get(i + 1)], g);
		}

		ArrowDrawer.drawArrow(points[fenotipo.get(fenotipo.size() - 1)], points[ProblemaTSP.Ciudad.MADRID.getValue()], g);
	}

	private static final Point[] points = {
		new Point(340,313),
		new Point(429,315),new Point(318,417),
		new Point(192,213),new Point(125,322),new Point(527,145),
		new Point(306,38),new Point(261,116),new Point(125,247),new Point(148,444),
		new Point(428,223),new Point(254,306),new Point(201,351),new Point(38,59),new Point(329,243),
		new Point(558,92),new Point(243,408),new Point(299,184),new Point(94,380),new Point(424,98),new Point(275,360),
		new Point(160,82),new Point(483,84),new Point(317,96),new Point(94,24),new Point(263,217),new Point(206,432),new Point(388,354)
	};
}
