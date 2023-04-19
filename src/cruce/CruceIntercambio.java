package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;
import src.utils.BinTree;
import src.problema.ProblemaRegSim.Symbol;

public class CruceIntercambio implements ICruce{

	@Override
	public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Problema problema, Random rand,
			double probCruce) {
		ArrayList<Individuo> hijos = new ArrayList<Individuo>();

		for(int i = 0; i < padres.size() - (padres.size() % 2); i += 2){
			if(rand.nextDouble() > probCruce){
				hijos.add(padres.get(i));
				hijos.add(padres.get(i + 1));
				continue;
			}
			
			BinTree<Symbol> padre1 = padres.get(i).getGenotipo();
			BinTree<Symbol> padre2 = padres.get(i + 1).getGenotipo();

			//Hacer el intercambio
			
			hijos.add(problema.build(padres.get(i)));
			hijos.add(problema.build(padres.get(i + 1)));
		}
		return hijos;
	}
	
	@Override
	public String toString() {
		return "Cruce Intercambio (arboles)";
	}
}
