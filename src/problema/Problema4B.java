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
	public <T> Individuo build(ArrayList<T> valores) {
		return new IndividuoReal(valores);
	}

	public Individuo build(Individuo ind) {
		return new IndividuoReal((IndividuoReal) ind);
	}

	@Override
	public String toString() {
		return "Problema4B";
	}
}
