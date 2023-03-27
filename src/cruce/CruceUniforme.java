package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;

public class CruceUniforme implements ICruce, Cloneable{

	@Override
	public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Problema problema, Random rand,double probCruce) {
		ArrayList<Individuo> hijos = new ArrayList<Individuo>();
		for(int i = 0; i < padres.size(); i+=2){

			if(rand.nextDouble() > probCruce){
				hijos.add(padres.get(i));
				hijos.add(padres.get(i + 1));
				continue;
			}

			ArrayList<T> genotipoPadre1 = padres.get(i).getGenotipo();
			ArrayList<T> genotipoPadre2 = padres.get(i + 1).getGenotipo();
			ArrayList<T> genotipoHijo1 = new ArrayList<T>();
			ArrayList<T> genotipoHijo2 = new ArrayList<T>();

			for(int j = 0; j < padres.get(i).getTamCromosoma(); j++){
				if(rand.nextDouble() > 0.5){
					genotipoHijo1.add(genotipoPadre1.get(j));
					genotipoHijo2.add(genotipoPadre2.get(j));
				}else{
					genotipoHijo1.add(genotipoPadre2.get(j));
					genotipoHijo2.add(genotipoPadre1.get(j));
				}
			}

			hijos.add(problema.build(genotipoHijo1));
			hijos.add(problema.build(genotipoHijo2));
		}
		return hijos;
	}
	
	@Override
	public CruceUniforme clone() { 
		try {
			return (CruceUniforme)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public String toString() {
		return "Cruce Uniforme";
	}
}
