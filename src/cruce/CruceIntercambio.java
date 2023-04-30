package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;
import src.utils.BinTree;
import src.utils.Pair;
import src.problema.ProblemaRegSim.Symbol;

public class CruceIntercambio implements ICruce{

	private static final double INI_PROB_PARAR = 0.9;
	private static final double INCREMENTO = 0.0;

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

			BinTree<Symbol> hijo1 = padre1;
			BinTree<Symbol> hijo2 = padre2;

			hijo1 = buscarNodo(hijo1, rand);
			hijo2 = buscarNodo(hijo2, rand);
			BinTree<Symbol> aux = new BinTree<Symbol>(hijo1);


			hijo1.setTree(hijo2);
			hijo2.setTree(aux);

			//Hacer el intercambio
			
			hijos.add(problema.build(padres.get(i)));
			hijos.add(problema.build(padres.get(i + 1)));
		}
		return hijos;
	}

	private BinTree<Symbol> buscarNodo(BinTree<Symbol> tree, Random rand){
		boolean parar = false;

		while(!parar && !tree.isLeaf()){
			if(rand.nextDouble() < 0.5){
				tree = tree.getLeftChild();
			}
			else{
				tree = tree.getRightChild();
			}
			parar = rand.nextDouble() < INI_PROB_PARAR;
		}
		return tree;
	}
	
	@Override
	public String toString() {
		return "Cruce Intercambio (arboles)";
	}
}
