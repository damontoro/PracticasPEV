package src.problema;

import java.util.ArrayList;
import java.util.Collections;

import src.individuo.Individuo;

public abstract class Problema {
	
	protected final ArrayList<Double> MIN;
	protected final ArrayList<Double> MAX;
	protected int dimension;

	public Problema(double min, double max, int dimension) {
		MIN = new ArrayList<Double>(dimension);
		MAX = new ArrayList<Double>(dimension);
		for(int i = 0; i < dimension; i++) {
			MIN.add(min);
			MAX.add(max);
		}
		this.dimension = dimension;
	}

	public Problema(Double[] min, Double[] max, int dimension) {
		MIN = new ArrayList<Double>(dimension);
		MAX = new ArrayList<Double>(dimension);
		Collections.addAll(MIN, min);
		Collections.addAll(MAX, max);
		this.dimension = dimension;
	}

	abstract public Individuo<?> build(float precision);
	abstract public double evaluar(ArrayList<Double> fenotipo);
}
