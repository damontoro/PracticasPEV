package src.individuo;

import java.util.ArrayList;

import java.util.Random;

public abstract class Individuo {
	
	static protected Integer tamCromosoma = null;
	static protected Random random = new Random();

	static protected ArrayList<Integer> tamGenes = null;

	protected ArrayList<Double> min, max;
	protected double fitness;

	public Individuo() {
	}

	public Individuo(ArrayList<Double> min, ArrayList<Double> max) {
		this.min = min;
		this.max = max;
		if(tamGenes == null) 
			tamGenes = new ArrayList<Integer>();
	}
	public <T> Individuo(ArrayList<T> valores) {
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

	public static void setTamGenes(ArrayList<Integer> n) {tamGenes = n;}

	


}
