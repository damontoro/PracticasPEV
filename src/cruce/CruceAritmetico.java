package src.cruce;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import src.individuo.Individuo;
import src.problema.Problema;

public class CruceAritmetico implements ICruce, Cloneable{


	@Override
	public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Problema problema, Random rand, double probCruce) {
		ArrayList<Individuo> hijos = new ArrayList<Individuo>();
		Double alpha;
		for(int i = 0; i < padres.size(); i+=2){

			alpha = ThreadLocalRandom.current().nextDouble(0.3, 0.7);
			if(rand.nextDouble() > probCruce){
				hijos.add(padres.get(i));
				hijos.add(padres.get(i + 1));
				continue;
			}

			ArrayList<Double> genotipoPadre1 = padres.get(i).getGenotipo();
			ArrayList<Double> genotipoPadre2 = padres.get(i + 1).getGenotipo();
			ArrayList<Double> genotipoHijo1 = new ArrayList<Double>();
			ArrayList<Double> genotipoHijo2 = new ArrayList<Double>();

			for(int j = 0; j < padres.get(i).getTamCromosoma(); j++){
				genotipoHijo1.add(genotipoPadre1.get(j)*alpha + genotipoPadre2.get(j)*(1-alpha));
				genotipoHijo2.add(genotipoPadre1.get(j)*(1-alpha) + genotipoPadre2.get(j)*alpha);
			}

			hijos.add(problema.build(0, genotipoHijo1));
			hijos.add(problema.build(0, genotipoHijo2));
		}
		return hijos;
	}
	
	
	@Override
	public CruceAritmetico clone() { 
		try {
			return (CruceAritmetico)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public String toString() {
		return "Cruce Aritmetico";
	}
}
