package src.utils;

import java.awt.*;
import java.awt.geom.*;

import java.awt.Point;

public class ArrowDrawer {
	
	private static int STROKE_WIDTH = 3;

	private ArrowDrawer() {

	}

	public static void drawArrow(Point from, Point to, Graphics2D g) {
		g.setStroke(new BasicStroke(STROKE_WIDTH));
		drawParabola(from, to, g);
	}

	private static void drawParabola(Point from, Point to, Graphics2D g2d) {
		//Draw a line between the points
		//La x es absoluta, la Y es la y que le pones - la altura/2
		int dist =(int) Math.sqrt((to.x - from.x) * (to.x - from.x) + (to.y - from.y) * (to.y - from.y));
		double angle = Math.toDegrees(Math.atan2(to.y - from.y, to.x - from.x));
		double ar = dist/2;

		AffineTransform old = g2d.getTransform();
		Arc2D arc = new Arc2D.Double(from.x, from.y - ar/2, dist, ar, 0, 180, Arc2D.OPEN);
		
		g2d.rotate(Math.toRadians(angle), from.x, from.y);
		g2d.draw(arc);
		drawArrowHead(g2d, arc);
		g2d.setTransform(old);

	}

	private static void drawArrowHead(Graphics2D g2d, Arc2D arc) {
		//Punto medio: (int)arc.getCenterX(), (int)(arc.getCenterY() - arc.getHeight()/2)

		AffineTransform old = g2d.getTransform();
		Polygon arrowHead = new Polygon();

		g2d.rotate(Math.toRadians(-90), (int)arc.getCenterX(), (int)(arc.getCenterY() - arc.getHeight()/2));
		arrowHead.addPoint((int)arc.getCenterX(), (int)(arc.getCenterY() - arc.getHeight()/2));
		arrowHead.addPoint((int)arc.getCenterX() - 10, (int)(arc.getCenterY() - arc.getHeight()/2) - 10);
		arrowHead.addPoint((int)arc.getCenterX() + 10, (int)(arc.getCenterY() - arc.getHeight()/2) - 10);
		g2d.fill(arrowHead);
		g2d.setTransform(old);
	}

}
