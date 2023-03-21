package src;

import src.vistas.MapView;
import javax.swing.JFrame;

public class MainPruebas {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(new MapView());
		f.pack();
		f.setLocation(200,200);
		f.setVisible(true);
	}
}
