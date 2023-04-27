package src.problema;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.ProblemaRegSim.Symbol;
import src.utils.BinTree;

public class BloatingTarpeian implements IBloating{

	private final static double PROB_MUERTE = 0.33;

	@Override
	public void penalizar(ArrayList<Individuo> poblacion, Random rand) {
		double mediaAltura = 0;
		double sumFit = 0;

		for(Individuo i : poblacion){
			mediaAltura += ((BinTree<Symbol>)i.getGenotipo()).getHeight();
			sumFit += i.getFitness();
		}
		mediaAltura /= poblacion.size();

		for(Individuo i : poblacion){
			if(((BinTree<Symbol>)i.getGenotipo()).getHeight() > mediaAltura && rand.nextDouble() < PROB_MUERTE)
				i.setFitness(sumFit);
		}
	}
	
}
