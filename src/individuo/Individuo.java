package src.individuo;

import java.util.ArrayList;

import java.util.Random;

public abstract class Individuo {
	
	static protected Integer tamCromosoma = null;
	static protected Random random = new Random();

	protected double fitness;

	public Individuo() {

	}

	public Individuo(Individuo i) {
		this.fitness = i.fitness;
	}
	
	abstract public <T> ArrayList<T> getFenotipo();
	abstract public <T> ArrayList<T> getGenotipo();

	public void setFitness(double fitness) { this.fitness = fitness; }
	public static void setTamCromosoma(Integer tamCrom) { tamCromosoma = tamCrom; }
	public double getFitness() { return fitness; }
	public Integer getTamCromosoma() { return tamCromosoma; }



}
