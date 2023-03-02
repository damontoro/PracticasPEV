package src.problema;

import java.util.ArrayList;

import src.individuo.Individuo;
import src.individuo.IndividuoReal;

public class Problema4B extends Problema4A{
	
	public Problema4B(int dimension) {
		super(dimension);
	}

	@Override
	public Individuo build(double precision) {
		return new IndividuoReal(super.MIN, super.MAX, dimension);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Individuo build(double precision, ArrayList<T> valores) {
		return new IndividuoReal(super.MIN, super.MAX, dimension, (ArrayList<Double>) valores);
	}
}
