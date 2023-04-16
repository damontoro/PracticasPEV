package src;

import javax.swing.JFrame;

import src.utils.BinTree;
import src.vistas.MapView;

public class MainPruebas {
	
	public static void main(String[] args) {
		AlgoritmoGenetico ag = new AlgoritmoGenetico();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.add(new MapView(ag));
		ag.setNumGeneraciones(10);
		ag.setTamPoblacion(10);
		ag.run();
		System.out.println(((BinTree)ag.getMejorAbs().getGenotipo()).toString());

	}
}
