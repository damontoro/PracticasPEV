package src.individuo;

import java.util.ArrayList;

import java.util.Random;

public abstract class Individuo {
	
	static protected Integer tamCromosoma;
	static protected Random random = new Random();

	static protected ArrayList<Integer> tamGenes;

	protected ArrayList<Double> min, max;
	protected double fitness;

	public Individuo(ArrayList<Double> min, ArrayList<Double> max, double precision) {
		this.min = min;
		this.max = max;

		tamGenes = new ArrayList<Integer>();
		tamCromosoma = null;
	}

	public <T> Individuo(ArrayList<Double> min, ArrayList<Double> max, double precision, ArrayList<T> valores) {
		this.min = min;
		this.max = max;

		tamGenes = new ArrayList<Integer>();
		tamCromosoma = null;
	}


	abstract public int tamGen(double valorError, double min, double max, double precision);
	abstract public ArrayList<Double> getFenotipo();
	
	public void setFitness(double fitness) { this.fitness = fitness; }
	public double getFitness() { return fitness; }
	public Integer getTamCromosoma() { return tamCromosoma; }

	abstract public <T> ArrayList<T> getGenotipo();
}
