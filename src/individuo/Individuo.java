package src.individuo;

import java.util.ArrayList;

import java.util.Random;

public abstract class Individuo<T> {
	
	static protected Integer tamCromosoma;
	static protected Random random;
	static protected ArrayList<Integer> tamGenes;

	protected ArrayList<T> cromosoma;
	protected ArrayList<Double> min, max;
	protected double fitness;

	public Individuo(ArrayList<Double> min, ArrayList<Double> max, float precision) {
		this.min = min;
		this.max = max;

		tamGenes = new ArrayList<Integer>();
		tamCromosoma = null;
		random = new Random();
	}

	public Individuo(ArrayList<Double> min, ArrayList<Double> max, float precision, ArrayList<T> valores) {
		
	}

	abstract public int tamGen(double valorError, double min, double max, float precision);
	abstract public ArrayList<Double> getFenotipo();
	
	public void setFitness(double fitness) { this.fitness = fitness; }
	public double getFitness() { return fitness; }
}
