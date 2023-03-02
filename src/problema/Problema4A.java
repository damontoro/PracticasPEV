package src.problema;

import java.util.ArrayList;

import src.individuo.Individuo;
import src.individuo.IndividuoBinario;
import src.utils.MyInteger;

public class Problema4A extends Problema{
	

	private static final Double _MAX = Math.PI;
	private static final Double _MIN = 0.0;

	public Problema4A(int dimension) {
		super(_MIN, _MAX, dimension);
		optimizacion = new MyInteger(-1);
	}
	
	@Override
	public Individuo build(double precision) {
		return new IndividuoBinario(super.MIN, super.MAX, precision);
	}

	@Override
	
	public <T> Individuo build(double precision, ArrayList<T> valores) {
		return new IndividuoBinario(super.MIN, super.MAX, precision, (ArrayList<Boolean>) valores);
	}

	@Override
	public double evaluar(ArrayList<Double> fenotipo) {
		int m = 10;

		//Michalewicz function
		double sum = 0;
		for(int i = 0; i < dimension; i++)
			sum += Math.sin(fenotipo.get(i)) * 
					Math.pow(Math.sin((i + 1) * Math.pow(fenotipo.get(i), 2) / Math.PI), 2 * m);

		sum = -sum;
		return optimizacion.get() * (sum);
	}

	@Override
	public String toString() {
		return "Problema4A";
	}
}
