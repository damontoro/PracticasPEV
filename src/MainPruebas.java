package src;

import javax.swing.JFrame;

import src.problema.ProblemaRegSim;
import src.problema.ProblemaRegSim.Symbol;
import src.utils.BinTree;
import src.vistas.MapView;
import src.vistas.TreeView;

public class MainPruebas {
	
	public static void main(String[] args) {
		AlgoritmoGenetico ag = new AlgoritmoGenetico();
		/*
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1400, 800);
		frame.setVisible(true);
		frame.add(new TreeView(ag));
		*/
		ag.setNumGeneraciones(100);
		ag.setTamPoblacion(100);
		ag.run();
		System.out.println((ag.getMejorAbs().getGenotipo()).toString());
		BinTree<Symbol> aux = new BinTree<Symbol>((BinTree<Symbol>)ag.getMejorAbs().getGenotipo());
		System.out.println(aux.toString());
	}
}
