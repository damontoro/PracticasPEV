package src;

import src.individuo.IndividuoEntero;
import src.problema.ProblemaGramEvo;


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
