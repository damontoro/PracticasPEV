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
		
		IndividuoEntero i = new IndividuoEntero(8);
		ProblemaGramEvo p = new ProblemaGramEvo();

		System.out.println(p.evaluar(i));

	}
}
