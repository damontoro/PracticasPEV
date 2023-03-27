package src.vistas;

import src.AlgoritmoGenetico;
import src.individuo.Individuo;
import src.patrones.AGobserver;
import src.utils.ArrowDrawer;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class MapView extends JPanel implements AGobserver{
	
	SpainMap imagen;

	public MapView(AlgoritmoGenetico ag) {

		ag.addObserver(this);
		//imagen = new JLabel(icon);
		imagen = new SpainMap();
		this.setMaximumSize(new Dimension(592, 539));
		JButton b1 = new JButton("Flecha");
		JButton b2 = new JButton("Save Positions");
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.add(imagen);
		//this.add(b1);
		//this.add(b2);
	}
		/*ArrayList<Point> points = new ArrayList<Point>();
		imagen.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Point b = evt.getPoint();
				points.add(b);
				imagen.getGraphics().fillOvpoints[fenotipo.get(i)].getX()  - POINT_SIZE/2, b.y  - POINT_SIZE/2, POINT_SIZE, POINT_SIZE);
			}
		});

		
		//Paint a line between the points when i press the button b1
		b1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				for (int i = 0; i < points.size() - 1; i++) {
					ArrowDrawer.drawArrow(points.get(i), points.get(i + 1), imagen);
				}
			}
		});

		//Save the points when i press the button b2
		b2.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				for (int i = 0; i < points.size(); i++) {
					try {
						BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("out/points.txt"));
						writePoint(points.get(0), out, true);
						for(int j = 1; j < points.size(); j++) {
							writePoint(points.get(j), out, false);
						}
						out.close();
					} catch (Exception e) {
						System.out.println("Error: " + e);
					}
				}
			}
		});
	}
	*/

	private void writePoint(Point point, BufferedOutputStream out, boolean ini) {
		StringBuilder a = new StringBuilder();
		if(!ini) 
			a.append(",");
		a.append("{");
		a.append(point.x);
		a.append(",");
		a.append(point.y);
		a.append("}");
		try{
			out.write(a.toString().getBytes());
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	@Override
	public void onInit(AlgoritmoGenetico ag) {
	}

	@Override
	public void onChange(AlgoritmoGenetico ag) {}

	@Override
	public void onError(String err) {}

	@Override
	public void onEnd(AlgoritmoGenetico ag) {
		imagen.setFenotipo(ag.getMejorAbs().getFenotipo());
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				imagen.repaint();
			}
		});
	}



}
