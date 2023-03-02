package src.problema;

import java.util.ArrayList;

import src.individuo.Individuo;
import src.individuo.IndividuoBinario;


public class Problema2 extends Problema{
	
	private static final Double MAX = 600.0;
	private static final Double MIN = -600.0;

	public Problema2() {
		super(MIN, MAX, 2);
	}
	
	@Override
	public Individuo build(double precision) {
		return new IndividuoBinario(super.MIN, super.MAX, precision);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Individuo build(double precision, ArrayList<T> valores) {
		return new IndividuoBinario(super.MIN, super.MAX, precision, (ArrayList<Boolean>) valores);
	}

	@Override
	public double evaluar(ArrayList<Double> fenotipo) {
		//Griewank function
		double sum = 0;
		for (int i = 0; i < dimension; i++)
			sum += Math.pow(fenotipo.get(i), 2) / 4000; //Calculamos el sumatorio
		
		double product = 1;
		for (int i = 0; i < dimension; i++)
			product *= Math.cos(fenotipo.get(i) / Math.sqrt(i + 1));//Calculamos el productorio

		return sum - product + 1;
	}
}
