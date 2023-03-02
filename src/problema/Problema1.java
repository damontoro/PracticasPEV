package src.problema;

import java.util.ArrayList;

import src.individuo.Individuo;
import src.individuo.IndividuoBinario;
import src.utils.TipoProblema;


public class Problema1 extends Problema{

	private static final Double [] MAX = {12.1, 5.8}; 
	private static final Double [] MIN = {-3.0, 4.1};

	public Problema1() {
		super(MIN, MAX, 2);
		tipo = TipoProblema.MAXIMIZACION;
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
		Double x = fenotipo.get(0);
		Double y = fenotipo.get(1);

		return (21.5 + x * Math.sin(4 * Math.PI * x) 
			+ y * Math.sin(20 * Math.PI * y));
	}

	@Override
	public String toString() {
		return "Problema1";
	}
}
