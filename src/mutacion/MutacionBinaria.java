package src.mutacion;

import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;


public class MutacionBinaria implements IMutacion, Cloneable{
	

	public MutacionBinaria() {
	}

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		for(int i = 0; i < individuo.getGenotipo().size(); i++){
			if(rand.nextDouble() < probMutacion){
				individuo.getGenotipo().set(i, rand.nextBoolean());
			}
		}
		return individuo;
	}

	public IMutacion clone() { 
		try {
			return (IMutacion)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public String toString() {
		return "Binaria";
	}
}
