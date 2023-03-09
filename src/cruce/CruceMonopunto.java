package src.cruce;

import src.individuo.Individuo;
import src.problema.Problema;

import java.util.ArrayList;
import java.util.Random;

public class CruceMonopunto implements ICruce, Cloneable{
	
	private int prueba = 0;

	@Override
	public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Problema problema, Random rand, double probCruce) {
		ArrayList<Individuo> hijos = new ArrayList<Individuo>();
		for(int i = 0; i < padres.size(); i+=2){

			if(rand.nextDouble() < probCruce){
				hijos.add(padres.get(i));
				hijos.add(padres.get(i + 1));
				continue;
			}

			int puntoCruce = rand.nextInt(padres.get(i).getTamCromosoma());
			
			ArrayList<T> genotipo1 = padres.get(i).getGenotipo();
			ArrayList<T> genotipo2 = padres.get(i + 1).getGenotipo();
			ArrayList<T> genotipoHijo1 = new ArrayList<T>();
			ArrayList<T> genotipoHijo2 = new ArrayList<T>();

			for(int j = 0; j < puntoCruce; j++){
				genotipoHijo1.add(genotipo1.get(j));
				genotipoHijo2.add(genotipo2.get(j));
			}
			for(int j = puntoCruce; j < padres.get(i).getTamCromosoma(); j++){
				genotipoHijo1.add(genotipo2.get(j));
				genotipoHijo2.add(genotipo1.get(j));
			}
			hijos.add(problema.build(genotipoHijo1));
			hijos.add(problema.build(genotipoHijo2));
		}
		return hijos;
	}

	public int getPrueba() {return prueba;}
	public void setPrueba(int prueba) {this.prueba = prueba;}

	public ICruce clone() { 
		try {
			return (ICruce)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public String toString() {
		return "Cruce Monopunto";
	}
}
