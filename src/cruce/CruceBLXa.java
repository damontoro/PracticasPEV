package src.cruce;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import src.individuo.Individuo;
import src.problema.Problema;

public class CruceBLXa implements ICruce, Cloneable{

	@Override
	public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Problema problema, Random rand, double probCruce) {
		ArrayList<Individuo> hijos = new ArrayList<Individuo>();
		Double alpha;
		for(int i = 0; i < padres.size(); i+=2){

			if(rand.nextDouble() > probCruce){
				hijos.add(padres.get(i));
				hijos.add(padres.get(i + 1));
				continue;
			}

			alpha = rand.nextDouble();
			ArrayList<Double> genotipoPadre1 = padres.get(i).getGenotipo();
			ArrayList<Double> genotipoPadre2 = padres.get(i + 1).getGenotipo();
			ArrayList<Double> genotipoHijo1 = new ArrayList<Double>();
			ArrayList<Double> genotipoHijo2 = new ArrayList<Double>();

			for(int j = 0; j < padres.get(i).getTamCromosoma(); j++){

				double min = Math.min(genotipoPadre1.get(j), genotipoPadre2.get(j));
				double max = Math.max(genotipoPadre1.get(j), genotipoPadre2.get(j));
				double rango = max - min;
				double minIntervaloHijo = min - alpha * rango;
				double maxIntervaloHijo = max + alpha * rango;

				if(rango == 0){
					genotipoHijo1.add(minIntervaloHijo);
					genotipoHijo2.add(minIntervaloHijo);
					continue;
				}
				genotipoHijo1.add(ThreadLocalRandom.current().nextDouble(minIntervaloHijo, maxIntervaloHijo));
				genotipoHijo2.add(ThreadLocalRandom.current().nextDouble(minIntervaloHijo, maxIntervaloHijo));
			}

			hijos.add(problema.build(0, genotipoHijo1));
			hijos.add(problema.build(0, genotipoHijo2));
		}
		return hijos;
	}
	
	@Override
	public CruceBLXa clone() { 
		try {
			return (CruceBLXa)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public String toString() {
		return "Cruce BLX";
	}
}
