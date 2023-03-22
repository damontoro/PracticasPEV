package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;

public class CruceERX implements ICruce{

	@Override
	public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Problema problema, Random rand,
			double probCruce) {
		// TODO Por implementar
		throw new UnsupportedOperationException("Unimplemented method 'cruzar'");
	}
    
	@Override
	public String toString() {
		return "Cruce ERX";
	}
}
