package src.problema;

import java.util.ArrayList;
import java.util.Collections;

import src.individuo.Individuo;
import src.utils.MyInteger;

public abstract class Problema implements Cloneable{
	
	protected final ArrayList<Double> MIN;
	protected final ArrayList<Double> MAX;
	protected int dimension;
	protected MyInteger maxmin = new MyInteger(1);

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

	public int getDimension() {return dimension;}
	public void setDimension(int dimension) {
		if(dimension > MIN.size()){
			for(int i = MIN.size(); i < dimension; i++) {
				MIN.add(MIN.get(0));
				MAX.add(MAX.get(0));
			}
		}
		else if(dimension < MIN.size()){
			for(int i = MIN.size(); i > dimension; i--) {
				MIN.remove(i - 1);
				MAX.remove(i - 1);
			}
		}
		this.dimension = dimension;
	}

	public Problema clone() { 
		try {
			return (Problema)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	public void setmaxmin(int maxmin) {this.maxmin.set(maxmin);}
	public void setmaxin(MyInteger maxmin) {this.maxmin = maxmin;}
	public MyInteger getmaxmin() {return maxmin;}

	abstract public Individuo build(double precision);
	abstract public <T> Individuo build(double precision, ArrayList<T> valores);
	abstract public double evaluar(ArrayList<Double> fenotipo);

}