package src;

import javax.swing.JFrame;

import src.utils.BinTree;
import src.vistas.MapView;
import src.vistas.TreeView;

public class MainPruebas {
	
	public static void main(String[] args) {
		AlgoritmoGenetico ag = new AlgoritmoGenetico();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1400, 800);
		frame.setVisible(true);
		frame.add(new TreeView(ag));
		ag.setNumGeneraciones(10);
		ag.setTamPoblacion(10);
		ag.run();
		System.out.println(((BinTree)ag.getMejorAbs().getGenotipo()).toString());

	}
}
