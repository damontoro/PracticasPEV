package src.utils;

import javax.swing.JLabel;

import java.awt.*;
import java.awt.geom.*;

import java.awt.Point;

public class ArrowDrawer {
	
	private static int STROKE_WIDTH = 2;

	private ArrowDrawer() {

	}

	public static void drawParabola(Point from, Point to, JLabel label) {
		//Draw a line between the points
		//La x es absoluta, la Y es la y que le pones - la altura/2
		Graphics2D g2d = (Graphics2D) label.getGraphics();
		int dist =(int) Math.sqrt((to.x - from.x) * (to.x - from.x) + (to.y - from.y) * (to.y - from.y));
		double angle = Math.toDegrees(Math.atan2(to.y - from.y, to.x - from.x));
		double ar = Math.max(150 - dist, dist/2);

		AffineTransform old = g2d.getTransform();
		Arc2D arc = new Arc2D.Double(from.x, from.y - ar/2, dist, ar, 0, 180, Arc2D.OPEN);
		g2d.setStroke(new BasicStroke(STROKE_WIDTH));
		g2d.rotate(Math.toRadians(angle), from.x, from.y);
		g2d.draw(arc);
		g2d.setTransform(old);

	}
}
