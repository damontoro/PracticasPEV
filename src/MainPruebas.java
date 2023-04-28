package src;

import javax.swing.JFrame;

import src.individuo.Individuo;
import src.individuo.IndividuoArboreo;
import src.individuo.IndividuoEntero;
import src.problema.ProblemaGramEvo;
import src.problema.ProblemaRegSim;
import src.problema.ProblemaRegSim.Symbol;
import src.problema.ProblemaRegSim.Symbol.Symbols;
import src.utils.BinTree;
import src.vistas.MapView;
import src.vistas.TreeView;

public class MainPruebas {
	
	public static void main(String[] args) {
		
		ProblemaGramEvo p = new ProblemaGramEvo();

		for(int i = 0; i < 100; i++) {
			IndividuoEntero AAAAA = new IndividuoEntero(7);
			//double fit = p.evaluar(AAAAA);
			System.out.println(p.decode(AAAAA.getGenotipo()) + " " + p.evaluar(AAAAA));
		}

	}
}
