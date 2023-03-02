package src.problema;

import java.util.ArrayList;

import src.individuo.Individuo;
import src.individuo.IndividuoBinario;
import src.utils.TipoProblema;

	
public class Problema3 extends Problema{
	private static final Double MAX = 5.0; 
	private static final Double MIN = -5.0;

	public Problema3() {
		super(MIN, MAX, 2);
		tipo = TipoProblema.MINIMIZACION;

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
		//Styblinsky-tang function
		double value = 0;
		for (int i = 0; i < dimension; i++)
			value += Math.pow(fenotipo.get(i), 4) - (16 * Math.pow(fenotipo.get(i), 2)) + (5 * fenotipo.get(i));

		value = value / 2;
		
		return value;
	}

	@Override
	public String toString() {
		return "Problema3";
	}
}
