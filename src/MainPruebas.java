package src;

import javax.swing.JFrame;

import src.individuo.Individuo;
import src.individuo.IndividuoArboreo;
import src.problema.ProblemaRegSim;
import src.problema.ProblemaRegSim.Symbol;
import src.problema.ProblemaRegSim.Symbol.Symbols;
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

		IndividuoArboreo aux = new IndividuoArboreo((IndividuoArboreo)(ag.getMejorAbs()));

		aux.getGenotipo().getElem().setSymbol(Symbols.X);

	}
}
