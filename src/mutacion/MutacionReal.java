package src.mutacion;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import src.individuo.Individuo;
import src.problema.Problema;

public class MutacionReal implements IMutacion, Cloneable{

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		for(int i = 0; i < individuo.getGenotipo().size(); i++) {
			if(rand.nextDouble() < probMutacion) {
				double valor = ThreadLocalRandom.current().nextDouble(problema.getMin().get(i), problema.getMax().get(i));
				individuo.getGenotipo().set(i, valor);
			}
		}
		return individuo;
	}

	@Override
	public MutacionReal clone() { 
		try {
			return (MutacionReal)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public String toString() {
		return "Mutacion Real";
	}
	
}
