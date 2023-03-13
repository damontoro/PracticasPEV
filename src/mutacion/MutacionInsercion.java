package src.mutacion;

import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;

public class MutacionInsercion implements IMutacion{

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mutar'");
	}
	
	@Override
	public String toString() {
		return "Mutacion Insercion";
	}
}
